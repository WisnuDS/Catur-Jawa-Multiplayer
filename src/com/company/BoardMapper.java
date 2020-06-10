package com.company;

public class BoardMapper {
    public BoardMapper(){

    }

    public static int map(int val){
        switch(val){
            case 1: return 2;
            case 2: return 1;
            case 3: return 0;
        }
        return -1;
    }

    public static int map(char val){
        switch(Character.toLowerCase(val)){
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
        }
        return -1;
    }
}
