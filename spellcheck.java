import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class spellcheck {
    static prefixTree dict;
    public static boolean printOne = false;
    static String currentWord = "";

    public static void main(String[] args) {
        populateDict();
        while (true) {
            printOne = false;
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            String rawInputWord = sc.nextLine();
            rawInputWord = rawInputWord.toLowerCase();
            if(dict.search(rawInputWord)){
                printOnce(rawInputWord);
                continue;
            }
            rawInputWord = rawInputWord.replaceAll("[aeiou]", "a");
            //System.out.println(rawInputWord);
            
            
            checkWord(rawInputWord, 1);
            if (!printOne)
                System.out.println("NO SUGGESTION");

        }
    }

    //This is only ever used by generateWords.java
    public spellcheck() {
        populateDict();
    }
    
    //This method checks the word for all possible corrections according to the problem definition.
    public static void checkWord(String inputWord, int index) {
        inputWord = inputWord.toLowerCase();
        if (dict.search(inputWord)) {
            printOnce(inputWord);
            return;
        }
        else
            ;//vowelSearch(inputWord, 0);

        if (index < inputWord.length() && inputWord.charAt(index - 1) == inputWord.charAt(index)) {
            int count = 0;
            for (int i = index; i < inputWord.length(); i++) {
                if (inputWord.charAt(i) != inputWord.charAt(i - 1))
                    break;
                else
                    count++;
            }
            checkWord(inputWord.substring(0, index) + inputWord.substring(index + count), index);
            if(printOne)
                return;

            if (index - 1 + count < inputWord.length()) {
                checkWord(inputWord.substring(0, index - 1) + inputWord.substring(index), index);
                if(printOne)
                    return;
            }
            if (index + count < inputWord.length()) {
                checkWord(inputWord, index + count);
                if(printOne)
                    return;
            }
        }
        else if (index < inputWord.length()) {
            checkWord(inputWord, index + 1);
            if(printOne)
                return;
        }
        
        vowelSearch(inputWord, 0);
        return;

    }
    
    //This method checks every vowel combination in the given input word and stops once a match is found. 
    public static void vowelSearch(String word, int count) {
        String vowels = "eaoiu"; //This order was chosen based on the frequency of occurrence of vowels in the english language
        if (dict.search(word)) {
            printOnce(word);
            return;
        }
        if (count >= word.length())
            return;

        int vowelLocation = vowels.indexOf(word.charAt(count));

        if (vowelLocation >= 0) {
            for (int i = 0; i < vowels.length(); i++) {
                // replace that vowel with vowels(i), then call the funciton
                // once again with the new word and an incremented count
                if (count == word.length() - 1)
                    word = word.substring(0, word.length() - 1) + vowels.charAt(i);
                else
                    word = word.substring(0, count) + vowels.charAt(i) + word.substring(count + 1, word.length());
                vowelSearch(word, count + 1);
                if(printOne)
                    return;
            }
        }
        else
            vowelSearch(word, count + 1);
    }

    public static void printOnce(String word) {
        if (!printOne) {
            currentWord = word;
            System.out.println(word);
        }
        printOne = true;

    }

    public static void populateDict() {
        dict = new prefixTree();
        try {// Putting all of the names in /usr/share/dict/words into a prefixtree
            Scanner sc = new Scanner(new File("/usr/share/dict/words"));
            while (sc.hasNext()) {
                String word = sc.next();
                dict.insert(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
    }

}
