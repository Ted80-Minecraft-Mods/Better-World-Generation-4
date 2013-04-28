package ted80.bwg4.layer;

import ted80.bwg4.biomes.BWG4BiomeGenBase;
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
            if (var5[var8] == BWG4BiomeGenBase.BDocean.biomeID || var5[var8] == BWG4BiomeGenBase.BDtropicalisland.biomeID || var5[var8] == BWG4BiomeGenBase.BDjungleisland.biomeID || var5[var8] == BWG4BiomeGenBase.BDmushroomisland.biomeID)
            {
                var7[var8] = var5[var8];
            }
            else if (var6[var8] >= 0)
            {
				if (var5[var8] == BWG4BiomeGenBase.BDsnowpines.biomeID || var5[var8] == BWG4BiomeGenBase.BDsnowforest.biomeID || var5[var8] == BWG4BiomeGenBase.BDsnowtaiga.biomeID || var5[var8] == BWG4BiomeGenBase.BDsnowplains.biomeID || var5[var8] == BWG4BiomeGenBase.BDsnowhills.biomeID)
				{
					var7[var8] = BWG4BiomeGenBase.BDiceriver.biomeID;
				}
				else if (var5[var8] == BWG4BiomeGenBase.BDjungle.biomeID || var5[var8] == BWG4BiomeGenBase.BDswampland.biomeID)
				{
					var7[var8] = BWG4BiomeGenBase.BDgreenriver.biomeID;
				}
				else if (var5[var8] == BWG4BiomeGenBase.BDdesert.biomeID || var5[var8] == BWG4BiomeGenBase.BDocean.biomeID || var5[var8] == BWG4BiomeGenBase.BDbeach.biomeID || var5[var8] == BWG4BiomeGenBase.BDbeachDunes.biomeID) 
				{
					var7[var8] = BWG4BiomeGenBase.BDsandriver.biomeID;
				}
				else
				{
					var7[var8] = BWG4BiomeGenBase.BDriver.biomeID;
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
