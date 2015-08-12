package ru.nord.common.network;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class CommandTest extends CommandBase {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void execute(ICommandSender sender, String[] args) throws CommandException {

    }

}