/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.grinnell.psychMAP25.AuditoryTest;

/**
 *
 * @author fui
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SequenceBankB2 {

    public static List<Sequence> getAllSequences() {
        List<Sequence> sequences = new ArrayList<>();

        // True sequences
        // Grammatical sequences (true)
        sequences.add(new Sequence(List.of("D", "C1", "D"), true));
        sequences.add(new Sequence(List.of("D", "C1", "C2", "D"), true));
        sequences.add(new Sequence(List.of("D", "C2", "C2", "C1", "D"), true));
        sequences.add(new Sequence(List.of("D", "C1", "C2", "C1", "C2", "D"), true));
        sequences.add(new Sequence(List.of("D", "C2", "C1", "C2", "D"), true));
        sequences.add(new Sequence(List.of("D", "C1", "C1", "C2", "D"), true));
        sequences.add(new Sequence(List.of("D", "C2", "C2", "C1", "C1", "C2", "D"), true));
        sequences.add(new Sequence(List.of("D", "C1", "C2", "C2", "C1", "C1", "D"), true));

        sequences.add(new Sequence(List.of("C2", "D"), false));
        sequences.add(new Sequence(List.of("C1", "D", "D"), false));
        sequences.add(new Sequence(List.of("C2", "D", "D", "D"), false));
        sequences.add(new Sequence(List.of("C1", "D", "D", "D", "D"), false));
        sequences.add(new Sequence(List.of("D", "C1"), false));
        sequences.add(new Sequence(List.of("D", "C2", "C1"), false));
        sequences.add(new Sequence(List.of("D", "C2", "C2", "C1"), false));
        sequences.add(new Sequence(List.of("D", "C1", "C2", "C2", "C1"), false));



        Collections.shuffle(sequences); // Randomize order each time
        return sequences;
    }
}

