/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package edu.grinnell.psychMAP25.AuditoryTest;

import java.util.List;

public class AuditoryTest {
    public static void main(String[] args) {
        List<Sequence> sequences = SequenceBank.getAllSequences();

        for (Sequence seq : sequences) {
            System.out.println("▶ Playing sequence: " + seq);
            seq.play();
            try {
                Thread.sleep(5000); // optional pause between sequences
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("✅ Done playing all sequences.");
    }
}


