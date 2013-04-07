package ted80.bwg4.noise;

import java.util.Random;

public class BWG4NoisePerlinAlpha extends BWG4oldNoiseGenerator2
{

    public BWG4NoisePerlinAlpha()
    {
        this(new Random());
    }

    public BWG4NoisePerlinAlpha(Random random)
    {
        permutations = new int[512];
        x_02 = random.nextDouble() * 256D;
        y_02 = random.nextDouble() * 256D;
        z_02 = random.nextDouble() * 256D;
        for(int i = 0; i < 256; i++)
        {
            permutations[i] = i;
        }

        for(int j = 0; j < 256; j++)
        {
            int k = random.nextInt(256 - j) + j;
            int l = permutations[j];
            permutations[j] = permutations[k];
            permutations[k] = l;
            permutations[j + 256] = permutations[j];
        }

    }

    public double generateNoise(double d, double d1, double d2)
    {
        double d3 = d + x_02;
        double d4 = d1 + y_02;
        double d5 = d2 + z_02;
        int i = (int)d3;
        int j = (int)d4;
        int k = (int)d5;
        if(d3 < (double)i)
        {
            i--;
        }
        if(d4 < (double)j)
        {
            j--;
        }
        if(d5 < (double)k)
        {
            k--;
        }
        int l = i & 0xff;
        int i1 = j & 0xff;
        int j1 = k & 0xff;
        d3 -= i;
        d4 -= j;
        d5 -= k;
        double d6 = d3 * d3 * d3 * (d3 * (d3 * 6D - 15D) + 10D);
        double d7 = d4 * d4 * d4 * (d4 * (d4 * 6D - 15D) + 10D);
        double d8 = d5 * d5 * d5 * (d5 * (d5 * 6D - 15D) + 10D);
        int k1 = permutations[l] + i1;
        int l1 = permutations[k1] + j1;
        int i2 = permutations[k1 + 1] + j1;
        int j2 = permutations[l + 1] + i1;
        int k2 = permutations[j2] + j1;
        int l2 = permutations[j2 + 1] + j1;
        return lerp(d8, lerp(d7, lerp(d6, grad(permutations[l1], d3, d4, d5), grad(permutations[k2], d3 - 1.0D, d4, d5)), lerp(d6, grad(permutations[i2], d3, d4 - 1.0D, d5), grad(permutations[l2], d3 - 1.0D, d4 - 1.0D, d5))), lerp(d7, lerp(d6, grad(permutations[l1 + 1], d3, d4, d5 - 1.0D), grad(permutations[k2 + 1], d3 - 1.0D, d4, d5 - 1.0D)), lerp(d6, grad(permutations[i2 + 1], d3, d4 - 1.0D, d5 - 1.0D), grad(permutations[l2 + 1], d3 - 1.0D, d4 - 1.0D, d5 - 1.0D))));
    }

    public final double lerp(double d, double d1, double d2)
    {
        return d1 + d * (d2 - d1);
    }

    public final double func_4110_a(int i, double d, double d1)
    {
        int j = i & 0xf;
        double d2 = (double)(1 - ((j & 8) >> 3)) * d;
        double d3 = j >= 4 ? j != 12 && j != 14 ? d1 : d : 0.0D;
        return ((j & 1) != 0 ? -d2 : d2) + ((j & 2) != 0 ? -d3 : d3);
    }

    public final double grad(int i, double d, double d1, double d2)
    {
        int j = i & 0xf;
        double d3 = j >= 8 ? d1 : d;
        double d4 = j >= 4 ? j != 12 && j != 14 ? d2 : d : d1;
        return ((j & 1) != 0 ? -d3 : d3) + ((j & 2) != 0 ? -d4 : d4);
    }

    public double func_801_a(double d, double d1)
    {
        return generateNoise(d, d1, 0.0D);
    }

