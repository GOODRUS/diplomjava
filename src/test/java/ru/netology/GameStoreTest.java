package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    ru.netology.GameStore store = new ru.netology.GameStore();
    ru.netology.Game NFS = store.publishGame("NFS", "racing");
    ru.netology.Player player = new ru.netology.Player("OLO");
    ru.netology.Game game5 = store.publishGame("Test Drive", "simulator");
    ru.netology.Game game6 = store.publishGame("NFS", "racing");
    ru.netology.Game game7 = store.publishGame("Forza5", "simulator");
    ru.netology.Game game8 = store.publishGame("Fly", "simulator");
    ru.netology.Game game9 = null;
    ru.netology.Game game10 = store.publishGame(null, null);
    ru.netology.Game game11 = game5;


    @Test
    public void shouldAddGame() {

        ru.netology.Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");


        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddGameNullTitleGenre() {

        ru.netology.Game game = store.publishGame(null, null);

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddGameNullGenre() {

        ru.netology.Game game = store.publishGame("NFS", null);

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddGameNullTitle() {

        ru.netology.Game game = store.publishGame(null, "Arcade");

        assertTrue(store.containsGame(game));
    }


    @Test
    public void shouldAddGameNull() {

        ru.netology.Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        ru.netology.Game game = null;

        assertFalse(store.containsGame(game)); //
    }

    @Test
    public void shouldCheckWhoPlayMostTimeMoreOneHour() {

        store.addPlayTime("Petya", 30);
        store.addPlayTime("Vasya", 35);

        String actual = "Vasya";
        String expected = store.getMostPlayer(); //

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckWhoPlayMostTimeEquals() {

        store.addPlayTime("Petya", 30);
        store.addPlayTime("Vasya", 30);

        String actual = "Petya";
        String expected = store.getMostPlayer();
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