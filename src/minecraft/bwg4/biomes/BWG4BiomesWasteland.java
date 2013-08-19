package bwg4.biomes;

import java.util.Random;

import bwg4.deco.BWG4decoWasteland;
import bwg4.deco.BWG4decoWastelandTree;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4BiomesWasteland extends BiomeGenBase
{
	private BWG4Decorator bwg4decorator;
	
	public int biome = 0;
	
	BWG4BiomesWasteland(int id, int b)
	{
		super(id);
        theBiomeDecorator = new BWG4Decorator(this);
        bwg4decorator = (BWG4Decorator) theBiomeDecorator;
        
		biome = b;
		bwg4decorator.usebwg4deco = true;
		spawnableCreatureList.clear();

		topBlock = (byte)Block.dirt.blockID;
		fillerBlock = (byte)Block.dirt.blockID;

		bwg4decorator.oresenabled = false;
		bwg4decorator.redflowers = -1;
		bwg4decorator.yellowflowers = -1; 
		bwg4decorator.grass = -1; 
		bwg4decorator.sugarcane = -1; 
		bwg4decorator.melon = 1;
		bwg4decorator.pumpkin = 1; 
		bwg4decorator.waterliquid = -1;
		bwg4decorator.lavaliquid = -1;
		
		if(biome == 1) //RIVER
		{
			bwg4decorator.tl1amount = -999;
		}
		if(biome == 2) //PINE FOREST
		{
			bwg4decorator.tl1amount = 3;
		}
		if(biome == 3) //PLAINS
		{
			bwg4decorator.tl1amount = -999;
		}
		if(biome == 4) //FOREST
		{
			bwg4decorator.tl1amount = 1;
		}
		if(biome == 5) //DESERT
		{
			bwg4decorator.tl1amount = -2;
			topBlock = (byte)Block.sand.blockID;
			fillerBlock = (byte)Block.sand.blockID;
		}
		if(biome == 6) //MOUNTAINS
		{
			bwg4decorator.tl1amount = -2;
		}
	}

    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		if(biome == 1) //RIVER
		{
			return new BWG4decoWastelandTree();
		}
		if(biome == 2) //PINE FOREST
		{
			return new BWG4decoWasteland(4);
		}
		if(biome == 3) //PLAINS
		{
			return new BWG4decoWastelandTree();
		}
		if(biome == 4) //FOREST
		{
			return new BWG4decoWastelandTree();
		}
		if(biome == 5) //DESERT
		{
			return new BWG4decoWastelandTree();
		}
		if(biome == 6) //MOUNTAINS
		{
			return new BWG4decoWastelandTree();
		}
		return new BWG4decoWastelandTree();
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);	
    }
    
	public int getBiomeGrassColor()
    {
		return 0xAFA49A;
    }

    public int getBiomeFoliageColor()
    {
		return 0xB1A08E;
    }

    public int getSkyColorByTemp(float par1)
    {
		return 0xD9BEA3;
    }
}
