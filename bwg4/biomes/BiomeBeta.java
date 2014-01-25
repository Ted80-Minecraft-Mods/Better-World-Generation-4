package bwg4.biomes;

import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeBeta extends BiomeGenBase
{
	private int id;

    public BiomeBeta(int par1, int i)
    {
        super(par1);
		id = i;
		
		if(id == 0 || id == 0)
		{
			spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		}
	
		if(id == 1 || id == 2 || id == 3 || id == 6)
		{
			spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
		}
		
		if(id == 1 || id == 2 || id == 3 || id == 6)
		{
			spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		}
		
		if(id == 7)
		{
			spawnableCreatureList.clear();
			topBlock = Blocks.sand;
			fillerBlock = Blocks.sand;
		}	
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return new WorldGenForest(false, true);
    }

    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		if( id == 6 || id == 9 )
		{
			return ColorizerGrass.getGrassColor(0.6F, 0.6F);
		}
		else if( id == 7 )
		{
			return ColorizerFoliage.getFoliageColor(0.8F, 0.2F);
		}
		else
		{
			double d = MathHelper.clamp_float(getFTemp(p_150558_1_, p_150558_2_, p_150558_3_), 0.0F, 1.0F); 
			double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
			return ColorizerGrass.getGrassColor(d, d1);
		}
    }

    public int getBiomeFoliageColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		if( id == 6 || id == 9 )
		{
			return ColorizerFoliage.getFoliageColor(0.6F, 0.6F);
		}
		else if( id == 7 )
		{
			return ColorizerFoliage.getFoliageColor(0.8F, 0.2F);
		}
		else
		{
			this.getBiomeFoliageColor(p_150558_1_, p_150558_2_, p_150558_3_);
			
			double d = MathHelper.clamp_float(getFTemp(p_150558_1_, p_150558_2_, p_150558_3_), 0.0F, 1.0F);
			double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
			return ColorizerFoliage.getFoliageColor(d, d1);
		}
    }    
    
    public float getFTemp(int p_150564_1_, int p_150564_2_, int p_150564_3_)
    {
        if (p_150564_2_ > 64)
        {
            float var4 = (float)field_150605_ac.func_151601_a((double)p_150564_1_ * 1.0D / 8.0D, (double)p_150564_3_ * 1.0D / 8.0D) * 4.0F;
            return this.temperature - (var4 + (float)p_150564_2_ - 64.0F) * 0.05F / 30.0F;
        }
        else
        {
            return this.temperature;
        }
    }
}
