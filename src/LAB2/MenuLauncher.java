package LAB2;

//Класс для запуска консольных меню

import static LAB2.Main.in;
import static LAB2.Main.precision;

public class MenuLauncher {
    public static ProgrammeResult<Short> launchMainMenu(Analyzable<Short> analyzer) {
        System.out.println("""
                                
                ЛАБОРАТОРНАЯ РАБОТА №2 ПО ООП
                Выберите задание:
                1. Работа с матрицей
                2. Работа с текстом
                0. Выход""");
        ProgrammeResult<Short> result;
        String inputData;
        do {
            System.out.print("Введите здесь: ");
            inputData = in.nextLine();
            result = analyzer.analyze(inputData, (short) 0, (short) 2);
            System.out.println(result.getMessage());
        } while (!result.getSuccessStatus());

        switch (result.getValue()) {
            case 1 -> launchMatrixMenu(analyzer);
            case 2 -> launchStringMenu(analyzer);
            case 0 -> {
                result.setValue((short) 0);
                result.setMessage("Работа завершена!");
            }
        }

        return result;
    }

    public static void launchMatrixMenu(Analyzable<Short> analyzer) {
        ProgrammeResult<Short> result;
        String inputData;
        Matrix matrix = new Matrix();
        System.out.println("Матрица создана по умолчанию с размером 3х3");
        do {
            System.out.println("""
                                  
                    Выберите команду:
                    1. Создать матрицу с заданным размером
                    2. Создать матрицу на основе существующей
                    3. Напечатать матрицу
                    4. Найти элемент в матрице
                    5. Привести матрицу к треугольному виду
                    6. Напечатать элементы матрицы по типу знака
                    7. Найти сумму элементов матрицы
                    8. Сохранить матрицу
                    0. Выход""");
            do {
                System.out.print("Введите здесь: ");
                inputData = in.nextLine();
                result = analyzer.analyze(inputData, (short) 0, (short) 8);
                System.out.println(result.getMessage());
            } while (!result.getSuccessStatus());

            switch (result.getValue()) {
                case 1 -> {
                    do {
                        System.out.print("Введите размер матрицы (целое число от 1 до 255): ");
                        inputData = in.nextLine();
                        result = analyzer.analyze(inputData, (short) 1, (short) 255);
                        System.out.println(result.getMessage());
                    } while (!result.getSuccessStatus());
                    matrix = new Matrix(result.getValue());
                    System.out.println("Матрица успешно создана!");
                }
                case 2 -> {
                    ProgrammeResult<Object> deserializationResult;
                    System.out.print("Введите имя исходной матрицы: ");
                    inputData = in.nextLine();
                    deserializationResult = Matrix.deserialize(inputData);
                    System.out.println(deserializationResult.getMessage());
                    if (deserializationResult.getSuccessStatus())
                        matrix = (Matrix) deserializationResult.getValue();
                }
                case 3 -> System.out.println(matrix.toString() + String.format("Ваша матрица имеет размер %dx%d", matrix.getSize(), matrix.getSize()));
                case 4 -> {
                    byte k;
                    short string;
                    short column;
                    do {
                        k = 0;
                        System.out.printf("Введите номер строки элемента (целое число от %d до %d): ", 1, matrix.getSize());
                        inputData = in.nextLine();
                        result = analyzer.analyze(inputData, (short) 1, matrix.getSize());
                        System.out.println(result.getMessage());
                        k += result.getSuccessStatus() ? 1 : 0;
                        string = result.getValue();
                        System.out.printf("Введите номер столбца элемента (целое число от %d до %d): ", 1, matrix.getSize());
                        inputData = in.nextLine();
                        result = analyzer.analyze(inputData, (short) 1, matrix.getSize());
                        System.out.println(result.getMessage());
                        k += result.getSuccessStatus() ? 1 : 0;
                        column = result.getValue();
                    } while (k != 2);
                    ProgrammeResult<Double> calculationResult = matrix.getElement(string, column);
                    System.out.println(calculationResult.getMessage());
                    System.out.printf("Найденный элемент: %s\n", precision.format(calculationResult.getValue()));
                }
                case 5 -> {
                    matrix.convertToTriangle();
                    System.out.println("Матрица успешно приведена к треугольному виду!");
                }
                case 6 -> {
                    ProgrammeResult<String> groupResult = matrix.getSignGroups();
                    System.out.println(groupResult.getMessage());
                    System.out.println(groupResult.getValue());
                }
                case 7 -> {
                    ProgrammeResult<Double> calculationResult = matrix.calculateSum();
                    System.out.println(calculationResult.getMessage());
                    System.out.printf("Вычисленная сумма: %s\n", precision.format(calculationResult.getValue()));
                }
                case 8 -> {
                    System.out.print("Введите имя матрицы: ");
                    inputData = in.nextLine();
                    ProgrammeResult<String> serializationResult = matrix.serialize(inputData);
                    System.out.println(serializationResult.getMessage());
                    System.out.printf("Теперь вы можете создавать копии на основе матрицы по данной директории: %s\n", serializationResult.getValue());
                }
                case 0 -> {
                    result.setValue((short) 0);
                    result.setMessage("Работа завершена!");
                    System.out.println(result.getMessage());
                }
            }
            System.out.print("Нажмите enter для продолжения: ");
            in.nextLine();
        } while (result.getValue() != 0);
    }

