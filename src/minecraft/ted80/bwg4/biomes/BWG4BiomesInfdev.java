package ted80.bwg4.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BWG4BiomesInfdev extends BWG4BiomeGenBase
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
