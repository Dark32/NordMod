package ru.nord_core.common.utils.schematic.abstracts;


import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.GameData;
import ru.nord_core.common.utils.schematic.interfaces.ISchematic;

/**
 * Абстрактный Шаблон
 * Содержин основные геттеры и сеттеры
 */
public class ASchematic implements ISchematic {

    protected static final FMLControlledNamespacedRegistry<Block> BLOCK_REGISTRY = GameData.getBlockRegistry();
    private short width;
    private short height;
    private short length;
    private BlockPos posOrigin;

    @Override
    public int getXLayers() {
        return width;
    }

    @Override
    public int getYLayers() {
        return height;
    }

    @Override
    public int getZLayers() {
        return length;
    }

    @Override
    public BlockPos getOrigin() {
        return posOrigin;
    }

    /**
     * Уствновить Х слоёв
     * @param v слоёв
     */
    public void setXLayers(short v) {
        this.width = v;
    }

    /**
     * Установить У слоёв
     * @param v слоёв
     */
    public void setYLayers(short v) {
        this.height = v;
    }

    /**
     * Установить Z слоёв
     * @param v слоёв
     */
    public void setZLayers(short v) {
        this.length = v;
    }

    /**
     * установить центр
     * @param origin
     */
    public void setOrigin(BlockPos origin) {
        this.posOrigin = origin;
    }

    @Override
    public void generate(World world, BlockPos pos) {

    }
}
