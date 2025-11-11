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

public class SequenceBankA2 {

    public static List<Sequence> getAllSequences() {
        List<Sequence> sequences = new ArrayList<>();

        // TRUE sequences 
        sequences.add(new Sequence(List.of("C1", "D", "C2"), true));
        sequences.add(new Sequence(List.of("C2", "D", "D", "C2"), true));
        sequences.add(new Sequence(List.of("C1", "D", "D", "D", "D", "C1"), true));
        sequences.add(new Sequence(List.of("C2", "D", "D", "D", "D", "C1"), true));
        sequences.add(new Sequence(List.of("C2", "D", "D", "D", "D", "C1"), true));
        sequences.add(new Sequence(List.of("C1", "D", "D", "D", "D", "C2"), true));
        sequences.add(new Sequence(List.of("C2", "D", "D", "D", "D", "D", "C1"), true));
        sequences.add(new Sequence(List.of("C1", "D", "D", "D", "D", "D", "C1"), true));

        // FALSE sequences 
        sequences.add(new Sequence(List.of("D", "C1"), false));
        sequences.add(new Sequence(List.of("D", "D", "C2"), false));
        sequences.add(new Sequence(List.of("D", "D", "D", "C2"), false));
        sequences.add(new Sequence(List.of("D", "D", "D", "D", "C1"), false));
        sequences.add(new Sequence(List.of("C2", "D"), false));
        sequences.add(new Sequence(List.of("C1", "D", "D"), false));
        sequences.add(new Sequence(List.of("C2", "D", "D", "D"), false));
        sequences.add(new Sequence(List.of("C1", "D", "D", "D", "D", "D"), false));



        Collections.shuffle(sequences); // Randomize order each time
        return sequences;
    }
}

