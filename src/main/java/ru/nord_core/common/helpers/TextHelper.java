package ru.nord_core.common.helpers;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

/**
 * Created by andrew on 03.04.16.
 */
public class TextHelper {
    public static String translate(String i18n) {
        return I18n.translateToLocal(i18n);
    }

    public static String translateColor(TextFormatting color, String i18n) {
        return color + I18n.translateToLocal(i18n);
    }

    public static String translateFormat(String i18n, Object... args) {
        return String.format(translate(i18n), args);
    }

    public static String translateFormatColor(TextFormatting color, String i18n, Object... args) {
        return color + String.format(translate(i18n), args);
    }

    public static ITextComponent componentTranslateFormat(String i18n, Object... args) {
        return new TextComponentString(translateFormat(i18n, args));
    }

    public static ITextComponent componentTranslateFormatColor(TextFormatting color, String i18n, Object... args) {
        return new TextComponentString(translateFormatColor(color, i18n, args));
    }

    public static ITextComponent componentTranslate(String i18n) {
        return new TextComponentString(translate(i18n));
    }
}
