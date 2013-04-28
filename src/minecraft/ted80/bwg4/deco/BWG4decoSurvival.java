package ted80.bwg4.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4decoSurvival extends WorldGenerator
{
	private int survivalobject;

    public BWG4decoSurvival(int survival)
    {
		survivalobject = survival;
    }

    public boolean generate(World par1World, Random par2Random, int x, int y, int z)
    {
		if(survivalobject == 1)//SURVIVAL ISLAND DIRT BLOCK WITH CHEST
		{
			//DIRT BLOCK
			for (int x1 = 0 + x; x1 <= 2 + x; x1++) 
			{
				for (int z1 = 0 + z; z1 <= 2 + z; z1++)
				{			
					for (int y1 = -2 + y; y1 <= 0 + y; y1++)
					{
						if(y1 == 0 + y)
						{
							par1World.setBlock(x1, y1, z1, Block.grass.blockID);
						}
						else
						{
							par1World.setBlock(x1, y1, z1, Block.dirt.blockID);
						}	
					}
				}
			}
			
			//TREE
			int xx = x + 1;
			int yy = y + 1;
			int zz = z + 1;
			for (int x3 = xx - 2; x3 <= xx + 2; x3++) //Leaves layer 1
			{
				for (int z3 = zz - 2; z3 <= zz + 2; z3++)
				{			
					for (int y3 = yy + 2; y3 <= yy + 3; y3++)
					{
						par1World.setBlock(x3, y3, z3, Block.leaves.blockID);
					}
				}	
			}		
			for (int x4 = xx - 1; x4 <= xx + 1; x4++) //Leaves layer 2
			{
				for (int z4 = zz - 1; z4 <= zz + 1; z4++)
				{			
					par1World.setBlock(x4, yy + 4, z4, Block.leaves.blockID);
				}	
			}		
			for (int y2 = yy; y2 <= yy + 4; y2++) //LOG
			{
				par1World.setBlock(xx, y2, zz, Block.wood.blockID);
			}
			par1World.setBlock(xx + 1, yy + 5, zz, Block.leaves.blockID);
			par1World.setBlock(xx - 1, yy + 5, zz, Block.leaves.blockID);
			par1World.setBlock(xx, yy + 5, zz + 1, Block.leaves.blockID);
			par1World.setBlock(xx, yy + 5, zz - 1, Block.leaves.blockID);
			par1World.setBlock(xx, yy + 5, zz, Block.leaves.blockID);
			
			//CHEST
			par1World.setBlock(xx - 1, yy, zz, Block.chest.blockID);
			TileEntityChest tileentitychest = (TileEntityChest)par1World.getBlockTileEntity(xx - 1, yy, zz);		
			for (int c = 0; c < 20; c++) 
			{ 
				ItemStack itemstack = getChestList(c, 1, par2Random, par1World); 
				if (tileentitychest != null && itemstack != null) 
				{ 
					tileentitychest.setInventorySlotContents(c, itemstack); 
				} 
			}
		}	
		else if(survivalobject == 2)//NETHER TREE
		{
			int var6 = par2Random.nextInt(4) + 6;
			int var7 = 1 + par2Random.nextInt(2);
			int var8 = var6 - var7;
			int var9 = 2 + par2Random.nextInt(2);
			boolean var10 = true;

			if (y >= 1 && y + var6 + 1 <= 256)
			{
				int var11;
				int var13;
				int var15;
				int var21;

				for (var11 = y; var11 <= y + 1 + var6 && var10; ++var11)
				{
					boolean var12 = true;

					if (var11 - y < var7)
					{
						var21 = 0;
					}
					else
					{
						var21 = var9;
					}

					for (var13 = x - var21; var13 <= x + var21 && var10; ++var13)
					{
						for (int var14 = z - var21; var14 <= z + var21 && var10; ++var14)
						{
							if (var11 >= 0 && var11 < 256)
							{
								var15 = par1World.getBlockId(var13, var11, var14);

								if (var15 != 0 && var15 != Block.glowStone.blockID)
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
					var11 = par1World.getBlockId(x, y - 1, z);

					if ((var11 == Block.netherrack.blockID) && y < 256 - var6 - 1)
					{
						this.setBlock(par1World, x, y - 1, z, Block.netherrack.blockID);
						var21 = par2Random.nextInt(2);
						var13 = 1;
						byte var22 = 0;
						int var17;
						int var16;

						for (var15 = 0; var15 <= var8; ++var15)
						{
							var16 = y + var6 - var15;

							for (var17 = x - var21; var17 <= x + var21; ++var17)
							{
								int var18 = var17 - x;

								for (int var19 = z - var21; var19 <= z + var21; ++var19)
								{
									int var20 = var19 - z;

									if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var17, var16, var19)])
									{
										this.setBlockAndMetadata(par1World, var17, var16, var19, Block.glowStone.blockID, 0);
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

						var15 = par2Random.nextInt(3);

						for (var16 = 0; var16 < var6 - var15; ++var16)
						{
							var17 = par1World.getBlockId(x, y + var16, z);

							if (var17 == 0 || var17 == Block.glowStone.blockID)
							{
								this.setBlockAndMetadata(par1World, x, y + var16, z, Block.slowSand.blockID, 0);
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

				if (par1World.isAirBlock(var7, var8, var9) && par1World.getBlockId(var7, var8 - 1, var9) == Block.netherrack.blockID)
				{
					par1World.setBlock(var7, var8 - 1, var9, Block.slowSand.blockID);
					par1World.setBlock(var7, var8, var9, Block.netherStalk.blockID, 1, 0);
				}
			}

			return true;
		}
		else if(survivalobject == 4)//PARADISE PALM TREES
		{
			int ground = par1World.getBlockId(x, y - 1, z);

			if (ground != Block.grass.blockID && ground != Block.sand.blockID)
			{
				return false;
			}
			
			int treeheight = par2Random.nextInt(5) + 6;
		
			for(int s = 0 + y; s < treeheight + y; s++)
			{
				if (par1World.isAirBlock(x, s, z)) { par1World.setBlock(x, s, z, Block.wood.blockID, 3, 0); }
			}
			if (par1World.isAirBlock(x, y + treeheight, z)) { par1World.setBlock(x, y + treeheight, z, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x, y + treeheight - 1, z + 1)) { par1World.setBlock(x, y + treeheight - 1, z + 1, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x, y + treeheight - 1, z - 1)) { par1World.setBlock(x, y + treeheight - 1, z - 1, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x + 1, y + treeheight - 1, z)) { par1World.setBlock(x + 1, y + treeheight - 1, z, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x - 1, y + treeheight - 1, z)) { par1World.setBlock(x - 1, y + treeheight - 1, z, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x, y + treeheight - 1, z + 2)) { par1World.setBlock(x, y + treeheight - 1, z + 2, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x, y + treeheight - 1, z - 2)) { par1World.setBlock(x, y + treeheight - 1, z - 2, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x + 2, y + treeheight - 1, z)) { par1World.setBlock(x + 2, y + treeheight - 1, z, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x - 2, y + treeheight - 1, z)) { par1World.setBlock(x - 2, y + treeheight - 1, z, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x, y + treeheight - 2, z + 3)) { par1World.setBlock(x, y + treeheight - 2, z + 3, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x, y + treeheight - 2, z - 3)) { par1World.setBlock(x, y + treeheight - 2, z - 3, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x + 3, y + treeheight - 2, z)) { par1World.setBlock(x + 3, y + treeheight - 2, z, Block.leaves.blockID, 3, 0); }
			if (par1World.isAirBlock(x - 3, y + treeheight - 2, z)) { par1World.setBlock(x - 3, y + treeheight - 2, z, Block.leaves.blockID, 3, 0); }
			
			return true;
		}
		else if(survivalobject == 5)//SNOW ICE
		{
			int stone = par1World.getBlockId(x, y + 1, z);
			if (stone != Block.stone.blockID) { return false; }
			
			//MAIN
			int length = par2Random.nextInt(7) + 6;
			for(int ice1 = y - length; ice1 < 128; ice1++)
			{	
				if(ice1 < 1) { /* do nothing */ }
				else if (!par1World.isAirBlock(x, ice1, z)) { break; }
				else { par1World.setBlock(x, ice1, z, Block.ice.blockID); }
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
			int xx = x + 5;
			int yy = y + 13;
			int zz = z + 1;
			for (int x3 = xx - 2; x3 <= xx + 2; x3++) //Leaves layer 1
			{
				for (int z3 = zz - 2; z3 <= zz + 2; z3++)
				{			
					for (int y3 = yy + 2; y3 <= yy + 3; y3++)
					{
						par1World.setBlock(x3, y3, z3, Block.leaves.blockID);
					}
				}	
			}		
			for (int x4 = xx - 1; x4 <= xx + 1; x4++) //Leaves layer 2
			{
				for (int z4 = zz - 1; z4 <= zz + 1; z4++)
				{			
					par1World.setBlock(x4, yy + 4, z4, Block.leaves.blockID);
				}	
			}		
			for (int y2 = yy; y2 <= yy + 4; y2++) //LOG
			{
				par1World.setBlock(xx, y2, zz, Block.wood.blockID);
			}
			par1World.setBlock(xx + 1, yy + 5, zz, Block.leaves.blockID);
			par1World.setBlock(xx - 1, yy + 5, zz, Block.leaves.blockID);
			par1World.setBlock(xx, yy + 5, zz + 1, Block.leaves.blockID);
			par1World.setBlock(xx, yy + 5, zz - 1, Block.leaves.blockID);
			par1World.setBlock(xx, yy + 5, zz, Block.leaves.blockID);		
	
			//CREATE BLOCK
			int bx = x - 1;
			int by = y + 10;
			int bz = z - 5;
			int blx, bly, blz;
			for(blx = 0 + bx; blx <= bx + 6 ; blx++)
			{
				for(bly = 0 + by; bly <= by + 2; bly++)
				{
					for(blz = 0 + bz; blz <= bz + 6 ; blz++)
					{
						if(bly == 2 + by)
						{
							par1World.setBlock(blx, bly, blz, Block.grass.blockID);
						}
						else
						{
							par1World.setBlock(blx, bly, blz, Block.dirt.blockID);
						}	
					}
				}
			}
			for(blx = 0 + bx + 3; blx <= bx + 6 ; blx++)
			{
				for(bly = 0 + by; bly <= by + 2; bly++)
				{
					for(blz = 0 + bz; blz <= bz + 3 ; blz++)
					{
						par1World.setBlock(blx, bly, blz, 0);
					}
				}
			}
			
			//ADD CHEST AND BEDROCK
			par1World.setBlock(bx + 1, by, bz + 5, Block.bedrock.blockID);
			par1World.setBlock(bx + 1, by + 3, bz, Block.chest.blockID);
			TileEntityChest tileentitychest = (TileEntityChest)par1World.getBlockTileEntity(bx + 1, by + 3, bz);
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
			par1World.setBlock(x + 1, y + 2, z + 1, Block.cactus.blockID);
			par1World.setBlock(x, y + 2, z, Block.chest.blockID);
			TileEntityChest tileentitychest = (TileEntityChest)par1World.getBlockTileEntity(x, y + 2, z);		
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
							par1World.setBlock(bx, by, bz, Block.sandStone.blockID);
						}
						else
						{
							par1World.setBlock(bx, by, bz, Block.sand.blockID);
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
						par1World.setBlock(bx, by, bz, Block.glowStone.blockID);
					}
				}
			}
			
			//chest and mushrooms
			par1World.setBlock(x, y + 2, z + 1, Block.mushroomBrown.blockID);
			par1World.setBlock(x + 1, y + 2, z, Block.mushroomRed.blockID);
			par1World.setBlock(x - 1, y + 2, z - 1, Block.chest.blockID);
			TileEntityChest tileentitychest = (TileEntityChest)par1World.getBlockTileEntity(x - 1, y + 2, z - 1);		
			for (int c = 0; c < 20; c++) 
			{ 
				ItemStack itemstack = getChestList(c, 4, par2Random, par1World); 
				if (tileentitychest != null && itemstack != null) 
				{ 
					tileentitychest.setInventorySlotContents(c, itemstack); 
				} 
			}
		}
		else if(survivalobject > 19 && survivalobject < 28)
		{
			int ore = 0, size = 1;
			if(survivalobject == 20) { ore = Block.oreCoal.blockID; size = 3; }
			if(survivalobject == 21) { ore = Block.oreIron.blockID; size = 3; }
			if(survivalobject == 22) { ore = Block.oreLapis.blockID; size = 2; }
			if(survivalobject == 23) { ore = Block.oreGold.blockID; size = 2; }
			if(survivalobject == 24) { ore = Block.oreRedstone.blockID; size = 3; }
			if(survivalobject == 26) { ore = Block.oreDiamond.blockID; size = 2; }
			if(survivalobject == 27) { ore = Block.oreEmerald.blockID; size = 2; }
			
			for(int oreX = x; oreX < size + x; oreX++) {
				for(int oreY = y; oreY < size + y; oreY++) {
					for(int oreZ = z; oreZ < size + z; oreZ++) {
						if(par2Random.nextInt(3) != 0) {
							par1World.setBlock(oreX, oreY, oreZ, Block.stone.blockID, 0, 2);
						} else {
							par1World.setBlock(oreX, oreY, oreZ, ore, 0, 2);
						}
					}
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
			if (chestid == 0) { return new ItemStack(Item.seeds, 5); }
			if (chestid == 1) { return new ItemStack(Block.cactus); }
			if (chestid == 2) { return new ItemStack(Item.silk, 2); } 
			if (chestid == 3) { return new ItemStack(Block.mushroomBrown, 1); } 
			if (chestid == 4) { return new ItemStack(Block.mushroomRed, 1); }
			return null;
		}
		if (chesttype == 2)
		{	
			if (chestid == 0) { return new ItemStack(Block.ice); } 
			if (chestid == 1) { return new ItemStack(Item.bucketLava); }
			return null;
		}
		if (chesttype == 3)
		{	
			if (chestid == 0) { return new ItemStack(Block.obsidian, 10); } 
			if (chestid == 1) { return new ItemStack(Item.melon); }
			if (chestid == 2) { return new ItemStack(Item.pumpkinSeeds); }
			return null;
		}
		if (chesttype == 4)
		{	
			if (chestid == 0) { return new ItemStack(Item.seeds, 3); }
			if (chestid == 1) { return new ItemStack(Item.reed); }
			if (chestid == 2) { return new ItemStack(Block.ice); }
			if (chestid == 3) { return new ItemStack(Block.sapling, 1, 3); }
			return null;
		}
		return null;
    }
}
