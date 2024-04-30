package TresEnRaya;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Hacer estos programas en Java y probar en JavaScript
 *
 * ----  Sin Interfaz Grafica : ----
 * - Piedra, Papel o Tijera -
 * - 4 en raya -
 * - Ahorcado -
 * - Busca Minas -
 * - 2048 -
 *
 * ----  Con Interfaz Grafica : ----
 * - Snake -
 * - Ajedrez -
 * - Solitario -
 *
 * ----  Con Interfaz Grafica y Servidor Local (Juego en 2 PCs): ----
 * - Hundir la flota -
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Celda[][] tablero = new Celda[3][3];
    private static Player p1 = new Player(PlayerNumber.PLAYER1);
    private static Player p2 = new Player(PlayerNumber.PLAYER2);


    public static void main(String[] args) {
        play();
    }

    public static void play(){
        creandoTablero();
        int modoJuego = modoDeJuego();
        boolean turno = true;
        if(modoJuego == 1) {
            modoJuego1(turno);
        }else{

            modoJuego2(turno);
        }
    }

    public static int modoDeJuego(){
        int opc;
        do {
            try {
                System.out.println("---- Desea jugar en modo clasico o modo 3 fichas? ----");
                System.out.println("---- 1. Clasico ");
                System.out.println("---- 2. Tres fichas ");
                opc = sc.nextInt();
                if(opc == 1 || opc == 2){break;}
                System.out.println("Escoja un modo de juego valido (1 o 2)");
            }catch (InputMismatchException e){
                System.out.println("Escoja un modo de juego valido (1 o 2)");
                sc.nextLine();
            }
        }while(true);
        return opc;
    }

    public static void modoJuego2(boolean turno){
        p1.tresFichas();
        p2.tresFichas();
        System.out.println("---- MODO 3 FICHAS ----");
        while(true){
            String playerTurn = turno ? "Player 1" : "Player 2";
            System.out.println("Turno del " + playerTurn + " : ");
            int row, col;
            do{
                mostrarTablero();
                try {
                    System.out.println("----  Escoja fila  ----");
                    row = sc.nextInt();
                    sc.nextLine();
                    System.out.println("---- Escoja columna ----");
                    col = sc.nextInt();
                    sc.nextLine();
                    if (!((row < 4 && row > 0) && (col < 4 && col > 0)) || tablero[row - 1][col - 1].isValueAssigned()) {
                        System.out.println("Esa casilla ya esta ocupada o los valores no son correctos, porfavor escoja de nuevo");
                    }
                }catch(InputMismatchException e) {
                    System.out.println("Introduzca un numero valido del 1 al 3");
                    row = 0;
                    col = 0;
                    sc.nextLine();
                }
            } while (!((row < 4 && row > 0) && (col < 4 && col > 0)) || tablero[row - 1][col - 1].isValueAssigned());

            tablero[row - 1][col - 1].setEstado(turno ? Estado.X : Estado.O);
            if(turno){
                p1.addFicha(tablero[row - 1][col - 1]);
            }else{
                p2.addFicha(tablero[row - 1][col - 1]);
            }

            if (ganador() != null || tableroLleno()) {
                break;
            }
            turno = !turno;
        }

        mostrarTablero();
        if (p1.isWinner()) {
            System.out.println("Felicidades Jugador 1 por su victoria!!!!");
        } else if (p2.isWinner()) {
            System.out.println("Felicidades Jugador 2 por su victoria!!!!");
        }
    }

    public static void modoJuego1(boolean turno){
        System.out.println("---- MODO CLASICO ----");
        while (true) {
            String playerTurn = turno ? "Player 1" : "Player 2";
            System.out.println("Turno del " + playerTurn + " : ");
            int row, col;
            do {
                mostrarTablero();
                try {
                    System.out.println("----  Escoja fila  ----");
                    row = sc.nextInt();
                    sc.nextLine();
                    System.out.println("---- Escoja columna ----");
                    col = sc.nextInt();
                    sc.nextLine();
                    if (!((row < 4 && row > 0) && (col < 4 && col > 0)) || tablero[row - 1][col - 1].isValueAssigned()) {
                        System.out.println("Esa casilla ya esta ocupada o los valores no son correctos, porfavor escoja de nuevo");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Introduzca un numero valido del 1 al 3");
                    row = 0;
                    col = 0;
                    sc.nextLine();
                }
            } while (!((row < 4 && row > 0) && (col < 4 && col > 0)) || tablero[row - 1][col - 1].isValueAssigned());

            tablero[row - 1][col - 1].setEstado(turno ? Estado.X : Estado.O);


            if (ganador() != null || tableroLleno()) {
                break;
            }
            turno = !turno;
        }

        mostrarTablero();
        if (p1.isWinner()) {
            System.out.println("Felicidades Jugador 1 por su victoria!!!!");
        } else if (p2.isWinner()) {
            System.out.println("Felicidades Jugador 2 por su victoria!!!!");
        } else {
            System.out.println("Esta partida ha acabado en empate!!!!");
        }
    }

    public static boolean tableroLleno(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if(!tablero[i][j].isValueAssigned()) return false;
            }
        }
        return true;
    }

    public static void creandoTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = new Celda();
            }
        }
    }

    public static void mostrarTablero(){
        System.out.println("      1   2   3   ");
        System.out.println("   + --- --- --- +");
        System.out.println(" 1 |  " + tablero[0][0].getEstado().getSTATE_VALUE() + "   " + tablero[0][1].getEstado().getSTATE_VALUE() + "   " + tablero[0][2].getEstado().getSTATE_VALUE() + "  |");
        System.out.println(" 2 |  " + tablero[1][0].getEstado().getSTATE_VALUE() + "   " + tablero[1][1].getEstado().getSTATE_VALUE() + "   " + tablero[1][2].getEstado().getSTATE_VALUE() + "  |");
        System.out.println(" 3 |  " + tablero[2][0].getEstado().getSTATE_VALUE() + "   " + tablero[2][1].getEstado().getSTATE_VALUE() + "   " + tablero[2][2].getEstado().getSTATE_VALUE() + "  |");
        System.out.println("   + --- --- --- +");
    }

    public static Player ganador(){
        if(     tablero[0][0].getEstado().equals(Estado.O) && tablero[0][1].getEstado().equals(Estado.O) && tablero[0][2].getEstado().equals(Estado.O)
                ||  tablero[1][0].getEstado().equals(Estado.O) && tablero[1][1].getEstado().equals(Estado.O) && tablero[1][2].getEstado().equals(Estado.O)
                ||  tablero[2][0].getEstado().equals(Estado.O) && tablero[2][1].getEstado().equals(Estado.O) && tablero[2][2].getEstado().equals(Estado.O)
                ||  tablero[0][0].getEstado().equals(Estado.O) && tablero[1][1].getEstado().equals(Estado.O) && tablero[2][2].getEstado().equals(Estado.O)
                ||  tablero[0][2].getEstado().equals(Estado.O) && tablero[1][1].getEstado().equals(Estado.O) && tablero[2][0].getEstado().equals(Estado.O)
                ||  tablero[0][0].getEstado().equals(Estado.O) && tablero[1][0].getEstado().equals(Estado.O) && tablero[2][0].getEstado().equals(Estado.O)
                ||  tablero[0][1].getEstado().equals(Estado.O) && tablero[1][1].getEstado().equals(Estado.O) && tablero[2][1].getEstado().equals(Estado.O)
                ||  tablero[0][2].getEstado().equals(Estado.O) && tablero[1][2].getEstado().equals(Estado.O) && tablero[2][2].getEstado().equals(Estado.O)){

            p2.winner();
            return p2;
        }else if(tablero[0][0].getEstado().equals(Estado.X) && tablero[0][1].getEstado().equals(Estado.X) && tablero[0][2].getEstado().equals(Estado.X)
                ||  tablero[1][0].getEstado().equals(Estado.X) && tablero[1][1].getEstado().equals(Estado.X) && tablero[1][2].getEstado().equals(Estado.X)
                ||  tablero[2][0].getEstado().equals(Estado.X) && tablero[2][1].getEstado().equals(Estado.X) && tablero[2][2].getEstado().equals(Estado.X)
                ||  tablero[0][0].getEstado().equals(Estado.X) && tablero[1][1].getEstado().equals(Estado.X) && tablero[2][2].getEstado().equals(Estado.X)
                ||  tablero[0][2].getEstado().equals(Estado.X) && tablero[1][1].getEstado().equals(Estado.X) && tablero[2][0].getEstado().equals(Estado.X)
                ||  tablero[0][0].getEstado().equals(Estado.X) && tablero[1][0].getEstado().equals(Estado.X) && tablero[2][0].getEstado().equals(Estado.X)
                ||  tablero[0][1].getEstado().equals(Estado.X) && tablero[1][1].getEstado().equals(Estado.X) && tablero[2][1].getEstado().equals(Estado.X)
                ||  tablero[0][2].getEstado().equals(Estado.X) && tablero[1][2].getEstado().equals(Estado.X) && tablero[2][2].getEstado().equals(Estado.X)){
            p1.winner();
            return p1;

        }
        return null;
    }

}
