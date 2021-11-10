package LAB2;

//Класс редактора строк

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class StringEditor implements Serializable {
    private String string;

    public StringEditor(){
        string = "У лукоморья дуб зелёный; " +
                "Златая цепь на дубе том: " +
                "И днём и ночью кот учёный " +
                "Всё ходит по цепи кругом; " +
                "Идёт направо — песнь заводит, " +
                "Налево — сказку говорит. " +
                "Там чудеса: там леший бродит, " +
                "Русалка на ветвях сидит;";
    }

    public StringEditor(String string){
        this.string = string;
    }

    public StringEditor(StringEditor stringEditor){
        string = stringEditor.string;
    }

    public StringEditor(String filename, byte type) throws IOException{
        filename = "src//LAB2//" + filename;
        filename += (type == 1)? ".txt" : ".bin";

        if (type == 1)
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
                string = reader.lines().collect(Collectors.joining());
            }
        else if (type == 2)
            try (DataInputStream inputStream = new DataInputStream(new FileInputStream(filename))){
                string = inputStream.readUTF();
            }
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public ProgrammeResult<String> serialize(String filename){
        String path = "src//LAB2//" + filename + ".ser";
        ProgrammeResult<String> result = new ProgrammeResult<>("Объект успешно сериализован!", true, filename);
        try(FileOutputStream destination =  new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(destination))
        {
            objectOutputStream.writeObject(this);
        }
        catch(IOException exception){
            result.setSuccessStatus(false);
            result.setMessage("Ошибка при записи в файл!");
        }
        return result;
    }

    public static ProgrammeResult<Object> deserialize(String objectStorage){
        String path = "src//LAB2//" + objectStorage + ".ser";
        StringEditor stringEditor = new StringEditor();
        ProgrammeResult<Object> result = new ProgrammeResult<>("Объект успешно получен путем десериализации!", true, null);
        try(FileInputStream storage =  new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(storage))
        {
            stringEditor = (StringEditor) objectInputStream.readObject();
        }
        catch(IOException exception){
            result.setMessage("Ошибка при чтении файла!");
            result.setSuccessStatus(false);
        }
        catch (ClassNotFoundException exception){
            result.setMessage("Не найдено объекта типа <<Редактор строк>>!");
            result.setSuccessStatus(false);
        }
        if (result.getSuccessStatus())
            result.setValue(stringEditor);

        return result;
    }

    public ProgrammeResult<String> writeToFile(String filename, byte type){
        filename = "src//LAB2//" + filename;
        filename += (type == 1)? ".txt" : ".bin";
        ProgrammeResult<String> result = new ProgrammeResult<>("Запись в файл успешно завершена!", true, "");
        if (type == 1){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
                writer.write(string);
            }
            catch(IOException exception){
                result.setMessage("Файл не найден!");
                result.setSuccessStatus(false);
            }
        }
        else if (type == 2){
            try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(filename))){
                outputStream.writeUTF(string);
            }
            catch(IOException exception){
                result.setMessage("Файл не найден!");
                result.setSuccessStatus(false);
            }
        }

        return result;
    }

    public ProgrammeResult<Short> countWords(){
        short amount = 0;
        for (String word : divide().getValue())
            ++amount;

        return new ProgrammeResult<>("Слова успешно подсчитаны!", true, amount);
    }

    public ProgrammeResult<String[]> divide(){
        String[] words = string.split("[\\s,.:—_;!?]+");
        return new ProgrammeResult<>("Строка успешно разделена", true, words);
    }

    public ProgrammeResult<String> remove(String[] words, short position){
        short k = 0;
        short i = 0;
        String editedString = "";
        while (i < words.length){
            ++k;
            if (k == position){
                k = 0;
            }
            else{
                editedString += words[i];
                editedString += " ";
            }

            ++i;
        }

        if (editedString != "")
            editedString = editedString.substring(0, editedString.length() - 1);

        return new ProgrammeResult<>("Строка успешно обработана!", true, editedString);
    }
}
