package net.minecraft.src;

public class BWG4LayerSmall extends GenLayer
{
    public BWG4LayerSmall(long par1, GenLayer par3GenLayer)
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
				int var10 = var9;
				/*
				if (var9 > 399 && var9 < 310) //JUNGLE
				{
					if (var9 == BiomeGenBase.GOLD3jungleFlat.biomeID)
					{
						if (this.nextInt(5) == 0)
						{
							var10 = BiomeGenBase.GOLD3jungleFlatLake.biomeID;
						}	
						else if (this.nextInt(5) == 0)
						{
							var10 = BiomeGenBase.GOLD3jungleFlatOpen.biomeID;
						}
						else if (this.nextInt(12) == 0)
						{
							var10 = BiomeGenBase.GOLD3jungleVulcano1.biomeID;
						}	
					}
					else if (var9 == BiomeGenBase.GOLD3jungleHilly.biomeID)
					{
						if (this.nextInt(8) == 0)
						{
							var10 = BiomeGenBase.GOLD3junglePlateau.biomeID;
						}
						else if (this.nextInt(12) == 0)
						{
							var10 = BiomeGenBase.GOLD3jungleVulcano1.biomeID;
						}	
					}			
				}	
				if (var9 > 309 && var9 < 320) //TROPICAL
				{
					if (var9 == BiomeGenBase.GOLD3tropicSea.biomeID)
					{
						if (this.nextInt(8) == 0)
						{
							var10 = BiomeGenBase.GOLD3tropicSmall.biomeID;
						}
					}
				}	
				*/
				 
				if(var9 >= BiomeGenBase.GOLD1iceOcean.biomeID && var9 <= BiomeGenBase.GOLD1iceForest.biomeID)
				{
					if (var9 == BiomeGenBase.GOLD1icePlains.biomeID)
					{
						if (this.nextInt(8) == 0)
						{
							var10 = BiomeGenBase.GOLD1iceForest.biomeID;
						}
					}
				}	
				else if(var9 >= BiomeGenBase.GOLD1snowpineLake.biomeID && var9 <= BiomeGenBase.GOLD1snowpineHigh.biomeID)
				{
					if (var9 == BiomeGenBase.GOLD1snowpineForest.biomeID)
					{
						if (this.nextInt(5) == 0)
						{
							var10 = BiomeGenBase.GOLD1snowpineField.biomeID;
						}
					}
				}	
				else if(var9 >= BiomeGenBase.GOLD2forestNormal.biomeID && var9 <= BiomeGenBase.GOLD2forestPlains.biomeID)
				{
					if (var9 == BiomeGenBase.GOLD2forestPlains.biomeID)
					{
						if (this.nextInt(6) == 0)
						{
							var10 = BiomeGenBase.GOLD2forestNormal.biomeID;
						}
					}
				}					
				else if(var9 >= BiomeGenBase.GOLD3rainFlat.biomeID && var9 <= BiomeGenBase.GOLD3rainLake.biomeID)
				{
					if (var9 == BiomeGenBase.GOLD3rainFlat.biomeID)
					{
						if (this.nextInt(6) == 0)
						{
							var10 = BiomeGenBase.GOLD3rainField.biomeID;
						}
					}
				}		
				else if(var9 >= BiomeGenBase.GOLD4desertFlat.biomeID && var9 <= BiomeGenBase.GOLD4desertBorder.biomeID)
				{
					if (var9 == BiomeGenBase.GOLD4desertFlat.biomeID)
					{
						if (this.nextInt(8) == 0)
						{
							var10 = BiomeGenBase.GOLD4desertOasis.biomeID;
						}
					}
				}		
				else if(var9 >= BiomeGenBase.GOLD4canyonHIGH1.biomeID && var9 <= BiomeGenBase.GOLD4canyonLake.biomeID)
				{
					if (var9 == BiomeGenBase.GOLD4canyonFields.biomeID)
					{
						if (this.nextInt(4) == 0)
						{
							var10 = BiomeGenBase.GOLD4canyonLake.biomeID;
						}
					}
				}
				
				if (var10 == var9)
				{
					var6[var8 + var7 * par3] = var9;
				}
				else
				{
					int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var11 == var9 && var12 == var9 && var13 == var9 && var14 == var9)
					{
						var6[var8 + var7 * par3] = var10;
					}
					else
					{
						var6[var8 + var7 * par3] = var9;
					}
				}
            }
        }

        return var6;
    }
}
