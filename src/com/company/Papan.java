package com.company;

public class Papan {
    private final Ubin[][] ubins;

    public Ubin[][] getUbins() {
        return ubins;
    }

    public Papan() {
        ubins = new Ubin[3][3];
        initializeBoard();
    }

    private void initializeBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                ubins[i][j] = new Ubin();
                ubins[i][j].setGaco(null);
            }
        }
    }





}
