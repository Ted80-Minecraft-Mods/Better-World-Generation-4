package net.minecraft.src;

import java.util.Random;

public class BWG4decoGold3 extends WorldGenerator
{
	private int objectID;

    public BWG4decoGold3(int object)
    {
		objectID = object;
    }

    public boolean generate(World par1World, Random par2Random, int x, int y, int z)
    {
		if(objectID == 1)//EASTER ISLAND STATUE
		{	
			//main frame
			for(int mainx = x - 1; mainx < x + 2; mainx++)
			{
				for(int mainz = z - 1; mainz < z + 2; mainz++)
				{
					for(int mainy = y - 5; mainy < y + 10; mainy++)
					{
						if (par2Random.nextInt(2) != 0)
						{
							par1World.setBlock(mainx, mainy, mainz, Block.cobblestoneMossy.blockID);
						}
						else
						{
							par1World.setBlock(mainx, mainy, mainz, Block.cobblestone.blockID);
						}
					}
				}
			}

			par1World.setBlock(x + 2, y + 6, z, Block.cobblestoneMossy.blockID);
			par1World.setBlock(x + 3, y + 6, z, Block.cobblestoneMossy.blockID);
			par1World.setBlock(x + 3, y + 5, z, Block.cobblestoneMossy.blockID);
			par1World.setBlock(x + 3, y + 4, z, Block.cobblestoneMossy.blockID);
			
			for(int earz = z - 2; earz < z + 3; earz++)
			{
				for(int eary = y + 2; eary < y + 8; eary++)
				{
					if (par2Random.nextInt(2) != 0)
					{
						par1World.setBlock(x, eary, earz, Block.cobblestoneMossy.blockID);
					}
					else
					{
						par1World.setBlock(x, eary, earz, Block.cobblestone.blockID);
					}
				}
			}
			
			for(int headz = z - 1; headz < z + 2; headz++)
			{
				for(int heady = y + 2; heady < y + 10; heady++)
				{
					if(heady > y + 7 || heady < y + 5)
					{
						if (par2Random.nextInt(2) != 0)
						{
							par1World.setBlock(x + 2, heady, headz, Block.cobblestoneMossy.blockID);
						}
						else
						{
							par1World.setBlock(x + 2, heady, headz, Block.cobblestone.blockID);
						}
					}	
				}
			}

		}
		else if(objectID == 2)//RainForest Trees
		{		
			int var5 = par1World.getBlockId(x, y - 1, z);
			if (var5 != Block.grass.blockID) { return false; }
			int rand = 14 + par2Random.nextInt(5);
			for(int i = 0; i < rand; i++) { par1World.setBlock(x, y + i, z, Block.wood.blockID); }
			
			createRainForestBranch(par1World, par2Random, x, y + rand - 5, z);
			createRainForestLeaves(par1World, par2Random, x, y + rand, z, 2);
			createRainForestLeaves(par1World, par2Random, x, y + rand + 1, z, 3);
			createRainForestLeaves(par1World, par2Random, x, y + rand + 2, z, 2);
			par1World.setBlock(x, y + rand, z, Block.wood.blockID);
			par1World.setBlock(x, y + rand + 1, z, Block.wood.blockID);
		}
		else if(objectID == 3)//Swampland Trees
		{		
			int var5 = par1World.getBlockId(x, y - 1, z);
			if (var5 != Block.grass.blockID && var5 != Block.waterMoving.blockID && var5 != Block.waterStill.blockID) { return false; }
			int rand = 11 + par2Random.nextInt(3);
			for(int i = 6; i < rand; i++) { par1World.setBlock(x, y + i, z, Block.wood.blockID); }
			
			createSwampRoots(par1World, par2Random, x, y, z);
			// if(par2Random.nextInt(3) == 0)
			// {
				// WorldGenerator worldgenerator = new BWG4decoBigTree(8 + par2Random.nextInt(7), 10);
				// worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				// worldgenerator.generate(par1World, par2Random, x, y + rand, y);
			// }
			// else
			// {
				createRainForestBranch(par1World, par2Random, x, y + rand - 5, z);
				createRainForestLeaves(par1World, par2Random, x, y + rand, z, 2);
				createRainForestLeaves(par1World, par2Random, x, y + rand + 1, z, 3);
				createRainForestLeaves(par1World, par2Random, x, y + rand + 2, z, 2);
				par1World.setBlock(x, y + rand, z, Block.wood.blockID);
				par1World.setBlock(x, y + rand + 1, z, Block.wood.blockID);
			// }
		}
		else
		{
		}
		return true;
	}
	
