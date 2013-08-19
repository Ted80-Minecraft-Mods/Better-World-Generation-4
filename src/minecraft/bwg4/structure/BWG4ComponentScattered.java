package bwg4.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

abstract class BWG4ComponentScattered extends StructureComponent
{
    /** The size of the bounding box for this feature in the X axis */
    protected final int scatteredFeatureSizeX;

    /** The size of the bounding box for this feature in the Y axis */
    protected final int scatteredFeatureSizeY;

    /** The size of the bounding box for this feature in the Z axis */
    protected final int scatteredFeatureSizeZ;
    protected int field_74936_d = -1;

    protected BWG4ComponentScattered(Random par1Random, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        super(0);
        this.scatteredFeatureSizeX = par5;
        this.scatteredFeatureSizeY = par6;
        this.scatteredFeatureSizeZ = par7;
        this.coordBaseMode = par1Random.nextInt(4);

        switch (this.coordBaseMode)
        {
            case 0:
            case 2:
                this.boundingBox = new StructureBoundingBox(par2, par3, par4, par2 + par5 - 1, par3 + par6 - 1, par4 + par7 - 1);
                break;
            default:
                this.boundingBox = new StructureBoundingBox(par2, par3, par4, par2 + par7 - 1, par3 + par6 - 1, par4 + par5 - 1);
        }
    }

    protected boolean func_74935_a(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3)
    {
        if (this.field_74936_d >= 0)
        {
            return true;
        }
        else
        {
            int j = 0;
            int k = 0;

            for (int l = this.boundingBox.minZ; l <= this.boundingBox.maxZ; ++l)
            {
                for (int i1 = this.boundingBox.minX; i1 <= this.boundingBox.maxX; ++i1)
                {
                    if (par2StructureBoundingBox.isVecInside(i1, 64, l))
                    {
                        j += Math.max(par1World.getTopSolidOrLiquidBlock(i1, l), par1World.provider.getAverageGroundLevel());
                        ++k;
                    }
                }
            }

            if (k == 0)
            {
                return false;
            }
            else
            {
                this.field_74936_d = j / k;
                this.boundingBox.offset(0, this.field_74936_d - this.boundingBox.minY + par3, 0);
                return true;
            }
        }
    }
}
