package net.minecraft.src;

public class BWG4LayerRiverMix extends GenLayer
{
    private GenLayer biomePatternGeneratorChain;
    private GenLayer riverPatternGeneratorChain;

    public BWG4LayerRiverMix(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer)
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
            if (var5[var8] == BiomeGenBase.BDocean.biomeID || var5[var8] == BiomeGenBase.BDtropicalisland.biomeID || var5[var8] == BiomeGenBase.BDjungleisland.biomeID || var5[var8] == BiomeGenBase.BDmushroomisland.biomeID)
            {
                var7[var8] = var5[var8];
            }
            else if (var6[var8] >= 0)
            {
				if (var5[var8] == BiomeGenBase.BDsnowpines.biomeID || var5[var8] == BiomeGenBase.BDsnowforest.biomeID || var5[var8] == BiomeGenBase.BDsnowtaiga.biomeID || var5[var8] == BiomeGenBase.BDsnowplains.biomeID || var5[var8] == BiomeGenBase.BDsnowhills.biomeID)
				{
					var7[var8] = BiomeGenBase.BDiceriver.biomeID;
				}
				else if (var5[var8] == BiomeGenBase.BDjungle.biomeID || var5[var8] == BiomeGenBase.BDswampland.biomeID)
				{
					var7[var8] = BiomeGenBase.BDgreenriver.biomeID;
				}
				else if (var5[var8] == BiomeGenBase.BDdesert.biomeID)
				{
					var7[var8] = BiomeGenBase.BDsandriver.biomeID;
				}
				else
				{
					var7[var8] = BiomeGenBase.BDriver.biomeID;
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