    public void func_805_a(double ad[], double d, double d1, double d2, 
            int i, int j, int k, double d3, double d4, 
            double d5, double d6)
    {
        if(j == 1)
        {
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            boolean flag6 = false;
            double d8 = 0.0D;
            double d10 = 0.0D;
            double d11 = 0.0D;
            double d14 = 0.0D;
            int j4 = 0;
            double d17 = 1.0D / d6;
            for(int k4 = 0; k4 < i; k4++)
            {
                double d19 = (d + (double)k4) * d3 + x_02;
                int i5 = (int)d19;
                if(d19 < (double)i5)
                {
                    i5--;
                }
                int j5 = i5 & 0xff;
                d19 -= i5;
                double d21 = d19 * d19 * d19 * (d19 * (d19 * 6D - 15D) + 10D);
                for(int i6 = 0; i6 < k; i6++)
                {
                    double d23 = (d2 + (double)i6) * d5 + z_02;
                    int k6 = (int)d23;
                    if(d23 < (double)k6)
                    {
                        k6--;
                    }
                    int l6 = k6 & 0xff;
                    d23 -= k6;
                    double d25 = d23 * d23 * d23 * (d23 * (d23 * 6D - 15D) + 10D);
                    int l = permutations[j5] + 0;
                    int j1 = permutations[l] + l6;
                    int k1 = permutations[l + 1] + l6;
                    int l1 = permutations[j5 + 1] + 0;
                    int j2 = permutations[l1] + l6;
                    int l2 = permutations[l1 + 1] + l6;
                    double d9 = lerp(d21, func_4110_a(permutations[j1], d19, d23), grad(permutations[j2], d19 - 1.0D, 0.0D, d23));
                    double d12 = lerp(d21, grad(permutations[j1 + 1], d19, 0.0D, d23 - 1.0D), grad(permutations[j2 + 1], d19 - 1.0D, 0.0D, d23 - 1.0D));
                    double d27 = lerp(d25, d9, d12);
                    ad[j4++] += d27 * d17;
                }

            }

            return;
        }
        int i1 = 0;
        double d7 = 1.0D / d6;
        int i2 = -1;
        boolean flag5 = false;
        boolean flag7 = false;
        boolean flag8 = false;
        boolean flag9 = false;
        boolean flag10 = false;
        boolean flag11 = false;
        double d13 = 0.0D;
        double d15 = 0.0D;
        double d16 = 0.0D;
        double d18 = 0.0D;
        for(int l4 = 0; l4 < i; l4++)
        {
            double d20 = (d + (double)l4) * d3 + x_02;
            int k5 = (int)d20;
            if(d20 < (double)k5)
            {
                k5--;
            }
            int l5 = k5 & 0xff;
            d20 -= k5;
            double d22 = d20 * d20 * d20 * (d20 * (d20 * 6D - 15D) + 10D);
            for(int j6 = 0; j6 < k; j6++)
            {
                double d24 = (d2 + (double)j6) * d5 + z_02;
                int i7 = (int)d24;
                if(d24 < (double)i7)
                {
                    i7--;
                }
                int j7 = i7 & 0xff;
                d24 -= i7;
                double d26 = d24 * d24 * d24 * (d24 * (d24 * 6D - 15D) + 10D);
                for(int k7 = 0; k7 < j; k7++)
                {
                    double d28 = (d1 + (double)k7) * d4 + y_02;
                    int l7 = (int)d28;
                    if(d28 < (double)l7)
                    {
                        l7--;
                    }
                    int i8 = l7 & 0xff;
                    d28 -= l7;
                    double d29 = d28 * d28 * d28 * (d28 * (d28 * 6D - 15D) + 10D);
                    if(k7 == 0 || i8 != i2)
                    {
                        i2 = i8;
                        int k2 = permutations[l5] + i8;
                        int i3 = permutations[k2] + j7;
                        int j3 = permutations[k2 + 1] + j7;
                        int k3 = permutations[l5 + 1] + i8;
                        int l3 = permutations[k3] + j7;
                        int i4 = permutations[k3 + 1] + j7;
                        d13 = lerp(d22, grad(permutations[i3], d20, d28, d24), grad(permutations[l3], d20 - 1.0D, d28, d24));
                        d15 = lerp(d22, grad(permutations[j3], d20, d28 - 1.0D, d24), grad(permutations[i4], d20 - 1.0D, d28 - 1.0D, d24));
                        d16 = lerp(d22, grad(permutations[i3 + 1], d20, d28, d24 - 1.0D), grad(permutations[l3 + 1], d20 - 1.0D, d28, d24 - 1.0D));
                        d18 = lerp(d22, grad(permutations[j3 + 1], d20, d28 - 1.0D, d24 - 1.0D), grad(permutations[i4 + 1], d20 - 1.0D, d28 - 1.0D, d24 - 1.0D));
                    }
                    double d30 = lerp(d29, d13, d15);
                    double d31 = lerp(d29, d16, d18);
                    double d32 = lerp(d26, d30, d31);
                    ad[i1++] += d32 * d7;
                }

            }

        }

    }

    private int permutations[];
    public double x_02;
    public double y_02;
    public double z_02;
}
