package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    ru.netology.GameStore store = new ru.netology.GameStore();
    ru.netology.Game NFS = store.publishGame("NFS", "racing");
    ru.netology.Player player = new ru.netology.Player("OLO");
    ru.netology.Game game5 = store.publishGame("Test Drive", "simulator");
    ru.netology.Game game6 = store.publishGame("NFS", "racing");
    ru.netology.Game game7 = store.publishGame("Forza5", "simulator");
    ru.netology.Game game8 = store.publishGame("Fly", "simulator");


    @Test
    public void shouldGetTimeInTheGame() {

        ru.netology.GameStore store = new ru.netology.GameStore();
        ru.netology.Game NFS = store.publishGame("NFS", "racing");
        ru.netology.Player player = new ru.netology.Player("Alex");

        player.installGame(NFS);

        int expected = 3;
        int actual = player.play(NFS, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetExceptionIfGameIsNotInstall() {

        ru.netology.GameStore store = new ru.netology.GameStore();
        ru.netology.Game NFS = store.publishGame("NFS", "racing");
        ru.netology.Player player = new ru.netology.Player("Alex");

        /** специально пропускаем установку игры, ловим исключение */

        boolean thrown = false;

        try {
            player.play(NFS, 7);
        } catch (NotInstallException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void shouldSumPlayTimeIfGamesHaveTheSameGenre() {
        ru.netology.Player player = new ru.netology.Player("Petya");
        ru.netology.GameStore store = new ru.netology.GameStore();
        ru.netology.Game game1 = store.publishGame("Test Drive", "racing");
        ru.netology.Game game2 = store.publishGame("NFS", "racing");
        ru.netology.Game game3 = store.publishGame("Forza5", "racing");
        ru.netology.Game game4 = store.publishGame("Fly", "action");
        ru.netology.Game game5 = store.publishGame("Fly2", "simulator");
        ru.netology.Game game6 = store.publishGame(null, "simulator");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);
        player.installGame(game6);

        player.play(game1, 30);
        player.play(game3, 60);
        player.play(game2, 10);
        player.play(game4, 50);
        player.play(game5, 20);
        player.play(game6, 20);


        int expected = 40;
        int actual = player.sumGenre("simulator");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotRewriteGameTimeIfTheGameWillReinstall() {
        ru.netology.GameStore store = new ru.netology.GameStore();
        ru.netology.Player player = new ru.netology.Player("Petya");
        ru.netology.Game game = store.publishGame("Test Drive", "simulator");
        ru.netology.Game game2 = store.publishGame("NFS", "simulator");
        ru.netology.Game game3 = store.publishGame("Forza5", "simulator");
        ru.netology.Game game4 = store.publishGame("Fly", "simulator");

        player.installGame(game);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);

        player.play(game, 25);
        player.play(game2, 10);
        player.play(game3, 60);
        player.play(game4, 5);
        player.installGame(game4);  /** устанавливаем второй раз - программа должна игнорировать это действие
         и не перезаписывать время игры на 0 */

        int expected = 100;
        int actual = player.sumGenre("simulator");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowBestGameInTheGenre() {
        ru.netology.GameStore store = new ru.netology.GameStore();
        ru.netology.Player player = new ru.netology.Player("Petya");

        ru.netology.Game game = store.publishGame("Test Drive", "simulator");
        ru.netology.Game game2 = store.publishGame("NFS", "racing");
        ru.netology.Game game3 = store.publishGame("Forza5", "simulator");
        ru.netology.Game game4 = store.publishGame("Fly", "simulator");

        player.installGame(game);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);

        player.play(game, 25);
        player.play(game2, 10);
        player.play(game3, 60);
        player.play(game4, 5);

        assertEquals(game3, player.mostPlayerByGenre("simulator"));
    }

    @Test
    public void shouldReturnNullInCaseNoGameInTheGenre() {
        ru.netology.GameStore store = new ru.netology.GameStore();
        ru.netology.Player player = new ru.netology.Player("Petya");

        ru.netology.Game game = store.publishGame("Test Drive", "simulator");
        ru.netology.Game game2 = store.publishGame("NFS", "racing");
        ru.netology.Game game3 = store.publishGame("Forza5", "simulator");
        ru.netology.Game game4 = store.publishGame("Fly", "simulator");

        player.installGame(game);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);

        player.play(game, 25);
        player.play(game2, 10);
        player.play(game3, 60);
        player.play(game4, 5);

        assertEquals(null, player.mostPlayerByGenre("arcada"));
    }

    @Test
    public void shouldTestEquals() {
        ru.netology.GameStore store = new ru.netology.GameStore();
        ru.netology.Player player = new ru.netology.Player("Petya");
        ru.netology.Player player1 = new ru.netology.Player("Petya");
        ru.netology.Player player2 = new ru.netology.Player("Petya");

        ru.netology.Game game = store.publishGame("Test Drive", "simulator");
        ru.netology.Game game2 = store.publishGame("NFS", "racing");
        ru.netology.Game game3 = store.publishGame("Forza5", "simulator");
        ru.netology.Game game4 = store.publishGame("NFS MW", "racing");
        ru.netology.Game game5 = store.publishGame("Test Drive", "simulator");
        ru.netology.Game game6 = store.publishGame("NFS", "racing");
        ru.netology.Game game7 = store.publishGame("Forza4", "simulator");
        ru.netology.Game game8 = store.publishGame("Fly", "simulator");

        player.installGame(game);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);
        player.installGame(game6);
        player.installGame(game7);
        player.installGame(game8);

        player.play(game, 25);
        player.play(game2, 10);
        player.play(game3, 60);
        player.play(game4, 5);
        player.play(game5, 25);
        player.play(game6, 10);
        player.play(game7, 65);
        player.play(game8, 60);

        ru.netology.Game expected = game7;

        ru.netology.Game actual = player.mostPlayerByGenre("simulator");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGamePlay() {
        GameStore store = new ru.netology.GameStore();
        Player player = new ru.netology.Player("Petya");
        Game game = store.publishGame("Test Drive", "simulator");
        Game game2 = store.publishGame("NFS", "simulator");
        Game game3 = store.publishGame("Forza5", "simulator");
        Game game4 = store.publishGame("Fly", "simulator");

        player.installGame(game);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);

        player.play(game, 25);
        player.play(game, 10);
        player.play(game, 60);
        player.play(game, 5);

        int expected = 100;
        int actual = player.sumGenre("simulator");
        assertEquals(expected, actual);
    }
}

