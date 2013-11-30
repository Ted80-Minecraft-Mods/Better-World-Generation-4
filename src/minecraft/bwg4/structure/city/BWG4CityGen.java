package bwg4.structure.city;

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

public class BWG4CityGen extends MapGenStructure
{
    private static List biomelist = Arrays.asList(new BiomeGenBase[] {BWG4Biomes.WASTELANDplains, BWG4Biomes.ADVENTUREdesert});

    private List scatteredFeatureSpawnList;
    
    public BWG4CityGen()
    {
        this.scatteredFeatureSpawnList = new ArrayList();
    }

    public BWG4CityGen(Map par1Map)
    {
        this();
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        if((par1 - 1) % 5 == 0 && (par2 - 1) % 5 == 0)
        {
        	return true;
        }

        return false;
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        return new BWG4CityGenStart(this.worldObj, this.rand, par1 * 16, par2 * 16);
    }
    
    public List getScatteredFeatureSpawnList()
    {
        return this.scatteredFeatureSpawnList;
    }
}
