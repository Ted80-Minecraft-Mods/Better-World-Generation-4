package bwg4.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4decoIsland extends WorldGenerator
{
	private int island;
	
    public BWG4decoIsland(int i)
    {
    	island = i;
    }
    
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		if(island == 1)
		{
			//DIRT + TREE
			for (int x1 = 0 + x; x1 <= 2 + x; x1++) 
			{
				for (int z1 = 0 + z; z1 <= 2 + z; z1++)
				{			
					for (int y1 = -2 + y; y1 <= 0 + y; y1++)
					{
						if(y1 == 0 + y)
						{
							world.setBlock(x1, y1, z1, Block.grass.blockID);
						}
						else
						{
							world.setBlock(x1, y1, z1, Block.dirt.blockID);
						}	
					}
				}
			}
			int xx = x + 1;
			int yy = y + 1;
			int zz = z + 1;
			for (int x3 = xx - 2; x3 <= xx + 2; x3++) 
			{
				for (int z3 = zz - 2; z3 <= zz + 2; z3++)
				{			
					for (int y3 = yy + 2; y3 <= yy + 3; y3++)
					{
						world.setBlock(x3, y3, z3, Block.leaves.blockID);
					}
				}	
			}		
			for (int x4 = xx - 1; x4 <= xx + 1; x4++)
			{
				for (int z4 = zz - 1; z4 <= zz + 1; z4++)
				{			
					world.setBlock(x4, yy + 4, z4, Block.leaves.blockID);
				}	
			}		
			for (int y2 = yy; y2 <= yy + 4; y2++)
			{
				world.setBlock(xx, y2, zz, Block.wood.blockID);
			}
			world.setBlock(xx + 1, yy + 5, zz, Block.leaves.blockID);
			world.setBlock(xx - 1, yy + 5, zz, Block.leaves.blockID);
			world.setBlock(xx, yy + 5, zz + 1, Block.leaves.blockID);
			world.setBlock(xx, yy + 5, zz - 1, Block.leaves.blockID);
			world.setBlock(xx, yy + 5, zz, Block.leaves.blockID);
	
			//GEN CACTI
			for(int i = 0; i < 60; i++)
			{
				int px = -20 + rand.nextInt(40);
				int pz = -20 + rand.nextInt(40);
				int py = world.getHeightValue(px, pz);
				
	            if (world.isAirBlock(px, py, pz))
	            {
	                if (Block.cactus.canBlockStay(world, px, py, pz))
	                {
	                	world.setBlock(px, py, pz, Block.cactus.blockID, 0, 2);
	                	break;
	                }
	            }
			}
			
			//GEN SUGARCANE
			for(int j = 0; j < 80; j++)
			{
				int px = -20 + rand.nextInt(40);
				int pz = -20 + rand.nextInt(40);
	
	            if (world.isAirBlock(px, 65, pz))
	            {
	                if (Block.reed.canBlockStay(world, px, 65, pz))
	                {
	                	world.setBlock(px, 65, pz, Block.reed.blockID, 0, 2);
	                	break;
	                }
	            }
			}
			
			//GEN SHIP WRECK
			for(int s = 0; s < 30; s++)
			{
				int px = -50 + rand.nextInt(100);
				int pz = -50 + rand.nextInt(100);
				if(px > -30 && px < 30 && pz > -30 && pz < 30)
				{
				}
				else
				{
					Material b = world.getBlockMaterial(px, 64, pz);
					
					if(b == Material.water)
					{
						for(int k = 0; k < 15; k++)
						{
							int ex = -4 + rand.nextInt(8);
							int ez = -4 + rand.nextInt(8);
							world.setBlock(px + ex, 64, pz + ez, Block.planks.blockID, 0, 2);
						}
						
						world.setBlock(px, 64, pz, Block.planks.blockID, 0, 2);
						world.setBlock(px, 65, pz, Block.chest.blockID);
						TileEntityChest tileentitychest = (TileEntityChest)world.getBlockTileEntity(px, 65, pz);		
						for (int c = 0; c < 5; c++) 
						{ 
							ItemStack itemstack = getItem(c, rand, world); 
							if (tileentitychest != null && itemstack != null) 
							{ 
								tileentitychest.setInventorySlotContents(c, itemstack); 
							} 
						}
						
						break;
					}
				}
			}
		}
		else if(island == 2)
		{
			//GEN MELON
			boolean done = false;
			for(int m = 0; m < 60; m++)
			{
				int px = -20 + rand.nextInt(40);
				int pz = -20 + rand.nextInt(40);
				int py = world.getHeightValue(px, pz);
				
				int count = 0;
		        for (int var6 = 0; var6 < 100; ++var6)
		        {
		            int var7 = px + rand.nextInt(8) - rand.nextInt(8);
		            int var8 = py + rand.nextInt(4) - rand.nextInt(4);
		            int var9 = pz + rand.nextInt(8) - rand.nextInt(8);
	
		            if (world.isAirBlock(var7, var8, var9) && world.getBlockId(var7, var8 - 1, var9) == Block.grass.blockID && Block.pumpkin.canPlaceBlockAt(world, var7, var8, var9))
		            {
		            	count++;
						world.setBlock(var7, var8, var9, Block.melon.blockID, 0, 0);
						done = true;
						
						if(count > 4)
						{
							break;
						}
		            }
		        }
		        
		        if(done)
		        {
		        	break;
		        }
			}
			
			//GEN CACTI
			for(int i = 0; i < 60; i++)
			{
				int px = -40 + rand.nextInt(80);
				int pz = -40 + rand.nextInt(80);
				int py = world.getHeightValue(px, pz);
				
	            if (world.isAirBlock(px, py, pz))
	            {
	                if (Block.cactus.canBlockStay(world, px, py, pz))
	                {
	                	world.setBlock(px, py, pz, Block.cactus.blockID, 0, 2);
	                	break;
	                }
	            }
			}
			
			//GEN SUGARCANE
			for(int j = 0; j < 80; j++)
			{
				int px = -40 + rand.nextInt(80);
				int pz = -40 + rand.nextInt(80);
	
	            if (world.isAirBlock(px, 65, pz))
	            {
	                if (Block.reed.canBlockStay(world, px, 65, pz))
	                {
	                	world.setBlock(px, 65, pz, Block.reed.blockID, 0, 2);
	                	break;
	                }
	            }
			}
		}
		return true;
	}

    private ItemStack getItem(int chestid, Random par1Random, World par1World)
    {
		if (chestid == 0) { return new ItemStack(Item.seeds, 5); }
		if (chestid == 1) { return new ItemStack(Item.silk, 2); } 
		if (chestid == 2) { return new ItemStack(Block.mushroomBrown, 1); } 
		if (chestid == 3) { return new ItemStack(Block.mushroomRed, 1); }
		return null;
    }
}
