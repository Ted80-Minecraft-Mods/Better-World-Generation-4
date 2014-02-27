package bwg4.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecoSurvival extends WorldGenerator
{
	private int survivalobject;

    public DecoSurvival(int survival)
    {
		survivalobject = survival;
    }

    public boolean generate(World par1World, Random par2Random, int x, int y, int z)
    {
		if(survivalobject == 2)//NETHER TREE
		{
			int var6 = par2Random.nextInt(4) + 6;
			int var7 = 1 + par2Random.nextInt(2);
			int var8 = var6 - var7;
			int var9 = 2 + par2Random.nextInt(2);
			boolean var10 = true;

			if (y >= 1 && y + var6 + 1 <= 256)
			{
				for (int var11 = y; var11 <= y + 1 + var6 && var10; ++var11)
				{
					boolean var12 = true;
					int var21;
					
					if (var11 - y < var7)
					{
						var21 = 0;
					}
					else
					{
						var21 = var9;
					}

					for (int var13 = x - var21; var13 <= x + var21 && var10; ++var13)
					{
						for (int var14 = z - var21; var14 <= z + var21 && var10; ++var14)
						{
							if (var11 >= 0 && var11 < 256)
							{
								Block var65 = par1World.getBlock(var13, var11, var14);

								if (var65 != Blocks.air && var65 != Blocks.glowstone)
								{
									var10 = false;
								}
							}
							else
							{
								var10 = false;
							}
						}
					}
				}

				if (!var10)
				{
					return false;
				}
				else
				{
					Block var11 = par1World.getBlock(x, y - 1, z);

					if ((var11 == Blocks.netherrack) && y < 256 - var6 - 1)
					{
						par1World.setBlock(x, y - 1, z, Blocks.netherrack);
						int var21 = par2Random.nextInt(2);
						int var13 = 1;
						byte var22 = 0;
						int var17;
						int var16;

						for (int var78 = 0; var78 <= var8; ++var78)
						{
							var16 = y + var6 - var78;

							for (var17 = x - var21; var17 <= x + var21; ++var17)
							{
								int var18 = var17 - x;

								for (int var19 = z - var21; var19 <= z + var21; ++var19)
								{
									int var20 = var19 - z;

									if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !par1World.getBlock(var17, var16, var19).isOpaqueCube())
									{
										par1World.setBlock(var17, var16, var19, Blocks.glowstone);
									}
								}
							}

							if (var21 >= var13)
							{
								var21 = var22;
								var22 = 1;
								++var13;

								if (var13 > var9)
								{
									var13 = var9;
								}
							}
							else
							{
								++var21;
							}
						}

						int var15 = par2Random.nextInt(3);

						for (var16 = 0; var16 < var6 - var15; ++var16)
						{
							Block var68 = par1World.getBlock(x, y + var16, z);

							if (var68 == Blocks.air || var68 == Blocks.glowstone)
							{
								par1World.setBlock(x, y + var16, z, Blocks.soul_sand);
							}
						}

						return true;
					}
					else
					{
						return false;
					}
				}
			}
			else
			{
				return false;
			}
		}	
		else if(survivalobject == 3)//NETHER WARD
		{
			for (int var6 = 0; var6 < 64; ++var6)
			{
				int var7 = x + par2Random.nextInt(8) - par2Random.nextInt(8);
				int var8 = y + par2Random.nextInt(4) - par2Random.nextInt(4);
				int var9 = z + par2Random.nextInt(8) - par2Random.nextInt(8);

				if (par1World.isAirBlock(var7, var8, var9) && par1World.getBlock(var7, var8 - 1, var9) == Blocks.netherrack)
				{
					par1World.setBlock(var7, var8 - 1, var9, Blocks.soul_sand);
					par1World.setBlock(var7, var8, var9, Blocks.nether_wart, 1, 0);
				}
			}

			return true;
		}
		else if(survivalobject == 4)//PARADISE PALM TREES
		{
			Block ground = par1World.getBlock(x, y - 1, z);

			if (ground != Blocks.grass && ground != Blocks.sand)
			{
				return false;
			}
			
			int treeheight = par2Random.nextInt(8) + 10;
		
			for(int s = 0 + y; s < treeheight + y; s++)
			{
				if (par1World.isAirBlock(x, s, z)) { par1World.setBlock(x, s, z, Blocks.log, 3, 2); }
			}
			
            if (par2Random.nextInt(6) == 0)
            {
                for (int k1 = 0; k1 < 4; ++k1)
                {
                    if (par2Random.nextInt(3) == 0)
                    {
                        int i2 = par2Random.nextInt(3);
                        par1World.setBlock(x + Direction.offsetX[Direction.rotateOpposite[k1]], y + treeheight - 2, z + Direction.offsetZ[Direction.rotateOpposite[k1]], Blocks.cocoa, i2 << 2 | k1, 0);
                    }
                }
            }
			
			int[] leaves = new int[]{
				x, y + treeheight, z,
				x, y + treeheight - 1, z + 1,
				x, y + treeheight - 1, z - 1,
				x + 1, y + treeheight - 1, z,
				x - 1, y + treeheight - 1, z,
				x, y + treeheight - 1, z + 2,
				x, y + treeheight - 1, z - 2,
				x + 2, y + treeheight - 1, z,
				x - 2, y + treeheight - 1, z,
				x, y + treeheight - 2, z + 3,
				x, y + treeheight - 2, z - 3,
				x + 3, y + treeheight - 2, z,
				x - 3, y + treeheight - 2, z
			};
			
			for(int i = 0; i < leaves.length; i+=3)
			{
				if (par1World.isAirBlock(leaves[i], leaves[i + 1], leaves[i + 2])) 
				{ 
					par1World.setBlock(leaves[i], leaves[i + 1], leaves[i + 2], Blocks.leaves, 3, 0); 
				}
			}
			
			return true;
		}
		else if(survivalobject == 5)//SNOW ICE
		{
			Block stone = par1World.getBlock(x, y + 1, z);
			if (stone != Blocks.stone) { return false; }
			
			//MAIN
			int length = par2Random.nextInt(7) + 6;
			for(int ice1 = y - length; ice1 < 128; ice1++)
			{	
				if(ice1 < 1) { /* do nothing */ }
				else if (!par1World.isAirBlock(x, ice1, z)) { break; }
				else { par1World.setBlock(x, ice1, z, Blocks.ice); }
			}
			
			/*
			boolean go = false;
			//1
			for() { if (!par1World.isAirBlock(x, ice21, z)) { go = true; break; } }
			if(go == true) { if (!par1World.isAirBlock(x, ice22, z)) { break; } } go = false;
			
			//2
			//3
			//4
			*/
		}
		else if(survivalobject == 6)//SURVIVAL SKYBLOCK MAIN
		{
			//CREATE TREE
			int size = x;
			x = 0;
			
			int xx = x + 5;
			int yy = y + 13;
			int zz = z + 1;
			for (int x3 = xx - 2; x3 <= xx + 2; x3++) //Leaves layer 1
			{
				for (int z3 = zz - 2; z3 <= zz + 2; z3++)
				{			
					for (int y3 = yy + 2; y3 <= yy + 3; y3++)
					{
						par1World.setBlock(x3, y3, z3, Blocks.leaves);
					}
				}	
			}		
			for (int x4 = xx - 1; x4 <= xx + 1; x4++) //Leaves layer 2
			{
				for (int z4 = zz - 1; z4 <= zz + 1; z4++)
				{			
					par1World.setBlock(x4, yy + 4, z4, Blocks.leaves);
				}	
			}		
			for (int y2 = yy; y2 <= yy + 4; y2++) //LOG
			{
				par1World.setBlock(xx, y2, zz, Blocks.log);
			}
			par1World.setBlock(xx + 1, yy + 5, zz, Blocks.leaves);
			par1World.setBlock(xx - 1, yy + 5, zz, Blocks.leaves);
			par1World.setBlock(xx, yy + 5, zz + 1, Blocks.leaves);
			par1World.setBlock(xx, yy + 5, zz - 1, Blocks.leaves);
			par1World.setBlock(xx, yy + 5, zz, Blocks.leaves);		
	
			//CREATE BLOCK
			int bx = x - 1;
			int by = y + 10;
			int bz = z - 5;
			int blx, bly, blz;

			if(size == 1)
			{
				for(blx = 1 + bx; blx <= bx + 6; blx++)
				{
					for(bly = 0 + by; bly <= by + 2; bly++)
					{
						for(blz = 4 + bz; blz <= bz + 6; blz++)
						{
							if(bly == 2 + by)
							{
								par1World.setBlock(blx, bly, blz, Blocks.grass);
							}
							else
							{
								par1World.setBlock(blx, bly, blz, Blocks.dirt);
							}	
						}
					}
				}
			}
			else
			{
				for(blx = 0 + bx; blx <= bx + 6 ; blx++)
				{
					for(bly = 0 + by; bly <= by + 2; bly++)
					{
						for(blz = 0 + bz; blz <= bz + 6 ; blz++)
						{
							if(bly == 2 + by)
							{
								par1World.setBlock(blx, bly, blz, Blocks.grass);
							}
							else
							{
								par1World.setBlock(blx, bly, blz, Blocks.dirt);
							}	
						}
					}
				}
				if(size != 3)
				{
					for(blx = 0 + bx + 3; blx <= bx + 6 ; blx++)
					{
						for(bly = 0 + by; bly <= by + 2; bly++)
						{
							for(blz = 0 + bz; blz <= bz + 3 ; blz++)
							{
								par1World.setBlock(blx, bly, blz, Blocks.air);
							}
						}
					}
				}
			}
			
			//ADD CHEST AND BEDROCK
			int cz = bz; if(size == 1) { cz += 4; }
			par1World.setBlock(bx + 1, by, bz + 5, Blocks.bedrock);
			par1World.setBlock(bx + 1, by + 3, cz, Blocks.chest);
			TileEntityChest tileentitychest = (TileEntityChest)par1World.getTileEntity(bx + 1, by + 3, cz);
			for (int c = 0; c < 20; c++) 
			{ 
				ItemStack itemstack = getChestList(c, 2, par2Random, par1World); 
				if (tileentitychest != null && itemstack != null) 
				{ 
					tileentitychest.setInventorySlotContents(c, itemstack); 
				} 
			}			
		}
		else if(survivalobject == 7)//SURVIVAL SKYBLOCK DESERT
		{
			//ADD CHEST AND CACTI
			par1World.setBlock(x + 1, y + 2, z + 1, Blocks.cactus);
			par1World.setBlock(x, y + 2, z, Blocks.chest);
			TileEntityChest tileentitychest = (TileEntityChest)par1World.getTileEntity(x, y + 2, z);		
			for (int c = 0; c < 20; c++) 
			{ 
				ItemStack itemstack = getChestList(c, 3, par2Random, par1World); 
				if (tileentitychest != null && itemstack != null) 
				{ 
					tileentitychest.setInventorySlotContents(c, itemstack); 
				} 
			}
		
			//CREATE BLOCK
			for(int bx = -1 + x; bx <= 1 + x ;bx++)
			{
				for(int by = -2 + y; by <= 1 + y ;by++)
				{
					for(int bz = -1 + z; bz <= 1 + z;bz++)
					{
						if(by == -2 + y)
						{
							par1World.setBlock(bx, by, bz, Blocks.sandstone);
						}
						else
						{
							par1World.setBlock(bx, by, bz, Blocks.sand);
						}	
					}
				}
			}
		}
		else if(survivalobject == 8)//SURVIVAL SKYBLOCK NETHER
		{
			//glowstone
			for(int bx = -1 + x; bx <= 1 + x ;bx++)
			{
				for(int by = -1 + y; by <= 1 + y ;by++)
				{
					for(int bz = -1 + z; bz <= 1 + z;bz++)
					{
						par1World.setBlock(bx, by, bz, Blocks.glowstone);
					}
				}
			}
			
			//chest and mushrooms
			par1World.setBlock(x, y + 2, z + 1, Blocks.brown_mushroom);
			par1World.setBlock(x + 1, y + 2, z, Blocks.red_mushroom);
			par1World.setBlock(x - 1, y + 2, z - 1, Blocks.chest);
			TileEntityChest tileentitychest = (TileEntityChest)par1World.getTileEntity(x - 1, y + 2, z - 1);		
			for (int c = 0; c < 20; c++) 
			{ 
				ItemStack itemstack = getChestList(c, 4, par2Random, par1World); 
				if (tileentitychest != null && itemstack != null) 
				{ 
					tileentitychest.setInventorySlotContents(c, itemstack); 
				} 
			}
		}
		else if(survivalobject == 12)
		{
			for(int i = x - 1; i < 2 + x; i++)
			{
				for(int k = z - 1; k < 2 + z; k++)
				{
					for(int j = y; j < 4 + y; j++)
					{
						if(j == 3 + y) 
						{ 
							par1World.setBlock(i, j, k, Blocks.nether_wart); 
						} 
						else 
						{ 
							par1World.setBlock(i, j, k, Blocks.soul_sand); 
						}
					}
				}
			}
		}
		else if(survivalobject == 13) //SURVIVAL CAVE CHEST
		{
			for(int i = 63; i > 1; i--)
			{
				if(par1World.getBlock(x, i, z) != Blocks.air)
				{
					par1World.setBlock(x, i + 1, z, Blocks.chest); 
					par1World.setBlock(x, i, z, Blocks.torch, 0, 3); 
					par1World.setBlock(x, i - 1, z, Blocks.stone); 
					
					TileEntityChest tileentitychest = (TileEntityChest)par1World.getTileEntity(x, i + 1, z);
					for (int c = 0; c < 20; c++) 
					{ 
						ItemStack itemstack = getChestList(c, 8, par2Random, par1World); 
						if (tileentitychest != null && itemstack != null) 
						{ 
							tileentitychest.setInventorySlotContents(c, itemstack); 
						} 
					}	
					break;
				}
			}
		}
		else
		{
		}
		return true;
	}
	
    private ItemStack getChestList(int chestid, int chesttype, Random par1Random, World par1World)
    {
		if (chesttype == 1)
		{	
			if (chestid == 0) { return new ItemStack(Items.wheat_seeds, 5); }
			if (chestid == 1) { return new ItemStack(Blocks.cactus); }
			if (chestid == 2) { return new ItemStack(Items.paper, 2); } 
			if (chestid == 3) { return new ItemStack(Blocks.brown_mushroom, 1); } 
			if (chestid == 4) { return new ItemStack(Blocks.red_mushroom, 1); }
			return null;
		}
		if (chesttype == 2)
		{	
			if (chestid == 0) { return new ItemStack(Blocks.ice); } 
			if (chestid == 1) { return new ItemStack(Items.lava_bucket); }
			return null;
		}
		if (chesttype == 3)
		{	
			if (chestid == 0) { return new ItemStack(Blocks.obsidian, 10); } 
			if (chestid == 1) { return new ItemStack(Items.melon); }
			if (chestid == 2) { return new ItemStack(Items.pumpkin_seeds); }
			return null;
		}
		if (chesttype == 4)
		{	
			if (chestid == 0) { return new ItemStack(Items.wheat_seeds, 3); }
			if (chestid == 1) { return new ItemStack(Items.reeds); }
			if (chestid == 2) { return new ItemStack(Blocks.ice); }
			if (chestid == 3) { return new ItemStack(Blocks.sapling, 1, 3); }
			return null;
		}
		if (chesttype == 5)
		{	
			if (chestid == 0) { return new ItemStack(Blocks.sapling, 1, 1); }
			if (chestid == 1) { return new ItemStack(Blocks.snow, 2); }
			return null;
		}
		if (chesttype == 6)
		{	
			if (chestid == 0) { return new ItemStack(Blocks.sapling, 1, 2); }
			if (chestid == 1) { return new ItemStack(Items.reeds, 1); }
			if (chestid == 2) { return new ItemStack(Items.spawn_egg, 2, 92); }
			return null;
		}
		if (chesttype == 7)
		{	
			if (chestid == 0) { return new ItemStack(Items.potato, 1); }
			if (chestid == 1) { return new ItemStack(Items.carrot, 1); }
			if (chestid == 2) { return new ItemStack(Items.spawn_egg, 1, 93); }
			return null;
		}
		if (chesttype == 8)
		{	
			if (chestid == 0) { return new ItemStack(Items.wooden_sword, 1); }
			if (chestid == 1) { return new ItemStack(Blocks.sapling, 1); }
			if (chestid == 2) { return new ItemStack(Blocks.torch, 8); }
			if (chestid == 3) { return new ItemStack(Items.wooden_pickaxe, 1); }
			if (chestid == 4) { return new ItemStack(Blocks.log, 4); }
			if (chestid == 5) { return new ItemStack(Items.wheat_seeds, 8); }
			return null;
		}
		return null;
    }
}
