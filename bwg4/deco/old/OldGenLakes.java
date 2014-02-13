package bwg4.deco.old;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class OldGenLakes extends WorldGenerator
{
    private Block field_15235_a;

    public OldGenLakes(Block b)
    {
        field_15235_a = b;
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        i -= 8;
        for(k -= 8; j > 0 && world.func_147437_c(i, j, k); j--) { }
        j -= 4;
        boolean aflag[] = new boolean[2048];
        int l = random.nextInt(4) + 4;
        for(int i1 = 0; i1 < l; i1++)
        {
            double d = random.nextDouble() * 6D + 3D;
            double d1 = random.nextDouble() * 4D + 2D;
            double d2 = random.nextDouble() * 6D + 3D; 
            double d3 = random.nextDouble() * (16D - d - 2D) + 1.0D + d / 2D;
            double d4 = random.nextDouble() * (8D - d1 - 4D) + 2D + d1 / 2D;
            double d5 = random.nextDouble() * (16D - d2 - 2D) + 1.0D + d2 / 2D;
            for(int j4 = 1; j4 < 15; j4++)
            {
                for(int k4 = 1; k4 < 15; k4++)
                {
                    for(int l4 = 1; l4 < 7; l4++)
                    {
                        double d6 = ((double)j4 - d3) / (d / 2D);
                        double d7 = ((double)l4 - d4) / (d1 / 2D);
                        double d8 = ((double)k4 - d5) / (d2 / 2D);
                        double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                        if(d9 < 1.0D)
                        {
                            aflag[(j4 * 16 + k4) * 8 + l4] = true;
                        }
                    }

                }

            }

        }

        for(int j1 = 0; j1 < 16; j1++)
        {
            for(int j2 = 0; j2 < 16; j2++)
            {
                for(int j3 = 0; j3 < 8; j3++)
                {
                    boolean flag = !aflag[(j1 * 16 + j2) * 8 + j3] && (j1 < 15 && aflag[((j1 + 1) * 16 + j2) * 8 + j3] || j1 > 0 && aflag[((j1 - 1) * 16 + j2) * 8 + j3] || j2 < 15 && aflag[(j1 * 16 + (j2 + 1)) * 8 + j3] || j2 > 0 && aflag[(j1 * 16 + (j2 - 1)) * 8 + j3] || j3 < 7 && aflag[(j1 * 16 + j2) * 8 + (j3 + 1)] || j3 > 0 && aflag[(j1 * 16 + j2) * 8 + (j3 - 1)]);
                    if(!flag)
                    {
                        continue;
                    }
                    Material material = world.func_147439_a(i + j1, j + j3, k + j2).func_149688_o();
                    if(j3 >= 4 && material.isLiquid())
                    {
                        return false;
                    }
                    if(j3 < 4 && !material.isSolid() && world.func_147439_a(i + j1, j + j3, k + j2) != field_15235_a)
                    {
                        return false;
                    }
                }

            }

        }

        /*
        for(int k1 = 0; k1 < 16; k1++)
        {
            for(int k2 = 0; k2 < 16; k2++)
            {
                for(int k3 = 0; k3 < 8; k3++)
                {
                    if(aflag[(k1 * 16 + k2) * 8 + k3])
                    {
                    	if(k3 < 4)
                    	{
                            world.func_147449_b(i + k1, j + k3, k + k2, Blocks.air); //field_15235_a
                    	}
                    	else
                    	{
                            world.func_147449_b(i + k1, j + k3, k + k2, Blocks.air);
                    	}
                    }
                }

            }

        }*/

        for(int l1 = 0; l1 < 16; l1++)
        {
            for(int l2 = 0; l2 < 16; l2++)
            {
                for(int l3 = 4; l3 < 8; l3++)
                {
                    if(aflag[(l1 * 16 + l2) * 8 + l3] && world.func_147439_a(i + l1, (j + l3) - 1, k + l2) == Blocks.dirt && world.getSavedLightValue(EnumSkyBlock.Sky, i + l1, j + l3, k + l2) > 0)
                    {
                        world.func_147449_b(i + l1, (j + l3) - 1, k + l2, Blocks.grass);
                    }
                }

            }

        }
        
        if(field_15235_a.func_149688_o() == Material.field_151574_g)
        {
            for(int i2 = 0; i2 < 16; i2++)
            {
                for(int i3 = 0; i3 < 16; i3++)
                {
                    for(int i4 = 0; i4 < 8; i4++)
                    {
                        boolean flag1 = !aflag[(i2 * 16 + i3) * 8 + i4] && (i2 < 15 && aflag[((i2 + 1) * 16 + i3) * 8 + i4] || i2 > 0 && aflag[((i2 - 1) * 16 + i3) * 8 + i4] || i3 < 15 && aflag[(i2 * 16 + (i3 + 1)) * 8 + i4] || i3 > 0 && aflag[(i2 * 16 + (i3 - 1)) * 8 + i4] || i4 < 7 && aflag[(i2 * 16 + i3) * 8 + (i4 + 1)] || i4 > 0 && aflag[(i2 * 16 + i3) * 8 + (i4 - 1)]);
                        if(flag1 && (i4 < 4 || random.nextInt(2) != 0) && world.func_147439_a(i + i2, j + i4, k + i3).func_149688_o().isSolid())
                        {
                            world.func_147449_b(i + i2, j + i4, k + i3, Blocks.stone); 
                        }
                    }

                }

            }

        }
        return true;
    }
}
