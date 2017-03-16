package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 *
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {

    private int[] data;

    public MyArrayList() {
        final int default_capacity = 2;
        data = new int[default_capacity];
    }

    public MyArrayList(int capacity) {
        data = new int[capacity];
    }

    @Override
    public void add(int item) {
        if (size == data.length) {
            int newLength = size * 2;
            int[] newData = new int[newLength];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
        data[size] = item;
        size++;
    }

    @Override
    public int remove(int idx) throws NoSuchElementException {
        if (idx < 0 || idx >= size) {
            throw new NoSuchElementException("MyArrayList : wrong index to remove");
        }
        int val = data[idx];
        for (int index = idx; index < size - 1; ++index) {
            data[index] = data[index + 1];
        }
        size--;
        return val;
    }

    @Override
    public int get(int idx) throws NoSuchElementException {
        if (idx < 0 || idx >= size) {
            throw new NoSuchElementException("MyArrayList: wrong index to get");
        }
        return data[idx];
    }

    /*@Override
    public int size() {
        return size;
    }*/
}
