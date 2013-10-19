package bwg4.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiSmallButton;

public class BWG4GuiSettingsSlider extends BWG4GuiSettingsButton
{
	public BWG4GuiSettingsSlider(String[] text, int[] values, int def, int buttonID, int posY, int width)
	{
		super(new BWG4GuiSlider(buttonID, width / 2 + 5, posY), text, values, buttonID, -1, new int[0]);
		((BWG4GuiSlider) button).setSlider(this, def);
	}
	
	public BWG4GuiSettingsSlider(String[] text, int[] values, int def, int buttonID, int posY, int width, int dep, int[] vel)
	{
		super(new BWG4GuiSlider(buttonID, width / 2 + 5, posY), text, values, buttonID, dep, vel);
		((BWG4GuiSlider) button).setSlider(this, def);
	}
	
	@Override
	public void click()
	{
	}

	@Override
	public void setOldValue(int oldValue)
	{
		for(int i = 0; i < valuearray.length; i++)
		{
			if(valuearray[i] == oldValue)
			{
				((BWG4GuiSlider) button).sliderValue = Math.round(oldValue) / (float) (valuearray.length - 1);
				((BWG4GuiSlider) button).setText();
				break;
			}
		} 
	}
}
