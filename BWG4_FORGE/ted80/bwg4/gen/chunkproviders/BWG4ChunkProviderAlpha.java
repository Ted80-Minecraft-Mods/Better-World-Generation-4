package ted80.bwg4.gen.chunkproviders;

import java.util.List;
import java.util.Random;

import ted80.bwg4.deco.old.BWG4oldGenBigTree;
import ted80.bwg4.deco.old.BWG4oldGenClay;
import ted80.bwg4.deco.old.BWG4oldGenLakes;
import ted80.bwg4.deco.old.BWG4oldGenMinable;
import ted80.bwg4.deco.old.BWG4oldGenReed;
import ted80.bwg4.deco.old.BWG4oldGenTrees;
import ted80.bwg4.gen.BWG4WorldChunkManager;
import ted80.bwg4.map.BWG4MapGenBase;
import ted80.bwg4.map.BWG4oldMapGenCaves;
import ted80.bwg4.noise.BWG4NoiseOctavesAlpha;
import ted80.bwg4.deco.BWG4decoDungeons;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenStronghold;

public class BWG4ChunkProviderAlpha implements IChunkProvider
{
    private Random field_913_j;
    private BWG4NoiseOctavesAlpha field_912_k;
    private BWG4NoiseOctavesAlpha field_911_l;
    private BWG4NoiseOctavesAlpha field_910_m;
    private BWG4NoiseOctavesAlpha field_909_n;
    private BWG4NoiseOctavesAlpha field_908_o;
    public BWG4NoiseOctavesAlpha field_922_a;
    public BWG4NoiseOctavesAlpha field_921_b;
    public BWG4NoiseOctavesAlpha field_920_c;
	
    private World worldObj_16;
    private final boolean mapFeaturesEnabled;
    private double field_4180_q[];
    private double field_905_r[];
    private double field_904_s[];
    private double field_903_t[];
    private BWG4MapGenBase field_902_u;
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private BiomeGenBase field_4179_v[];
	
    double field_4185_d[];
    double field_4184_e[];
    double field_4183_f[];
    double field_4182_g[];
    double field_4181_h[];
    int field_914_i[][];
    private double field_4178_w[];

    public BWG4ChunkProviderAlpha(World world, long l, boolean par4)
    {
        field_905_r = new double[256];
        field_904_s = new double[256];
        field_903_t = new double[256];
        field_902_u = new BWG4oldMapGenCaves();
        field_914_i = new int[32][32];
        worldObj_16 = world;
        mapFeaturesEnabled = par4;
        field_913_j = new Random(l);
        field_912_k = new BWG4NoiseOctavesAlpha(field_913_j, 16);
        field_911_l = new BWG4NoiseOctavesAlpha(field_913_j, 16);
        field_910_m = new BWG4NoiseOctavesAlpha(field_913_j, 8);
        field_909_n = new BWG4NoiseOctavesAlpha(field_913_j, 4);
        field_908_o = new BWG4NoiseOctavesAlpha(field_913_j, 4);
        field_922_a = new BWG4NoiseOctavesAlpha(field_913_j, 10);
        field_921_b = new BWG4NoiseOctavesAlpha(field_913_j, 16);
        field_920_c = new BWG4NoiseOctavesAlpha(field_913_j, 8);
    }

