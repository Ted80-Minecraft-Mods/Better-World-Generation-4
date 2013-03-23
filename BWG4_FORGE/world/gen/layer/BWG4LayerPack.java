package net.minecraft.world.gen.layer;

public class BWG4LayerPack extends GenLayer
{
	private int theworldID;
	
    public BWG4LayerPack(long par1, GenLayer par3GenLayer, int worldid)
    {
        super(par1);
        this.parent = par3GenLayer;
		theworldID = worldid;
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

				if(theworldID == 1)
				{ 
					//var10[var12 + var11 * par3] = 1;
					
					if(var13 == 0) //COLD
					{ 
						var10[var12 + var11 * par3] = nextInt(2) + 0; 
					}
					else if(var13 == 1) //NORM
					{
						var10[var12 + var11 * par3] = nextInt(2) + 10;
					}
					else if(var13 == 2) //WARM
					{
						var10[var12 + var11 * par3] = nextInt(3) + 20;
					}
					else //HOT
					{
						var10[var12 + var11 * par3] = nextInt(3) + 30;
					} 
				}
				else
				{
					int var14 = this.nextInt(3);

					if (var14 == 0)
					{
						var14 = 33;
					}
					else
					{
						var14 = 32;
					}

					var10[var12 + var11 * par3] = var14;
				}
            }
        }

        return var10;
    }
}
