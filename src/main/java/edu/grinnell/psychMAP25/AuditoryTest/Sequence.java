package edu.grinnell.psychMAP25.AuditoryTest;

import java.util.List;

public class Sequence {
    private final List<String> symbols;
    private final boolean isGrammatical;

    public Sequence(List<String> toneLabels, boolean isGrammatical) {
        this.symbols = toneLabels;
        this.isGrammatical = isGrammatical;
    }

    public List<String> getToneLabels() {
        return symbols;
    }

    public boolean isGrammatical() {
        return isGrammatical;
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
