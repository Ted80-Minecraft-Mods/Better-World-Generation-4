package ted80.bwg4.biomes;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.REED;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND_PASS2;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE;

import java.util.Random;

import ted80.bwg4.deco.BWG4decoPumpkin;
import ted80.bwg4.noise.BWG4NoiseOctavesBeta;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BWG4Decorator extends BiomeDecorator
{
	//BASIC STUFF
    protected World currentWorld;
    protected Random randomGenerator;
    protected int chunk_X;
    protected int chunk_Z;
    protected BiomeGenBase biome;
	
	//NEW DECORATOR
	protected boolean usebwg4deco;
    public BWG4NoiseOctavesBeta TreeNoise;
	
	//treelayers
	protected boolean mayrandtrees; //use the beta tree generator
	protected int tl1miny; //treelayer 1 minY
	protected int tl1maxy; //treelayer 1 maxY
	protected int tl1amount; //treelayer 1 amount of trees
	protected int tl1chance; //treelayer 1 spawning chance
	protected boolean usetl2; //use second tree layer?
	protected int tl2miny; //treelayer 2 minY
	protected int tl2maxy; //treelayer 2 maxY
	protected int tl2amount; //treelayer 2 amount of trees
	protected int tl2chance; //treelayer 2 spawning chance
	
	//ores
	protected boolean shiftoreheight; //used for skylands and skydimension
	protected boolean disableoreheight; //used for cavedimension
	protected boolean emeralds; //enable or disable emeralds
	protected boolean silverfish; //enable or disable rare silverfish
    protected WorldGenerator dirtGen;
    protected WorldGenerator gravelGen;
    protected WorldGenerator coalGen;
    protected WorldGenerator ironGen;
    protected WorldGenerator goldGen;
    protected WorldGenerator redstoneGen;
    protected WorldGenerator diamondGen;
    protected WorldGenerator lapisGen;
	protected WorldGenerator emeraldGen;
	protected WorldGenerator silverfishGen;
	
	//surface
	protected int bigMushrooms; //bigmushrooms per chunk
	protected int redflowers; //red flowers per chunk
	protected int yellowflowers; //yellow flowers per chunk
	protected int grass; //grass per chunk
	protected int grassminy; //grass minY
	protected int grassmaxy; //grass maxY
	protected int deadBush; //deadbush per chunk
	protected int waterlily; //waterlilys per chunk
	protected int brownmush; //brown mushrooms per chunk
	protected int redmush; //red mushrooms per chunk
	protected int sugarcane; //reeds per chunk
	protected int cactus; //cacti per chunk
	protected int melon; //melon per chunk
	protected int pumpkin; //pumpkin per chunk		
	protected int waterliquid; //Water per chunk
	protected int lavaliquid; //Lava per chunk
	
	//OLD DECORATOR
    protected WorldGenerator clayGen = new WorldGenClay(4);
    protected WorldGenerator sandGen;
    protected WorldGenerator gravelAsSandGen;
    protected WorldGenerator plantYellowGen;
    protected WorldGenerator plantRedGen;
    protected WorldGenerator mushroomBrownGen;
    protected WorldGenerator mushroomRedGen;
    protected WorldGenerator bigMushroomGen;
    protected WorldGenerator reedGen;
    protected WorldGenerator cactusGen;
    protected WorldGenerator waterlilyGen;
    protected int waterlilyPerChunk;
    protected int treesPerChunk;
    protected int flowersPerChunk;
    protected int grassPerChunk;
    protected int deadBushPerChunk;
    protected int mushroomsPerChunk;
    protected int reedsPerChunk;
    protected int cactiPerChunk;
    protected int sandPerChunk;
    protected int sandPerChunk2;
    protected int clayPerChunk;
    protected int bigMushroomsPerChunk;
    public boolean generateLakes2;

    public BWG4Decorator(BiomeGenBase par1BiomeGenBase)
    {
    	super(par1BiomeGenBase);
    	
		//BASIC STUFF
        biome = par1BiomeGenBase;	
		usebwg4deco = false;
		
		//treelayers
		mayrandtrees = true; 
		tl1miny = 0; 
		tl1maxy = 128; 
		tl1amount = 2; 
		tl1chance = 1;
		usetl2 = false;
		tl2miny = 0; 
		tl2maxy = 128; 
		tl2amount = 0;
		tl2chance = 1;
		
		//ores
		shiftoreheight = false; 
		disableoreheight = false; 
		emeralds = false;
		silverfish = false;
        dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
        gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
		coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
        ironGen = new WorldGenMinable(Block.oreIron.blockID, 8);
        goldGen = new WorldGenMinable(Block.oreGold.blockID, 8);
        redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 7);
        diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 7);
        lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 6);
        emeraldGen = new WorldGenMinable(Block.oreEmerald.blockID, 1);
		silverfishGen = new WorldGenMinable(Block.silverfish.blockID, 1);
		
		//surface
        bigMushrooms = 0;
		redflowers = 1;
		yellowflowers = 1; 
		grass = 1; 
		grassminy = 0; 
		grassmaxy = 128; 
		deadBush = 0;  
		waterlily = 0;
		brownmush = 0;
		redmush = 0; 
		sugarcane = 10; 
		cactus = 0;
		melon = 32;
		pumpkin = 32; 
		waterliquid = 50;
		lavaliquid = 20;
		
		//OLD DECORATOR
        sandGen = new WorldGenSand(7, Block.sand.blockID);
        gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
        plantYellowGen = new WorldGenFlowers(Block.plantYellow.blockID);
        plantRedGen = new WorldGenFlowers(Block.plantRed.blockID);
        mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
        mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
        bigMushroomGen = new WorldGenBigMushroom();
        reedGen = new WorldGenReed();
        cactusGen = new WorldGenCactus();
        waterlilyGen = new WorldGenWaterlily();
        waterlilyPerChunk = 0;
        treesPerChunk = 0;
        flowersPerChunk = 2;
        grassPerChunk = 1;
        deadBushPerChunk = 0;
        mushroomsPerChunk = 0;
        reedsPerChunk = 0;
        cactiPerChunk = 0;
        sandPerChunk = 1;
        sandPerChunk2 = 3;
        clayPerChunk = 1;
        bigMushroomsPerChunk = 0;
        generateLakes = true;
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        if (currentWorld != null)
        {
            //throw new RuntimeException("Already decorating!!");
        }
        else
        {
            currentWorld = par1World;
			TreeNoise = new BWG4NoiseOctavesBeta(par2Random, 8);
            randomGenerator = par2Random;
            chunk_X = par3;
            chunk_Z = par4;
            decorate();
            currentWorld = null;
            randomGenerator = null;
        }
    }
	
    protected void decorate()
    {
    	MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
    	
		if(usebwg4deco == true) //NEW DECORATOR
		{	
			//===== GENERATE ORES =====
			generateOres();

			//===== TREE LAYER 1 =====
			double treedouble = 0.5D;
			int l = (int)((TreeNoise.func_806_a((double)chunk_X * treedouble, (double)chunk_Z * treedouble) / 8D + randomGenerator.nextDouble() * 4D + 4D) / 3D);
			if(l < 0)
			{
				l = 0;
			}
			l = l + tl1amount;
			if(mayrandtrees == false)
			{
				l = tl1amount;
			}
			if (randomGenerator.nextInt(10) == 0)
			{
				l++;
			}
			
			for (int l1 = 0; l1 < l; l1++)
			{
				int j6 = chunk_X + randomGenerator.nextInt(16) + 8;
				int k10 = chunk_Z + randomGenerator.nextInt(16) + 8;
				if(currentWorld.getHeightValue(j6, k10) < tl1maxy && currentWorld.getHeightValue(j6, k10) > tl1miny && randomGenerator.nextInt(tl1chance) == 0)
				{
					WorldGenerator worldgenerator = biome.getRandomWorldGenForTrees(randomGenerator);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(currentWorld, randomGenerator, j6, currentWorld.getHeightValue(j6, k10), k10);
				}	
			}
			
			//===== TREE LAYER 2 =====
			if(usetl2 == true)
			{
				int l2 = (int)((TreeNoise.func_806_a((double)chunk_X * treedouble, (double)chunk_Z * treedouble) / 8D + randomGenerator.nextDouble() * 4D + 4D) / 3D);
				if(l2 < 0)
				{
					l2 = 0;
				}
				l2 = l2 + tl2amount;
				if(mayrandtrees == false)
				{
					l2 = tl2amount;
				}
				if (randomGenerator.nextInt(10) == 0)
				{
					l2++;
				}
				
				for (int l1 = 0; l1 < l2; l1++)
				{
					int j6 = chunk_X + randomGenerator.nextInt(16) + 8;
					int k10 = chunk_Z + randomGenerator.nextInt(16) + 8;
					if(currentWorld.getHeightValue(j6, k10) < tl2maxy && currentWorld.getHeightValue(j6, k10) > tl2miny && randomGenerator.nextInt(tl2chance) == 0)
					{
						WorldGenerator worldgenerator = biome.getRandomWorldGenForTrees2(randomGenerator);
						worldgenerator.setScale(1.0D, 1.0D, 1.0D);
						worldgenerator.generate(currentWorld, randomGenerator, j6, currentWorld.getHeightValue(j6, k10), k10);
					}	
				}
			}
			
			//===== SURFACE =====
		
			for (int bm = 0; bm < bigMushrooms; ++bm)
			{
				int bm1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int bm2 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new WorldGenBigMushroom()).generate(currentWorld, randomGenerator, bm1, currentWorld.getHeightValue(bm1, bm2), bm2);
			}		
		
			for (int yf = 0; yf < yellowflowers; ++yf)
			{
				int yf1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int yf2 = randomGenerator.nextInt(128);
				int yf3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new WorldGenFlowers(Block.plantYellow.blockID)).generate(currentWorld, randomGenerator, yf1, yf2, yf3);
			}	

			for (int rf = 0; rf < redflowers; ++rf)
			{
				int rf1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int rf2 = randomGenerator.nextInt(128);
				int rf3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new WorldGenFlowers(Block.plantRed.blockID)).generate(currentWorld, randomGenerator, rf1, rf2, rf3);
			}	

			for (int gr = 0; gr < grass; ++gr)
			{
				int gr1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int gr2 = randomGenerator.nextInt(128);
				int gr3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				if( gr2 > grassminy && gr2 < grassmaxy)
				{
					WorldGenerator grr = biome.getRandomWorldGenForGrass(randomGenerator);
					grr.generate(currentWorld, randomGenerator, gr1, gr2, gr3);
				}	
			}

			for (int db = 0; db < deadBush; ++db)
			{
				int db1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int db2 = randomGenerator.nextInt(128);
				int db3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new WorldGenDeadBush(Block.deadBush.blockID)).generate(currentWorld, randomGenerator, db1, db2, db3);
			}

			for (int wl = 0; wl < waterlily; ++wl) 
			{
				int wl1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int wl3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				int wl2;

				for (wl2 = randomGenerator.nextInt(128); wl2 > 0 && currentWorld.getBlockId(wl1, wl2 - 1, wl3) == 0; --wl2)
				{
					;
				}

				(new WorldGenWaterlily()).generate(currentWorld, randomGenerator, wl1, wl2, wl3);
			}

			for (int bm = 0; bm < brownmush; ++bm)
			{
				if (randomGenerator.nextInt(4) == 0)
				{
					int bm1 = chunk_X + randomGenerator.nextInt(16) + 8;
					int bm3 = chunk_Z + randomGenerator.nextInt(16) + 8;
					int bm2 = currentWorld.getHeightValue(bm1, bm3);
					mushroomBrownGen.generate(currentWorld, randomGenerator, bm1, bm2, bm3);
				}
			}
			
			for (int rm = 0; rm < redmush; ++rm)
			{
				if (randomGenerator.nextInt(6) == 0)
				{
					int rm1 = chunk_X + randomGenerator.nextInt(16) + 8;
					int rm3 = chunk_Z + randomGenerator.nextInt(16) + 8;
					int rm2 = currentWorld.getHeightValue(rm1, rm3);
					mushroomRedGen.generate(currentWorld, randomGenerator, rm1, rm2, rm3);
				}
			}

			if (randomGenerator.nextInt(4) == 0)
			{
				int nbm1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int nbm2 = randomGenerator.nextInt(128);
				int nbm3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				mushroomBrownGen.generate(currentWorld, randomGenerator, nbm1, nbm2, nbm3);
			}

			if (randomGenerator.nextInt(8) == 0)
			{
				int nrm1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int nrm2 = randomGenerator.nextInt(128);
				int nrm3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				mushroomRedGen.generate(currentWorld, randomGenerator, nrm1, nrm2, nrm3);
			}

			for (int sc = 0; sc < sugarcane; ++sc)
			{
				int sc1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int sc3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				int sc2 = randomGenerator.nextInt(128);
				reedGen.generate(currentWorld, randomGenerator, sc1, sc2, sc3);
			}

			if (randomGenerator.nextInt(melon) == 0)
			{
				int mel1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int mel2 = randomGenerator.nextInt(128);
				int mel3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new BWG4decoPumpkin(1)).generate(currentWorld, randomGenerator, mel1, mel2, mel3);
			}
			
			if (randomGenerator.nextInt(pumpkin) == 0)
			{
				int pum1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int pum2 = randomGenerator.nextInt(128);
				int pum3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new BWG4decoPumpkin(0)).generate(currentWorld, randomGenerator, pum1, pum2, pum3);
			}

			for (int cac = 0; cac < cactus; ++cac)
			{
				int cac1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int cac2 = randomGenerator.nextInt(128);
				int cac3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				cactusGen.generate(currentWorld, randomGenerator, cac1, cac2, cac3);
			}

			for (int lqw = 0; lqw < waterliquid; ++lqw)
			{
				int lqw1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int lqw2 = randomGenerator.nextInt(randomGenerator.nextInt(120) + 8);
				int lqw3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new WorldGenLiquids(Block.waterMoving.blockID)).generate(currentWorld, randomGenerator, lqw1, lqw2, lqw3);
			}

			for (int lql = 0; lql < lavaliquid; ++lql)
			{
				int lql1 = chunk_X + randomGenerator.nextInt(16) + 8;
				int lql2 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(112) + 8) + 8);
				int lql3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(currentWorld, randomGenerator, lql1, lql2, lql3);
			}
		}
		else //OLD DECORATOR
		{
			generateOres();
			int var1;
			int var2;
			int var3;

			for (var1 = 0; var1 < sandPerChunk2; ++var1)
			{
				var2 = chunk_X + randomGenerator.nextInt(16) + 8;
				var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				sandGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
			}

			for (var1 = 0; var1 < clayPerChunk; ++var1)
			{
				var2 = chunk_X + randomGenerator.nextInt(16) + 8;
				var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				clayGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
			}

			for (var1 = 0; var1 < sandPerChunk; ++var1)
			{
				var2 = chunk_X + randomGenerator.nextInt(16) + 8;
				var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				sandGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
			}

			var1 = treesPerChunk;

			if (randomGenerator.nextInt(10) == 0)
			{
				++var1;
			}

			int var4;

			for (var2 = 0; var2 < var1; ++var2)
			{
				var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
				WorldGenerator var5 = biome.getRandomWorldGenForTrees(randomGenerator);
				var5.setScale(1.0D, 1.0D, 1.0D);
				var5.generate(currentWorld, randomGenerator, var3, currentWorld.getHeightValue(var3, var4), var4);
			}

			for (var2 = 0; var2 < bigMushroomsPerChunk; ++var2)
			{
				var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
				bigMushroomGen.generate(currentWorld, randomGenerator, var3, currentWorld.getHeightValue(var3, var4), var4);
			}

			int var7;

			for (var2 = 0; var2 < flowersPerChunk; ++var2)
			{
				var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				var4 = randomGenerator.nextInt(128);
				var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
				plantYellowGen.generate(currentWorld, randomGenerator, var3, var4, var7);

				if (randomGenerator.nextInt(4) == 0)
				{
					var3 = chunk_X + randomGenerator.nextInt(16) + 8;
					var4 = randomGenerator.nextInt(128);
					var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
					plantRedGen.generate(currentWorld, randomGenerator, var3, var4, var7);
				}
			}

			for (var2 = 0; var2 < grassPerChunk; ++var2)
			{
				var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				var4 = randomGenerator.nextInt(128);
				var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
				WorldGenerator var6 = biome.getRandomWorldGenForGrass(randomGenerator);
				var6.generate(currentWorld, randomGenerator, var3, var4, var7);
			}

			for (var2 = 0; var2 < deadBushPerChunk; ++var2)
			{
				var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				var4 = randomGenerator.nextInt(128);
				var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new WorldGenDeadBush(Block.deadBush.blockID)).generate(currentWorld, randomGenerator, var3, var4, var7);
			}

			for (var2 = 0; var2 < waterlilyPerChunk; ++var2)
			{
				var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				var4 = chunk_Z + randomGenerator.nextInt(16) + 8;

				for (var7 = randomGenerator.nextInt(128); var7 > 0 && currentWorld.getBlockId(var3, var7 - 1, var4) == 0; --var7)
				{
					;
				}

				waterlilyGen.generate(currentWorld, randomGenerator, var3, var7, var4);
			}

			for (var2 = 0; var2 < mushroomsPerChunk; ++var2)
			{
				if (randomGenerator.nextInt(4) == 0)
				{
					var3 = chunk_X + randomGenerator.nextInt(16) + 8;
					var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
					var7 = currentWorld.getHeightValue(var3, var4);
					mushroomBrownGen.generate(currentWorld, randomGenerator, var3, var7, var4);
				}

				if (randomGenerator.nextInt(8) == 0)
				{
					var3 = chunk_X + randomGenerator.nextInt(16) + 8;
					var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
					var7 = randomGenerator.nextInt(128);
					mushroomRedGen.generate(currentWorld, randomGenerator, var3, var7, var4);
				}
			}

			if (randomGenerator.nextInt(4) == 0)
			{
				var2 = chunk_X + randomGenerator.nextInt(16) + 8;
				var3 = randomGenerator.nextInt(128);
				var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
				mushroomBrownGen.generate(currentWorld, randomGenerator, var2, var3, var4);
			}

			if (randomGenerator.nextInt(8) == 0)
			{
				var2 = chunk_X + randomGenerator.nextInt(16) + 8;
				var3 = randomGenerator.nextInt(128);
				var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
				mushroomRedGen.generate(currentWorld, randomGenerator, var2, var3, var4);
			}

			for (var2 = 0; var2 < reedsPerChunk; ++var2)
			{
				var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
				var7 = randomGenerator.nextInt(128);
				reedGen.generate(currentWorld, randomGenerator, var3, var7, var4);
			}

			for (var2 = 0; var2 < 10; ++var2)
			{
				var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				var4 = randomGenerator.nextInt(128);
				var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
				reedGen.generate(currentWorld, randomGenerator, var3, var4, var7);
			}

			if (randomGenerator.nextInt(32) == 0)
			{
				var2 = chunk_X + randomGenerator.nextInt(16) + 8;
				var3 = randomGenerator.nextInt(128);
				var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new WorldGenPumpkin()).generate(currentWorld, randomGenerator, var2, var3, var4);
			}

			for (var2 = 0; var2 < cactiPerChunk; ++var2)
			{
				var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				var4 = randomGenerator.nextInt(128);
				var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
				cactusGen.generate(currentWorld, randomGenerator, var3, var4, var7);
			}

			if (generateLakes)
			{
				for (var2 = 0; var2 < 50; ++var2)
				{
					var3 = chunk_X + randomGenerator.nextInt(16) + 8;
					var4 = randomGenerator.nextInt(randomGenerator.nextInt(120) + 8);
					var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
					(new WorldGenLiquids(Block.waterMoving.blockID)).generate(currentWorld, randomGenerator, var3, var4, var7);
				}

				for (var2 = 0; var2 < 20; ++var2)
				{
					var3 = chunk_X + randomGenerator.nextInt(16) + 8;
					var4 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(112) + 8) + 8);
					var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
					(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(currentWorld, randomGenerator, var3, var4, var7);
				}
			}
		}
		
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }
	
    protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
        for (int var5 = 0; var5 < par1; ++var5)
        {
            int var6 = chunk_X + randomGenerator.nextInt(16);
            int var8 = chunk_Z + randomGenerator.nextInt(16);
			int var7;
			
			if(shiftoreheight == true) //IF SKY WORLDS
			{
				if(par4 < 17)
				{
					var7 = randomGenerator.nextInt(32) + 16;
				}
				else if(par4 > 111)
				{
					var7 = randomGenerator.nextInt(par4 - 16) + 16;
				}
				else
				{
					var7 = randomGenerator.nextInt(par4) + 16;
				}
			}
			else if(disableoreheight == true) //IF CAVE WORLDS
			{
				var7 = randomGenerator.nextInt(128);
			}
			else
			{
				var7 = randomGenerator.nextInt(par4 - par3) + par3;
			}
            par2WorldGenerator.generate(currentWorld, randomGenerator, var6, var7, var8);
        }
    }
	
    protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
        for (int var5 = 0; var5 < par1; ++var5)
        {
            int var6 = chunk_X + randomGenerator.nextInt(16);
            int var7 = randomGenerator.nextInt(par4) + randomGenerator.nextInt(par4) + (par3 - par4);
            int var8 = chunk_Z + randomGenerator.nextInt(16);
            par2WorldGenerator.generate(currentWorld, randomGenerator, var6, var7, var8);
        }
    }

    protected void generateOres()
    {
    	int diamondAmount = 1;
		if(disableoreheight == true) { diamondAmount = 2; }
    	
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
        
		if (TerrainGen.generateOre(currentWorld, randomGenerator, dirtGen, chunk_X, chunk_Z, DIRT))
		this.genStandardOre1(20, this.dirtGen, 0, 128);
        if (TerrainGen.generateOre(currentWorld, randomGenerator, gravelGen, chunk_X, chunk_Z, GRAVEL))
        this.genStandardOre1(10, this.gravelGen, 0, 128);
        if (TerrainGen.generateOre(currentWorld, randomGenerator, coalGen, chunk_X, chunk_Z, COAL))
        this.genStandardOre1(20, this.coalGen, 0, 128);
        if (TerrainGen.generateOre(currentWorld, randomGenerator, ironGen, chunk_X, chunk_Z, IRON))
        this.genStandardOre1(20, this.ironGen, 0, 64);
        if (TerrainGen.generateOre(currentWorld, randomGenerator, goldGen, chunk_X, chunk_Z, GOLD))
        this.genStandardOre1(2, this.goldGen, 0, 32);
        if (TerrainGen.generateOre(currentWorld, randomGenerator, redstoneGen, chunk_X, chunk_Z, REDSTONE))
        this.genStandardOre1(8, this.redstoneGen, 0, 16);
        if (TerrainGen.generateOre(currentWorld, randomGenerator, diamondGen, chunk_X, chunk_Z, DIAMOND))
        this.genStandardOre1(diamondAmount, this.diamondGen, 0, 16);
        if (TerrainGen.generateOre(currentWorld, randomGenerator, lapisGen, chunk_X, chunk_Z, LAPIS))
        this.genStandardOre2(1, this.lapisGen, 16, 16);
        
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
        
		if(emeralds == true) { genStandardOre1(3 + randomGenerator.nextInt(6), emeraldGen, 4, 32); }
		if(silverfish == true) { genStandardOre1(7, silverfishGen, 0, 64); }
    }
}
