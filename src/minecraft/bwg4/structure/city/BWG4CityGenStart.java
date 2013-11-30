package bwg4.structure.city;

import java.util.Random;

import bwg4.biomes.BWG4Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureStart;

public class BWG4CityGenStart extends StructureStart
{
    public BWG4CityGenStart(World par1World, Random par2Random, int par3, int par4)
    {
        BWG4CityComponentOffice1 office1 = new BWG4CityComponentOffice1(par2Random, par3, par4);
        this.components.add(office1);

        this.updateBoundingBox();
    }
}