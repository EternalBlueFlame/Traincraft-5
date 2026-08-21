package train.common.core.compat;

import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

import java.util.HashMap;
import java.util.Map;

public class DataWatcher {
	private static final Map<String, DataParameter<?>> KEYS = new HashMap<String, DataParameter<?>>();
	private final Entity entity;
	private final Map<Integer, DataParameter<?>> local = new HashMap<Integer, DataParameter<?>>();

	public DataWatcher(Entity entity) {
		this.entity = entity;
	}

	public void addObject(int id, Object value) {
		DataParameter param = parameter(id, value);
		local.put(id, param);
		try {
			entity.getDataManager().register(param, boxed(value));
		} catch (IllegalArgumentException e) {
			entity.getDataManager().set(param, boxed(value));
		}
	}

	public void updateObject(int id, Object value) {
		DataParameter param = local.get(id);
		if (param == null) {
			addObject(id, value);
			return;
		}
		entity.getDataManager().set(param, boxed(value));
	}

	public int getWatchableObjectInt(int id) {
		Object v = entity.getDataManager().get(local.get(id));
		return v instanceof Number ? ((Number) v).intValue() : 0;
	}

	public String getWatchableObjectString(int id) {
		Object v = entity.getDataManager().get(local.get(id));
		return v == null ? "" : v.toString();
	}

	public float getWatchableObjectFloat(int id) {
		Object v = entity.getDataManager().get(local.get(id));
		return v instanceof Number ? ((Number) v).floatValue() : 0F;
	}

	public byte getWatchableObjectByte(int id) {
		Object v = entity.getDataManager().get(local.get(id));
		return v instanceof Number ? ((Number) v).byteValue() : 0;
	}

	private DataParameter parameter(int id, Object value) {
		String key = entity.getClass().getName() + ":" + id;
		DataParameter existing = KEYS.get(key);
		if (existing != null) {
			return existing;
		}
		DataParameter created;
		if (value instanceof String) {
			created = EntityDataManager.createKey(entity.getClass(), DataSerializers.STRING);
		} else if (value instanceof Float) {
			created = EntityDataManager.createKey(entity.getClass(), DataSerializers.FLOAT);
		} else if (value instanceof Byte) {
			created = EntityDataManager.createKey(entity.getClass(), DataSerializers.BYTE);
		} else if (value instanceof Boolean) {
			created = EntityDataManager.createKey(entity.getClass(), DataSerializers.BOOLEAN);
		} else {
			created = EntityDataManager.createKey(entity.getClass(), DataSerializers.VARINT);
		}
		KEYS.put(key, created);
		return created;
	}

	private static Object boxed(Object value) {
		if (value instanceof Byte || value instanceof Boolean || value instanceof Float || value instanceof String) {
			return value;
		}
		if (value instanceof Number) {
			return Integer.valueOf(((Number) value).intValue());
		}
		return value;
	}
}
