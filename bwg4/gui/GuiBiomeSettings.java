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
	private GuiBiomeSettingsList guidefaultlist;     
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
		this.buttonList.clear();
		this.guidefaultlist = new GuiBiomeSettingsList(this);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 102, this.height - 28, 100, 20, StatCollector.translateToLocal("gui.done")));
        this.buttonList.add(new GuiButton(1, this.width / 2 + 3, this.height - 28, 100, 20, StatCollector.translateToLocal("gui.cancel")));
        this.buttonList.add(this.buttonEnable = new GuiButton(2, this.width / 2 - 102, this.height - 52, 100, 20, "Enable/Disable"));
        this.buttonList.add(this.buttonAll = new GuiButton(3, this.width / 2 + 3, this.height - 52, 100, 20, "Disable All"));
		setButtons();
	}
	
    protected void actionPerformed(GuiButton par1GuiButton)
    {
		int var2 = this.theDefaultGeneratorInfo.theBiomesList().size() - this.guidefaultlist.selected - 1;
	
		if (par1GuiButton.id == 0)
        {
			guiGeneratorSettings.BD_biomestring = getGeneratorInfo();
            this.mc.displayGuiScreen(guiGeneratorSettings);
        }
		else if (par1GuiButton.id == 1)
        {
            this.mc.displayGuiScreen(guiGeneratorSettings);
        }
		else if (par1GuiButton.id == 2)
        {
			BiomeInfo var6 = (BiomeInfo)theDefaultGeneratorInfo.theBiomesList().get(theDefaultGeneratorInfo.theBiomesList().size() - this.guidefaultlist.selected - 1);
			if(var6.getEnabled() == true)
			{
				var6.setEnabled(false);
			}
			else
			{
				var6.setEnabled(true);
			}
        }
		else if (par1GuiButton.id == 3)
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
		else if (par1GuiButton.id == 4)
        {
			biome = (BiomeInfo)theDefaultGeneratorInfo.theBiomesList().get(theDefaultGeneratorInfo.theBiomesList().size() - this.guidefaultlist.selected - 1);
        }
	}
	
	public void updateButtons()
	{
		if(all == true)
		{
			buttonAll.displayString = "Enable All";
		}	
		else
		{
			buttonAll.displayString = "Disable All";
		}
	}
	
    public void setButtons()
    {
        boolean possible = checkPossible();
        this.buttonEnable.enabled = possible;
    }

    private boolean checkPossible()
    {
        return this.guidefaultlist.selected > -1 && this.guidefaultlist.selected < this.theDefaultGeneratorInfo.theBiomesList().size();
    }
	
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
		this.guidefaultlist.drawScreen(par1, par2, par3);
		this.drawCenteredString(this.fontRendererObj, "Biome Settings", this.width / 2, 8, 16777215);
		this.drawCenteredString(this.fontRendererObj, "Biome Name", this.width / 2 - 80, 32, 16777215);
		this.drawCenteredString(this.fontRendererObj, "Enabled", this.width / 2 + 80, 32, 16777215);
        super.drawScreen(par1, par2, par3);
    }
}