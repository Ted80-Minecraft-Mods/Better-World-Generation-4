package bwg4.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import bwg4.biomes.BWG4Biomes;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureScatteredFeatureStart;
import net.minecraft.world.gen.structure.StructureStart;

public class BWG4ScatteredFeature extends MapGenStructure
{
    private static List biomelist = Arrays.asList(new BiomeGenBase[] {BWG4Biomes.WASTELANDplains, BWG4Biomes.ADVENTUREdesert});

    private List scatteredFeatureSpawnList;
    private int maxDistanceBetweenScatteredFeatures;
    private int minDistanceBetweenScatteredFeatures;
    
    public BWG4ScatteredFeature()
    {
        this.scatteredFeatureSpawnList = new ArrayList();
        this.maxDistanceBetweenScatteredFeatures = 20; //32
        this.minDistanceBetweenScatteredFeatures = 18; //8
        this.scatteredFeatureSpawnList.add(new SpawnListEntry(EntityWitch.class, 1, 1, 1));
    }

    public BWG4ScatteredFeature(Map par1Map)
    {
        this();
        Iterator iterator = par1Map.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();

            if (((String)entry.getKey()).equals("distance"))
            {
                this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.maxDistanceBetweenScatteredFeatures, this.minDistanceBetweenScatteredFeatures + 1);
            }
        }
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        int k = par1;
        int l = par2;

        if (par1 < 0)
        {
            par1 -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        if (par2 < 0)
        {
            par2 -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        int i1 = par1 / this.maxDistanceBetweenScatteredFeatures;
        int j1 = par2 / this.maxDistanceBetweenScatteredFeatures;
        Random random = this.worldObj.setRandomSeed(i1, j1, 14357617);
        i1 *= this.maxDistanceBetweenScatteredFeatures;
        j1 *= this.maxDistanceBetweenScatteredFeatures;
        i1 += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
        j1 += random.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);

        if (k == i1 && l == j1)
        {
            BiomeGenBase biomegenbase = this.worldObj.getWorldChunkManager().getBiomeGenAt(k * 16 + 8, l * 16 + 8);
            Iterator iterator = biomelist.iterator();

            while (iterator.hasNext())
            {
                BiomeGenBase biomegenbase1 = (BiomeGenBase)iterator.next();

                if (biomegenbase == biomegenbase1)
                {
                    return true;
                }
            }
        }

        return false;
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        return new BWG4ScatteredFeatureStart(this.worldObj, this.rand, par1, par2);
    }

    /**
     * returns possible spawns for scattered features
     */
    public List getScatteredFeatureSpawnList()
    {
        return this.scatteredFeatureSpawnList;
    }
}
