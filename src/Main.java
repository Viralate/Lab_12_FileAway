import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        // Run the file chooser on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            //initiate file chooser
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                //logic to select file and initiate lines, words, and char count
                File selectedFile = fileChooser.getSelectedFile();
                processFile(selectedFile);
            } else {
                // if no file is selected prompt user
                System.out.println("No file was selected.");
            }
        });
    }

    private static void processFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int linesCount = 0;
            int wordsCount = 0;
            int charCount = 0;
            String line;
            //logic to count lines, words and chars
            while ((line = reader.readLine()) != null) {
                linesCount++;
                String[] words = line.split("\\s+");  // Split line into words using whitespace as delimiter
                wordsCount += words.length;
                charCount += line.length();
            }
            //print results
            System.out.println("File: " + file.getName());
            System.out.println("Number of lines: " + linesCount);
            System.out.println("Number of words: " + wordsCount);
            System.out.println("Number of characters: " + charCount);
        } catch (Exception e) {
            //error handling if file cannot be read
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
