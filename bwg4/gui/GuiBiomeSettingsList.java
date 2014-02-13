package bwg4.gui;

import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;

class GuiBiomeSettingsList extends GuiSlot
{
    final GuiBiomeSettings bwg4guidefault;
	public int selected; 

    public GuiBiomeSettingsList(GuiBiomeSettings par1)
    {
        super(par1.minecraft, par1.field_146294_l, par1.field_146295_m, 43, par1.field_146295_m - 60, 24);
        this.bwg4guidefault = par1;
		selected = -1;
    }

    protected int func_148127_b()
    {
		return GuiBiomeSettings.getBiomeArray(bwg4guidefault).theBiomesList().size();
    }

    protected void func_148144_a(int par1, boolean par2, int var3, int var4)
    {
        this.selected = par1;
        this.bwg4guidefault.setButtons();
    }
	
    protected boolean func_148131_a(int par1)
    {
		return par1 == selected;
    }	
	
    protected void func_148123_a() 
	{
	}
	
    protected void func_148126_a(int par1, int par2, int par3, int par4, Tessellator par5Tessellator, int var12, int var13)
    {
		//GET ARRAY LIST
		BiomeInfo var6 = (BiomeInfo)GuiBiomeSettings.getBiomeArray(this.bwg4guidefault).theBiomesList().get(GuiBiomeSettings.getBiomeArray(this.bwg4guidefault).theBiomesList().size() - par1 - 1);
		
		//GET DATA
		String name = var6.getNAME();
		boolean enable = var6.getEnabled();
		
		//DISPLAY TEXT
		if(enable == true)
		{	
			bwg4guidefault.fr.drawString(name, par2 + 1, par3 + 7, 16777215);
			bwg4guidefault.fr.drawString("YES", par2 + 179, par3 + 7, 16777215);
		}
		else
		{
			bwg4guidefault.fr.drawString(name, par2 + 1, par3 + 7, 10526880);
			bwg4guidefault.fr.drawString("NO", par2 + 182, par3 + 7, 10526880);
		}
	}

    protected int getScrollBarX()
    {
        return this.bwg4guidefault.field_146294_l - 70;
    }

	protected void func_130003_c() 
	{
	}
}
