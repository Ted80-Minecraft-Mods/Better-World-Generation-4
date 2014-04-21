package bwg4.gui;

import java.util.ArrayList;
import bwg4.api.biome.BiomeManager;
import bwg4.api.gen.GeneratorType;
import bwg4.data.DecodeGeneratorString;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;


public class GuiGeneratorSettings extends GuiScreen
{
	private final GuiCreateWorld createWorldGui;
	
	private GuiButton BUTTON_DONE;
	private GuiButton BUTTON_CATEGORY;
	private GuiButton BUTTON_BIOMELIST;
	private GuiButton BUTTON_WORLDSETTINGS;
	
	private int CATEGORY = 1;
	private String[] categories = new String[]{"Enhanced", "Old", "Extreme Survival", "Fun"};//, "Adventure"};
	
	private int generatorSelected = -1;
	private ArrayList<GuiGeneratorButton> generators;
	private ArrayList<GuiSettingsButton> settings;

	public String BD_biomestring;
	public String BD_worldstring;
	
	public boolean decodebool;
	public boolean setremember;
	public int[] rememberSettings;
	
	public GuiGeneratorSettings(GuiCreateWorld gcw, String gs)
	{
    	createWorldGui = gcw;
    	decodebool = true;
	}

	public void initGui()
	{
        buttonList.add(BUTTON_DONE = new GuiButton(0, width / 2 - 155, height - 24, 150, 20, StatCollector.translateToLocal("gui.done")));
        buttonList.add(new GuiButton(1, width / 2 + 5, height - 24, 150, 20, StatCollector.translateToLocal("gui.cancel")));
        buttonList.add(new GuiButton(3, width / 2 - 155, height - 48, 310, 20, "Copy generator-settings to Clipboard"));
		
		buttonList.add(BUTTON_CATEGORY = new GuiButton(2, width / 2 - 155, 40, 150, 20, categories[CATEGORY]));
		buttonList.add(BUTTON_BIOMELIST = new GuiButton(4, width / 2 + 5, 80, 150, 20, "Biome Settings"));
		buttonList.add(BUTTON_WORLDSETTINGS = new GuiButton(5, width / 2 + 5, 100, 150, 20, "World Settings"));
		BUTTON_WORLDSETTINGS.enabled = false;
		
		if(decodebool)
		{
			decodebool = false;
			decodeString(createWorldGui.field_146334_a);
		}
		else
		{
        	switchCategory();
        	for(int i = 0; i < generators.size(); i++)
        	{
        		generators.get(i).button.enabled = true;
        		if(generators.get(i).generatorID == generatorSelected)
        		{
        			System.out.println(generatorSelected);
            		generators.get(i).button.enabled = false;
        		}
        	}
			selectGenerator();

			if(setremember)
			{
				for(int s = 0; s < settings.size(); s++)
				{
					settings.get(s).setOldValue(rememberSettings[s]);
				}
				setremember = false;
			}
		}
	}

	protected void actionPerformed(GuiButton button)
	{
        if (button.id == 0) //DONE
        {
        	createWorldGui.field_146334_a = createString();
        	mc.displayGuiScreen(this.createWorldGui); 
        }
        else if (button.id == 1) //CANCEL
        {
        	mc.displayGuiScreen(this.createWorldGui);
        }
        else if (button.id == 2) //CATEGORY
        {
        	BD_biomestring = "";
        	CATEGORY++;
        	if(CATEGORY >= categories.length)
        	{
        		CATEGORY = 0;
        	}
        	switchCategory();
    		generatorSelected = -1;
    		selectGenerator();
        }
        else if (button.id == 3) //COPY SETTINGS
        {
        	String copy = createString();
        	System.out.println(copy);
        	setClipboardString(copy);
        }
        else if (button.id == 4) //COSTUMIZE BIOME LIST
        {
        	mc.displayGuiScreen(new GuiBiomeSettings(mc, this, BD_biomestring, fontRendererObj));
        	
        	setremember = true;
        	rememberSettings = new int[settings.size()];
			for(int s = 0; s < settings.size(); s++)
			{
				rememberSettings[s] = settings.get(s).valuearray[settings.get(s).selected];
			}
        }
        else if (button.id >= 10 && button.id < 20)
        {
        	for(int i = 0; i < generators.size(); i++)
        	{
        		generators.get(i).button.enabled = true;
        		if(generators.get(i).button.id == button.id)
        		{
            		generators.get(i).button.enabled = false;
        			generatorSelected = generators.get(i).generatorID;
        		}
        	}
    		selectGenerator();
        }
        else if (button.id >= 20 && button.id < 30)
        {
        	for(int i = 0; i < settings.size(); i++)
        	{
        		if(settings.get(i).button.id == button.id)
        		{
        			settings.get(i).click();
        		}
        	}
        	dependencies();
        }
	}
	
