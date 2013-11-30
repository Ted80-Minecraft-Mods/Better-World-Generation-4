package bwg4.gen.chunkproviders;

import java.util.List;
import java.util.Random;

import bwg4.deco.BWG4decoCaves;
import bwg4.deco.BWG4decoMites;
import bwg4.deco.BWG4decoWasteland;
import bwg4.map.BWG4MapGenBase;
import bwg4.map.BWG4MapGenRoads;
import bwg4.structure.BWG4ScatteredFeature;
import bwg4.structure.city.BWG4CityGen;
import bwg4.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class BWG4ChunkProviderCity implements IChunkProvider
{
    private Random rand;
    private World worldObj;
    private BWG4MapGenRoads roadGen = new BWG4MapGenRoads();
    private BWG4CityGen cityGen = new BWG4CityGen();

    public BWG4ChunkProviderCity(World par1World, long par2)
    {
        this.worldObj = par1World;
        this.rand = new Random(par2);
    }

    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        byte[] blocks = new byte[32768];
        generateFloor(par1, par2, blocks);
        
        roadGen.generate(this, this.worldObj, par1, par2, blocks);
        cityGen.generate(this, this.worldObj, par1, par2, blocks);

        Chunk var4 = new Chunk(this.worldObj, blocks, par1, par2);
        BiomeGenBase[] var5 = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, par1 * 16, par2 * 16, 16, 16);
        byte[] var6 = var4.getBiomeArray();

        for (int var7 = 0; var7 < var6.length; ++var7)
        {
            var6[var7] = (byte)var5[var7].biomeID;
        }

        var4.generateSkylightMap();
        return var4;
    }
    
    public void generateFloor(int par1, int par2, byte[] blocks)
    {
		int i = par1 << 4;
		int j = par2 << 4;
		int jj = 0;
		
		boolean p = false;
		for (int k = i; k < i + 16; k++)
		{
			for (int m = j; m < j + 16; m++)
			{
				for (int i3 = 0; i3 < 128; i3++)
				{
					if(i3 == 0)
					{
						blocks[jj] = (byte)Block.bedrock.blockID;
					}
					else
					{
						if(i3 < 40)
						{
							blocks[jj] = (byte)Block.stone.blockID;
						}
						else
						{
							blocks[jj] = 0;
						}
					}
					jj++;
				}
			}
		}
    }

    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockSand.fallInstantly = true;
        int k = par2 * 16;
        int l = par3 * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)par2 * i1 + (long)par3 * j1 ^ this.worldObj.getSeed());
        boolean flag = false;

		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, worldObj, rand, par2, par3, false));
    	
    	cityGen.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
    	
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, worldObj, rand, par2, par3, false));
		
        BlockSand.fallInstantly = false;
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }
	
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void func_104112_b() {}

    public void recreateStructures(int par1, int par2)
    {
    }
}
