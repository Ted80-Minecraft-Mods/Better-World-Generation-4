package net.minecraft.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BWG4DefaultGeneratorInfo
{
    private final List Biomelist = new ArrayList();
	
    public List theBiomesList()
    {
        return this.Biomelist;
    }	
	
    public static BWG4DefaultGeneratorInfo defaultBiomesList()
    {
		//DONT FORGET TO UPDATE THE 2 GEN STRINGS IN GuiCreateWorld.java :)
	
        BWG4DefaultGeneratorInfo var0 = new BWG4DefaultGeneratorInfo();

		//HOT
		var0.theBiomesList().add(new BWG4BiomeInfo("Shrubland", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Savanna", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Desert", true));
		
		//WARM
		var0.theBiomesList().add(new BWG4BiomeInfo("Swampland", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Jungle", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("RainForest", true));
	
		//NORMAL
		var0.theBiomesList().add(new BWG4BiomeInfo("Grassland", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Taiga", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Pines", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Forest Lakes", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Forest Hills", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Forest", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Plains", true));
		
		//COLD
		var0.theBiomesList().add(new BWG4BiomeInfo("Snow Hills", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Snow Plains", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Snow Taiga", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Snow Forest", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Snow Pines", true));
	
		//OCEAN
		var0.theBiomesList().add(new BWG4BiomeInfo("Beach Dunes", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Beach", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Mushroom Island", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Jungle Island", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Tropical Island", true));
		var0.theBiomesList().add(new BWG4BiomeInfo("Ocean", true));  
        return var0;
	}
	
    public String toString()
    {
		//CREATE STRING
        StringBuilder var1 = new StringBuilder();
		
		//VERSION
		var1.append("1&");
		
		//GET BIOME INFO
        for (int var2 = 0; var2 < this.Biomelist.size(); ++var2)
        {
            if (var2 > 0)
            {
                var1.append(";");
            }

            var1.append(((BWG4BiomeInfo)this.Biomelist.get(var2)).toString());
        }

		return var1.toString();
	}
	
	public static BWG4DefaultGeneratorInfo FromString(String par0Str)
    {
		BWG4DefaultGeneratorInfo var0 = new BWG4DefaultGeneratorInfo();
        if (par0Str != "")
        {
			String[] mainarray = par0Str.split("&");
			String[] biomesarray = mainarray[1].split(";");
			String settingName;
			boolean enabled;
			
			for(int biome = 0; biome < biomesarray.length; ++biome)
			{
				String[] biomeINFO = biomesarray[biome].split("=");
				settingName = biomeINFO[0];

				if(biomeINFO[1].equals("true"))
				{
					enabled = true;
				}
				else
				{
					enabled = false;
				}
				
				var0.theBiomesList().add(new BWG4BiomeInfo(settingName, enabled)); 
			}
			return var0;
		}
		return defaultBiomesList();
	}
}