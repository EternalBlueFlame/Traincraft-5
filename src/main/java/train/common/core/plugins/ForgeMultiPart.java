package train.common.core.plugins;

import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import net.minecraft.item.ItemSlab;
import train.common.library.BlockIDs;

public class ForgeMultiPart
{
    public static void registerBlocks()
    {
        try
        {
            for (BlockIDs block : BlockIDs.values())
            {
                // Blocks that don't make since to register
                if (block.itemBlockClass != ItemSlab.class)
                {
                    switch (block.MaxMetadata)
                    {
                        case -1:
                            // When the MaxMetaData is -1 this indicates that this block should never be allowed to be made a Multipart
                            // Blocks such as doors will crash the mod
                            break;
                        case 0:
                            MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block.block, 0), BlockMicroMaterial.materialKey(block.block, 0));
                            break;
                        default:
                            for (byte i = 0; i <= block.MaxMetadata; i++)
                            {
                                MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block.block, i), BlockMicroMaterial.materialKey(block.block, i));
                            }
                            break;
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
