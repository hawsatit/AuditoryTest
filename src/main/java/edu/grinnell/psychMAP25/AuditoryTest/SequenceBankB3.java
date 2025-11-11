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

public class SequenceBankB3 {

    public static List<Sequence> getAllSequences() {
        List<Sequence> sequences = new ArrayList<>();

        sequences.add(new Sequence(List.of("A2", "B1", "A1"), true));
        sequences.add(new Sequence(List.of("A1", "B2", "B2", "A1"), true));
        sequences.add(new Sequence(List.of("A2", "B1", "B2", "B2", "B1", "A1"), true));
        sequences.add(new Sequence(List.of("A1", "B2", "B1", "B2", "B2", "A2"), true));

        sequences.add(new Sequence(List.of("A1", "B2", "B1", "B2", "A2"), true));
        sequences.add(new Sequence(List.of("A2", "B2", "B2", "B1", "A1"), true));
        sequences.add(new Sequence(List.of("A1", "B1", "B2", "B1", "B2", "B2", "A2"), true));
        sequences.add(new Sequence(List.of("A2", "B1", "B1", "B1", "B2", "B2", "A1"), true));

        sequences.add(new Sequence(List.of("B1", "A2"), false));
        sequences.add(new Sequence(List.of("B2", "B1", "A1"), false));
        sequences.add(new Sequence(List.of("B1", "B1", "B1", "A2"), false));
        sequences.add(new Sequence(List.of("B2", "B2", "B1", "B2", "A1"), false));

        sequences.add(new Sequence(List.of("A2", "B2"), false));
        sequences.add(new Sequence(List.of("A1", "B2", "B2"), false));
        sequences.add(new Sequence(List.of("A2", "B1", "B2", "B2"), false));
        sequences.add(new Sequence(List.of("A2", "B2", "B1", "B2", "B1"), false));



        Collections.shuffle(sequences); // Randomize order each time
        return sequences;
    }
}

