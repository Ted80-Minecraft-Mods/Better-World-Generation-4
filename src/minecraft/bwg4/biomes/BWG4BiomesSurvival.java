package bwg4.biomes;

import java.awt.Color;
import java.util.Random;

import bwg4.deco.BWG4decoBigTree;
import bwg4.deco.BWG4decoGold1;
import bwg4.deco.BWG4decoSurvival;
import bwg4.deco.old.BWG4oldGenTrees;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4BiomesSurvival extends BiomeGenBase
{
	private boolean snow = false;

    public BWG4BiomesSurvival(int par1, int id)
    {
        super(par1);

        if(id == 0) //NORMAL 1
        {
        	spawnableCreatureList.clear();
        }
		if(id == 1) //NORMAL 2
		{
			spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 2, 6));
			spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
			spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		}
		if(id == 2) //SNOW
		{
			spawnableCreatureList.clear();
			snow = true;
		}
		if(id == 3) //NETHER
		{
			spawnableMonsterList.clear();
			spawnableCreatureList.clear();
			spawnableWaterCreatureList.clear();
			spawnableCaveCreatureList.clear();
			spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
			spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
			spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
			spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
			spawnableMonsterList.add(new SpawnListEntry(EntityBlaze.class, 25, 4, 4));
		}
    }

    public int getBiomeGrassColor()
    { 
		if(snow)
		{
			return ColorizerGrass.getGrassColor(0.6F, 0.6F);
		}
		else
		{
			double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F); 
			double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
			return ColorizerGrass.getGrassColor(d, d1);
		}	
    } 

    public int getBiomeFoliageColor()
    {  
		if(snow)
		{
			return ColorizerFoliage.getFoliageColor(0.6F, 0.6F);
		}
		else
		{
			double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
			double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
			return ColorizerFoliage.getFoliageColor(d, d1);
		}	
    } 
}

/*if(biomeID == 0) //island_normal
{
	spawnableCreatureList.clear();
	bwg4decorator.usebwg4deco = true;
	bwg4decorator.tl1amount = -20; 
	bwg4decorator.sugarcane = 0;
}
else if(biomeID == 1) //island_tropical
{
	spawnableCreatureList.clear();
	bwg4decorator.usebwg4deco = true;
	bwg4decorator.tl1amount = 5; 
	bwg4decorator.tl1miny = 67;
	bwg4decorator.usetl2 = true;
	bwg4decorator.tl2miny = 0; 
	bwg4decorator.tl2maxy = 75; 
	bwg4decorator.tl2amount = 3;
	bwg4decorator.tl2tree = new WorldGenShrub(3, 0);
	bwg4decorator.redflowers = 3;
	bwg4decorator.yellowflowers = 3; 
	bwg4decorator.grass = 4; 
}
else if(biomeID == 2) //island_iceberg
{
	spawnableCreatureList.clear();
	bwg4decorator.usebwg4deco = true;
	bwg4decorator.tl1amount = -20; 
	bwg4decorator.sugarcane = 0;
}
else if(biomeID == 3) //island_paradise
{
	spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 2, 6));
	spawnableCreatureList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
	spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
	
	bwg4decorator.usebwg4deco = true;
	
	bwg4decorator.mayrandtrees = true; 
	bwg4decorator.tl1amount = 5; 
	bwg4decorator.usetl2 = true;
	bwg4decorator.tl2miny = 0; 
	bwg4decorator.tl2maxy = 75; 
	bwg4decorator.tl2amount = 1;
	bwg4decorator.tl2tree = new BWG4decoSurvival(4);
	
	bwg4decorator.redflowers = 3;
	bwg4decorator.yellowflowers = 3; 
	bwg4decorator.grass = 4; 
	bwg4decorator.deadBush = 1;  
	bwg4decorator.sugarcane = 20; 
	bwg4decorator.cactus = 1;
	bwg4decorator.melon = 25;
	bwg4decorator.pumpkin = 25; 
	bwg4decorator.waterliquid = 50;
	bwg4decorator.lavaliquid = 20;
}
else if(biomeID == 4) //island_normal
{
	bwg4decorator.usebwg4deco = true;
	bwg4decorator.tl1amount = -20; 
	bwg4decorator.sugarcane = 0;
}
else if(biomeID == 5) //island_normal
{
	bwg4decorator.usebwg4deco = true;
	bwg4decorator.tl1amount = -20; 
	bwg4decorator.sugarcane = 0;
}
else if(biomeID == 6) //skyland_normal
{
	spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 2, 6));
	spawnableCreatureList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
	spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
	
	bwg4decorator.usebwg4deco = true;
	bwg4decorator.mayrandtrees = true;
	bwg4decorator.tl1amount = 4; 
	bwg4decorator.redflowers = 3;
	bwg4decorator.yellowflowers = 3; 
	bwg4decorator.grass = 4; 
	bwg4decorator.melon = 8;
	bwg4decorator.pumpkin = 8; 
}
else if(biomeID == 7) //skyland_hell
{
	bwg4decorator.usebwg4deco = true;
}
else if(biomeID == 8) //skyland_ice
{
	bwg4decorator.usebwg4deco = true;
	bwg4decorator.mayrandtrees = true;
	bwg4decorator.tl1amount = 10;
	bwg4decorator.yellowflowers = 1;
	bwg4decorator.redflowers = 2;	
	bwg4decorator.grass = 4;	
	spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
}
else if(biomeID == 9) //skyland_jungle
{
	bwg4decorator.usebwg4deco = true;
	bwg4decorator.mayrandtrees = false;
	bwg4decorator.redflowers = 4;
	bwg4decorator.yellowflowers = 2;
	bwg4decorator.waterlily = 5;
	bwg4decorator.sugarcane = 20;
	bwg4decorator.grass = 8;
	bwg4decorator.melon = 8;				
	bwg4decorator.tl1amount = 14;
	waterColorMultiplier = 0x99DB37;
	spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 2, 6));
	spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
	spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
}
else if(biomeID == 10) //island_normal
{
	bwg4decorator.usebwg4deco = true;
}
else if(biomeID == 11) //island_normal
{
	bwg4decorator.usebwg4deco = true;
}
else if(biomeID == 12) //survival_nether
{
	this.spawnableMonsterList.clear();
	this.spawnableCreatureList.clear();
	this.spawnableWaterCreatureList.clear();
	this.spawnableCaveCreatureList.clear();
	this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
	this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
	this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
	this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
	spawnableMonsterList.add(new SpawnListEntry(EntityBlaze.class, 25, 4, 4));
}
else if(biomeID == 13) //skyblock_world
{
}

bwg4decorator.treesPerChunk = bwg4decorator.tl1amount; 
*/

