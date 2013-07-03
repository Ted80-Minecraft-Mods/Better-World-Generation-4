package bwg4.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;

public class BWG4decoDungeons extends WorldGenerator
{
	public int chestID = 0; 
	public boolean checkpos = true;
	public boolean isSkyDungeon = false;
	public boolean isEndDungeon = false;
	public boolean specialChest = false;
	
    public BWG4decoDungeons(int chest, boolean check, boolean sky, boolean end)
    {
		chestID = chest;
		checkpos = check;
		isSkyDungeon = sky;
		isEndDungeon = end;
		
		if(chest == 9 || chest == 10)
		{
			specialChest = true;
		}
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
		if(isSkyDungeon || isEndDungeon)
		{
			boolean chain1 = false, chain2 = false, chain3 = false, chain4 = false;
		
			//Check for chains
			for(int ch1 = par4; ch1 < 60; ch1++) { if(!par1World.isAirBlock(par3 + 4, ch1 + 5, par5 + 4)) { chain1 = true; break; } }
			for(int ch2 = par4; ch2 < 60; ch2++) { if(!par1World.isAirBlock(par3 + 4, ch2 + 5, par5 - 4)) { chain2 = true; break; } }
			for(int ch3 = par4; ch3 < 60; ch3++) { if(!par1World.isAirBlock(par3 - 4, ch3 + 5, par5 + 4)) { chain3 = true; break; } }
			for(int ch4 = par4; ch4 < 60; ch4++) { if(!par1World.isAirBlock(par3 - 4, ch4 + 5, par5 - 4)) { chain4 = true; break; } }
			
			if(chain1 == false || chain2 == false || chain3 == false || chain4 == false )
			{
				return false;
			}
			
			//Build Chains
			for(int chy1 = par4 + 5; chy1 < 70 ; chy1++) { if(!par1World.isAirBlock(par3 + 4, chy1, par5 + 4)) { break; } par1World.setBlock(par3 + 4, chy1, par5 + 4, Block.fenceIron.blockID); }		
			for(int chy2 = par4 + 5; chy2 < 70 ; chy2++) { if(!par1World.isAirBlock(par3 + 4, chy2, par5 - 4)) { break; } par1World.setBlock(par3 + 4, chy2, par5 - 4, Block.fenceIron.blockID); }		
			for(int chy3 = par4 + 5; chy3 < 70 ; chy3++) { if(!par1World.isAirBlock(par3 - 4, chy3, par5 + 4)) { break; } par1World.setBlock(par3 - 4, chy3, par5 + 4, Block.fenceIron.blockID); }		
			for(int chy4 = par4 + 5; chy4 < 70 ; chy4++) { if(!par1World.isAirBlock(par3 - 4, chy4, par5 - 4)) { break; } par1World.setBlock(par3 - 4, chy4, par5 - 4, Block.fenceIron.blockID); }	
			if(checkpos)
			{	
				par1World.setBlock(par3 + 4, par4 + 6, par5 + 4, Block.netherBrick.blockID);
				par1World.setBlock(par3 + 4, par4 + 6, par5 - 4, Block.netherBrick.blockID);
				par1World.setBlock(par3 - 4, par4 + 6, par5 + 4, Block.netherBrick.blockID);
				par1World.setBlock(par3 - 4, par4 + 6, par5 - 4, Block.netherBrick.blockID);
			}
			else
			{
				par1World.setBlock(par3 + 4, par4 + 6, par5 + 4, Block.cobblestoneMossy.blockID);
				par1World.setBlock(par3 + 4, par4 + 6, par5 - 4, Block.cobblestoneMossy.blockID);
				par1World.setBlock(par3 - 4, par4 + 6, par5 + 4, Block.cobblestoneMossy.blockID);
				par1World.setBlock(par3 - 4, par4 + 6, par5 - 4, Block.cobblestoneMossy.blockID);
			}	
			
			//Build SkyDungeon
			for(int x1 = par3 - 4; x1 < par3 + 5 ; x1++)
			{
				for(int y1 = par4; y1 < par4 + 6 ; y1++)
				{
					for(int z1 = par5 - 4; z1 < par5 + 5 ; z1++)
					{
						if (par2Random.nextInt(10) == 0)
						{
							par1World.setBlock(x1, y1, z1, 0);
						}
						else
						{
							if(checkpos)
							{
								par1World.setBlock(x1, y1, z1, Block.netherBrick.blockID);
							}
							else
							{
								if (par2Random.nextInt(2) != 0)
								{
									par1World.setBlock(x1, y1, z1, Block.cobblestoneMossy.blockID);
								}
								else
								{
									par1World.setBlock(x1, y1, z1, Block.cobblestone.blockID);
								}
							}
						}
					}
				}
			}
			
			//Fill with Air
			for(int x2 = par3 - 3; x2 < par3 + 4 ; x2++)
			{
				for(int y2 = par4 + 1; y2 < par4 + 5 ; y2++)
				{
					for(int z2 = par5 - 3; z2 < par5 + 4 ; z2++)
					{
						par1World.setBlock(x2, y2, z2, 0);
					}
				}
			}
			
			//Chests and Spawners
			if(isEndDungeon)
			{
				par1World.setBlock(par3 + 2, par4 + 1, par5 - 1, Block.endPortalFrame.blockID, 1 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.setBlock(par3 + 2, par4 + 1, par5 + 0, Block.endPortalFrame.blockID, 1 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.setBlock(par3 + 2, par4 + 1, par5 + 1, Block.endPortalFrame.blockID, 1 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				
				par1World.setBlock(par3 - 1, par4 + 1, par5 + 2, Block.endPortalFrame.blockID, 2 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.setBlock(par3 + 0, par4 + 1, par5 + 2, Block.endPortalFrame.blockID, 2 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.setBlock(par3 + 1, par4 + 1, par5 + 2, Block.endPortalFrame.blockID, 2 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				
				par1World.setBlock(par3 - 2, par4 + 1, par5 - 1, Block.endPortalFrame.blockID, 3 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.setBlock(par3 - 2, par4 + 1, par5 + 0, Block.endPortalFrame.blockID, 3 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.setBlock(par3 - 2, par4 + 1, par5 + 1, Block.endPortalFrame.blockID, 3 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				
				par1World.setBlock(par3 - 1, par4 + 1, par5 - 2, Block.endPortalFrame.blockID, 0 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.setBlock(par3 + 0, par4 + 1, par5 - 2, Block.endPortalFrame.blockID, 0 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.setBlock(par3 + 1, par4 + 1, par5 - 2, Block.endPortalFrame.blockID, 0 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);

				par1World.setBlock(par3, par4 + 6, par5, Block.mobSpawner.blockID);
				TileEntityMobSpawner spawn3 = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4 + 6, par5);
				if (spawn3 != null) { spawn3.func_98049_a().setMobID(this.pickMobSpawner(par2Random)); }
			}
			else
			{
				par1World.setBlock(par3, par4 + 1, par5, Block.mobSpawner.blockID);
				par1World.setBlock(par3, par4 + 3, par5, Block.mobSpawner.blockID);
				par1World.setBlock(par3, par4 + 6, par5, Block.mobSpawner.blockID);
				TileEntityMobSpawner spawn1 = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4 + 1, par5);
				TileEntityMobSpawner spawn2 = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4 + 3, par5);
				TileEntityMobSpawner spawn3 = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4 + 6, par5);
				if (spawn1 != null) { spawn1.func_98049_a().setMobID(this.pickMobSpawner(par2Random)); }
				if (spawn2 != null) { spawn2.func_98049_a().setMobID(this.pickMobSpawner(par2Random)); }
				if (spawn3 != null) { spawn3.func_98049_a().setMobID(this.pickMobSpawner(par2Random)); }
				
				par1World.setBlock(par3, par4 + 2, par5, Block.chest.blockID);
				TileEntityChest var16 = (TileEntityChest)par1World.getBlockTileEntity(par3, par4 + 2, par5);
				
				if(specialChest == true)
				{
					for (int var17 = 0; var17 < 20; ++var17)
					{
						ItemStack var18 = this.pickCheckLootItem(par2Random, var17);

						if (var18 != null)
						{
							var16.setInventorySlotContents(var17, var18);
						}
					}
				}
				else
				{
					for (int var17 = 0; var17 < 14; ++var17)
					{
						ItemStack var18 = this.pickCheckLootItem(par2Random, 0);

						if (var18 != null)
						{
							var16.setInventorySlotContents(par2Random.nextInt(var16.getSizeInventory()), var18);
						}
					}
				}	
			}
		}
		else
		{
			byte var6 = 3;
			int var7 = par2Random.nextInt(2) + 2;
			int var8 = par2Random.nextInt(2) + 2;
			int var9 = 0;
			int var10;
			int var11;
			int var12;
			
			if(checkpos)
			{
				for (var10 = par3 - var7 - 1; var10 <= par3 + var7 + 1; ++var10)
				{
					for (var11 = par4 - 1; var11 <= par4 + var6 + 1; ++var11)
					{
						for (var12 = par5 - var8 - 1; var12 <= par5 + var8 + 1; ++var12)
						{
							Material var13 = par1World.getBlockMaterial(var10, var11, var12);

							if (var11 == par4 - 1 && !var13.isSolid())
							{
								return false;
							}

							if (var11 == par4 + var6 + 1 && !var13.isSolid())
							{
								return false;
							}

							if ((var10 == par3 - var7 - 1 || var10 == par3 + var7 + 1 || var12 == par5 - var8 - 1 || var12 == par5 + var8 + 1) && var11 == par4 && par1World.isAirBlock(var10, var11, var12) && par1World.isAirBlock(var10, var11 + 1, var12))
							{
								++var9;
							}
						}
					}
				}
			}

			if ((var9 >= 1 && var9 <= 5) || !checkpos)
			{
				for (var10 = par3 - var7 - 1; var10 <= par3 + var7 + 1; ++var10)
				{
					for (var11 = par4 + var6; var11 >= par4 - 1; --var11)
					{
						for (var12 = par5 - var8 - 1; var12 <= par5 + var8 + 1; ++var12)
						{
							if (var10 != par3 - var7 - 1 && var11 != par4 - 1 && var12 != par5 - var8 - 1 && var10 != par3 + var7 + 1 && var11 != par4 + var6 + 1 && var12 != par5 + var8 + 1)
							{
								par1World.setBlock(var10, var11, var12, 0);
							}
							else if (var11 >= 0 && !par1World.getBlockMaterial(var10, var11 - 1, var12).isSolid())
							{
								par1World.setBlock(var10, var11, var12, 0);
							}
							else if (par1World.getBlockMaterial(var10, var11, var12).isSolid())
							{
								if (var11 == par4 - 1 && par2Random.nextInt(4) != 0)
								{
									par1World.setBlock(var10, var11, var12, Block.cobblestoneMossy.blockID);
								}
								else
								{
									par1World.setBlock(var10, var11, var12, Block.cobblestone.blockID);
								}
							}
						}
					}
				}

				var10 = 0;
				if(specialChest == true) { var10 = 1; }

				while (var10 < 2)
				{
					var11 = 0;

					while (true)
					{
						if (var11 < 3)
						{
							label210:
							{
								var12 = par3 + par2Random.nextInt(var7 * 2 + 1) - var7;
								int var14 = par5 + par2Random.nextInt(var8 * 2 + 1) - var8;

								if (par1World.isAirBlock(var12, par4, var14))
								{
									int var15 = 0;

									if (par1World.getBlockMaterial(var12 - 1, par4, var14).isSolid())
									{
										++var15;
									}

									if (par1World.getBlockMaterial(var12 + 1, par4, var14).isSolid())
									{
										++var15;
									}

									if (par1World.getBlockMaterial(var12, par4, var14 - 1).isSolid())
									{
										++var15;
									}

									if (par1World.getBlockMaterial(var12, par4, var14 + 1).isSolid())
									{
										++var15;
									}
									
									if(specialChest == true) 
									{
										var15 = 1;
									}

									if (var15 == 1)
									{
										par1World.setBlock(var12, par4, var14, Block.chest.blockID);
										TileEntityChest var16 = (TileEntityChest)par1World.getBlockTileEntity(var12, par4, var14);

										if (var16 != null)
										{
											if(specialChest == true)
											{
												for (int var17 = 0; var17 < 20; ++var17)
												{
													ItemStack var18 = this.pickCheckLootItem(par2Random, var17);

													if (var18 != null)
													{
														var16.setInventorySlotContents(var17, var18);
													}
												}
											}
											else
											{
												for (int var17 = 0; var17 < 14; ++var17)
												{
													ItemStack var18 = this.pickCheckLootItem(par2Random, 0);

													if (var18 != null)
													{
														var16.setInventorySlotContents(par2Random.nextInt(var16.getSizeInventory()), var18);
													}
												}
											}
										}

										break label210;
									}
								}

								++var11;
								continue;
							}
						}

						++var10;
						break;
					}
				}

				par1World.setBlock(par3, par4, par5, Block.mobSpawner.blockID);
				TileEntityMobSpawner var19 = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4, par5);

				if (var19 != null)
				{
					var19.func_98049_a().setMobID(this.pickMobSpawner(par2Random));
				}
				else
				{
					System.err.println("Failed to fetch mob spawner entity at (" + par3 + ", " + par4 + ", " + par5 + ")");
				}

				return true;
			}
			else
			{
				return false;
			}
		}	
		return false;
    }
	
    private ItemStack pickCheckLootItem(Random par1Random, int listitem)
    {
		//CHEST ID'S
		//X default = 0
		//X beta1 = 1 beta
		//X beta2 = 2 default
		//X alpha = 3
		//X infdev = 4
		//X indev1 = 5 inland
		//X indev2 = 6 floating
		//X gold = 7
		//X island = 8
		//X islandspecial1 = 9
		//X islandspecial2 = 10
		//X skyland = 11
		//X sky1 = 12 default
		//X sky2 = 13 beta
		//- cave1 = 14 biomes
		//- cave2 = 15 stone
		//- cave3 = 16 desert
		//X skydimnether = 17
		//X skylandnether = 18
	
		if(chestID == 0)//DEFAULT LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); } 
			if (i == 1 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 2 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 3 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 4 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 5) { return new ItemStack(Item.saddle); }
			if (i == 6) { return new ItemStack(Item.gunpowder, par1Random.nextInt(4) + 1); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.goldenCarrot); }
			if (i == 8 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.appleGold); }
			if (i == 9) { return new ItemStack(Item.ingotIron, par1Random.nextInt(4) + 1); }
			if (i == 10) { return new ItemStack(Item.bread); }
			if (i == 11) { return new ItemStack(Item.wheat, par1Random.nextInt(4) + 1); }
			if (i == 12) { return new ItemStack(Item.silk, par1Random.nextInt(4) + 1); }
			if (i == 13) { return new ItemStack(Item.bucketEmpty); }
			if (i == 14) { return new ItemStack(Item.redstone, par1Random.nextInt(4) + 1); }
			if (i == 15) { return new ItemStack(Item.dyePowder, 1, 3); }
			if (i > 16 || i < 16 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 1)//BETA1 LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 8) { return new ItemStack(Item.saddle); }
			if (i == 9 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); }
			if (i == 10 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 11 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 12 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 13) { return new ItemStack(Block.sapling, 1, 3); }
			if (i == 15) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i > 15 || i < 15 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 2)//BETA2 LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 3 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 4) { return new ItemStack(Item.saddle); }
			if (i == 5 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 8 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 10) { return new ItemStack(Item.bucketEmpty); }
			if (i == 11) { return new ItemStack(Item.redstone, par1Random.nextInt(4) + 1); }
			if (i == 12) { return new ItemStack(Item.wheat, par1Random.nextInt(4) + 1); }
			if (i == 13) { return new ItemStack(Item.gunpowder, par1Random.nextInt(4) + 1); }
			if (i == 14) { return new ItemStack(Item.ingotIron, par1Random.nextInt(4) + 1); }
			if (i == 15) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i > 15 || i < 15 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 3)//ALPHA LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 8) { return new ItemStack(Item.saddle); }
			if (i == 9 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); }
			if (i == 10 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 11 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 12 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 13) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
			if (i == 15 || i == 16 || i == 17) { return new ItemStack(Item.seeds, par1Random.nextInt(12) + 1); }
			if (i == 18) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i > 18 || i < 18 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 4)//INFDEV LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 8) { return new ItemStack(Item.saddle); }
			if (i == 9 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); }
			if (i == 10 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 11 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 12 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 13) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
			if (i == 15 || i == 16 || i == 17) { return new ItemStack(Item.seeds, par1Random.nextInt(12) + 1); }
			if (i == 18) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i == 19) { return new ItemStack(Block.cactus, par1Random.nextInt(4) + 1); }
			if (i == 20) { return new ItemStack(Item.reed, par1Random.nextInt(4) + 1); }
			if (i == 21) { return new ItemStack(Block.blockSnow, par1Random.nextInt(12) + 1); }
			if (i > 21 || i < 21 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 5)//INDEV1 LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 8) { return new ItemStack(Item.saddle); }
			if (i == 9 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); }
			if (i == 10 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 11 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 12 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 13) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
			if (i == 15 || i == 16 || i == 17) { return new ItemStack(Item.seeds, par1Random.nextInt(12) + 1); }
			if (i == 18) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i == 19) { return new ItemStack(Block.cactus, par1Random.nextInt(4) + 1); }
			if (i == 20) { return new ItemStack(Item.reed, par1Random.nextInt(4) + 1); }
			if (i == 21) { return new ItemStack(Item.brick, par1Random.nextInt(63) + 1); }
			if (i == 22) { return new ItemStack(Block.blockSnow, par1Random.nextInt(12) + 1); }
			if (i > 22 || i < 22 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 6)//INDEV2 LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 8) { return new ItemStack(Item.saddle); }
			if (i == 9 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); }
			if (i == 10 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 11 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 12 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 13) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
			if (i == 15 || i == 16 || i == 17) { return new ItemStack(Item.seeds, par1Random.nextInt(12) + 1); }
			if (i == 18) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i == 19) { return new ItemStack(Block.cactus, par1Random.nextInt(4) + 1); }
			if (i == 20) { return new ItemStack(Item.reed, par1Random.nextInt(4) + 1); }
			if (i == 21) { return new ItemStack(Item.brick, par1Random.nextInt(63) + 1); }
			if (i == 22) { return new ItemStack(Block.blockSnow, par1Random.nextInt(12) + 1); }
			if (i > 22 || i < 22 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 7)//GOLD LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); } 
			if (i == 1 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 2 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 3 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 4 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 5) { return new ItemStack(Item.saddle); }
			if (i == 6) { return new ItemStack(Item.gunpowder, par1Random.nextInt(4) + 1); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.goldenCarrot); }
			if (i == 8 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.appleGold); }
			if (i == 9) { return new ItemStack(Item.ingotIron, par1Random.nextInt(4) + 1); }
			if (i == 10) { return new ItemStack(Item.bread); }
			if (i == 11) { return new ItemStack(Item.wheat, par1Random.nextInt(4) + 1); }
			if (i == 12) { return new ItemStack(Item.silk, par1Random.nextInt(4) + 1); }
			if (i == 13) { return new ItemStack(Item.bucketEmpty); }
			if (i == 14) { return new ItemStack(Item.redstone, par1Random.nextInt(4) + 1); }
			if (i == 15) { return new ItemStack(Item.dyePowder, 1, 3); }
			if (i == 17 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i > 17 || i < 17 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 8)//ISLAND LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); } 
			if (i == 1 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 2 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 3 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 4 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 5) { return new ItemStack(Item.saddle); }
			if (i == 6) { return new ItemStack(Item.gunpowder, par1Random.nextInt(4) + 1); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.goldenCarrot); }
			if (i == 8 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.appleGold); }
			if (i == 9) { return new ItemStack(Item.ingotIron, par1Random.nextInt(4) + 1); }
			if (i == 10) { return new ItemStack(Item.bread); }
			if (i == 11) { return new ItemStack(Item.wheat, par1Random.nextInt(4) + 1); }
			if (i == 12) { return new ItemStack(Item.silk, par1Random.nextInt(4) + 1); }
			if (i == 13) { return new ItemStack(Item.bucketEmpty); }
			if (i == 14) { return new ItemStack(Item.redstone, par1Random.nextInt(4) + 1); }
			if (i == 15) { return new ItemStack(Item.dyePowder, 1, 3); }
			if (i == 17 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.monsterPlacer, 1, 95); }
			if (i == 18 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.monsterPlacer, 1, 98); }
			if (i > 18 || i < 18 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }		
		} 
		else if(chestID == 9)//ISLAND1 LOOT
		{
			if(listitem == 0) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(2) + 1); }
			if(listitem == 1) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(2) + 1); }
			if(listitem == 2) { return new ItemStack(Block.cactus, par1Random.nextInt(4) + 1); }
			if(listitem == 3) { return new ItemStack(Item.reed, par1Random.nextInt(4) + 1); }
			if(listitem == 4) { return new ItemStack(Item.seeds, 12); }
			if(listitem == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if(listitem == 6) { return new ItemStack(Item.monsterPlacer, 2, 93); }
			if(listitem == 7) { return new ItemStack(Item.monsterPlacer, 2, 91); }
			else { return null; }		
		} 
		else if(chestID == 10)//ISLAND2 LOOT
		{
			if(listitem == 0) { return new ItemStack(Item.potato, par1Random.nextInt(2) + 1); }
			if(listitem == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(2) + 1); }
			if(listitem == 2) { return new ItemStack(Block.blockSnow, par1Random.nextInt(12) + 1); }
			if(listitem == 3) { return new ItemStack(Block.mycelium); }
			if(listitem == 4) { return new ItemStack(Item.monsterPlacer, 2, 92); }
			if(listitem == 5) { return new ItemStack(Item.monsterPlacer, 2, 90); }
			if(listitem == 6) { return new ItemStack(Item.monsterPlacer, 2, 120); }
			else { return null; }		
		} 
		else if(chestID == 11)//SKYLAND LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0) { return new ItemStack(Block.cactus, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.reed, par1Random.nextInt(4) + 1); }
			if (i == 2) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
			if (i == 3 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); }
			if (i == 4 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 5 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 8) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
			if (i == 9) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
			if (i == 10) { return new ItemStack(Item.bread); }
			if (i == 11) { return new ItemStack(Item.wheat, par1Random.nextInt(4) + 1); }
			if (i == 12) { return new ItemStack(Item.silk, par1Random.nextInt(4) + 1); }
			if (i == 13) { return new ItemStack(Item.bucketEmpty); }
			if (i == 14) { return new ItemStack(Item.redstone, par1Random.nextInt(4) + 1); }
			if (i == 15) { return new ItemStack(Item.saddle); }
			if (i > 15 || i < 15 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 12)//SKY1 LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 8) { return new ItemStack(Item.saddle); }
			if (i == 9 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); }
			if (i == 10 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 11 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 12 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 13) { return new ItemStack(Block.sapling, 1, 3); }
			if (i == 15) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i > 15 || i < 15 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 13)//SKY2 LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 3 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 4) { return new ItemStack(Item.saddle); }
			if (i == 5 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 8 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 10) { return new ItemStack(Item.bucketEmpty); }
			if (i == 11) { return new ItemStack(Item.redstone, par1Random.nextInt(4) + 1); }
			if (i == 12) { return new ItemStack(Item.wheat, par1Random.nextInt(4) + 1); }
			if (i == 13) { return new ItemStack(Item.gunpowder, par1Random.nextInt(4) + 1); }
			if (i == 14) { return new ItemStack(Item.ingotIron, par1Random.nextInt(4) + 1); }
			if (i == 15) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i > 15 || i < 15 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
		} 
		else if(chestID == 17)//SKYNETHER
		{
			int i = par1Random.nextInt(30);
			if (i == 0 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.helmetChain); } 
			if (i == 1 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.plateChain); }
			if (i == 2 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.legsChain); }
			if (i == 3 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.bootsChain); }
			if (i == 4 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 5) { return new ItemStack(Item.saddle); }
			if (i == 6) { return new ItemStack(Item.gunpowder, par1Random.nextInt(4) + 1); }
			if (i == 7 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.goldenCarrot); }
			if (i == 8 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.appleGold); }
			if (i == 9) { return new ItemStack(Item.ingotIron, par1Random.nextInt(4) + 1); }
			if (i == 10) { return new ItemStack(Item.bread); }
			if (i == 11) { return new ItemStack(Item.wheat, par1Random.nextInt(4) + 1); }
			if (i == 12) { return new ItemStack(Item.silk, par1Random.nextInt(4) + 1); }
			if (i == 13) { return new ItemStack(Item.bucketEmpty); }
			if (i == 14) { return new ItemStack(Item.redstone, par1Random.nextInt(4) + 1); }
			if (i == 15) { return new ItemStack(Item.dyePowder, 1, 3); }
			if (i > 16 || i < 16 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }		
		} 
		else if(chestID == 18)//SKYLAND LOOT
		{
			int i = par1Random.nextInt(30);
			if (i == 1) { return new ItemStack(Item.brick, par1Random.nextInt(63) + 1); }
			if (i == 2) { return new ItemStack(Block.blockSnow, par1Random.nextInt(12) + 1); }
			if (i == 3) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i == 5 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.itemsList[Item.record13.itemID + par1Random.nextInt(10)]); }
			if (i == 7) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if (i == 8) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 9) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 10) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
			if (i == 11) { return new ItemStack(Item.gunpowder, par1Random.nextInt(4) + 1); }
			if (i == 12 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.goldenCarrot); }
			if (i == 13 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.appleGold); }
			if (i > 13 || i < 13 + 6) { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }			
		} 
		else
		{
			return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); 
		}
	}

    /**
     * Randomly decides which spawner to use in a dungeon
     */
    private String pickMobSpawner(Random par1Random)
    {
        int randvar = par1Random.nextInt(5);
		
		if(randvar == 0)
		{
			return "Skeleton";
		}
		else if(randvar == 1)
		{
			return "Zombie";
		}
		else if(randvar == 2)
		{
			return "Zombie";
		}
		else if(randvar == 3)
		{
			return "Spider";
		}
		else if(randvar == 4)
		{
			return "Creeper";
		}
		else
		{
			return "";
		}
    }
}
