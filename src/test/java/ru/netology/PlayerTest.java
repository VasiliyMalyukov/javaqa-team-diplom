package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    GameStore store = new GameStore();
    Player player = new Player("Petya");

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
}
