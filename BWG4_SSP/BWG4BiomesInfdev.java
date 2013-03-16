package net.minecraft.src;

import java.awt.Color;
import java.util.Random;

public class BWG4BiomesInfdev extends BiomeGenBase
{
    public BWG4BiomesInfdev(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 1, 2, 3));
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
		return 0x8FBEFF; 
	}
}
