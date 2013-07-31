package bwg4.layer;

import bwg4.mod_bwg4;
import bwg4.api.DefaultBiomeList;
import bwg4.biomes.BWG4Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public class BWG4LayerShore extends BWG4Layer
{
	private int shoreID;
	private boolean beachdunes = false;
	private boolean beach = false;

    public BWG4LayerShore(long par1, BWG4Layer par3GenLayer, int shore, String[] Settings)
    {
        super(par1);
        this.parent = par3GenLayer;
		shoreID = shore;
		
		for(int i = 0; i < Settings.length; i++)
		{
			if(Settings[i].equals("Beach Dunes") || Settings[i].equals("Beach Dunes=true")) { beachdunes = true; }
			else if(Settings[i].equals("Beach") || Settings[i].equals("Beach=true")) { beach = true; }
		}
    }

    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] var6 = IntCache.getIntCache(par3 * par4);

        for (int var7 = 0; var7 < par4; ++var7)
        {
            for (int var8 = 0; var8 < par3; ++var8)
            {
                this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
                int var9 = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];
                int var10;
                int var11;
                int var12;
                int var13;
				
				var6[var8 + var7 * par3] = var9;
				
				if(shoreID == 2 && beachdunes)
				{
					if (DefaultBiomeList.biomeType[var9] != 0 && DefaultBiomeList.biomeType[var9] != 1)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if (var10 == BWG4Biomes.BDbeach.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDbeachDunes.biomeID;
						}
						else if (var11 == BWG4Biomes.BDbeach.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDbeachDunes.biomeID;
						}
						else if (var12 == BWG4Biomes.BDbeach.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDbeachDunes.biomeID;
						}
						else if (var13 == BWG4Biomes.BDbeach.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDbeachDunes.biomeID;
						}
					}	
				}
				else if(shoreID == 1)
				{
					if (DefaultBiomeList.biomeType[var9] == 4)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if (DefaultBiomeList.biomeType[var10] == 1)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if (DefaultBiomeList.biomeType[var11] == 1)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if (DefaultBiomeList.biomeType[var12] == 1)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if (DefaultBiomeList.biomeType[var13] == 1)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
					}
					if(DefaultBiomeList.biomeType[var9] == 1)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if(DefaultBiomeList.biomeType[var10] == 4)	
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if(DefaultBiomeList.biomeType[var11] == 4)		
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if(DefaultBiomeList.biomeType[var12] == 4)	
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if(DefaultBiomeList.biomeType[var13] == 4)							
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
					}
					if (beach && DefaultBiomeList.biomeType[var9] != 1 && DefaultBiomeList.biomeType[var9] != 5)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if (var10 == BWG4Biomes.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDbeach.biomeID;
						}
						else if (var11 == BWG4Biomes.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDbeach.biomeID;
						}
						else if (var12 == BWG4Biomes.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDbeach.biomeID;
						}
						else if (var13 == BWG4Biomes.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDbeach.biomeID;
						}
					}
				}
                else
                {
                    var6[var8 + var7 * par3] = var9;
                }
            }
        }
        return var6;
    }
}
