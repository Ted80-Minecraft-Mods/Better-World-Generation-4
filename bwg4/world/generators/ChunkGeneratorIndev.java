package bwg4.world.generators;

import java.util.List;
import java.util.Random;

import bwg4.deco.DecoDungeons;
import bwg4.deco.DecoIndevHouse;
import bwg4.deco.old.OldGenMinable;
import bwg4.deco.old.OldGenTrees;
import bwg4.noise.NoiseOctavesIndev;
import bwg4.noise.NoiseOctavesInfdev;
import bwg4.noise.NoisePerlinIndev;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkGeneratorIndev implements IChunkProvider
{
    private Random rand;
    private NoiseOctavesIndev noiseGen1;
    private NoiseOctavesIndev noiseGen2;
    private NoiseOctavesIndev noiseGen3;
    private NoiseOctavesIndev noiseGen4;
    public NoiseOctavesIndev noiseGen5;
    public NoiseOctavesIndev noiseGen6;
    public NoiseOctavesInfdev mobSpawnerNoise;
	public NoiseOctavesIndev noiseGen10;
	public NoiseOctavesIndev noiseGen11;
	public NoisePerlinIndev perlinGen1;

    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private double[] noiseArray;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private BiomeGenBase[] biomesForGeneration;

    double[] noise1;
    double[] noise2;
    double[] noise3;
    double[] noise5;
    double[] noise6;

    float[] parabolicField;
    int[][] field_73219_j = new int[32][32];

	boolean themeHELL = false;
	boolean themePARADISE = false;
	boolean themeWOODS = false;
	boolean themeSNOW = false;
	boolean typeIsland = false;
	boolean typeFloating = false;
	boolean typeInland = false;
	boolean typeFinite = false;
	int size = 1;
	double width = 1;
	int layers = 1;

    public ChunkGeneratorIndev(World par1World, long par2, boolean par4, int type, int theme, int s, int l)
    {
        this.worldObj = par1World;
        this.mapFeaturesEnabled = par4;
        this.rand = new Random(par2);
        this.noiseGen1 = new NoiseOctavesIndev(this.rand, 16);
        this.noiseGen2 = new NoiseOctavesIndev(this.rand, 16);
        this.noiseGen3 = new NoiseOctavesIndev(this.rand, 8);
        this.noiseGen4 = new NoiseOctavesIndev(this.rand, 4);
        this.noiseGen5 = new NoiseOctavesIndev(this.rand, 4);
        this.noiseGen6 = new NoiseOctavesIndev(this.rand, 5);
        this.mobSpawnerNoise = new NoiseOctavesInfdev(this.rand, 8);
        this.noiseGen10 = new NoiseOctavesIndev(this.rand, 6);
        this.noiseGen11 = new NoiseOctavesIndev(this.rand, 8);
		this.perlinGen1 = new NoisePerlinIndev(this.rand);
		
		if(theme == 2) { themeHELL = true; }
		if(theme == 3) { themePARADISE = true; }
		if(theme == 4) { themeWOODS = true; }
		if(theme == 5) { themeSNOW = true; }
		if(type == 1) { typeIsland = true; }
		if(type == 2) { typeFloating = true; }
		if(type == 3) { typeInland = true; }
		if(type == 4) { typeFinite = true; } 

		size = s;
		if(typeFloating)
		{
			if(s == 1) { size = 6; width = 1.2D; }
			if(s == 2) { size = 12; width = 2D; }
			if(s == 3) { size = 18; width = 3D; }
		}
		if(typeIsland)
		{
			if(s == 1) { size = 3; }
			if(s == 2) { size = 5; }
			if(s == 3) { size = 7; }
		}
		layers = l;
		System.out.println(layers);
    }

	public void generateSkylands(int par1, int par2, Block[] blocks)
	{
		int seaLevel = 64;
		int i = par1 << 4;
		int j = par2 << 4;
		int jj = 0;
		int lx = 0; int lz = 0;
		
		if(par1 > -size && par1 < size && par2 > -size && par2 < size)
		{
			for(int layer = 0; layer < layers; layer++)
			{
				jj = 0;
				for (int k = i; k < i + 16; k++)
				{
					for (int m = j; m < j + 16; m++)
					{
						float f2 = (float)this.noiseGen5.a((k + (layer * 2000F)) / 4.0F, (m + (layer * 2000F)) / 4.0F);
						int i2 = 35 + (layer * 45) + ((int) f2);
						
						if(i2 < 1) 
						{ 
							i2 = 1; 
						}
		
						if ((float)this.noiseGen5.a(k, m) < 0.0F)
						{
							i2 = i2 / 2 << 1;
							if ((float)this.noiseGen5.a(k / 5, m / 5) < 0.0F)
							{
								i2++;
							}	
						}
						
						int thickness = -25;
						int less = (int) Math.floor(Math.sqrt((k-0)*(k-0) + (m-0)*(m-0)) / width);
						if(less > 150) { less = 150; }
						thickness += less;
						
						double ovar32 = clamp(getNoise(8, k + (layer * 2000), m + (layer * 2000), 50, 50, 0));
						int var77 = (int) (ovar32 * (seaLevel / 2)) + 20 + (layer * 45) + thickness;
						
						boolean flagSand = noiseGen3.a(k + (layer * 2000F), m + (layer * 2000F)) > 52D + (less / 3D); 
						boolean flagGravel = noiseGen11.a(k + (layer * 2000F), m + (layer * 2000F)) > 62D + (less / 3D); 
						
						for (int i3 = 0; i3 < 256; i3++)
						{
							jj++;
							if(i3 == i2)
							{
								if(flagGravel)
								{
									blocks[jj] = Blocks.gravel;
								}
								else if(flagSand)
								{
									blocks[jj] = Blocks.sand;
								}
								else if(i3 > var77)
								{
									blocks[jj] = Blocks.stone;
								}
							}
							else if (i3 > var77 && i3 < i2)
							{
								blocks[jj] = Blocks.stone;
							}
						}
					}	
				}
			}
		}
	}
	
	public void generateSurface(int par1, int par2, Block[] blocks)
	{
		int jj = 0;
		
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				int t = -1;
				boolean air = true;
				
				jj+=256;
				for (int y = 255; y > -1; y--)
				{
					Block b = Blocks.air;
					
					int l1 = (x * 16 + z) * 256 + y;
					if(blocks[l1] == null)
					{
						b = Blocks.air;
						t = -1;
					}
					else if(blocks[l1] == Blocks.stone)
					{
						t++;
						if(t == 0 && air)
						{
							b = Blocks.grass;
						}
						else if(t < 3)
						{
							b = Blocks.dirt;
						}
						else
						{
							b = Blocks.stone;
						}
						air = false;
					}
					else
					{
						t++;
						b = blocks[l1];
					}
					jj--;
					blocks[jj] = b;
				}
				jj+=256;
			}
		}
	}
	
	public void generateTerrain(int par1, int par2, Block[] ba)
	{
		int height = 128;
		int seaLevel = 64;
		//Block[] blocks = new Block[32768];
		int i = par1 << 4;
		int j = par2 << 4;
		int jj = 0;
		int lx = 0; int lz = 0;

		/*if(typeFinite && (par1 < -7 || par1 > 7 || par2 < -7 || par2 > 7))
		{
			for (int k = i; k < i + 16; k++)
			{
				for (int m = j; m < j + 16; m++)
				{
					for (int i3 = 0; i3 < 128; i3++)
					{
						int i4 = 0;
						if(i3 < 63)
						{
							i4 = Block.stone.blockID;
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
		else
		{*/
			for (int k = i; k < i + 16; k++)
			{
				for (int m = j; m < j + 16; m++)
				{
					int n = k / 1024;
					int i1 = m / 1024;
					
					int i2 = 64;
					if(typeIsland)
					{
						float f2 = (float)this.noiseGen5.a(k / 4.0F, m / 4.0F);
						i2 = 74 - ((int) Math.floor(Math.sqrt((0D-k)*(0D-k) + (0D-m)*(0D-m)) / (double) size));
						if(i2 < 50) { i2 = 50; }
						i2 += ((int) f2);
					}
					else
					{
						float f1 = (float)(this.noiseGen1.a(k / 0.03125F, 0.0D, m / 0.03125F) - this.noiseGen2.a(k / 0.015625F, 0.0D, m / 0.015625F)) / 512.0F / 4.0F;
						float f2 = (float)this.noiseGen5.a(k / 4.0F, m / 4.0F);
						float f3 = (float)this.noiseGen6.a(k / 8.0F, m / 8.0F) / 8.0F;
						f2 = f2 > 0.0F ? (float)(this.noiseGen3.a(k * 0.2571428F * 2.0F, m * 0.2571428F * 2.0F) * f3 / 4.0D) : (float)(this.noiseGen4.a(k * 0.2571428F, m * 0.2571428F) * f3);
						i2 = (int)(f1 + 64.0F + f2);
					}
					
					if ((float)this.noiseGen5.a(k, m) < 0.0F)
					{
						i2 = i2 / 2 << 1;
						if ((float)this.noiseGen5.a(k / 5, m / 5) < 0.0F)
						{
							i2++;
						}	
					}
	
					//BEACH SETTINGS
					boolean flagSand = noiseGen3.a(k, m) > 8D;
					boolean flagGravel = noiseGen11.a(k, m) > 18D;
					if(themePARADISE)
					{ 
						flagSand = noiseGen3.a(k, m) > -32D; 
					}
					else if(themeHELL || themeWOODS)
					{ 
						flagSand = noiseGen3.a(k, m) > -8D; 
					}
					
					if(typeIsland)
					{
						flagSand = true;
					}

					//CREATE WORLD
					for (int i3 = 0; i3 < 256; i3++)
					{
						Block i4 = Blocks.air;
						int beachHeight = seaLevel + 1;
						if(themePARADISE){ beachHeight = seaLevel + 3; }
						
						//GENERATE BEDROCK
						if(i3 == 0)
						{
							i4 = Blocks.bedrock;
						}
						
						//GENERATE GRASS
						else if ((i3 == i2) && i2 >= beachHeight) 
						{
							if(themeHELL)
							{
								i4 = Blocks.dirt;
							}
							else
							{
								i4 = Blocks.grass;
							}	
						}
						
						//BEACH GEN
						else if (i3 == i2)
						{
							if(flagGravel)
							{
								i4 = Blocks.gravel;
								if(themeHELL)
								{
									i4 = Blocks.dirt;
								}
							}
							else if(flagSand)
							{
								i4 = Blocks.sand;
								if(themeHELL)
								{
									i4 = Blocks.dirt;
								}
							}
							else if (i2 > seaLevel - 1)
							{
								i4 = Blocks.grass;
							}
							else
							{
								i4 = Blocks.dirt;
							}
						}
						
						//GENERATE STONE
						else if (i3 <= i2 - 2)
						{
							i4 = Blocks.stone;
						}
						
						//GENERATE DIRT
						else if (i3 < i2)
						{
							i4 = Blocks.dirt;
						}
	
						//GENERATE LIQUIDS
						else if (i3 <= 64 && !typeFloating)
						{
							if(themeHELL)
							{
								if (i3 == 64)
								{
									i4 = Blocks.flowing_lava;
								}
								else
								{
									i4 = Blocks.lava;
								}
							}
							else
							{
								i4 = Blocks.water;
							}	
						}	
				
						rand.setSeed(n + i1 * 13871);
						int i5 = (n << 10) + 128 + rand.nextInt(512);
						int i6 = (i1 << 10) + 128 + rand.nextInt(512);
						i5 = k - i5;
						int i7 = m - i6;
						if (i5 < 0)
						{
							i5 = -i5;
						}	
						if (i7 < 0)
						{
							i7 = -i7;
						}
						if (i7 > i5)
						{
							i5 = i7;
						}	
						if ((i5 = 127 - i5) == 255)
						{
							i5 = 1;
						}	
						if (i5 < i2)
						{
							i5 = i2;
						}	
						if ((i3 <= i5) && ((i4 == Blocks.air) || (i4 == Blocks.water) || (i4 == Blocks.lava)))
						{
							i4 = Blocks.brick_block;
						} /*
						if (i4 < 0)
						{
							i4 = 0;
						} */
						
						ba[jj++] = i4;
					}
				}	
			}
		//}
	}
	
    private double clamp(double input)
	{
		if (input > 1.0D)
		{
			return 1.0D;
		}
		if (input < -1.0D)
		{
			return -1.0D;
		}
		return input;
	}
    
	private double getNoise(int level, int x, int y, double xfact, double yfact, double zstart)
	{
		double output = 0;
        for (double l = 1; l <= level*level; l *= 2)
        {
            output += perlinGen1.a((x / xfact) * l, (y / yfact) * l) / l;
        }
		return output; 
	}

    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    public Chunk provideChunk(int cx, int cy)
    {
    	this.rand.setSeed((long)cx * 341873128712L + (long)cy * 132897987541L);
    	Block[] var3 = new Block[65536];
    	byte[] metadata = new byte[65536];
    	
        if(typeFloating)
        {
        	generateSkylands(cx, cy, var3);
        	generateSurface(cx, cy, var3);
        }
        else
        {
        	generateTerrain(cx, cy, var3);
        }
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, cx * 16, cy * 16, 16, 16);

        if(!typeFloating)
        {
	        caveGenerator.func_151539_a(this, this.worldObj, cx, cy, var3);
	        if (mapFeaturesEnabled)
	        {
	            mineshaftGenerator.func_151539_a(this, this.worldObj, cx, cy, var3);
	            strongholdGenerator.func_151539_a(this, this.worldObj, cx, cy, var3);
	        }
        }
        
        Chunk var4 = new Chunk(worldObj, var3, metadata, cx, cy);
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
        BlockSand.fallInstantly = true;
        int var4 = par2 * 16;
        int var5 = par3 * 16; 
        BiomeGenBase var6 = worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)par2 * var7 + (long)par3 * var9 ^ this.worldObj.getSeed());
		double d = 0.25D;
		
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, worldObj, rand, par2, par3, false));
		
        if (mapFeaturesEnabled && !typeFloating)
        {
            mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
            strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
        }
        
    	if(par2 == (int) Math.floor(worldObj.getWorldInfo().getSpawnX() / 16) && par3 == (int) Math.floor(worldObj.getWorldInfo().getSpawnZ() / 16))
    	{
			int ix = worldObj.getWorldInfo().getSpawnX();
			int iz = worldObj.getWorldInfo().getSpawnZ();
			int iy = worldObj.getTopSolidOrLiquidBlock(ix, iz);
	
			(new DecoIndevHouse(1)).generate(worldObj, rand, ix, iy, iz);
    	}
    	
    	int extraheight = 128;
    	int extradeco = 1;
		if(typeFloating)
		{
			extradeco = 2;
			extraheight = 256;
		}
		
		//ORES
		if(typeFloating)
		{
			if(rand.nextInt(30) == 0)
			{
				int j5 = var4 + rand.nextInt(16) + 8;
				int k88 = rand.nextInt(15);
				int j11 = var5 + rand.nextInt(16) + 8;
				if(rand.nextInt(8) == 0)
				{
					(new DecoDungeons(2, false, false, false, true)).generate(worldObj, rand, j5, k88, j11);
				}
				else
				{
					(new DecoDungeons(2, false, false, true, false)).generate(worldObj, rand, j5, k88, j11);
				}
			}
		}
		else
		{	
			for(int k1 = 0; k1 < 12; k1++)
			{
				int j5 = var4 + rand.nextInt(16) + 8;
				int k88 = rand.nextInt(128);
				int j11 = var5 + rand.nextInt(16) + 8;
				(new DecoDungeons(1, false, true, false, false)).generate(worldObj, rand, j5, k88, j11);
			}
		}
		
		for(int k2 = 0; k2 < 5; k2++)
		{
			int i6 = var4 + rand.nextInt(16);
			int j9 = rand.nextInt(64);
			int i12 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.gravel, 32, 2)).generate(worldObj, rand, i6, j9, i12);
		}

		for(int i3 = 0; i3 < 20 * extradeco; i3++)
		{
			int j6 = var4 + rand.nextInt(16);
			int k9 = rand.nextInt(extraheight);
			int j12 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.coal_ore, 16, 2)).generate(worldObj, rand, j6, k9, j12);
		}

		for(int j3 = 0; j3 < 20 * extradeco; j3++)
		{
			int k6 = var4 + rand.nextInt(16);
			int l9 = rand.nextInt(64 * extradeco);
			int k12 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.iron_ore, 8, 2)).generate(worldObj, rand, k6, l9, k12);
		}
		
		int floatingore = 0;
		if(typeFloating) { floatingore = 16; }

		for(int k3 = 0; k3 < 2; k3++)
		{
			int l6 = var4 + rand.nextInt(16);
			int i10 = rand.nextInt(32) + floatingore;
			int l12 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.gold_ore, 8, 2)).generate(worldObj, rand, l6, i10, l12);
		}

		for(int l33 = 0; l33 < 8; l33++)
		{
			int i7 = var4 + rand.nextInt(16);
			int j10 = rand.nextInt(16) + floatingore;
			int i13 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.redstone_ore, 7, 2)).generate(worldObj, rand, i7, j10, i13);
		}

		for(int i4 = 0; i4 < 1; i4++)
		{
			int j7 = var4 + rand.nextInt(16);
			int k10 = rand.nextInt(16) + floatingore;
			int j13 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.diamond_ore, 7, 2)).generate(worldObj, rand, j7, k10, j13);
		}

		for(int j4 = 0; j4 < 1; j4++)
		{
			int k7 = var4 + rand.nextInt(16);
			int l10 = rand.nextInt(16) + floatingore;
			int k13 = var5 + rand.nextInt(16);
			(new OldGenMinable(Blocks.lapis_ore, 6, 2)).generate(worldObj, rand, k7, l10, k13);
		}		
		
		//TREES
        d = 0.5D;
        int l333 = (int)((mobSpawnerNoise.func_806_a((double)var4 * d, (double)var5 * d) / 8D + rand.nextDouble() * 4D + 4D) / 3D);
        if(l333 < 0)
        {
            l333 = 0;
        }
        if(rand.nextInt(10) == 0)
        {
            l333++;
        }
		if(themeWOODS){ l333 += 8; }
		else if(typeIsland){ l333 += 1; }
		Object obj = new WorldGenTrees(false, 5, 0, 0, false);
        for(int k88 = 0; k88 < l333; k88++)
        {
            int j133 = var4 + rand.nextInt(16) + 8;
            int l155 = var5 + rand.nextInt(16) + 8;
            ((WorldGenerator) (obj)).setScale(1.0D, 1.0D, 1.0D);
            ((WorldGenerator) (obj)).generate(worldObj, rand, j133, worldObj.getHeightValue(j133, l155), l155);
        }
		
		//FLOWERS
		int amount1 = 2; 
		if(themePARADISE){ amount1 = 8; }
		for(int i34 = 0; i34 < amount1; i34++)
		{
			for(int i14 = 0; i14 < 2 * extradeco; i14++)
			{
				int k14 = var4 + rand.nextInt(16) + 8;
				int l16 = rand.nextInt(extraheight);
				int k19 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Blocks.yellow_flower)).generate(worldObj, rand, k14, l16, k19);
			}
			if(rand.nextInt(2 / extradeco) == 0)
			{
				int j15 = var4 + rand.nextInt(16) + 8;
				int j17 = rand.nextInt(extraheight);
				int j20 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Blocks.red_flower)).generate(worldObj, rand, j15, j17, j20);
			}
		}	
		
		//MUSHROOMS
		if(themeHELL || themeWOODS) 
		{
			int k15 = var4 + rand.nextInt(16) + 8;
			int k17 = rand.nextInt(extraheight);
			int k20 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Blocks.brown_mushroom)).generate(worldObj, rand, k15, k17, k20);
			
			if(rand.nextInt(2 / extradeco) == 0)
			{
				int l15 = var4 + rand.nextInt(16) + 8;
				int l17 = rand.nextInt(extraheight);
				int l20 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Blocks.red_mushroom)).generate(worldObj, rand, l15, l17, l20);
			} 
		}
		else
		{
			if(rand.nextInt(4 / extradeco) == 0)
			{
				int k15 = var4 + rand.nextInt(16) + 8;
				int k17 = rand.nextInt(extraheight);
				int k20 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Blocks.brown_mushroom)).generate(worldObj, rand, k15, k17, k20);
			}
			if(rand.nextInt(8 / extradeco) == 0)
			{
				int l15 = var4 + rand.nextInt(16) + 8;
				int l17 = rand.nextInt(extraheight);
				int l20 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Blocks.red_mushroom)).generate(worldObj, rand, l15, l17, l20);
			} 
		}
		
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, var6, var4 + 8, var5 + 8, 16, 16, this.rand);
        BlockSand.fallInstantly = false;
		
		if(themeSNOW)
		{
			var4 += 8;
			var5 += 8;

			for (int var85 = 0; var85 < 16; ++var85)
			{
				for (int var86 = 0; var86 < 16; ++var86)
				{
					int var87 = this.worldObj.getPrecipitationHeight(var4 + var85, var5 + var86);
					
					if (this.worldObj.isBlockFreezable(var85 + var4, var87 - 1, var86 + var5))
					{
						this.worldObj.setBlock(var85 + var4, var87 - 1, var86 + var5, Blocks.ice, 0, 2);
					}

					Block b = worldObj.getBlock(var85 + var4, var87 - 1, var86 + var5);
					if (this.worldObj.func_147478_e(var85 + var4, var87, var86 + var5, false) && b != Blocks.ice && b != Blocks.water && var87 > 63)
					{
						this.worldObj.setBlock(var85 + var4, var87, var86 + var5, Blocks.snow_layer, 0, 2);
					}
				}
			}
		}
		
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, worldObj, rand, par2, par3, false));
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

    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(par1World, par3, par4, par5) : null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void saveExtraData() {}

    public void recreateStructures(int par1, int par2)
    {
        if (this.mapFeaturesEnabled && !typeFloating)
        {
            this.mineshaftGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
            this.strongholdGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
        }
    }
}
