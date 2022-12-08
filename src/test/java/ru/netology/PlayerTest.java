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
        int actual = player.play(NFS, 3);
        assertEquals(expected, actual);
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




    @Test    // Почему тест падает если запустить его , но проходит в режиме отладки ??
    public void shouldSumPlayTimeIfGamesHaveTheSameGenre() {
        Player player = new Player("Petya");
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Test Drive", "racing");
        Game game2 = store.publishGame("NFS", "racing");
        Game game3 = store.publishGame("Forza5", "racing");
        Game game4 = store.publishGame("Fly", "action");
        Game game5 = store.publishGame("Fly2", "simulator");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);

        player.play(game1, 30);
        player.play(game3, 60);
        player.play(game2, 10);
        player.play(game4, 50);
        player.play(game5, 20);

        int expected = 20;
        int actual = player.sumGenre("simulator");
        assertEquals(expected, actual);
    }



    @Test // тест актуален после исправления метода "Play"
    public void shouldNotRewriteGameTimeIfTheGameWillReinstall() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");
        Game game = store.publishGame("Test Drive", "simulator");
        Game game2 = store.publishGame("NFS", "simulator");
        Game game3 = store.publishGame("Forza5", "simulator");
        Game game4 = store.publishGame("Fly", "simulator");

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
}

