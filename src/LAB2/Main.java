package LAB2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Analyzable<Short> analyzer = (short bottomLim, short topLim) ->{
            ProgrammeResult<Short> result = new ProgrammeResult<>("Получены корректные данные!", true, (short) 0);
            short value;
            if (!in.hasNextShort()){
                value = in.nextShort();
                if (value >= bottomLim && value <= topLim)
                    result.setValue(value);
                else{
                    result.setSuccessStatus(false);
                    result.setMessage("Значение не входит в заданный диапазон!");
                }
            }
            else{
                result.setSuccessStatus(false);
                result.setMessage("Введите целое число!");
            }
            return result;
        };
        Launchable menuLauncher = () ->{
            System.out.println("ЛАБОРАТОРНАЯ РАБОТА №2 ПО ООП");
            System.out.println("""
                Выберите задание:
                1. Работа с матрицей
                2. Работа с текстом
                0. Выход""");
            ProgrammeResult<Short> result;
            do {
                System.out.print("Введите здесь: ");
                result = analyzer.analyze((short) 0, (short) 2);
                System.out.println(result.getMessage());
            } while (!result.getSuccessStatus());

            return result;
        };
        Launchable taskMatrixLauncher = () -> new ProgrammeResult<>("Получены корректные данные!", true, (short) 0);
        Launchable taskTextLauncher = () -> new ProgrammeResult<>("Получены корректные данные!", true, (short) 0);


        ProgrammeResult<Short> result;
        do{
            result = menuLauncher.launch();
            switch (result.getValue()) {
                case 1 -> taskMatrixLauncher.launch();
                case 2 -> taskTextLauncher.launch();
                case 0 -> System.out.println("Завершение работы...");
            }
        } while (result.getValue() != 0);
    }
}
