package LAB2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Launchable menuManager = (short code)->{
            String data;
            try{
                data = switch (code) {
                    case 1 -> Files.readString(Path.of("src//LAB2//mainMenu.txt"));
                    case 2 -> Files.readString(Path.of("src//LAB2//task1.txt"));
                    case 3 -> Files.readString(Path.of("src//LAB2//task2.txt"));
                    default -> "Такой команды не существует!";
                };
            }
            catch (IOException exception){
                data = "Данное главное/контекстное меню не существует!";
            }
            return data;
        };


    }
}
