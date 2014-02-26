package bwg4.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecoIsland extends WorldGenerator
{
	private int island;
	
    public DecoIsland(int i)
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
							world.func_147449_b(x1, y1, z1, Blocks.grass);
						}
						else
						{
							world.func_147449_b(x1, y1, z1, Blocks.dirt);
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
						world.func_147449_b(x3, y3, z3, Blocks.leaves);
					}
				}	
			}		
			for (int x4 = xx - 1; x4 <= xx + 1; x4++)
			{
				for (int z4 = zz - 1; z4 <= zz + 1; z4++)
				{			
					world.func_147449_b(x4, yy + 4, z4, Blocks.leaves);
				}	
			}		
			for (int y2 = yy; y2 <= yy + 4; y2++)
			{
				world.func_147449_b(xx, y2, zz, Blocks.log);
			}
			world.func_147449_b(xx + 1, yy + 5, zz, Blocks.leaves);
			world.func_147449_b(xx - 1, yy + 5, zz, Blocks.leaves);
			world.func_147449_b(xx, yy + 5, zz + 1, Blocks.leaves);
			world.func_147449_b(xx, yy + 5, zz - 1, Blocks.leaves);
			world.func_147449_b(xx, yy + 5, zz, Blocks.leaves);

			//GEN CACTI
			for(int i = 0; i < 60; i++)
			{
				int px = -20 + rand.nextInt(40);
				int pz = -20 + rand.nextInt(40);
				int py = world.getHeightValue(px, pz);
				
	            if (world.func_147437_c(px, py, pz))
	            {
	                if (Blocks.cactus.func_149718_j(world, px, py, pz))
	                {
	                	world.func_147465_d(px, py, pz, Blocks.cactus, 0, 2);
	                	break;
	                }
	            }
			}
			
			//GEN SUGARCANE
			for(int j = 0; j < 200; j++)
			{
				int px = -50 + rand.nextInt(100);
				int pz = -50 + rand.nextInt(100);
	
	            if (world.func_147437_c(px, 65, pz))
	            {
	                if (Blocks.reeds.func_149718_j(world, px, 65, pz))
	                {
	                	world.func_147465_d(px, 65, pz, Blocks.reeds, 0, 2);
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
					Material b = world.func_147439_a(px, 64, pz).func_149688_o();
					
					if(b == Material.field_151596_z)
					{
						for(int k = 0; k < 15; k++)
						{
							int ex = -4 + rand.nextInt(8);
							int ez = -4 + rand.nextInt(8);
							world.func_147465_d(px + ex, 64, pz + ez, Blocks.planks, 0, 2);
						}
						
						world.func_147465_d(px, 64, pz, Blocks.planks, 0, 2);
						world.func_147449_b(px, 65, pz, Blocks.chest);
						TileEntityChest tileentitychest = (TileEntityChest)world.func_147438_o(px, 65, pz);		
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
	
		            if (world.func_147437_c(var7, var8, var9) && world.func_147439_a(var7, var8 - 1, var9) == Blocks.grass && Blocks.pumpkin.canPlaceBlockAt(world, var7, var8, var9))
		            {
		            	count++;
						world.func_147465_d(var7, var8, var9, Blocks.melon_block, 0, 0);
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
			
			//GEN PUMPKIN
			done = false;
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
	
		            if (world.func_147437_c(var7, var8, var9) && world.func_147439_a(var7, var8 - 1, var9) == Blocks.grass && Blocks.pumpkin.canPlaceBlockAt(world, var7, var8, var9))
		            {
		            	count++;
						world.func_147465_d(var7, var8, var9, Blocks.pumpkin, 0, 0);
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
				
	            if (world.func_147437_c(px, py, pz))
	            {
	                if (Blocks.cactus.func_149718_j(world, px, py, pz))
	                {
	                	world.func_147465_d(px, py, pz, Blocks.cactus, 0, 2);
	                	break;
	                }
	            }
			}
			
			//GEN SUGARCANE
			for(int j = 0; j < 200; j++)
			{
				int px = -60 + rand.nextInt(120);
				int pz = -60 + rand.nextInt(120);
	
	            if (world.func_147437_c(px, 65, pz))
	            {
	                if (Blocks.reeds.func_149718_j(world, px, 65, pz))
	                {
	                	world.func_147465_d(px, 65, pz, Blocks.reeds, 0, 2);
	                	break;
	                }
	            }
			}
		}
		return true;
	}

    private ItemStack getItem(int chestid, Random par1Random, World par1World)
    {
		if (chestid == 0) { return new ItemStack(Items.wheat_seeds, 5); }
		if (chestid == 1) { return new ItemStack(Items.paper, 2); } 
		if (chestid == 2) { return new ItemStack(Blocks.brown_mushroom, 1); } 
		if (chestid == 3) { return new ItemStack(Blocks.red_mushroom, 1); }
		return null;
    }
}
