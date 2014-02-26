package bwg4.world.generators;

import java.util.List;
import java.util.Random;

import bwg4.deco.DecoDungeons;
import bwg4.noise.NoiseOctavesBeta;
import bwg4.util.PerlinNoise;
import bwg4.util.TerrainMath;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
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
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkGeneratorIsland implements IChunkProvider
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
	public int THEMEID = 1;
	public double width;
	public int height;
	
	public PerlinNoise perlin1;
	public PerlinNoise perlin2;
	
	public double volcanoX;
	public double volcanoY;
	
    public ChunkGeneratorIsland(World par1World, long par2, int theme, int s)
    {
        world = par1World;
        rand = new Random(par2);
        noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        noiseGen4 = new NoiseGeneratorOctaves(this.rand, 10);
        noiseGen5 = new NoiseGeneratorOctaves(this.rand, 16);
        TreeNoise = new NoiseOctavesBeta(rand, 8);
        
		THEMEID = theme;
		
		perlin1 = new PerlinNoise(par2);
		perlin2 = new PerlinNoise(par2 + 100L);
		
		if(THEMEID == 1)
		{
			switch (s)
			{
				case 1: width = 3.3D; height = 67; break;
				case 2: width = 5.0D; height = 68; break;
				case 3: width = 7.0D; height = 70; break;
			}
		}
		else if(THEMEID == 2)
		{
			switch (s)
			{
				case 1: width = 3.3D; height = 72; break;
				case 2: width = 5.0D; height = 74; break;
				case 3: width = 6.5D; height = 76; break;
			}
		}
		else if(THEMEID == 3)
		{
		}
		else if(THEMEID == 4)
		{
			switch (s)
			{
				case 1: width = 2.5D; height = 5; break;
				case 2: width = 3.8D; height = 7; break;
				case 3: width = 4.9D; height = 10; break;
			}
		}
		else if(THEMEID == 5)
		{
			int dir = rand.nextInt(360);
			volcanoX = TerrainMath.nextX(0, dir, 150);
			volcanoY = TerrainMath.nextY(0, dir, 150);
		}
    }

    public void generateTerrain(int par1, int par2, Block[] blocks, BiomeGenBase[] par4ArrayOfBiomeGenBase)
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
					float i2 = height;
					i2 -= (Math.sqrt((0D-k)*(0D-k) + (0D-m)*(0D-m)) / width) + (perlin1.turbulence2(k / 60F, m / 60F, 4F) * 5F);
					if(i2 < 50f) { i2 = 50f; }
					
					for (int i3 = 0; i3 < 128; i3++)
					{
						Block i4 = Blocks.air;
						if(i3 < i2 - 6 + rand.nextInt(3))
						{
							i4 = Blocks.stone;
						}
						else if(i3 < i2 - 3)
						{
							i4 = Blocks.sandstone;
						}
						else if(i3 < i2)
						{
							i4 = Blocks.sand;
						}
						else if(i3 <= 64)
						{
							i4 = Blocks.water;
						}
						blocks[jj++] = i4;
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
					float i2 = height;
					i2 -= (Math.sqrt((0D-k)*(0D-k) + (0D-m)*(0D-m)) / width) + (perlin1.turbulence2(k / 60F, m / 60F, 4F) * 5F);
					if(i2 < 50f) { i2 = 50f; }
					
					for (int i3 = 0; i3 < 128; i3++)
					{
						Block i4 = Blocks.air;
						if(i2 > 67)
						{
							if(i3 < i2 - 3)
							{
								i4 = Blocks.stone;
							}
							else if(i3 < i2 - 1)
							{
								i4 = Blocks.dirt;
							}
							else if(i3 < i2)
							{
								i4 = Blocks.grass;
							}
						}
						else
						{
							if(i3 < i2 - 6 + rand.nextInt(3))
							{
								i4 = Blocks.stone;
							}
							else if(i3 < i2 - 3)
							{
								i4 = Blocks.sandstone;
							}
							else if(i3 < i2)
							{
								i4 = Blocks.sand;
							}
							else if(i3 <= 64)
							{
								i4 = Blocks.water;
							}
						}
						blocks[jj++] = i4;
					}
				}
			}
    	}
    	else if(THEMEID == 3)
    	{
    		
    	}
    	else if(THEMEID == 4)
    	{
    		int jj = 0;
    		int i = par1 << 4;
    		int j = par2 << 4;
			for (int k = i; k < i + 16; k++)
			{
				for (int m = j; m < j + 16; m++)
				{
					float surface = height;
					float dis = (float) (Math.sqrt((0D-k)*(0D-k) + (0D-m)*(0D-m)) / width);
					surface -= dis + (perlin1.turbulence2(k / 60F, m / 60F, 4F) * 5F);
					float ice = (perlin2.turbulence2(k / 20F, m / 20F, 4F) * 10F);
					ice = 1 -(2 *(ice * ice)); 
					
					for (int i3 = 0; i3 < 128; i3++)
					{
						Block i4 = Blocks.air;
						
						if(i3 < 63)
						{
							i4 = Blocks.water;
						}
						if(i3 == 63)
						{
							if(ice < -1f)
							{
								i4 = Blocks.ice;
							}
							else
							{
								i4 = Blocks.water;
							}
						}
						if(surface > -1)
						{
							if(i3 < surface + 65 && i3 > -(surface * 8) + 61)
							{
								i4 = Blocks.snow;
							}
						}
						
						blocks[jj++] = i4;
					}
				}
			}
    	}
    	else
    	{
    		int jj = 0;
    		int i = par1 << 4;
    		int j = par2 << 4;
    		
    		float volcanoground = 0f;
    		float volcanoair = 0f;
    		float volcanograss = 0f;
			for (int k = i; k < i + 16; k++)
			{
				for (int m = j; m < j + 16; m++)
				{
					int i1 = 0;
					float dis = (float) Math.sqrt((0D-k)*(0D-k) + (0D-m)*(0D-m));
					float stength = -(100F / 300f) * dis + 100F;
					dis /= 7f;
					
					float volcanodis = (float) TerrainMath.dis2(k, m, volcanoX, volcanoY) - 8;
	        		if(volcanodis + 8 < 100)
	        		{
	        			float noise = perlin2.turbulence2(k / 30f, m / 30f, 4f) * 7f;
		        		if(volcanodis > 0)
		        		{
		        			volcanoground = 128 + noise - volcanodis;
		        			volcanoair = 128 + noise - (volcanodis / 0.25f);
		        			volcanograss = 165 + (-noise * 2) - (volcanodis / 0.5f);
		        		}
		        		else
		        		{
		        			volcanoair = 128;
		        			volcanoground = 128;
		        			volcanograss = 128;
		        		}
	        		}
					
					for (int i3 = 0; i3 < 128; i3++)
					{
						float n = 0;
						if(i3 > 50 && stength > 0f)
						{
							n += perlin1.turbulence3(k / 90f, m / 82f, i3 / 90f, 4f) * stength;
							n += perlin2.turbulence3(k / 40f, m / 34f, i3 / 40f, 4f) * (stength / 2f);
						}
						float i2 = -100 + i3 + i1 + n + dis;

						Block i4 = Blocks.air;
						if(i2 > 0)
						{
							i4 = Blocks.air;

							if(i3 < 64)
							{
								i4 = Blocks.water;
							}
						}
						else
						{
							i4 = Blocks.stone;
						}
						
						if(i2 > -10 && i3 < volcanoground)
						{
							if(i2 > -8 && i3 < volcanoair)
							{
								if(i3 < 105)
								{
									i4 = Blocks.lava;
								}
								else
								{
									i4 = Blocks.air;
								}
							}
							else if(i3 > volcanograss)
							{
								i4 = Blocks.stone;
							}
							else
							{
								i4 = Blocks.obsidian;
							}
						}
						
						blocks[jj++] = i4;
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
                Block var9 = Blocks.sand;
                Block var10 = Blocks.sand;

                for (int var11 = 127; var11 >= 0; --var11)
                {
                    int var12 = (var6 * 16 + var5) * 128 + var11;
                    if(var11 <= 0 + rand.nextInt(5))
                    {
                    	blocks[var12] = Blocks.bedrock;
                        continue;
                    }
                    Block var13 = blocks[var12];

					if (var13 == Blocks.air)
					{
						var8 = -1;
					}
					else if (var13 == Blocks.stone || var13 == Blocks.obsidian)
					{
						if (var8 == -1)
						{
							var9 = Blocks.sand;
							var10 = Blocks.sand;
							
							if(var11 > 66) 
							{ 
								var9 = Blocks.grass;
								var10 = Blocks.dirt;
							}
							if(var13 == Blocks.obsidian)
							{
								var9 = Blocks.stone;
								var10 = Blocks.stone;
							}

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

							if (var8 == 0 && var10 == Blocks.sand)
							{
								var8 = this.rand.nextInt(4);
								var10 = Blocks.sandstone;
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

    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        Block[] var3 = new Block[32768];
        this.biomesForGeneration = this.world.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.generateTerrain(par1, par2, var3, this.biomesForGeneration);
        
        if(THEMEID == 5)
        {
        	this.replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
        }
        
        this.caveGenerator.func_151539_a(this, this.world, par1, par2, var3);
		this.strongholdGenerator.func_151539_a(this, this.world, par1, par2, var3);
		
        Chunk var4 = new Chunk(this.world, var3, par1, par2);
        byte[] var5 = var4.getBiomeArray();

        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = (byte)this.biomesForGeneration[var6].biomeID;
        }

        var4.generateSkylightMap();
        return var4;
    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockSand.field_149832_M = true;
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = this.world.getBiomeGenForCoords(var4 + 16, var5 + 16);
		rand.setSeed(world.getSeed());
		long l1 = (rand.nextLong() / 2L) * 2L + 1L;
		long l2 = (rand.nextLong() / 2L) * 2L + 1L;
		rand.setSeed((long)par2 * l1 + (long)par3 * l2 ^ world.getSeed());
		
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, world, rand, par2, par3, false));
        
		this.strongholdGenerator.generateStructuresInChunk(this.world, this.rand, par2, par3);
		
		double dis = Math.sqrt((0D-var4)*(0D-var4) + (0D-var5)*(0D-var5));
		byte deco_clay = 0;
		byte deco_flowerRed = 0;
		byte deco_flowerYellow = 0;
		byte deco_grass = 0;
		byte deco_dungeon = 0;
		byte deco_tree = 0;
		boolean mayrandtrees = false;
		int mintree = 0;
		int maxtree = 128;
		
		//========================= THEME SETTINGS ======================
		
		if(THEMEID == 1) //NORMAL ISLAND
		{
			if(par2 == 0 && par3 == 0)
			{
				(new DecoIsland(THEMEID)).generate(world, rand, 0, world.getHeightValue(0, 0), 0);
				if (!(new DecoDungeons(1, true)).generate(this.world, this.rand, -15 + rand.nextInt(30), 40, -15 + rand.nextInt(30))) { ; }		
				if (!(new DecoDungeons(2, true)).generate(this.world, this.rand, -15 + rand.nextInt(30), 20, -15 + rand.nextInt(30))) { ; }	
			}
			
			deco_clay = 20;
			deco_dungeon = 20;
		}
		else if(THEMEID == 2) //TROPICAL ISLAND
		{
			if(par2 == 0 && par3 == 0)
			{
				(new DecoIsland(THEMEID)).generate(world, rand, 0, world.getHeightValue(0, 0), 0);
				if (!(new DecoDungeons(1, true)).generate(this.world, this.rand, -15 + rand.nextInt(30), 40, -15 + rand.nextInt(30))) { ; }		
				if (!(new DecoDungeons(2, true)).generate(this.world, this.rand, -15 + rand.nextInt(30), 20, -15 + rand.nextInt(30))) { ; }	
			}
			deco_clay = 20;
			deco_dungeon = 20;
			deco_flowerRed = 3;
			deco_flowerYellow = 3;
			deco_grass = 4;
			deco_tree = 10;
			mintree = 70;
		}
		else if(THEMEID == 3) //HELL ISLAND
		{
			
		}
		else if(THEMEID == 4) //ICEBERG ISLAND
		{
			
		}
		else if(THEMEID == 5) //PARADISE ISLAND
		{
			deco_clay = 20;
			deco_dungeon = 20;
			deco_flowerRed = 3;
			deco_flowerYellow = 3;
			deco_grass = 4;
			deco_tree = 10;
			if(dis < 65D) { deco_tree = 20; }
			else if(dis < 130D) { deco_tree = 15; }
			else { deco_tree = 10; }
			mintree = 69;
		}
		
		//======================== DECO =================================

		for (int var42 = 0; var42 < deco_dungeon; ++var42)
		{
			int var66 = var4 + this.rand.nextInt(16) + 8;
			int var67 = this.rand.nextInt(128);
			int var68 = var5 + this.rand.nextInt(16) + 8;

			if ((new BWG4decoDungeons()).generate(this.world, this.rand, var66, var67, var68))
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
        
		for(int j2 = 0; j2 < 16; j2++)
		{
			int l5 = var4 + rand.nextInt(16);
			int i9 = rand.nextInt(90);
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
			int z52 = world.getHeightValue(j6, k10);
			if(z52 >= mintree && z52 <= maxtree)
			{
				WorldGenerator worldgenerator;
				if(dis < 65D) { worldgenerator = getRandomWorldGenForTrees(rand, 2); }
				else if(dis < 130D) { worldgenerator = getRandomWorldGenForTrees(rand, 1); }
				else { worldgenerator = getRandomWorldGenForTrees(rand, 0); }
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
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

		if (rand.nextInt(4) == 0)
		{
			int nbm1 = var4 + rand.nextInt(16) + 8;
			int nbm2 = rand.nextInt(128);
			int nbm3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(world, rand, nbm1, nbm2, nbm3);
		}

		if (rand.nextInt(8) == 0)
		{
			int nrm1 = var4 + rand.nextInt(16) + 8;
			int nrm2 = rand.nextInt(128);
			int nrm3 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomRed.blockID)).generate(world, rand, nrm1, nrm2, nrm3);
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
		
        BlockSand.field_149832_M = false;
    }

	public WorldGenerator getRandomWorldGenForTrees(Random par1Random, int distance)
	{
		if(THEMEID == 2 || (THEMEID == 5 && distance == 0)) //island_tropical
		{
			if(par1Random.nextInt(5) == 0)
			{
				return new BWG4decoBigTree(par1Random.nextInt(4) + 8, 0); 
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
		else if(THEMEID == 5 && distance == 1)
		{
			if (par1Random.nextInt(8) == 0) 
			{ 
				return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); 
			}
			if (par1Random.nextInt(4) == 0) 
			{ 
				return new WorldGenShrub(3, 0); 
			}
			if (par1Random.nextInt(3) != 0) 
			{ 
				return new WorldGenHugeTrees(false, 12 + par1Random.nextInt(10), 3, 3); 
			} 
			if (par1Random.nextInt(2) == 0) 
			{ 
				return new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true); 
			}
			else 
			{ 
				return new BWG4decoSurvival(4);
			}
		}
		else if(THEMEID == 5 && distance == 2)
		{
			if (par1Random.nextInt(8) == 0) 
			{ 
				return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); 
			}
			if (par1Random.nextInt(4) == 0) 
			{ 
				return new WorldGenShrub(3, 0); 
			}
			if (par1Random.nextInt(3) != 0) 
			{ 
				return new WorldGenHugeTrees(false, 25 + par1Random.nextInt(15), 3, 3); 
			} 
			else 
			{ 
				return new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true); 
			}
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

	@Override
	public ChunkPosition func_147416_a(World var1, String var2, int var3,
			int var4, int var5) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveExtraData() {
		// TODO Auto-generated method stub
		
	}
}
