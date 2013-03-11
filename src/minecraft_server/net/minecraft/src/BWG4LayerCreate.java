package net.minecraft.src;

public class BWG4LayerCreate extends GenLayer
{
	private boolean ocean = false;

    public BWG4LayerCreate(long par1)
    {
        super(par1);
    }
	
    public BWG4LayerCreate(long par1, String[] Settings, boolean remote)
    {
        super(par1);
		
		if(remote)
		{
			for(int i = 0; i < Settings.length; i++)
			{
				if(Settings[i].equals("Ocean=true")) { ocean = true; }
			}
		}
    }

    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = IntCache.getIntCache(par3 * par4);

        for (int var6 = 0; var6 < par4; ++var6)
        {
            for (int var7 = 0; var7 < par3; ++var7)
            {
                this.initChunkSeed((long)(par1 + var7), (long)(par2 + var6));
				if(ocean)
				{
					var5[var7 + var6 * par3] = this.nextInt(10) == 0 ? 1 : 0;
				}
				else
				{
					var5[var7 + var6 * par3] = 1;
				}	
            }
        }
        return var5;
    }
}