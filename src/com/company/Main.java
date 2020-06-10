package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(4000);
        Socket request = server.accept();
        PenangananInput handler = new PenangananInput();
        Scanner scanner = new Scanner(System.in);


        Gaco[] gacos = new Gaco[6];
        GameLogic game = new GameLogic();
        game.setCurrentPlayer(Gaco.Player.SATU);
        TampilanPapan.clearConsole();
        TampilanPapan.printBoard(game.getPapan());
        int i = 0;
        while (i < 6 && !game.isFinised()){
            System.out.println("Put Gaco (eg. A2): ");
            String input = scanner.nextLine();
            gacos[i] = new Gaco(game.getCurrentPlayer());
            if (!handler.isValidPut(input)) {
                System.out.println("Invalid input!");
                System.out.println("Valid input is in form: A2-A3");
            } else {
                Tuple from = handler.parse(input);
                System.out.println("Y: "+from.Y());
                System.out.println("X: "+from.X());
                boolean movePlayed = game.putGaco(from.X(), from.Y(), gacos[i]);
                if (!movePlayed)
                    System.out.println("Illegal move!");
                else {
                    TampilanPapan.clearConsole();
                    TampilanPapan.printBoard(game.getPapan());
                    i++;
                    if (!game.cekKemenangan()){
                        game.switcPlayer();
                    }
                }
            }
            if (game.isFinised()) {
                scanner.close();
                System.out.println("Game has finished. The Winner is Player "+ game.getWinner());
                break;
            }
        }

        while (!game.isFinised()) {
            System.out.println("Enter move (eg. A2-A3): ");
            String input = scanner.nextLine();

            if (!handler.isValid(input)) {
                System.out.println("Invalid input!");
                System.out.println("Valid input is in form: A2-A3");
            } else {
                Tuple from = handler.getFrom(input);
                Tuple to = handler.getTo(input);

                boolean movePlayed = game.moveGaco(from, to);
                if (!movePlayed)
                    System.out.println("Illegal move!");
                else {
                    TampilanPapan.clearConsole();
                    TampilanPapan.printBoard(game.getPapan());
                    if (!game.cekKemenangan()){
                        game.switcPlayer();
                    }
                }
            }
        }
        scanner.close();
        System.out.println("Game has finished. The winner is "+game.getCurrentPlayer());
    }
}
