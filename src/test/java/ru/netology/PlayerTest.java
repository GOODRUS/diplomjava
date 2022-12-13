package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {


    @Test
    public void shouldGetTimeInTheGame() {
        GameStore store = new GameStore();
        Game NFS = store.publishGame("NFS", "racing");
        Player player = new Player("Alex");

        player.installGame(NFS);

        int expected = 3;
        int actual = player.play(NFS, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetExceptionIfGameIsNotInstall() {
        GameStore store = new GameStore();
        Game NFS = store.publishGame("NFS", "racing");
        Player player = new Player("Alex");

        /** специально пропускаем установку игры , ловим исключение */

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
        Player player = new Player("Petya");
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Test Drive", "racing");
        Game game2 = store.publishGame("NFS", "racing");
        Game game3 = store.publishGame("Forza5", "racing");
        Game game4 = store.publishGame("Fly", "action");
        Game game5 = store.publishGame("Fly2", "simulator");

        player.installGameAlternative(game1);
        player.installGameAlternative(game2);
        player.installGameAlternative(game3);
        player.installGameAlternative(game4);
        player.installGameAlternative(game5);

        player.play(game1, 30);
        player.play(game3, 60);
        player.play(game2, 10);
        player.play(game4, 50);
        player.play(game5, 20);

        int expected = 20;
        int actual = player.sumGenre("simulator");
        assertEquals(expected, actual);
    }


    @Test
    public void shouldNotRewriteGameTimeIfTheGameWillReinstall() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");
        Game game = store.publishGame("Test Drive", "simulator");
        Game game2 = store.publishGame("NFS", "simulator");
        Game game3 = store.publishGame("Forza5", "simulator");
        Game game4 = store.publishGame("Fly", "simulator");

        player.installGame(game);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);

        player.play(game, 25);
        player.play(game2, 10);
        player.play(game3, 60);
        player.play(game4, 5);
        player.installGameAlternative(game4);  /** устанавливаем второй раз - программа должна игнорировать это действие
         и не перезаписывать время игры на 0 */

        int expected = 100;
        int actual = player.sumGenre("simulator");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowBestGameInTheGenre() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");

        Game game = store.publishGame("Test Drive", "simulator");
        Game game2 = store.publishGame("NFS", "racing");
        Game game3 = store.publishGame("Forza5", "simulator");
        Game game4 = store.publishGame("Fly", "simulator");

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
        GameStore store = new GameStore();
        Player player = new Player("Petya");

        Game game = store.publishGame("Test Drive", "simulator");
        Game game2 = store.publishGame("NFS", "racing");
        Game game3 = store.publishGame("Forza5", "simulator");
        Game game4 = store.publishGame("Fly", "simulator");

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
}

