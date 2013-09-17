package bwg4.gen.chunkproviders;

import java.util.List;
import java.util.Random;

import bwg4.biomes.BWG4Biomes;
import bwg4.deco.BWG4decoWasteland;
import bwg4.deco.old.BWG4oldGenMinable;
import bwg4.map.BWG4MapGenBase;
import bwg4.map.BWG4MapGenPocket;
import bwg4.structure.BWG4ScatteredFeature;
import bwg4.util.PerlinNoise;
import bwg4.util.Coords;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.MapGenScatteredFeature;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.Event.*;
import net.minecraftforge.event.terraingen.*;

public class BWG4ChunkProviderWasteland implements IChunkProvider
{
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    
    public PerlinNoise noisetest;
    
    public PerlinNoise cavenoise_1;
    public PerlinNoise cavenoise_2;

    private World worldObj;
    private double[] noiseArray;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenBase ravineGenerator = new MapGenRavine();
    private BWG4ScatteredFeature scatteredFeatureGenerator = new BWG4ScatteredFeature();
    private BiomeGenBase[] biomesForGeneration;
    
    private BWG4MapGenBase pocketGenerator = new BWG4MapGenPocket();

    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise5;
    double[] noise6;
    float[] parabolicField;
    int[][] field_73219_j = new int[32][32];

    public BWG4ChunkProviderWasteland(World par1World, long par2)
    {
        this.worldObj = par1World;
        this.rand = new Random(par2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        
        cavenoise_1 = new PerlinNoise(par2 + 1L);
        cavenoise_2 = new PerlinNoise(par2 + 2L);
        
        noisetest = new PerlinNoise(par2);
    }
    
    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        byte[] abyte = new byte[32768];
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.generateSurface(par1, par2, abyte);
        //this.generateCaveLayer(par1, par2, abyte);
        this.replaceBlocksForBiome(par1, par2, abyte, this.biomesForGeneration);
        //this.caveGenerator.generate(this, this.worldObj, par1, par2, abyte);
        //this.ravineGenerator.generate(this, this.worldObj, par1, par2, abyte);
        //pocketGenerator.generate(this, this.worldObj, par1, par2, abyte);
        this.strongholdGenerator.generate(this, this.worldObj, par1, par2, abyte);
        this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, abyte);

        Chunk chunk = new Chunk(this.worldObj, abyte, par1, par2);
        byte[] abyte1 = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private double[][] getNoiseValues(int x, int y)
    {
    	double[][] values = new double[24][24];
    	
        for (int k = -4; k < 20; ++k)
        {
            for (int l = -4; l < 20; ++l)
            {
    			BiomeGenBase biomegenbase = worldObj.getWorldChunkManager().getBiomeGenAt((x * 16) + l, (y * 16) + k);
            	if(biomegenbase == BWG4Biomes.WASTELANDforest)
            	{
            		values[l + 4][k + 4] = 15;
            	}
            	else
            	{
            		values[l + 4][k + 4] = 0;
            	}
    		}
    	}
    	
    	return values;
    }

