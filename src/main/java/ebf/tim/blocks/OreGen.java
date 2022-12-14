package ebf.tim.blocks;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Arrays;
import java.util.Random;

/**
 * <h1>Ore generation</h1>
 * spawns liquids and ores in the world.
 * @author Eternal Blue Flame
 */
public class OreGen implements IWorldGenerator{
	private Block ore, notOre = Blocks.stone;
	private int minY, maxY,veinSize, minOres, maxVeinsPerChunk;
	private Integer[] dimensions = new Integer[]{0};
	private String[] biomes;
	private int heightOffset=0;

	public OreGen(Block oreBlock, int yMin, int yMax, int maxVeinSize, int minOreBlocks, int maxVeins){
		ore=oreBlock;
		minY=yMin;
		maxY=yMax;
		veinSize=maxVeinSize;
		minOres=minOreBlocks;
		maxVeinsPerChunk=maxVeins;
	}

	public OreGen setFiller(Block filler){
		notOre=filler;
		return this;
	}

	public OreGen setBiomes(String[] allowedBiomes){
		biomes=allowedBiomes;
		return this;
	}

	public OreGen setDimensions(Integer[] allowedDimensions){
		dimensions=allowedDimensions;
		return this;
	}

	public OreGen setHeightOffset(int offset){
		heightOffset=offset;
		return this;
	}

	public void register(){

	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		//be sure it's the correct dimension
		if ((dimensions==null || Arrays.asList(dimensions).contains(world.provider.dimensionId))
				//be sure it's the correct biome
				&& (biomes==null || Arrays.asList(biomes).contains(world.getBiomeGenForCoords(chunkX,chunkZ).biomeName))) {

			//calculate the max ore veins per chunk
			int maxVeins=random.nextInt(maxVeinsPerChunk);
			//height offset defines how far above bedrock it should spawn,
			int spawnHeightOffset=0;
			if(heightOffset==1){//defines only in lans above water level
				spawnHeightOffset=63;//defined in ChunkProvider around the use of BlockWater, seems hardcoded to 63
			} else if (heightOffset==2){//defined only in areas that are above the minimum of the chunk heightmap
				spawnHeightOffset= world.getChunkFromChunkCoords(chunkX,chunkZ).heightMapMinimum-1;
			}
			for(int i=0; i<maxVeins;i++) {
				//actually generate the vein
				makeVein(world,random).generate(world, random,
						(chunkX * 16) + random.nextInt(16),
						minY+spawnHeightOffset+random.nextInt(maxY-minY),
						chunkZ * 16 + random.nextInt(16));
			}
		}
	}

	//NOTE: 1.12 does not use blocks directly, instead use BlockMatcher.forBlock(notOre)
	private WorldGenMinable makeVein(World world, Random random){
		if(world.provider.dimensionId==-1){
			if(notOre==Blocks.stone) {
				return new WorldGenMinable(ore, Math.max(minOres, random.nextInt(veinSize)), Blocks.netherrack);
			} else if(notOre==Blocks.sand){
				return new WorldGenMinable(ore, Math.max(minOres, random.nextInt(veinSize)), Blocks.soul_sand);
			} else {
				return new WorldGenMinable(ore, Math.max(minOres, random.nextInt(veinSize)), notOre);
			}
		} else {
			return new WorldGenMinable(ore, Math.max(minOres, random.nextInt(veinSize)), notOre);
		}
	}

}
