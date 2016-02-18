package ru.nord_core.common.items;


import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.client.utils.RenderUtils;
import ru.nord_core.common.helpers.NBTHelper;
import ru.nord_core.common.items.interfaces.ISelectItem;
import ru.nord_core.common.utils.Version;
import ru.nord_core.common.utils.schematic.Schematic;
import ru.nord_core.common.utils.schematic.SchematicUtils;

import java.io.File;
import java.util.List;

import static ru.nord_core.common.items.ItemDebugStickSchematicSave.EnumSchematicPosition;


public class ItemDebugStickSchematicLoad extends ItemBase implements ISelectItem {

    public ItemDebugStickSchematicLoad(String modid) {
        super(modid);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabAllSearch);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return false;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();
        if (playerIn.isSneaking()) {
            if (!tag.hasKey("SchematicIndex")) {
                tag.setInteger("SchematicIndex", 0);
            }
            int index = tag.getInteger("SchematicIndex");
            File[] list = SchematicUtils.get().getSchematicks();
            File file = list[index];
            Schematic schem = new Schematic();
            schem.getFromFile(file.getName());
            SchematicUtils.schemMap.put(file.getName(), schem);
            tag.setString("SchematicName", file.getName());
            playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + StatCollector.translateToLocal("debug.description.load.msg") + file.getName()));
            index = list.length - 1 > index ? index + 1 : 0;
            tag.setInteger("SchematicIndex", index);
            for (EnumSchematicPosition _enum : EnumSchematicPosition.values()) {
                if (tag.hasKey(_enum.getName(), Constants.NBT.TAG_COMPOUND)) {
                    tag.removeTag(_enum.getName());
                }
            }
        } else {
            if (!tag.hasKey(EnumSchematicPosition.BOX_ORIGIN.getName(), Constants.NBT.TAG_COMPOUND)) {
                setAllPosition(pos, tag, playerIn);
            } else if (NBTHelper.getBlockPos(tag, EnumSchematicPosition.BOX_ORIGIN.getName()).equals(pos)) {
                if (tag.hasKey("SchematicName")) {
                    String name = tag.getString("SchematicName");
                    Schematic schematic = SchematicUtils.get().loadSchematic(name, Version.MODID);
                    schematic.generate(worldIn, pos);
                } else {
                    playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + StatCollector.translateToLocal("debug.description.notselected.msg")));
                }

            } else {
                setAllPosition(pos, tag, playerIn);
            }
        }
        return false;
    }

    private void setAllPosition(BlockPos pos, NBTTagCompound tag, EntityPlayer playerIn) {
        EnumSchematicPosition.setPosition(tag, EnumSchematicPosition.BOX_ORIGIN, pos, playerIn);
        if (!tag.hasKey("SchematicName")) {
            return;
        }
        String schematicName = tag.getString("SchematicName");
        Schematic schematic = SchematicUtils.get().loadSchematic(schematicName, Version.MODID);
        BlockPos boxPos1 = pos.add(schematic.getOrigin());
        BlockPos boxPos2 = boxPos1.add(schematic.getXLayers(), schematic.getYLayers(), schematic.getZLayers()).add(-1, -1, -1);
//        System.err.println(schematic.collusion);
        BlockPos collPos1 = boxPos1.add(schematic.collusion.minX, schematic.collusion.minY, schematic.collusion.minZ);
        BlockPos collPos2 = boxPos1.add(schematic.collusion.maxX, schematic.collusion.maxY, schematic.collusion.maxZ);
        EnumSchematicPosition.setPosition(tag, EnumSchematicPosition.BOX_POS1, boxPos1, playerIn);
        EnumSchematicPosition.setPosition(tag, EnumSchematicPosition.BOX_POS2, boxPos2, playerIn);
        EnumSchematicPosition.setPosition(tag, EnumSchematicPosition.COLLISION_POS1, collPos1, playerIn);
        EnumSchematicPosition.setPosition(tag, EnumSchematicPosition.COLLISION_POS2, collPos2, playerIn);

    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        return itemStackIn;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        if (!stack.hasTagCompound()) {
            return;
        }
        NBTTagCompound tag = stack.getTagCompound();
        if (tag.hasKey("SchematicIndex")) {
            tooltip.add(String.format(StatCollector.translateToLocal("debug.description.next.msg"), EnumChatFormatting.WHITE, tag.getInteger("SchematicIndex")));
        }
        if (tag.hasKey("SchematicName")) {
            tooltip.add(String.format(StatCollector.translateToLocal("debug.description.name.msg"), EnumChatFormatting.WHITE, tag.getString("SchematicName")));
        }
        for (EnumSchematicPosition _enum : EnumSchematicPosition.values()) {
            if (tag.hasKey(_enum.getName(), Constants.NBT.TAG_COMPOUND)) {
                BlockPos pos = EnumSchematicPosition.getPosition(tag, _enum);
                tooltip.add(String.format(StatCollector.translateToLocal("debug.description.point"),
                                EnumChatFormatting.WHITE, _enum.getName(), pos.getX(), pos.getY(), pos.getZ())
                );
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    public void renderSelectArea(ItemStack itemStack, EntityPlayer player, double partialTicks) {
        double shift2 = 0.01d;
        double px = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
        double py = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
        double pz = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;
        NBTTagCompound tag = player.getHeldItem().getTagCompound();
        if (tag != null) {
            boolean bpos1 = tag.hasKey(EnumSchematicPosition.BOX_POS1.getName(), Constants.NBT.TAG_COMPOUND);
            boolean bpos2 = tag.hasKey(EnumSchematicPosition.BOX_POS2.getName(), Constants.NBT.TAG_COMPOUND);
            boolean bposO = tag.hasKey(EnumSchematicPosition.BOX_ORIGIN.getName(), Constants.NBT.TAG_COMPOUND);
            BlockPos pos1 = new BlockPos(0, 0, 0);
            BlockPos pos2 = new BlockPos(0, 0, 0);
            BlockPos posO;

            boolean bcoll1 = tag.hasKey(EnumSchematicPosition.COLLISION_POS1.getName(), Constants.NBT.TAG_COMPOUND);
            boolean bcoll2 = tag.hasKey(EnumSchematicPosition.COLLISION_POS2.getName(), Constants.NBT.TAG_COMPOUND);
            BlockPos coll1 = new BlockPos(0, 0, 0);
            BlockPos coll2 = new BlockPos(0, 0, 0);

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            float transp = 0.15f;

            if (bposO) {
                posO = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.BOX_ORIGIN);
                AxisAlignedBB aabb = new AxisAlignedBB(posO, posO.add(1, 1, 1)).offset(-px, -py, -pz).expand(shift2, shift2, shift2);
                RenderUtils.boxAndLine(aabb, 0, 0, 0, transp, 0, 0, 0, 1);
            }
            if (bpos1 && bpos2) {
                pos1 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.BOX_POS1);
                pos2 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.BOX_POS2);
                AxisAlignedBB aabb = new AxisAlignedBB(pos1, pos2).offset(-px, -py, -pz).addCoord(1, 1, 1).expand(shift2, shift2, shift2);
                RenderUtils.boxAndLine(aabb, 0, 1, 0, transp * 2, 0, 1, 0, 1);
            }
            if (bcoll1 && bcoll2 && coll1 != pos1 && coll2 != pos2) {
                coll1 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.COLLISION_POS1);
                coll2 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.COLLISION_POS2);
                AxisAlignedBB aabb = new AxisAlignedBB(coll1, coll2).offset(-px, -py, -pz).addCoord(1, 1, 1).expand(shift2, shift2, shift2);
                RenderUtils.boxAndLine(aabb, 1.0F, 1.0F, 0.0F, transp * 2, 1.0F, 1.0F, 0.0F, 1);
            }
            GlStateManager.depthMask(true);
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
        }
    }
}