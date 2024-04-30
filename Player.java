package TresEnRaya;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private PlayerNumber playerNumber;
    private int numFichas;
    private boolean winner;

    private List<Celda> fichas;

    public Player(PlayerNumber playerNumber) {
        this.playerNumber = playerNumber;
        winner = false;
        fichas = new LinkedList<>();
    }

    public void addFicha(Celda celda){
        fichas.add(celda);
        if(fichas.size() >= 4){
            removeFicha();
        }
    }

    public void removeFicha(){
        fichas.get(0).setEstado(Estado.Undefined);
        fichas.remove(0);
    }

    public int getNumFichas() {
        return numFichas;
    }

    public void tresFichas(){
        numFichas = 3;
    }

    public void winner(){
        winner = true;
    }

    public boolean isWinner() {
        return winner;
    }
}
