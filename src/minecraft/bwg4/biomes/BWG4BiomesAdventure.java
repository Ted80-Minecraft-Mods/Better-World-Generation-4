package bwg4.biomes;

import java.awt.Color;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;

public class BWG4BiomesAdventure extends BiomeGenBase
{
    public BWG4BiomesAdventure(int par1, int id)
    {
        super(par1);
        
        if(id == 0) //DEADLY DESERT
        {
        	spawnableCreatureList.clear();
        }
    }
    
    public int getSkyColorByTemp(float par1)
    {
        return 0xC2B280;
    }
}
