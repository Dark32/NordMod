package ru.nord_core.client.helpers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import ru.nord.Nord;
import ru.nord_core.NordCore;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public class RegisterRenderHelper extends ru.nord_core.common.helpers.RegisterRenderHelper {

    public static final RegisterRenderHelper INSTANCE = new RegisterRenderHelper();

    public static RegisterRenderHelper modelRegister(){
        return (RegisterRenderHelper) NordCore.proxy.registerModel();
    }

    /**
     * Регистрация модели для блока по его имени ркгистрации
     * @param block блок
     */
    @Override
    public void registerBlockItemModel(Block block) {
        Item item = Item.getItemFromBlock(block);
        if (item != null) {
            registerItemModel(item);
        }
    }

    /**
     * Регистраци модели для блока по пути к ресурсу
     * @param block блок
     * @param modelLocation путь к ресурсу
     */
    @Override
    public void registerBlockItemModel(Block block, String modelLocation) {
        registerItemModel(Item.getItemFromBlock(block), modelLocation);
    }

    /**
     * Регистрация модели для блока с методатой по соотношению вариантов
     * {@code registerBlockItemModelForMeta(NordBloks.variantTest, enumType.getMeta(), "variant=" + enumType.getName());}
     * @param block блок
     * @param metadata методата
     * @param variant соотношение вариантов
     */
    @Override
    public void registerBlockItemModelForMeta(Block block, int metadata, String variant) {
        registerItemModelForMeta(Item.getItemFromBlock(block), metadata, variant);
    }

    /**
     * Регистрация модели для ведра
     * @param item предмет ведра
     */
    @Override
    public void registerBucketModel(Item item) {
        ModelLoader.setBucketModelDefinition(item);
    }

    /**
     * Регистрация модели для предмета по его имени в регистрации
     * @param item предмет
     */
    @Override
    public void registerItemModel(Item item) {
        registerItemModel(item, item.getRegistryName());
    }

    /**
     * Регистраия модели для предмета по пути к есурсу
     * @param item предмет
     * @param modelLocation путь к ресурсу
     */
    @Override
    public void registerItemModel(Item item, String modelLocation) {
        final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
        registerItemModel(item, fullModelLocation);
    }

    /**
     * Регистрация модели для предмета по ресурсу
     * @param item предмет
     * @param fullModelLocation ресурс
     */
    @Override
    public void registerItemModel(Item item, final ModelResourceLocation fullModelLocation) {
        ModelBakery.registerItemVariants(item, fullModelLocation); // Ensure the custom model is loaded and prevent the default model from being loaded
//        registerItemModel(item, MeshDefinitionFix.create(stack -> fullModelLocation));
        registerItemModel(item,new ItemMesh(fullModelLocation));
    }

    /**
     * Регистрируем модель для предмета по мешу
     * @param item предмет
     * @param meshDefinition меш
     */
    @Override
    public void registerItemModel(Item item, ItemMeshDefinition meshDefinition) {
        ModelLoader.setCustomMeshDefinition(item, meshDefinition);
    }

    /**
     * Регистрируем модель для предмета с методатой по соотношению с вариантом
     * Пример {@code registerBlockItemModelForMeta(NordBloks.variantTest, enumType.getMeta(), "variant=" + enumType.getName());}
     * @param item предмет
     * @param metadata метадата
     * @param variant вариант
     */
    @Override
    public void registerItemModelForMeta(Item item, int metadata, String variant) {
        registerItemModelForMeta(item, metadata, new ModelResourceLocation(item.getRegistryName(), variant));
    }

    /**
     * Регистрируем модель для предмете с методатой по ресурсу
     * @param item предмет
     * @param metadata методата
     * @param modelResourceLocation ресурс
     */
    @Override
    public void registerItemModelForMeta(Item item, int metadata, ModelResourceLocation modelResourceLocation) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, modelResourceLocation);
    }

    /**
     * Регистрируем модель для предмета с методатой по соотношению с вариантом на основе Enum
     * Пример {@code registerBlockItemModelForMeta(NordBloks.variantTest, enumType.getMeta(), "variant=" + enumType.getName());}
     *
     * @param item     предмет
     * @param enum_    Перечесление
     * @param _prefix  вариант
     */
//    @Override
//    public void registerItemModelForMeta(Item item, IMetadataEnum enum_, String _prefix) {
//        registerItemModelForMeta(item, enum_.getMetadata(), _prefix + enum_.getName());
//    }
}
