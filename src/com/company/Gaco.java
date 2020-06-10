package com.company;

public class Gaco {
    private int posisiX;
    private int poisisY;
    private Player player;

    public Gaco(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public char getCharValue() {
        return player.name().trim().charAt(0);
    }

    public enum Player{
        SATU,DUA
    };

    public int getPosisiX() {
        return posisiX;
    }

    public void setPosisiX(int posisiX) {
        this.posisiX = posisiX;
    }

    public int getPoisisY() {
        return poisisY;
    }

    public void setPoisisY(int poisisY) {
        this.poisisY = poisisY;
    }
}
