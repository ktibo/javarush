package com.javarush.archiver.command;

import com.javarush.archiver.ConsoleHelper;
import com.javarush.archiver.ZipFileManager;
import com.javarush.archiver.exception.WrongZipFileException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipExtractCommand extends ZipCommand{
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Распаковка архива.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите путь для распаковки:");
            Path destinationPath = Paths.get(ConsoleHelper.readString());
            zipFileManager.extractAll(destinationPath);

            ConsoleHelper.writeMessage("Архив был распакован.");

        } catch (WrongZipFileException e) {
            ConsoleHelper.writeMessage("Архив не существует.");
        }
    }
}
