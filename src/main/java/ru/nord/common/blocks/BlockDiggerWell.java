package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.stream.ChatController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * Created by lionzxy on 12.07.15.
 */
public class BlockDiggerWell extends Block {
    public BlockDiggerWell(){
        super(Material.iron);
    }
    int enterVal=0;
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn){
        worldIn.destroyBlock(pos,false);
        int radius = 32;
        for(int y=0;y<pos.getY()-5;y++){
            for(int x=-radius;x<radius;x++){
                for(int z=-radius;z<radius;z++){
                    worldIn.setBlockToAir(pos.add(x, -y, z));
                }
            }
            System.out.println("Завершенно " + y + "/" + pos.getY());
         }
    }
    /*public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn){
        enterVal++;
        switch (enterVal){
            case 0:System.out.println("Ничего не выбранно");break;
            case 1:System.out.println("Выбран режим копания Шахта вниз 3*3");break;
            case 2:System.out.println("Выбран режим копания Шахта север");break;
            case 3:System.out.println("Выбран режим копания Шахта юг");break;
            case 4:System.out.println("Выбран режим копания Шахта запад");break;
            case 5:System.out.println("Выбран режим копания Шахта восток");break;
}}*/


}
