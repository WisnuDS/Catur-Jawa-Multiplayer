package com.company;

public class GameLogic {
    private Papan papan;
    private boolean isFinished;
    private Gaco.Player winner;
    private Gaco.Player currentPlayer;

    public Gaco.Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Gaco.Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Gaco.Player getWinner() {
        return winner;
    }

    public void setWinner(Gaco.Player winner) {
        this.winner = winner;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public GameLogic() {
        papan = new Papan();
        this.isFinished = false;
        this.winner = null;
    }

    public Papan getPapan() {
        return papan;
    }

    public void setPapan(Papan papan) {
        this.papan = papan;
    }

    public boolean putGaco(int posisiX, int posisiY, Gaco gaco) {
        if (papan.getUbins()[posisiX][posisiY].isEmpty()) {
            papan.getUbins()[posisiX][posisiY].setGaco(gaco);
            return true;
        } else {
            return false;
        }
    }

    public boolean cekKemenangan() {
        if (papan.getUbins()[0][0].getGaco()!=null && papan.getUbins()[1][1].getGaco()!=null && papan.getUbins()[2][2].getGaco() != null){
            if (papan.getUbins()[0][0].getGaco().getPlayer() == Gaco.Player.SATU
                    && papan.getUbins()[1][1].getGaco().getPlayer() == Gaco.Player.SATU
                    && papan.getUbins()[2][2].getGaco().getPlayer() == Gaco.Player.SATU) {
                setFinished(true);
                setWinner(Gaco.Player.SATU);
                return true;
            }else if (papan.getUbins()[0][0].getGaco().getPlayer() == Gaco.Player.DUA
                    && papan.getUbins()[1][1].getGaco().getPlayer() == Gaco.Player.DUA
                    && papan.getUbins()[2][2].getGaco().getPlayer() == Gaco.Player.DUA) {
                setFinished(true);
                setWinner(Gaco.Player.DUA);
                return true;
            }
        }else if (papan.getUbins()[0][2].getGaco()!=null && papan.getUbins()[1][1].getGaco()!=null && papan.getUbins()[2][0].getGaco() != null){
            if ((papan.getUbins()[0][2].getGaco().getPlayer() == Gaco.Player.SATU)
                    && (papan.getUbins()[1][1].getGaco().getPlayer() == Gaco.Player.SATU)
                    && (papan.getUbins()[2][0].getGaco().getPlayer() == Gaco.Player.SATU)) {
                setFinished(true);
                setWinner(Gaco.Player.SATU);
                return true;
            }else if (papan.getUbins()[0][2].getGaco().getPlayer() == Gaco.Player.DUA
                    && papan.getUbins()[1][1].getGaco().getPlayer() == Gaco.Player.DUA
                    && papan.getUbins()[2][0].getGaco().getPlayer() == Gaco.Player.DUA) {
                setFinished(true);
                setWinner(Gaco.Player.DUA);
                return true;
            }
        } else {
            for (int i = 0; i < 3; i++) {
                if (papan.getUbins()[i][0].getGaco() != null && papan.getUbins()[i][1].getGaco() != null && papan.getUbins()[i][2].getGaco() != null){
                    if (papan.getUbins()[i][0].getGaco().getPlayer() == Gaco.Player.SATU
                            && papan.getUbins()[i][1].getGaco().getPlayer() == Gaco.Player.SATU
                            && papan.getUbins()[i][2].getGaco().getPlayer() == Gaco.Player.SATU) {
                        setFinished(true);
                        setWinner(Gaco.Player.SATU);
                        return true;
                    }else if (papan.getUbins()[i][0].getGaco().getPlayer() == Gaco.Player.DUA
                            && papan.getUbins()[i][1].getGaco().getPlayer() == Gaco.Player.DUA
                            && papan.getUbins()[i][2].getGaco().getPlayer() == Gaco.Player.DUA) {
                        setFinished(true);
                        setWinner(Gaco.Player.DUA);
                        return true;
                    }
                }if (papan.getUbins()[0][i].getGaco() != null && papan.getUbins()[1][i].getGaco() != null && papan.getUbins()[2][i].getGaco() != null){
                    if (papan.getUbins()[0][i].getGaco().getPlayer() == Gaco.Player.SATU
                            && papan.getUbins()[1][i].getGaco().getPlayer() == Gaco.Player.SATU
                            && papan.getUbins()[2][i].getGaco().getPlayer() == Gaco.Player.SATU) {
                        setFinished(true);
                        setWinner(Gaco.Player.SATU);
                        return true;
                    }else if (papan.getUbins()[0][i].getGaco().getPlayer() == Gaco.Player.DUA
                            && papan.getUbins()[1][i].getGaco().getPlayer() == Gaco.Player.DUA
                            && papan.getUbins()[2][i].getGaco().getPlayer() == Gaco.Player.DUA) {
                        setFinished(true);
                        setWinner(Gaco.Player.DUA);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean moveGaco(Tuple from, Tuple to){
        if(!isValidMoved(from,to)){
            return false;
        }else {
            papan.getUbins()[to.X()][to.Y()].setGaco(papan.getUbins()[from.X()][from.Y()].getGaco());
            papan.getUbins()[from.X()][from.Y()].empty();
            return true;
        }
    }

    public boolean isValidMoved(Tuple from, Tuple to){
        if(papan.getUbins()[from.X()][from.Y()].isEmpty()){
            System.out.println("cek1");
            return false;
        }
        if (papan.getUbins()[from.X()][from.Y()].getGaco().getPlayer() != currentPlayer){
            System.out.println("cek2");
            return false;
        }
        if (!papan.getUbins()[to.X()][to.Y()].isEmpty()){
            System.out.println("cek3");
            return false;
        }
        if(!((Math.abs(from.Y()-to.Y())==1 && from.X()==to.X())
        || (Math.abs(from.X()-to.X())==1 && from.Y()==to.Y())
        || (Math.abs(from.Y()-to.Y())==1 && Math.abs(from.X()-to.X())==1))){
            System.out.println("cek4");
            return false;
        }
        return true;
    }

    public void switcPlayer(){
        if (currentPlayer == Gaco.Player.SATU){
            setCurrentPlayer(Gaco.Player.DUA);
        }else {
            setCurrentPlayer(Gaco.Player.SATU);
        }
    }
}
