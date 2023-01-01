package com.skillbox;

import com.skillbox.model.Player;

import java.util.Comparator;

public class LeagueComparator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        return o1.getLeague().compareTo(o2.getLeague());
    }
}
