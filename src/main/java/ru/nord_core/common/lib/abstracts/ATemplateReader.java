package ru.nord_core.common.lib.abstracts;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord_core.common.lib.interfaces.ITemplte;

import java.util.HashMap;

/**
 * Абстрактный Шаблон
 * Содержин основные геттеры и сеттеры
 */
public abstract class ATemplateReader implements ITemplte {

    private String rawTemplate;
    protected int xLayers;
    protected int yLayers;
    protected int zLayers;
    protected int xOffset;
    protected int yOffset;
    protected int zOffset;
    protected HashMap<Integer, Block> blockIndex = new HashMap<Integer,Block>(10);
    protected HashMap<Integer, Integer> metadataIndex=new HashMap<Integer, Integer>(10);
    protected HashMap<Integer, String> modIndex=new HashMap<Integer, String>(10);
    protected HashMap<Integer, String> nameIndex=new HashMap<Integer, String>(10);

    @Override
    public void setRawTemplate(String template) {
        this.rawTemplate = template;
    }

    @Override
    public String getRawTemplate() {
        return this.rawTemplate;
    }

    @Override
    public int getXLayers() {
        return this.xLayers;
    }

    @Override
    public int getYLayers() {
        return this.yLayers;
    }

    @Override
    public int getZLayers() {
        return this.zLayers;
    }

    @Override
    public int getXOffset() {
        return xOffset;
    }

    @Override
    public int getYOffset() {
        return yOffset;
    }

    @Override
    public int getZOffset() {
        return zOffset;
    }


    @Override
    public HashMap<Integer, Block> getIndexsesBlock() {
        return blockIndex;
    }

    @Override
    public HashMap<Integer, Integer> getIndexesBlockMetadata() {
        return metadataIndex;
    }

    @Override
    public HashMap<Integer, String> getIndexsesMod() {
        return modIndex;
    }

    @Override
    public HashMap<Integer, String> getIndexsesName() {
        return nameIndex;
    }

    @Override
    public void readBlockIndexes(int index, String modId, String name) {
        Block block = GameRegistry.findBlock(modId,name);
        blockIndex.put(index,block);
    }
}
