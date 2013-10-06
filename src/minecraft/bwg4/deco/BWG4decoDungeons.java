package bwg4.deco;

import java.util.Random;

import bwg4.generatordata.BWG4DungeonLoot;
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
				if (spawn3 != null) { spawn3.getSpawnerLogic().setMobID(this.pickMobSpawner(par2Random)); }
			}
			else
			{
				par1World.setBlock(par3, par4 + 1, par5, Block.mobSpawner.blockID);
				par1World.setBlock(par3, par4 + 3, par5, Block.mobSpawner.blockID);
				par1World.setBlock(par3, par4 + 6, par5, Block.mobSpawner.blockID);
				TileEntityMobSpawner spawn1 = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4 + 1, par5);
				TileEntityMobSpawner spawn2 = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4 + 3, par5);
				TileEntityMobSpawner spawn3 = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4 + 6, par5);
				if (spawn1 != null) { spawn1.getSpawnerLogic().setMobID(this.pickMobSpawner(par2Random)); }
				if (spawn2 != null) { spawn2.getSpawnerLogic().setMobID(this.pickMobSpawner(par2Random)); }
				if (spawn3 != null) { spawn3.getSpawnerLogic().setMobID(this.pickMobSpawner(par2Random)); }
				
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
					var19.getSpawnerLogic().setMobID(this.pickMobSpawner(par2Random));
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
    	return BWG4DungeonLoot.pickCheckLootItem(par1Random, listitem, chestID);
	}

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
