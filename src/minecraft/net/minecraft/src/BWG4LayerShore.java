package net.minecraft.src;

public class BWG4LayerShore extends GenLayer
{
	private int shoreID;
	private boolean beachdunes = false;
	private boolean beach = false;

    public BWG4LayerShore(long par1, GenLayer par3GenLayer, int shore, String[] Settings, boolean remote)
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
				
				if(shoreID == 1 && beachdunes)
				{
					if (var9 != BiomeGenBase.BDocean.biomeID)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if (var10 == BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.BDbeachDunes.biomeID;
						}
						else if (var11 == BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.BDbeachDunes.biomeID;
						}
						else if (var12 == BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.BDbeachDunes.biomeID;
						}
						else if (var13 == BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.BDbeachDunes.biomeID;
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
				else if(shoreID == 2 && beach)
				{
					if (var9 == BiomeGenBase.BDocean.biomeID)
					{
						var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if (var10 != BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.BDbeach.biomeID;
						}
						else if (var11 != BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.BDbeach.biomeID;
						}
						else if (var12 != BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.BDbeach.biomeID;
						}
						else if (var13 != BiomeGenBase.BDocean.biomeID)
						{
							var6[var8 + var7 * par3] = BiomeGenBase.BDbeach.biomeID;
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
                else
                {
                    var6[var8 + var7 * par3] = var9;
                }
            }
        }
        return var6;
    }
}
