package bwg4.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class GuiBiomeSettings extends GuiScreen
{
	protected Minecraft minecraft;
	protected FontRenderer fr;
	
    private final GuiGeneratorSettings guiGeneratorSettings;
	private GuiBiomeSettingsList bwg4guidefaultlist;     
    private GuiBiomeSettingsInfo theDefaultGeneratorInfo = GuiBiomeSettingsInfo.defaultBiomesList(); 
	
    private GuiButton buttonEnable;
    private GuiButton buttonAll;
	
	private boolean all = false;
	private BiomeInfo biome;
	
    public GuiBiomeSettings(Minecraft instance, GuiGeneratorSettings par1, String par2Str, FontRenderer f)
    {
    	minecraft = instance;
    	fr = f;
    	guiGeneratorSettings = par1;
		setGeneratorInfo(par2Str);
    }
	
    public String getGeneratorInfo()
    {
        return this.theDefaultGeneratorInfo.toString();
    }

    public void setGeneratorInfo(String par1Str)
    {
        this.theDefaultGeneratorInfo = GuiBiomeSettingsInfo.FromString(par1Str);
    }
	
    static GuiBiomeSettingsInfo getBiomeArray(GuiBiomeSettings par0GuiCreateFlatWorld)
    {
        return par0GuiCreateFlatWorld.theDefaultGeneratorInfo;
    }
	
    public void initGui()
    {
		this.field_146292_n.clear();
		this.bwg4guidefaultlist = new GuiBiomeSettingsList(this);
        this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 102, this.field_146295_m - 28, 100, 20, StatCollector.translateToLocal("gui.done")));
        this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 + 3, this.field_146295_m - 28, 100, 20, StatCollector.translateToLocal("gui.cancel")));
        this.field_146292_n.add(this.buttonEnable = new GuiButton(2, this.field_146294_l / 2 - 102, this.field_146295_m - 52, 100, 20, "Enable/Disable"));
        this.field_146292_n.add(this.buttonAll = new GuiButton(3, this.field_146294_l / 2 + 3, this.field_146295_m - 52, 100, 20, "Disable All"));
		setButtons();
	}
	
    protected void func_146284_a(GuiButton par1GuiButton)
    {
		int var2 = this.theDefaultGeneratorInfo.theBiomesList().size() - this.bwg4guidefaultlist.selected - 1;
	
		if (par1GuiButton.field_146127_k == 0)
        {
			guiGeneratorSettings.BD_biomestring = getGeneratorInfo();
            this.field_146297_k.func_147108_a(guiGeneratorSettings);
        }
		else if (par1GuiButton.field_146127_k == 1)
        {
            this.field_146297_k.func_147108_a(guiGeneratorSettings);
        }
		else if (par1GuiButton.field_146127_k == 2)
        {
			BiomeInfo var6 = (BiomeInfo)theDefaultGeneratorInfo.theBiomesList().get(theDefaultGeneratorInfo.theBiomesList().size() - this.bwg4guidefaultlist.selected - 1);
			if(var6.getEnabled() == true)
			{
				var6.setEnabled(false);
			}
			else
			{
				var6.setEnabled(true);
			}
        }
		else if (par1GuiButton.field_146127_k == 3)
        {
			if(all == false)
			{
				for(int i = 0; i < theDefaultGeneratorInfo.theBiomesList().size(); i++)
				{
					BiomeInfo var6 = (BiomeInfo)theDefaultGeneratorInfo.theBiomesList().get(i);
					var6.setEnabled(false);
				}
				all = true;
			}
			else
			{
				for(int i = 0; i < theDefaultGeneratorInfo.theBiomesList().size(); i++)
				{
					BiomeInfo var6 = (BiomeInfo)theDefaultGeneratorInfo.theBiomesList().get(i);
					var6.setEnabled(true);
				}
				all = false;
			}
			updateButtons();
        }
		else if (par1GuiButton.field_146127_k == 4)
        {
			biome = (BiomeInfo)theDefaultGeneratorInfo.theBiomesList().get(theDefaultGeneratorInfo.theBiomesList().size() - this.bwg4guidefaultlist.selected - 1);
        }
	}
	
	public void updateButtons()
	{
		if(all == true)
		{
			buttonAll.field_146126_j = "Enable All";
		}	
		else
		{
			buttonAll.field_146126_j = "Disable All";
		}
	}
	
    public void setButtons()
    {
        boolean possible = checkPossible();
        this.buttonEnable.field_146124_l = possible;
    }

    private boolean checkPossible()
    {
        return this.bwg4guidefaultlist.selected > -1 && this.bwg4guidefaultlist.selected < this.theDefaultGeneratorInfo.theBiomesList().size();
    }
	
    public void drawScreen(int par1, int par2, float par3)
    {
        this.func_146276_q_();
		this.bwg4guidefaultlist.func_148128_a(par1, par2, par3);
		this.drawCenteredString(this.field_146289_q, "Biome Settings", this.field_146294_l / 2, 8, 16777215);
		this.drawCenteredString(this.field_146289_q, "Biome Name", this.field_146294_l / 2 - 80, 32, 16777215);
		this.drawCenteredString(this.field_146289_q, "Enabled", this.field_146294_l / 2 + 80, 32, 16777215);
        super.drawScreen(par1, par2, par3);
    }
}