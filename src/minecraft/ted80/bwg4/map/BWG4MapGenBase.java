package ted80.bwg4.map;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class BWG4MapGenBase
{
    protected int range;
    protected Random rand;
    protected World worldObj;

    public BWG4MapGenBase()
    {
        range = 8;
        rand = new Random();
    }

    public void generate(IChunkProvider ichunkprovider, World world, int i, int j, byte abyte0[])
    {
        int k = range;
		worldObj = world;
        rand.setSeed(worldObj.getSeed());
        long l = (rand.nextLong() / 2L) * 2L + 1L;
        long l1 = (rand.nextLong() / 2L) * 2L + 1L;
        for(int i1 = i - k; i1 <= i + k; i1++)
        {
            for(int j1 = j - k; j1 <= j + k; j1++)
            {
                rand.setSeed((long)i1 * l + (long)j1 * l1 ^ worldObj.getSeed());
                recursiveGenerate(worldObj, i1, j1, i, j, abyte0);
            }

        }

    }

    protected void recursiveGenerate(World world, int i, int j, int k, int l, byte abyte0[])
    {
    }
}
