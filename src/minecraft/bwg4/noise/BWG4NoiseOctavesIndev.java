package bwg4.noise;

import java.util.Random;

public class BWG4NoiseOctavesIndev extends BWG4oldNoiseGenerator2
{

    public BWG4NoiseOctavesIndev(Random random, int i)
    {
        field_1191_b = i;
        field_1192_a = new BWG4NoisePerlinIndev[i];
        for(int j = 0; j < i; j++)
        {
            field_1192_a[j] = new BWG4NoisePerlinIndev(random);
        }

    }

	public final double a(double paramDouble1, double paramDouble2)
	{
		double d1 = 0.0D;
		double d2 = 1.0D;
		for (int i = 0; i < this.field_1191_b; i++)
		{
			d1 += this.field_1192_a[i].a(paramDouble1 / d2, paramDouble2 / d2) * d2;
			d2 *= 2.0D;
		}
		return d1;
	}

	public final double a(double paramDouble1, double paramDouble2, double paramDouble3)
	{
		double d1 = 0.0D;
		double d2 = 1.0D;
		for (int i = 0; i < this.field_1191_b; i++)
		{
			d1 += this.field_1192_a[i].a(paramDouble1 / d2, 0.0D / d2, paramDouble3 / d2) * d2;
			d2 *= 2.0D;
		}
		return d1;
	}	

    private BWG4NoisePerlinIndev field_1192_a[];
    private int field_1191_b;
}
