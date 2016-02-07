package ru.nord_core.common.items;


import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
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
import ru.nord_core.common.utils.schematic.Schematic;

import java.util.List;

public class ItemDebugStickSchematicSave extends ItemBase implements ISelectItem {

    static enum EnumSchematicPosition {
        BOX_POS1("POS1"),
        BOX_POS2("POS2"),
        BOX_ORIGIN("ORIGIN"),
        COLLISION_POS1("COLL1"),
        COLLISION_POS2("COLL2"),;
        private final String namePos;

        EnumSchematicPosition(String string) {
            this.namePos = string;
        }

        public String getName() {
            return namePos;
        }

        public String toString() {
            return namePos;
        }

        public static void setPosition(NBTTagCompound tag, EnumSchematicPosition _enum, BlockPos pos, EntityPlayer playerIn) {
            NBTHelper.setBlockPos(tag, _enum.getName(), pos);
            playerIn.addChatMessage(new ChatComponentText(
                    String.format(
                            StatCollector.translateToLocal("debug.description.set.msg"),
                            EnumChatFormatting.GRAY.toString(), _enum.getName(), pos.getX(), pos.getY(), pos.getZ()
                    )
            ));
        }

        public static BlockPos getPosition(NBTTagCompound tag, EnumSchematicPosition _enum) {
            return NBTHelper.getBlockPos(tag, _enum.getName());
        }
    }

