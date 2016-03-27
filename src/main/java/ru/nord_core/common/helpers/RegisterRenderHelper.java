package ru.nord_core.common.helpers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import ru.nord.Nord;
import ru.nord_core.NordCore;
import ru.nord_core.common.utils.enums.EnumOreDrop;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

/**
 * Заглушка. На серверной стороне нет необходимости регистрировать модели для предметов
 * {@link ru.nord_core.client.helpers.RegisterRenderHelper} для просмотра реализации
 */
public class RegisterRenderHelper {



    public static final RegisterRenderHelper INSTANCE = new RegisterRenderHelper();

    public static RegisterRenderHelper modelRegister(){
        return  NordCore.proxy.registerModel();
    }
    /**
     * Регистрация модели для блока по его имени регистрации
     *
     * @param block блок
     */
    public void registerBlockItemModel(Block block) {
    }

    /**
     * Регистраци модели для блока по пути к ресурсу
     *
     * @param block         блок
     * @param modelLocation путь к ресурсу
     */
    public void registerBlockItemModel(Block block, String modelLocation) {
    }

    /**
     * Регистрация модели для блока с методатой по соотношению вариантов
     * {@code registerBlockItemModelForMeta(NordBloks.variantTest, enumType.getMeta(), "variant=" + enumType.getName());}
     *
     * @param block    блок
     * @param metadata методата
     * @param variant  соотношение вариантов
     */
    public void registerBlockItemModelForMeta(Block block, int metadata, String variant) {
    }

    /**
     * Регистрация модели для ведра
     *
     * @param item предмет ведра
     */

    public void registerBucketModel(Item item) {
    }

    /**
     * Регистрация модели для предмета по его имени в регистрации
     *
     * @param item предмет
     */
    public void registerItemModel(Item item) {
    }

    /**
     * Регистраия модели для предмета по пути к есурсу
     *
     * @param item          предмет
     * @param modelLocation путь к ресурсу
     */
    public void registerItemModel(Item item, String modelLocation) {
    }

    /**
     * Регистрация модели для предмета по ресурсу
     *
     * @param item              предмет
     * @param fullModelLocation ресурс
     */
    public void registerItemModel(Item item, ModelResourceLocation fullModelLocation) {
    }

    /**
     * Регистрируем модель для предмета по мешу
     *
     * @param item           предмет
     * @param meshDefinition меш
     */
    public void registerItemModel(Item item, ItemMeshDefinition meshDefinition) {
    }

    /**
     * Регистрируем модель для предмета с методатой по соотношению с вариантом
     * Пример {@code registerBlockItemModelForMeta(NordBloks.variantTest, enumType.getMeta(), "variant=" + enumType.getName());}
     *
     * @param item     предмет
     * @param metadata метадата
     * @param variant  вариант
     */
    public void registerItemModelForMeta(Item item, int metadata, String variant) {
    }

    /**
     * Регистрируем модель для предмета с методатой по соотношению с вариантом на основе Enum
     * Пример {@code registerBlockItemModelForMeta(NordBloks.variantTest, enumType.getMeta(), "variant=" + enumType.getName());}
     *
     * @param item     предмет
     * @param enum_    Перечесление
     * @param _prefix  вариант
     */
    public void registerItemModelForMeta(Item item, IMetadataEnum enum_, String _prefix) {
        registerItemModelForMeta(item, enum_.getMetadata(), _prefix + enum_.getName());
    }

    /**
     * Регистрируем модель для предмете с методатой по ресурсу
     *
     * @param item                  предмет
     * @param metadata              методата
     * @param modelResourceLocation ресурс
     */
    public void registerItemModelForMeta(Item item, int metadata, ModelResourceLocation modelResourceLocation) {
    }

}
