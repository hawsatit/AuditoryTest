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
                if (result > 0) {
                    return result;
                } else {
                    System.out.println("Please enter a positive number.");
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
            System.out.println("(Enter \"1\" for Familiar, \"2\" for Unfamiliar, or \"q\" to quit)");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("q")) {
                running = false;
                System.out.println("Exiting...");
                return -1;
            }
            try {
                int choice = Integer.parseInt(input);
                if (choice == 1 || choice == 2) {
                    return choice;
                } else {
                    System.out.println("Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int participantNumber = getValidatedIntegerInput(scanner, "Please enter participant number:");

        clearConsole();
        System.out.println("Welcome to the auditory test phase.\n" +
                "You will hear a series of tone sequences.\n" +
                "After each sequence, you will be asked if it sounds familiar.");
        System.out.println("Press Enter to begin...");
        scanner.nextLine();

        List<Sequence> sequences = SequenceBank.getAllSequences();
        List<Integer> responses = new ArrayList<>();

        int questionNumber = 1;

        for (Sequence sequence : sequences) {
            if (!running) break;

            clearConsole();
            sequence.play();

            int response = getValidatedResponse(scanner, questionNumber);
            if (!running || response == -1) break;

            responses.add(response);
            questionNumber++;

            Thread.sleep(1000);
        }

        // Save to CSV like in AGLSpeedReader
        String filePath = "results/participant_" + participantNumber + "_auditory.csv";
        FileWriter logWriter = new FileWriter(filePath);
        logWriter.write("Participant," + participantNumber + "\n");
        logWriter.write("SequenceNumber,Sequence,IsGrammatical,Response\n");

        for (int i = 0; i < responses.size(); i++) {
            Sequence seq = sequences.get(i);
            logWriter.write((i + 1) + "," + seq.toString() + "," + seq.isGrammatical() + "," + responses.get(i) + "\n");
        }

        logWriter.close();

        clearConsole();
        System.out.println("Thank you! You have completed the auditory portion of the experiment.");
        System.out.println("Results saved to: " + filePath);
        System.out.println("Total sequences: " + responses.size());

        System.exit(0);
    }
}
