package bwg4.layer;

import bwg4.mod_bwg4;
import bwg4.biomes.BWG4Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public class BWG4LayerRiverMix extends BWG4Layer
{
    private BWG4Layer biomePatternGeneratorChain;
    private BWG4Layer riverPatternGeneratorChain;

    public BWG4LayerRiverMix(long par1, BWG4Layer par3GenLayer, BWG4Layer par4GenLayer)
    {
        super(par1);
        this.biomePatternGeneratorChain = par3GenLayer;
        this.riverPatternGeneratorChain = par4GenLayer;
    }

    public void initWorldGenSeed(long par1)
    {
        this.biomePatternGeneratorChain.initWorldGenSeed(par1);
        this.riverPatternGeneratorChain.initWorldGenSeed(par1);
        super.initWorldGenSeed(par1);
    }

    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = this.biomePatternGeneratorChain.getInts(par1, par2, par3, par4);
        int[] var6 = this.riverPatternGeneratorChain.getInts(par1, par2, par3, par4);
        int[] var7 = IntCache.getIntCache(par3 * par4);

        for (int var8 = 0; var8 < par3 * par4; ++var8)
        {
            if (var5[var8] == BWG4Biomes.BDocean.biomeID || var5[var8] == BWG4Biomes.BDtropicalisland.biomeID || var5[var8] == BWG4Biomes.BDjungleisland.biomeID || var5[var8] == BWG4Biomes.BDmushroomisland.biomeID)
            {
                var7[var8] = var5[var8];
            }
            else if (var6[var8] >= 0)
            {
				if (var5[var8] == BWG4Biomes.BDsnowpines.biomeID || var5[var8] == BWG4Biomes.BDsnowforest.biomeID || var5[var8] == BWG4Biomes.BDsnowtaiga.biomeID || var5[var8] == BWG4Biomes.BDsnowplains.biomeID || var5[var8] == BWG4Biomes.BDsnowhills.biomeID)
				{
					var7[var8] = BWG4Biomes.BDiceriver.biomeID;
				}
				else if (var5[var8] == BWG4Biomes.BDjungle.biomeID || var5[var8] == BWG4Biomes.BDswampland.biomeID)
				{
					var7[var8] = BWG4Biomes.BDgreenriver.biomeID;
				}
				else if (var5[var8] == BWG4Biomes.BDdesert.biomeID || var5[var8] == BWG4Biomes.BDocean.biomeID || var5[var8] == BWG4Biomes.BDbeach.biomeID || var5[var8] == BWG4Biomes.BDbeachDunes.biomeID) 
				{
					var7[var8] = BWG4Biomes.BDsandriver.biomeID;
				}
				else
				{
					var7[var8] = BWG4Biomes.BDriver.biomeID;
				}
			}
            else
            {
                var7[var8] = var5[var8];
            }
        }
        return var7;
    }
}
