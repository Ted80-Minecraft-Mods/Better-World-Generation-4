package bwg4.generatordata;

public class BWG4DecodeGeneratorString 
{
	public static void decode(String generatorString)
	{
		String[] genstring = generatorString.split("#");
		BWG4GeneratorType gentype = getGeneratorFromName(genstring[0]);
		
		if(gentype != null)
		{
			BWG4GeneratorType.Current = gentype;
		}
		
		if(genstring.length >= 2)
		{
			BWG4GeneratorType.generatorinfo = genstring[1];
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
}
