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
                if (result >= 0) {
                    return result;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
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

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Get participant number
        int participantNumber = getValidatedIntegerInput(scanner, "Please enter participant number:");

        // Prompt for test bank
        clearConsole();
        System.out.println("Which sequence bank would you like to use?");
        System.out.println("Enter 'a' for Condition A or 'b' for Condition B:");
        String bankChoice = scanner.nextLine().trim().toLowerCase();

        System.out.println("Press Enter to begin...");
        scanner.nextLine();
        clearConsole();

        // Load the appropriate sequence bank
        List<Sequence> sequences;
        if (bankChoice.equals("b")) {
            sequences = SequenceBank2.getAllSequences();
        } else {
            sequences = SequenceBank.getAllSequences();
        }

        List<Integer> responses = new ArrayList<>();
        List<Integer> correctness = new ArrayList<>();
        int questionNumber = 1;

        for (Sequence sequence : sequences) {
            if (!running) break;

            clearConsole();
            sequence.play();

            int response = getValidatedResponse(scanner, questionNumber);
            if (!running || response == -1) break;

            responses.add(response);

            boolean isCorrect = (sequence.isGrammatical() && response == 1)
                    || (!sequence.isGrammatical() && response == 0);
            correctness.add(isCorrect ? 1 : 0);

            questionNumber++;
            Thread.sleep(1000);
        }

        // Save to CSV
        String filePath = "results/participant_" + participantNumber + "_auditory.csv";
        FileWriter logWriter = new FileWriter(filePath);
        logWriter.write("Participant," + participantNumber + "\n");
        logWriter.write("Bank," + (bankChoice.equals("b") ? "B" : "A") + "\n");
        logWriter.write("SequenceNumber,Sequence,IsGrammatical,Response,Correct\n");

        for (int i = 0; i < responses.size(); i++) {
            Sequence seq = sequences.get(i);
            logWriter.write((i + 1) + "," + seq.toString() + "," + seq.isGrammatical() + "," +
                    responses.get(i) + "," + correctness.get(i) + "\n");
        }

        logWriter.close();

        clearConsole();
        System.out.println("Thank you! You have completed the auditory portion of the experiment.");
        System.out.println("Results saved to: " + filePath);
        System.out.println("Total sequences answered: " + responses.size());

        System.exit(0);
    }
}
