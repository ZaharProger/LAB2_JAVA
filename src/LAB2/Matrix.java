package LAB2;

import java.io.*;

//Матрица

public class Matrix implements Serializable {
    private double[][] elements;
    private short size;
    private static final long serialID = 1L;//ID для сериализации

    public Matrix(){
        size = 3;
        elements = new double[size][size];
    }

    public Matrix(short size){
        this.size = size;
        elements = new double[size][size];
    }

    public Matrix(Matrix matrix){
        this.size = matrix.size;
        elements = new double[size][size];
        short i = 0;
        while (i < size){
            short j = 0;
            while (j < size){
                elements[i][j] = matrix.elements[i][j];
                ++j;
            }
            ++i;
        }
    }

    public ProgrammeResult<String> serialize(String filename){
        String path = "src//LAB2//" + filename + ".ser";
        ProgrammeResult<String> result = new ProgrammeResult<>("Объект успешно сериализован!", true, path);
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
        Matrix matrix = new Matrix();
        ProgrammeResult<Object> result = new ProgrammeResult<>("Объект успешно получен путем десериализации!", true, null);
        try(FileInputStream storage =  new FileInputStream(objectStorage);
            ObjectInputStream objectInputStream = new ObjectInputStream(storage))
        {
            matrix = (Matrix)objectInputStream.readObject();
        }
        catch(IOException exception){
            result.setMessage("Ошибка при чтении файла!");
            result.setSuccessStatus(false);
        }
        catch (ClassNotFoundException exception){
            result.setMessage("Не найдено объекта типа <<Матрица>>!");
            result.setSuccessStatus(false);
        }
        if (result.getSuccessStatus())
            result.setValue(matrix);

        return result;
    }

    public void setSize(short size){
        this.size = size;
    }

    public void setElements(double[][] elements){
        short i = 0;
        while (i < size){
            short j = 0;
            while (j < size){
                this.elements[i][j] = elements[i][j];
                ++j;
            }
            ++i;
        }
    }

    public void setElement(double value, short string, short column){
        elements[string][column] = value;
    }

    public short getSize() {
        return size;
    }

    public double[][] getElements() {
        return elements;
    }

    public ProgrammeResult<Double> getElement(short string, short column){
        ProgrammeResult<Double> result = new ProgrammeResult<>("Элемент успешно найден!", true, 0.d);
        if (string >= 0 && string < size && column >= 0 && column < size){
            result.setValue(elements[string][column]);
        }
        else{
            result.setMessage("Элемент не найден!");
            result.setSuccessStatus(false);
        }

        return result;
    }
}
