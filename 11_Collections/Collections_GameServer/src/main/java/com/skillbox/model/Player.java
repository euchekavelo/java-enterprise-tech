package com.skillbox.model;

/**
 *  TODO: реализовать возможность сравнивать игроков между собой:
 *  1. По количеству очков (реализовать интерфейс Comparable)
 *  2. Реализовать LeagueComparator, который будет сравнивать игроков по их лиге
 */

import java.util.Objects;

/**
 *  TODO: сделать удобочитаемый вывод данных об игроках реализовав метод toString()
 */
public class Player implements Comparable<Player> {
    private String nickName;
    private int points;
    private League league;
    private Race race;

    public Player() {
    }

    public Player(String nickName, int points) {
        this.nickName = nickName;
        this.points = points;
    }

    public Player(String nickName, int points, League league, Race race) {
        this.nickName = nickName;
        this.points = points;
        this.league = league;
        this.race = race;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickName='" + nickName + '\'' +
                ", points=" + points +
                ", league=" + league +
                ", race=" + race +
                '}';
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(points, o.getPoints());
    }

    /**
     * TODO: реализовать методы hashcode и equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return points == player.points && Objects.equals(nickName, player.nickName)
                && league == player.league && race == player.race;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName, points, league, race);
    }
}
