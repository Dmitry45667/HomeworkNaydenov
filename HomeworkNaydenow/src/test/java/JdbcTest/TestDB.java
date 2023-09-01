package JdbcTest;

import DBconfig.ConnectionPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;

public class TestDB {
    @Test
    @DisplayName("Тест БД, добавление товара.JDBC")
    public void addCheckDel() {

        FoodDB foodDB = new FoodDB();

        // Шаг 1: Добавление товара в таблицу FOOD
        foodDB.insertFood("Огурец", "VEGETABLE", 0);
        System.out.println("Шаг 1: Запрос выполнен");

        // Шаг 2: Просмотр содержимого таблицы FOOD
        foodDB.displayFoodTable();
        System.out.println("Шаг 2: Запрос вернул содержимое таблицы FOOD");

        // Постусловие: Удаление добавленного товара
        foodDB.deleteGoodById(5);

    }

    @Test
    @DisplayName("Тест БД, добавление товара.JDBC Template")
    public void addCheckDelTemplate()  {

        DataSource dataSource = ConnectionPool.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        FoodDBTemplate foodDBTemplate = new FoodDBTemplate(jdbcTemplate);

        // Шаг 1: Добавление товара в таблицу FOOD
        foodDBTemplate.insertFood("Огурец", "VEGETABLE", 0);
        System.out.println("Шаг 1: Запрос выполнен");

        // Шаг 2: Просмотр содержимого таблицы FOOD
        foodDBTemplate.displayFoodTable();
        System.out.println("Шаг 2: Запрос вернул содержимое таблицы FOOD");

        // Постусловие: Удаление добавленного товара
        foodDBTemplate.deleteFoodById(5);


    }
}