package bwg4.biomes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeCommon extends BiomeGenBase
{
	private boolean snow = false;

    public BiomeCommon(int par1, int id)
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
    
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    { 
		if(snow)
		{
			return ColorizerGrass.getGrassColor(0.6F, 0.6F);
		}
		else
		{
			double d = MathHelper.clamp_float(getFTemp(p_150558_1_, p_150558_2_, p_150558_3_), 0.0F, 1.0F); 
			double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
			return ColorizerGrass.getGrassColor(d, d1);
		}	
    } 
    
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {  
		if(snow)
		{
			return ColorizerFoliage.getFoliageColor(0.6F, 0.6F);
		}
		else
		{
			double d = MathHelper.clamp_float(getFTemp(p_150558_1_, p_150558_2_, p_150558_3_), 0.0F, 1.0F);
			double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
			return ColorizerFoliage.getFoliageColor(d, d1);
		}	
    } 

    @SideOnly(Side.CLIENT)
    public float getFTemp(int p_150564_1_, int p_150564_2_, int p_150564_3_)
    {
        if (p_150564_2_ > 64)
        {
            float var4 = (float)temperatureNoise.func_151601_a((double)p_150564_1_ * 1.0D / 8.0D, (double)p_150564_3_ * 1.0D / 8.0D) * 4.0F;
            return this.temperature - (var4 + (float)p_150564_2_ - 64.0F) * 0.05F / 30.0F;
        }
        else
        {
            return this.temperature;
        }
    }
}
