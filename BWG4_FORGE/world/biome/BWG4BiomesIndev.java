package net.minecraft.world.biome;

import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.util.MathHelper;
import java.util.Random;

public class BWG4BiomesIndev extends BiomeGenBase
{
	private int indevID;

    public BWG4BiomesIndev(int par1)
    {
        super(par1);
		indevID = par1 - 101; //normal, hell, paradise, woods, snow
        this.spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 1, 2, 3));
    }
	
    public int getBiomeGrassColor()
    { 
        return 0xABFF67;
    } 

    public int getBiomeFoliageColor()
    {  
        return 0x4FFF2B;
    } 
	
    public int getSkyColorByTemp(float par1)
    {
		if(indevID == 2)
		{
			return 0x210800; 
		}
		else if(indevID == 3)
		{
			return 0xC6DEFF; 
		}
		else if(indevID == 4)
		{
			return 0x757d87; 
		}
		else 
		{
			return 0x99CCFF; 
		}
	}
}
