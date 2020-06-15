package com.company;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

public class Room extends Thread{
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    PenangananInput handler = new PenangananInput();
    Gaco[] gacos = new Gaco[6];
    GameLogic game = new GameLogic();

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Room(Player player1) {
        this.player1 = player1;
    }

    public void playGame(){
        Gson gson = new Gson();
        setCurrentPlayer(player1);
        game.setCurrentPlayer(currentPlayer.getStatus());
        sendPapan();
        int i = 0;
        while (i < 6 && !game.isFinished()){
            HashMap<String,String> dataPlayer1 = new HashMap<>();
            HashMap<String,String> dataPlayer2 = new HashMap<>();
            dataPlayer1.put("status","play");
            dataPlayer1.put("message","Put Gaco (eg. A2): ");
            dataPlayer2.put("status","wait");
            dataPlayer2.put("message","This is not your turn");

            sendDifferentMessage(gson.toJson(dataPlayer1),gson.toJson(dataPlayer2));
            String input = currentPlayer.listen();
            System.out.println("input: "+input);
            gacos[i] = new Gaco(game.getCurrentPlayer());
            if (!handler.isValidPut(input)) {
                HashMap<String,String> dataPlayer3 = new HashMap<>();
                dataPlayer3.put("status","message");
                dataPlayer3.put("message","Invalid input!Valid input is in form: A2");
                currentPlayer.sendMessage(gson.toJson(dataPlayer3));
            } else {
                Tuple from = handler.parse(input);
                System.out.println("Y: "+from.Y());
                System.out.println("X: "+from.X());
                boolean movePlayed = game.putGaco(from.X(), from.Y(), gacos[i]);
                if (!movePlayed){
                    HashMap<String,String> dataPlayer4 = new HashMap<>();
                    dataPlayer4.put("status","message");
                    dataPlayer4.put("message","Illegal move!");
                    currentPlayer.sendMessage(gson.toJson(dataPlayer4));
                }
                else {
                    sendPapan();
                    i++;
                    if (!game.cekKemenangan()){
                        game.switcPlayer();
                        switchPlayer();
                    }
                }
            }
            if (game.isFinished()) {
                HashMap<String,String> dataPlayer5 = new HashMap<>();
                dataPlayer5.put("status","message");
                dataPlayer5.put("message","Game has finished. The Winner is Player "+ game.getWinner());
                sendAllPlayerMessage(gson.toJson(dataPlayer5));
                finish();
                break;
            }
        }

        while (!game.isFinished()) {
            HashMap<String,String> dataPlayer6 = new HashMap<>();
            HashMap<String,String> dataPlayer7 = new HashMap<>();
            dataPlayer6.put("status","play");
            dataPlayer6.put("message","Enter move (eg. A2-A3): ");
            dataPlayer7.put("status","wait");
            dataPlayer7.put("message","This is not your turn");
            sendDifferentMessage(gson.toJson(dataPlayer6),gson.toJson(dataPlayer7));
            String input = currentPlayer.listen();

            if (!handler.isValid(input)) {
                HashMap<String,String> dataPlayer8 = new HashMap<>();
                dataPlayer8.put("status","message");
                dataPlayer8.put("message","Invalid input!Valid input is in form: A2-A3");
                currentPlayer.sendMessage(gson.toJson(dataPlayer8));
            } else {
                Tuple from = handler.getFrom(input);
                Tuple to = handler.getTo(input);

                boolean movePlayed = game.moveGaco(from, to);
                if (!movePlayed) {
                    HashMap<String,String> dataPlayer9 = new HashMap<>();
                    dataPlayer9.put("status","message");
                    dataPlayer9.put("message","Illegal move!");
                    currentPlayer.sendMessage(gson.toJson(dataPlayer9));
                }
                else {
                    sendPapan();
                    if (!game.cekKemenangan()){
                        game.switcPlayer();
                        switchPlayer();
                    }
                }
            }
        }
        HashMap<String,String> dataPlayer10 = new HashMap<>();
        dataPlayer10.put("status","message");
        dataPlayer10.put("message","Game has finished. The winner is "+game.getCurrentPlayer());
        sendAllPlayerMessage(gson.toJson(dataPlayer10));
        finish();
    }

    public boolean isFull(){
        return player1 != null && player2 != null;
    }

    public void sendPapan(){
        Gson gson = new Gson();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("status","papan");
        hashMap.put("papan",gson.toJson(game.getPapan().getUbins()));
        getPlayer1().sendMessage(gson.toJson(hashMap));
        getPlayer2().sendMessage(gson.toJson(hashMap));
    }

    public void sendAllPlayerMessage(String message){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("status","message");
        hashMap.put("message",message);
        Gson gson = new Gson();
        getPlayer1().sendMessage(gson.toJson(hashMap));
        getPlayer2().sendMessage(gson.toJson(hashMap));
    }

    public void sendDifferentMessage(String messageForCurrentPlayer, String message){
        getCurrentPlayer().sendMessage(messageForCurrentPlayer);
        if (getCurrentPlayer() == player1){
            player2.sendMessage(message);
        }else {
            player1.sendMessage(message);
        }
    }

    public void switchPlayer(){
        if (getCurrentPlayer() == player1){
            setCurrentPlayer(player2);
        }else {
            setCurrentPlayer(player1);
        }
    }

    public void finish(){
        player1.closeConnection();
        player2.closeConnection();
    }

    @Override
    public void run() {
        playGame();
    }
}
