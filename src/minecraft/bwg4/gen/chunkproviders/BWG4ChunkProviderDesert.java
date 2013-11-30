package bwg4.gen.chunkproviders;

import java.util.List;
import java.util.Random;

import bwg4.mod_bwg4;
import bwg4.biomes.BWG4Biomes;
import bwg4.deco.BWG4decoCaves;
import bwg4.deco.BWG4decoDungeons;
import bwg4.deco.BWG4decoMites;
import bwg4.deco.old.BWG4oldGenClay;
import bwg4.deco.old.BWG4oldGenMinable;
import bwg4.noise.BWG4NoiseOctavesBeta;
import bwg4.structure.BWG4ScatteredFeature;
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
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.feature.MapGenScatteredFeature;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class BWG4ChunkProviderDesert implements IChunkProvider
{
    private Random rand;
    private BWG4NoiseOctavesBeta noiseGen1;
    private BWG4NoiseOctavesBeta noiseGen2;
    private BWG4NoiseOctavesBeta noiseGen3;
    private BWG4NoiseOctavesBeta noiseGen4;
    public BWG4NoiseOctavesBeta noiseGen5;
    public BWG4NoiseOctavesBeta noiseGen6;
    public BWG4NoiseOctavesBeta mobSpawnerNoise;

    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private double[] noiseArray;
    private double[] stoneNoise = new double[256];
    private double sandNoise[] = new double[256];
    private double gravelNoise[] = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private BWG4ScatteredFeature scatteredFeatureGenerator = new BWG4ScatteredFeature();
    private MapGenBase ravineGenerator = new MapGenRavine();
    private BiomeGenBase[] biomesForGeneration;

    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise5;
    double[] noise6;
    float[] parabolicField;
    int[][] field_73219_j = new int[32][32];
    
    public PerlinNoise cavenoise_1;
    public PerlinNoise cavenoise_2;
    public PerlinNoise cavenoise_3;
    public PerlinNoise cactusnoise;

    public BWG4ChunkProviderDesert(World par1World, long par2, boolean par4)
    {
        this.worldObj = par1World;
        this.mapFeaturesEnabled = par4;
        this.rand = new Random(par2);
        this.noiseGen1 = new BWG4NoiseOctavesBeta(this.rand, 16);
        this.noiseGen2 = new BWG4NoiseOctavesBeta(this.rand, 16);
        this.noiseGen3 = new BWG4NoiseOctavesBeta(this.rand, 8);
        this.noiseGen4 = new BWG4NoiseOctavesBeta(this.rand, 4);
        this.noiseGen5 = new BWG4NoiseOctavesBeta(this.rand, 10);
        this.noiseGen6 = new BWG4NoiseOctavesBeta(this.rand, 16);
        this.mobSpawnerNoise = new BWG4NoiseOctavesBeta(this.rand, 8);
        
        cavenoise_1 = new PerlinNoise(par2 + 1L);
        cavenoise_2 = new PerlinNoise(par2 + 2L);
        cavenoise_3 = new PerlinNoise(par2 + 2L);
        cactusnoise = new PerlinNoise(par2);
    }

    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        byte[] var3 = new byte[32768];
        this.generateTerrain(par1, par2, var3);
        this.generateCaveLayer(par1, par2, var3);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
        //this.caveGenerator.generate(this, this.worldObj, par1, par2, var3);
        //this.ravineGenerator.generate(this, this.worldObj, par1, par2, var3);

        if (this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generate(this, this.worldObj, par1, par2, var3);
            this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, var3);
		}

        Chunk var4 = new Chunk(this.worldObj, var3, par1, par2);
        byte[] var5 = var4.getBiomeArray();

        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = (byte)this.biomesForGeneration[var6].biomeID;
        }

        var4.generateSkylightMap();
        
        return var4;
    }

    public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte)
    {
        byte var4 = 4;
        byte var5 = 16;
        byte var6 = 63;
        int var7 = var4 + 1;
        byte var8 = 17;
        int var9 = var4 + 1;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, var7 + 5, var9 + 5);
        this.noiseArray = this.initializeNoiseField(this.noiseArray, par1 * var4, 0, par2 * var4, var7, var8, var9);

        for (int var10 = 0; var10 < var4; ++var10)
        {
            for (int var11 = 0; var11 < var4; ++var11)
            {
                for (int var12 = 0; var12 < var5; ++var12)
                {
                    double var13 = 0.125D;
                    double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
                    double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
                    double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
                    double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

                    for (int var31 = 0; var31 < 8; ++var31)
                    {
                        double var32 = 0.25D;
                        double var34 = var15;
                        double var36 = var17;
                        double var38 = (var19 - var15) * var32;
                        double var40 = (var21 - var17) * var32;

                        for (int var42 = 0; var42 < 4; ++var42)
                        {
                            int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
                            short var44 = 128;
                            var43 -= var44;
                            double var45 = 0.25D;
                            double var49 = (var36 - var34) * var45;
                            double var47 = var34 - var49;

                            for (int var51 = 0; var51 < 4; ++var51)
                            {
                                if ((var47 += var49) > 0.0D)
                                {
                                    par3ArrayOfByte[var43 += var44] = (byte)Block.stone.blockID;
                                }
                                else
                                {
                                    par3ArrayOfByte[var43 += var44] = 0;
                                }
                            }

                            var34 += var38;
                            var36 += var40;
                        }

                        var15 += var23;
                        var17 += var25;
                        var19 += var27;
                        var21 += var29;
                    }
                }
            }
        }
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
				float t = 69 + cavenoise_1.turbulence2(((float) k / 60F),((float) m / 60F), 5F) * 18;
				float b = 28 + cavenoise_2.turbulence2(((float) k / 200F),((float) m / 200F), 5F) * 12;
				b += cavenoise_3.turbulence2(((float) k / 80F),((float) m / 80F), 5F) * 16;

				for (int i3 = 0; i3 < 128; i3++)
				{
					jj++;

					if(i3 <= t && i3 >= b)
					{
						if(i3 < 23)
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
        byte var5 = 63;
        double var6 = 0.03125D;

        for (int var8 = 0; var8 < 16; ++var8)
        {
            for (int var9 = 0; var9 < 16; ++var9)
            {
            	BiomeGenBase var10 = par4ArrayOfBiomeGenBase[var9 + var8 * 16];
                float var11 = var10.getFloatTemperature();
                int var12 = (int)(this.stoneNoise[var8 + var9 * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var13 = -1;
                byte var14 = (byte)Block.sand.blockID;
                byte var15 = (byte)Block.sand.blockID;

                for (int var16 = 127; var16 >= 0; --var16)
                {
                    int var17 = (var9 * 16 + var8) * 128 + var16;

                    if (var16 <= 0 + this.rand.nextInt(5))
                    {
                        par3ArrayOfByte[var17] = (byte)Block.bedrock.blockID;
                    }
                    else
                    {
                        byte var18 = par3ArrayOfByte[var17];

                        if (var18 == 0)
                        {
                            var13 = -1;
                        }
                        else if (var18 == Block.stone.blockID)
                        {
                            if (var13 == -1)
                            {
        						if(var16 < 27)
        						{
        							if(var16 == 24)
        							{
        								if(rand.nextInt(4) != 0)
        								{
        									var15 = (byte)Block.stone.blockID;
        								}
        								else
        								{
        									var15 = (byte)Block.sand.blockID;
        								}
        							}
        							if(var16 > 24)
        							{
        								if(rand.nextInt(var16 - 23) == 0)
        								{
        									var15 = (byte)Block.stone.blockID;
        								}
        								else
        								{
        									var15 = (byte)Block.sand.blockID;
        								}
        							}
        							else
        							{
        								var15 = (byte)Block.stone.blockID;
        							}
        						}
        						else
        						{
        							var15 = (byte)Block.sand.blockID;
        						}
        						
        						var13 = var12;

                                if (var16 >= var5 - 1)
                                {
                                    par3ArrayOfByte[var17] = var14;
                                }
                                else
                                {
                                    par3ArrayOfByte[var17] = var15;
                                }
                            }
                            else if (var16 > 40)
                            {
                            	par3ArrayOfByte[var17] = (byte)Block.sandStone.blockID;
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

            for (int var8 = -2; var8 <= 2; ++var8)
            {
                for (int var9 = -2; var9 <= 2; ++var9)
                {
                    float var10 = 10.0F / MathHelper.sqrt_float((float)(var8 * var8 + var9 * var9) + 0.2F);
                    this.parabolicField[var8 + 2 + (var9 + 2) * 5] = var10;
                }
            }
        }

        double var44 = 684.412D;
        double var45 = 684.412D;
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, par5, par6, par7, var44 / 80.0D, var45 / 160.0D, var44 / 80.0D);
        this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, par2, par3, par4, par5, par6, par7, var44, var45, var44);
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, par2, par3, par4, par5, par6, par7, var44, var45, var44);
        boolean var43 = false;
        boolean var42 = false;
        int var12 = 0;
        int var13 = 0;

        for (int var14 = 0; var14 < par5; ++var14)
        {
            for (int var15 = 0; var15 < par7; ++var15)
            {
                float var16 = 0.0F;
                float var17 = 0.0F;
                float var18 = 0.0F;
                byte var19 = 2;
                BiomeGenBase var20 = this.biomesForGeneration[var14 + 2 + (var15 + 2) * (par5 + 5)];

                for (int var21 = -var19; var21 <= var19; ++var21)
                {
                    for (int var22 = -var19; var22 <= var19; ++var22)
                    {
                        BiomeGenBase var23 = this.biomesForGeneration[var14 + var21 + 2 + (var15 + var22 + 2) * (par5 + 5)];
                        float var24 = this.parabolicField[var21 + 2 + (var22 + 2) * 5] / (1.7F + 2.0F); //

                        float maxheight = 0.2F; //not higher than 2F

                        var16 += maxheight * var24;
                        var17 += 1.7F * var24; //
                        var18 += var24;
                    }
                }

                var16 /= var18;
                var17 /= var18;
                var16 = var16 * 0.9F + 0.1F;
                var17 = (var17 * 4.0F - 1.0F) / 8.0F;
                double var47 = this.noise6[var13] / 8000.0D;

                if (var47 < 0.0D)
                {
                    var47 = -var47 * 0.3D;
                }

                var47 = var47 * 3.0D - 2.0D;

                if (var47 < 0.0D)
                {
                    var47 /= 2.0D;

                    if (var47 < -1.0D)
                    {
                        var47 = -1.0D;
                    }

                    var47 /= 1.4D;
                    var47 /= 2.0D;
                }
                else
                {
                    if (var47 > 1.0D)
                    {
                        var47 = 1.0D;
                    }

                    var47 /= 8.0D;
                }

                ++var13;

                for (int var46 = 0; var46 < par6; ++var46)
                {
                    double var48 = (double)var17;
                    double var26 = (double)var16;
                    var48 += var47 * 0.2D;
                    var48 = var48 * (double)par6 / 16.0D;
                    double var28 = (double)par6 / 2.0D + var48 * 4.0D;
                    double var30 = 0.0D;
                    double var32 = ((double)var46 - var28) * 12.0D * 128.0D / 128.0D / var26;

                    if (var32 < 0.0D)
                    {
                        var32 *= 4.0D;
                    }

                    double var34 = this.noise1[var12] / 512.0D;
                    double var36 = this.noise2[var12] / 512.0D;
                    double var38 = (this.noise3[var12] / 10.0D + 1.0D) / 2.0D;

                    if (var38 < 0.0D)
                    {
                        var30 = var34;
                    }
                    else if (var38 > 1.0D)
                    {
                        var30 = var36;
                    }
                    else
                    {
                        var30 = var34 + (var36 - var34) * var38;
                    }

                    var30 -= var32;

                    if (var46 > par6 - 4)
                    {
                        double var40 = (double)((float)(var46 - (par6 - 4)) / 3.0F);
                        var30 = var30 * (1.0D - var40) + -10.0D * var40;
                    }

                    par1ArrayOfDouble[var12] = var30;
                    ++var12;
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
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)par2 * var7 + (long)par3 * var9 ^ this.worldObj.getSeed());
        boolean var11 = false;
        
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, worldObj, rand, par2, par3, var11));

        if (this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
            this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
        }
        
        //stalactites and stalagmites ====================================================
        if(rand.nextInt(3) == 0)
        {
            int dx1 = var4 + this.rand.nextInt(16) + 8;
            int dy1 = 48;
            int dz1 = var5 + this.rand.nextInt(16) + 8;
        	(new BWG4decoMites(2, 18 + rand.nextInt(5), 80)).generate(this.worldObj, this.rand, dx1, dy1, dz1);
        }
        
        //ORES ====================================================
		for(int k2 = 0; k2 < 10; k2++)
		{
			int i6 = var4 + rand.nextInt(16);
			int j9 = rand.nextInt(128);
			int i12 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.gravel.blockID, 32, 2)).generate(worldObj, rand, i6, j9, i12);
		}
		for(int i3 = 0; i3 < 20; i3++)
		{
			int j6 = var4 + rand.nextInt(16);
			int k9 = rand.nextInt(128);
			int j12 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreCoal.blockID, 16, 2)).generate(worldObj, rand, j6, k9, j12);
		}
		for(int j3 = 0; j3 < 20; j3++)
		{
			int k6 = var4 + rand.nextInt(16);
			int l9 = rand.nextInt(64);
			int k12 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreIron.blockID, 8, 2)).generate(worldObj, rand, k6, l9, k12);
		}
		for(int k3 = 0; k3 < 2; k3++)
		{
			int l6 = var4 + rand.nextInt(16);
			int i10 = rand.nextInt(32);
			int l12 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreGold.blockID, 8, 2)).generate(worldObj, rand, l6, i10, l12);
		}
		for(int l3 = 0; l3 < 8; l3++)
		{
			int i7 = var4 + rand.nextInt(16);
			int j10 = rand.nextInt(16);
			int i13 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreRedstone.blockID, 7, 2)).generate(worldObj, rand, i7, j10, i13);
		}
		for(int i4 = 0; i4 < 1; i4++)
		{
			int j7 = var4 + rand.nextInt(16);
			int k10 = rand.nextInt(16);
			int j13 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreDiamond.blockID, 7, 2)).generate(worldObj, rand, j7, k10, j13);
		}
		for(int j4 = 0; j4 < 1; j4++)
		{
			int k7 = var4 + rand.nextInt(16);
			int l10 = rand.nextInt(16) + rand.nextInt(16);
			int k13 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreLapis.blockID, 6, 2)).generate(worldObj, rand, k7, l10, k13);
		}
        for (int l5 = 0; l5 < 3 + rand.nextInt(6); ++l5)
        {
            int i1 = var4 + rand.nextInt(16);
            int j1 = rand.nextInt(28) + 4;
            int k1 = var5 + rand.nextInt(16);
            int s1 = worldObj.getBlockId(i1, j1, k1);

            if (s1 == Block.stone.blockID)
            {
            	worldObj.setBlock(i1, j1, k1, Block.oreEmerald.blockID, 0, 2);
            }
        }

        //CACTUS ====================================================
		for(int k18 = 0; k18 < 5; k18++)
		{
			int k21 = var4 + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(64) + 64;
			int k24 = var5 + rand.nextInt(16) + 8;
			(new WorldGenCactus()).generate(worldObj, rand, k21, j23, k24);
		}
		for(int k19 = 0; k19 < 3; k19++)
		{
			int k211 = var4 + rand.nextInt(16) + 8;
			int j231 = rand.nextInt(64);
			int k241 = var5 + rand.nextInt(16) + 8;
			(new WorldGenCactus()).generate(worldObj, rand, k211, j231, k241);
		}
        int ca = (int) Math.floor(cactusnoise.turbulence2(var4 / 140F, var5 / 140F, 5F) * 40F) - 3;
		for(int cg = 0; cg < ca; cg++)
		{
            int dx2 = var4 + this.rand.nextInt(16) + 8;
            int dy2 = 38;
            int dz2 = var5 + this.rand.nextInt(16) + 8;
        	(new BWG4decoCaves(1)).generate(this.worldObj, this.rand, dx2, dy2, dz2);
        }
		
		//DEAD BUSH ====================================================
		for (int db = 0; db < 2; ++db)
		{
			int db1 = var4 + rand.nextInt(16) + 8;
			int db2 = rand.nextInt(128);
			int db3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Block.deadBush.blockID)).generate(worldObj, rand, db1, db2, db3);
		}
		
        //MUSHROOM ====================================================
		if(rand.nextInt(7) == 0)
		{
			int k15 = var4 + rand.nextInt(16) + 8;
			int k17 = rand.nextInt(40) + 20;
			int k20 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, rand, k15, k17, k20);
		}
		if(rand.nextInt(7) == 0)
		{
			int l15 = var4 + rand.nextInt(16) + 8;
			int l17 = rand.nextInt(40) + 20;
			int l20 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, rand, l15, l17, l20);
		} 
        
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, var6, var4 + 8, var5 + 8, 16, 16, this.rand);
        var4 += 8;
        var5 += 8;

        for (int var12 = 0; var12 < 16; ++var12)
        {
            for (int var13 = 0; var13 < 16; ++var13)
            {
                int var14 = this.worldObj.getPrecipitationHeight(var4 + var12, var5 + var13);

                if (this.worldObj.isBlockFreezable(var12 + var4, var14 - 1, var13 + var5))
                {
                    this.worldObj.setBlock(var12 + var4, var14 - 1, var13 + var5, Block.ice.blockID, 0, 2);
                }

                if (this.worldObj.canSnowAt(var12 + var4, var14, var13 + var5))
                {
                    this.worldObj.setBlock(var12 + var4, var14, var13 + var5, Block.snow.blockID, 0, 2);
                }
            }
        }
        
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, worldObj, rand, par2, par3, var11));

        BlockSand.fallInstantly = false;
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }
	
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : (var5 == BiomeGenBase.swampland && par1EnumCreatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.hasStructureAt(par2, par3, par4) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : var5.getSpawnableList(par1EnumCreatureType));
    }

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
        if (this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
            this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
        }
    }
}
