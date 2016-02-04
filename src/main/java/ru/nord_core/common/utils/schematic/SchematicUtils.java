package ru.nord_core.common.utils.schematic;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLLog;
import ru.nord_core.NordCore;
import ru.nord_core.common.utils.Constants;

import java.io.*;

/**
 * Created by andrew on 04.02.16.
 */
public class SchematicUtils {
    public final FileFilterSchematic FILE_FILTER_SCHEMATIC = new FileFilterSchematic(false);
    private static File currentDirectory;
    public static SchematicUtils INSTANCE;

    public static SchematicUtils get(){
        if (INSTANCE==null){
            INSTANCE = new SchematicUtils();
        }
        return INSTANCE;
    }

    public NBTTagCompound readTagCompoundFromFile(final File file) throws IOException {
        try {
            return CompressedStreamTools.readCompressed(new FileInputStream(file));
        } catch (final Exception ex) {
            return CompressedStreamTools.read(file);
        }
    }

    public File[] getSchematicks(){
        currentDirectory = new File(currentDirectory,  "schematics/");
        try {
            currentDirectory = currentDirectory.getCanonicalFile();
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }
        final File[] filesFolders = currentDirectory.listFiles(FILE_FILTER_SCHEMATIC);
        return filesFolders;
    }

    public void writeToFile(String fileName, NBTTagCompound nbtdata) {
        fileName = "schematics/" + fileName;
        try {
            File file = new File(NordCore.proxy.getDataDirectory(), fileName);
            FileOutputStream stream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(stream);
            /**
             * Метод по умолчанию приватный
             * http://www.minecraftforge.net/wiki/Using_Access_Transformers
             */
            NBTTagCompound.writeEntry(Constants.NBT.ROOT, nbtdata, dataOutputStream);
        } catch (final Exception ex) {
            ex.printStackTrace();
            FMLLog.getLogger().error("Failed to write schematic!", ex);
        }

    }
}
