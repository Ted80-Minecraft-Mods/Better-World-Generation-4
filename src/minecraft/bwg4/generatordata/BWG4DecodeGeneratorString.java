package bwg4.generatordata;

public class BWG4DecodeGeneratorString 
{
	public static void decode(String generatorString)
	{
		String[] genstring = generatorString.split("#");
		BWG4GeneratorType gentype = getGeneratorFromName(genstring[0]);
		
		if(gentype != null) //GENERATOR NAME
		{
			BWG4GeneratorType.currentGenerator = gentype;
		}
		if(genstring.length > 1) //GENERATOR SETTINGS
		{
			String[] settingstring = genstring[1].split("&");
			BWG4GeneratorType.currentSettings = new int[settingstring.length];
			for(int i = 0; i < settingstring.length; i++)
			{
				BWG4GeneratorType.currentSettings[i] = Integer.parseInt(settingstring[i]);
			}
		}
		if(genstring.length > 2) //BIOME STRING
		{
			BWG4GeneratorType.biomestring = genstring[2];
		}
	}
	
	public static BWG4GeneratorType getGeneratorFromName(String name)
	{
		for(int g = 0; g < BWG4GeneratorType.generatortypes.length; g++)
		{
			if(BWG4GeneratorType.generatortypes[g] != null)
			{
				if(BWG4GeneratorType.generatortypes[g].GetName().equals(name))
				{
					return BWG4GeneratorType.generatortypes[g];
				}
			}
		}
		return null;
	}

	public static int getGeneratorIDFromName(String name)
	{
		for(int g = 0; g < BWG4GeneratorType.generatortypes.length; g++)
		{
			if(BWG4GeneratorType.generatortypes[g] != null)
			{
				if(BWG4GeneratorType.generatortypes[g].GetName().equals(name))
				{
					return BWG4GeneratorType.generatortypes[g].GetID();
				}
			}
		}
		return -1;
	}
}
