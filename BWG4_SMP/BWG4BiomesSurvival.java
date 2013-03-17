package net.minecraft.src;

import java.awt.Color;
import java.util.Random;

public class BWG4BiomesSurvival extends BiomeGenBase
{
	int biomeID;

    public BWG4BiomesSurvival(int par1)
    {
        super(par1);
		biomeID = par1;
		
		if(biomeID == 107) //island_normal
		{
			theBiomeDecorator.usebwg4deco = true;
			theBiomeDecorator.tl1amount = -20; 
		}
		else if(biomeID == 108) //island_hell
		{
			theBiomeDecorator.usebwg4deco = true;
			theBiomeDecorator.tl1amount = -20; 
		}
		else if(biomeID == 109) //island_ice
		{
			theBiomeDecorator.usebwg4deco = true;
			theBiomeDecorator.tl1amount = -20; 
		}
		else if(biomeID == 110) //island_paradise
		{
			theBiomeDecorator.usebwg4deco = true;
			
			theBiomeDecorator.mayrandtrees = true; 
			theBiomeDecorator.tl1amount = 4; 
			theBiomeDecorator.usetl2 = true;
			theBiomeDecorator.tl2miny = 0; 
			theBiomeDecorator.tl2maxy = 75; 
			theBiomeDecorator.tl2amount = 1;
			
			theBiomeDecorator.redflowers = 3;
			theBiomeDecorator.yellowflowers = 3; 
			theBiomeDecorator.grass = 4; 
			theBiomeDecorator.deadBush = 1;  
			theBiomeDecorator.sugarcane = 20; 
			theBiomeDecorator.cactus = 1;
			theBiomeDecorator.melon = 25;
			theBiomeDecorator.pumpkin = 25; 
			theBiomeDecorator.waterliquid = 50;
			theBiomeDecorator.lavaliquid = 20;
		}/*
		else if(biomeID == 111) //island_normal
		{
			theBiomeDecorator.usebwg4deco = true;
			theBiomeDecorator.tl1amount = -20; 
		}
		else if(biomeID == 112) //island_normal
		{
			theBiomeDecorator.usebwg4deco = true;
			theBiomeDecorator.tl1amount = -20; 
		}*/
		else if(biomeID == 113) //skyland_normal
		{
			theBiomeDecorator.usebwg4deco = true;
			theBiomeDecorator.mayrandtrees = true;
			theBiomeDecorator.tl1amount = 4; 
			theBiomeDecorator.redflowers = 3;
			theBiomeDecorator.yellowflowers = 3; 
			theBiomeDecorator.grass = 4; 
			theBiomeDecorator.melon = 8;
			theBiomeDecorator.pumpkin = 8; 
		}/*
		else if(biomeID == 114) //skyland_hell
		{
			theBiomeDecorator.usebwg4deco = true;
		}*/
		else if(biomeID == 115) //skyland_ice
		{
			theBiomeDecorator.usebwg4deco = true;
			theBiomeDecorator.mayrandtrees = true;
			theBiomeDecorator.tl1amount = 10;
			theBiomeDecorator.yellowflowers = 1;
			theBiomeDecorator.redflowers = 2;	
			theBiomeDecorator.grass = 4;	
			spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		}
		else if(biomeID == 116) //skyland_jungle
		{
			theBiomeDecorator.usebwg4deco = true;
			theBiomeDecorator.mayrandtrees = false;
			theBiomeDecorator.redflowers = 4;
			theBiomeDecorator.yellowflowers = 2;
			theBiomeDecorator.waterlily = 5;
			theBiomeDecorator.sugarcane = 20;
			theBiomeDecorator.grass = 8;
			theBiomeDecorator.melon = 8;				
			theBiomeDecorator.tl1amount = 14;
			waterColorMultiplier = 0x99DB37;
			spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
			spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));	
		}/*
		else if(biomeID == 117) //island_normal
		{
			theBiomeDecorator.usebwg4deco = true;
		}
		else if(biomeID == 118) //island_normal
		{
			theBiomeDecorator.usebwg4deco = true;
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
	
    public WorldGenerator getRandomWorldGenForTrees2(Random par1Random)
    {
		if(biomeID == 110) //island_paradise
		{
			return new BWG4decoSurvival(4);
		}
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
}
