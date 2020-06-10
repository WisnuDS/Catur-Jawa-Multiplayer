package com.company;

public class Ubin {
    private Gaco gaco;

    public Gaco getGaco() {
        return gaco;
    }

    public void setGaco(Gaco gaco) {
        this.gaco = gaco;
    }

    public String getValue(){
        if(gaco != null){
            return "[" + gaco.getCharValue() + "]";
        } else {
            return "[ ]";
        }
    }

    public boolean isEmpty(){
        return gaco == null;
    }

    public void empty(){
        gaco = null;
    }

}
