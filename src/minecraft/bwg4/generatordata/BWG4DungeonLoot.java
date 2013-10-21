package bwg4.generatordata;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ChestGenHooks;

public class BWG4DungeonLoot
{
    public static ItemStack pickCheckLootItem(Random par1Random, int listitem, int chestID)
    {
    	if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ALPHA11)
    	{
			int i = par1Random.nextInt(20);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 7) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
			if (i == 8 || i == 9 || i == 10) { return new ItemStack(Item.seeds, par1Random.nextInt(12) + 1); }
			if (i == 11) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i == 12) { return new ItemStack(Block.blockSnow, par1Random.nextInt(12) + 1); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
    	}
    	else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ALPHA12)
    	{
			int i = par1Random.nextInt(17);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 7) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
			if (i == 8 || i == 9 || i == 10) { return new ItemStack(Item.seeds, par1Random.nextInt(12) + 1); }
			if (i == 11) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
    	}
    	else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.BETA173)
    	{
    		if(chestID == 1)
    		{
				int i = par1Random.nextInt(15);
				if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
				if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
				if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
				if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
				if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
				if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
				if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
				if (i == 7) { return new ItemStack(Block.sapling, 1, 3); }
				if (i == 8) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
				else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
    		}
    		else if(chestID == 2)
    		{
    			int i = par1Random.nextInt(12);
    			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
    			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
    			if (i == 2 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
    			if (i == 3) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
    			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
    		}
    	}
    	else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.DEFAULT)
    	{
    		return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random);
    	}
    	else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INDEV)
    	{
    		if(chestID == 1)
    		{
    			int i = par1Random.nextInt(22);
    			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
    			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
    			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
    			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
    			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
    			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
    			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
    			if (i == 7) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
    			if (i == 8 || i == 9 || i == 10) { return new ItemStack(Item.seeds, par1Random.nextInt(12) + 1); }
    			if (i == 11) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
    			if (i == 12) { return new ItemStack(Block.cactus, par1Random.nextInt(4) + 1); }
    			if (i == 13) { return new ItemStack(Item.reed, par1Random.nextInt(4) + 1); }
    			if (i == 14) { return new ItemStack(Item.brick, par1Random.nextInt(63) + 1); }
    			if (i == 15) { return new ItemStack(Block.blockSnow, par1Random.nextInt(12) + 1); }
    			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
    		} 
    		else
    		{
    			int i = par1Random.nextInt(30);
    			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
    			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
    			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
    			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
    			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
    			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
    			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
    			if (i == 7) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
    			if (i == 8 || i == 9 || i == 10) { return new ItemStack(Item.seeds, par1Random.nextInt(12) + 1); }
    			if (i == 11 || i == 12 || i == 13) { return new ItemStack(Item.bucketWater, par1Random.nextInt(2) + 1); }
    			if (i == 14 || i == 15 || i == 16) { return new ItemStack(Item.bucketLava, par1Random.nextInt(2) + 1); }
    			if (i == 17) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
    			if (i == 18) { return new ItemStack(Block.cactus, par1Random.nextInt(4) + 1); }
    			if (i == 19) { return new ItemStack(Item.reed, par1Random.nextInt(4) + 1); }
    			if (i == 20) { return new ItemStack(Item.brick, par1Random.nextInt(63) + 1); }
    			if (i == 21) { return new ItemStack(Block.blockSnow, par1Random.nextInt(12) + 1); }
    			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
    		} 
    	}
    	else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INFDEV)
    	{
			int i = par1Random.nextInt(21);
			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
			if (i == 7) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
			if (i == 8 || i == 9 || i == 10) { return new ItemStack(Item.seeds, par1Random.nextInt(12) + 1); }
			if (i == 11) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
			if (i == 12) { return new ItemStack(Block.blockClay, par1Random.nextInt(15) + 1); }
			if (i == 13) { return new ItemStack(Block.cactus, par1Random.nextInt(4) + 1); }
			if (i == 14) { return new ItemStack(Item.reed, par1Random.nextInt(4) + 1); }
			if (i == 15) { return new ItemStack(Block.blockSnow, par1Random.nextInt(12) + 1); }
			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
    	}
    	else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ISLAND)
    	{ 
    		if(chestID == 1)
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
    		else if(chestID == 2)
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
    		else
    		{
    			int i = par1Random.nextInt(18);
    			if (i == 0 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.monsterPlacer, 1, 95); }
    			if (i == 1 && par1Random.nextInt(3) == 0) { return new ItemStack(Item.monsterPlacer, 1, 98); }
    			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }		
    		}
    	}
    	else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND)
    	{
    		if(chestID == 2)
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
				int i = par1Random.nextInt(15);
				if (i == 0) { return new ItemStack(Block.cactus, par1Random.nextInt(4) + 1); }
				if (i == 1) { return new ItemStack(Item.reed, par1Random.nextInt(4) + 1); }
				if (i == 2) { return new ItemStack(Block.sapling, 1, par1Random.nextInt(3) + 1); }
				if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
				if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
				if (i == 5 || i == 6) { return new ItemStack(Item.bucketLava, par1Random.nextInt(4) + 1); }
				else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
    		}
    	}
    	else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYLANDS)
    	{
        	if(chestID == 1)
    		{
    			int i = par1Random.nextInt(21);
    			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
    			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
    			if (i == 2) { return new ItemStack(Item.emerald, par1Random.nextInt(4) + 1); }
    			if (i == 3) { return new ItemStack(Item.melonSeeds, par1Random.nextInt(4) + 1); }
    			if (i == 4) { return new ItemStack(Item.pumpkinSeeds, par1Random.nextInt(4) + 1); }
    			if (i == 5) { return new ItemStack(Block.vine, par1Random.nextInt(4) + 1); }
    			if (i == 6 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
    			if (i == 7) { return new ItemStack(Block.sapling, 1, 3); }
    			if (i == 8) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
    			if (i == 9 || i == 10 || i == 11) { return new ItemStack(Item.bucketWater, par1Random.nextInt(2) + 1); }
    			if (i == 12 || i == 13 || i == 14) { return new ItemStack(Item.bucketLava, par1Random.nextInt(2) + 1); }
    			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
    		} 
    		if(chestID == 3)
    		{
    			return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random);
    		} 
    		else
    		{
    			int i = par1Random.nextInt(20);
    			if (i == 0) { return new ItemStack(Item.potato, par1Random.nextInt(4) + 1); }
    			if (i == 1) { return new ItemStack(Item.carrot, par1Random.nextInt(4) + 1); }
    			if (i == 2 && par1Random.nextInt(3) == 0) { return new ItemStack(Block.mycelium); }
    			if (i == 3) { return new ItemStack(Block.waterlily, par1Random.nextInt(4) + 1); }
    			if (i == 4 || i == 5 || i == 6) { return new ItemStack(Item.bucketWater, par1Random.nextInt(2) + 1); }
    			if (i == 7 || i == 8 || i == 9) { return new ItemStack(Item.bucketLava, par1Random.nextInt(2) + 1); }
    			else { return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random); }	
    		} 
    	}
    	
    	return ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, par1Random);
    }
}
