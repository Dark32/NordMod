package ru.nord_deco.common.blocks;


import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_deco.common.blocks.abstracts.BlockAbstractChair;
import ru.nord_deco.common.utils.enums.EnumChairOther;

public class BlockChairOther extends BlockAbstractChair {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumChairOther.class);

    public BlockChairOther(String modid) {
        super(modid, Material.cloth);
        setHardness(1.0F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumChairOther.byMetadata(meta);
    }


}