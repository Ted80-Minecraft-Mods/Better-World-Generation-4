package net.minecraft.client.gui;

public class BWG4BiomeInfo
{
    private String Setting;
	private boolean enabled;
	
	//private float minHeight;
	//private float maxHeight;
	//private int dungeonsPerChunk;
	
    public BWG4BiomeInfo(String name, boolean enable)//, float min, float max, int dungeon)
    {
		Setting = name;
		enabled = enable;
		
		//minHeight = min;
		//maxHeight = max;
		//dungeonsPerChunk = dungeon;
	}
	
    public String getNAME()
    {
        return Setting;
    }
	
	public boolean getEnabled()
	{
		return enabled;
	}
	
	public void setEnabled(boolean bool)
	{
		enabled = bool;
	}
	
    public String toString()
    {
        String var1 = "";
		if(enabled == true)
		{
			var1 = Setting + "=true";
		}
		else
		{
			var1 = Setting + "=false";
		}
		
		//var1 = var1 + "." + minHeight + "." + maxHeight + "." + dungeonsPerChunk;
		
        return var1;
    }
}
