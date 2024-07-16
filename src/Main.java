import java.util.ArrayList;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        createHang();
    }

    public static ArrayList<Integer> findPositions(String string, char character) {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < string.length(); i++){
            if (string.charAt(i) == character) {
                positions.add(i);
            }
        }
        return positions;
    }

    public static int getRandomNumber() {
        return (int) (Math.random() * 5);
    }

    static Scanner in = new Scanner(System.in);

    private static int FATAL_MISS_COUNT = 6;

    private static String[] WORDS = new String[] {"виселица", "шаловливость", "кормилица", "яркость", "прозорливость"};

    public static void createHang() {
        String word;
        int miss_count = 0;
        int wordId = getRandomNumber();
        word = WORDS[wordId];
        char[] hang = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            hang[i] = '_';
        }
        startGameLoop(word,hang, miss_count);
    }

    public static void startGameLoop(String word, char[] hang, int miss_count) {
        playerTurn(word, hang, miss_count);
    }

    public static void playerTurn(String word, char[] hang, int miss_count) {
        System.out.println(miss_count);
        System.out.println(hang);
        char playerChoice = in.next().charAt(0);
        letterCheck(playerChoice, word, hang, miss_count);
    }

    public static void letterCheck(char playerChoice, String word, char[] hang, int miss_count){
        if(word.contains(String.valueOf(playerChoice))) {
            ArrayList<Integer> result = findPositions(word, playerChoice);
            for (Integer n : result){
                hang[n] = playerChoice;
            }
        }
        else {
            miss_count += 1;
        }
        checkGameState(playerChoice, word, hang, miss_count);
    }

    private static void checkGameState(char playerChoice, String word, char[] hang, int missCount) {
        if (!(new String(hang).contains("_"))){
            System.out.println("YOU WIN");
            System.exit(0);
        }
        else if (missCount == FATAL_MISS_COUNT){
            System.out.println("YOU LOSE");
            System.exit(0);
        }
        playerTurn(word, hang, missCount);
    }

}