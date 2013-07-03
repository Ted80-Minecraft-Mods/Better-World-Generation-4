package bwg4.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BWG4BiomesIndev extends BiomeGenBase
{
	private int indevID;

    public BWG4BiomesIndev(int par1, int id)
    {
        super(par1);
		indevID = id;
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
