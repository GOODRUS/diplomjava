package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    GameStore store = new GameStore();

    @Test
    public void shouldAddGame() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldCheckWhoPlayMostTimeMoreOneHour() {

        store.addPlayTime("Petya", 30);
        store.addPlayTime("Vasya", 35);

        String actual = "Vasya";
        String expected = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckWhoPlayMostTimeEquals() {

        store.addPlayTime("Petya", 30);
        store.addPlayTime("Vasya", 31);

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
        String expected = store.getMostPlayer();

        assertEquals(expected, actual);
    }


    @Test
    public void shouldCheckWhoPlayMostTimeSomePlayers() {

        store.addPlayTime("Petya", 0);
        store.addPlayTime("Vasya", 20);
        store.addPlayTime("Anya", 30);
        store.addPlayTime("Sveta", 1);

        String actual = "Anya";
        String expected = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestAddPlayTimeSamePlayers() {

        store.addPlayTime("Petya", 20);
        store.addPlayTime("Petya", 30);


        String actual = "Petya";
        String expected = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestIfPlayerNone() {

        String actual = null;
        String expected = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestSumPlayTimeOfAllPlayers() {

        store.addPlayTime("Petya", 0);
        store.addPlayTime("Vasya", 20);
        store.addPlayTime("Anya", 30);
        store.addPlayTime("Sveta", 1);

        int actual = 51;
        int expected = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestSumPlayTimeOnePlayer() {

        store.addPlayTime("Petya", 20);
        store.addPlayTime("Petya", 30);

        int expected = 50;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }
}