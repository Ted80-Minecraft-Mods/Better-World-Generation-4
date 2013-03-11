package net.minecraft.src;

import java.util.Random;

public class BWG4BiomesIndev extends BiomeGenBase
{
	private int indevID;

    public BWG4BiomesIndev(int par1)
    {
        super(par1);
		indevID = par1 - 101; //normal, hell, paradise, woods, snow
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 1, 2, 3));
    }
}
