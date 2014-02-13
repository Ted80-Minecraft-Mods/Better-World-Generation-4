package bwg4.deco.old;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class OldGenReed extends WorldGenerator
{

    public OldGenReed()
    {
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        for(int l = 0; l < 20; l++)
        {
            int i1 = (i + random.nextInt(4)) - random.nextInt(4);
            int j1 = j;
            int k1 = (k + random.nextInt(4)) - random.nextInt(4);
            if(!world.func_147437_c(i1, j1, k1) || world.func_147439_a(i1 - 1, j1 - 1, k1).func_149688_o() != Material.field_151586_h && world.func_147439_a(i1 + 1, j1 - 1, k1).func_149688_o() != Material.field_151586_h && world.func_147439_a(i1, j1 - 1, k1 - 1).func_149688_o() != Material.field_151586_h && world.func_147439_a(i1, j1 - 1, k1 + 1).func_149688_o() != Material.field_151586_h)
            {
                continue;
            }
            int l1 = 2 + random.nextInt(random.nextInt(3) + 1);
            for(int i2 = 0; i2 < l1; i2++)
            {
                if(Blocks.reeds.func_149718_j(world, i1, j1 + i2, k1))
                {
                    world.func_147449_b(i1, j1 + i2, k1, Blocks.reeds);
                }
            }

        }

        return true;
    }
}
