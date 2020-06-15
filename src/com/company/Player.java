package com.company;

import com.google.gson.JsonObject;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class Player{
    private String nickName;
    private Socket socket;
    private PrintWriter stringOut;
    private BufferedReader in;
    private ObjectOutputStream objectOut;
    private Gaco.Player status;

    public ObjectOutputStream getObjectOut() {
        return objectOut;
    }

    public void setObjectOut(ObjectOutputStream objectOut) {
        this.objectOut = objectOut;
    }

    public Gaco.Player getStatus() {
        return status;
    }

    public void setStatus(Gaco.Player status) {
        this.status = status;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PrintWriter getStringOut() {
        return stringOut;
    }

    public void setStringOut(PrintWriter stringOut) {
        this.stringOut = stringOut;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public Player(Socket socket, Gaco.Player status) {
        this.socket = socket;
        this.status =status;

        try {
            this.stringOut = new PrintWriter(socket.getOutputStream(),true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.objectOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stringOut.print("Send Nickname\n");
        this.stringOut.flush();
        try {
            this.nickName = this.in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        stringOut.print(message+"\n");
        stringOut.flush();
    }

    public void sendObject(JsonObject object){
        try {
            objectOut.writeObject(object);
            objectOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String listen(){
        try {
            return in.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(){
        try {
            objectOut.close();
            stringOut.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
