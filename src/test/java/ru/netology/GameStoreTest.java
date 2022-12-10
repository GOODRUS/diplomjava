package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    GameStore store = new GameStore();

    @Test
    public void shouldAddGame() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game)); // Метод containsGame не производит поиск игры в каталоге, ошибка в цикле, счётчик начинается с 1, при проходе отладчиком до условия не доходит, выкидывает false
    }

    @Test
    public void shouldCheckWhoPlayMostTimeMoreOneHour() {

        store.addPlayTime("Petya", 30);
        store.addPlayTime("Vasya", 35);

        String actual = "Vasya";
        String expected = store.getMostPlayer(); // в случае если у одного участника времени наиграно больше, чем у другого возвращает верное значение

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckWhoPlayMostTimeEquals() {

        store.addPlayTime("Petya", 30);
        store.addPlayTime("Vasya", 30);

        String actual = "Vasya";
        String expected = store.getMostPlayer(); /*
                                                   возвращает некорректное значение в случае, если участники отыграли равное
                                                   количество времени, опять же в условиях метода об этом ничего не сказано,
                                                   может тут, что-то придумать, чтобы метод возвращал нужные имена игроков.
                                                 */
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckWhoPlayMostTime() {

        store.addPlayTime("Petya", 1);
        store.addPlayTime("Vasya", 0);

        String actual = "Petya";
        String expected = store.getMostPlayer(); // возвращает некорректное значение-null в случае, если один из участников отыграл 0 часов, а другой 1 час

        assertEquals(expected, actual);
    }


    @Test
    public void shouldCheckWhoPlayMostTimeSomePlayers() {

        store.addPlayTime("Petya", 0);
        store.addPlayTime("Vasya", 20);
        store.addPlayTime("Anya", 30);
        store.addPlayTime("Sveta", 1);

        String actual = "Anya";
        String expected = store.getMostPlayer(); // Здесь метод сработал корректно

        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestAddPlayTimeSamePlayers() {

        store.addPlayTime("Petya", 20);
        store.addPlayTime("Petya", 30);


        String actual = "Petya";
        String expected = store.getMostPlayer(); // Проверка для покрытия кода в методе addPlayTime, в итоге у метода getMostPlayer для сравнения осталась только одна запись - первая

        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestIfPlayerNone() {

        String actual = null;
        String expected = store.getMostPlayer(); // возвращает null согласно описанному методу

        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestSumPlayTimeOfAllPlayers() {

        store.addPlayTime("Petya", 0);
        store.addPlayTime("Vasya", 20);
        store.addPlayTime("Anya", 30);
        store.addPlayTime("Sveta", 1);

        int actual = 51;
        int expected = store.getSumPlayedTime(); // метод не производит суммирование времени проведённого игроками за играми этого каталога(нет описания метода)

        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestSumPlayTimeOnePlayer() {

        store.addPlayTime("Petya", 20);
        store.addPlayTime("Petya", 30);

        int expected = 50;
        int actual = store.getSumPlayedTime(); /*
                                               метод не суммирует время проведенное одним и тем же игроком в игре, нужно дописать его и
                                               в методе addPlayTime добавить возможность сложения значений (возможно через map.Merge())
                                               */

        assertEquals(expected, actual);
    }
}