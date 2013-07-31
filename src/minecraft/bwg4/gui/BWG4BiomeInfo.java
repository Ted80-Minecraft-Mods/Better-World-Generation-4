package bwg4.gui;

public class BWG4BiomeInfo
{
    private String Setting;
	private boolean enabled;
	
    public BWG4BiomeInfo(String name, boolean enable)
    {
		Setting = name;
		enabled = enable;
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
			var1 = Setting;
		}
        return var1;
    }
}
