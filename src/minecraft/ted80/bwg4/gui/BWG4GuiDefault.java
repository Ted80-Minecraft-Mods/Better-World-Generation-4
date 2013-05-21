package ted80.bwg4.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class BWG4GuiDefault extends GuiScreen
{
	protected Minecraft minecraft;
	protected FontRenderer fr;
	
    private final BWG4GuiGeneratorSettings guiGeneratorSettings;
	private BWG4GuiDefaultList bwg4guidefaultlist;     
    private BWG4DefaultGeneratorInfo theDefaultGeneratorInfo = BWG4DefaultGeneratorInfo.defaultBiomesList(); 
	
    private GuiButton buttonEnable;
    private GuiButton buttonAll;
    private GuiButton buttonBiome;
    private GuiButton buttonSetting;
	
    //private static final EnumOptions[] relevantOptions = new EnumOptions[] {EnumOptions.MUSIC, EnumOptions.SOUND, EnumOptions.INVERT_MOUSE, EnumOptions.SENSITIVITY, EnumOptions.FOV, EnumOptions.DIFFICULTY, EnumOptions.TOUCHSCREEN};
    //private GuiSlider biomeMinHeight;
    //private GuiSlider biomeMaxHeight;
    //private GuiSlider biomeDungeon;
	
	private boolean all = false;
    private boolean customize;
	private boolean biomescreen;
	private BWG4BiomeInfo biome;
	
    public BWG4GuiDefault(Minecraft instance, BWG4GuiGeneratorSettings par1, String par2Str, FontRenderer f)
    {
    	minecraft = instance;
    	fr = f;
    	guiGeneratorSettings = par1;
		setGeneratorInfo(par2Str);
		customize = false;
    }
	
    public String getGeneratorInfo()
    {
        return this.theDefaultGeneratorInfo.toString();
    }

    public void setGeneratorInfo(String par1Str)
    {
        this.theDefaultGeneratorInfo = BWG4DefaultGeneratorInfo.FromString(par1Str);
    }
	
    static BWG4DefaultGeneratorInfo getBiomeArray(BWG4GuiDefault par0GuiCreateFlatWorld)
    {
        return par0GuiCreateFlatWorld.theDefaultGeneratorInfo;
    }
	
    public void initGui()
    {
		this.buttonList.clear();
		this.bwg4guidefaultlist = new BWG4GuiDefaultList(this);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 100, 20, StatCollector.translateToLocal("gui.done")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height - 28, 100, 20, StatCollector.translateToLocal("gui.cancel")));
        this.buttonList.add(this.buttonEnable = new GuiButton(2, this.width / 2 - 155, this.height - 52, 100, 20, "Enable/Disable"));
        this.buttonList.add(this.buttonAll = new GuiButton(3, this.width / 2 - 50, this.height - 52, 100, 20, "Disable All"));
        this.buttonList.add(this.buttonBiome = new GuiButton(4, this.width / 2 + 55, this.height - 52, 100, 20, "-")); //Biome Settings
        this.buttonList.add(this.buttonSetting = new GuiButton(5, this.width / 2 + 55, this.height - 28, 100, 20, "-")); //World Settings
		
		// EnumOptions[] var3 = relevantOptions;
        // int var4 = var3.length;

        // for (int var5 = 0; var5 < var4; ++var5)
        // {
            // EnumOptions var6 = var3[var5];
		//this.buttonList.add(this.biomeMinHeight = new GuiSlider(10, this.width / 2 + 55, 28, 10, "Min Height", 0.3F));     
		//this.buttonList.add(this.biomeMaxHeight = new GuiSlider(11, this.width / 2 + 55, 28, 11, "Max Height", 0.3F));         
		//this.buttonList.add(this.biomeDungeon = new GuiSlider(12, this.width / 2 + 55, 28, 12, "Dungeons Per Chunk", 0.3F));       

		setButtons();
		switchScreen();
	}
	
    protected void actionPerformed(GuiButton par1GuiButton)
    {
		int var2 = this.theDefaultGeneratorInfo.theBiomesList().size() - this.bwg4guidefaultlist.selected - 1;
	
		if (par1GuiButton.id == 0)
        {
			//this.createWorldGui.generatorOptionsToUse = this.getGeneratorInfo();
            this.mc.displayGuiScreen(guiGeneratorSettings);
        }
		else if (par1GuiButton.id == 1)
        {
			//this.createWorldGui.generatorOptionsToUse = this.getGeneratorInfo();
            this.mc.displayGuiScreen(guiGeneratorSettings);
        }
		else if (par1GuiButton.id == 2)
        {
			BWG4BiomeInfo var6 = (BWG4BiomeInfo)theDefaultGeneratorInfo.theBiomesList().get(theDefaultGeneratorInfo.theBiomesList().size() - this.bwg4guidefaultlist.selected - 1);
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
					BWG4BiomeInfo var6 = (BWG4BiomeInfo)theDefaultGeneratorInfo.theBiomesList().get(i);
					var6.setEnabled(false);
				}
				all = true;
			}
			else
			{
				for(int i = 0; i < theDefaultGeneratorInfo.theBiomesList().size(); i++)
				{
					BWG4BiomeInfo var6 = (BWG4BiomeInfo)theDefaultGeneratorInfo.theBiomesList().get(i);
					var6.setEnabled(true);
				}
				all = false;
			}
			updateButtons();
        }
		else if (par1GuiButton.id == 4)
        {
			biome = (BWG4BiomeInfo)theDefaultGeneratorInfo.theBiomesList().get(theDefaultGeneratorInfo.theBiomesList().size() - this.bwg4guidefaultlist.selected - 1);
			customize = false;
			biomescreen = true;
			switchScreen();
        }
		else if (par1GuiButton.id == 5)
        {
			if(biomescreen == true)
			{
				biomescreen = false;
				customize = false;
			}	
			else if(customize == true)
			{
				customize = false;
			}
			else
			{
				customize = true;
			}
			switchScreen();
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
        //this.buttonBiome.enabled = possible;
		buttonBiome.enabled = false;
		buttonSetting.enabled = false;
    }

    private boolean checkPossible()
    {
        return this.bwg4guidefaultlist.selected > -1 && this.bwg4guidefaultlist.selected < this.theDefaultGeneratorInfo.theBiomesList().size();
    }
	
	public void switchScreen()
	{
 		if(customize)
		{ 
			buttonEnable.drawButton = false;
			buttonAll.drawButton = false;
			buttonBiome.drawButton = false;
			buttonSetting.displayString = "-"; //Biome List
			
			// biomeMinHeight.drawButton = false;
			// biomeMaxHeight.drawButton = false;
			// biomeDungeon.drawButton = false;
 		}
		else if(biomescreen)
		{
			buttonEnable.drawButton = false;
			buttonAll.drawButton = false;
			buttonBiome.drawButton = false;
			buttonSetting.displayString = "-"; //Biome List
			
			// biomeMinHeight.drawButton = true;
			// biomeMaxHeight.drawButton = true;
			// biomeDungeon.drawButton = true;
		}
		else
		{
			buttonEnable.drawButton = true;
			buttonAll.drawButton = true;
			buttonBiome.drawButton = true;
			buttonSetting.displayString = "-"; //World Settings
			
			// biomeMinHeight.drawButton = false;
			// biomeMaxHeight.drawButton = false;
			// biomeDungeon.drawButton = false;
		} 
	}
	
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        
		if(customize)
		{
			this.drawCenteredString(this.fontRenderer, "World Settings", this.width / 2, 8, 16777215);
		}
		else if(biomescreen)
		{
			this.drawCenteredString(this.fontRenderer, "Biome: " + biome.getNAME(), this.width / 2, 8, 16777215);
		}
		else
		{
			this.bwg4guidefaultlist.drawScreen(par1, par2, par3);
			this.drawCenteredString(this.fontRenderer, "Biome List", this.width / 2, 8, 16777215);
			this.drawCenteredString(this.fontRenderer, "Biome Name", this.width / 2 - 80, 32, 16777215);
			this.drawCenteredString(this.fontRenderer, "Enabled", this.width / 2 + 80, 32, 16777215);
		}
        super.drawScreen(par1, par2, par3);
    }
}