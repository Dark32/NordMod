package ru.nord_core.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordTabs;
import ru.nord.common.utils.enums.EnumFoodNord;

import java.util.List;

public class ItemFoodNord extends ItemFood {
    public ItemFoodNord() {
        super(0, 0, false);
        this.setHasSubtypes(true);
        this.setCreativeTab(NordTabs.tabFood);
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        return EnumFoodNord.values()[stack.getMetadata()].getHeal();
    }

    @Override
    public float getSaturationModifier(ItemStack stack) {
        return EnumFoodNord.values()[stack.getMetadata()].getSaturation();
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        EnumFoodNord num = EnumFoodNord.values()[stack.getMetadata()];
        if (!worldIn.isRemote &&
                num.getEffect() != null &&
                worldIn.rand.nextInt(100) < num.getEffectPercentl()) {
            for (PotionEffect effect : num.getEffect()) {
                player.addPotionEffect(effect);
            }

        }
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EnumFoodNord num = EnumFoodNord.values()[stack.getMetadata()];
        --stack.stackSize;
        --stack.stackSize;

        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            this.onFoodEaten(stack, worldIn, entityplayer);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.entity_player_burp, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            if (!entityplayer.capabilities.isCreativeMode && num.getContainer() != null) {
                if (stack.stackSize <= 0) {
                    return num.getContainer();
                }

                entityplayer.inventory.addItemStackToInventory(num.getContainer());
            }
        }
        return stack;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumFoodNord.values().length) {
            return super.getUnlocalizedName() + "." + EnumFoodNord.values()[stack.getMetadata()].getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {

        for (int i = 0; i < EnumFoodNord.values().length; ++i) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        EnumFoodNord num = EnumFoodNord.values()[stack.getMetadata()];
        return num.getAnimation();
    }
}
