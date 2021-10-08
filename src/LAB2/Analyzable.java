package LAB2;
//Интерфейс анализируемых объектов (используется для реализации проверки ввода в виде лямбда-выражений)
public interface Analyzable<T> {
    ProgrammeResult analyze(T inputData);
}
