package com.skillbox.service;

import com.skillbox.model.League;
import com.skillbox.model.Player;
import com.skillbox.model.Race;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class LeagueManagerTest {
    private LeagueManager leagueManager;

    @Before
    public void setUp() throws Exception {
        Class<?> stackInterface = LeagueManager.class;
        Reflections reflections = new Reflections("com.skillbox.service", new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toSet());
        for (Class<?> clazz : classes) {
            HashSet<Class<?>> interfaces = new HashSet<>(Arrays.asList(clazz.getInterfaces()));
            for (Class<?> i : interfaces) {
                if (i.equals(stackInterface)) {
                    Object o = clazz.newInstance();
                    leagueManager = (LeagueManager) o;
                }
            }
        }
    }

    @Test
    public void testAddPlayer() {
        leagueManager.addPlayer(generatePlayer());
    }

    @Test
    public void testRemovePlayer() {
        leagueManager.addPlayer(generatePlayer());
        leagueManager.removePlayer(generatePlayer());
    }

    @Test
    public void testGetPlayer() {
        Player player = generatePlayer();
        leagueManager.addPlayer(player);
        Player actual = leagueManager.getPlayer(player.getNickName());
        Assert.assertEquals(player, actual);
    }

    @Test
    public void testGetAllPlayers() {
        Player player = new Player("Kirill", 1);
        Player player2 = new Player("Vlad", 2);
        Player player3 = new Player("KirillS", 3);
        Player[] testPlayers = new Player[3];
        testPlayers[0] = player;
        testPlayers[1] = player2;
        testPlayers[2] = player3;
        List<Player> testPlayersList = Arrays.asList(testPlayers);

        leagueManager.addPlayer(player);
        leagueManager.addPlayer(player2);
        leagueManager.addPlayer(player3);
        List<Player> playersList = Arrays.asList(leagueManager.getAllPlayers());
        assertTrue(testPlayersList.containsAll(playersList));
    }

    @Test
    public void testGetPlayersByRace() {
        Player playerElf = new Player("Kirill", 1, League.BRONZE, Race.ELF);
        Player playerHuman = new Player("testName", 1, League.GOLD, Race.HUMAN);

        leagueManager.addPlayer(playerElf);
        leagueManager.addPlayer(playerHuman);

        List<Player> testPlayerElfList = List.of(playerElf);
        List<Player> playerElfList = Arrays.asList(leagueManager.getPlayers(Race.ELF));
        assertEquals(2, leagueManager.getAllPlayers().length);
        assertTrue(playerElfList.size() == 1 && testPlayerElfList.containsAll(playerElfList));
    }

    @Test
    public void testGetPlayersByLeague() {
        Player playerTest1 = new Player("Kirill", 1, League.BRONZE, Race.ELF);
        Player playerTest2 = new Player("testName", 1, League.GOLD, Race.ELF);
        Player playerTest3 = new Player("Evgen", 1, League.GOLD, Race.HUMAN);

        leagueManager.addPlayer(playerTest1);
        leagueManager.addPlayer(playerTest2);
        leagueManager.addPlayer(playerTest3);

        List<Player> testPlayerElfList = List.of(playerTest2, playerTest3);
        List<Player> playerElfList = Arrays.asList(leagueManager.getPlayers(League.GOLD));
        assertEquals(3, leagueManager.getAllPlayers().length);
        assertTrue(playerElfList.size() == 2 && testPlayerElfList.containsAll(playerElfList));
    }

    @Test
    public void testAddPoints() {
        Player player = generatePlayer();
        int expected = player.getPoints();
        leagueManager.addPlayer(player);
        int points = 10;
        expected += points;
        leagueManager.addPoints(player.getNickName(), points);
        Player actual = leagueManager.getPlayer(player.getNickName());
        Assert.assertEquals(expected, actual.getPoints());
    }

    Player generatePlayer() {
        return new Player("name", 1, League.BRONZE, Race.ELF);
    }
}
