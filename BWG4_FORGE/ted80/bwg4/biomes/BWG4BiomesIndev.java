package ted80.bwg4.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BWG4BiomesIndev extends BiomeGenBase
{
	private int indevID;

    public BWG4BiomesIndev(int par1)
    {
        super(par1);
		indevID = par1 - 101; //normal, hell, paradise, woods, snow
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
