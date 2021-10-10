package LAB2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Analyzable<Short> analyzer = (String data, short bottomLim, short topLim) ->{
            ProgrammeResult<Short> result = new ProgrammeResult<>("Получены корректные данные!", true, (short) 0);
            short parsedData = 0;
            try{
                parsedData = Short.parseShort(data);
            }
            catch(NumberFormatException exception){
                result.setSuccessStatus(false);
                result.setMessage("Введите целое числовое значение!");
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


    }
}
