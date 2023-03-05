package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    GameStore store = new GameStore();
    Player player = new Player("Petya");

    @Test
    public void shouldGetName() {
        // производим проверку (сравниваем ожидаемый и фактический):
        String actual = "Petya";
        String expected = player.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetTitle() {
        // подготовка данных
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        // производим проверку (сравниваем ожидаемый и фактический):
        String actual = "Нетология Баттл Онлайн";
        String expected = game.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfOneGame() {
        // подготовка данных
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        player.installGame(game);
        player.play(game, 3);

        // производим проверку (сравниваем ожидаемый и фактический):
        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSeveralGames() {
        // подготовка данных
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        player.installGame(game);
        player.play(game, 3);
        player.play(game, 10);
        player.play(game, 5);
        player.play(game, 150);
        player.play(game, 1);

        // производим проверку (сравниваем ожидаемый и фактический):
        int expected = 169;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSeveralDifferentGamesDifferentGenres() {
        // подготовка данных
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game6 = store.publishGame("Шахматы", "Головоломка");
        Game game7 = store.publishGame("Формула1", "Гоночки");

        player.installGame(game);
        player.installGame(game6);
        player.installGame(game7);

        player.play(game, 3);
        player.play(game6, 3);
        player.play(game, 10);
        player.play(game, 5);
        player.play(game7, 1);
        player.play(game, 150);
        player.play(game, 1);
        player.play(game6, 101);

        // производим проверку (сравниваем ожидаемый и фактический):
        int expected = 169;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSeveralDifferentGamesMixtureGenres() {
        // подготовка данных
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game5 = store.publishGame("Кроссворд", "Головоломка");
        Game game6 = store.publishGame("Шахматы", "Головоломка");
        Game game7 = store.publishGame("Формула1", "Гоночки");

        player.installGame(game);
        player.installGame(game5);
        player.installGame(game6);
        player.installGame(game7);

        player.play(game, 3);
        player.play(game6, 3);
        player.play(game, 10);
        player.play(game5, 100);
        player.play(game, 5);
        player.play(game7, 1);
        player.play(game, 150);
        player.play(game5, 105);
        player.play(game, 1);
        player.play(game6, 101);
        player.play(game5, 1);

        // производим проверку (сравниваем ожидаемый и фактический):
        int expected = 310;
        int actual = player.sumGenre(game6.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSeveralDifferentGamesSameGenres() {
        // подготовка данных
        Game game7 = store.publishGame("Формула1", "Гоночки");
        Game game8 = store.publishGame("Дакар", "Гоночки");
        Game game9 = store.publishGame("Тур де Франс", "Гоночки");

        player.installGame(game7);
        player.installGame(game8);
        player.installGame(game9);

        player.play(game7, 3);
        player.play(game8, 3);
        player.play(game9, 10);
        player.play(game7, 100);
        player.play(game8, 5);
        player.play(game9, 1);
        player.play(game7, 150);
        player.play(game8, 105);
        player.play(game9, 1);
        player.play(game7, 101);
        player.play(game8, 1);

        // производим проверку (сравниваем ожидаемый и фактический):
        int expected = 480;
        int actual = player.sumGenre(game8.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfZeroHoursValue() {
        // подготовка данных
        Game game7 = store.publishGame("Формула1", "Гоночки");
        Game game8 = store.publishGame("Дакар", "Гоночки");
        Game game9 = store.publishGame("Тур де Франс", "Гоночки");

        player.installGame(game7);
        player.installGame(game8);
        player.installGame(game9);

        player.play(game7, 0);
        player.play(game8, 0);
        player.play(game9, 0);
        player.play(game7, 0);
        player.play(game8, 0);
        player.play(game9, 0);
        player.play(game7, 0);
        player.play(game8, 0);
        player.play(game9, 0);
        player.play(game7, 0);
        player.play(game8, 0);

        // производим проверку (сравниваем ожидаемый и фактический):
        int expected = 0;
        int actual = player.sumGenre(game8.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotInstallGameRepeated() {
        // подготовка данных
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        player.installGame(game);
        player.play(game, 3);
        player.installGame(game);

        // производим проверку (сравниваем ожидаемый и фактический):
        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenre() {
        // подготовка данных
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Поиск сокровищ", "Аркады");
        Game game3 = store.publishGame("На тему учебного материала", "Головоломка");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 1);

        // производим проверку (сравниваем ожидаемый и фактический):
        int expected = 7;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionDuringMethodPlayWithoutInstallGameBefore() {
        // подготовка данных
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        // производим проверку
        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game, 4);
        });
    }

    @Test
    public void shouldFindMostPlayerByGenre() {
        // подготовка данных
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Поиск сокровищ", "Аркады");
        Game game3 = store.publishGame("На тему учебного материала", "Головоломка");
        Game game4 = store.publishGame("Ребусы", "Головоломка");
        Game game5 = store.publishGame("Кроссворд", "Головоломка");
        Game game6 = store.publishGame("Шахматы", "Головоломка");
        Game game7 = store.publishGame("Формула1", "Гоночки");
        Game game8 = store.publishGame("Дакар", "Гоночки");
        Game game9 = store.publishGame("Тур де Франс", "Гоночки");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);
        player.installGame(game6);
        player.installGame(game7);
        player.installGame(game8);
        player.installGame(game9);

        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 1);
        player.play(game4, 3);
        player.play(game5, 4);
        player.play(game6, 100);
        player.play(game7, 3);
        player.play(game8, 4);
        player.play(game9, 1);

        // производим проверку
        Game expected = game6;
        Game actual = player.mostPlayerByGenre("Головоломка");

        assertEquals(expected, actual);

    }


    @Test
    public void shouldFindMostPlayerByGenreIfNotPlaying() {
        // подготовка данных
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Поиск сокровищ", "Аркады");
        Game game3 = store.publishGame("На тему учебного материала", "Головоломка");
        Game game4 = store.publishGame("Ребусы", "Головоломка");
        Game game5 = store.publishGame("Кроссворд", "Головоломка");
        Game game6 = store.publishGame("Шахматы", "Головоломка");
        Game game7 = store.publishGame("Формула1", "Гоночки");
        Game game8 = store.publishGame("Дакар", "Гоночки");
        Game game9 = store.publishGame("Тур де Франс", "Гоночки");
        Game game10 = store.publishGame("Синтезатор", "Музыка");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);
        player.installGame(game6);
        player.installGame(game7);
        player.installGame(game8);
        player.installGame(game9);
        player.installGame(game10);

        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 1);
        player.play(game4, 3);
        player.play(game5, 100);
        player.play(game6, 100);
        player.play(game7, 3);
        player.play(game8, 4);
        player.play(game9, 1);

        // производим проверку
        Game actual = player.mostPlayerByGenre("Музыка");

        assertEquals(null, actual);

    }

    @Test
    public void shouldFindMostPlayerByGenreIfZeroHoursValue() {
        // подготовка данных
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Поиск сокровищ", "Аркады");
        Game game3 = store.publishGame("На тему учебного материала", "Головоломка");
        Game game4 = store.publishGame("Ребусы", "Головоломка");
        Game game5 = store.publishGame("Кроссворд", "Головоломка");
        Game game6 = store.publishGame("Шахматы", "Головоломка");
        Game game7 = store.publishGame("Формула1", "Гоночки");
        Game game8 = store.publishGame("Дакар", "Гоночки");
        Game game9 = store.publishGame("Тур де Франс", "Гоночки");
        Game game10 = store.publishGame("Синтезатор", "Музыка");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);
        player.installGame(game6);
        player.installGame(game7);
        player.installGame(game8);
        player.installGame(game9);
        player.installGame(game10);

        player.play(game1, 0);
        player.play(game2, 0);
        player.play(game3, 0);
        player.play(game4, 0);
        player.play(game5, 0);
        player.play(game6, 0);
        player.play(game7, 0);
        player.play(game8, 0);
        player.play(game9, 0);

        // производим проверку
        Game actual = player.mostPlayerByGenre("Головоломка");

        assertEquals(null, actual);

    }

}