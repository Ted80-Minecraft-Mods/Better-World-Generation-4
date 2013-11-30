package bwg4.gen.chunkproviders;

import java.util.List;
import java.util.Random;

import bwg4.biomes.BWG4Biomes;
import bwg4.deco.BWG4decoCaves;
import bwg4.deco.BWG4decoMites;
import bwg4.deco.BWG4decoWasteland;
import bwg4.deco.old.BWG4oldGenBigTree;
import bwg4.deco.old.BWG4oldGenTrees;
import bwg4.map.BWG4MapGenBase;
import bwg4.map.BWG4oldMapGenCaves;
import bwg4.noise.BWG4NoiseOctavesBeta;
import bwg4.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
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
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
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
    private BWG4MapGenBase cavegen;

    public PerlinNoise cavenoise_top;
    public PerlinNoise cavenoise_1;
    public PerlinNoise cavenoise_2;
    public PerlinNoise cavenoise_3;
	
    public BWG4ChunkProviderCave(World par1World, long par2, boolean par4)
    {
        this.worldObj = par1World;
        this.rand = new Random(par2);
        this.mapFeaturesEnabled = par4;
        cavegen = new BWG4oldMapGenCaves();
		
        cavenoise_top = new PerlinNoise(par2 + 1L);
        cavenoise_1 = new PerlinNoise(par2 + 2L);
        cavenoise_2 = new PerlinNoise(par2 + 3L);
        cavenoise_3 = new PerlinNoise(par2 + 4L);
    }

    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        byte[] blocks = new byte[32768];
        generateWorld(par1, par2, blocks);
        replaceBlocks(par1, par2, blocks);
        cavegen.generate(this, worldObj, par1, par2, blocks);
        
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
        byte var5 = 48;
		int i = par1 << 4;
		int j = par2 << 4;
		int jj = 0;
		
		boolean p = false;
		for (int k = i; k < i + 16; k++)
		{
			for (int m = j; m < j + 16; m++)
			{
				float t = 100 + cavenoise_top.turbulence2(((float) k / 80F),((float) m / 80F), 5F) * 20;
				float b = cavenoise_2.turbulence2(((float) k / 300F),((float) m / 300F), 5F) * 25;
				float strength = 1.6F + cavenoise_1.turbulence2(((float) k / 300F),((float) m / 300F), 5F) * 2F;
				
				if(strength < 0.5F) 
				{
					strength = 0.5F; 
				}

				for (int i3 = 0; i3 < 128; i3++)
				{
					float s;
					if(i3 < b + 50 - (20 * strength))
					{
						s = -1;
					}
					else if(i3 > b + 50 + (20 * strength))
					{
						s = 1;
					}
					else
					{
						s = (i3 - 50 + b) + (cavenoise_3.turbulence3(((float) k / 30F), ((float) i3 / 30F), ((float) m / 30F), 3F) * (15 * strength)) + (cavenoise_3.turbulence3(((float) k / 100F), ((float) i3 / 100F), ((float) m / 100F), 3F) * (25 * strength));
					}
					
					
					if(i3 == 0 || i3 == 127)
					{
						blocks[jj] = (byte)Block.bedrock.blockID;
					}
					else
					{
						if(s < 0 || i3 > t)
						{
							blocks[jj] = (byte)Block.stone.blockID;
						}
						else
						{
							if(i3 > var5)
							{
								blocks[jj] = 0;
							}
							else
							{
								blocks[jj] = (byte)Block.waterStill.blockID;
							}
						}
					}
					jj++;
				}
			}
		}
    }
    
    public void replaceBlocks(int par1, int par2, byte[] par3ArrayOfByte)
    {
        byte var5 = 48;
        double var6 = 0.03125D;
        for (int var8 = 0; var8 < 16; ++var8)
        {
            for (int var9 = 0; var9 < 16; ++var9)
            {
                int var13 = 0;
                for (int var16 = 127; var16 >= 0; --var16)
                {
                    int var17 = (var9 * 16 + var8) * 128 + var16;
                    byte var18 = par3ArrayOfByte[var17];

                    if (var18 == 0)
                    {
                        var13 = 0;
                    }
                    else if (var18 == Block.stone.blockID)
                    {
                    	if	(var16 > var5 + 1)
                    	{
                            if (var13 == 0)
                            {
								par3ArrayOfByte[var17] = (byte)Block.grass.blockID;
                            }
                            else if (var13 > -1 && var13 < 4)
                            {
                                par3ArrayOfByte[var17] = (byte)Block.dirt.blockID;
                            }
                    	}
                    	else if(var13 > -1 && var13 < 6)
                    	{
                        	if(var13 > 2 && rand.nextInt(3) == 0)
                        	{
                                par3ArrayOfByte[var17] = (byte)Block.sandStone.blockID;
                        	}
                        	else if(var13 < 4)
                        	{
                        		par3ArrayOfByte[var17] = (byte)Block.sand.blockID;
                        	}
                    	}
                        var13++;
                    }
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

        //stalactites and stalagmites
        if(rand.nextInt(4) == 0)
        {
            int dx1 = var4 + this.rand.nextInt(16) + 8;
            int dy1 = 75;
            int dz1 = var5 + this.rand.nextInt(16) + 8;
        	(new BWG4decoMites(3, 22 + rand.nextInt(6), 120)).generate(this.worldObj, this.rand, dx1, dy1, dz1);
        }
        
        //trees
		for(int i11 = 0; i11 < 4; i11++)
		{
			int l13 = var4 + rand.nextInt(16) + 8;
			int j14 = var5 + rand.nextInt(16) + 8;
			WorldGenerator worldgenerator = getRandomWorldGenForTrees(rand);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			int h = getGrassPos(worldObj, l13, j14, 15);
			if(h > 0)
			{
				worldgenerator.generate(worldObj, rand, l13, h, j14);
			}
		}
        
        //mushrooms
		if(rand.nextInt(6) == 0)
		{
			int k15 = var4 + rand.nextInt(16) + 8;
			int k17 = rand.nextInt(40) + 40;
			int k20 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, rand, k15, k17, k20);
		}
		if(rand.nextInt(6) == 0)
		{
			int l15 = var4 + rand.nextInt(16) + 8;
			int l17 = rand.nextInt(40) + 40;
			int l20 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, rand, l15, l17, l20);
		}
		
		//water and lava
		for (int lqw = 0; lqw < 20; ++lqw)
		{
			int lqw1 = var4 + rand.nextInt(16) + 8;
			int lqw2 = rand.nextInt(rand.nextInt(64) + 8);
			int lqw3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenLiquids(Block.waterMoving.blockID)).generate(worldObj, rand, lqw1, lqw2, lqw3);
		}
		for (int lql = 0; lql < 10; ++lql)
		{
			int lql1 = var4 + rand.nextInt(16) + 8;
			int lql2 = rand.nextInt(rand.nextInt(64) + 8);
			int lql3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(worldObj, rand, lql1, lql2, lql3);
		}
		if(rand.nextInt(2) == 0)
		{
			int lqw1 = var4 + rand.nextInt(16) + 8;
			int lqw2 = rand.nextInt(20) + 90;
			int lqw3 = var5 + rand.nextInt(16) + 8;
			(new BWG4decoCaves(2)).generate(worldObj, rand, lqw1, lqw2, lqw3);
		}
		if(rand.nextInt(3) == 0)
		{
			int lql1 = var4 + rand.nextInt(16) + 8;
			int lql2 = rand.nextInt(20) + 90;
			int lql3 = var5 + rand.nextInt(16) + 8;
			(new BWG4decoCaves(3)).generate(worldObj, rand, lql1, lql2, lql3);
		}
        
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, worldObj, rand, par2, par3, false));
		
        BlockSand.fallInstantly = false;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		if(par1Random.nextInt(8) == 0)
		{
			return new BWG4oldGenBigTree(2);
		} 
		if(par1Random.nextInt(18) == 0)
		{
			return new WorldGenTaiga1();
		} 
		if(par1Random.nextInt(10) == 0)
		{
			return new WorldGenForest(false);
		} 
		return new BWG4oldGenTrees(2);
    }
    
    public int getGrassPos(World world, int x, int z, int req)
    {
    	int h = 0;
    	for(int i = 105; i > 40; i--)
    	{
    		if(world.getBlockMaterial(x, i, z) == Material.grass)
    		{
    			if(h >= req)
    			{
    				return i + 1;
    			}
    		}
    		else if(world.getBlockMaterial(x, i, z) == Material.air)
    		{
    			h++;
    		}
    		else
    		{
    			h = 0;
    		}
    	}
    	return -1;
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
        if (this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
        }
    }
}
