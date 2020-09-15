package ru.fyodorov.lesson2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
    private HashMap hashMap;

    @Before
    public void createNewHashTable() {
        hashMap = new HashMap();
    }

    /**
     * Добавляет ли метод put ключ-значение в HashMap
     */
    @Test
    public void put() {
        hashMap.put(1, "Ivan");
        hashMap.put(2, "Petr");
        Object actual = hashMap.get(1);
        Assert.assertEquals("Ivan", actual);
        actual = hashMap.get(2);
        Assert.assertEquals("Petr", actual);
    }

    /**
     * Возвращает ли метод get верное значение
     */
    @Test
    public void get() {
        hashMap.put(1, "Ivan");
        Assert.assertEquals("Ivan", hashMap.get(1));
    }

    /**
     * Удаляет ли метод remove объект, который указали
     */
    @Test
    public void removeTrue() {
        hashMap.put(1, "Ivan");
        hashMap.put(2, "Petr");
        hashMap.remove(1);
        Assert.assertFalse(hashMap.contains(1));
        Assert.assertTrue(hashMap.contains(2));
    }

    /**
     * Не влияет ли удаление на новый HashMap
     */
    @Test
    public void removeDoesNotEffectNewHashMap() {
        hashMap.remove(1);
        Assert.assertEquals(0, hashMap.getSize());
    }

    /**
     * Уменьшает ли метод remove размер HashMap при удалении объекта
     */
    @Test
    public void removeSize() {
        hashMap.put("Ivan", "Ivanov");
        hashMap.put("Petr", "Petrov");
        hashMap.remove("Ivan");
        Assert.assertEquals(1, hashMap.getSize());
        hashMap.remove("Petr");
        Assert.assertEquals(0, hashMap.getSize());
    }

    /**
     * Содержит ли метод contains значение, если содержит вернет try
     */
    @Test
    public void contains() {
        hashMap.put(1, "Ivan");
        Assert.assertTrue(hashMap.contains(1));
    }

    /**
     * Возвращает ли правильное значение метод getSize
     */
    @Test
    public void getSize() {
        hashMap.put("Ivan", "Ivanov");
        Assert.assertEquals(1, hashMap.getSize());
        hashMap.put("Petr", "Petrov");
        Assert.assertEquals(2, hashMap.getSize());
    }

    /**
     * Возвращает ли getSize 0, если HashMap пустой
     */
    @Test
    public void testGetSizeIfMapIsEmpty() {
        Assert.assertEquals(0, hashMap.getSize());
    }

    /**
     * Вщзвращает ли метод true, если HashMap пустой
     */
    @Test
    public void isEmptyTrue() {
        Assert.assertTrue(hashMap.isEmpty());
    }

    /**
     * Вщзвращает ли метод false, если HashMap заполнен
     */
    @Test
    public void isEmptyFalse() {
        hashMap.put(1, "Ivan");
        Assert.assertFalse(hashMap.isEmpty());
    }

    /**
     * Не переписывает ли метод put ключи с одинаковым хешкодом
     */
    @Test
    public void putHash() {
        hashMap.put("Ivan", 5);
        hashMap.put("Petr", 10);
        Assert.assertEquals(5,hashMap.get("Ivan"));
        Assert.assertEquals(10,hashMap.get("Petr"));
    }
}