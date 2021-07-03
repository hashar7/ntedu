package com.company;

import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector {

    private double[] array;

    public ArrayVectorImpl() {
        array = new double[10];
    }

    public ArrayVectorImpl(double[] elements) {
        array = elements.clone();
    }

    public ArrayVectorImpl(int capacity) {
        if (capacity > 0) {
            array = new double[capacity];
        } else {
            array = new double[10];
        }
    }

    /**
     * Задает все элементы вектора (определяет длину вектора).
     * Передаваемый массив не клонируется.
     *
     * @param elements Не равен null
     */
    @Override
    public void set(double... elements) {
        int length = elements.length;
        array = new double[length];
        System.arraycopy(elements, 0, array, 0, length);
    }

    /**
     * Возвращает все элементы вектора. Массив не клонируется.
     */
    @Override
    public double[] get() {
        return array;
    }

    /**
     * Возвращает копию вектора (такую, изменение элементов
     * в которой не приводит к изменению элементов данного вектора).<br/>
     * Рекомендуется вызвать метод clone() у самого массива или использовать
     * {@link System#arraycopy(Object, int, Object, int, int)}.
     */
    @Override
    public ArrayVector clone() {
        return new ArrayVectorImpl(array.clone());
    }

    /**
     * Возвращает число элементов вектора.
     */
    @Override
    public int getSize() {
        return this.array.length;
    }

    /**
     * Изменяет элемент по индексу.
     *
     * @param index В случае выхода индекса за пределы массива:<br/>
     *              а) если index<0, ничего не происходит;<br/>
     *              б) если index>=0, размер массива увеличивается так, чтобы index стал последним.
     * @param value
     */
    @Override
    public void set(int index, double value) {
        if (index < 0) {
            return;
        } else if (index < array.length) {
            array[index] = value;
            return;
        }
        double[] new_array = new double[index + 1];
        System.arraycopy(array, 0, new_array, 0, array.length);
        array = new_array;
        array[index] = value;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index В случае выхода индекса за пределы массива
     *              должно генерироваться ArrayIndexOutOfBoundsException
     */
    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * Возвращает максимальный элемент вектора.
     */
    @Override
    public double getMax() {
        return Arrays.stream(array).max().getAsDouble();
    }

    /**
     * Возвращает минимальный элемент вектора.
     */
    @Override
    public double getMin() {
        return Arrays.stream(array).min().getAsDouble();
    }

    /**
     * Сортирует элементы вектора в порядке возрастания.
     */
    @Override
    public void sortAscending() {
        Arrays.sort(array);
    }

    /**
     * Умножает вектор на число.<br/>
     * Замечание: не пытайтесь использовать безиндексный цикл foreach:
     * для изменения элемента массива нужно знать его индекс.
     *
     * @param factor
     */
    @Override
    public void mult(double factor) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            array[i] *= factor;
        }
    }

    /**
     * Складывает вектор с другим вектором, результат запоминает в элементах данного вектора.<br/>
     * Если векторы имеют разный размер, последние элементы большего вектора не учитываются<br/>
     * (если данный вектор - больший, его размер менять не надо, просто не меняйте последние элементы).
     *
     * @param anotherVector Не равен null
     * @return Ссылка на себя (результат сложения)
     */
    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        int length = Math.min(array.length, anotherVector.getSize());
        for (int i = 0; i < length; i++) {
            array[i] += anotherVector.get(i);
        }
        return this;
    }

    /**
     * Возвращает скалярное произведение двух векторов.<br/>
     * Если векторы имеют разный размер, последние элементы большего вектора не учитываются.
     *
     * @param anotherVector Не равен null
     */
    @Override
    public double scalarMult(ArrayVector anotherVector) {
        int length = Math.min(array.length, anotherVector.getSize());
        double result = 0;
        for (int i = 0; i < length; i++) {
            result += array[i] * anotherVector.get(i);
        }
        return result;
    }

    /**
     * Возвращает евклидову норму вектора (длину вектора
     * в n-мерном евклидовом пространстве, n={@link #getSize()}).
     * Это можно подсчитать как корень квадратный от скалярного произведения вектора на себя.
     */
    @Override
    public double getNorm() {
        return Math.sqrt(this.scalarMult(this));
    }
}
