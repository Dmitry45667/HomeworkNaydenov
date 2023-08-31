package JdbcTest;

import DBconfig.DataBaseConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;

public class TestDB {
    @Test
    @DisplayName("Тест БД, добавление товара.JDBC")
    public void addCheckDel() {
        DataBaseConfig dataBaseConfig = new DataBaseConfig();
        FoodDB foodDB = new FoodDB(dataBaseConfig);

        // Шаг 1: Добавление товара в таблицу FOOD
        foodDB.insertFood("Огурец", "VEGETABLE", 0);
        System.out.println("Шаг 1: Запрос выполнен");

        // Шаг 2: Просмотр содержимого таблицы FOOD
        foodDB.displayFoodTable();
        System.out.println("Шаг 2: Запрос вернул содержимое таблицы FOOD");

        // Постусловие: Удаление добавленного товара
        foodDB.deleteGoodById(5);
        System.out.println("Постусловие: Товар удален");
    }

    @Test
    @DisplayName("Тест БД, добавление товара.JDBC Template")
    public void addCheckDelTemplate()  {
        DataBaseConfig dataBaseConfig = new DataBaseConfig();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataBaseConfig.getDataSource());
        FoodDBTemplate foodDBTemplate = new FoodDBTemplate(jdbcTemplate);

        // Шаг 1: Добавление товара в таблицу FOOD
        foodDBTemplate.insertFood("Огурец", "VEGETABLE", 0);
        System.out.println("Шаг 1: Запрос выполнен");

        // Шаг 2: Просмотр содержимого таблицы FOOD
        foodDBTemplate.displayFoodTable();
        System.out.println("Шаг 2: Запрос вернул содержимое таблицы FOOD");

        // Постусловие: Удаление добавленного товара
        foodDBTemplate.deleteFoodById(5);
        System.out.println("Постусловие: Товар удален");

    }
}
