package bwg4.generatordata;

public class DecodeGeneratorString 
{
	public static void decode(String generatorString)
	{
		String[] genstring = generatorString.split("#");
		GeneratorType gentype = getGeneratorFromName(genstring[0]);
		
		if(gentype != null)
		{
			GeneratorType.Current = gentype;
		}
		
		if(genstring.length >= 2)
		{
			GeneratorType.generatorinfo = genstring[1];
		}
	}
	
	public static GeneratorType getGeneratorFromName(String name)
	{
		for(int g = 0; g < GeneratorType.generatortypes.length; g++)
		{
			if(GeneratorType.generatortypes[g] != null)
			{
				if(GeneratorType.generatortypes[g].GetName().equals(name))
				{
					return GeneratorType.generatortypes[g];
				}
			}
		}
		return null;
	}
}
