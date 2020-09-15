package ru.fyodorov.lesson2;

/**
 * @author Fyodorov Alexandr
 * @code Entry
 *
 * Класс хранит ключ, значения и узлы в HashMap
 */
public class Entry {
    /**
     * Поля класса
     */
    final Object key;
    Object value;
    Entry next;

    /**
     * Конструктор класса
     *
     * @param key ключ
     * @param value значение
     * @param next ссылка на следующий узел
     */
    public Entry(Object key, Object value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public Entry getNext() {
        return next;
    }

    /**
     * Переопределенный метод equals
     *
     * @param o объект
     * @return возвращаем true если ключ и значение одинаковые, в другом случае - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Entry) {
            Entry entry = (Entry) o;
            return key.equals (entry.getKey())&&value.equals(entry.getValue());
        }
        return false;
    }

    /**
     * Переопределенный метод hashCode
     *
     * @return если ключ и значения не найдены возвращает null, в другом случае возвращает hashCode
     */
    @Override
    public int hashCode() {
        int hash = 12;
        hash = 19 * hash + ((key == null) ? 0 : key.hashCode());
        hash = 19 * hash + ((value == null) ? 0 : value.hashCode());
        return hash;
    }

    /**
     * Переопределенный метод toString
     *
     * @return возвращает объекты
     */
    @Override
    public String toString() {
        return "{" +
                "key = " + getKey() +
                ", value = " + getValue() +
                '}' + '\n';
    }
}
