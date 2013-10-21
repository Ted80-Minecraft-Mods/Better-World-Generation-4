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