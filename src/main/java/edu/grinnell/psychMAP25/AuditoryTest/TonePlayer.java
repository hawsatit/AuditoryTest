package edu.grinnell.psychMAP25.AuditoryTest;

import javax.sound.sampled.*;

public class TonePlayer {

    private static final int DURATION_MS = 200;

    public static void playTone(double hz) throws LineUnavailableException {
        float sampleRate = 44100;
        int bufferLength = (int) (DURATION_MS * sampleRate / 1000);
        byte[] buffer = new byte[bufferLength];

        for (int i = 0; i < buffer.length; i++) {
            double angle = 2.0 * Math.PI * i * hz / sampleRate;
            buffer[i] = (byte) (Math.sin(angle) * 127);
        }

        AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
        try (SourceDataLine line = AudioSystem.getSourceDataLine(format)) {
            line.open(format);
            line.start();
            line.write(buffer, 0, buffer.length);
            line.drain();
        }
    }

    public static void playSymbol(String symbol) {
        int freq = ToneBank.getFrequency(symbol);
        if (freq <= 0) {
            System.err.println("⚠️ Unknown symbol: " + symbol);
            return;
        }
        try {
            playTone(freq);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
