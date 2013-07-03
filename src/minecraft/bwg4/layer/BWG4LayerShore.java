package bwg4.layer;

import bwg4.mod_bwg4;
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
			if(Settings[i].equals("Beach Dunes=true")) { beachdunes = true; }
			else if(Settings[i].equals("Beach=true")) { beach = true; }
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
					if (var9 != BWG4Biomes.BDocean.biomeID && var9 != BWG4Biomes.BDbeach.biomeID && var9 != BWG4Biomes.BDsnowpines.biomeID && var9 != BWG4Biomes.BDsnowforest.biomeID && var9 != BWG4Biomes.BDsnowtaiga.biomeID && var9 != BWG4Biomes.BDsnowplains.biomeID && var9 != BWG4Biomes.BDsnowhills.biomeID)
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
					if( var9 == BWG4Biomes.BDdesert.biomeID || var9 == BWG4Biomes.BDsavanna.biomeID || var9 == BWG4Biomes.BDsavannaforest.biomeID || var9 == BWG4Biomes.BDshrubland.biomeID || var9 == BWG4Biomes.BDswampland.biomeID || var9 == BWG4Biomes.BDjungle.biomeID || var9 == BWG4Biomes.BDrainforest.biomeID || var9 == BWG4Biomes.BDshrublandHill.biomeID)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if (var10 == BWG4Biomes.BDsnowpines.biomeID || var10 == BWG4Biomes.BDsnowforest.biomeID || var10 == BWG4Biomes.BDsnowtaiga.biomeID || var10 == BWG4Biomes.BDsnowplains.biomeID || var10 == BWG4Biomes.BDsnowhills.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if (var11 == BWG4Biomes.BDsnowpines.biomeID || var11 == BWG4Biomes.BDsnowforest.biomeID || var11 == BWG4Biomes.BDsnowtaiga.biomeID || var11 == BWG4Biomes.BDsnowplains.biomeID || var11 == BWG4Biomes.BDsnowhills.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if (var12 == BWG4Biomes.BDsnowpines.biomeID || var12 == BWG4Biomes.BDsnowforest.biomeID || var12 == BWG4Biomes.BDsnowtaiga.biomeID || var12 == BWG4Biomes.BDsnowplains.biomeID || var12 == BWG4Biomes.BDsnowhills.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if (var13 == BWG4Biomes.BDsnowpines.biomeID || var13 == BWG4Biomes.BDsnowforest.biomeID || var13 == BWG4Biomes.BDsnowtaiga.biomeID || var13 == BWG4Biomes.BDsnowplains.biomeID || var13 == BWG4Biomes.BDsnowhills.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
					}
					if(var9 == BWG4Biomes.BDsnowpines.biomeID || var9 == BWG4Biomes.BDsnowforest.biomeID || var9 == BWG4Biomes.BDsnowtaiga.biomeID || var9 == BWG4Biomes.BDsnowplains.biomeID || var9 == BWG4Biomes.BDsnowhills.biomeID)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if( var10 == BWG4Biomes.BDdesert.biomeID || var10 == BWG4Biomes.BDsavanna.biomeID || var10 == BWG4Biomes.BDsavannaforest.biomeID || var10 == BWG4Biomes.BDshrubland.biomeID || var10 == BWG4Biomes.BDswampland.biomeID || var10 == BWG4Biomes.BDjungle.biomeID || var10 == BWG4Biomes.BDrainforest.biomeID || var10 == BWG4Biomes.BDshrublandHill.biomeID)		
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if( var11 == BWG4Biomes.BDdesert.biomeID || var11 == BWG4Biomes.BDsavanna.biomeID || var11 == BWG4Biomes.BDsavannaforest.biomeID || var11 == BWG4Biomes.BDshrubland.biomeID || var11 == BWG4Biomes.BDswampland.biomeID || var11 == BWG4Biomes.BDjungle.biomeID || var11 == BWG4Biomes.BDrainforest.biomeID || var11 == BWG4Biomes.BDshrublandHill.biomeID)			
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if( var12 == BWG4Biomes.BDdesert.biomeID || var12 == BWG4Biomes.BDsavanna.biomeID || var12 == BWG4Biomes.BDsavannaforest.biomeID || var12 == BWG4Biomes.BDshrubland.biomeID || var12 == BWG4Biomes.BDswampland.biomeID || var12 == BWG4Biomes.BDjungle.biomeID || var12 == BWG4Biomes.BDrainforest.biomeID || var12 == BWG4Biomes.BDshrublandHill.biomeID)			
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
						else if( var13 == BWG4Biomes.BDdesert.biomeID || var13 == BWG4Biomes.BDsavanna.biomeID || var13 == BWG4Biomes.BDsavannaforest.biomeID || var13 == BWG4Biomes.BDshrubland.biomeID || var13 == BWG4Biomes.BDswampland.biomeID || var13 == BWG4Biomes.BDjungle.biomeID || var13 == BWG4Biomes.BDrainforest.biomeID || var13 == BWG4Biomes.BDshrublandHill.biomeID)							
						{
							var6[var8 + var7 * par3] = BWG4Biomes.BDforest.biomeID;
						}
					}
					if (beach && var9 != BWG4Biomes.BDmushroomisland.biomeID && var9 != BWG4Biomes.BDjungleisland.biomeID && var9 != BWG4Biomes.BDtropicalisland.biomeID && var9 != BWG4Biomes.BDocean.biomeID && var9 != BWG4Biomes.BDsnowpines.biomeID && var9 != BWG4Biomes.BDsnowforest.biomeID && var9 != BWG4Biomes.BDsnowtaiga.biomeID && var9 != BWG4Biomes.BDsnowplains.biomeID && var9 != BWG4Biomes.BDsnowhills.biomeID)
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
