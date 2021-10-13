package LAB2;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {

    public static final Scanner in = new Scanner(System.in);
    public static final DecimalFormat precision = new DecimalFormat("0.000");

    public static void main(String[] args) {
        Analyzable<Short> analyzer = (String data, short bottomLim, short topLim) ->{
            ProgrammeResult<Short> result = new ProgrammeResult<>("Получены корректные данные!", true, (short) 0);
            short parsedData = 0;
            try{
                parsedData = Short.parseShort(data);
            }
            catch (NumberFormatException exception){
                result.setSuccessStatus(false);
                result.setMessage("Введите целое число!");
            }
            if (result.getSuccessStatus()){
                if (parsedData >= bottomLim && parsedData <= topLim)
                    result.setValue(parsedData);
                else{
                    result.setSuccessStatus(false);
                    result.setMessage("Значение не входит в заданный диапазон!");
                }
            }
            return result;
        };

        ProgrammeResult<Short> result;
        do result = MenuLauncher.launchMainMenu(analyzer); while (result.getValue() != 0);
        System.out.println(result.getMessage());
    }
}
