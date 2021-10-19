package LAB2;

import java.io.*;
import static LAB2.Main.precision;

//Матрица

public class Matrix implements Serializable {
    private double[][] elements;
    private short size;
    private static final long serialID = 1L;//ID для сериализации

    public Matrix(){
        size = 3;
        elements = new double[size][size];
        short i = 0;
        fillMatrixByRandom();
    }

    public Matrix(short size){
        this.size = size;
        elements = new double[size][size];
        fillMatrixByRandom();
    }

    public Matrix(Matrix matrix){
        this.size = matrix.size;
        elements = new double[size][size];
        fillMatrix(matrix.elements);
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
        Matrix matrix = new Matrix();
        ProgrammeResult<Object> result = new ProgrammeResult<>("Объект успешно получен путем десериализации!", true, null);
        try(FileInputStream storage =  new FileInputStream(path);
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

    private void fillMatrix(double[][] elements){
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

    private void fillMatrixByRandom(){
        short i = 0;
        while (i < size){
            short j = 0;
            while (j < size){
                if ((i + j) % 2 == 0)
                    elements[i][j] = Math.random() * 1000;
                else
                    elements[i][j] = -(Math.random() * 1000);
                ++j;
            }
            ++i;
        }
    }

    public short getSize() {
        return size;
    }

    public ProgrammeResult<Double> getElement(short string, short column){
        return new ProgrammeResult<>("Элемент успешно найден!", true, elements[--string][--column]);
    }

    public String toString(){
        String printedMatrix = "";
        short i = 0;
        while (i < size){
            short j = 0;
            while (j < size){
                printedMatrix += precision.format(elements[i][j]);
                printedMatrix += (j == size - 1)?"":" ";
                ++j;
            }
            printedMatrix += '\n';
            ++i;
        }
        return printedMatrix;
    }

    public void convertToTriangle(){
        short k = 0;
        while (k < size - 1) {
            short maxString = k;
            short i = (short) (k + 1);
            while (i < size) {
                if (Math.abs(elements[i][k]) > Math.abs(elements[maxString][k]))
                    maxString = i;
                ++i;
            }

            if (maxString != k) {
                double[] buff = elements[k];
                elements[k] = elements[maxString];
                elements[maxString] = buff;
            }
            i = (short) (k + 1);
            while (i < size) {
                short j = (short) (k + 1);
                while (j < size) {
                    elements[i][j] -= elements[k][j] * elements[i][k] / elements[k][k];
                    ++j;
                }
                elements[i][k] = 0;
                ++i;
            }
            ++k;
        }
    }

    public ProgrammeResult<String> getSignGroups(){
        String positiveElements = "";
        String negativeElements = "";
        String zeroElements = "";
        for (double[] string : elements){
            for (double element : string){
                if (element > 0){
                    positiveElements += precision.format(element);
                    positiveElements += ' ';
                }
                else if (element < 0){
                    negativeElements += precision.format(element);
                    negativeElements += ' ';
                }
                else{
                    zeroElements += 0;
                    zeroElements += ' ';
                }
            }
        }

        if (positiveElements != "")
            positiveElements = positiveElements.substring(0, positiveElements.length() -1);
        if (negativeElements != "")
            negativeElements = negativeElements.substring(0, negativeElements.length() -1);
        if (zeroElements != "")
            zeroElements = zeroElements.substring(0, zeroElements.length() -1);

        String groups = String.format("Положительные элементы: %s\nОтрицательные элементы: %s\nНули: %s", positiveElements, negativeElements, zeroElements);

        return new ProgrammeResult<>("Элементы успешно сгруппированы!", true, groups);

    }

    public ProgrammeResult<Double> calculateSum(){
        double sum = 0;
        for (double[] string : elements)
            for (double element : string){
                sum += element;
            }

        return new ProgrammeResult<>("Сумма элементов матрицы вычислена!", true, sum);
    }
}
