package ru.nord_core.common.utils.schematic;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLLog;
import ru.nord_core.NordCore;
import ru.nord_core.common.utils.Constants;
import ru.nord_core.common.utils.schematic.abstracts.ASchematic;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class SchematicUtils {
    public final FileFilterSchematic FILE_FILTER_SCHEMATIC = new FileFilterSchematic(false);
    private static File currentDirectory;
    public static SchematicUtils INSTANCE;
    public static HashMap<String, Schematic> schemMap= new HashMap<String,Schematic>(5);

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
        currentDirectory = new File(NordCore.proxy.getDataDirectory(),  "schematics/");
        try {
            currentDirectory = currentDirectory.getCanonicalFile();
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }
        return currentDirectory.listFiles(FILE_FILTER_SCHEMATIC);
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
        //todo fix it
//            NBTTagCompound.writeEntry(Constants.NBT.ROOT, nbtdata, dataOutputStream);

        } catch (final Exception ex) {
            ex.printStackTrace();
            FMLLog.getLogger().error("Failed to write schematic!", ex);
        }
    }


    public Schematic loadSchematic(String name,String modid) {
        if (SchematicUtils.schemMap.containsKey(name)) {
            Schematic schematic = SchematicUtils.schemMap.get(name);
            if (schematic == null) {
                Schematic schem = new Schematic().getFromInnerStream(modid,name);
                System.err.println(schem);
                SchematicUtils.schemMap.put(name, schem);
                return schem;
            } else {
                return schematic;
            }
        } else {
            Schematic schem = new Schematic();
            schem.getFromInnerStream(modid,name);
            SchematicUtils.schemMap.put(name, schem);
            return schem;
        }
    }
}
