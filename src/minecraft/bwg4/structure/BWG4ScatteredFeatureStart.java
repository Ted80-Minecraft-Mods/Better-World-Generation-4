package bwg4.structure;

import java.util.Random;

import bwg4.biomes.BWG4Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureStart;

public class BWG4ScatteredFeatureStart extends StructureStart
{
    public BWG4ScatteredFeatureStart(World par1World, Random par2Random, int par3, int par4)
    {
        BiomeGenBase biomegenbase = par1World.getBiomeGenForCoords(par3 * 16 + 8, par4 * 16 + 8);

        if(biomegenbase == BWG4Biomes.WASTELANDplains)
        {
        	BWG4ComponentWastelandRuin1 wastelandruin1 = new BWG4ComponentWastelandRuin1(par2Random, par3 * 16, par4 * 16);
            this.components.add(wastelandruin1);
        }
        
        /*if (biomegenbase != BiomeGenBase.jungle && biomegenbase != BiomeGenBase.jungleHills)
        {
            if (biomegenbase == BiomeGenBase.swampland)
            {
                ComponentScatteredFeatureSwampHut componentscatteredfeatureswamphut = new ComponentScatteredFeatureSwampHut(par2Random, par3 * 16, par4 * 16);
                this.components.add(componentscatteredfeatureswamphut);
            }
            else
            {
                ComponentScatteredFeatureDesertPyramid componentscatteredfeaturedesertpyramid = new ComponentScatteredFeatureDesertPyramid(par2Random, par3 * 16, par4 * 16);
                this.components.add(componentscatteredfeaturedesertpyramid);
            }
        }
        else
        {
            ComponentScatteredFeatureJunglePyramid componentscatteredfeaturejunglepyramid = new ComponentScatteredFeatureJunglePyramid(par2Random, par3 * 16, par4 * 16);
            this.components.add(componentscatteredfeaturejunglepyramid);
        }*/

        this.updateBoundingBox();
    }
}