import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        System.out.println("Введите (1) чтобы начать игру, (2) чтобы закрыть приложение");
        String menuInput = in.next();
        while (!Objects.equals(menuInput, "1") && !Objects.equals(menuInput, "2")){
            System.out.println("Введите (1) или (2)");
            menuInput = in.next();
        }
        if(menuInput.equals("1")) {
            startGame();
        }
        if(menuInput.equals("2")) {
            System.exit(0);
        }
    }

    private static void startGame() {
        String word;
        int miss_count = 0;
        int wordId = getRandomNumber();
        word = WORDS[wordId];
        char[] mask = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            mask[i] = '_';
        }
        startGameLoop(word,mask, miss_count);
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

    private static String[] WORDS = new String[] {"виселица", "шаловливость", "кормилица", "яркость", "прозорливость"};


    public static void startGameLoop(String word, char[] mask, int miss_count) {
        playerTurn(word, mask, miss_count);
    }

    public static void playerTurn(String word, char[] mask, int miss_count) {
        System.out.println(miss_count);
        System.out.println(mask);
        String playerInput = in.next();
        String isNumber = ".*\\d.*";
        String isUpperCase = "^[А-Я]+$";
        while (playerInput.length() > 1 || playerInput.matches(isNumber) || playerInput.matches(isUpperCase)) {
            System.out.println("Введите маленькую букву");
            playerInput = in.next();
        }
        char playerChoice = playerInput.charAt(0);
        letterCheck(playerChoice, word, mask, miss_count);
    }

    public static void letterCheck(char playerChoice, String word, char[] mask, int miss_count){
        if(word.contains(String.valueOf(playerChoice))) {
            ArrayList<Integer> result = findPositions(word, playerChoice);
            for (Integer n : result){
                mask[n] = playerChoice;
            }
        }
        else {
            miss_count += 1;
        }
        checkGameState(word, mask, miss_count);
    }

    private static void checkGameState(String word, char[] mask, int missCount) {
        int FATAL_MISS_COUNT = 6;
        if (!(new String(mask).contains("_"))){
            System.out.println("YOU WIN");
            System.exit(0);
        }
        else if (missCount == FATAL_MISS_COUNT){
            System.out.println("YOU LOSE");
            System.exit(0);
        }
        playerTurn(word, mask, missCount);
    }

}