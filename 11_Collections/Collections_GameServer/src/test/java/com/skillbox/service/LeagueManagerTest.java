package com.skillbox.service;

import com.skillbox.model.League;
import com.skillbox.model.Player;
import com.skillbox.model.Race;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    }

    @Test
    public void testGetPlayersByRace() {
    }

    @Test
    public void testGetPlayersByLeague() {
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
