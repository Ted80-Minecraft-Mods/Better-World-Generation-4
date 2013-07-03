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
	int biomeID;
	
	private BWG4Decorator bwg4decorator;

    public BWG4BiomesSurvival(int par1, int id)
    {
        super(par1);
        theBiomeDecorator = new BWG4Decorator(this);
        bwg4decorator = (BWG4Decorator) theBiomeDecorator;
        
		biomeID = id;
		
		if(biomeID == 107) //island_normal
		{
			bwg4decorator.usebwg4deco = true;
			bwg4decorator.tl1amount = -20; 
		}
		else if(biomeID == 108) //island_hell
		{
			bwg4decorator.usebwg4deco = true;
			bwg4decorator.tl1amount = -20; 
		}
		else if(biomeID == 109) //island_ice
		{
			bwg4decorator.usebwg4deco = true;
			bwg4decorator.tl1amount = -20; 
		}
		else if(biomeID == 110) //island_paradise
		{
			spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 2, 6));
			spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
			spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
			
			bwg4decorator.usebwg4deco = true;
			
			bwg4decorator.mayrandtrees = true; 
			bwg4decorator.tl1amount = 4; 
			bwg4decorator.usetl2 = true;
			bwg4decorator.tl2miny = 0; 
			bwg4decorator.tl2maxy = 75; 
			bwg4decorator.tl2amount = 1;
			
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
		}/*
		else if(biomeID == 111) //island_normal
		{
			bwg4decorator.usebwg4deco = true;
			bwg4decorator.tl1amount = -20; 
		}
		else if(biomeID == 112) //island_normal
		{
			bwg4decorator.usebwg4deco = true;
			bwg4decorator.tl1amount = -20; 
		}*/
		else if(biomeID == 113) //skyland_normal
		{
			spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 2, 6));
			spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
			spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
			
			bwg4decorator.usebwg4deco = true;
			bwg4decorator.mayrandtrees = true;
			bwg4decorator.tl1amount = 4; 
			bwg4decorator.redflowers = 3;
			bwg4decorator.yellowflowers = 3; 
			bwg4decorator.grass = 4; 
			bwg4decorator.melon = 8;
			bwg4decorator.pumpkin = 8; 
		}/*
		else if(biomeID == 114) //skyland_hell
		{
			bwg4decorator.usebwg4deco = true;
		}*/
		else if(biomeID == 115) //skyland_ice
		{
			bwg4decorator.usebwg4deco = true;
			bwg4decorator.mayrandtrees = true;
			bwg4decorator.tl1amount = 10;
			bwg4decorator.yellowflowers = 1;
			bwg4decorator.redflowers = 2;	
			bwg4decorator.grass = 4;	
			spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		}
		else if(biomeID == 116) //skyland_jungle
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
		}/*
		else if(biomeID == 117) //island_normal
		{
			bwg4decorator.usebwg4deco = true;
		}
		else if(biomeID == 118) //island_normal
		{
			bwg4decorator.usebwg4deco = true;
		}*/
		else if(biomeID == 119) //survival_nether
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
		else if(biomeID == 120) //skyblock_world
		{
		}
    }
	
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		if(biomeID == 107) //island_normal
		{
		}
		else if(biomeID == 108) //island_hell
		{
		}
		else if(biomeID == 109) //island_ice
		{
		}
		else if(biomeID == 110) //island_paradise
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
		else if(biomeID == 111) //island_normal
		{
		}
		else if(biomeID == 112) //island_normal
		{
		}*/
		else if(biomeID == 113) //skyland_normal
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
		else if(biomeID == 114) //skyland_hell
		{
		}*/
		else if(biomeID == 115) //skyland_ice
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
		else if(biomeID == 116) //skyland_jungle
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
		}/*
		else if(biomeID == 117) //island_normal
		{
		}
		else if(biomeID == 118) //island_normal
		{
		}*/
		return new BWG4oldGenTrees(2);
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
		if(biomeID == 116)
		{
			WorldGenVines var5 = new WorldGenVines();
			for (int var6 = 0; var6 < 50; ++var6)
			{
				int var7 = par3 + par2Random.nextInt(16) + 8;
				byte var8 = 64;
				int var9 = par4 + par2Random.nextInt(16) + 8;
				var5.generate(par1World, par2Random, var7, var8, var9);
			}
		}	
    }	

    public int getBiomeGrassColor()
    { 
		if(biomeID == 109 || biomeID == 115)
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
		if(biomeID == 109 || biomeID == 115)
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

	/*
    public int getSkyColorByTemp(float par1)
    {
		if(biomeID == 147 || biomeID == 153)
		{
			return 0x210800; 
		}
		else 
		{
			par1 /= 3.0F;

			if (par1 < -1.0F)
			{
				par1 = -1.0F;
			}

			if (par1 > 1.0F)
			{
				par1 = 1.0F;
			}

			return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
		}
	}
	*/
}
