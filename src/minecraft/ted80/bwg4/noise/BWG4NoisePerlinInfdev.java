package ted80.bwg4.noise;

import java.util.Random;

import net.minecraft.world.gen.NoiseGenerator;

public class BWG4NoisePerlinInfdev extends NoiseGenerator
{

    public BWG4NoisePerlinInfdev()
    {
        this(new Random());
    }

    public BWG4NoisePerlinInfdev(Random random)
    {
        field_1189_d = new int[512];
        field_1188_a = random.nextDouble() * 256D;
        field_1187_b = random.nextDouble() * 256D;
        field_1190_c = random.nextDouble() * 256D;
        for(int i = 0; i < 256; i++)
        {
            field_1189_d[i] = i;
        }

        for(int j = 0; j < 256; j++)
        {
            int k = random.nextInt(256 - j) + j;
            int l = field_1189_d[j];
            field_1189_d[j] = field_1189_d[k];
            field_1189_d[k] = l;
            field_1189_d[j + 256] = field_1189_d[j];
        }

    }

    public double func_802_a(double d, double d1, double d2)
    {
        double d3 = d + field_1188_a;
        double d4 = d1 + field_1187_b;
        double d5 = d2 + field_1190_c;
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
        int k1 = field_1189_d[l] + i1;
        int l1 = field_1189_d[k1] + j1;
        int i2 = field_1189_d[k1 + 1] + j1;
        int j2 = field_1189_d[l + 1] + i1;
        int k2 = field_1189_d[j2] + j1;
        int l2 = field_1189_d[j2 + 1] + j1;
        return func_804_b(d8, func_804_b(d7, func_804_b(d6, func_803_a(field_1189_d[l1], d3, d4, d5), func_803_a(field_1189_d[k2], d3 - 1.0D, d4, d5)), func_804_b(d6, func_803_a(field_1189_d[i2], d3, d4 - 1.0D, d5), func_803_a(field_1189_d[l2], d3 - 1.0D, d4 - 1.0D, d5))), func_804_b(d7, func_804_b(d6, func_803_a(field_1189_d[l1 + 1], d3, d4, d5 - 1.0D), func_803_a(field_1189_d[k2 + 1], d3 - 1.0D, d4, d5 - 1.0D)), func_804_b(d6, func_803_a(field_1189_d[i2 + 1], d3, d4 - 1.0D, d5 - 1.0D), func_803_a(field_1189_d[l2 + 1], d3 - 1.0D, d4 - 1.0D, d5 - 1.0D))));
    }

    public double func_804_b(double d, double d1, double d2)
    {
        return d1 + d * (d2 - d1);
    }

    public double func_803_a(int i, double d, double d1, double d2)
    {
        int j = i & 0xf;
        double d3 = j >= 8 ? d1 : d;
        double d4 = j >= 4 ? j != 12 && j != 14 ? d2 : d : d1;
        return ((j & 1) != 0 ? -d3 : d3) + ((j & 2) != 0 ? -d4 : d4);
    }

    public double func_801_a(double d, double d1)
    {
        return func_802_a(d, d1, 0.0D);
    }

    public void func_805_a(double ad[], double d, double d1, double d2, 
            int i, int j, int k, double d3, double d4, 
            double d5, double d6)
    {
        int l = 0;
        double d7 = 1.0D / d6;
        int i1 = -1;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        boolean flag5 = false;
        double d8 = 0.0D;
        double d9 = 0.0D;
        double d10 = 0.0D;
        double d11 = 0.0D;
        for(int l2 = 0; l2 < i; l2++)
        {
            double d12 = (d + (double)l2) * d3 + field_1188_a;
            int i3 = (int)d12;
            if(d12 < (double)i3)
            {
                i3--;
            }
            int j3 = i3 & 0xff;
            d12 -= i3;
            double d13 = d12 * d12 * d12 * (d12 * (d12 * 6D - 15D) + 10D);
            for(int k3 = 0; k3 < k; k3++)
            {
                double d14 = (d2 + (double)k3) * d5 + field_1190_c;
                int l3 = (int)d14;
                if(d14 < (double)l3)
                {
                    l3--;
                }
                int i4 = l3 & 0xff;
                d14 -= l3;
                double d15 = d14 * d14 * d14 * (d14 * (d14 * 6D - 15D) + 10D);
                for(int j4 = 0; j4 < j; j4++)
                {
                    double d16 = (d1 + (double)j4) * d4 + field_1187_b;
                    int k4 = (int)d16;
                    if(d16 < (double)k4)
                    {
                        k4--;
                    }
                    int l4 = k4 & 0xff;
                    d16 -= k4;
                    double d17 = d16 * d16 * d16 * (d16 * (d16 * 6D - 15D) + 10D);
                    if(j4 == 0 || l4 != i1)
                    {
                        i1 = l4;
                        int j1 = field_1189_d[j3] + l4;
                        int k1 = field_1189_d[j1] + i4;
                        int l1 = field_1189_d[j1 + 1] + i4;
                        int i2 = field_1189_d[j3 + 1] + l4;
                        int j2 = field_1189_d[i2] + i4;
                        int k2 = field_1189_d[i2 + 1] + i4;
                        d8 = func_804_b(d13, func_803_a(field_1189_d[k1], d12, d16, d14), func_803_a(field_1189_d[j2], d12 - 1.0D, d16, d14));
                        d9 = func_804_b(d13, func_803_a(field_1189_d[l1], d12, d16 - 1.0D, d14), func_803_a(field_1189_d[k2], d12 - 1.0D, d16 - 1.0D, d14));
                        d10 = func_804_b(d13, func_803_a(field_1189_d[k1 + 1], d12, d16, d14 - 1.0D), func_803_a(field_1189_d[j2 + 1], d12 - 1.0D, d16, d14 - 1.0D));
                        d11 = func_804_b(d13, func_803_a(field_1189_d[l1 + 1], d12, d16 - 1.0D, d14 - 1.0D), func_803_a(field_1189_d[k2 + 1], d12 - 1.0D, d16 - 1.0D, d14 - 1.0D));
                    }
                    double d18 = func_804_b(d17, d8, d9);
                    double d19 = func_804_b(d17, d10, d11);
                    double d20 = func_804_b(d15, d18, d19);
                    ad[l++] += d20 * d7;
                }

            }

        }

    }

    private int field_1189_d[];
    public double field_1188_a;
    public double field_1187_b;
    public double field_1190_c;
}