    public ItemDebugStickSchematicSave(String modid) {
        super(modid);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabAllSearch);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return false;
        if (playerIn.isSneaking()) {
            System.err.println("onItemUseFirst+Shift");
        } else {
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }
            NBTTagCompound tag = stack.getTagCompound();
            if (!tag.hasKey(EnumSchematicPosition.BOX_POS1.getName(), Constants.NBT.TAG_COMPOUND)) {
                // Первая позиция коробки
                EnumSchematicPosition.setPosition(tag, EnumSchematicPosition.BOX_POS1, pos, playerIn);
            } else if (!tag.hasKey(EnumSchematicPosition.BOX_POS2.getName(), Constants.NBT.TAG_COMPOUND)) {
                // Вторая позиция коробки
                EnumSchematicPosition.setPosition(tag, EnumSchematicPosition.BOX_POS2, pos, playerIn);
            } else if (!tag.hasKey(EnumSchematicPosition.BOX_ORIGIN.getName(), Constants.NBT.TAG_COMPOUND)) {
                // Центр коробки
                EnumSchematicPosition.setPosition(tag, EnumSchematicPosition.BOX_ORIGIN, pos, playerIn);
            } else if (!tag.hasKey(EnumSchematicPosition.COLLISION_POS1.getName(), Constants.NBT.TAG_COMPOUND) && stack.getMetadata() == 1) {
                // Коробка столкновений, первая позиция
                EnumSchematicPosition.setPosition(tag, EnumSchematicPosition.COLLISION_POS1, pos, playerIn);
            } else if (!tag.hasKey(EnumSchematicPosition.COLLISION_POS2.getName(), Constants.NBT.TAG_COMPOUND) && stack.getMetadata() == 1) {
                // Коробка столкновений, вторая позиция
                EnumSchematicPosition.setPosition(tag, EnumSchematicPosition.COLLISION_POS2, pos, playerIn);
            } else {
                BlockPos box1 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.BOX_POS1);
                BlockPos box2 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.BOX_POS2);
                BlockPos boxO = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.BOX_ORIGIN);

                AxisAlignedBB schematicBox = new AxisAlignedBB(box1.getX(), box1.getY(), box1.getZ(), box2.getX(), box2.getY(), box2.getZ());
                Schematic schem = new Schematic();
                String time = String.valueOf(System.currentTimeMillis());
                if (stack.getMetadata() == 1) {
                    BlockPos coll1 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.COLLISION_POS1);
                    BlockPos coll2 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.COLLISION_POS2);
                    AxisAlignedBB collBox = new AxisAlignedBB(
                            coll1.getX(), coll1.getY(), coll1.getZ(),
                            coll2.getX(), coll2.getY(), coll2.getZ()
                    );
                    collBox = collBox.offset(-schematicBox.minX, -schematicBox.minY, -schematicBox.minZ);//.addCoord(1,1,1);
                    schem.save(time, schem.getFromWorld(schematicBox, worldIn, boxO, collBox));
                } else {
                    schem.save(time, schem.getFromWorld(schematicBox, worldIn, boxO));
                }
                playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + StatCollector.translateToLocal("debug.description.save.msg") + time));
            }
            stack.setTagCompound(tag);
        }

        return false;
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        if (worldIn.isRemote) return itemStackIn;
        if (playerIn.isSneaking()) {
            if (!itemStackIn.hasTagCompound()) {
                itemStackIn.setTagCompound(new NBTTagCompound());
            } else {
                NBTTagCompound tag = itemStackIn.getTagCompound();
                for (EnumSchematicPosition _enum : EnumSchematicPosition.values()) {
                    if (tag.hasKey(_enum.getName(), Constants.NBT.TAG_COMPOUND)) {
                        tag.removeTag(_enum.getName());
                    }
                }
                playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + StatCollector.translateToLocal("debug.description.clear.msg")));
            }
        }
        return itemStackIn;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            for (EnumSchematicPosition _enum : EnumSchematicPosition.values()) {
                if (tag.hasKey(_enum.getName(), Constants.NBT.TAG_COMPOUND)) {
                    BlockPos pos = EnumSchematicPosition.getPosition(tag, _enum);
                    tooltip.add(String.format(StatCollector.translateToLocal("debug.description.point"),
                                    EnumChatFormatting.WHITE, _enum.getName(), pos.getX(), pos.getY(), pos.getZ())
                    );
                }
            }
        } else {
            tooltip.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("debug.description.pos"));
            tooltip.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("debug.description.pos1"));
            tooltip.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("debug.description.pos2"));
            tooltip.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("debug.description.poso"));
            if (stack.getMetadata() == 1) {
                tooltip.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("debug.description.coll1"));
                tooltip.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("debug.description.coll2"));
            }
            tooltip.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("debug.description.clear"));


        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
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

            if (bpos1) {
                pos1 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.BOX_POS1);
                AxisAlignedBB aabb = new AxisAlignedBB(pos1, pos1.add(1, 1, 1)).offset(-px, -py, -pz).expand(shift2, shift2, shift2);
                RenderUtils.boxAndLine(aabb, 1, 0, 0, transp, 1, 0, 0, 1);
            }
            if (bpos2) {
                pos2 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.BOX_POS2);
                AxisAlignedBB aabb = new AxisAlignedBB(pos2, pos2.add(1, 1, 1)).offset(-px, -py, -pz).expand(shift2, shift2, shift2);
                RenderUtils.boxAndLine(aabb, 0, 0, 1, transp, 0, 0, 1, 1);
            }
            if (bposO) {
                posO = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.BOX_ORIGIN);
                AxisAlignedBB aabb = new AxisAlignedBB(posO, posO.add(1, 1, 1)).offset(-px, -py, -pz).expand(shift2, shift2, shift2);
                RenderUtils.boxAndLine(aabb, 0, 0, 0, transp, 0, 0, 0, 1);
            }
            if (bcoll1) {
                coll1 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.COLLISION_POS1);
                AxisAlignedBB aabb = new AxisAlignedBB(coll1, coll1.add(1, 1, 1)).offset(-px, -py, -pz).expand(shift2, shift2, shift2);
                RenderUtils.boxAndLine(aabb, 0.5F, 1.0F, 0.5F, transp, 0.5F, 1.0F, 0.5F, 1);
            }
            if (bcoll2) {
                coll2 = EnumSchematicPosition.getPosition(tag, EnumSchematicPosition.COLLISION_POS2);
                AxisAlignedBB aabb = new AxisAlignedBB(coll2, coll2.add(1, 1, 1)).offset(-px, -py, -pz).expand(shift2, shift2, shift2);
                RenderUtils.boxAndLine(aabb, 1.0F, 0.5F, 0.5F, transp, 1.0F, 0.5F, 0.5F, 1);
            }

            if (bpos1 && bpos2) {
                AxisAlignedBB aabb = new AxisAlignedBB(pos1, pos2).offset(-px, -py, -pz).addCoord(1, 1, 1).expand(shift2, shift2, shift2);
                RenderUtils.boxAndLine(aabb, 0, 1, 0, transp * 2, 0, 1, 0, 1);
            }
            if (bcoll1 && bcoll2) {
                AxisAlignedBB aabb = new AxisAlignedBB(coll1, coll2).offset(-px, -py, -pz).addCoord(1, 1, 1).expand(shift2, shift2, shift2);
                RenderUtils.boxAndLine(aabb, 1.0F, 1.0F, 0.0F, transp * 2, 1.0F, 1.0F, 0.0F, 1);
            }
            GlStateManager.depthMask(true);
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
        }
    }
}