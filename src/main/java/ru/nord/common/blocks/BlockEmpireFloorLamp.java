package ru.nord.common.blocks;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.abstracts.BlockAbstractRoofLamp;
import ru.nord_core.common.utils.enums.EnumColors;

import java.util.List;

public class BlockEmpireFloorLamp extends BlockAbstractRoofLamp {

    public static final PropertyEnum COLOR = PropertyEnum.create("type", EnumColors.class);

    public BlockEmpireFloorLamp() {
        super(Version.MODID);
        this.setBlockBounds(0.187F, 0.0F, 0.187F, 0.812F, 0.1F, 0.812F);
        setLightLevel(0.9375F);
        setStepSound(soundTypeWood);
        setHardness(0.0F);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, EnumColors.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */@Override
    public int getMetaFromState(IBlockState state)
    {

        return ((EnumColors)state.getValue(COLOR)).getMetadata();
    }
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {COLOR});
    }
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        for (int i = 0; i < 16; ++i)
        {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }
    /**
     * Get the damage value that this Block should drop
     */
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumColors)state.getValue(COLOR)).getMetadata();
    }


    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return ((EnumColors)(state.getValue(COLOR))).getSecondColor();
    }
    private boolean canPlaceOn(World worldIn, BlockPos pos)
    {
        return World.doesBlockHaveSolidTopSurface(worldIn, pos);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return this.canPlaceOn(worldIn, pos.down());
    }

}
