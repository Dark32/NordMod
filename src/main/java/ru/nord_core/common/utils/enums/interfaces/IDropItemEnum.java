package ru.nord_core.common.utils.enums.interfaces;

import net.minecraft.item.ItemStack;

/**
 * Created by andrew on 08.01.16.
 * Отвечает за замену дропа
 * Используется, как пример, в листве {@link ru.nord_core.common.blocks.abstracts.BlockAbstractLeaves#dropApple}
 * Реализуется в enum {@link ru.nord.common.utils.enums.EnumNordPlank}
 */
public interface IDropItemEnum {
    /**
     * Что ронять
     * @return предмет
     */
    ItemStack dropItem();
}