/*
public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
{
	if(biomeID == 0) //island_normal
	{
	}
	else if(biomeID == 1) //island_tropical
	{
		if(par1Random.nextInt(5) == 0)
		{
			return new BWG4decoBigTree(par1Random.nextInt(5) + 9, 0); 
		}
		if(par1Random.nextInt(2) == 0)
		{
			return new BWG4oldGenTrees(2); 
		} 
		else
		{
			return new BWG4decoSurvival(4);
		}
	}
	else if(biomeID == 2) //island_iceberg
	{
	}
	else if(biomeID == 3) //island_paradise
	{
		if(par1Random.nextInt(5) == 0)
		{
			return worldGeneratorForest;
		}
		if(par1Random.nextInt(4) == 0)
		{
			return new BWG4decoBigTree(par1Random.nextInt(5) + 9, 0); 
		} 
		else
		{
			return new BWG4oldGenTrees(2);
		}
	}/*
	else if(biomeID == 4) //island_normal
	{
	}
	else if(biomeID == 5) //island_normal
	{
	}
	else if(biomeID == 6) //skyland_normal
	{
		if(par1Random.nextInt(5) == 0)
		{
			return worldGeneratorForest;
		}
		if(par1Random.nextInt(4) == 0)
		{
			return new BWG4decoBigTree(par1Random.nextInt(5) + 9, 0); 
		} 
		else
		{
			return new BWG4oldGenTrees(2);
		}
	}
	else if(biomeID == 7) //skyland_hell
	{
	}
	else if(biomeID == 8) //skyland_ice
	{
		if (par1Random.nextInt(5) == 0) 
		{ 
			return new BWG4decoGold1(3, 6, 16, 3); 
		}	
		else if (par1Random.nextInt(2) == 0) 
		{ 
			return new BWG4decoGold1(1, 6, 14, 0);
		}
		else if (par1Random.nextInt(2) == 0) 
		{ 
			return new BWG4decoGold1(2, 6, 12, 3); 
		}
		else 
		{ 
			return worldGeneratorTrees; 
		}
	}
	else if(biomeID == 9) //skyland_jungle
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
			return new WorldGenHugeTrees(false, 15 + par1Random.nextInt(10), 3, 3); 
		} 
		else 
		{ 
			return new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true); 
		}
	}
	else if(biomeID == 10) //island_normal
	{
	}
	else if(biomeID == 11) //island_normal
	{
	}
	return new BWG4oldGenTrees(2);
}
*/

//public void decorate(World par1World, Random par2Random, int par3, int par4)
//{
    //super.decorate(par1World, par2Random, par3, par4);
	//if(biomeID == 9)
	//{
	//	WorldGenVines var5 = new WorldGenVines();
	//	for (int var6 = 0; var6 < 50; ++var6)
	//	{
	//		int var7 = par3 + par2Random.nextInt(16) + 8;
	//		byte var8 = 64;
	//		int var9 = par4 + par2Random.nextInt(16) + 8;
	//		var5.generate(par1World, par2Random, var7, var8, var9);
	//	}
	//}	
//}	
