package ru.nord.common.lib.utils;

public class Things {

    private static final int[] colors = new int[]{
            0x1E1B1B, 0xB3312C, 0x3B511A, 0x51301A,
            0x253192, 0x7B2FBE, 0x287697, 0xABABAB,
            0x434343, 0xD88198, 0x41CD34, 0xDECF2A,
            0x6689D3, 0xC354CD, 0xEB8844, 0xF0F0F0
    };

    public static int getColor(int i) {
        return colors[i & 15];
    }
}
