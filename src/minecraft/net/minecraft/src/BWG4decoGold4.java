package net.minecraft.src;

import java.util.Random;

public class BWG4decoGold4 extends WorldGenerator
{
	private int objectID;
	private int input1;

    public BWG4decoGold4(int object)
    {
		objectID = object;
    }
	
    public BWG4decoGold4(int object, int in1)
    {
		objectID = object;
		input1 = in1;
    }

    public boolean generate(World par1World, Random par2Random, int x, int y, int z)
    {
		if(objectID == 1)//GRASS CACTI
		{	
			int var5 = par1World.getBlockId(x, y - 1, z);
			if (var5 != Block.grass.blockID) { return false; }
			else if (par1World.getBlockMaterial(x - 1, y, z).isSolid()) { return false; }
			else if (par1World.getBlockMaterial(x + 1, y, z).isSolid()) { return false; }
			else if (par1World.getBlockMaterial(x, y, z - 1).isSolid()) { return false; }
			else if (par1World.getBlockMaterial(x, y, z + 1).isSolid()) { return false; }

			par1World.setBlock(x, y - 1, z, Block.sand.blockID);
			int var10 = 2 + par2Random.nextInt(par2Random.nextInt(3) + 1);
			for (int var11 = 0; var11 < var10; ++var11)
			{
				par1World.setBlock(x, y + var11, z, Block.cactus.blockID);
			}
		}
		else if(objectID == 2)//SAVANNA TREE
		{	
			int var5 = par1World.getBlockId(x, y - 1, z);
			if (var5 != Block.grass.blockID) { return false; }
			int rand = 4 + par2Random.nextInt(3);
			for(int i = 0; i < rand; i++) { par1World.setBlock(x, y + i, z, Block.wood.blockID); }
			
			if(par2Random.nextInt(4) == 0) { //branch1
			par1World.setBlock(x + 0, y + rand + 1, z + 1, Block.wood.blockID);
			par1World.setBlock(x + 1, y + rand + 2, z + 2, Block.wood.blockID);
			createSavannaLeaves(par1World, par2Random, x + 1, y + rand + 2, z + 2, 3);
			createSavannaLeaves(par1World, par2Random, x + 1, y + rand + 3, z + 2, 2);
			}
			
			if(par2Random.nextInt(4) == 0) { //branch2
			par1World.setBlock(x + 1, y + rand + 0, z + 0, Block.wood.blockID);
			par1World.setBlock(x + 2, y + rand + 1, z + 0, Block.wood.blockID);
			par1World.setBlock(x + 3, y + rand + 2, z - 1, Block.wood.blockID);
			createSavannaLeaves(par1World, par2Random, x + 3, y + rand + 3, z - 1, 3);
			createSavannaLeaves(par1World, par2Random, x + 3, y + rand + 4, z - 1, 2);
			}
			
			if(par2Random.nextInt(4) == 0) { //branch3
			par1World.setBlock(x - 1, y + rand + 0, z + 0, Block.wood.blockID);
			par1World.setBlock(x - 2, y + rand + 1, z + 0, Block.wood.blockID);
			par1World.setBlock(x - 3, y + rand + 2, z - 1, Block.wood.blockID);
			par1World.setBlock(x - 4, y + rand + 3, z - 2, Block.wood.blockID);
			createSavannaLeaves(par1World, par2Random, x - 4, y + rand + 4, z - 2, 3);
			createSavannaLeaves(par1World, par2Random, x - 4, y + rand + 5, z - 2, 2);
			}
			
			if(par2Random.nextInt(4) == 0) { //branch4
			par1World.setBlock(x + 0, y + rand + 0, z - 1, Block.wood.blockID);
			par1World.setBlock(x + 1, y + rand + 1, z - 2, Block.wood.blockID);
			par1World.setBlock(x + 2, y + rand + 2, z - 2, Block.wood.blockID);
			par1World.setBlock(x + 3, y + rand + 3, z - 2, Block.wood.blockID);
			createSavannaLeaves(par1World, par2Random, x + 3, y + rand + 3, z - 2, 3);
			createSavannaLeaves(par1World, par2Random, x + 3, y + rand + 4, z - 2, 2);
			}
			
			if(par2Random.nextInt(4) == 0) { //branch5
			par1World.setBlock(x + 0, y + rand + 0, z - 1, Block.wood.blockID);
			par1World.setBlock(x + 0, y + rand + 0, z - 2, Block.wood.blockID);
			par1World.setBlock(x + 1, y + rand + 1, z - 3, Block.wood.blockID);
			createSavannaLeaves(par1World, par2Random, x + 1, y + rand + 1, z - 3, 3);
			createSavannaLeaves(par1World, par2Random, x + 1, y + rand + 2, z - 3, 2);
			}
			
			//branch6
			par1World.setBlock(x - 0, y + rand + 0, z + 0, Block.wood.blockID);
			par1World.setBlock(x - 0, y + rand + 1, z + 0, Block.wood.blockID);
			par1World.setBlock(x - 0, y + rand + 2, z - 0, Block.wood.blockID);
			createSavannaLeaves(par1World, par2Random, x + 0, y + rand + 3, z - 0, 3);
			createSavannaLeaves(par1World, par2Random, x + 0, y + rand + 4, z - 0, 2);
		}
		else
		{
		}
		return true;
	}
	
	private void createSavannaLeaves(World par1World, Random par2Random, int x, int y, int z, int size)
	{
		for(int x1 = -size + x; x1 < size + 1 + x; x1++) {
			for(int z1 = -size + z; z1 < size + 1 + z; z1++) {			
				int var5 = par1World.getBlockId(x1, y, z1);
				if (var5 == 0)  {
					if(x1 == -size + x && z1 == -size + z ){} else if(x1 == -size + x && z1 == size + z ){} else if(x1 == size + x && z1 == -size + z ){} else if(x1 == size + x && z1 == size + z ){}
					else { par1World.setBlock(x1, y, z1, Block.leaves.blockID); }	
				}
			}
		}
		if(size==3){par1World.setBlock(x, y, z, Block.wood.blockID);}
	}
	
    private ItemStack getChestList(int chestid, Random par1Random, World par1World)
    {
		return null;
    }
}
