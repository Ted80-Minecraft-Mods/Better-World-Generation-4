package bwg4.gui;

import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;

class GuiBiomeSettingsList extends GuiSlot
{
    final GuiBiomeSettings guidefault;
	public int selected; 

    public GuiBiomeSettingsList(GuiBiomeSettings par1)
    {
        super(par1.minecraft, par1.width, par1.height, 43, par1.height - 60, 24);
        this.guidefault = par1;
		selected = -1;
    }

    protected int getSize()
    {
		return GuiBiomeSettings.getBiomeArray(guidefault).theBiomesList().size();
    }

    protected void elementClicked(int par1, boolean par2, int a, int b)
    {
        this.selected = par1;
        this.guidefault.setButtons();
    }
	
    protected boolean isSelected(int par1)
    {
		return par1 == selected;
    }	
	
    protected void drawBackground() 
	{
	}
	
    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator, int a, int b)
    {
		//GET ARRAY LIST
		BiomeInfo var6 = (BiomeInfo)GuiBiomeSettings.getBiomeArray(this.guidefault).theBiomesList().get(GuiBiomeSettings.getBiomeArray(this.guidefault).theBiomesList().size() - par1 - 1);
		
		//GET DATA
		String name = var6.getNAME();
		boolean enable = var6.getEnabled();
		
		//DISPLAY TEXT
		if(enable == true)
		{	
			guidefault.fr.drawString(name, par2 + 1, par3 + 7, 16777215);
			guidefault.fr.drawString("YES", par2 + 179, par3 + 7, 16777215);
		}
		else
		{
			guidefault.fr.drawString(name, par2 + 1, par3 + 7, 10526880);
			guidefault.fr.drawString("NO", par2 + 182, par3 + 7, 10526880);
		}
	}

    protected int getScrollBarX()
    {
        return this.guidefault.width - 70;
    }

	protected void func_130003_c() 
	{
	}
}