    public void generateSurface(int i, int j, byte[] blockarray)
    {
    	int jj = 0;
    	int a = i * 16;
    	int b = j * 16;
    	
    	double[][] noise = getNoiseValues(i, j);

		for (int x = a; x < a + 16; x++)
		{
			for (int z = b; z < b + 16; z++)
			{
				byte height = (byte) noise[x - a + 4][z - b + 4];
				double h = height;
				
				for(int k = x - a - 4; k < x - a + 4; k++)
				{
					for(int l = z - b - 4; l < z - b + 4; l++)
					{
						if(x - a != k && z - b != l)
						{
							double diff = height - noise[k + 4][l + 4];
							h += -(0.05 * diff) / Coords.getDistance(x - a, z - b, k, l);
						}
					}
				}
				if(h != 0)
				{
					height = (byte) (60 + Math.floor(noisetest.turbulence2(((float) x / 40F),((float) z / 40F), 5F) * h));				
				}
				else
				{
					height = 60;
				}
				for (int y = 0; y < 128; y++)
				{
					jj++;
					if(y < height)
					{
						blockarray[jj] = (byte)Block.stone.blockID;
					}
				}
			}
		}
    	/*
    	int jj = 0;
    	
    	double[] noise = getNoiseValues(i, j);

		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				byte height = (byte) noise[(x + 8) + (z + 8) * 16];
				double h = height;
				
				for(int k = x - 8; k < x + 8; k++)
				{
					for(int l = z - 8; l < z + 8; l++)
					{
						if(x != k && z != l)
						{
							double diff = height - noise[(k + 8) + (l + 8) * 16];
							//h += -(0.03 * diff) / Coords.getDistance(x, z, k, l);
						}
					}
				}
				
				height = (byte) Math.floor(h);
				for (int y = 0; y < 128; y++)
				{
					jj++;
					if(y < height)
					{
						blockarray[jj] = (byte)Block.stone.blockID;
					}
				}
			}
		}
		*/
		
    	/*
        byte b0 = 4;
        byte b1 = 16;
        byte b2 = 72;
        int k = b0 + 1;
        byte b3 = 17;
        int l = b0 + 1;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, k + 5, l + 5);
        this.noiseArray = this.initializeNoiseField(this.noiseArray, par1 * b0, 0, par2 * b0, k, b3, l);

        for (int i1 = 0; i1 < b0; ++i1)
        {
            for (int j1 = 0; j1 < b0; ++j1)
            {
                for (int k1 = 0; k1 < b1; ++k1)
                {
                    double d0 = 0.125D;
                    double d1 = this.noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1 + 0];
                    double d2 = this.noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1 + 0];
                    double d3 = this.noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1 + 0];
                    double d4 = this.noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 0];
                    double d5 = (this.noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1 + 1] - d1) * d0;
                    double d6 = (this.noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1 + 1] - d2) * d0;
                    double d7 = (this.noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1 + 1] - d3) * d0;
                    double d8 = (this.noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 1] - d4) * d0;

                    for (int l1 = 0; l1 < 8; ++l1)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 4; ++i2)
                        {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
                            short short1 = 128;
                            j2 -= short1;
                            double d14 = 0.25D;
                            double d15 = (d11 - d10) * d14;
                            double d16 = d10 - d15;

                            for (int k2 = 0; k2 < 4; ++k2)
                            {
                                if ((d16 += d15) > 0.0D)
                                {
                                	if(rand.nextInt(2) == 0)
                                	{
                                		par3ArrayOfByte[j2 += short1] = (byte)Block.stone.blockID;
                                	}
                                	else
                                	{
                                		par3ArrayOfByte[j2 += short1] = (byte)Block.cobblestone.blockID;
                                	}
                                }
                                else
                                {
                                    par3ArrayOfByte[j2 += short1] = 0;
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
        */
    }

    public void generateCaveLayer(int par1, int par2, byte[] blocks)
    {
		int i = par1 << 4;
		int j = par2 << 4;
		int jj = 0;
		
		for (int k = i; k < i + 16; k++)
		{
			for (int m = j; m < j + 16; m++)
			{
				float t = 50 + cavenoise_1.turbulence2(((float) k / 80F),((float) m / 80F), 5F) * 20;
				float b = 20 + cavenoise_2.turbulence2(((float) k / 80F),((float) m / 80F), 5F) * 20;

				for (int i3 = 0; i3 < 128; i3++)
				{
					jj++;
					
					if(i3 <= t && i3 >= b)
					{
						if(i3 < 15)
						{
							blocks[jj] = (byte)Block.lavaStill.blockID;
						}
						else
						{
							blocks[jj] = 0;
						}
					}
				}
			}
		}
    }
    
