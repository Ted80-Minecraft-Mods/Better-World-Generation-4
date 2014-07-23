package bwg4.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bwg4.api.BiomeManager;
import net.minecraft.world.biome.BiomeGenBase;

public class GuiBiomeSettingsInfo
{
    private final List Biomelist = new ArrayList();
    public int biomesEnabled;
	
    public List theBiomesList()
    {
        return this.Biomelist;
    }	
	
    public static GuiBiomeSettingsInfo defaultBiomesList()
    {
        return FromString(BiomeManager.getDefaultString());
	}
	
    public String toString()
    {
		//CREATE STRING
    	String s = "";
		
		//GET BIOME INFO
		boolean first = true;
        for (int var2 = 0; var2 < this.Biomelist.size(); ++var2)
        {
        	if(((BiomeInfo)this.Biomelist.get(var2)).getEnabled())
        	{
        		if (!first)
	            {
	            	s += ";";
	            }
	            else
	            {
	                first = false;
	            }
	
	            s += ((BiomeInfo)this.Biomelist.get(var2)).getNAME();
        	}
        }

		return s;
	}
	
	public static GuiBiomeSettingsInfo FromString(String settings)
    {
		GuiBiomeSettingsInfo info = new GuiBiomeSettingsInfo();
		info.biomesEnabled = 0;
        if (settings != null && settings.length() > 0)
        {
        	String[] enabledbiomes = settings.split(";");
        	
        	String[] biomes = BiomeManager.getBiomeNames();
        	int l = biomes.length;
        	for(int i = 0; i < l; i++)
        	{
        		for(int j = enabledbiomes.length - 1; j > -1; j--)
        		{
        			if(biomes[i].equals(enabledbiomes[j]))
        			{
                		info.theBiomesList().add(new BiomeInfo(biomes[i], true)); 
                		info.biomesEnabled++;
                		break;
        			}
        			
        			if(j == 0)
        			{
                		info.theBiomesList().add(new BiomeInfo(biomes[i], false)); 
        			}
        		}
        	}
        }
        else
        {
        	String[] biomes = BiomeManager.getBiomeNames();
        	int l = biomes.length;
        	for(int i = 0; i < l; i++)
        	{
        		info.theBiomesList().add(new BiomeInfo(biomes[i], true)); 
        		info.biomesEnabled++;
        	}
        }
        return info;
	}
}