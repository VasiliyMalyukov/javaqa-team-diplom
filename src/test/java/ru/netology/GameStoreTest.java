package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    GameStore store = new GameStore();

    @BeforeEach
    public void setup() {
        store.addPlayTime("Viktor", 5);
        store.addPlayTime("Olga", 20);
        store.addPlayTime("Nikolay", 19);
        store.addPlayTime("Petr", 15);
    }

    @Test
    public void shouldAddGame() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldBeEmpty() {
        assertFalse(store.containsGame(null));
    }

    @Test
    public void shouldContainGame() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddSeveralGames() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("GTA5", "Экшен");

        assertTrue(store.containsGame(game));
        assertTrue(store.containsGame(game1));
    }

    @Test
    public void shouldGetMostPlayerIfAllPlayersAreDifferent() {

        assertEquals("Olga", store.getMostPlayer());
    }

    @Test
    public void shouldGetMostPlayerIfPlayerIsNotTheLast() {

        store.addPlayTime("Olga", 7);
        store.addPlayTime("Nikolay", 19);
        store.addPlayTime("Petr", 15);

        assertEquals("Nikolay", store.getMostPlayer());
    }

    @Test
    public void shouldNotGetMostPlayer() {
        assertEquals(null, store.getMostPlayer());
    }

    @Test
    public void shouldGetSumPlayedTime() {

        assertEquals(36, store.getSumPlayedTime());
    }

    @Test
    public void shouldGetSumOfOnyOneTime() {
        store.addPlayTime("Olga", 7);

        assertEquals(7, store.getSumPlayedTime());
    }

    @Test
    public void shouldNotGetSum() {
        assertEquals(0, store.getSumPlayedTime());
    }
}
