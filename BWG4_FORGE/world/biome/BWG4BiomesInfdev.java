package net.minecraft.world.biome;

import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.util.MathHelper;
import java.util.Random;

public class BWG4BiomesInfdev extends BiomeGenBase
{
    public BWG4BiomesInfdev(int par1)
    {
        super(par1);
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
		return 0x8FBEFF; 
	}
}
