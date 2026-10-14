package train.common.core.util;

import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Keeps the 1.7.10 {@code DataWatcher} API ({@code addObject(id, value)}, {@code getWatchableObjectInt(id)},
 * {@code updateObject(id, value)}) on top of 1.12's {@link EntityDataManager}, so the entity code using
 * numeric slots keeps working.
 * <p>
 * 1.12 needs every synced value to have a {@link DataParameter} created once per class, in the same order
 * on client and server. {@link #registerFamily(Class)} must be called from a static initializer of the base
 * class of an entity family (e.g. {@code AbstractTrains}); it creates a fixed set of keys - one per
 * slot id (0-31) and value type (int, float, byte, String) - so the numbering never depends on which entity
 * happens to be created first. An entity only registers the slots it actually uses.
 * <p>
 * Like the old DataWatcher, a slot id holds one value per entity: re-adding an id just updates it, and
 * values are converted between number types / String when a slot is read or written with another type.
 */
public final class LegacyDataWatcher {
    public static final int SLOTS = 32;

    private enum Kind {
        INT(DataSerializers.VARINT), FLOAT(DataSerializers.FLOAT), BYTE(DataSerializers.BYTE), STRING(DataSerializers.STRING);

        final DataSerializer<?> serializer;

        Kind(DataSerializer<?> serializer) {
            this.serializer = serializer;
        }

        static Kind of(Object value) {
            if (value instanceof Float || value instanceof Double) return FLOAT;
            if (value instanceof Byte || value instanceof Boolean) return BYTE;
            if (value instanceof Number) return INT;
            return STRING;
        }
    }

    private static final Map<Class<? extends Entity>, DataParameter<?>[][]> FAMILIES = new HashMap<>();

    private final Entity entity;
    private final DataParameter<?>[][] keys;
    private final Kind[] registered = new Kind[SLOTS];

    /** Call once, from a static initializer of the family's base class. */
    @SuppressWarnings("unchecked")
    public static synchronized void registerFamily(Class<? extends Entity> base) {
        if (FAMILIES.containsKey(base)) {
            return;
        }
        DataParameter<?>[][] keys = new DataParameter<?>[SLOTS][Kind.values().length];
        for (int id = 0; id < SLOTS; id++) {
            for (Kind kind : Kind.values()) {
                keys[id][kind.ordinal()] = EntityDataManager.createKey(base, (DataSerializer<Object>) kind.serializer);
            }
        }
        FAMILIES.put(base, keys);
    }

    public LegacyDataWatcher(Entity entity, Class<? extends Entity> familyBase) {
        this.entity = entity;
        DataParameter<?>[][] k = FAMILIES.get(familyBase);
        if (k == null) {
            throw new IllegalStateException("LegacyDataWatcher.registerFamily(" + familyBase.getSimpleName() + ".class) was not called");
        }
        this.keys = k;
    }

    @SuppressWarnings("unchecked")
    private <T> DataParameter<T> key(int id, Kind kind) {
        return (DataParameter<T>) this.keys[id][kind.ordinal()];
    }

    private static Object convert(Object value, Kind kind) {
        switch (kind) {
            case INT:
                if (value instanceof Number) return ((Number) value).intValue();
                if (value instanceof Boolean) return (Boolean) value ? 1 : 0;
                try {
                    return Integer.parseInt(String.valueOf(value).trim());
                } catch (NumberFormatException e) {
                    return 0;
                }
            case FLOAT:
                if (value instanceof Number) return ((Number) value).floatValue();
                try {
                    return Float.parseFloat(String.valueOf(value).trim());
                } catch (NumberFormatException e) {
                    return 0F;
                }
            case BYTE:
                if (value instanceof Number) return ((Number) value).byteValue();
                if (value instanceof Boolean) return (byte) ((Boolean) value ? 1 : 0);
                return (byte) 0;
            default:
                return value == null ? "" : String.valueOf(value);
        }
    }

    /** 1.7.10 {@code DataWatcher.addObject}. Call from {@code entityInit()}. */
    public void addObject(int id, Object value) {
        if (this.registered[id] != null) {
            updateObject(id, value);
            return;
        }
        Kind kind = Kind.of(value);
        this.entity.getDataManager().register(key(id, kind), convert(value, kind));
        this.registered[id] = kind;
    }

    /** 1.7.10 {@code DataWatcher.updateObject}. */
    public void updateObject(int id, Object value) {
        Kind kind = this.registered[id];
        if (kind == null) {
            addObject(id, value);
            return;
        }
        this.entity.getDataManager().set(key(id, kind), convert(value, kind));
    }

    private Object get(int id) {
        Kind kind = this.registered[id];
        if (kind == null) {
            return null;
        }
        return this.entity.getDataManager().get(key(id, kind));
    }

    public int getWatchableObjectInt(int id) {
        Object v = get(id);
        return v == null ? 0 : (Integer) convert(v, Kind.INT);
    }

    public float getWatchableObjectFloat(int id) {
        Object v = get(id);
        return v == null ? 0F : (Float) convert(v, Kind.FLOAT);
    }

    public byte getWatchableObjectByte(int id) {
        Object v = get(id);
        return v == null ? 0 : (Byte) convert(v, Kind.BYTE);
    }

    public short getWatchableObjectShort(int id) {
        return (short) getWatchableObjectInt(id);
    }

    public String getWatchableObjectString(int id) {
        Object v = get(id);
        return v == null ? "" : (String) convert(v, Kind.STRING);
    }
}
