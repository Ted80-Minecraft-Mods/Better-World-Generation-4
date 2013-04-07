package ted80.bwg4.layer;

import net.minecraft.world.gen.layer.IntCache;

public class BWG4LayerRegion extends BWG4Layer
{
    public BWG4LayerRegion(long par1, BWG4Layer par3GenLayer)
    {
        super(par1);
        this.parent = par3GenLayer;
    }

    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int var5 = par1 - 1;
        int var6 = par2 - 1;
        int var7 = par3 + 2;
        int var8 = par4 + 2;
        int[] var9 = this.parent.getInts(var5, var6, var7, var8);
        int[] var10 = IntCache.getIntCache(par3 * par4);

        for (int var11 = 0; var11 < par4; ++var11)
        {
            for (int var12 = 0; var12 < par3; ++var12)
            {
                int var13 = var9[var12 + 1 + (var11 + 1) * var7];
                this.initChunkSeed((long)(var12 + par1), (long)(var11 + par2));

				int var20 = var9[var12 + 1 + (var11 + 1 - 1) * (par3 + 2)];
				int var21 = var9[var12 + 1 + 1 + (var11 + 1) * (par3 + 2)];
				int var22 = var9[var12 + 1 - 1 + (var11 + 1) * (par3 + 2)];
				int var23 = var9[var12 + 1 + (var11 + 1 + 1) * (par3 + 2)];				
				
				int var14 = this.nextInt(2);
				if(var14 == 0)
				{
					int var15 = this.nextInt(2);
					if(var15 == 0 && var20 != 2 && var20 != 3 && var21 != 2 && var21 != 3 && var22 != 2 && var22 != 3 && var23 != 2 && var23 != 3) //no hot/wet around
					{
						var10[var12 + var11 * par3] = 0;
					}
					else
					{
						var10[var12 + var11 * par3] = 1;
					}
				}
				else
				{
					int var16 = this.nextInt(2);
					if(var16 == 0 && var20 != 0 && var20 != 1 && var21 != 0 && var21 != 1 && var22 != 0 && var22 != 1 && var23 != 0 && var23 != 1) //no cold/norm around
					{
						var10[var12 + var11 * par3] = 3;
					}
					else if(var20 != 0 && var21 != 0 && var22 != 0 && var23 != 0)
					{
						var10[var12 + var11 * par3] = 2;
					}
					else
					{
						var10[var12 + var11 * par3] = 1;
					}
				}
            }
        }

        return var10;
    }
}
