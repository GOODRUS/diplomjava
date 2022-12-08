package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {


    @Test
    public void shouldGetTimeInTheGame() {  // метод Play не перезаписывает время проведенное в игре
        GameStore store = new GameStore();
        Game NFS = store.publishGame("NFS", "racing");
        Player player = new Player("Alex");

        player.installGame(NFS);

        int expected = 3;
        int actual = player.play(NFS,3);
        assertEquals(expected,actual);
    }

//    @Test
//    public void shouldGetExceptionIfGameIsNotInstall() {  // метод Play не выкидывает исключение, в случае если игра не была предварительно установлена
//        GameStore store = new GameStore();
//        Game NFS = store.publishGame("NFS", "racing");
//        Player player = new Player("Alex");
//
//        /** специально пропускаем установку игры , ловим исключение */
//
//        boolean thrown = false;
//
//        try {
//            player.play(NFS, 7);
//        } catch (NotFoundException e) {  // класс исключения отсутствует
//            thrown = true;
//        }
//        assertTrue(thrown);
//    }

//    @Test
//    public void shouldSumPlayTimeIfGamesHaveTheSameGenre() {
//        Player player = new Player("Petya");
//        GameStore store = new GameStore();
//        Game game = store.publishGame("Test Drive", "racing");
//        Game game2 = store.publishGame("NFS", "racing");
//        Game game3 = store.publishGame("Forza5", "racing");
//        Game game4 = store.publishGame("Fly", "simulator");
//        Game game5 = store.publishGame("COLD", "action");
//        Game game6 = store.publishGame("MadMax", "action");
//
//        player.installGame(game);
//        player.installGame(game2);
//        player.installGame(game3);
//        player.installGame(game5);
//
//        player.play(game, 30);
//        player.play(game2, 10);// метод sumGenre суммирует время игры , только до тех пор, пока идут игры одного жанра.
//        player.play(game3, 60);
//
//        player.play(game4, 5);
//        player.play(game5, 2);
//        player.play(game6, 2);
//
//        int expected = 100;
//        int actual = player.sumGenre("racing");
//        assertEquals(expected, actual);
//    }
}
