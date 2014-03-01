package bwg4.world.generators;

import java.util.List;
import java.util.Random;

import bwg4.deco.DecoBigTree;
import bwg4.deco.DecoDungeons;
import bwg4.deco.DecoGold1;
import bwg4.deco.DecoSurvival;
import bwg4.deco.old.OldGenMinable;
import bwg4.map.MapGenBWG4;
import bwg4.map.MapGenBWG4Caves;
import bwg4.noise.NoiseOctavesBeta;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
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
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkGeneratorSkyIsland implements IChunkProvider
{
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    public NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseOctavesBeta TreeNoise;
    private World world;
    private double[] densities;

    private BiomeGenBase[] biomesForGeneration;
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;
    int[][] field_73203_h = new int[32][32];
    private MapGenBWG4 caveGenerator = new MapGenBWG4Caves();
	int THEMEID = 1;

    public ChunkGeneratorSkyIsland(World par1World, long par2, int theme)
    {
        this.world = par1World;
        this.rand = new Random(par2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 16);
        TreeNoise = new NoiseOctavesBeta(rand, 8);
        
		THEMEID = theme;
    }

    public void generateTerrain(int par1, int par2, Block[] blocks, BiomeGenBase[] par4ArrayOfBiomeGenBase)
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
                                Block var51 = Blocks.air;

                                if (var46 > 0.0D)
                                {
                                    var51 = Blocks.stone;
                                }

                                blocks[var42] = var51;
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

    public void replaceBlocksForBiome(int par1, int par2, Block[] blocks, BiomeGenBase[] par4ArrayOfBiomeGenBase)
    {
        for (int var5 = 0; var5 < 16; ++var5)
        {
            for (int var6 = 0; var6 < 16; ++var6)
            {
                byte var7 = 1;
                int var8 = -1;
                Block var9 = Blocks.grass;
                Block var10 = Blocks.dirt;

                for (int var11 = 127; var11 >= 0; --var11)
                {
                    int var12 = (var6 * 16 + var5) * 128 + var11;
                    Block var13 = blocks[var12];

                    if (var13 == Blocks.air)
                    {
                        var8 = -1;
                    }
                    else if (var13 == Blocks.stone)
                    {
                        if (var8 == -1)
                        {
                            /*if (var7 <= 0)
                            {
								if(THEMEID == 3)
								{
									var9 = (byte)Block.blockSnow.blockID;
									var10 = (byte)Block.blockSnow.blockID;
								}
								else
								{
									var9 = (byte)Block.sand.blockID;
									var10 = (byte)Block.sand.blockID;
								}	
                            }*/

                            var8 = var7;

                            if (var11 >= 0)
                            {
                                blocks[var12] = var9;
                            }
                            else
                            {
                            	blocks[var12] = var10;
                            }
                        }
                        else if (var8 > 0)
                        {
                            --var8;
                            blocks[var12] = var10;
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

    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        Block[] var3 = new Block[32768];
        this.biomesForGeneration = this.world.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.generateTerrain(par1, par2, var3, this.biomesForGeneration);
        this.replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
        this.caveGenerator.generate(this, this.world, par1, par2, var3);
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
		double extremenes = -270.0D;

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

                double var18 = this.noiseData5[var13] / 8000.0D;

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

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockSand.fallInstantly = true;
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = this.world.getBiomeGenForCoords(var4 + 16, var5 + 16);
        
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, world, rand, par2, par3, false));
		
		byte deco_flowerRed = 0;
		byte deco_flowerYellow = 0;
		byte deco_grass = 0;
		byte deco_dungeon = 12;
		byte deco_tree = 0;
		byte deco_pumpkin = 16;
		byte deco_melon = 16; 
		boolean mayrandtrees = false;
		
		//========================= THEME SETTINGS ======================

		if(THEMEID == 1) //DEFAULT
		{
			deco_flowerRed = 3;
			deco_flowerYellow = 3;
			deco_grass = 4;
			deco_tree = 4;
			mayrandtrees = true;
		}
		if(THEMEID == 2) //SNOW
		{
			deco_tree = 10;
			deco_flowerRed = 2;
			deco_flowerYellow = 1;
			deco_grass = 4;
			mayrandtrees = true;
			
			for (int ice = 0; ice < 80; ++ice)
			{
				int lql1 = var4 + rand.nextInt(16) + 8;
				int lql2 = rand.nextInt(128);
				int lql3 = var5 + rand.nextInt(16) + 8;
				(new DecoSurvival(5)).generate(world, rand, lql1, lql2, lql3);
			}
		}
		if(THEMEID == 3) //JUNGLE
		{
			mayrandtrees = false;
			deco_flowerRed = 4;
			deco_flowerYellow = 2;
			//bwg4decorator.waterlily = 5;
			//bwg4decorator.sugarcane = 20;
			deco_grass = 8;
			deco_melon = 8;				
			deco_tree = 14;
		}
		
		//======================== DECO =================================
		if(par2 == 0 && par3 == 0)
		{
			(new DecoDungeons(1, false, false, true, false)).generate(world, rand, 0, 2, 0);
		}
		else
		{
			if(rand.nextInt(deco_dungeon) == 0)
			{
				int j5 = var4 + rand.nextInt(16) + 8;
				int k88 = rand.nextInt(10);
				int j11 = var5 + rand.nextInt(16) + 8;
				(new DecoDungeons(1, false, false, true, false)).generate(world, rand, j5, k88, j11);
			}	
		}		

		for(int j2 = 0; j2 < 20; j2++)
		{
			int l5 = var4 + rand.nextInt(16);
			int i9 = rand.nextInt(128);
			int l11 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.dirt, 32, 2)).generate(world, rand, l5, i9, l11);
		}

		for(int k2 = 0; k2 < 10; k2++)
		{
			int i6 = var4 + rand.nextInt(16);
			int j9 = rand.nextInt(128);
			int i12 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.gravel, 32, 2)).generate(world, rand, i6, j9, i12);
		}

		for(int i3 = 0; i3 < 20; i3++)
		{
			int j6 = var4 + rand.nextInt(16);
			int k9 = rand.nextInt(128);
			int j12 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.coal_ore, 16, 2)).generate(world, rand, j6, k9, j12);
		}

		for(int j3 = 0; j3 < 20; j3++)
		{
			int k6 = var4 + rand.nextInt(16);
			int l9 = rand.nextInt(84) + 20;
			int k12 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.iron_ore, 8, 2)).generate(world, rand, k6, l9, k12);
		}

		for(int k3 = 0; k3 < 2; k3++)
		{
			int l6 = var4 + rand.nextInt(16);
			int i10 = rand.nextInt(32) + 15;
			int l12 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.gold_ore, 8, 2)).generate(world, rand, l6, i10, l12);
		}

		for(int l3 = 0; l3 < 8; l3++)
		{
			int i7 = var4 + rand.nextInt(16);
			int j10 = rand.nextInt(16) + 15;
			int i13 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.redstone_ore, 7, 2)).generate(world, rand, i7, j10, i13);
		}

		for(int i4 = 0; i4 < 1; i4++)
		{
			int j7 = var4 + rand.nextInt(16);
			int k10 = rand.nextInt(16) + 15;
			int j13 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.diamond_ore, 7, 2)).generate(world, rand, j7, k10, j13);
		}

		for(int j4 = 0; j4 < 1; j4++)
		{
			int k7 = var4 + rand.nextInt(16);
			int l10 = rand.nextInt(16) + 15;
			int k13 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.lapis_ore, 6, 2)).generate(world, rand, k7, l10, k13);
		}
		
        for (int l5 = 0; l5 < 3 + rand.nextInt(6); ++l5)
        {
            int i1 = var4 + rand.nextInt(16);
            int j1 = rand.nextInt(28) + 15;
            int k1 = var5 + rand.nextInt(16);
            Block s1 = world.getBlock(i1, j1, k1);

            if (s1 == Blocks.stone)
            {
            	world.setBlock(i1, j1, k1, Blocks.emerald_ore, 0, 2);
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
			int z52 = world.getHeightValue(j6, k10);
			WorldGenerator worldgenerator = getRandomWorldGenForTrees(rand);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}

		for (int yf = 0; yf < deco_flowerYellow; ++yf)
		{
			int yf1 = var4 + rand.nextInt(16) + 8;
			int yf2 = rand.nextInt(128);
			int yf3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Blocks.yellow_flower)).generate(world, rand, yf1, yf2, yf3);
		}	

		for (int rf = 0; rf < deco_flowerRed; ++rf)
		{
			int rf1 = var4 + rand.nextInt(16) + 8;
			int rf2 = rand.nextInt(128);
			int rf3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Blocks.red_flower)).generate(world, rand, rf1, rf2, rf3);
		}	

		for (int gr = 0; gr < deco_grass; ++gr)
		{
			int gr1 = var4 + rand.nextInt(16) + 8;
			int gr2 = rand.nextInt(128);
			int gr3 = var5 + rand.nextInt(16) + 8;
			WorldGenerator grr = var6.getRandomWorldGenForGrass(rand);
			grr.generate(world, rand, gr1, gr2, gr3);
		}
		
		if (rand.nextInt(4) == 0)
		{
			int nbm1 = var4 + rand.nextInt(16) + 8;
			int nbm2 = rand.nextInt(128);
			int nbm3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Blocks.brown_mushroom)).generate(world, rand, nbm1, nbm2, nbm3);
		}

		if (rand.nextInt(8) == 0)
		{
			int nrm1 = var4 + rand.nextInt(16) + 8;
			int nrm2 = rand.nextInt(128);
			int nrm3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Blocks.red_mushroom)).generate(world, rand, nrm1, nrm2, nrm3);
		}
		
		if (rand.nextInt(deco_melon) == 0)
		{
			int mel1 = var4 + rand.nextInt(16) + 8;
			int mel2 = rand.nextInt(128);
			int mel3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenMelon()).generate(world, rand, mel1, mel2, mel3);
		}
		
		if (rand.nextInt(deco_pumpkin) == 0)
		{
			int pum1 = var4 + rand.nextInt(16) + 8;
			int pum2 = rand.nextInt(128);
			int pum3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(world, rand, pum1, pum2, pum3);
		}
		
		//============================= SNOW  =============================
		
        int var7 = var4 + 8;
        int var8 = var5 + 8;
        for (int var12 = 0; var12 < 16; ++var12)
        {
            for (int var13 = 0; var13 < 16; ++var13)
            {
                int var14 = this.world.getPrecipitationHeight(var7 + var12, var8 + var13);

                if (this.world.isBlockFreezable(var12 + var7, var14 - 1, var13 + var8))
                {
                    this.world.setBlock(var12 + var7, var14 - 1, var13 + var8, Blocks.ice, 0, 2);
                }

                Block b = world.getBlock(var12 + var7, var14 - 1, var13 + var8);
                if (this.world.func_147478_e(var12 + var7, var14, var13 + var8, false) && b != Blocks.ice && b != Blocks.water)
                {
                    this.world.setBlock(var12 + var7, var14, var13 + var8, Blocks.snow, 0, 2);
                }	
            }
        }			
		
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, world, rand, par2, par3, false));
        
        BlockSand.fallInstantly = false;
    }
    
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		if(THEMEID == 1) //DEFAULT
		{
			if(par1Random.nextInt(5) == 0)
			{
				return new WorldGenForest(false, false);
			}
			if(par1Random.nextInt(4) == 0)
			{
				return new DecoBigTree(par1Random.nextInt(5) + 9, 0); 
			} 
			else
			{
				return new WorldGenTrees(false, 5, 0, 0, false);
			}
		}
		else if(THEMEID == 2) //SNOW
		{
			if (par1Random.nextInt(5) == 0) 
			{ 
				return new DecoGold1(3, 6, 16, 3); 
			}	
			else if (par1Random.nextInt(2) == 0) 
			{ 
				return new DecoGold1(1, 6, 14, 0);
			}
			else if (par1Random.nextInt(2) == 0) 
			{ 
				return new DecoGold1(2, 6, 12, 3); 
			}
			else 
			{ 
				return new WorldGenTrees(false); 
			}
		}
		else if(THEMEID == 3) //JUNGLE
		{
			if (par1Random.nextInt(8) == 0) 
			{ 
				return new DecoBigTree(8 + par1Random.nextInt(7), 0); 
			}
			if (par1Random.nextInt(4) == 0) 
			{ 
				return new WorldGenShrub(3, 0); 
			}
			if (par1Random.nextInt(3) != 0) 
			{ 
				return new WorldGenMegaJungle(false, 12 + par1Random.nextInt(10), 3, 3, 3); 
			} 
			else 
			{ 
				return new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true); 
			}
		}
		return new WorldGenTrees(false, 5, 0, 0, false);
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
        BiomeGenBase var5 = this.world.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void saveExtraData() {}

    public void recreateStructures(int par1, int par2) {}
}
