package ru.nord.common.lib.utils.enums;

public enum EnumColors {
    WHITE       ( 0xDDDDDD , 0xF0F0F0 ),
    ORANGE      ( 0xDB7D3E , 0xEB8844 ),
    MAGENTA     ( 0xB350BC , 0xC354CD ),
    LIGHT_BLUE  ( 0x6B8AC9 , 0x6689D3 ),
    YELLOW      ( 0xB1A627 , 0xDECF2A ),
    LIME        ( 0x41AE38 , 0x41CD34 ),
    PINK        ( 0xD08499 , 0xD88198 ),
    GRAY        ( 0x404040 , 0x434343 ),
    LIGHT_GRAY  ( 0x9AA1A1 , 0xABABAB ),
    CYAN        ( 0x2E6E89 , 0x287697 ),
    PURPLE      ( 0x7E3DB5 , 0x7B2FBE ),
    BLUE        ( 0x2E388D , 0x253192 ),
    BROWN       ( 0x4F321F , 0x51301A ),
    GREEN       ( 0x35461B , 0x3B511A ),
    RED         ( 0x963430 , 0xB3312C ),
    BLACK       ( 0x191616 , 0x1E1B1B );
    private final int color;
    private final int secondColor;

    EnumColors(int color, int secondColor) {
        this.color = color;
        this.secondColor = secondColor;
    }

    public int getColor() {
        return this.color;
    }

    public int getSecondColor() {
        return this.secondColor;
    }


}
