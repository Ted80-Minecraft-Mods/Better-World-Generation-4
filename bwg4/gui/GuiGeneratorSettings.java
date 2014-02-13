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
	
	private int CATEGORY = 0;
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
		field_146292_n.add(BUTTON_DONE = new GuiButton(0, field_146294_l / 2 - 155, field_146295_m - 24, 150, 20, StatCollector.translateToLocal("gui.done")));
		field_146292_n.add(new GuiButton(1, field_146294_l / 2 + 5, field_146295_m - 24, 150, 20, StatCollector.translateToLocal("gui.cancel")));
		field_146292_n.add(new GuiButton(3, field_146294_l / 2 - 155, field_146295_m - 48, 310, 20, "Copy generator-settings to Clipboard"));
		
		field_146292_n.add(BUTTON_CATEGORY = new GuiButton(2, field_146294_l / 2 - 155, 40, 150, 20, categories[CATEGORY]));
		field_146292_n.add(BUTTON_BIOMELIST = new GuiButton(4, field_146294_l / 2 + 5, 80, 150, 20, "Biome Settings"));
		field_146292_n.add(BUTTON_WORLDSETTINGS = new GuiButton(5, field_146294_l / 2 + 5, 100, 150, 20, "World Settings"));
		BUTTON_WORLDSETTINGS.field_146124_l = false;
		
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
        		generators.get(i).button.field_146124_l = true;
        		if(generators.get(i).generatorID == generatorSelected)
        		{
        			System.out.println(generatorSelected);
            		generators.get(i).button.field_146124_l = false;
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

	protected void func_146284_a(GuiButton button)
	{
        if (button.field_146127_k == 0) //DONE
        {
        	createWorldGui.field_146334_a = createString();
        	field_146297_k.func_147108_a(this.createWorldGui); 
        }
        else if (button.field_146127_k == 1) //CANCEL
        {
        	field_146297_k.func_147108_a(this.createWorldGui);
        }
        else if (button.field_146127_k == 2) //CATEGORY
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
        else if (button.field_146127_k == 3) //COPY SETTINGS
        {
        	String copy = createString();
        	System.out.println(copy);
        	func_146275_d(copy);
        }
        else if (button.field_146127_k == 4) //COSTUMIZE BIOME LIST
        {
        	field_146297_k.func_147108_a(new GuiBiomeSettings(field_146297_k, this, BD_biomestring, field_146289_q));
        	
        	setremember = true;
        	rememberSettings = new int[settings.size()];
			for(int s = 0; s < settings.size(); s++)
			{
				rememberSettings[s] = settings.get(s).valuearray[settings.get(s).selected];
			}
        }
        else if (button.field_146127_k >= 10 && button.field_146127_k < 20)
        {
        	for(int i = 0; i < generators.size(); i++)
        	{
        		generators.get(i).button.field_146124_l = true;
        		if(generators.get(i).button.field_146127_k == button.field_146127_k)
        		{
            		generators.get(i).button.field_146124_l = false;
        			generatorSelected = generators.get(i).generatorID;
        		}
        	}
    		selectGenerator();
        }
        else if (button.field_146127_k >= 20 && button.field_146127_k < 30)
        {
        	for(int i = 0; i < settings.size(); i++)
        	{
        		if(settings.get(i).button.field_146127_k == button.field_146127_k)
        		{
        			settings.get(i).click();
        		}
        	}
        	dependencies();
        }
	}
	
	public void drawScreen(int par1, int par2, float par3)
	{
		func_146276_q_();
		
		//title
		String title = "Better World Generation 4";
		drawString(field_146289_q, title, (int) Math.floor(field_146294_l / 2) - (int) Math.floor(field_146289_q.getStringWidth(title) / 2), 10, 16777215);
		
		//category
		drawString(field_146289_q, "Choose a category:", field_146294_l / 2 - 155 + 1, 30, 10526880);
    	if(CATEGORY != 4)
    	{
    		drawString(field_146289_q, "Select a world generator:", field_146294_l / 2 - 155 + 1, 70, 10526880);
    	}
		
    	String catpos = "(" + (CATEGORY + 1) + "/4)";
    	drawString(field_146289_q, catpos, field_146294_l / 2 - 5 - field_146289_q.getStringWidth(catpos), 30, 10526880);
    	
    	if(CATEGORY == 4)
    	{
    		drawString(field_146289_q, "Coming soon!", field_146294_l / 2 - 110 + 1, 110, 10526880);
    	}

		if(generatorSelected != -1)
		{
	    	if(GeneratorType.generatortypes[generatorSelected].HasSettings())
	    	{
	    		drawString(field_146289_q, "Generator settings:", field_146294_l / 2 + 5 + 1, 70, 10526880);
	    	}
		}
		
		super.drawScreen(par1, par2, par3);
	}
	
	public void switchCategory()
	{
		BUTTON_CATEGORY.field_146126_j = categories[CATEGORY];

		if(generators != null)
		{
			for(int i = 0; i < generators.size(); i++)
			{
				field_146292_n.remove(generators.get(i).button);
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
					generators.add(new GuiGeneratorButton(GeneratorType.generatortypes[g].GetScreenName(), g, count + 10, 80 + (20 * count), field_146294_l));
					field_146292_n.add(generators.get(generators.size() - 1).button);
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
    			settings.get(i).button.field_146125_m = false;
    			for(int p = 0; p < settings.get(i).depvalues.length; p++)
    			{
    				if(settings.get(settings.get(i).dependencie - 20).selected == settings.get(i).depvalues[p])
    				{
    					settings.get(i).button.field_146125_m = true;
    				}
    			}
    		}
		}
	}
	
	public void selectGenerator()
	{
		if(generatorSelected > -1)
		{
			BUTTON_DONE.field_146124_l = true;
		}
		else
		{
			BUTTON_DONE.field_146124_l = false;
		}
		
		if(settings != null)
		{
			for(int i = 0; i < settings.size(); i++)
			{
				field_146292_n.remove(settings.get(i).button);
			}
		}
		settings = new ArrayList<GuiSettingsButton>();

		BUTTON_BIOMELIST.field_146125_m = false;
		BUTTON_WORLDSETTINGS.field_146125_m = false;
		if(generatorSelected == GeneratorType.DEFAULT.GetID())
		{
			BUTTON_BIOMELIST.field_146125_m = true;
			BUTTON_WORLDSETTINGS.field_146125_m = true;
			settings.add(new GuiSettingsSlider(new String[]{"Biome size: 1", "Biome size: 2", "Biome size: 3", "Biome size: 4 (default)", "Biome size: 5", "Biome size: 6 (Large)", "Biome size: 7", "Biome size: 8"}, new int[]{1,2,3,4,5,6,7,8}, 3, 20, 120, field_146294_l));
			settings.add(new GuiSettingsButton(new String[]{"Amplified: OFF", "Amplified: ON"}, new int[]{0,1}, 21, 140, field_146294_l));
		}
		else if(generatorSelected == GeneratorType.BETA173.GetID()) 
		{
			settings.add(new GuiSettingsButton(new String[]{"Biomes: Beta", "Biomes: Better Default"}, new int[]{0, 1}, 20, 80, field_146294_l));
		}
		else if(generatorSelected == GeneratorType.INFDEV.GetID() || generatorSelected == GeneratorType.ALPHA11.GetID()) 
		{
			settings.add(new GuiSettingsButton(new String[]{"Snow World: OFF", "Snow World: ON"}, new int[]{0, 1}, 20, 80, field_146294_l));
		}
		else if(generatorSelected == GeneratorType.INDEV.GetID()) 
		{ 
			settings.add(new GuiSettingsButton(new String[]{"Theme: Default", "Theme: Hell", "Theme: Paradise", "Theme: Woods", "Theme: Snow"}, new int[]{0, 1, 2, 3, 4}, 20, 80, field_146294_l));
			settings.add(new GuiSettingsButton(new String[]{"Type: Island", "Type: Floating", "Type: Inland"}, new int[]{0, 1, 2}, 21, 100, field_146294_l));
			settings.add(new GuiSettingsSlider(new String[]{"Size: Small", "Size: Default", "Size: Large"}, new int[]{0, 1, 2}, 1, 22, 120, field_146294_l, 21, new int[]{0, 1}));
			settings.add(new GuiSettingsSlider(new String[]{"Layers: 1", "Layers: 2", "Layers: 3", "Layers: 4", "Layers: 5"}, new int[]{0, 1, 2, 3, 4}, 1, 23, 140, field_146294_l, 21, new int[]{1}));
		}
		else if(generatorSelected == GeneratorType.ISLAND.GetID()) 
		{ 
			settings.add(new GuiSettingsButton(new String[]{"Theme: Default", "Theme: Tropical", "Theme: Paradise"}, new int[]{0, 1, 4}, 20, 80, field_146294_l)); //"Theme: Hell", "Theme: Iceberg",   2, 3,
			settings.add(new GuiSettingsSlider(new String[]{"Size: Small", "Size: Default", "Size: Large"}, new int[]{0, 1, 2}, 1, 21, 100, field_146294_l, 20, new int[]{0, 1})); //, 2, 3
		}
		else if(generatorSelected == GeneratorType.SKYISLAND.GetID()) 
		{ 
			settings.add(new GuiSettingsButton(new String[]{"Theme: Default", "Theme: Snow", "Theme: Jungle"}, new int[]{0, 1, 2}, 20, 80, field_146294_l)); 
		}
		else if(generatorSelected == GeneratorType.SKYBLOCK.GetID())
		{
			settings.add(new GuiSettingsSlider(new String[]{"Size: Small", "Size: Default", "Size: Large"}, new int[]{0, 1, 2}, 1, 20, 80, field_146294_l));
		}
		else if(generatorSelected == GeneratorType.SKYLANDS.GetID()) 
		{ 
			settings.add(new GuiSettingsButton(new String[]{"Biomes: Better Default", "Biomes: Beta 1.7.3"}, new int[]{0, 1}, 20, 80, field_146294_l));
		}
		else if(generatorSelected == GeneratorType.DEADLYDESERT.GetID()) 
		{ 
			settings.add(new GuiSettingsButton(new String[]{"Extremely hard: OFF", "Extremely hard: ON"}, new int[]{0, 1}, 20, 80, field_146294_l));
		}

		for(int s = 0; s < settings.size(); s++)
		{
			field_146292_n.add(settings.get(s).button);
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
        		generators.get(i).button.field_146124_l = true;
        		if(generators.get(i).generatorID == generatorSelected)
        		{
            		generators.get(i).button.field_146124_l = false;
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