    public static void launchStringMenu(Analyzable<Short> analyzer) {
        ProgrammeResult<Short> result;
        String inputData;
        StringEditor stringEditor = new StringEditor();
        System.out.println("Редактор строк создан по умолчанию! Для просмотра данных редактора напечатайте их");
        do {
            System.out.println("""
                                  
                    Выберите команду:
                    1. Создать объект с заданной строкой
                    2. Создать объект на основе существующего
                    3. Напечатать данные редактора
                    4. Посчитать число слов в строке
                    5. Разделить строку на слова
                    6. Удалить каждое n-ое слово из строки
                    7. Сохранить объект
                    0. Выход""");

            do {
                System.out.print("Введите здесь: ");
                inputData = in.nextLine();
                result = analyzer.analyze(inputData, (short) 0, (short) 7);
                System.out.println(result.getMessage());
            } while (!result.getSuccessStatus());

            switch (result.getValue()) {
                case 1 -> {
                    System.out.print("""
                            Введите строку, разделяя слова следующими символами (пробел , . : — _ ; ! ?):
                            Введите здесь:""");
                    inputData = in.nextLine();
                    stringEditor = new StringEditor(inputData);
                    System.out.println("Объект успешно создан!");
                }
                case 2 -> {
                    ProgrammeResult<Object> deserializationResult;
                    System.out.print("Введите имя исходного объекта: ");
                    inputData = in.nextLine();
                    deserializationResult = StringEditor.deserialize(inputData);
                    System.out.println(deserializationResult.getMessage());
                    if (deserializationResult.getSuccessStatus())
                        stringEditor = (StringEditor) deserializationResult.getValue();
                }
                case 3 -> System.out.printf("Данные редактора: %s\n", stringEditor.getString());
                case 4 -> {
                    ProgrammeResult<Short> countResult = stringEditor.countWords();
                    System.out.println(countResult.getMessage());
                    System.out.printf("Числов слов: %d\n", countResult.getValue());
                }
                case 5 -> {
                    ProgrammeResult<String[]> divisionResult = stringEditor.divide();
                    System.out.println(divisionResult.getMessage());
                    String[] words = divisionResult.getValue();
                    System.out.println("Полученные слова:");
                    for (int i = 0; i < words.length; ++i)
                        System.out.print((i != words.length - 1) ? words[i] + " " : words[i] + "\n");
                }
                case 6 -> {
                    do {
                        short wordsAmount = stringEditor.countWords().getValue();
                        System.out.printf("Введите позицию n, под которой будут удаляться слова (число от 1 до %d): ", wordsAmount);
                        inputData = in.nextLine();
                        result = analyzer.analyze(inputData, (short) 1, wordsAmount);
                        System.out.println(result.getMessage());
                    } while (!result.getSuccessStatus());
                    ProgrammeResult<String> removalResult = stringEditor.remove(stringEditor.divide().getValue(), Short.parseShort(inputData));
                    System.out.println(removalResult.getMessage());
                    System.out.printf("Полученная строка: %s\n", removalResult.getValue());
                }
                case 7 -> {
                    System.out.print("Введите имя объекта: ");
                    inputData = in.nextLine();
                    ProgrammeResult<String> serializationResult = stringEditor.serialize(inputData);
                    System.out.println(serializationResult.getMessage());
                    System.out.printf("Теперь вы можете создавать копии на основе данного объекта по данной директории: %s\n", serializationResult.getValue());
                }
                case 0 -> {
                    result.setValue((short) 0);
                    result.setMessage("Работа завершена!");
                    System.out.println(result.getMessage());
                }
            }
            System.out.print("Нажмите enter для продолжения: ");
            in.nextLine();
        } while (result.getValue() != 0);
    }
}
