import java.util.*;


public class Main {
    public static void main(String[] args) {
        startGameMenu();
    }

    private static void startGameMenu(){
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
        List<Character> error_chars = new ArrayList();
        int wordId = getRandomNumber();
        word = WORDS[wordId];
        char[] mask = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            mask[i] = '_';
        }
        startGameLoop(word,mask, miss_count, error_chars);
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


    public static void startGameLoop(String word, char[] mask, int miss_count, List<Character> error_chars) {
        playerTurn(word, mask, miss_count, error_chars);
    }

    public static void playerTurn(String word, char[] mask, int miss_count, List<Character> error_chars) {
        System.out.println(miss_count);
        System.out.println(error_chars);
        System.out.println(mask);
        String playerInput = in.next();
        String isNumber = ".*\\d.*";
        String isUpperCase = "^[А-Я]+$";
        while (playerInput.length() > 1 || playerInput.matches(isNumber) || playerInput.matches(isUpperCase)) {
            System.out.println("Введите маленькую букву");
            playerInput = in.next();
        }
        char playerChoice = playerInput.charAt(0);
        letterCheck(playerChoice, word, mask, miss_count, error_chars);
    }

    public static void letterCheck(char playerChoice, String word, char[] mask, int miss_count, List<Character> error_chars){
        if(word.contains(String.valueOf(playerChoice))) {
            ArrayList<Integer> result = findPositions(word, playerChoice);
            for (Integer n : result){
                mask[n] = playerChoice;
            }
        }
        else {
            if (error_chars.contains(playerChoice)) {
                System.out.println("Вы уже вводили такую букву");
                checkGameState(word, mask, miss_count, error_chars);
            }
            else {
                System.out.println("Такой буквы нет");
                error_chars.add(playerChoice);
                miss_count += 1;
            }
        }
        checkGameState(word, mask, miss_count, error_chars);
    }

    private static void checkGameState(String word, char[] mask, int missCount, List<Character> error_chars) {
        int FATAL_MISS_COUNT = 6;
        if (!(new String(mask).contains("_"))){
            System.out.println("YOU WIN");
            startGameMenu();
        }
        else if (missCount == FATAL_MISS_COUNT){
            System.out.println("YOU LOSE");
            startGameMenu();
        }
        playerTurn(word, mask, missCount, error_chars);
    }

}