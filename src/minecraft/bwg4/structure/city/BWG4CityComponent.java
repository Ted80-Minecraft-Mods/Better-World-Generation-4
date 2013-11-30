package bwg4.structure.city;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

abstract class BWG4CityComponent extends StructureComponent
{
    protected final int scatteredFeatureSizeX;
    protected final int scatteredFeatureSizeY;
    protected final int scatteredFeatureSizeZ;
    protected int field_74936_d = -1;

    protected BWG4CityComponent(Random par1Random, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        super(0);
        this.scatteredFeatureSizeX = par5;
        this.scatteredFeatureSizeY = par6;
        this.scatteredFeatureSizeZ = par7;
        this.coordBaseMode = 0;//par1Random.nextInt(4);

        this.boundingBox = new StructureBoundingBox(par2, par3, par4, par2 + par5 - 1, par3 + par6 - 1, par4 + par7 - 1);
    }

    protected boolean func_74935_a(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3)
    {
        if (this.field_74936_d >= 0)
        {
            return true;
        }
        else
        {
            this.field_74936_d = 40;
            this.boundingBox.offset(0, this.field_74936_d - this.boundingBox.minY + par3, 0);
            return true;
        }
    }
}
