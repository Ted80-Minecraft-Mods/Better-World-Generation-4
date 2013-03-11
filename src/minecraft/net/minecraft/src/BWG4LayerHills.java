package net.minecraft.src;

public class BWG4LayerHills extends GenLayer
{
	private boolean IslandMushroom = false;
	private boolean IslandJungle = false;
	private boolean IslandTropic = false;

    public BWG4LayerHills(long par1, GenLayer par3GenLayer, String[] Settings, boolean remote)
    {
        super(par1);
        this.parent = par3GenLayer;
	
		if(remote)
		{
			for(int i = 0; i < Settings.length; i++)
			{
				if(Settings[i].equals("Mushroom Island=true")) { IslandMushroom = true; }
				else if(Settings[i].equals("Jungle Island=true")) { IslandJungle = true; }
				else if(Settings[i].equals("Tropical Island=true")) { IslandTropic = true; }
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

                if (this.nextInt(5) == 0)
                {
                    int var10 = var9;

					if (var9 == BiomeGenBase.BDsavanna.biomeID)
                    {
                        var10 = BiomeGenBase.BDsavannaforest.biomeID;
                    }
					else if (var9 == BiomeGenBase.BDshrubland.biomeID)
                    {
                        var10 = BiomeGenBase.BDshrublandHill.biomeID;
                    }
					else if (var9 == BiomeGenBase.BDocean.biomeID)
                    {
						if (this.nextInt(40) == 0 && IslandMushroom == true)
						{
							var10 = BiomeGenBase.BDmushroomisland.biomeID;
						}
						else if (this.nextInt(12) == 0 && IslandJungle == true)
						{
							var10 = BiomeGenBase.BDjungleisland.biomeID;
						}
						else if (this.nextInt(7) == 0 && IslandTropic == true)
						{
							var10 = BiomeGenBase.BDtropicalisland.biomeID;
						}
						else
						{
							var10 = var9;
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
                else
                {
                    var6[var8 + var7 * par3] = var9;
                }
            }
        }

        return var6;
    }
}
