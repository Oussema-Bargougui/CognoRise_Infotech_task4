import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame{
    private static final String[] WORDS = {"JAVA", "HANGMAN", "PROGRAMMING", "DEVELOPER", "COMPUTER"};
    private static final int MAX_TRIES = 6;

    public static void main(String[] args) {
        String wordToGuess = getRandomWord();
        List<Character> guessedLetters = new ArrayList<>();
        int triesLeft = MAX_TRIES;
        boolean wordGuessed = false;

        Scanner scanner = new Scanner(System.in);

        while (triesLeft > 0 && !wordGuessed) {
            System.out.println("Word to guess: " + getWordDisplay(wordToGuess, guessedLetters));
            System.out.println("Tries left: " + triesLeft);
            System.out.println("Guessed letters: " + guessedLetters);

            System.out.print("Enter a letter: ");
            char guess = scanner.next().toUpperCase().charAt(0);

            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter.");
                continue;
            }

            guessedLetters.add(guess);

            if (wordToGuess.indexOf(guess) == -1) {
                triesLeft--;
                System.out.println("Incorrect guess.");
            } else {
                System.out.println("Correct guess!");
            }

            wordGuessed = isWordGuessed(wordToGuess, guessedLetters);
        }

        if (wordGuessed) {
            System.out.println("Congratulations! You guessed the word: " + wordToGuess);
        } else {
            System.out.println("Game over. The word was: " + wordToGuess);
        }

        scanner.close();
    }

    private static String getRandomWord() {
        Random random = new Random();
        return WORDS[random.nextInt(WORDS.length)];
    }

    private static String getWordDisplay(String word, List<Character> guessedLetters) {
        StringBuilder display = new StringBuilder();
        for (char letter : word.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                display.append(letter).append(" ");
            } else {
                display.append("_ ");
            }
        }
        return display.toString().trim();
    }

    private static boolean isWordGuessed(String word, List<Character> guessedLetters) {
        for (char letter : word.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }
}
