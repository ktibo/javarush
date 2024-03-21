package com.javarush.archiver;

import com.javarush.archiver.command.ExitCommand;
import com.javarush.archiver.exception.WrongZipFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Archiver {

    public static void main(String[] args) throws Exception {

        Operation operation = null;

        while (true) {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
            if (operation == Operation.EXIT) break;
        }

//        String pathArchive;
//        String pathFile;
//
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            pathArchive = reader.readLine();
//            pathFile = reader.readLine();
//        } catch (IOException e){
//            throw new RuntimeException(e);
//        }
//
//        // test
//        pathArchive = "C:\\Users\\Andrey\\Desktop\\123.zip";
//        pathFile = "C:\\Users\\Andrey\\Desktop\\pokraska-potolka-2.jpg";
//
//        ZipFileManager zipFileManager = new ZipFileManager(Path.of(pathArchive));
//        zipFileManager.createZip(Path.of(pathFile));

        //new ExitCommand().execute();

    }

    public static Operation askOperation() throws IOException {

        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(String.format("\t %d - упаковать файлы в архив", Operation.CREATE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - добавить файл в архив", Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - удалить файл из архива", Operation.REMOVE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - распаковать архив", Operation.EXTRACT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - просмотреть содержимое архива", Operation.CONTENT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - выход", Operation.EXIT.ordinal()));

        Operation operation = Operation.values()[ConsoleHelper.readInt()];
        return operation;

    }




}
