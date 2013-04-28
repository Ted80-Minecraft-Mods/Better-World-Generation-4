package ted80.bwg4.layer;

import ted80.bwg4.biomes.BWG4BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public class BWG4LayerShore extends BWG4Layer
{
	private int shoreID;
	private boolean beachdunes = false;
	private boolean beach = false;

    public BWG4LayerShore(long par1, BWG4Layer par3GenLayer, int shore, String[] Settings, boolean remote)
    {
        super(par1);
        this.parent = par3GenLayer;
		shoreID = shore;
		
		if(remote)
		{
			for(int i = 0; i < Settings.length; i++)
			{
				if(Settings[i].equals("Beach Dunes=true")) { beachdunes = true; }
				else if(Settings[i].equals("Beach=true")) { beach = true; }
			}
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
					if (var9 != BWG4BiomeGenBase.BDocean.biomeID && var9 != BWG4BiomeGenBase.BDbeach.biomeID && var9 != BWG4BiomeGenBase.BDsnowpines.biomeID && var9 != BWG4BiomeGenBase.BDsnowforest.biomeID && var9 != BWG4BiomeGenBase.BDsnowtaiga.biomeID && var9 != BWG4BiomeGenBase.BDsnowplains.biomeID && var9 != BWG4BiomeGenBase.BDsnowhills.biomeID)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if (var10 == BWG4BiomeGenBase.BDbeach.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDbeachDunes.biomeID;
						}
						else if (var11 == BWG4BiomeGenBase.BDbeach.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDbeachDunes.biomeID;
						}
						else if (var12 == BWG4BiomeGenBase.BDbeach.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDbeachDunes.biomeID;
						}
						else if (var13 == BWG4BiomeGenBase.BDbeach.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDbeachDunes.biomeID;
						}
					}	
				}
				else if(shoreID == 1)
				{
					if( var9 == BWG4BiomeGenBase.BDdesert.biomeID || var9 == BWG4BiomeGenBase.BDsavanna.biomeID || var9 == BWG4BiomeGenBase.BDsavannaforest.biomeID || var9 == BWG4BiomeGenBase.BDshrubland.biomeID || var9 == BWG4BiomeGenBase.BDswampland.biomeID || var9 == BWG4BiomeGenBase.BDjungle.biomeID || var9 == BWG4BiomeGenBase.BDrainforest.biomeID || var9 == BWG4BiomeGenBase.BDshrublandHill.biomeID)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if (var10 == BWG4BiomeGenBase.BDsnowpines.biomeID || var10 == BWG4BiomeGenBase.BDsnowforest.biomeID || var10 == BWG4BiomeGenBase.BDsnowtaiga.biomeID || var10 == BWG4BiomeGenBase.BDsnowplains.biomeID || var10 == BWG4BiomeGenBase.BDsnowhills.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDforest.biomeID;
						}
						else if (var11 == BWG4BiomeGenBase.BDsnowpines.biomeID || var11 == BWG4BiomeGenBase.BDsnowforest.biomeID || var11 == BWG4BiomeGenBase.BDsnowtaiga.biomeID || var11 == BWG4BiomeGenBase.BDsnowplains.biomeID || var11 == BWG4BiomeGenBase.BDsnowhills.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDforest.biomeID;
						}
						else if (var12 == BWG4BiomeGenBase.BDsnowpines.biomeID || var12 == BWG4BiomeGenBase.BDsnowforest.biomeID || var12 == BWG4BiomeGenBase.BDsnowtaiga.biomeID || var12 == BWG4BiomeGenBase.BDsnowplains.biomeID || var12 == BWG4BiomeGenBase.BDsnowhills.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDforest.biomeID;
						}
						else if (var13 == BWG4BiomeGenBase.BDsnowpines.biomeID || var13 == BWG4BiomeGenBase.BDsnowforest.biomeID || var13 == BWG4BiomeGenBase.BDsnowtaiga.biomeID || var13 == BWG4BiomeGenBase.BDsnowplains.biomeID || var13 == BWG4BiomeGenBase.BDsnowhills.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDforest.biomeID;
						}
					}
					if(var9 == BWG4BiomeGenBase.BDsnowpines.biomeID || var9 == BWG4BiomeGenBase.BDsnowforest.biomeID || var9 == BWG4BiomeGenBase.BDsnowtaiga.biomeID || var9 == BWG4BiomeGenBase.BDsnowplains.biomeID || var9 == BWG4BiomeGenBase.BDsnowhills.biomeID)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if( var10 == BWG4BiomeGenBase.BDdesert.biomeID || var10 == BWG4BiomeGenBase.BDsavanna.biomeID || var10 == BWG4BiomeGenBase.BDsavannaforest.biomeID || var10 == BWG4BiomeGenBase.BDshrubland.biomeID || var10 == BWG4BiomeGenBase.BDswampland.biomeID || var10 == BWG4BiomeGenBase.BDjungle.biomeID || var10 == BWG4BiomeGenBase.BDrainforest.biomeID || var10 == BWG4BiomeGenBase.BDshrublandHill.biomeID)		
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDforest.biomeID;
						}
						else if( var11 == BWG4BiomeGenBase.BDdesert.biomeID || var11 == BWG4BiomeGenBase.BDsavanna.biomeID || var11 == BWG4BiomeGenBase.BDsavannaforest.biomeID || var11 == BWG4BiomeGenBase.BDshrubland.biomeID || var11 == BWG4BiomeGenBase.BDswampland.biomeID || var11 == BWG4BiomeGenBase.BDjungle.biomeID || var11 == BWG4BiomeGenBase.BDrainforest.biomeID || var11 == BWG4BiomeGenBase.BDshrublandHill.biomeID)			
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDforest.biomeID;
						}
						else if( var12 == BWG4BiomeGenBase.BDdesert.biomeID || var12 == BWG4BiomeGenBase.BDsavanna.biomeID || var12 == BWG4BiomeGenBase.BDsavannaforest.biomeID || var12 == BWG4BiomeGenBase.BDshrubland.biomeID || var12 == BWG4BiomeGenBase.BDswampland.biomeID || var12 == BWG4BiomeGenBase.BDjungle.biomeID || var12 == BWG4BiomeGenBase.BDrainforest.biomeID || var12 == BWG4BiomeGenBase.BDshrublandHill.biomeID)			
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDforest.biomeID;
						}
						else if( var13 == BWG4BiomeGenBase.BDdesert.biomeID || var13 == BWG4BiomeGenBase.BDsavanna.biomeID || var13 == BWG4BiomeGenBase.BDsavannaforest.biomeID || var13 == BWG4BiomeGenBase.BDshrubland.biomeID || var13 == BWG4BiomeGenBase.BDswampland.biomeID || var13 == BWG4BiomeGenBase.BDjungle.biomeID || var13 == BWG4BiomeGenBase.BDrainforest.biomeID || var13 == BWG4BiomeGenBase.BDshrublandHill.biomeID)							
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDforest.biomeID;
						}
					}
					if (beach && var9 != BWG4BiomeGenBase.BDmushroomisland.biomeID && var9 != BWG4BiomeGenBase.BDjungleisland.biomeID && var9 != BWG4BiomeGenBase.BDtropicalisland.biomeID && var9 != BWG4BiomeGenBase.BDocean.biomeID && var9 != BWG4BiomeGenBase.BDsnowpines.biomeID && var9 != BWG4BiomeGenBase.BDsnowforest.biomeID && var9 != BWG4BiomeGenBase.BDsnowtaiga.biomeID && var9 != BWG4BiomeGenBase.BDsnowplains.biomeID && var9 != BWG4BiomeGenBase.BDsnowhills.biomeID)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if (var10 == BWG4BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDbeach.biomeID;
						}
						else if (var11 == BWG4BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDbeach.biomeID;
						}
						else if (var12 == BWG4BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDbeach.biomeID;
						}
						else if (var13 == BWG4BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BWG4BiomeGenBase.BDbeach.biomeID;
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
