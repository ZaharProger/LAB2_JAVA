package LAB2;

//Интерфейс объектов, данные которых можно анализировать

public interface Analyzable<T> {
    ProgrammeResult<T> analyze(short bottomLim, short topLim);
}
