package net.minecraft.src;

import java.util.Random;

public class BWG4BiomesAlpha extends BiomeGenBase
{
	private int biomeid;

    public BWG4BiomesAlpha(int par1)
    {
        super(par1);
		biomeid = par1;
		
		if(par1 == 90)
		{
			spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityOcelot.class, 2, 1, 1));
		}
		
		if(par1 == 91 || par1 == 92 || par1 == 93 || par1 == 96)
		{
			spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 8, 4, 4));
		}
		
		if(par1 == 98)
		{
			spawnableCreatureList.clear();
			topBlock = (byte)Block.sand.blockID;
			fillerBlock = (byte)Block.sand.blockID;
		}	
    }
}
