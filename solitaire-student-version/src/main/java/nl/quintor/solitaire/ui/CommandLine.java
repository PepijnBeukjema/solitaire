package nl.quintor.solitaire.ui;

import nl.quintor.solitaire.game.moves.Move;
import nl.quintor.solitaire.models.card.Card;
import nl.quintor.solitaire.models.deck.Deck;
import nl.quintor.solitaire.models.state.GameState;

import java.io.IOException;
import java.util.*;

public class CommandLine implements UI {

    private Scanner sc;
    private String message;

    public CommandLine() {
        sc = new Scanner(System.in);
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setErrorMessage(String message) {
        System.out.println("Error: " + message);
        this.message = message;
    }

    @Override
    public void refresh(GameState gameState) {
        // clear screen
        clrscr();

        gameState.getStock().get(gameState.getStock().size() -1);
        printStock(gameState);
        printColumns(gameState);

    }

    private void printColumns(GameState gameState) {
        // calculate maximum column size
        int maxColumnSize = 0;
        for (Deck column : gameState.getColumns().values()) {
            maxColumnSize = Math.max(maxColumnSize, column.size());
        }

        // print columns
        for (int i = 0; i < maxColumnSize; i++) {

            for (Deck column : gameState.getColumns().values()) {
                if (column.size() > i) {
                    if (i < column.size() - 1) {
                        System.out.print("| " + "? ?" + " | ");
                    } else {
                        System.out.print("| " + column.get(i).toShortString() + " | ");
                    }
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    private void printStock(GameState gameState) {

        //print top left stockpiles
        for (int i = 0; i <= gameState.getStock().size(); i++) {
            for (Deck : gameState.getStock().values());
            {
                if (gameState.getStock().size() > i) {
                    if (i < gameState.getStock().size() - 1) {
                        System.out.print("| " + "? ?" + " | ");
                    } else {
                        System.out.println("| " + gameState.getStock().get(i).toShortString() + " | ");
                    }
                } else {
                    System.out.print("\t");
                }
                System.out.println();
            }
        }
    }

    @Override
    public String refreshAndRequestMove(GameState gameState, Collection<Move> moves) {
        refresh(gameState);

        String playerInput = sc.nextLine();

        return playerInput;
    }

    private static void clrscr(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033\143");
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException("Screen clearing error");
        }
    }
}
