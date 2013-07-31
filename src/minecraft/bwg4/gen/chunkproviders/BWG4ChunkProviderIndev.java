package bwg4.gen.chunkproviders;

import java.util.List;
import java.util.Random;

import bwg4.chunk.BWG4SkylightMap;
import bwg4.deco.BWG4decoDungeons;
import bwg4.deco.BWG4decoIndevHouse;
import bwg4.deco.old.BWG4oldGenMinable;
import bwg4.deco.old.BWG4oldGenTrees;
import bwg4.noise.BWG4NoiseOctavesIndev;
import bwg4.noise.BWG4NoiseOctavesInfdev;
import bwg4.noise.BWG4NoisePerlinIndev;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class BWG4ChunkProviderIndev implements IChunkProvider
{
    private Random rand;
    private BWG4NoiseOctavesIndev noiseGen1;
    private BWG4NoiseOctavesIndev noiseGen2;
    private BWG4NoiseOctavesIndev noiseGen3;
    private BWG4NoiseOctavesIndev noiseGen4;
    public BWG4NoiseOctavesIndev noiseGen5;
    public BWG4NoiseOctavesIndev noiseGen6;
    public BWG4NoiseOctavesInfdev mobSpawnerNoise;
	public BWG4NoiseOctavesIndev noiseGen10;
	public BWG4NoiseOctavesIndev noiseGen11;
	public BWG4NoisePerlinIndev perlinGen1;

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

    public BWG4ChunkProviderIndev(World par1World, long par2, boolean par4, int type, int theme)
    {
        this.worldObj = par1World;
        this.mapFeaturesEnabled = par4;
        this.rand = new Random(par2);
        this.noiseGen1 = new BWG4NoiseOctavesIndev(this.rand, 16);
        this.noiseGen2 = new BWG4NoiseOctavesIndev(this.rand, 16);
        this.noiseGen3 = new BWG4NoiseOctavesIndev(this.rand, 8);
        this.noiseGen4 = new BWG4NoiseOctavesIndev(this.rand, 4);
        this.noiseGen5 = new BWG4NoiseOctavesIndev(this.rand, 4);
        this.noiseGen6 = new BWG4NoiseOctavesIndev(this.rand, 5);
        this.mobSpawnerNoise = new BWG4NoiseOctavesInfdev(this.rand, 8);
        this.noiseGen10 = new BWG4NoiseOctavesIndev(this.rand, 6);
        this.noiseGen11 = new BWG4NoiseOctavesIndev(this.rand, 8);
		this.perlinGen1 = new BWG4NoisePerlinIndev(this.rand);
		
		if(theme == 2) { themeHELL = true; }
		if(theme == 3) { themePARADISE = true; }
		if(theme == 4) { themeWOODS = true; }
		if(theme == 5) { themeSNOW = true; }
		if(type == 1) { typeIsland = true; }
		if(type == 2) { typeFloating = true; }
		if(type == 3) { typeInland = true; }
		if(type == 4) { typeFinite = true; } 
    }

	public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte)
	{
        int layers = 1;
		int height = 128;
		int seaLevel = 64;
		byte[] arrayOfByte = new byte[32768];
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
						i2 = 74 - ((int) Math.floor(Math.sqrt((0D-k)*(0D-k) + (0D-m)*(0D-m)) / 5D));
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
					if(typeFloating)
					{ 
						flagSand = noiseGen3.a(k, m) > 25D; 
						flagGravel = noiseGen11.a(k, m) > 50D; 
					}
					else if(themePARADISE)
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
			
					double ovar32 = clamp(getNoise(8, k, m, 70.3, 70.3, 0));
					int var77 = (int) ((ovar32 * (seaLevel / 2)) * 2) + seaLevel;
					//if (var77 > seaLevel)
					//{
					//	var77 = height;
					//}			
			
					//CREATE WORLD
					for (int i3 = 0; i3 < 128; i3++)
					{
						int i4 = 0;
						int beachHeight = seaLevel + 1;
						if(themePARADISE){ beachHeight = seaLevel + 3; }
						
						//GENERATE BEDROCK
						if(!typeFloating && i3 == 0)
						{
							i4 = Block.bedrock.blockID;
						}
						
						//GENERATE GRASS
						else if ((i3 == i2) && i2 >= beachHeight) 
						{
							if(themeHELL)
							{
								i4 = Block.dirt.blockID;
							}
							else
							{
								i4 = Block.grass.blockID;
							}	
						}
						
						//BEACH GEN
						else if (i3 == i2)
						{
							if(flagGravel)
							{
								i4 = Block.gravel.blockID;
								if(themeHELL)
								{
									i4 = Block.dirt.blockID;
								}
							}
							else if(flagSand)
							{
								i4 = Block.sand.blockID;
								if(themeHELL)
								{
									i4 = Block.dirt.blockID;
								}
							}
							else if (i2 > seaLevel - 1)
							{
								i4 = Block.grass.blockID;
							}
							else if (typeFloating)
							{
								i4 = Block.grass.blockID;
							}
							else
							{
								i4 = Block.dirt.blockID;
							}
						}
						
						//GENERATE STONE
						else if (i3 <= i2 - 2)
						{
							i4 = Block.stone.blockID;
						}
						
						//GENERATE DIRT
						else if (i3 < i2)
						{
							i4 = Block.dirt.blockID;
						}
	
						//GENERATE LIQUIDS
						else if (i3 <= 64 && !typeFloating)
						{
							if(themeHELL)
							{
								if (i3 == 64)
								{
									i4 = Block.lavaMoving.blockID;
								}
								else
								{
									i4 = Block.lavaStill.blockID;
								}
							}
							else
							{
								i4 = Block.waterStill.blockID;
							}	
						}	
	
						if (typeFloating && i3 < var77 && i4 != 0)
						{
							if((i3 > 60) && i4 == Block.gravel.blockID ) { }
							else if((i3 > 60) && i4 == Block.sand.blockID ) { }
							else
							{
								i4 = 0;
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
						if (!typeFloating && (i3 <= i5) && ((i4 == 0) || (i4 == Block.waterStill.blockID) || (i4 == Block.lavaStill.blockID)))
						{
							i4 = Block.brick.blockID;
						}
						if (i4 < 0)
						{
							i4 = 0;
						}
						
						par3ArrayOfByte[jj++] = (byte)i4;
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

    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        byte[] var3 = new byte[32768];
        this.generateTerrain(par1, par2, var3);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.caveGenerator.generate(this, this.worldObj, par1, par2, var3);

        if (mapFeaturesEnabled && !typeFloating)
        {
            mineshaftGenerator.generate(this, this.worldObj, par1, par2, var3);
            strongholdGenerator.generate(this, this.worldObj, par1, par2, var3);
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
	
			(new BWG4decoIndevHouse(1)).generate(worldObj, rand, ix, iy, iz);
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
					(new BWG4decoDungeons(6, false, false, true)).generate(worldObj, rand, j5, k88, j11);
				}
				else
				{
					(new BWG4decoDungeons(6, false, true, false)).generate(worldObj, rand, j5, k88, j11);
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
				(new BWG4decoDungeons(5, true, false, false)).generate(worldObj, rand, j5, k88, j11);
			}
		}
		
		for(int k2 = 0; k2 < 5; k2++)
		{
			int i6 = var4 + rand.nextInt(16);
			int j9 = rand.nextInt(64);
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
		
		int floatingore = 0;
		if(typeFloating) { floatingore = 16; }

		for(int k3 = 0; k3 < 2; k3++)
		{
			int l6 = var4 + rand.nextInt(16);
			int i10 = rand.nextInt(32) + floatingore;
			int l12 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreGold.blockID, 8, 2)).generate(worldObj, rand, l6, i10, l12);
		}

		for(int l33 = 0; l33 < 8; l33++)
		{
			int i7 = var4 + rand.nextInt(16);
			int j10 = rand.nextInt(16) + floatingore;
			int i13 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreRedstone.blockID, 7, 2)).generate(worldObj, rand, i7, j10, i13);
		}

		for(int i4 = 0; i4 < 1; i4++)
		{
			int j7 = var4 + rand.nextInt(16);
			int k10 = rand.nextInt(16) + floatingore;
			int j13 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreDiamond.blockID, 7, 2)).generate(worldObj, rand, j7, k10, j13);
		}

		for(int j4 = 0; j4 < 1; j4++)
		{
			int k7 = var4 + rand.nextInt(16);
			int l10 = rand.nextInt(16) + floatingore;
			int k13 = var5 + rand.nextInt(16);
			(new BWG4oldGenMinable(Block.oreLapis.blockID, 6, 2)).generate(worldObj, rand, k7, l10, k13);
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
		else if(typeIsland){ l333 += 2; }
		Object obj = new BWG4oldGenTrees(0);
        for(int k88 = 0; k88 < l333; k88++)
        {
            int j133 = var4 + rand.nextInt(16) + 8;
            int l155 = var5 + rand.nextInt(16) + 8;
            ((WorldGenerator) (obj)).setScale(1.0D, 1.0D, 1.0D);
            ((WorldGenerator) (obj)).generate(worldObj, rand, j133, worldObj.getHeightValue(j133, l155), l155);
        }
		
		//FLOWERS
		int amount1 = 1; 
		if(themePARADISE){ amount1 = 5; }
		for(int i34 = 0; i34 < amount1; i34++)
		{
			for(int i14 = 0; i14 < 2; i14++)
			{
				int k14 = var4 + rand.nextInt(16) + 8;
				int l16 = rand.nextInt(128);
				int k19 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Block.plantYellow.blockID)).generate(worldObj, rand, k14, l16, k19);
			}
			if(rand.nextInt(2) == 0)
			{
				int j15 = var4 + rand.nextInt(16) + 8;
				int j17 = rand.nextInt(128);
				int j20 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Block.plantRed.blockID)).generate(worldObj, rand, j15, j17, j20);
			}
		}	
		
		//MUSHROOMS
		if(themeHELL || themeWOODS) 
		{
			int k15 = var4 + rand.nextInt(16) + 8;
			int k17 = rand.nextInt(128);
			int k20 = var5 + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, rand, k15, k17, k20);
			
			if(rand.nextInt(2) == 0)
			{
				int l15 = var4 + rand.nextInt(16) + 8;
				int l17 = rand.nextInt(128);
				int l20 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, rand, l15, l17, l20);
			} 
		}
		else
		{
			if(rand.nextInt(4) == 0)
			{
				int k15 = var4 + rand.nextInt(16) + 8;
				int k17 = rand.nextInt(128);
				int k20 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, rand, k15, k17, k20);
			}
			if(rand.nextInt(8) == 0)
			{
				int l15 = var4 + rand.nextInt(16) + 8;
				int l17 = rand.nextInt(128);
				int l20 = var5 + rand.nextInt(16) + 8;
				(new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, rand, l15, l17, l20);
			} 
		}

		//WATER AND LAVA
		for(int h19 = 0; h19 < 40; h19++)
		{
			int h21 = var4 + rand.nextInt(16) + 8;
			int h23 = rand.nextInt(rand.nextInt(120) + 8);
			int h24 = var5 + rand.nextInt(16) + 8;
			(new WorldGenLiquids(Block.waterMoving.blockID)).generate(worldObj, rand, h21, h23, h24);
		}

		for(int m19 = 0; m19 < 15; m19++)
		{
			int m22 = var4 + rand.nextInt(16) + 8;
			int m23 = rand.nextInt(rand.nextInt(rand.nextInt(112) + 8) + 8);
			int m25 = var5 + rand.nextInt(16) + 8;
			(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(worldObj, rand, m22, m23, m25);
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
						this.worldObj.setBlock(var85 + var4, var87 - 1, var86 + var5, Block.ice.blockID, 0, 2);
					}

					if (this.worldObj.canSnowAt(var85 + var4, var87, var86 + var5))
					{
						this.worldObj.setBlock(var85 + var4, var87, var86 + var5, Block.snow.blockID, 0, 2);
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
        if (this.mapFeaturesEnabled && !typeFloating)
        {
            this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
            this.strongholdGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
        }
    }
}
