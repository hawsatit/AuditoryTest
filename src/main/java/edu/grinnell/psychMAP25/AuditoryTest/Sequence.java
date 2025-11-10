package edu.grinnell.psychMAP25.AuditoryTest;

import java.util.List;

public class Sequence {
    private final List<String> symbols;

    public Sequence(List<String> symbols) {
        this.symbols = symbols;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void play() {
        for (String symbol : symbols) {
            TonePlayer.playSymbol(symbol);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public String toString() {
        return String.join("-", symbols);
    }
}