	private void createSwampRoots(World par1World, Random par2Random, int x, int y, int z)
	{
		int rand1 = 0 + par2Random.nextInt(5);
		for(int r1 = rand1; r1 < 8; r1++) { par1World.setBlock(x, y + r1, z, Block.wood.blockID); }
		par1World.setBlock(x - 1, y + rand1, z, Block.wood.blockID);
		par1World.setBlock(x - 1, y + rand1 - 1, z, Block.wood.blockID);
		par1World.setBlock(x - 2, y + rand1 - 1, z, Block.wood.blockID);
		par1World.setBlock(x - 2, y + rand1 - 2, z, Block.wood.blockID);
		par1World.setBlock(x - 2, y + rand1 - 3, z, Block.wood.blockID);
		par1World.setBlock(x - 3, y + rand1 - 3, z, Block.wood.blockID);
		for(int rr1 = y + rand1 - 3; rr1 > 50; rr1--) { 
			int b1 = par1World.getBlockId(x - 3, rr1, z);
			if (b1 != 0 && b1 != Block.grass.blockID && b1 != Block.waterMoving.blockID && b1 != Block.waterStill.blockID && b1 != Block.leaves.blockID && b1 != Block.wood.blockID && b1 != Block.vine.blockID && b1 != Block.tallGrass.blockID) { break; } else { par1World.setBlock(x - 3, rr1, z, Block.wood.blockID); }
		}
		
		int rand2 = 2 + par2Random.nextInt(5);
		for(int r2 = rand2; r2 < 8; r2++) { par1World.setBlock(x, y + r2, z, Block.wood.blockID); }
		par1World.setBlock(x + 1, y + rand2, z, Block.wood.blockID);
		par1World.setBlock(x + 1, y + rand2 - 1, z, Block.wood.blockID);
		par1World.setBlock(x + 2, y + rand2 - 1, z, Block.wood.blockID);
		par1World.setBlock(x + 2, y + rand2 - 2, z, Block.wood.blockID);
		par1World.setBlock(x + 2, y + rand2 - 3, z, Block.wood.blockID);
		par1World.setBlock(x + 3, y + rand2 - 3, z, Block.wood.blockID);
		for(int rr2 = y + rand2 - 3; rr2 > 50; rr2--) { 
			int b2 = par1World.getBlockId(x + 3, rr2, z);
			if (b2 != 0 && b2 != Block.grass.blockID && b2 != Block.waterMoving.blockID && b2 != Block.waterStill.blockID && b2 != Block.leaves.blockID && b2 != Block.wood.blockID && b2 != Block.vine.blockID && b2 != Block.tallGrass.blockID) { break; } else { par1World.setBlock(x + 3, rr2, z, Block.wood.blockID); }
		}
		
		int rand3 = 2 + par2Random.nextInt(5);
		for(int r3 = rand3; r3 < 8; r3++) { par1World.setBlock(x, y + r3, z, Block.wood.blockID); }
		par1World.setBlock(x, y + rand3, z - 1, Block.wood.blockID);
		par1World.setBlock(x, y + rand3 - 1, z - 1, Block.wood.blockID);
		par1World.setBlock(x, y + rand3 - 1, z - 2, Block.wood.blockID);
		par1World.setBlock(x, y + rand3 - 2, z - 2, Block.wood.blockID);
		par1World.setBlock(x, y + rand3 - 3, z - 2, Block.wood.blockID);
		par1World.setBlock(x, y + rand3 - 3, z - 3, Block.wood.blockID);
		for(int rr3 = y + rand3 - 3; rr3 > 50; rr3--) { 
			int b3 = par1World.getBlockId(x, rr3, z - 3);
			if (b3 != 0 && b3 != Block.grass.blockID && b3 != Block.waterMoving.blockID && b3 != Block.waterStill.blockID && b3 != Block.leaves.blockID && b3 != Block.wood.blockID && b3 != Block.vine.blockID && b3 != Block.tallGrass.blockID) { break; } else { par1World.setBlock(x, rr3, z - 3, Block.wood.blockID); }
		}
		
		int rand4 = 2 + par2Random.nextInt(5);
		for(int r4 = rand4; r4 < 8; r4++) { par1World.setBlock(x, y + r4, z, Block.wood.blockID); }
		par1World.setBlock(x, y + rand4, z + 1, Block.wood.blockID);
		par1World.setBlock(x, y + rand4 - 1, z + 1, Block.wood.blockID);
		par1World.setBlock(x, y + rand4 - 1, z + 2, Block.wood.blockID);
		par1World.setBlock(x, y + rand4 - 2, z + 2, Block.wood.blockID);
		par1World.setBlock(x, y + rand4 - 3, z + 2, Block.wood.blockID);
		par1World.setBlock(x, y + rand4 - 3, z + 3, Block.wood.blockID);
		for(int rr4 = y + rand4 - 3; rr4 > 50; rr4--) { 
			int b4 = par1World.getBlockId(x, rr4, z + 3);
			if (b4 != 0 && b4 != Block.grass.blockID && b4 != Block.waterMoving.blockID && b4 != Block.waterStill.blockID && b4 != Block.leaves.blockID && b4 != Block.wood.blockID && b4 != Block.vine.blockID && b4 != Block.tallGrass.blockID) { break; } else { par1World.setBlock(x, rr4, z + 3, Block.wood.blockID); }
		}
	}
	
