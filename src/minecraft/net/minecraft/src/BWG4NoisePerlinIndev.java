package net.minecraft.src;

import java.util.Random;

public class BWG4NoisePerlinIndev extends BWG4oldNoiseGenerator2
{
	private int[] a = new int[512];
	private double b;
	private double c;
	private double d;

    public BWG4NoisePerlinIndev()
    {
        this(new Random());
    }

	public BWG4NoisePerlinIndev(Random paramRandom)
	{
		this.b = (paramRandom.nextDouble() * 256.0D);
		this.c = (paramRandom.nextDouble() * 256.0D);
		this.d = (paramRandom.nextDouble() * 256.0D);
		for (int i = 0; i < 256; i++)
		{
			this.a[i] = i;
		}
		for (int i2 = 0; i2 < 256; i2++)
		{
			int j = paramRandom.nextInt(256 - i2) + i2;
			int k = this.a[i2];
			this.a[i2] = this.a[j];
			this.a[j] = k;
			this.a[(i2 + 256)] = this.a[i2];
		}
	}

    private double b(double paramDouble1, double paramDouble2, double paramDouble3)
    {
        double paramDouble4 = paramDouble1 + b;
        double paramDouble5 = paramDouble2 + c;
        double paramDouble6 = paramDouble3 + d;
        paramDouble1 = MathHelper.floor_double(paramDouble4) & 0xff;
        double k = MathHelper.floor_double(paramDouble5) & 0xff;
        paramDouble2 =  MathHelper.floor_double(paramDouble6) & 0xff;
        paramDouble4 -=  MathHelper.floor_double(paramDouble4);
        paramDouble5 -=  MathHelper.floor_double(paramDouble5);
        paramDouble6 -=  MathHelper.floor_double(paramDouble6);
        double paramDouble7 = a(paramDouble4);
        double paramDouble8 = a(paramDouble5);
        double paramDouble9 = a(paramDouble6);
        double l = a[(int)paramDouble1] + k;
        paramDouble3 = a[(int)l] + paramDouble2;
        l = a[(int)l + 1] + paramDouble2;
        paramDouble1 = a[(int)paramDouble1 + 1] + k;
        k = a[(int)paramDouble1] + paramDouble2;
        paramDouble1 = a[(int)paramDouble1 + 1] + paramDouble2;
        return c(paramDouble9, c(paramDouble8, c(paramDouble7, a(a[(int)paramDouble3], paramDouble4, paramDouble5, paramDouble6), a(a[(int)k], paramDouble4 - 1.0D, paramDouble5, paramDouble6)), c(paramDouble7, a(a[(int)l], paramDouble4, paramDouble5 - 1.0D, paramDouble6), a(a[(int)paramDouble1], paramDouble4 - 1.0D, paramDouble5 - 1.0D, paramDouble6))), c(paramDouble8, c(paramDouble7, a(a[(int)paramDouble3 + 1], paramDouble4, paramDouble5, paramDouble6 - 1.0D), a(a[(int)k + 1], paramDouble4 - 1.0D, paramDouble5, paramDouble6 - 1.0D)), c(paramDouble7,  a(a[(int)l + 1], paramDouble4, paramDouble5 - 1.0D, paramDouble6 - 1.0D), a(a[(int)paramDouble1 + 1], paramDouble4 - 1.0D, paramDouble5 - 1.0D, paramDouble6 - 1.0D))));
    }	
	
	private static double a(double paramDouble)
	{
		return paramDouble * paramDouble * paramDouble * (paramDouble * (paramDouble * 6.0D - 15.0D) + 10.0D);
	}

	private static double c(double paramDouble1, double paramDouble2, double paramDouble3)
	{
		return paramDouble2 + paramDouble1 * (paramDouble3 - paramDouble2);
	}

	private static double a(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3)
	{
		double d1 = (paramInt &= 15) < 8 ? paramDouble1 : paramDouble2;
		double d2 = (paramInt == 12) || (paramInt == 14) ? paramDouble1 : paramInt < 4 ? paramDouble2 : paramDouble3;
		return ((paramInt & 0x1) == 0 ? d1 : -d1) + ((paramInt & 0x2) == 0 ? d2 : -d2);
	}

	public final double a(double paramDouble1, double paramDouble2)
	{
		return b(paramDouble1, paramDouble2, 0.0D);
	}

	public final double a(double paramDouble1, double paramDouble2, double paramDouble3)
	{
		return b(paramDouble1, paramDouble2, paramDouble3);
	}
}