	public void drawScreen(int par1, int par2, float par3)
	{
		drawDefaultBackground();
		
		//title
		String title = "Better World Generation 4";
		drawString(fontRendererObj, title, (int) Math.floor(width / 2) - (int) Math.floor(fontRendererObj.getStringWidth(title) / 2), 10, 16777215);
		
		//category
		drawString(fontRendererObj, "Choose a category:", width / 2 - 155 + 1, 30, 10526880);
    	if(CATEGORY != 4)
    	{
    		drawString(fontRendererObj, "Select a world generator:", width / 2 - 155 + 1, 70, 10526880);
    	}
		
    	String catpos = "(" + (CATEGORY + 1) + "/4)";
    	drawString(fontRendererObj, catpos, width / 2 - 5 - fontRendererObj.getStringWidth(catpos), 30, 10526880);
    	
    	if(CATEGORY == 4)
    	{
    		drawString(fontRendererObj, "Coming soon!", width / 2 - 110 + 1, 110, 10526880);
    	}

		if(generatorSelected != -1)
		{
	    	if(GeneratorType.generatortypes[generatorSelected].HasSettings())
	    	{
	    		drawString(fontRendererObj, "Generator settings:", width / 2 + 5 + 1, 70, 10526880);
	    	}
		}
		
		super.drawScreen(par1, par2, par3);
	}
	
	public void switchCategory()
	{
		BUTTON_CATEGORY.displayString = categories[CATEGORY];

		if(generators != null)
		{
			for(int i = 0; i < generators.size(); i++)
			{
				buttonList.remove(generators.get(i).button);
			}
		}
		
		generators = new ArrayList<GuiGeneratorButton>();
		int count = 0;
		for(int g = 0; g < GeneratorType.generatortypes.length; g++)
		{
			if(GeneratorType.generatortypes[g] != null)
			{
				if(GeneratorType.generatortypes[g].GetCategory() == CATEGORY && GeneratorType.generatortypes[g].CanBeCreated())
				{
					generators.add(new GuiGeneratorButton(GeneratorType.generatortypes[g].GetScreenName(), g, count + 10, 80 + (20 * count), width));
					buttonList.add(generators.get(generators.size() - 1).button);
					count++;
				}
			}
		}
	}
	
	public void dependencies()
	{
		for(int i = 0; i < settings.size(); i++)
		{
    		if(settings.get(i).dependencie > -1)
    		{
    			settings.get(i).button.visible = false;
    			for(int p = 0; p < settings.get(i).depvalues.length; p++)
    			{
    				if(settings.get(settings.get(i).dependencie - 20).selected == settings.get(i).depvalues[p])
    				{
    					settings.get(i).button.visible = true;
    				}
    			}
    		}
		}
	}
	
