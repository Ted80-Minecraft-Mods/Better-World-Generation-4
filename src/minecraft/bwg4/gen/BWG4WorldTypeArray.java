package bwg4.gen;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.world.WorldType;

public class BWG4WorldTypeArray 
{
	/*
	public static void changeArraySize()
	{
		try 
		{
			Field field = WorldType.class.getField("worldTypes");
			Object newValue = new WorldType[32];
	        field.setAccessible(true);
	        Field modifiersField = Field.class.getDeclaredField("modifiers");
	        modifiersField.setAccessible(true);
	        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
	        field.set(null, newValue);
		}
		catch(Exception e)
		{
			System.out.println("cannot change worldtypes list length");
		}	
	}
	*/
}
