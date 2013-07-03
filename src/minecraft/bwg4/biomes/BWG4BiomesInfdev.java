package bwg4.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BWG4BiomesInfdev extends BiomeGenBase
{
    public BWG4BiomesInfdev(int par1)
    {
        super(par1);
		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 2, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 2, 1, 1));
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