	private void createRainForestBranch(World par1World, Random par2Random, int x, int y, int z)
	{
		if(par2Random.nextInt(4) == 0)
		{
			par1World.setBlock(x + 1, y, z, Block.wood.blockID);
			par1World.setBlock(x + 2, y + 1, z, Block.wood.blockID);
			par1World.setBlock(x + 3, y + 2, z, Block.wood.blockID);
			par1World.setBlock(x + 4, y + 3, z, Block.wood.blockID);
			int rand = 1 + par2Random.nextInt(5);
			createRainForestLeaves(par1World, par2Random, x + 4, y + 4 + rand, z, 2);
			createRainForestLeaves(par1World, par2Random, x + 4, y + 5 + rand, z, 3);
			createRainForestLeaves(par1World, par2Random, x + 4, y + 6 + rand, z, 2);
			for(int e = 0; e <= rand; e++)
			{
				par1World.setBlock(x + 4, y + 4 + e, z, Block.wood.blockID);
			}
		}
		if(par2Random.nextInt(4) == 0)
		{
			par1World.setBlock(x - 1, y, z, Block.wood.blockID);
			par1World.setBlock(x - 2, y + 1, z, Block.wood.blockID);
			par1World.setBlock(x - 3, y + 2, z, Block.wood.blockID);
			par1World.setBlock(x - 4, y + 3, z, Block.wood.blockID);
			int rand = 1 + par2Random.nextInt(5);
			createRainForestLeaves(par1World, par2Random, x - 4, y + 4 + rand, z, 2);
			createRainForestLeaves(par1World, par2Random, x - 4, y + 5 + rand, z, 3);
			createRainForestLeaves(par1World, par2Random, x - 4, y + 6 + rand, z, 2);
			for(int e = 0; e <= rand; e++)
			{
				par1World.setBlock(x - 4, y + 4 + e, z, Block.wood.blockID);
			}
		}
		if(par2Random.nextInt(4) == 0)
		{
			par1World.setBlock(x, y, z + 1, Block.wood.blockID);
			par1World.setBlock(x, y + 1, z + 2, Block.wood.blockID);
			par1World.setBlock(x, y + 2, z + 3, Block.wood.blockID);
			par1World.setBlock(x, y + 3, z + 4, Block.wood.blockID);
			int rand = 1 + par2Random.nextInt(5);
			createRainForestLeaves(par1World, par2Random, x, y + 4 + rand, z + 4, 2);
			createRainForestLeaves(par1World, par2Random, x, y + 5 + rand, z + 4, 3);
			createRainForestLeaves(par1World, par2Random, x, y + 6 + rand, z + 4, 2);
			for(int e = 0; e <= rand; e++)
			{
				par1World.setBlock(x, y + 4 + e, z + 4, Block.wood.blockID);
			}
		}
		if(par2Random.nextInt(4) == 0)
		{
			par1World.setBlock(x, y, z - 1, Block.wood.blockID);
			par1World.setBlock(x, y + 1, z - 2, Block.wood.blockID);
			par1World.setBlock(x, y + 2, z - 3, Block.wood.blockID);
			par1World.setBlock(x, y + 3, z - 4, Block.wood.blockID);
			int rand = 1 + par2Random.nextInt(5);
			createRainForestLeaves(par1World, par2Random, x, y + 4 + rand, z - 4, 2);
			createRainForestLeaves(par1World, par2Random, x, y + 5 + rand, z - 4, 3);
			createRainForestLeaves(par1World, par2Random, x, y + 6 + rand, z - 4, 2);
			for(int e = 0; e <= rand; e++)
			{
				par1World.setBlock(x, y + 4 + e, z - 4, Block.wood.blockID);
			}
		}
	}
	
	private void createRainForestLeaves(World par1World, Random par2Random, int x, int y, int z, int size)
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
	}
	
    private ItemStack getChestList(int chestid, Random par1Random, World par1World)
    {
		return null;
    }
}
