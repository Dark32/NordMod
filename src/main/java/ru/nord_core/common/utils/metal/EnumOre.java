package ru.nord_core.common.utils.metal;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata2;
import ru.nord_core.common.utils.enums.interfaces.IMetadata2Enum;

public enum EnumOre implements IMetadata2Enum {
    SILVER("silver"),
    GALENA("galena"),
    TIN("tin"),
    ZINCITE("zincite"),
    BAUXITE("bauxite"),
    UVAROVITE("uvarovite"),
    CROCOITE("crocoite"),
    COPPER("copper"),
    OLIVINE("olivine"),
    MAGNETITE("magnetite"),
    HEMATITE("hematite"),
    LIMONITE("limonite"),
    ALMANDINE("almandine"),
    PENTLANDITE("pentlandite"),
    MILLERITE("millerite"),
    MERCURY("mercury"),
    CINNABAR("cinnabar"),
    MALACHITE("malachite"),;
    private final String name;

    EnumOre(String _name) {
        this.name = _name;
    }

    @Override
    public int getMetadata() {
        return this.ordinal();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean getCrystal() {
        return BindOre.INSTANCE().getCrystal(this);
    }
    public boolean getVanila() {
        return BindOre.INSTANCE().getVanila(this);
    }

    public EnumOre getDrop() {
        return BindOre.INSTANCE().getDrop(this);
    }

    @Override
    public int getReIndexMetadata(ImmutableList list) {
        return list.indexOf(this);
    }

    @Override
    public int getReIndexMetadata(Block block) {
        return ((IVariantMetadata2) block).getAllowedValues().indexOf(this);
    }

    public static EnumOre byMetadata(int meta) {
        return meta < EnumOre.values().length ? EnumOre.values()[meta] : EnumOre.values()[0];
    }

    public static EnumOre byReIndexMetadata(ImmutableList list, int meta) {
        return (EnumOre) list.get(meta);
    }
}