    public void generateTerrain(int i, int j, byte abyte0[], BiomeGenBase abiomegenbase[], double ad[])
    {
        byte byte0 = 4;
        byte byte1 = 64;
        int k = byte0 + 1;
        byte byte2 = 17;
        int l = byte0 + 1;
        field_4180_q = initializeNoiseField(field_4180_q, i * byte0, 0, j * byte0, k, byte2, l);
        for(int i1 = 0; i1 < byte0; i1++)
        {
            for(int j1 = 0; j1 < byte0; j1++)
            {
                for(int k1 = 0; k1 < 16; k1++)
                {
                    double d = 0.125D;
                    double d1 = field_4180_q[((i1 + 0) * l + (j1 + 0)) * byte2 + (k1 + 0)];
                    double d2 = field_4180_q[((i1 + 0) * l + (j1 + 1)) * byte2 + (k1 + 0)];
                    double d3 = field_4180_q[((i1 + 1) * l + (j1 + 0)) * byte2 + (k1 + 0)];
                    double d4 = field_4180_q[((i1 + 1) * l + (j1 + 1)) * byte2 + (k1 + 0)];
                    double d5 = (field_4180_q[((i1 + 0) * l + (j1 + 0)) * byte2 + (k1 + 1)] - d1) * d;
                    double d6 = (field_4180_q[((i1 + 0) * l + (j1 + 1)) * byte2 + (k1 + 1)] - d2) * d;
                    double d7 = (field_4180_q[((i1 + 1) * l + (j1 + 0)) * byte2 + (k1 + 1)] - d3) * d;
                    double d8 = (field_4180_q[((i1 + 1) * l + (j1 + 1)) * byte2 + (k1 + 1)] - d4) * d;
                    for(int l1 = 0; l1 < 8; l1++)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for(int i2 = 0; i2 < 4; i2++)
                        {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
                            char c = '\200';
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for(int k2 = 0; k2 < 4; k2++)
                            {
                                double d17 = ad[(i1 * 4 + i2) * 16 + (j1 * 4 + k2)];
                                int l2 = 0;
                                if(k1 * 8 + l1 < byte1)
                                {
                                    //if(d17 < 0.5D && k1 * 8 + l1 >= byte1 - 1)
                                    //{
                                    //    l2 = Block.ice.blockID;
                                    //} else
                                    //{
                                        l2 = Block.waterStill.blockID;
                                    //}
                                }
                                if(d15 > 0.0D)
                                {
                                    l2 = Block.stone.blockID;
                                }
                                abyte0[j2] = (byte)l2;
                                j2 += c;
                                d15 += d16;
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

    }

    public void replaceBlocksForBiome(int i, int j, byte abyte0[], BiomeGenBase abiomegenbase[])
    {
        byte byte0 = 64;
        double d = 0.03125D;
        field_905_r = field_909_n.func_807_a(field_905_r, i * 16, j * 16, 0.0D, 16, 16, 1, d, d, 1.0D);
        field_904_s = field_909_n.func_807_a(field_904_s, j * 16, 109.0134D, i * 16, 16, 1, 16, d, 1.0D, d);
        field_903_t = field_908_o.func_807_a(field_903_t, i * 16, j * 16, 0.0D, 16, 16, 1, d * 2D, d * 2D, d * 2D);
        for(int k = 0; k < 16; k++)
        {
            for(int l = 0; l < 16; l++)
            {
                BiomeGenBase biomegenbase = abiomegenbase[k * 16 + l];
                boolean flag = field_905_r[k + l * 16] + field_913_j.nextDouble() * 0.20000000000000001D > 0.0D;
                boolean flag1 = field_904_s[k + l * 16] + field_913_j.nextDouble() * 0.20000000000000001D > 3D;
                int i1 = (int)(field_903_t[k + l * 16] / 3D + 3D + field_913_j.nextDouble() * 0.25D);
                int j1 = -1;
                byte byte1 = biomegenbase.topBlock;
                byte byte2 = biomegenbase.fillerBlock;
                for(int k1 = 127; k1 >= 0; k1--)
                {
                    int l1 = (k * 16 + l) * 128 + k1;
                    if(k1 <= 0 + field_913_j.nextInt(5))
                    {
                        abyte0[l1] = (byte)Block.bedrock.blockID;
                        continue;
                    }
                    byte byte3 = abyte0[l1];
                    if(byte3 == 0)
                    {
                        j1 = -1;
                        continue;
                    }
                    if(byte3 != Block.stone.blockID)
                    {
                        continue;
                    }
                    if(j1 == -1)
                    {
                        if(i1 <= 0)
                        {
                            byte1 = 0;
                            byte2 = (byte)Block.stone.blockID;
                        } else
                        if(k1 >= byte0 - 4 && k1 <= byte0 + 1)
                        {
                            byte1 = biomegenbase.topBlock;
                            byte2 = biomegenbase.fillerBlock;
                            if(flag1)
                            {
                                byte1 = 0;
                            }
                            if(flag1)
                            {
                                byte2 = (byte)Block.gravel.blockID;
                            }
                            if(flag)
                            {
                                byte1 = (byte)Block.sand.blockID;
                            }
                            if(flag)
                            {
                                byte2 = (byte)Block.sand.blockID;
                            }
                        }
                        if(k1 < byte0 && byte1 == 0)
                        {
                            byte1 = (byte)Block.waterMoving.blockID;
                        }
                        j1 = i1;
                        if(k1 >= byte0 - 1)
                        {
                            abyte0[l1] = byte1;
                        } else
                        {
                            abyte0[l1] = byte2;
                        }
                        continue;
                    }
                    if(j1 > 0)
                    {
                        j1--;
                        abyte0[l1] = byte2;
                    }
                }

            }

        }

    }
	
    public Chunk loadChunk(int par1, int par2)
    {
        return provideChunk(par1, par2);
    }

    public Chunk provideChunk(int i, int j)
    {
        field_913_j.setSeed((long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L);
        byte abyte0[] = new byte[32768];
        field_4179_v = worldObj_16.getWorldChunkManager().loadBlockGeneratorData(field_4179_v, i * 16, j * 16, 16, 16);
        double ad[] = BWG4WorldChunkManager.temperature;
        generateTerrain(i, j, abyte0, field_4179_v, ad);
        replaceBlocksForBiome(i, j, abyte0, field_4179_v);
        field_902_u.generate(this, worldObj_16, i, j, abyte0);
		
        if (mapFeaturesEnabled)
        {
			strongholdGenerator.generate(this, worldObj_16, i, j, abyte0);
		}	
		
        Chunk chunk = new Chunk(worldObj_16, abyte0, i, j);
        byte abyte1[] = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; k++)
        {
            abyte1[k] = (byte)field_4179_v[k].biomeID;
        }				
		
        chunk.generateSkylightMap();
        return chunk;
    }

    private double[] initializeNoiseField(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
        if(ad == null)
        {
            ad = new double[l * i1 * j1];
        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        double ad1[] = BWG4WorldChunkManager.temperature;
        double ad2[] = BWG4WorldChunkManager.humidity;
        field_4182_g = field_922_a.func_4109_a(field_4182_g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        field_4181_h = field_921_b.func_4109_a(field_4181_h, i, k, l, j1, 200D, 200D, 0.5D);
        field_4185_d = field_910_m.func_807_a(field_4185_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        field_4184_e = field_912_k.func_807_a(field_4184_e, i, j, k, l, i1, j1, d, d1, d);
        field_4183_f = field_911_l.func_807_a(field_4183_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        int i2 = 16 / l;
        for(int j2 = 0; j2 < l; j2++)
        {
            int k2 = j2 * i2 + i2 / 2;
            for(int l2 = 0; l2 < j1; l2++)
            {
                int i3 = l2 * i2 + i2 / 2;
                double d2 = ad1[k2 * 16 + i3];
                double d3 = ad2[k2 * 16 + i3] * d2;
                double d4 = 1.0D - d3;
                d4 *= d4;
                d4 *= d4;
                d4 = 1.0D - d4;
                double d5 = (field_4182_g[l1] + 256D) / 512D;
                d5 *= d4;
                if(d5 > 1.0D)
                {
                    d5 = 1.0D;
                }
                double d6 = field_4181_h[l1] / 8000D;
                if(d6 < 0.0D)
                {
                    d6 = -d6 * 0.29999999999999999D;
                }
                d6 = d6 * 3D - 2D;
                if(d6 < 0.0D)
                {
                    d6 /= 2D;
                    if(d6 < -1D)
                    {
                        d6 = -1D;
                    }
                    d6 /= 1.3999999999999999D;
                    d6 /= 2D;
                    d5 = 0.0D;
                } else
                {
                    if(d6 > 1.0D)
                    {
                        d6 = 1.0D;
                    }
                    d6 /= 8D;
                }
                if(d5 < 0.0D)
                {
                    d5 = 0.0D;
                }
                d5 += 0.5D;
                d6 = (d6 * (double)i1) / 16D;
                double d7 = (double)i1 / 2D + d6 * 4D;
                l1++;
                for(int j3 = 0; j3 < i1; j3++)
                {
                    double d8 = 0.0D;
                    double d9 = (((double)j3 - d7) * 12D) / d5;
                    if(d9 < 0.0D)
                    {
                        d9 *= 4D;
                    }
                    double d10 = field_4184_e[k1] / 512D;
                    double d11 = field_4183_f[k1] / 512D;
                    double d12 = (field_4185_d[k1] / 10D + 1.0D) / 2D;
                    if(d12 < 0.0D)
                    {
                        d8 = d10;
                    } else
                    if(d12 > 1.0D)
                    {
                        d8 = d11;
                    } else
                    {
                        d8 = d10 + (d11 - d10) * d12;
                    }
                    d8 -= d9;
                    if(j3 > i1 - 4)
                    {
                        double d13 = (float)(j3 - (i1 - 4)) / 3F;
                        d8 = d8 * (1.0D - d13) + -10D * d13;
                    }
                    ad[k1] = d8;
                    k1++;
                }

            }

        }

        return ad;
    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        BlockSand.fallInstantly = true;
        int k = i * 16;
        int l = j * 16;
        BiomeGenBase biomegenbase = worldObj_16.getWorldChunkManager().getBiomeGenAt(k + 16, l + 16);
        field_913_j.setSeed(worldObj_16.getSeed());
        long l1 = (field_913_j.nextLong() / 2L) * 2L + 1L;
        long l2 = (field_913_j.nextLong() / 2L) * 2L + 1L;
        field_913_j.setSeed((long)i * l1 + (long)j * l2 ^ worldObj_16.getSeed());
        double d = 0.25D;
		
        if (mapFeaturesEnabled)
        {
			strongholdGenerator.generateStructuresInChunk(worldObj_16, field_913_j, i, j);
		}	
		
		if(field_913_j.nextInt(4) == 0)
		{
			int i111 = k + field_913_j.nextInt(16) + 8;
			int l44 = field_913_j.nextInt(128);
			int i88 = l + field_913_j.nextInt(16) + 8;
			(new BWG4oldGenLakes(Block.waterStill.blockID)).generate(worldObj_16, field_913_j, i111, l44, i88);
		}
		if(field_913_j.nextInt(8) == 0)
		{
			int j111 = k + field_913_j.nextInt(16) + 8;
			int i55 = field_913_j.nextInt(field_913_j.nextInt(120) + 8);
			int j88 = l + field_913_j.nextInt(16) + 8;
			if(i55 < 64 || field_913_j.nextInt(10) == 0)
			{
				(new BWG4oldGenLakes(Block.lavaStill.blockID)).generate(worldObj_16, field_913_j, j111, i55, j88);
			}
		} 		
		
        for(int i1 = 0; i1 < 8; i1++)
        {
            int i4 = k + field_913_j.nextInt(16) + 8;
            int k6 = field_913_j.nextInt(128);
            int l8 = l + field_913_j.nextInt(16) + 8;
            (new BWG4decoDungeons(3, true, false, false)).generate(worldObj_16, field_913_j, i4, k6, l8);
        }

        for(int j1 = 0; j1 < 10; j1++)
        {
            int j4 = k + field_913_j.nextInt(16);
            int l6 = field_913_j.nextInt(128);
            int i9 = l + field_913_j.nextInt(16);
            (new BWG4oldGenClay(32, 1)).generate(worldObj_16, field_913_j, j4, l6, i9);
        }
		
        for(int k1 = 0; k1 < 20; k1++)
        {
            int k4 = k + field_913_j.nextInt(16);
            int i7 = field_913_j.nextInt(128);
            int j9 = l + field_913_j.nextInt(16);
            (new BWG4oldGenMinable(Block.dirt.blockID, 32, 1)).generate(worldObj_16, field_913_j, k4, i7, j9);
        }

        for(int i2 = 0; i2 < 10; i2++)
        {
            int l4 = k + field_913_j.nextInt(16);
            int j7 = field_913_j.nextInt(128);
            int k9 = l + field_913_j.nextInt(16);
            (new BWG4oldGenMinable(Block.gravel.blockID, 32, 1)).generate(worldObj_16, field_913_j, l4, j7, k9);
        }

        for(int j2 = 0; j2 < 20; j2++)
        {
            int i5 = k + field_913_j.nextInt(16);
            int k7 = field_913_j.nextInt(128);
            int l9 = l + field_913_j.nextInt(16);
            (new BWG4oldGenMinable(Block.oreCoal.blockID, 16, 1)).generate(worldObj_16, field_913_j, i5, k7, l9);
        }

        for(int k2 = 0; k2 < 20; k2++)
        {
            int j5 = k + field_913_j.nextInt(16);
            int l7 = field_913_j.nextInt(64);
            int i10 = l + field_913_j.nextInt(16);
            (new BWG4oldGenMinable(Block.oreIron.blockID, 8, 1)).generate(worldObj_16, field_913_j, j5, l7, i10);
        }

        for(int i3 = 0; i3 < 2; i3++)
        {
            int k5 = k + field_913_j.nextInt(16);
            int i8 = field_913_j.nextInt(32);
            int j10 = l + field_913_j.nextInt(16);
            (new BWG4oldGenMinable(Block.oreGold.blockID, 8, 1)).generate(worldObj_16, field_913_j, k5, i8, j10);
        }

        for(int j3 = 0; j3 < 8; j3++)
        {
            int l5 = k + field_913_j.nextInt(16);
            int j8 = field_913_j.nextInt(16);
            int k10 = l + field_913_j.nextInt(16);
            (new BWG4oldGenMinable(Block.oreRedstone.blockID, 7, 1)).generate(worldObj_16, field_913_j, l5, j8, k10);
        }

        for(int k3 = 0; k3 < 1; k3++)
        {
            int i6 = k + field_913_j.nextInt(16);
            int k8 = field_913_j.nextInt(16);
            int l10 = l + field_913_j.nextInt(16);
            (new BWG4oldGenMinable(Block.oreDiamond.blockID, 7, 1)).generate(worldObj_16, field_913_j, i6, k8, l10);
        }
		
        d = 0.5D;
        int l3 = (int)((field_920_c.func_806_a((double)k * d, (double)l * d) / 8D + field_913_j.nextDouble() * 4D + 4D) / 3D);
        int j6 = 0;
        if(field_913_j.nextInt(10) == 0)
        {
            j6++;
        }
        if(biomegenbase == BiomeGenBase.ALPHAforest)
        {
            j6 += l3 + 5;
        }
        if(biomegenbase == BiomeGenBase.ALPHArainforest)
        {
            j6 += l3 + 5;
        }
        if(biomegenbase == BiomeGenBase.ALPHAseasonalForest)
        {
            j6 += l3 + 2;
        }
        if(biomegenbase == BiomeGenBase.ALPHAtaiga)
        {
            j6 += l3 + 5;
        }
        if(biomegenbase == BiomeGenBase.ALPHAdesert)
        {
            j6 -= 20;
        }
        if(biomegenbase == BiomeGenBase.ALPHAtundra)
        {
            j6 -= 20;
        }
        if(biomegenbase == BiomeGenBase.ALPHAplains)
        {
            j6 -= 20;
        }
        Object obj = new BWG4oldGenTrees(1);
        if(field_913_j.nextInt(10) == 0)
        {
            obj = new BWG4oldGenBigTree(1);
        }
        if(biomegenbase == BiomeGenBase.ALPHArainforest && field_913_j.nextInt(3) == 0)
        {
            obj = new BWG4oldGenBigTree(1);
        }
        for(int i11 = 0; i11 < j6; i11++)
        {
            int i13 = k + field_913_j.nextInt(16) + 8;
            int l15 = l + field_913_j.nextInt(16) + 8;
            ((WorldGenerator) (obj)).setScale(1.0D, 1.0D, 1.0D);
            ((WorldGenerator) (obj)).generate(worldObj_16, field_913_j, i13, worldObj_16.getHeightValue(i13, l15), l15);
        }
		
        for(int j11 = 0; j11 < 2; j11++)
        {
            int j13 = k + field_913_j.nextInt(16) + 8;
            int i16 = field_913_j.nextInt(128);
            int k18 = l + field_913_j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.plantYellow.blockID)).generate(worldObj_16, field_913_j, j13, i16, k18);
        }

        if(field_913_j.nextInt(2) == 0)
        {
            int k11 = k + field_913_j.nextInt(16) + 8;
            int k13 = field_913_j.nextInt(128);
            int j16 = l + field_913_j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.plantRed.blockID)).generate(worldObj_16, field_913_j, k11, k13, j16);
        } 
        if(field_913_j.nextInt(4) == 0)
        {
            int l11 = k + field_913_j.nextInt(16) + 8;
            int l13 = field_913_j.nextInt(128);
            int k16 = l + field_913_j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj_16, field_913_j, l11, l13, k16);
        }
        if(field_913_j.nextInt(8) == 0)
        {
            int i12 = k + field_913_j.nextInt(16) + 8;
            int i14 = field_913_j.nextInt(128);
            int l16 = l + field_913_j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj_16, field_913_j, i12, i14, l16);
        } 
        for(int j12 = 0; j12 < 10; j12++)
        {
            int j14 = k + field_913_j.nextInt(16) + 8;
            int i17 = field_913_j.nextInt(128);
            int l18 = l + field_913_j.nextInt(16) + 8;
            (new BWG4oldGenReed()).generate(worldObj_16, field_913_j, j14, i17, l18);
        }

        if(field_913_j.nextInt(32) == 0)
        {
            int k12 = k + field_913_j.nextInt(16) + 8;
            int k14 = field_913_j.nextInt(128);
            int j17 = l + field_913_j.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(worldObj_16, field_913_j, k12, k14, j17);
        }
        int l12 = 0;
        if(biomegenbase == BiomeGenBase.ALPHAdesert)
        {
            l12 += 10;
        }
        for(int l14 = 0; l14 < l12; l14++)
        {
            int k17 = k + field_913_j.nextInt(16) + 8;
            int i19 = field_913_j.nextInt(128);
            int i20 = l + field_913_j.nextInt(16) + 8;
            (new WorldGenCactus()).generate(worldObj_16, field_913_j, k17, i19, i20);
        }

        for(int i15 = 0; i15 < 50; i15++)
        {
            int l17 = k + field_913_j.nextInt(16) + 8;
            int j19 = field_913_j.nextInt(field_913_j.nextInt(120) + 8);
            int j20 = l + field_913_j.nextInt(16) + 8;
            (new WorldGenLiquids(Block.waterMoving.blockID)).generate(worldObj_16, field_913_j, l17, j19, j20);
        }

        for(int j15 = 0; j15 < 20; j15++)
        {
            int i18 = k + field_913_j.nextInt(16) + 8;
            int k19 = field_913_j.nextInt(field_913_j.nextInt(field_913_j.nextInt(112) + 8) + 8);
            int k20 = l + field_913_j.nextInt(16) + 8;
            (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(worldObj_16, field_913_j, i18, k19, k20);
        }

		SpawnerAnimals.performWorldGenSpawning(worldObj_16, biomegenbase, k + 8, l + 8, 16, 16, field_913_j);
		
        field_4178_w = BWG4WorldChunkManager.getColdTemperatures(field_4178_w, k + 8, l + 8, 16, 16);
        for(int k15 = k + 8; k15 < k + 8 + 16; k15++)
        {
            for(int j18 = l + 8; j18 < l + 8 + 16; j18++)
            {
                int l19 = k15 - (k + 8);
                int l20 = j18 - (l + 8);
                int i21 = worldObj_16.getPrecipitationHeight(k15, j18);
                double d1 = field_4178_w[l19 * 16 + l20] - ((double)(i21 - 64) / 64D) * 0.29999999999999999D;
                if(d1 < 0.5D && i21 > 0 && i21 < 128 && worldObj_16.isAirBlock(k15, i21, j18))
                {
					if( worldObj_16.getBlockMaterial(k15, i21 - 1, j18).blocksMovement() && worldObj_16.getBlockMaterial(k15, i21 - 1, j18) != Material.ice )
					{
						worldObj_16.setBlock(k15, i21, j18, Block.snow.blockID, 0, 2);
					}
					else if ( worldObj_16.getBlockMaterial(k15, i21 - 1, j18) == Material.water )
					{
						worldObj_16.setBlock(k15, i21 - 1, j18, Block.ice.blockID, 0, 2);					
					}
                }
            }

        }

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
        BiomeGenBase var5 = this.worldObj_16.getBiomeGenForCoords(par2, par4);
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
        if (mapFeaturesEnabled)
        {
			strongholdGenerator.generate(this, worldObj_16, par1, par2, (byte[])null);
		}	
	}
}
