package bwg4.layer;

import bwg4.mod_bwg4;
import bwg4.biomes.BWG4Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public class BWG4LayerHills extends BWG4Layer
{
	private boolean IslandMushroom = false;
	private boolean IslandJungle = false;
	private boolean IslandTropic = false;

    public BWG4LayerHills(long par1, BWG4Layer par3GenLayer, String[] Settings)
    {
        super(par1);
        this.parent = par3GenLayer;
	
		for(int i = 0; i < Settings.length; i++)
		{
			if(Settings[i].equals("Mushroom Island=true")) { IslandMushroom = true; }
			else if(Settings[i].equals("Jungle Island=true")) { IslandJungle = true; }
			else if(Settings[i].equals("Tropical Island=true")) { IslandTropic = true; }
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

					if (var9 == BWG4Biomes.BDsavanna.biomeID)
                    {
                        var10 = BWG4Biomes.BDsavannaforest.biomeID;
                    }
					else if (var9 == BWG4Biomes.BDocean.biomeID)
                    {
						if (this.nextInt(30) == 0 && IslandMushroom == true)
						{
							var10 = BWG4Biomes.BDmushroomisland.biomeID;
						}
						else if (this.nextInt(8) == 0 && IslandJungle == true)
						{
							var10 = BWG4Biomes.BDjungleisland.biomeID;
						}
						else if (this.nextInt(5) == 0 && IslandTropic == true)
						{
							var10 = BWG4Biomes.BDtropicalisland.biomeID;
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
