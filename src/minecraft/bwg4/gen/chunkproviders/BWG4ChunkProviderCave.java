package bwg4.gen.chunkproviders;

import java.util.List;
import java.util.Random;

import bwg4.deco.BWG4decoCaveLight;
import bwg4.deco.BWG4decoDesertCaves;
import bwg4.deco.BWG4decoHugeCaves;
import bwg4.deco.BWG4decoMites;
import bwg4.deco.BWG4decoWasteland;
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
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class BWG4ChunkProviderCave implements IChunkProvider
{
    private Random rand;
    private World worldObj;
    private double[] noiseField;
    private double[] netherrackExclusivityNoise = new double[256];
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private final boolean mapFeaturesEnabled;

    public PerlinNoise cavenoise_top;
    public PerlinNoise cavenoise_1;
    public PerlinNoise cavenoise_2;
    public PerlinNoise cavenoise_3;
    public PerlinNoise cactusnoise;
    
	boolean themeCOLD = false;
	boolean themeDESERT = false;	
	
    public BWG4ChunkProviderCave(World par1World, long par2, boolean par4, int theme)
    {
        this.worldObj = par1World;
        this.rand = new Random(par2);
        this.mapFeaturesEnabled = par4;

		if(theme == 1) { themeCOLD = true; }
		if(theme == 2) { themeDESERT = true; }
		
        cavenoise_top = new PerlinNoise(par2 + 1L);
        cavenoise_1 = new PerlinNoise(par2 + 2L);
        cavenoise_2 = new PerlinNoise(par2 + 3L);
        cavenoise_3 = new PerlinNoise(par2 + 4L);
        cactusnoise = new PerlinNoise(par2);
    }

    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        byte[] blocks = new byte[32768];
        generateWorld(par1, par2, blocks);
        replaceBlocks(par1, par2, blocks);
        
        if (mapFeaturesEnabled)
        {
            strongholdGenerator.generate(this, worldObj, par1, par2, blocks);
        }
        Chunk var4 = new Chunk(this.worldObj, blocks, par1, par2);
        BiomeGenBase[] var5 = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, par1 * 16, par2 * 16, 16, 16);
        byte[] var6 = var4.getBiomeArray();

        for (int var7 = 0; var7 < var6.length; ++var7)
        {
            var6[var7] = (byte)var5[var7].biomeID;
        }

        var4.resetRelightChecks();
        return var4;
    }
    
    public void generateWorld(int par1, int par2, byte[] blocks)
    {
		int i = par1 << 4;
		int j = par2 << 4;
		int jj = 0;
		
		boolean p = false;
		for (int k = i; k < i + 16; k++)
		{
			for (int m = j; m < j + 16; m++)
			{
				float t = 100 + cavenoise_top.turbulence2(((float) k / 80F),((float) m / 80F), 5F) * 20;
				float b;
				if(themeDESERT)
				{
					b = 50 + cavenoise_1.turbulence2(((float) k / 200F),((float) m / 200F), 5F) * 30;
					float strength = (float) Math.floor(cavenoise_3.turbulence2(((float) k / 150F),((float) m / 150F), 5F) * 130F);
					if(strength < 0) { strength = 0; }
					if(!p)
					{
						System.out.println(strength);
						p = true;
					}
					b += (cavenoise_2.turbulence2(((float) k / 60F),((float) m / 60F), 5F) * strength);
				}
				else
				{
					b = 50 + cavenoise_1.turbulence2(((float) k / 80F),((float) m / 80F), 5F) * 20;
				}

				for (int i3 = 0; i3 < 128; i3++)
				{
					if(i3 == 0 || i3 == 127)
					{
						blocks[jj] = (byte)Block.bedrock.blockID;
					}
					else
					{
						if(i3 <= t && i3 >= b)
						{
							if(themeDESERT)
							{
								if(i3 == 42)
								{
									blocks[jj] = (byte)Block.lavaMoving.blockID;
								}
								else if(i3 < 42)
								{
									blocks[jj] = (byte)Block.lavaStill.blockID;
								}
								else
								{
									blocks[jj] = 0;
								}
							}
							else
							{
								if(i3 < 50)
								{
									blocks[jj] = (byte)Block.ice.blockID;
								}
								else
								{
									blocks[jj] = 0;
								}
							}
						}
						else
						{
							blocks[jj] = (byte)Block.stone.blockID;
						}
					}
					jj++;
				}
			}
		}
    }
    
	public void replaceBlocks(int par1, int par2, byte[] blocks)
	{
		int jj = 0;
		
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				int t = -1;
				
				jj+=128;
				for (int y = 127; y > -1; y--)
				{
					byte b = 0;
					
					int l1 = (x * 16 + z) * 128 + y;
					if(blocks[l1] == 0)
					{
						t = -1;
					}
					else if(blocks[l1] == 1)
					{
						t++;
						if(themeCOLD)
						{
							if(y > 85)
							{
								if(rand.nextInt(2) == 0)
								{
									b = (byte)Block.cobblestone.blockID;
								}
								else
								{
									b = (byte)Block.stone.blockID;
								}
							}
							else if(t < 10 + rand.nextInt(5))
							{
								if(rand.nextInt(2) == 0)
								{
									b = (byte)Block.cobblestone.blockID;
								}
								else
								{
									b = (byte)Block.stone.blockID;
								}
							}
							else
							{
								b = (byte)Block.stone.blockID;
							}
						}
						else if(themeDESERT)
						{
							if(y > 85)
							{
								b = (byte)Block.sandStone.blockID;
							}
							else if(t < 8)
							{
								b = (byte)Block.sand.blockID;
							}
							else if(t < 10 + rand.nextInt(5))
							{
								b = (byte)Block.sandStone.blockID;
							}
							else
							{
								b = (byte)Block.stone.blockID;
							}
						}
						else
						{
							if(t == 0)
							{
								b = (byte)Block.grass.blockID;
							}
							else if(t < 3)
							{
								b = (byte)Block.dirt.blockID;
							}
							else
							{
								b = (byte)Block.stone.blockID;
							}
						}
					}
					else
					{
						t++;
						b = blocks[l1];
					}
					jj--;
					blocks[jj] = b;
				}
				jj+=128;
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
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long var77 = this.rand.nextLong() / 2L * 2L + 1L;
        long var99 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)par2 * var77 + (long)par3 * var99 ^ this.worldObj.getSeed());
		double d = 0.25D;
		
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, worldObj, rand, par2, par3, false));
		
        if (mapFeaturesEnabled)
        {
            strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
        }

        if(themeCOLD)
	    {
	        //stalactites and stalagmites
	        if(rand.nextInt(1) == 0)
	        {
	            int dx1 = var4 + this.rand.nextInt(16) + 8;
	            int dy1 = 64;
	            int dz1 = var5 + this.rand.nextInt(16) + 8;
	        	(new BWG4decoMites(1, 10 + rand.nextInt(15), 120)).generate(this.worldObj, this.rand, dx1, dy1, dz1);
	        }
        }
        else
        {
	        //stalactites and stalagmites
	        if(rand.nextInt(4) == 0)
	        {
	            int dx1 = var4 + this.rand.nextInt(16) + 8;
	            int dy1 = 64;
	            int dz1 = var5 + this.rand.nextInt(16) + 8;
	        	(new BWG4decoMites(2, 20 + rand.nextInt(10), 120)).generate(this.worldObj, this.rand, dx1, dy1, dz1);
	        }
	        
	        int ca = (int) Math.floor(cactusnoise.turbulence2(var4 / 140F, var5 / 140F, 5F) * 40F) - 3;
			for(int cg = 0; cg < ca; cg++)
			{
	            int dx2 = var4 + this.rand.nextInt(16) + 8;
	            int dy2 = 75;
	            int dz2 = var5 + this.rand.nextInt(16) + 8;
	        	(new BWG4decoDesertCaves(1)).generate(this.worldObj, this.rand, dx2, dy2, dz2);
	        }

			if(rand.nextInt(7) == 0)
			{
	            int dx3 = var4 + this.rand.nextInt(16) + 8;
	            int dy3 = 75;
	            int dz3 = var5 + this.rand.nextInt(16) + 8;
	        	(new BWG4decoDesertCaves(1)).generate(this.worldObj, this.rand, dx3, dy3, dz3);
			}
	        
			if(rand.nextInt(7) == 0)
			{
				int k15 = var4 + rand.nextInt(16) + 8;
				int k17 = rand.nextInt(40) + 40;
				int k20 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, rand, k15, k17, k20);
			}
			if(rand.nextInt(7) == 0)
			{
				int l15 = var4 + rand.nextInt(16) + 8;
				int l17 = rand.nextInt(40) + 40;
				int l20 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, rand, l15, l17, l20);
			} 
        }
        
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
        return "HellRandomLevelSource";
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
        if (this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
        }
    }
}
