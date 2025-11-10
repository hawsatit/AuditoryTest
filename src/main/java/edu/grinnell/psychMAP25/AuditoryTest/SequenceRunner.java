/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.psychMAP25.AuditoryTest;

import java.util.List;


public class SequenceRunner {
    public static void playSequence(List<Tone> sequence) {
        for (Tone tone : sequence) {
            try {
                TonePlayer.playTone(tone.frequency);
                Thread.sleep(200); // short pause between tones
            } catch (Exception e) {
                System.err.println("Error playing tone: " + e.getMessage());
            }
        }
    }
}

