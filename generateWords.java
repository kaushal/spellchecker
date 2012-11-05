import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class generateWords {
    public static void main(String[] args) throws IOException {
        spellcheck checker = new spellcheck();

        // Scanner sc = new Scanner(new File("words.txt"));
        String word = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("words.txt")));

        while ((word = br.readLine()) != null) {
            char first = word.charAt(0);
            word = word.toLowerCase();
            if (!checker.dict.search(word) || word.length() <= 1 || !(first - 'a' >= 0 && first - 'a' < 26))
                continue;
            word = messUpWord(word);
            try {
                System.out.print("Corrected Word: ");
                checker.checkWord(word, 1);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            if (!checker.printOne) {
                System.out.println("ERROR");
                System.out.println(word);
                System.out.println(checker.currentWord);
            }
            else {
                System.out.println("Original Word: " + word);
                // System.out.println("Corrected to: " + checker.currentWord);
                System.out.println("LGTM");
            }

            checker.printOne = false;
        }
    }

    public static String messUpWord(String word) {

        Random generator = new Random();
        int choice;
        for (int i = 0; i < word.length() - 1; i++) {

            choice = generator.nextInt() % 3;
            if (choice == 0) {
                if (word.charAt(i) - 'a' >= 0 && word.charAt(i) - 'a' < 26)
                    word = word.substring(0, i) + (char) (word.charAt(i) - 32) + word.substring(i + 1);

                continue;
            }

            if (choice == 1) {
                for (int j = 0; j < generator.nextInt() % 4; j++)
                    word = word.substring(0, i) + word.charAt(i) + word.substring(i);
                continue;
            }
            String vowels = "aeiou";
            if (choice == 3) {
                if (vowels.contains(word.substring(i, i + 1))) {
                    word = word.substring(0, i) + vowels.charAt((i % 4)) + word.substring(i + 1);
                }
                continue;

            }
        }
        // System.out.println(word);
        return word;
    }

}
