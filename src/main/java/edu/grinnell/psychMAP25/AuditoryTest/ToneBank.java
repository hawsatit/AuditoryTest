/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.grinnell.psychMAP25.AuditoryTest;

import java.util.HashMap;
import java.util.Map;

public class ToneBank {
    private static final Map<String, Integer> toneFrequencies = new HashMap<>();

    static {
        toneFrequencies.put("A1", 2000);
        toneFrequencies.put("A2", 4000);
        toneFrequencies.put("B1", 8000);
        toneFrequencies.put("B2", 12000);
        toneFrequencies.put("C1", 1000);
        toneFrequencies.put("C2", 6000);
        toneFrequencies.put("D", 16000);
    }

    public static int getFrequency(String symbol) {
        return toneFrequencies.getOrDefault(symbol, 0); // return 0 if not found
    }
}
