package biomesoplenty.common.world;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_BRIDGE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_CAVE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.FIRE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.GLOWSTONE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.NETHER_LAVA;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderBOPHell implements IChunkProvider
{
	public ChunkProviderBOPHell(World par1World, long par2)
	{
	}
	
	public void generateNetherTerrain(int par1, int par2, Block[] blocks)
	{
	}
	
	public void replaceBlocksForBiome(int par1, int par2, Block[] blocks, BiomeGenBase[] par4ArrayOfBiomeGenBase)
	{
	}
	 
	@Override
	public Chunk loadChunk(int par1, int par2)
	{
		return null;
	}
	
	@Override
	public Chunk provideChunk(int par1, int par2)
	{
		return null;
	}
	
	private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
	{
		return null;
	}
	
	@Override
	public boolean chunkExists(int par1, int par2)
	{
		return true;
	}
	
	@Override
	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
	{
	}
	
	@Override
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
	{
		return true;
	}
	
	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}
	
	@Override
	public boolean canSave()
	{
		return true;
	}
	
	@Override
	public String makeString()
	{
		return "HellRandomLevelSource";
	}
	
	@Override
	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
	{
		return null;
	}
	
	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}
	
	@Override
	public void recreateStructures(int par1, int par2)
	{
	}
	
	@Override
	public void saveExtraData() 
	{
	}
	
	@Override
	public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5) 
	{
		return null;
	}
}