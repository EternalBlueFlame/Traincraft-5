package train.common.blocks.tracks.kits;

import ebf.tim.utility.CommonUtil;
import mods.railcraft.api.tracks.IOutfittedTrackTile;
import mods.railcraft.api.tracks.ITrackKitInstance;
import mods.railcraft.api.tracks.ITrackKitPowered;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import train.common.api.ElectricTrain;
import train.common.core.handlers.ConfigHandler;

import javax.annotation.Nullable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Supplies energy to Traincraft electric locomotives.
 * <p>
 * Default mode: charges itself while powered by redstone.
 * With {@code ENERGYTRACK_USES_RF}: pulls Forge Energy (RF) from neighbours below/next to it
 * and balances its charge with neighbouring energy tracks.
 * (1.7.10 used Railcraft's IElectricGrid and the CoFH RF API; both are gone in 1.12.)
 *
 * @author Spitfire4466, TheBlueCrystal (RF support)
 */
public class TrackKitEnergy extends TrackKitTraincraft implements ITrackKitPowered {
    public static final int MAX_ENERGY = 2000;
    private static final int PULL_PER_SIDE = 100;
    private static final EnumFacing[] HORIZONTALS = EnumFacing.HORIZONTALS;

    private double charge = 0;
    private boolean powered = false;
    private int updateTicks = 0;

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.ENERGY;
    }

    public double getCharge() {
        return this.charge;
    }

    private void addCharge(double amount) {
        this.charge = Math.max(0, Math.min(MAX_ENERGY, this.charge + amount));
    }

    @Override
    public void update() {
        if (!isServer()) {
            return;
        }
        this.updateTicks++;
        if (!ConfigHandler.ENERGYTRACK_USES_RF) {
            if (isPowered() && this.updateTicks % 2 == 0) {
                addCharge(1);
            }
        } else if (this.updateTicks % 10 == 0) {
            pullEnergy();
            balanceWithNeighbours();
        }
        if (this.updateTicks % 50 == 0) {
            sendUpdateToClient();
        }
    }

    private void pullEnergy() {
        BlockPos pos = getPos();
        pullFrom(pos.down(), EnumFacing.UP);
        for (EnumFacing side : HORIZONTALS) {
            if (this.charge >= MAX_ENERGY) {
                return;
            }
            pullFrom(pos.offset(side), side.getOpposite());
            pullFrom(pos.offset(side).down(), side.getOpposite());
        }
    }

    private void pullFrom(BlockPos source, EnumFacing face) {
        if (this.charge >= MAX_ENERGY) {
            return;
        }
        TileEntity te = getWorld().getTileEntity(source);
        if (te != null && te.hasCapability(CapabilityEnergy.ENERGY, face)) {
            IEnergyStorage storage = te.getCapability(CapabilityEnergy.ENERGY, face);
            if (storage != null && storage.canExtract()) {
                int wanted = (int) Math.min(PULL_PER_SIDE, MAX_ENERGY - this.charge);
                addCharge(storage.extractEnergy(wanted, false));
            }
        }
    }

    private void balanceWithNeighbours() {
        BlockPos pos = getPos();
        for (EnumFacing side : HORIZONTALS) {
            BlockPos n = pos.offset(side);
            for (BlockPos p : new BlockPos[]{n, n.down(), n.up()}) {
                TrackKitEnergy other = getEnergyKit(p);
                if (other != null && (int) other.charge - (int) this.charge > 1) {
                    double diff = (other.charge - this.charge) / 2.0;
                    this.addCharge(diff);
                    other.addCharge(-diff);
                }
            }
        }
    }

    @Nullable
    private TrackKitEnergy getEnergyKit(BlockPos pos) {
        TileEntity te = getWorld().getTileEntity(pos);
        if (te instanceof IOutfittedTrackTile) {
            ITrackKitInstance kit = ((IOutfittedTrackTile) te).getTrackKitInstance();
            if (kit instanceof TrackKitEnergy) {
                return (TrackKitEnergy) kit;
            }
        }
        return null;
    }

    @Override
    public boolean onCrowbarWhack(EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem) {
        if (isServer()) {
            CommonUtil.sendChat(player, "stored: " + (int) this.charge + "/" + MAX_ENERGY + " RF");
        }
        return true;
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        if (!(cart instanceof ElectricTrain)) {
            return;
        }
        ElectricTrain train = (ElectricTrain) cart;
        if (this.charge > 20 && train.fuelTrain < train.maxEnergy) {
            double transferred = this.charge * 0.05;
            train.fuelTrain += transferred;
            addCharge(-transferred);
        }
    }

    @Override
    public int getRenderState() {
        return this.charge > 0 ? 1 : 0;
    }

    @Override
    public boolean isPowered() {
        return this.powered;
    }

    @Override
    public void setPowered(boolean powered) {
        this.powered = powered;
    }

    @Override
    public int getPowerPropagation() {
        return 5;
    }

    @Override
    public boolean canPropagatePowerTo(ITrackKitInstance track) {
        return true;
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setDouble("energy", this.charge);
        data.setBoolean("powered", this.powered);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.charge = data.getDouble("energy");
        this.powered = data.getBoolean("powered");
    }

    @Override
    public void writePacketData(DataOutputStream data) throws IOException {
        super.writePacketData(data);
        data.writeDouble(this.charge);
        data.writeBoolean(this.powered);
    }

    @Override
    public void readPacketData(DataInputStream data) throws IOException {
        super.readPacketData(data);
        this.charge = data.readDouble();
        this.powered = data.readBoolean();
        markBlockNeedsUpdate();
    }
}
