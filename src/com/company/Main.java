package com.company;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Handler;

public class Main {
    private static ArrayList<Room> rooms;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3002);
        rooms = new ArrayList<>();
         while (true){
             Socket client = serverSocket.accept();
             if (rooms.isEmpty()){
                 Player newPlayer = new Player(client,Gaco.Player.SATU);
                 Room newRoom = new Room(newPlayer);
                 rooms.add(newRoom);
                 newPlayer.sendMessage("waiting");
             }else {
                 System.out.println("cek2");
                 Room lastRoom = rooms.get(rooms.size()-1);
                 if (lastRoom.isFull()){
                     Player newPlayer = new Player(client,Gaco.Player.SATU);
                     Room newRoom = new Room(newPlayer);
                     rooms.add(newRoom);
                     newPlayer.sendMessage("waiting");
                 }else {
                     lastRoom.setPlayer2(new Player(client, Gaco.Player.DUA));
                     lastRoom.start();
                 }
             }
         }
    }
}
