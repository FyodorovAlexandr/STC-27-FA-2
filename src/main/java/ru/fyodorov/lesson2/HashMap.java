package ru.fyodorov.lesson2;

/**
 * @author Fyodorov Alexandr
 * @code HashMap
 *
 * Класс реализовывает коллекцию HashMap
 */
public class HashMap {
    public static void main(String[] args) {
    HashMap hashMap = new HashMap();
    hashMap.put(1, "Ivan");
    hashMap.put(2, "Petr");
    hashMap.put(3, "Andrey");
        System.out.println(hashMap.toString());
        System.out.println(hashMap.get(1));
        System.out.println(hashMap.remove(1));
        System.out.println(hashMap.toString());
    }

    /**
     * Поля класса
     */
    private int capacity;
    private int size = 0;
    public Entry[] bucket;
    private final double loadFactor = 0.75;

    /**
     * Начальный размер массива - 16
     */
    public HashMap(){
        this(16);
    }

    /**
     * Конструктор класса
     * @param capacity вместимость HashMap
     */
    public HashMap(int capacity) {
        this.capacity = capacity;
        this.bucket = new Entry[this.capacity];
    }

    /**
     * Метод позволяет добавлять ключ-значение в HashMap
     * Ключ-значение может содержать любой тип данных
     * При заполнении массива - увеличиваем его в 2 раза
     *
     * @param key ключ
     * @param value значение
     */
    public void put (Object key, Object value){
        if (size == loadFactor * capacity) {
            Entry[] oldBucket = bucket;
            capacity *= 2;
            size = 0;
            bucket = new Entry[capacity];
            for (Entry entry : oldBucket) {
                while (entry != null) {
                    put(entry.key, entry.value);
                    entry = entry.next;
                }
            }
        }

        /**
         * Создаем объект entry и вычисляем индекс,
         * помещаем объект с полученным индексом, если место свободно,
         * если с таким индексом уже существует объект (коллизия),
         * то проверяем с помощью hashCode и equals, что оба ключа одинаковы,
         * если ключи одинаковые - заменяем значения новыми.
         * В другом случае связываем новый и старый объект, указав ссылку на след. объект
         */
        Entry entry = new Entry(key, value, null);
        int index = getIndex(key);

        Entry current = bucket[index];
        if (current == null) {
            bucket[index] = entry;
            size++;
        } else {
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value;
            } else {
                current.next = entry;
                size++;
            }
        }
    }

    /**
     * Метод позволяет получить данные из HasMap по ключу
     * если не находим - возвращаем null
     *
     * @param key ключ
     * @return если условие совпадает - возвращаем объект, в другом случае null
     */
    public Object get (Object key){
        Entry buckets = bucket[getIndex(key)];
        while (buckets != null) {
            if (getHash(key) == getHash(buckets.key)) {
                if (key.equals(buckets.key)) {
                    return buckets.getValue();
                }
            }
            buckets = buckets.next;
        }
        return null;
    }

    /**
     * Метод производит проверку ключа в HashMap
     *
     * @param key ключ
     * @return возвращает true или false
     */
    public boolean contains (Object key){
        Entry buckets = bucket[getIndex(key)];
        while (buckets != null) {
            if (getHash(key) == getHash(buckets.key)) {
                if (key.equals(buckets.key)) {
                    return true;
                } else buckets = buckets.next;
            }
        }
        return false;
    }

    /**
     * Метод позволяет удалить данные из HashMap
     *
     * @param key ключ
     * @return при удалении возвращает true, в другом случае false
     */
    public boolean remove (Object key){
        Entry previous = null;
        Entry current = bucket[getIndex(key)];
        if (current == null) {
            return false;
        }
        while (current != null) {
            if (current.key.equals(key)) {
                if (previous != null) {
                    previous.next = current.next;

                } else {
                    bucket[getIndex(key)] = null;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /**
     * Метод возвращает hashCode объекта
     *
     * @param key ключ
     * @return если объект равен null - возвращает null, в другом случае hashCode объекта
     */
    public int getHash (Object key){
        return key == null ? 0 : Math.abs(key.hashCode());
    }

    /**
     * Метод возвращает размер массива bucket
     *
     * @return возвращает размер массива
     */
    public int getBucketsSize () {
        return bucket.length;
    }

    /**
     * Метод возвращает количество элементов
     * @return size
     */
    public int getSize () {
        return size;
    }

    /**
     * Метод проверяет пусто ли в HashMap
     *
     * @return возвращает true или false
     */
    public boolean isEmpty () {
        return getSize() == 0;
    }

    /**
     * Метод вычисляет хешключ
     *
     * @param key ключ
     * @return возвращает индекс
     */
    public int getIndex (Object key){
        return getHash(key) & getBucketsSize() - 1;
    }

    /**
     * Метод позволяет вывести объекты
     *
     * @return возвращает объекты
     */
    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        for (Entry entry : bucket) {
            while (entry != null) {
                sb.append(entry);
                if (entry.next != null) {
                    sb.append(", ");
                }
                entry = entry.next;
            }
        }
        return sb.toString();
    }
}
