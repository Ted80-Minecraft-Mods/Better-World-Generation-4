package ted80.bwg4.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BWG4BiomesAlpha extends BWG4BiomeGenBase
{
	private int biomeid;

    public BWG4BiomesAlpha(int par1)
    {
        super(par1);
		biomeid = par1;
		
		if(par1 == 90)
		{
			spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		}
		
		if(par1 == 91 || par1 == 92 || par1 == 93 || par1 == 96)
		{
			spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
		}
		
		if(par1 == 98)
		{
			spawnableCreatureList.clear();
			topBlock = (byte)Block.sand.blockID;
			fillerBlock = (byte)Block.sand.blockID;
		}	
    }
	
    public int getBiomeGrassColor()
    {
		if( biomeid == 96 || biomeid == 99 ) 
		{
			return ColorizerGrass.getGrassColor(0.6F, 0.6F);
		}
		else if( biomeid == 97 )
		{
			return ColorizerGrass.getGrassColor(1.0F, 0.4F);
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
		if( biomeid == 96 || biomeid == 99 )
		{
			return ColorizerFoliage.getFoliageColor(0.6F, 0.6F);
		}
		else if( biomeid == 97 )
		{
			return ColorizerFoliage.getFoliageColor(1.0F, 0.4F);
		}
		else
		{
			double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
			double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
			return ColorizerFoliage.getFoliageColor(d, d1);
		}
    }
}
