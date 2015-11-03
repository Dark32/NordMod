package ru.nord.common.blocks;


import com.sun.org.apache.bcel.internal.generic.SWITCH;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumPaperEmp;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.EnumCrystal;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public class BlockEmpPaper extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumPaperEmp.class);
    public BlockEmpPaper(String[] names) {
        super(Material.iron, names, EnumPaperEmp.class, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumPaperEmp.byMetadata(meta));
    }
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{TYPE});
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((IMetadataEnum)state.getValue(TYPE)).getMetadata();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((IMetadataEnum)state.getValue(TYPE)).getMetadata();
    }

}