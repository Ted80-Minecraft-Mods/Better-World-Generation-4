package bwg4.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiSmallButton;

public class BWG4GuiSettingsButton 
{
	public GuiButton button;
	public String[] textarray;
	public int[] valuearray;
	public int selected;
	
	public BWG4GuiSettingsButton(String[] text, int[] values, int buttonID, int posY, int width)
	{
		button = new GuiSmallButton(buttonID, width / 2 + 5, posY, 150, 20, text[0]);
		textarray = text;
		valuearray = values;
		selected = 0;
	}
	
	public void click()
	{
		selected++;
		if(selected >= textarray.length)
		{
			selected = 0;
		}
		button.displayString = textarray[selected];
	}
	
	public void setOldValue(int oldValue)
	{
		selected = 0;
		for(int i = 0; i < valuearray.length; i++)
		{
			if(valuearray[i] == oldValue)
			{
				selected = i;
				button.displayString = textarray[selected];
				break;
			}
		}
	}
}
