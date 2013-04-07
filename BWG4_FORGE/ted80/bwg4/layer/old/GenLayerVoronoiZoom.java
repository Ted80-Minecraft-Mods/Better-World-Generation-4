package ted80.bwg4.layer.old;

import ted80.bwg4.layer.BWG4Layer;

public class GenLayerVoronoiZoom extends BWG4Layer
{
    public GenLayerVoronoiZoom(long par1, BWG4Layer par3GenLayer)
    {
        super(par1);
        super.parent = par3GenLayer;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        par1 -= 2;
        par2 -= 2;
        byte b0 = 2;
        int i1 = 1 << b0;
        int j1 = par1 >> b0;
        int k1 = par2 >> b0;
        int l1 = (par3 >> b0) + 3;
        int i2 = (par4 >> b0) + 3;
        int[] aint = this.parent.getInts(j1, k1, l1, i2);
        int j2 = l1 << b0;
        int k2 = i2 << b0;
        int[] aint1 = IntCache.getIntCache(j2 * k2);
        int l2;

        for (int i3 = 0; i3 < i2 - 1; ++i3)
        {
            l2 = aint[0 + (i3 + 0) * l1];
            int j3 = aint[0 + (i3 + 1) * l1];

            for (int k3 = 0; k3 < l1 - 1; ++k3)
            {
                double d0 = (double)i1 * 0.9D;
                this.initChunkSeed((long)(k3 + j1 << b0), (long)(i3 + k1 << b0));
                double d1 = ((double)this.nextInt(1024) / 1024.0D - 0.5D) * d0;
                double d2 = ((double)this.nextInt(1024) / 1024.0D - 0.5D) * d0;
                this.initChunkSeed((long)(k3 + j1 + 1 << b0), (long)(i3 + k1 << b0));
                double d3 = ((double)this.nextInt(1024) / 1024.0D - 0.5D) * d0 + (double)i1;
                double d4 = ((double)this.nextInt(1024) / 1024.0D - 0.5D) * d0;
                this.initChunkSeed((long)(k3 + j1 << b0), (long)(i3 + k1 + 1 << b0));
                double d5 = ((double)this.nextInt(1024) / 1024.0D - 0.5D) * d0;
                double d6 = ((double)this.nextInt(1024) / 1024.0D - 0.5D) * d0 + (double)i1;
                this.initChunkSeed((long)(k3 + j1 + 1 << b0), (long)(i3 + k1 + 1 << b0));
                double d7 = ((double)this.nextInt(1024) / 1024.0D - 0.5D) * d0 + (double)i1;
                double d8 = ((double)this.nextInt(1024) / 1024.0D - 0.5D) * d0 + (double)i1;
                int l3 = aint[k3 + 1 + (i3 + 0) * l1];
                int i4 = aint[k3 + 1 + (i3 + 1) * l1];

                for (int j4 = 0; j4 < i1; ++j4)
                {
                    int k4 = ((i3 << b0) + j4) * j2 + (k3 << b0);

                    for (int l4 = 0; l4 < i1; ++l4)
                    {
                        double d9 = ((double)j4 - d2) * ((double)j4 - d2) + ((double)l4 - d1) * ((double)l4 - d1);
                        double d10 = ((double)j4 - d4) * ((double)j4 - d4) + ((double)l4 - d3) * ((double)l4 - d3);
                        double d11 = ((double)j4 - d6) * ((double)j4 - d6) + ((double)l4 - d5) * ((double)l4 - d5);
                        double d12 = ((double)j4 - d8) * ((double)j4 - d8) + ((double)l4 - d7) * ((double)l4 - d7);

                        if (d9 < d10 && d9 < d11 && d9 < d12)
                        {
                            aint1[k4++] = l2;
                        }
                        else if (d10 < d9 && d10 < d11 && d10 < d12)
                        {
                            aint1[k4++] = l3;
                        }
                        else if (d11 < d9 && d11 < d10 && d11 < d12)
                        {
                            aint1[k4++] = j3;
                        }
                        else
                        {
                            aint1[k4++] = i4;
                        }
                    }
                }

                l2 = l3;
                j3 = i4;
            }
        }

        int[] aint2 = IntCache.getIntCache(par3 * par4);

        for (l2 = 0; l2 < par4; ++l2)
        {
            System.arraycopy(aint1, (l2 + (par2 & i1 - 1)) * (l1 << b0) + (par1 & i1 - 1), aint2, l2 * par3, par3);
        }

        return aint2;
    }
}
