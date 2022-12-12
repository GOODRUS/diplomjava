package ru.netology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;

    /**
     * информация о том, в какую игру сколько часов было сыграно
     * ключ - игра
     * значение - суммарное количество часов игры в эту игру
     */
    private Map<Game, Integer> playedTime = new HashMap<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * добавление игры игроку
     * если игра уже была, никаких изменений происходить не должно
     */
    public void installGame(Game game) {
        playedTime.put(game, 0);
    }

    public void installGameAlternative(Game game) {
        if (playedTime.containsKey(game)) {
            playedTime.put(game, playedTime.get(game));
        } else {
            playedTime.put(game, 0);
        }
    }



    /**
     * игрок играет в игру game на протяжении hours часов
     * об этом нужно сообщить объекту-каталогу игр, откуда была установлена игра
     * также надо обновить значения в мапе игрока, добавив проигранное количество часов
     * возвращает суммарное количество часов, проигранное в эту игру.
     * если игра не была установлена, то надо выкидывать RuntimeException
     */

    public int play(Game game, int hours) {
        game.getStore().addPlayTime(name, hours);
        if (playedTime.containsKey(game)) {
            playedTime.merge(game, hours, (oldValue, newValue) -> oldValue + newValue);
        } else {
            throw new NotInstallException(game.getTitle());
        }
        return playedTime.get(game);
    }


    /**
     * Метод принимает жанр игры (одно из полей объекта игры) и
     * суммирует время, проигранное во все игры этого жанра этим игроком
     */
    public int sumGenre(String genre) {
        int sum = 0;
        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                sum += playedTime.get(game);
            } else {
                sum += 0;
            }
        }
        return sum;
    }

    /**
     * Метод принимает жанр и возвращает игру этого жанра, в которую играли больше всего
     * Если в игры этого жанра не играли, возвращается null
     */

    public Game mostPlayerByGenre(String genre) {

        ArrayList<Game> games = new ArrayList<>();
        Game bestGame = null;
        int mostTime = 0;

        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                games.add(game);
            } else {
                continue;
            }
            int gamePlay = playedTime.get(game);
            if (gamePlay >= mostTime) {
                mostTime = gamePlay;
                bestGame = game;
            }
        }
        return bestGame;
    }

    public ArrayList<Game> mostPlayerByGenreAlternative(String genre) {

        ArrayList<Game> games = new ArrayList<>();

        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                games.add(game);
            } else {
                continue;
            }
            int mostTime = 0;
            int gameTime = playedTime.get(game);
            if (gameTime >= mostTime) {
                mostTime = gameTime;
            } else {
                games.remove(game);
            }
        }
        return games;
    }

    public ArrayList<Game> mostPlayerByGenreAlternativeOne(String genre) {

        ArrayList<Game> games = new ArrayList<>();

        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                games.add(game);
                int mostTime = 0;
                int gameTime = playedTime.get(game);
                if (gameTime >= mostTime) {
                    mostTime = gameTime;
                } else {
                    games.remove(game);
                }
            } else {
                continue;
            }
            return games;
        }
        if (games.size() == 0) {
            games.add(null);
        }
        return games;
    }

    public ArrayList<Game> mostPlayerByGenreAlternativeTwo(String genre) {

        ArrayList<Game> games = new ArrayList<>();

        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                games.add(game);
            }
            if (games.size() == 0) {
                games.add(null);
            }
            if (games.size() > 0) {
                games.remove(null);
            } else {
                continue;
            }
            int mostTime = 0;
            int gamePlay = playedTime.get(game);
            if (gamePlay >= mostTime) {
                mostTime = gamePlay;

            } else {
                games.remove(game);
            }
        }
        return games;
    }
}
