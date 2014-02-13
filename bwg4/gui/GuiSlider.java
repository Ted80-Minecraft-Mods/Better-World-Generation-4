package bwg4.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiSlider extends GuiButton
{
    public float sliderValue = 1.0F;
    public boolean dragging;

    public GuiSettingsSlider settings;
	public int max;
	public int selected;

    public GuiSlider(int id, int x, int y)
    {
        super(id, x, y, 150, 20, "");
    }
    
    public void setSlider(GuiSettingsSlider s, int def)
    {
    	settings = s;
    	max = settings.valuearray.length - 1;
    	sliderValue = Math.round(def) / (float) max;
    	
    	setText();
    }
    
    public void setText()
    {
    	int pos = Math.round(sliderValue * max);
    	
    	field_146126_j = settings.textarray[pos];
    	settings.selected = settings.valuearray[pos];
    }

    protected int func_146114_a(boolean par1)
    {
        return 0;
    }

    protected void func_146119_b(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.field_146125_m)
        {
            if (this.dragging)
            {
                this.sliderValue = (float)(par2 - (this.field_146128_h + 4)) / (float)(this.field_146120_f - 8);

                if (this.sliderValue < 0.0F)
                {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F)
                {
                    this.sliderValue = 1.0F;
                }
            }
            
            setText();

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.field_146128_h + (int)(this.sliderValue * (float)(this.field_146120_f - 8)), this.field_146129_i, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.field_146128_h + (int)(this.sliderValue * (float)(this.field_146120_f - 8)) + 4, this.field_146129_i, 196, 66, 4, 20);
        }
    }

    public boolean func_146116_c(Minecraft par1Minecraft, int par2, int par3)
    {
        if (super.func_146116_c(par1Minecraft, par2, par3))
        {
            this.sliderValue = (float)(par2 - (this.field_146128_h + 4)) / (float)(this.field_146120_f - 8);
            
            if (this.sliderValue < 0.0F)
            {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F)
            {
                this.sliderValue = 1.0F;
            }
            
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void func_146118_a(int par1, int par2)
    {
        this.dragging = false;
        
        sliderValue = Math.round(sliderValue * max) / (float) max;
        
        setText();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(this.field_146128_h + (int)(this.sliderValue * (float)(this.field_146120_f - 8)), this.field_146129_i, 0, 66, 4, 20);
        this.drawTexturedModalRect(this.field_146128_h + (int)(this.sliderValue * (float)(this.field_146120_f - 8)) + 4, this.field_146129_i, 196, 66, 4, 20);
    }
}
