package com.javarush.archiver.command;

import com.javarush.archiver.ConsoleHelper;
import com.javarush.archiver.ZipFileManager;

import java.nio.file.Path;

public abstract class ZipCommand implements Command{

    public ZipFileManager getZipFileManager() throws Exception {

        ConsoleHelper.writeMessage("Введите полный путь файла архива: ");
        String zipPath = ConsoleHelper.readString();
        return new ZipFileManager(Path.of(zipPath));

    }

}
