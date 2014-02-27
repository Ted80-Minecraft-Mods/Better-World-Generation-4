package bwg4.gui;

import net.minecraft.client.gui.GuiButton;

public class GuiSettingsButton 
{
	public GuiButton button;
	public String[] textarray;
	public int[] valuearray;
	public int selected;
	public int dependencie;
	public int[] depvalues;
	
	public GuiSettingsButton(String[] text, int[] values, int buttonID, int posY, int width)
	{
		this(new GuiButton(buttonID, width / 2 + 5, posY, 150, 20, text[0]), text, values, buttonID, -1, new int[0]);
	}

	public GuiSettingsButton(String[] text, int[] values, int buttonID, int posY, int width, int dep, int[] vel)
	{
		this(new GuiButton(buttonID, width / 2 + 5, posY, 150, 20, text[0]), text, values, buttonID, dep, vel);
	}
	
	public GuiSettingsButton(GuiButton b, String[] text, int[] values, int buttonID, int dep, int[] vel)
	{
		button = b;
		textarray = text;
		valuearray = values;
		selected = 0;
		dependencie = dep;
		depvalues = vel;
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