	public void selectGenerator()
	{
		if(generatorSelected > -1)
		{
			BUTTON_DONE.enabled = true;
		}
		else
		{
			BUTTON_DONE.enabled = false;
		}
		
		if(settings != null)
		{
			for(int i = 0; i < settings.size(); i++)
			{
				buttonList.remove(settings.get(i).button);
			}
		}
		settings = new ArrayList<GuiSettingsButton>();

		BUTTON_BIOMELIST.visible = false;
		BUTTON_WORLDSETTINGS.visible = false;
		if(generatorSelected == GeneratorType.DEFAULT.GetID())
		{
			BUTTON_BIOMELIST.visible = true;
			BUTTON_WORLDSETTINGS.visible = true;
			settings.add(new GuiSettingsSlider(new String[]{"Biome size: 1", "Biome size: 2", "Biome size: 3", "Biome size: 4 (default)", "Biome size: 5", "Biome size: 6 (Large)", "Biome size: 7", "Biome size: 8"}, new int[]{1,2,3,4,5,6,7,8}, 3, 20, 120, width));
			settings.add(new GuiSettingsButton(new String[]{"Amplified: OFF", "Amplified: ON"}, new int[]{0,1}, 21, 140, width));
		}
		else if(generatorSelected == GeneratorType.BETA173.GetID()) 
		{
			//settings.add(new GuiSettingsButton(new String[]{"Biomes: Beta", "Biomes: Better Default"}, new int[]{0, 1}, 20, 80, width));
		}
		else if(generatorSelected == GeneratorType.INFDEV.GetID() || generatorSelected == GeneratorType.ALPHA11.GetID()) 
		{
			settings.add(new GuiSettingsButton(new String[]{"Snow World: OFF", "Snow World: ON"}, new int[]{0, 1}, 20, 80, width));
		}
		else if(generatorSelected == GeneratorType.INDEV.GetID()) 
		{ 
			settings.add(new GuiSettingsButton(new String[]{"Theme: Default", "Theme: Hell", "Theme: Paradise", "Theme: Woods", "Theme: Snow"}, new int[]{0, 1, 2, 3, 4}, 20, 80, width));
			settings.add(new GuiSettingsButton(new String[]{"Type: Island", "Type: Floating", "Type: Inland"}, new int[]{0, 1, 2}, 21, 100, width));
			settings.add(new GuiSettingsSlider(new String[]{"Size: Small", "Size: Default", "Size: Large"}, new int[]{0, 1, 2}, 1, 22, 120, width, 21, new int[]{0, 1}));
			settings.add(new GuiSettingsSlider(new String[]{"Layers: 1", "Layers: 2", "Layers: 3", "Layers: 4", "Layers: 5"}, new int[]{0, 1, 2, 3, 4}, 1, 23, 140, width, 21, new int[]{1}));
		}
		else if(generatorSelected == GeneratorType.ISLAND.GetID()) 
		{ 
			settings.add(new GuiSettingsButton(new String[]{"Theme: Default", "Theme: Tropical", "Theme: Paradise"}, new int[]{0, 1, 4}, 20, 80, width)); //"Theme: Hell", "Theme: Iceberg",   2, 3,
			settings.add(new GuiSettingsSlider(new String[]{"Size: Small", "Size: Default", "Size: Large"}, new int[]{0, 1, 2}, 1, 21, 100, width, 20, new int[]{0, 1})); //, 2, 3
		}
		else if(generatorSelected == GeneratorType.SKYISLAND.GetID()) 
		{ 
			settings.add(new GuiSettingsButton(new String[]{"Theme: Default", "Theme: Snow", "Theme: Jungle"}, new int[]{0, 1, 2}, 20, 80, width)); 
		}
		else if(generatorSelected == GeneratorType.SKYBLOCK.GetID())
		{
			settings.add(new GuiSettingsSlider(new String[]{"Size: Small", "Size: Default", "Size: Large"}, new int[]{0, 1, 2}, 1, 20, 80, width));
		}
		else if(generatorSelected == GeneratorType.SKYLANDS.GetID()) 
		{ 
			//settings.add(new GuiSettingsButton(new String[]{"Biomes: Beta 1.7.3", "Biomes: Better Default"}, new int[]{1, 0}, 20, 80, width));
		}
		else if(generatorSelected == GeneratorType.DEADLYDESERT.GetID()) 
		{ 
			settings.add(new GuiSettingsButton(new String[]{"Extremely hard: OFF", "Extremely hard: ON"}, new int[]{0, 1}, 20, 80, width));
		}

		for(int s = 0; s < settings.size(); s++)
		{
			buttonList.add(settings.get(s).button);
		}
		
		dependencies();
	}
	
	public void decodeString(String decodestring)
	{
		String[] genstring = decodestring.split("#");
		String[] gensettings = new String[0];
		if(genstring.length > 1)
		{
			gensettings = genstring[1].split("&");
		}
		if(genstring.length > 2)
		{
			BD_biomestring = genstring[2];
		}
		else
		{
			BD_biomestring = BiomeManager.getDefaultString();
		}
		
		int n = DecodeGeneratorString.getGeneratorIDFromName(genstring[0]);
		if(n > -1)
		{
			CATEGORY = GeneratorType.generatortypes[n].GetCategory();
			switchCategory();
			generatorSelected = n;
			
        	for(int i = 0; i < generators.size(); i++)
        	{
        		generators.get(i).button.enabled = true;
        		if(generators.get(i).generatorID == generatorSelected)
        		{
            		generators.get(i).button.enabled = false;
        		}
        	}
			selectGenerator();
			
			for(int i = 0; i < settings.size(); i++)
			{
				if(i < gensettings.length)
				{
					settings.get(i).setOldValue(Integer.parseInt(gensettings[i]));
				}
			}
		}
		else
		{
			switchCategory();
			generatorSelected = -1;
			selectGenerator();
		}
	}
	
	public String createString()
	{
		if(generatorSelected > -1 && generatorSelected < GeneratorType.generatortypes.length)
		{
			String genstring = GeneratorType.generatortypes[generatorSelected].GetName();
			for(int s = 0; s < settings.size(); s++)
			{
				if(s == 0)
				{
					genstring += "#" + settings.get(s).valuearray[settings.get(s).selected];
				}
				else
				{
					genstring += "&" + settings.get(s).valuearray[settings.get(s).selected];
				}
			}
			
			if(generatorSelected == GeneratorType.DEFAULT.GetID())
			{
				genstring += "#" + BD_biomestring;
			}
			
			return genstring;
		}
		else
		{
			return GeneratorType.DEFAULT.GetName() + "#4&0#" + BiomeManager.getDefaultString();
		}
	}
}