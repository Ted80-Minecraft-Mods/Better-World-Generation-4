package ted80.bwg4.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public class BWG4LayerBorder extends BWG4Layer
{
    public BWG4LayerBorder(long par1, BWG4Layer par3GenLayer)
    {
        super(par1);
        this.parent = par3GenLayer;
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
				
				if (var9 >= BiomeGenBase.GOLD1iceOcean.biomeID && var9 <= BiomeGenBase.GOLD1iceForest.biomeID) //ICEPLAINS
				{	
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 < BiomeGenBase.GOLD1iceOcean.biomeID || var10 > BiomeGenBase.GOLD1iceForest.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD1iceBorder.biomeID;
					}
					else if (var11 < BiomeGenBase.GOLD1iceOcean.biomeID  || var11 > BiomeGenBase.GOLD1iceForest.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD1iceBorder.biomeID;
					}
					else if (var12 < BiomeGenBase.GOLD1iceOcean.biomeID  || var12 > BiomeGenBase.GOLD1iceForest.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD1iceBorder.biomeID;
					}
					else if (var13 < BiomeGenBase.GOLD1iceOcean.biomeID  || var13 > BiomeGenBase.GOLD1iceForest.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD1iceBorder.biomeID;
					}
					else 
					{
						if (var9 == BiomeGenBase.GOLD1icePlains.biomeID && var10 == BiomeGenBase.GOLD1iceOcean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.GOLD1iceBeach.biomeID;
						}
						else if (var9 == BiomeGenBase.GOLD1icePlains.biomeID && var11 == BiomeGenBase.GOLD1iceOcean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.GOLD1iceBeach.biomeID;
						}
						else if (var9 == BiomeGenBase.GOLD1icePlains.biomeID && var12 == BiomeGenBase.GOLD1iceOcean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.GOLD1iceBeach.biomeID;
						}
						else if (var9 == BiomeGenBase.GOLD1icePlains.biomeID && var13 == BiomeGenBase.GOLD1iceOcean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.GOLD1iceBeach.biomeID;
						}
						else
						{
							var6[var8 + var7 * par3] = var9;
						}	
					}		
				}		
				else if (var9 >= BiomeGenBase.GOLD1snowpineLake.biomeID && var9 <= BiomeGenBase.GOLD1snowpineHigh.biomeID) //PINEFOREST
				{	
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 < BiomeGenBase.GOLD1snowpineLake.biomeID || var10 > BiomeGenBase.GOLD1snowpineHigh.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD1snowpineBorder.biomeID;
					}
					else if (var11 < BiomeGenBase.GOLD1snowpineLake.biomeID  || var11 > BiomeGenBase.GOLD1snowpineHigh.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD1snowpineBorder.biomeID;
					}
					else if (var12 < BiomeGenBase.GOLD1snowpineLake.biomeID  || var12 > BiomeGenBase.GOLD1snowpineHigh.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD1snowpineBorder.biomeID;
					}
					else if (var13 < BiomeGenBase.GOLD1snowpineLake.biomeID  || var13 > BiomeGenBase.GOLD1snowpineHigh.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD1snowpineBorder.biomeID;
					}
					else
					{
						if (var9 == BiomeGenBase.GOLD1snowpineLake.biomeID && (var10 == BiomeGenBase.GOLD1snowpineHigh.biomeID || var11 == BiomeGenBase.GOLD1snowpineHigh.biomeID || var12 == BiomeGenBase.GOLD1snowpineHigh.biomeID || var13 == BiomeGenBase.GOLD1snowpineHigh.biomeID))
						{
							var6[var8 + var7 * par3] = BiomeGenBase.GOLD1snowpineBeach.biomeID;
						}
						else if (var9 == BiomeGenBase.GOLD1snowpineLake.biomeID && (var10 == BiomeGenBase.GOLD1snowpineForest.biomeID || var11 == BiomeGenBase.GOLD1snowpineForest.biomeID || var12 == BiomeGenBase.GOLD1snowpineForest.biomeID || var13 == BiomeGenBase.GOLD1snowpineForest.biomeID))
						{
							var6[var8 + var7 * par3] = BiomeGenBase.GOLD1snowpineBeach.biomeID;
						}
						else if (var9 == BiomeGenBase.GOLD1snowpineHigh.biomeID && (var10 == BiomeGenBase.GOLD1snowpineLake.biomeID || var11 == BiomeGenBase.GOLD1snowpineLake.biomeID || var12 == BiomeGenBase.GOLD1snowpineLake.biomeID || var13 == BiomeGenBase.GOLD1snowpineLake.biomeID))
						{
							var6[var8 + var7 * par3] = BiomeGenBase.GOLD1snowpineForest.biomeID;
						}
						else
						{
							var6[var8 + var7 * par3] = var9;
						}	
					}		
				}	
				else if (var9 >= BiomeGenBase.GOLD2pineNormal.biomeID && var9 <= BiomeGenBase.GOLD2pineValleys.biomeID) //PINEFOREST
				{	
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 < BiomeGenBase.GOLD2pineNormal.biomeID || var10 > BiomeGenBase.GOLD2pineValleys.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD2pineBorder.biomeID;
					}
					else if (var11 < BiomeGenBase.GOLD2pineNormal.biomeID  || var11 > BiomeGenBase.GOLD2pineValleys.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD2pineBorder.biomeID;
					}
					else if (var12 < BiomeGenBase.GOLD2pineNormal.biomeID  || var12 > BiomeGenBase.GOLD2pineValleys.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD2pineBorder.biomeID;
					}
					else if (var13 < BiomeGenBase.GOLD2pineNormal.biomeID  || var13 > BiomeGenBase.GOLD2pineValleys.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD2pineBorder.biomeID;
					}
					else
					{
						var6[var8 + var7 * par3] = var9;
					}		
				}
				else if (var9 >= BiomeGenBase.GOLD2forestNormal.biomeID && var9 <= BiomeGenBase.GOLD2forestPlains.biomeID) //FOREST
				{	
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 < BiomeGenBase.GOLD2forestNormal.biomeID || var10 > BiomeGenBase.GOLD2forestPlains.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD2forestBorder.biomeID;
					}
					else if (var11 < BiomeGenBase.GOLD2forestNormal.biomeID  || var11 > BiomeGenBase.GOLD2forestPlains.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD2forestBorder.biomeID;
					}
					else if (var12 < BiomeGenBase.GOLD2forestNormal.biomeID  || var12 > BiomeGenBase.GOLD2forestPlains.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD2forestBorder.biomeID;
					}
					else if (var13 < BiomeGenBase.GOLD2forestNormal.biomeID  || var13 > BiomeGenBase.GOLD2forestPlains.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD2forestBorder.biomeID;
					}
					else
					{
						var6[var8 + var7 * par3] = var9;
					}		
				}
				else if (var9 >= BiomeGenBase.GOLD3jungleFlat.biomeID && var9 <= BiomeGenBase.GOLD3jungleVulcano2.biomeID) //JUNGLE
				{	
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 < BiomeGenBase.GOLD3jungleFlat.biomeID || var10 > BiomeGenBase.GOLD3jungleBorder.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3jungleBorder.biomeID;
					}
					else if (var11 < BiomeGenBase.GOLD3jungleFlat.biomeID  || var11 > BiomeGenBase.GOLD3jungleBorder.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3jungleBorder.biomeID;
					}
					else if (var12 < BiomeGenBase.GOLD3jungleFlat.biomeID  || var12 > BiomeGenBase.GOLD3jungleBorder.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3jungleBorder.biomeID;
					}
					else if (var13 < BiomeGenBase.GOLD3jungleFlat.biomeID  || var13 > BiomeGenBase.GOLD3jungleBorder.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3jungleBorder.biomeID;
					}
					else
					{
						var6[var8 + var7 * par3] = var9;
					}		
				}
				else if (var9 >= BiomeGenBase.GOLD3tropicSea.biomeID && var9 <= BiomeGenBase.GOLD3tropicSmall.biomeID) //TROPICAL
				{
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var9 == BiomeGenBase.GOLD3tropicIsland.biomeID && var10 == BiomeGenBase.GOLD3tropicSea.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3tropicBeach.biomeID;
					}
					else if (var9 == BiomeGenBase.GOLD3tropicIsland.biomeID && var11 == BiomeGenBase.GOLD3tropicSea.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3tropicBeach.biomeID;
					}
					else if (var9 == BiomeGenBase.GOLD3tropicIsland.biomeID && var12 == BiomeGenBase.GOLD3tropicSea.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3tropicBeach.biomeID;
					}
					else if (var9 == BiomeGenBase.GOLD3tropicIsland.biomeID && var13 == BiomeGenBase.GOLD3tropicSea.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3tropicBeach.biomeID;
					}
					else if (var10 < BiomeGenBase.GOLD3tropicSea.biomeID || var10 > BiomeGenBase.GOLD3tropicBorder.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3tropicBorder.biomeID;
					}
					else if (var11 < BiomeGenBase.GOLD3tropicSea.biomeID || var11 > BiomeGenBase.GOLD3tropicBorder.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3tropicBorder.biomeID;
					}
					else if (var12 < BiomeGenBase.GOLD3tropicSea.biomeID || var12 > BiomeGenBase.GOLD3tropicBorder.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3tropicBorder.biomeID;
					}
					else if (var13 < BiomeGenBase.GOLD3tropicSea.biomeID || var13 > BiomeGenBase.GOLD3tropicBorder.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3tropicBorder.biomeID;
					}
					else
					{
						var6[var8 + var7 * par3] = var9;
					}
				} 
				else if (var9 >= BiomeGenBase.GOLD3rainFlat.biomeID && var9 <= BiomeGenBase.GOLD3rainLake.biomeID) //RAINFOREST
				{
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 < BiomeGenBase.GOLD3rainFlat.biomeID || var10 > BiomeGenBase.GOLD3rainLake.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3rainBorder.biomeID;
					}
					else if (var11 < BiomeGenBase.GOLD3rainFlat.biomeID || var11 > BiomeGenBase.GOLD3rainLake.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3rainBorder.biomeID;
					}
					else if (var12 < BiomeGenBase.GOLD3rainFlat.biomeID || var12 > BiomeGenBase.GOLD3rainLake.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3rainBorder.biomeID;
					}
					else if (var13 < BiomeGenBase.GOLD3rainFlat.biomeID || var13 > BiomeGenBase.GOLD3rainLake.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD3rainBorder.biomeID;
					}
					else
					{
						var6[var8 + var7 * par3] = var9;
					}
				}
				else if (var9 >= BiomeGenBase.GOLD4desertFlat.biomeID && var9 <= BiomeGenBase.GOLD4desertOasis.biomeID) //DESERT
				{
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 < BiomeGenBase.GOLD4desertFlat.biomeID || var10 > BiomeGenBase.GOLD4desertOasis.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4desertBorder.biomeID;
					}
					else if (var11 < BiomeGenBase.GOLD4desertFlat.biomeID || var11 > BiomeGenBase.GOLD4desertOasis.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4desertBorder.biomeID;
					}
					else if (var12 < BiomeGenBase.GOLD4desertFlat.biomeID || var12 > BiomeGenBase.GOLD4desertOasis.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4desertBorder.biomeID;
					}
					else if (var13 < BiomeGenBase.GOLD4desertFlat.biomeID || var13 > BiomeGenBase.GOLD4desertOasis.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4desertBorder.biomeID;
					}
					else
					{
						var6[var8 + var7 * par3] = var9;
					}
				}
				else if (var9 >= BiomeGenBase.GOLD4savannaFields.biomeID && var9 <= BiomeGenBase.GOLD4savannaHills.biomeID) //SAVANNA
				{
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 < BiomeGenBase.GOLD4savannaFields.biomeID || var10 > BiomeGenBase.GOLD4savannaHills.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4savannaBorder.biomeID;
					}
					else if (var11 < BiomeGenBase.GOLD4savannaFields.biomeID || var11 > BiomeGenBase.GOLD4savannaHills.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4savannaBorder.biomeID;
					}
					else if (var12 < BiomeGenBase.GOLD4savannaFields.biomeID || var12 > BiomeGenBase.GOLD4savannaHills.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4savannaBorder.biomeID;
					}
					else if (var13 < BiomeGenBase.GOLD4savannaFields.biomeID || var13 > BiomeGenBase.GOLD4savannaHills.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4savannaBorder.biomeID;
					}
					else
					{
						var6[var8 + var7 * par3] = var9;
					}
				}
				else if (var9 >= BiomeGenBase.GOLD4canyonHIGH1.biomeID && var9 <= BiomeGenBase.GOLD4canyonLake.biomeID) //CANYON
				{
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var9 == BiomeGenBase.GOLD4canyonHIGH1.biomeID && var10 == BiomeGenBase.GOLD4canyonHIGH2.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH1.biomeID && var11 == BiomeGenBase.GOLD4canyonHIGH2.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH1.biomeID && var12 == BiomeGenBase.GOLD4canyonHIGH2.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH1.biomeID && var13 == BiomeGenBase.GOLD4canyonHIGH2.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH2.biomeID && var10 == BiomeGenBase.GOLD4canyonHIGH3.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH2.biomeID && var11 == BiomeGenBase.GOLD4canyonHIGH3.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH2.biomeID && var12 == BiomeGenBase.GOLD4canyonHIGH3.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH2.biomeID && var13 == BiomeGenBase.GOLD4canyonHIGH3.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH3.biomeID && var10 == BiomeGenBase.GOLD4canyonHIGH1.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH3.biomeID && var11 == BiomeGenBase.GOLD4canyonHIGH1.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH3.biomeID && var12 == BiomeGenBase.GOLD4canyonHIGH1.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var9 == BiomeGenBase.GOLD4canyonHIGH3.biomeID && var13 == BiomeGenBase.GOLD4canyonHIGH1.biomeID) { var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID; }
					else if (var10 < BiomeGenBase.GOLD4canyonHIGH1.biomeID || var10 > BiomeGenBase.GOLD4canyonLake.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID;
					}
					else if (var11 < BiomeGenBase.GOLD4canyonHIGH1.biomeID || var11 > BiomeGenBase.GOLD4canyonLake.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID;
					}
					else if (var12 < BiomeGenBase.GOLD4canyonHIGH1.biomeID || var12 > BiomeGenBase.GOLD4canyonLake.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID;
					}
					else if (var13 < BiomeGenBase.GOLD4canyonHIGH1.biomeID || var13 > BiomeGenBase.GOLD4canyonLake.biomeID)
					{
						var6[var8 + var7 * par3] = BiomeGenBase.GOLD4canyonValley.biomeID;
					}
					else
					{
						var6[var8 + var7 * par3] = var9;
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