    public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase)
    {
        byte b0 = 63;
        double d0 = 0.03125D;
        this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                BiomeGenBase biomegenbase = par4ArrayOfBiomeGenBase[l + k * 16];
                float f = biomegenbase.getFloatTemperature();
                int i1 = (int)(this.stoneNoise[k + l * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int j1 = -1;
                byte b1 = biomegenbase.topBlock;
                byte b2 = biomegenbase.fillerBlock;

                for (int k1 = 127; k1 >= 0; --k1)
                {
                    int l1 = (l * 16 + k) * 128 + k1;

                    if (k1 <= 0 + this.rand.nextInt(5))
                    {
                        par3ArrayOfByte[l1] = (byte)Block.bedrock.blockID;
                    }
                    else
                    {
                        byte b3 = par3ArrayOfByte[l1];

                        if (b3 == 0)
                        {
                            j1 = -1;
                        }
                        else if (b3 == Block.stone.blockID || b3 == Block.cobblestone.blockID)
                        {
                            if (j1 == -1)
                            {
                            	if(biomegenbase.biomeID == BWG4Biomes.WASTELANDpines.biomeID)
                            	{
                            		if(rand.nextInt(3) == 0) { b1 = (byte)Block.dirt.blockID; b2 = (byte)Block.dirt.blockID; }
                            		else if(rand.nextInt(2) == 0) { b1 = (byte)Block.cobblestone.blockID; b2 = (byte)Block.cobblestone.blockID; }
                            		else { b1 = (byte)Block.gravel.blockID; b2 = (byte)Block.gravel.blockID; }
                            	}
                            	if(biomegenbase.biomeID == BWG4Biomes.WASTELANDmountains.biomeID)
                            	{
                            		if(k1 > 112)
                            		{
                            			if(rand.nextInt(2) == 0) 
                            			{
                            				b1 = (byte)Block.cobblestone.blockID; 
                            				b2 = (byte)Block.cobblestone.blockID;
                            			}
                            			else
                            			{
                            				b1 = (byte)Block.stone.blockID; 
                            				b2 = (byte)Block.stone.blockID;
                            			}
                            		}
                            		else if(k1 > 108)
                            		{
                            			if(rand.nextInt(3) == 0) 
                            			{
                                			if(rand.nextInt(2) == 0) 
                                			{
                                				b1 = (byte)Block.cobblestone.blockID; 
                                				b2 = (byte)Block.cobblestone.blockID;
                                			}
                                			else
                                			{
                                				b1 = (byte)Block.stone.blockID; 
                                				b2 = (byte)Block.stone.blockID;
                                			}
                            			}
                            		}
                            	}
                        		if(k1 < 72)
                        		{
                        			if(rand.nextInt(2) == 0) 
                        			{
                        				b1 = (byte)Block.cobblestone.blockID; 
                        				b2 = (byte)Block.cobblestone.blockID;
                        			}
                        			else
                        			{
                        				b1 = (byte)Block.gravel.blockID; 
                        				b2 = (byte)Block.gravel.blockID;
                        			}
                        		}
                        		else if(k1 < 75)
                        		{
                        			if(rand.nextInt(2) == 0) 
                        			{
                            			if(rand.nextInt(2) == 0) 
                            			{
                            				b1 = (byte)Block.cobblestone.blockID; 
                            				b2 = (byte)Block.cobblestone.blockID;
                            			}
                            			else
                            			{
                            				b1 = (byte)Block.gravel.blockID; 
                            				b2 = (byte)Block.gravel.blockID;
                            			}
                        			}
                        		}
                            	
                                j1 = i1;

                                if (k1 > 40)
                                {
	                                if (k1 >= b0 - 1)
	                                {
	                                    par3ArrayOfByte[l1] = b1;
	                                }
	                                else
	                                {
	                                    par3ArrayOfByte[l1] = b2;
	                                }
                                }
                            }
                            else if (j1 > 0)
                            {
                                --j1;
                                par3ArrayOfByte[l1] = b2;

                                if (j1 == 0 && b2 == Block.sand.blockID)
                                {
                                    j1 = this.rand.nextInt(4);
                                    b2 = (byte)Block.sandStone.blockID;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }
    
    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        if (par1ArrayOfDouble == null)
        {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        if (this.parabolicField == null)
        {
            this.parabolicField = new float[25];

            for (int k1 = -2; k1 <= 2; ++k1)
            {
                for (int l1 = -2; l1 <= 2; ++l1)
                {
                    float f = 10.0F / MathHelper.sqrt_float((float)(k1 * k1 + l1 * l1) + 0.2F);
                    this.parabolicField[k1 + 2 + (l1 + 2) * 5] = f;
                }
            }
        }

        double d0 = 684.412D;
        double d1 = 684.412D;
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, par5, par6, par7, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
        this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, par2, par3, par4, par5, par6, par7, d0, d1, d0);
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, par2, par3, par4, par5, par6, par7, d0, d1, d0);
        boolean flag = false;
        boolean flag1 = false;
        int i2 = 0;
        int j2 = 0;

        for (int k2 = 0; k2 < par5; ++k2)
        {
            for (int l2 = 0; l2 < par7; ++l2)
            {
                float f1 = 0.0F;
                float f2 = 0.0F;
                float f3 = 0.0F;
                byte b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[k2 + 2 + (l2 + 2) * (par5 + 5)];

                for (int i3 = -b0; i3 <= b0; ++i3)
                {
                    for (int j3 = -b0; j3 <= b0; ++j3)
                    {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[k2 + i3 + 2 + (l2 + j3 + 2) * (par5 + 5)];
                        float f4 = this.parabolicField[i3 + 2 + (j3 + 2) * 5] / (biomegenbase1.minHeight + 2.0F);

                        if (biomegenbase1.minHeight > biomegenbase.minHeight)
                        {
                            f4 /= 2.0F;
                        }

                        f1 += biomegenbase1.maxHeight * f4;
                        f2 += biomegenbase1.minHeight * f4;
                        f3 += f4;
                    }
                }

                f1 /= f3;
                f2 /= f3;
                f1 = f1 * 0.9F + 0.1F;
                f2 = (f2 * 4.0F - 1.0F) / 8.0F;
                double d2 = this.noise6[j2] / 8000.0D;

                if (d2 < 0.0D)
                {
                    d2 = -d2 * 0.3D;
                }

                d2 = d2 * 3.0D - 2.0D;

                if (d2 < 0.0D)
                {
                    d2 /= 2.0D;

                    if (d2 < -1.0D)
                    {
                        d2 = -1.0D;
                    }

                    d2 /= 1.4D;
                    d2 /= 2.0D;
                }
                else
                {
                    if (d2 > 1.0D)
                    {
                        d2 = 1.0D;
                    }

                    d2 /= 8.0D;
                }

                ++j2;

                for (int k3 = 0; k3 < par6; ++k3)
                {
                    double d3 = (double)f2;
                    double d4 = (double)f1;
                    d3 += d2 * 0.2D;
                    d3 = d3 * (double)par6 / 16.0D;
                    double d5 = (double)par6 / 2.0D + d3 * 4.0D;
                    double d6 = 0.0D;
                    double d7 = ((double)k3 - d5) * 12.0D * 128.0D / 128.0D / d4;

                    if (d7 < 0.0D)
                    {
                        d7 *= 4.0D;
                    }

                    double d8 = this.noise1[i2] / 512.0D;
                    double d9 = this.noise2[i2] / 512.0D;
                    double d10 = (this.noise3[i2] / 10.0D + 1.0D) / 2.0D;

                    if (d10 < 0.0D)
                    {
                        d6 = d8;
                    }
                    else if (d10 > 1.0D)
                    {
                        d6 = d9;
                    }
                    else
                    {
                        d6 = d8 + (d9 - d8) * d10;
                    }

                    d6 -= d7;

                    if (k3 > par6 - 4)
                    {
                        double d11 = (double)((float)(k3 - (par6 - 4)) / 3.0F);
                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    par1ArrayOfDouble[i2] = d6;
                    ++i2;
                }
            }
        }

        return par1ArrayOfDouble;
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
		
        pocketGenerator.populate(worldObj, par2, par3);
        
        this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
        this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
        
    	//dirt
		for(int j2 = 0; j2 < 3; j2++)
		{
			int dx3 = k + rand.nextInt(16);
			int dy3 = 35 + rand.nextInt(50);
			int dz3 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 32, Block.dirt.blockID)).generate(worldObj, rand, dx3, dy3, dz3);
		}

		//gravel
		for(int k2 = 0; k2 < 4; k2++)
		{
			int dx4 = k + rand.nextInt(16);
			int dy4 = rand.nextInt(35);
			int dz4 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 32, Block.gravel.blockID)).generate(worldObj, rand, dx4, dy4, dz4);
		}
        
        //stalactites and stalagmites
        //if(rand.nextInt(2) == 0)
        //{
        //    int dx1 = k + this.rand.nextInt(16) + 8;
        //    int dy1 = 35;
        //    int dz1 = l + this.rand.nextInt(16) + 8;
        //	(new BWG4decoWasteland(1)).generate(this.worldObj, this.rand, dx1, dy1, dz1);
        //}
        
        //lava
        int dx2 = k + this.rand.nextInt(16) + 8;
        int dy2 = 14;
        int dz2 = l + this.rand.nextInt(16) + 8;
    	(new BWG4decoWasteland(2)).generate(this.worldObj, this.rand, dx2, dy2, dz2);
    	
    	//coal
		for(int b21 = 0; b21 < 8; b21++)
		{
			int dx5 = k + rand.nextInt(16);
			int dy5 = 40 + rand.nextInt(90);
			int dz5 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 16, Block.oreCoal.blockID)).generate(worldObj, rand, dx5, dy5, dz5);
		}
		for(int b22 = 0; b22 < 3; b22++)
		{
			int dx5 = k + rand.nextInt(16);
			int dy5 = rand.nextInt(50);
			int dz5 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 16, Block.oreCoal.blockID)).generate(worldObj, rand, dx5, dy5, dz5);
		}

    	//iron
		for(int b31 = 0; b31 < 5; b31++)
		{
			int dx6 = k + rand.nextInt(16);
			int dy6 = 35 + rand.nextInt(35);
			int dz6 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 8, Block.oreIron.blockID)).generate(worldObj, rand, dx6, dy6, dz6);
		}
		for(int b32 = 0; b32 < 3; b32++)
		{
			int dx6 = k + rand.nextInt(16);
			int dy6 = rand.nextInt(50);
			int dz6 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 8, Block.oreIron.blockID)).generate(worldObj, rand, dx6, dy6, dz6);
		}
		
		//gold
		for(int b4 = 0; b4 < 1; b4++)
		{
			int dx7 = k + rand.nextInt(16);
			int dy7 = rand.nextInt(32);
			int dz7 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 8, Block.oreGold.blockID)).generate(worldObj, rand, dx7, dy7, dz7);
		}
		
		//redstone
		for(int b5 = 0; b5 < 5; b5++)
		{
			int dx8 = k + rand.nextInt(16);
			int dy8 = 16 + rand.nextInt(32);
			int dz8 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 7, Block.oreRedstone.blockID)).generate(worldObj, rand, dx8, dy8, dz8);
		}
		
		//diamond
		for(int b6 = 0; b6 < 1; b6++)
		{
			int dx9 = k + rand.nextInt(16);
			int dy9 = rand.nextInt(16);
			int dz9 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 7, Block.oreDiamond.blockID)).generate(worldObj, rand, dx9, dy9, dz9);
		}
		
		//lapis
		for(int b7 = 0; b7 < 1; b7++)
		{
			int dx10 = k + rand.nextInt(16);
			int dy10 = 8 + rand.nextInt(32);
			int dz10 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 6, Block.oreLapis.blockID)).generate(worldObj, rand, dx10, dy10, dz10);
		}
		
		//emerald
		for(int b7 = 0; b7 < 1; b7++)
		{
			int dx11 = k + rand.nextInt(16);
			int dy11 = 8 + rand.nextInt(32);
			int dz11 = l + rand.nextInt(16);
			(new BWG4decoWasteland(3, 6, Block.oreEmerald.blockID)).generate(worldObj, rand, dx11, dy11, dz11);
		}
		
		//mushroom
		if (rand.nextInt(5) == 0)
		{
			int bm1 = k + rand.nextInt(16) + 8;
			int bm2 = rand.nextInt(40);
			int bm3 = l + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, rand, bm1, bm2, bm3);
		}
		if (rand.nextInt(5) == 0)
		{
			int rm1 = k + rand.nextInt(16) + 8;
			int rm2 = rand.nextInt(40);
			int rm3 = l + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, rand, rm1, rm2, rm3);
		}

        biomegenbase.decorate(this.worldObj, this.rand, k, l);

		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, worldObj, rand, par2, par3, false));
		
        BlockSand.fallInstantly = false;
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    public void func_104112_b() 
    {
    }

    public boolean unloadQueuedChunks()
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
        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.getNearestInstance(par1World, par3, par4, par5) : null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(int par1, int par2)
    {
    	this.strongholdGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
    	this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
    }
}
