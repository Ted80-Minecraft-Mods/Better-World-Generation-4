package bwg4.data;

import net.minecraft.block.Block;

public class PlanetData 
{
	public Block outside;
	public Block inside;
	public int max;
	public int min;
	public int rarity;
	public boolean snow = false;
	public Block top;
	
	public PlanetData(Block o, Block i, int sizeMax, int sizeMin, int r)
	{
		outside = o;
		inside = i;
		max = sizeMax;
		min = sizeMin;
		rarity = r;
		top = outside;
	}
	
	public PlanetData(Block o, Block i, int sizeMax, int sizeMin, int r, Block t, boolean s)
	{
		this(o, i, sizeMax, sizeMin, r);
		
		top = t;
		snow = s;
	}
}
