package edu.grinnell.psychMAP25.AuditoryTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AuditoryTest {

    public static volatile boolean running = true;

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    private static int getValidatedIntegerInput(Scanner scanner, String prompt) {
        int result;
        while (true) {
            clearConsole();
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                result = Integer.parseInt(input);
                if (result >= 0) return result;
                else System.out.println("Please enter a non-negative number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static int getValidatedResponse(Scanner scanner, int questionNumber) {
        while (true) {
            clearConsole();
            System.out.println("Sequence " + questionNumber + ":\nWas the sequence familiar?");
            System.out.println("(Enter \"1\" for Yes, \"0\" for No, or \"q\" to quit)");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("q")) {
                running = false;
                System.out.println("Exiting...");
                return -1;
            }
            if (input.equals("0") || input.equals("1")) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Please enter 0 or 1.");
            }
        }
    }

    public static void runPhase(List<Sequence> sequences, Scanner scanner, List<Sequence> allSeqs, List<Integer> responses, List<Integer> correctness, int[] questionNumber) throws InterruptedException {
        for (Sequence sequence : sequences) {
            if (!running) break;
            clearConsole();
            sequence.play();

            int response = getValidatedResponse(scanner, questionNumber[0]);
            if (!running || response == -1) break;

            allSeqs.add(sequence);
            responses.add(response);
            boolean isCorrect = (sequence.isGrammatical() && response == 1)
                    || (!sequence.isGrammatical() && response == 0);
            correctness.add(isCorrect ? 1 : 0);

            questionNumber[0]++;
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int participantNumber = getValidatedIntegerInput(scanner, "Please enter participant number:");

        clearConsole();
        System.out.println("Which sequence bank would you like to use?");
        System.out.println("Enter 'a' for Condition A or 'b' for Condition B:");
        String bankChoice = scanner.nextLine().trim().toLowerCase();

        System.out.println("Press Enter to begin...");
        scanner.nextLine();
        clearConsole();

        List<Sequence> sequences1, sequences2, sequences3;
        
        while (true){
            if (bankChoice.equals("b")) {
                clearConsole();
                sequences1 = SequenceBankB1.getAllSequences();
                sequences2 = SequenceBankB2.getAllSequences();
                sequences3 = SequenceBankB3.getAllSequences();
                break;
            } else if (bankChoice.equals("a")){
                clearConsole();
                sequences1 = SequenceBankA1.getAllSequences();
                sequences2 = SequenceBankA2.getAllSequences();
                sequences3 = SequenceBankA3.getAllSequences();
                break;
            } else {
                clearConsole();
                System.out.println("You must enter a or b!");
            }
        }

        List<Sequence> allSequences = new ArrayList<>();
        List<Integer> responses = new ArrayList<>();
        List<Integer> correctness = new ArrayList<>();
        int[] questionNumber = {1};

        // Run Phase 1
        runPhase(sequences1, scanner, allSequences, responses, correctness, questionNumber);
        if (!running) return;

        // Phase 2
        clearConsole();
        System.out.println("End of Phase 1. Press Enter to begin Phase 2...");
        scanner.nextLine();
        runPhase(sequences2, scanner, allSequences, responses, correctness, questionNumber);
        if (!running) return;

        // Phase 3
        clearConsole();
        System.out.println("End of Phase 2. Press Enter to begin Phase 3...");
        scanner.nextLine();
        runPhase(sequences3, scanner, allSequences, responses, correctness, questionNumber);

        // Save results
        String filePath = "results/participant_" + participantNumber + "_auditory.csv";
        FileWriter logWriter = new FileWriter(filePath);
        logWriter.write("Participant," + participantNumber + "\n");
        logWriter.write("Bank," + (bankChoice.equals("b") ? "B" : "A") + "\n");
        logWriter.write("SequenceNumber,Sequence,IsGrammatical,Response,Correct\n");

        for (int i = 0; i < responses.size(); i++) {
            Sequence seq = allSequences.get(i);
            logWriter.write((i + 1) + "," + seq.toString() + "," + seq.isGrammatical() + "," +
                    responses.get(i) + "," + correctness.get(i) + "\n");
        }

        logWriter.close();

        clearConsole();
        System.out.println("Thank you! You have completed the auditory portion of the experiment.");
        System.out.println("Results saved to: " + filePath);
        System.out.println("Total sequences answered: " + responses.size());
    }
}
