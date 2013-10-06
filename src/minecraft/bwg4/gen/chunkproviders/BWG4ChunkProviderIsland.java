package bwg4.gen.chunkproviders;

import java.util.List;
import java.util.Random;

import bwg4.deco.BWG4decoBigTree;
import bwg4.deco.BWG4decoDungeons;
import bwg4.deco.BWG4decoIsland;
import bwg4.deco.BWG4decoSurvival;
import bwg4.deco.old.BWG4oldGenClay;
import bwg4.deco.old.BWG4oldGenMinable;
import bwg4.deco.old.BWG4oldGenTrees;
import bwg4.noise.BWG4NoiseOctavesBeta;
import bwg4.util.PerlinNoise;
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
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class BWG4ChunkProviderIsland implements IChunkProvider
{
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    public NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public BWG4NoiseOctavesBeta TreeNoise;
    private World world;
    private double[] densities;

    /** The biomes that are used to generate the chunk */
    private BiomeGenBase[] biomesForGeneration;
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;
    int[][] field_73203_h = new int[32][32];
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
	int THEMEID = 1;
	
	public PerlinNoise perlin1;
	public PerlinNoise perlin2;
	
    public BWG4ChunkProviderIsland(World par1World, long par2, int theme)
    {
        world = par1World;
        rand = new Random(par2);
        noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        noiseGen4 = new NoiseGeneratorOctaves(this.rand, 10);
        noiseGen5 = new NoiseGeneratorOctaves(this.rand, 16);
        TreeNoise = new BWG4NoiseOctavesBeta(rand, 8);
        
		THEMEID = theme;
		
		perlin1 = new PerlinNoise(par2);
		perlin2 = new PerlinNoise(par2 + 100L);
    }

    public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase)
    {
    	if(THEMEID == 1)
    	{
    		int jj = 0;
    		int i = par1 << 4;
    		int j = par2 << 4;
			for (int k = i; k < i + 16; k++)
			{
				for (int m = j; m < j + 16; m++)
				{
					int i2 = 67;
					i2 -= (int) Math.floor((Math.sqrt((0D-k)*(0D-k) + (0D-m)*(0D-m)) / 5D) + (perlin1.turbulence2(k / 60F, m / 60F, 4F) * 5F));
					if(i2 < 50) { i2 = 50; }
					
					for (int i3 = 0; i3 < 128; i3++)
					{
						int i4 = 0;
						if(i3 < i2 - 6 + rand.nextInt(3))
						{
							i4 = Block.stone.blockID;
						}
						else if(i3 < i2 - 3)
						{
							i4 = Block.sandStone.blockID;
						}
						else if(i3 < i2)
						{
							i4 = Block.sand.blockID;
						}
						else if(i3 <= 64)
						{
							i4 = Block.waterStill.blockID;
						}
						par3ArrayOfByte[jj++] = (byte)i4;
					}
				}
			}
    	}
    	else if(THEMEID == 2)
    	{
    		int jj = 0;
    		int i = par1 << 4;
    		int j = par2 << 4;
			for (int k = i; k < i + 16; k++)
			{
				for (int m = j; m < j + 16; m++)
				{
					int i2 = 78;
					i2 -= (int) Math.floor((Math.sqrt((0D-k)*(0D-k) + (0D-m)*(0D-m)) / 3D) + (perlin1.turbulence2(k / 60F, m / 60F, 4F) * 5F));
					if(i2 < 50) { i2 = 50; }
					
					for (int i3 = 0; i3 < 128; i3++)
					{
						int i4 = 0;
						if(i2 > 67)
						{
							if(i3 < i2 - 3)
							{
								i4 = Block.stone.blockID;
							}
							else if(i3 < i2 - 1)
							{
								i4 = Block.dirt.blockID;
							}
							else if(i3 < i2)
							{
								i4 = Block.grass.blockID;
							}
						}
						else
						{
							if(i3 < i2 - 6 + rand.nextInt(3))
							{
								i4 = Block.stone.blockID;
							}
							else if(i3 < i2 - 3)
							{
								i4 = Block.sandStone.blockID;
							}
							else if(i3 < i2)
							{
								i4 = Block.sand.blockID;
							}
							else if(i3 <= 64)
							{
								i4 = Block.waterStill.blockID;
							}
						}
						par3ArrayOfByte[jj++] = (byte)i4;
					}
				}
			}
    	}
    	else
    	{
	        byte var5 = 2;
	        int var6 = var5 + 1;
	        byte var7 = 33;
	        int var8 = var5 + 1;
	        this.densities = this.initializeNoiseField(this.densities, par1 * var5, 0, par2 * var5, var6, var7, var8);
	
	        for (int var9 = 0; var9 < var5; ++var9)
	        {
	            for (int var10 = 0; var10 < var5; ++var10)
	            {
	                for (int var11 = 0; var11 < 32; ++var11)
	                {
	                    double var12 = 0.25D;
	                    double var14 = this.densities[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 0];
	                    double var16 = this.densities[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 0];
	                    double var18 = this.densities[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 0];
	                    double var20 = this.densities[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 0];
	                    double var22 = (this.densities[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var14) * var12;
	                    double var24 = (this.densities[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
	                    double var26 = (this.densities[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var18) * var12;
	                    double var28 = (this.densities[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;
	
	                    for (int var30 = 0; var30 < 4; ++var30)
	                    {
	                        double var31 = 0.125D;
	                        double var33 = var14;
	                        double var35 = var16;
	                        double var37 = (var18 - var14) * var31;
	                        double var39 = (var20 - var16) * var31;
	
	                        for (int var41 = 0; var41 < 8; ++var41)
	                        {
	                            int var42 = var41 + var9 * 8 << 11 | 0 + var10 * 8 << 7 | var11 * 4 + var30;
	                            short var43 = 128;
	                            double var44 = 0.125D;
	                            double var46 = var33;
	                            double var48 = (var35 - var33) * var44;
	
	                            for (int var50 = 0; var50 < 8; ++var50)
	                            {
	                                int var51 = 0;
	
	                                if (var46 > 0.0D)
	                                {
	                                    var51 = Block.stone.blockID;
	                                }	
									else if (var11 * 4 + var30 < 63)
	                                {
										if (var11 * 4 + var30 < 55)
										{
											var51 = Block.stone.blockID;
										}
										else
										{
											//if(THEMEID == 2)
											//{
											//	var51 = Block.lavaStill.blockID;
											//}
											//else
											//{
												var51 = Block.waterStill.blockID;
											//}	
										}	
	                                } 
	
	                                par3ArrayOfByte[var42] = (byte)var51;
	                                var42 += var43;
	                                var46 += var48;
	                            }
	
	                            var33 += var37;
	                            var35 += var39;
	                        }
	
	                        var14 += var22;
	                        var16 += var24;
	                        var18 += var26;
	                        var20 += var28;
	                    }
	                }
	            }
	        }
    	}
    }

    public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase)
    {
        for (int var5 = 0; var5 < 16; ++var5)
        {
            for (int var6 = 0; var6 < 16; ++var6)
            {
                byte var7 = 1;
                int var8 = -1;
                byte var9 = (byte)Block.sand.blockID;
                byte var10 = (byte)Block.sand.blockID;

                for (int var11 = 127; var11 >= 0; --var11)
                {
                    int var12 = (var6 * 16 + var5) * 128 + var11;
                    if(var11 <= 0 + rand.nextInt(5))
                    {
                        par3ArrayOfByte[var12] = (byte)Block.bedrock.blockID;
                        continue;
                    }
                    byte var13 = par3ArrayOfByte[var12];

					if (var13 == 0)
					{
						var8 = -1;
					}
					else if (var13 == Block.stone.blockID)
					{
						if (var8 == -1)
						{
							var9 = (byte)Block.sand.blockID;
							var10 = (byte)Block.sand.blockID;
							
							if(var11 > 67) 
							{ 
								var9 = (byte)Block.grass.blockID;
								var10 = (byte)Block.dirt.blockID;
							}

							var8 = var7;

							if (var11 >= 0)
							{
								par3ArrayOfByte[var12] = var9;
							}
							else
							{
								par3ArrayOfByte[var12] = var10;
							}
						}
						else if (var8 > 0)
						{
							--var8;
							par3ArrayOfByte[var12] = var10;

							if (var8 == 0 && var10 == Block.sand.blockID)
							{
								var8 = this.rand.nextInt(4);
								var10 = (byte)Block.sandStone.blockID;
							}
						}
					}	
                }
            }
        }
    }

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        byte[] var3 = new byte[32768];
        this.biomesForGeneration = this.world.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.generateTerrain(par1, par2, var3, this.biomesForGeneration);
        
        if(THEMEID == 4)
        {
        	this.replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
        }
        
        this.caveGenerator.generate(this, this.world, par1, par2, var3);
		this.strongholdGenerator.generate(this, this.world, par1, par2, var3);
		
        Chunk var4 = new Chunk(this.world, var3, par1, par2);
        byte[] var5 = var4.getBiomeArray();

        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = (byte)this.biomesForGeneration[var6].biomeID;
        }

        var4.generateSkylightMap();
        return var4;
    }

    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        if (par1ArrayOfDouble == null)
        {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }
		
		//SETTINGS
		float maxHeight = 64.0F;
		double extremenes = -3000.0D;
		double weight = 8000.0D;
		extremenes = -400.0D;
		
        double var8 = 684.412D;
        double var10 = 684.412D;
        this.noiseData4 = this.noiseGen4.generateNoiseOctaves(this.noiseData4, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
        this.noiseData5 = this.noiseGen5.generateNoiseOctaves(this.noiseData5, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
        var8 *= 2.0D;
        this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, var8 / 80.0D, var10 / 160.0D, var8 / 80.0D);
        this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, var8, var10, var8);
        this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;

        for (int var14 = 0; var14 < par5; ++var14)
        {
            for (int var15 = 0; var15 < par7; ++var15)
            {
                double var16 = (this.noiseData4[var13] + 256.0D) / 512.0D;

                if (var16 > 1.0D)
                {
                    var16 = 1.0D;
                }

                double var18 = this.noiseData5[var13] / weight;

                if (var18 < 0.0D)
                {
                    var18 = -var18 * 0.3D;
                }

                var18 = var18 * 3.0D - 2.0D;
                float var20 = (float)(var14 + par2 - 0) / 1.0F;
                float var21 = (float)(var15 + par4 - 0) / 1.0F;
                float var22 = 100.0F - MathHelper.sqrt_float(var20 * var20 + var21 * var21) * 8.0F;

                if (var22 > 80.0F)
                {
                    var22 = 80.0F;
                }

                if (var22 < -100.0F)
                {
                    var22 = -100.0F;
                }

                if (var18 > 1.0D)
                {
                    var18 = 1.0D;
                }

                var18 /= 8.0D;
                var18 = 0.0D;

                if (var16 < 0.0D)
                {
                    var16 = 0.0D;
                }

                var16 += 0.5D;
                var18 = var18 * (double)par6 / 16.0D;
                ++var13;
                double var23 = (double)par6 / 2.0D;

                for (int var25 = 0; var25 < par6; ++var25)
                {
                    double var26 = 0.0D;
                    double var28 = ((double)var25 - var23) * 8.0D / var16;

                    if (var28 < 0.0D)
                    {
                        var28 *= -1.0D;
                    }

                    double var30 = this.noiseData2[var12] / 512.0D;
                    double var32 = this.noiseData3[var12] / 512.0D;
                    double var34 = (this.noiseData1[var12] / 10.0D + 1.0D) / 2.0D;

                    if (var34 < 0.0D)
                    {
                        var26 = var30;
                    }
                    else if (var34 > 1.0D)
                    {
                        var26 = var32;
                    }
                    else
                    {
                        var26 = var30 + (var32 - var30) * var34;
                    }

                    var26 -= 8.0D;
                    var26 += (double)var22;
                    byte var36 = 2;
                    double var37;

                    if (var25 > par6 / 2 - var36)
                    {
                        var37 = (double)((float)(var25 - (par6 / 2 - var36)) / maxHeight);

                        if (var37 < 0.0D)
                        {
                            var37 = 0.0D;
                        }

                        if (var37 > 1.0D)
                        {
                            var37 = 1.0D;
                        }
						
                        var26 = var26 * (1.0D - var37) + extremenes * var37;
                    }

                    var36 = 8;

                    if (var25 < var36)
                    {
                        var37 = (double)((float)(var36 - var25) / ((float)var36 - 1.0F));
                        var26 = var26 * (1.0D - var37) + -30.0D * var37;
                    }

                    par1ArrayOfDouble[var12] = var26;
                    ++var12;
                }
            }
        }

        return par1ArrayOfDouble;
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockSand.fallInstantly = true;
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = this.world.getBiomeGenForCoords(var4 + 16, var5 + 16);
		rand.setSeed(world.getSeed());
		long l1 = (rand.nextLong() / 2L) * 2L + 1L;
		long l2 = (rand.nextLong() / 2L) * 2L + 1L;
		rand.setSeed((long)par2 * l1 + (long)par3 * l2 ^ world.getSeed());
		
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, world, rand, par2, par3, false));
        
		this.strongholdGenerator.generateStructuresInChunk(this.world, this.rand, par2, par3);
		
		byte deco_clay = 0;
		byte deco_flowerRed = 0;
		byte deco_flowerYellow = 0;
		byte deco_grass = 0;
		byte deco_dungeon = 0;
		byte deco_tree = 0;
		boolean mayrandtrees = false;
		
		//========================= THEME SETTINGS ======================
		
		if(THEMEID == 1) //NORMAL ISLAND
		{
			if(par2 == 0 && par3 == 0)
			{
				(new BWG4decoIsland(THEMEID)).generate(world, rand, 0, world.getHeightValue(0, 0), 0);
				if (!(new BWG4decoDungeons(9, false, false, false)).generate(this.world, this.rand, -15 + rand.nextInt(30), 40, -15 + rand.nextInt(30))) { ; }		
				if (!(new BWG4decoDungeons(10, false, false, false)).generate(this.world, this.rand, -15 + rand.nextInt(30), 20, -15 + rand.nextInt(30))) { ; }	
			}
			
			deco_clay = 20;
			deco_dungeon = 20;
		}
		else if(THEMEID == 2) //TROPICAL ISLAND
		{
			if(par2 == 0 && par3 == 0)
			{
				(new BWG4decoIsland(THEMEID)).generate(world, rand, 0, world.getHeightValue(0, 0), 0);
				if (!(new BWG4decoDungeons(9, false, false, false)).generate(this.world, this.rand, -15 + rand.nextInt(30), 40, -15 + rand.nextInt(30))) { ; }		
				if (!(new BWG4decoDungeons(10, false, false, false)).generate(this.world, this.rand, -15 + rand.nextInt(30), 20, -15 + rand.nextInt(30))) { ; }	
			}
			deco_clay = 20;
			deco_dungeon = 20;
			deco_flowerRed = 3;
			deco_flowerYellow = 3;
			deco_grass = 4;
			deco_tree = 10;
		}
		
		//======================== DECO =================================

		for (int var42 = 0; var42 < deco_dungeon; ++var42)
		{
			int var66 = var4 + this.rand.nextInt(16) + 8;
			int var67 = this.rand.nextInt(128);
			int var68 = var5 + this.rand.nextInt(16) + 8;

			if ((new BWG4decoDungeons(8, true, false, false)).generate(this.world, this.rand, var66, var67, var68))
			{
				;
			}
		}	
		
        for(int i2 = 0; i2 < deco_clay; i2++)
        {
            int k5 = var4 + rand.nextInt(16);
            int l8 = rand.nextInt(128);
            int k11 = var5 + rand.nextInt(16);
            (new BWG4oldGenClay(32, 2)).generate(world, rand, k5, l8, k11);
        }	
        
		for(int j2 = 0; j2 < 20; j2++)
		{
			int l5 = var4 + rand.nextInt(16);
			int i9 = rand.nextInt(128);
			int l11 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.dirt.blockID, 32, 2)).generate(world, rand, l5, i9, l11);
		}

		for(int k2 = 0; k2 < 10; k2++)
		{
			int i6 = var4 + rand.nextInt(16);
			int j9 = rand.nextInt(128);
			int i12 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.gravel.blockID, 32, 2)).generate(world, rand, i6, j9, i12);
		}

		for(int i3 = 0; i3 < 20; i3++)
		{
			int j6 = var4 + rand.nextInt(16);
			int k9 = rand.nextInt(128);
			int j12 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreCoal.blockID, 16, 2)).generate(world, rand, j6, k9, j12);
		}

		for(int j3 = 0; j3 < 20; j3++)
		{
			int k6 = var4 + rand.nextInt(16);
			int l9 = rand.nextInt(64);
			int k12 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreIron.blockID, 8, 2)).generate(world, rand, k6, l9, k12);
		}

		for(int k3 = 0; k3 < 2; k3++)
		{
			int l6 = var4 + rand.nextInt(16);
			int i10 = rand.nextInt(32);
			int l12 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreGold.blockID, 8, 2)).generate(world, rand, l6, i10, l12);
		}

		for(int l3 = 0; l3 < 8; l3++)
		{
			int i7 = var4 + rand.nextInt(16);
			int j10 = rand.nextInt(16);
			int i13 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreRedstone.blockID, 7, 2)).generate(world, rand, i7, j10, i13);
		}

		for(int i4 = 0; i4 < 1; i4++)
		{
			int j7 = var4 + rand.nextInt(16);
			int k10 = rand.nextInt(16);
			int j13 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreDiamond.blockID, 7, 2)).generate(world, rand, j7, k10, j13);
		}

		for(int j4 = 0; j4 < 1; j4++)
		{
			int k7 = var4 + rand.nextInt(16);
			int l10 = rand.nextInt(16) + rand.nextInt(16);
			int k13 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreLapis.blockID, 6, 2)).generate(world, rand, k7, l10, k13);
		}
		
        for (int l5 = 0; l5 < 3 + rand.nextInt(6); ++l5)
        {
            int i1 = var4 + rand.nextInt(16);
            int j1 = rand.nextInt(28) + 4;
            int k1 = var5 + rand.nextInt(16);
            int s1 = world.getBlockId(i1, j1, k1);

            if (s1 == Block.stone.blockID)
            {
            	world.setBlock(i1, j1, k1, Block.oreEmerald.blockID, 0, 2);
            }
        }
        
        double treedouble = 0.5D;
		int l = (int)((TreeNoise.func_806_a((double)var4 * treedouble, (double)var5 * treedouble) / 8D + rand.nextDouble() * 4D + 4D) / 3D);
		if(l < 0) { l = 0; }
		l = l + deco_tree;
		if(mayrandtrees == false) { l = deco_tree; }
		if (rand.nextInt(10) == 0) { l++; }
		
		for (int b1 = 0; b1 < l; b1++)
		{
			int j6 = var4 + rand.nextInt(16) + 8;
			int k10 = var5 + rand.nextInt(16) + 8;
			WorldGenerator worldgenerator = getRandomWorldGenForTrees(rand);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, world.getHeightValue(j6, k10), k10);
		}
        
		for (int yf = 0; yf < deco_flowerYellow; ++yf)
		{
			int yf1 = var4 + rand.nextInt(16) + 8;
			int yf2 = rand.nextInt(128);
			int yf3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.plantYellow.blockID)).generate(world, rand, yf1, yf2, yf3);
		}	

		for (int rf = 0; rf < deco_flowerRed; ++rf)
		{
			int rf1 = var4 + rand.nextInt(16) + 8;
			int rf2 = rand.nextInt(128);
			int rf3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.plantRed.blockID)).generate(world, rand, rf1, rf2, rf3);
		}	

		for (int gr = 0; gr < deco_grass; ++gr)
		{
			int gr1 = var4 + rand.nextInt(16) + 8;
			int gr2 = rand.nextInt(128);
			int gr3 = var5 + rand.nextInt(16) + 8;
			WorldGenerator grr = var6.getRandomWorldGenForGrass(rand);
			grr.generate(world, rand, gr1, gr2, gr3);
		}
		
      //======================== SNOW AND ANIMALS =======================
        if(THEMEID == 4) 
        { 
        	SpawnerAnimals.performWorldGenSpawning(this.world, var6, var4 + 8, var5 + 8, 16, 16, this.world.rand); 
        }
		
        int var7 = var4 + 8;
        int var8 = var5 + 8;
        for (int var12 = 0; var12 < 16; ++var12)
        {
            for (int var13 = 0; var13 < 16; ++var13)
            {
                int var14 = this.world.getPrecipitationHeight(var7 + var12, var8 + var13);

                if (this.world.isBlockFreezable(var12 + var7, var14 - 1, var13 + var8))
                {
                    this.world.setBlock(var12 + var7, var14 - 1, var13 + var8, Block.ice.blockID, 0, 2);
                }

                if (this.world.canSnowAt(var12 + var7, var14, var13 + var8))
                {
                    this.world.setBlock(var12 + var7, var14, var13 + var8, Block.snow.blockID, 0, 2);
                }
            }
        }	
        
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, world, rand, par2, par3, false));
		
        BlockSand.fallInstantly = false;
    }

	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		if(THEMEID == 2) //island_tropical
		{
			if(par1Random.nextInt(5) == 0)
			{
				return new BWG4decoBigTree(par1Random.nextInt(5) + 9, 0); 
			}
			if(par1Random.nextInt(2) == 0)
			{
				return new BWG4oldGenTrees(2); 
			} 
			if(par1Random.nextInt(4) == 0)
			{
				return new WorldGenShrub(3, 0);
			} 
			return new BWG4decoSurvival(4);
		}
		return new BWG4oldGenTrees(2);
	}

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }
	
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Unloads the 100 oldest chunks from memory, due to a bug with chunkSet.add() never being called it thinks the list
     * is always empty and will not remove any chunks.
     */
    public boolean unload100OldestChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "RandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase var5 = this.world.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    /**
     * Returns the location of the closest structure of the specified type. If not found returns null.
     */
    public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.getNearestInstance(par1World, par3, par4, par5) : null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void func_104112_b() {}

    public void recreateStructures(int par1, int par2)
    {
		this.strongholdGenerator.generate(this, this.world, par1, par2, (byte[])null);
    }
}
