package hangman;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.*;
import java.io.*;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HangmanApp extends GameApplication {
    private final HangmanFactory hangmanFactory = new HangmanFactory();
    private Entity hangman, underline;
    private String word = ""; // variable that will hold the current word being asked
    private String[] lettersGuessed; // array to hold all letters that the user has guessed
    private int guessesIndex = 0; // counter variable to hold the current index of the lettersGuessed array
    private int incorrectGuesses = 0; // variable to hold the number of incorrect letter guesses that the user has
    private int correctGuesses; // variable used to hold the number of correct letter guesses that the user has
    private int wordsAskedCounter = 0; // variable used to hold the number of words asked
    private String[] wordList = new String[10]; // array used to hold all the words that will be asked to the user
    private String[] wordsAsked = new String[10]; // array used to hold all the words that have already been asked
    private final int KEY_GAP_SPACE = 5; // constant variable used as a separation value for the "keyboard" letters that are displayed
    private final int KEY_WIDTH = 65; // constant variable used to represent the width of each "keyboard" letter
    private final int KEY_HEIGHT = 65; // constant variable used to represent the height of each "keyboard" letter
    private int functionOrder = 0;

    private int roundsWon = 0; // variable used to hold the number of words correctly guessed by user
    private int roundsLost = 0; // variable used to hold the number of words that were not correctly guessed

    Text wordCountText; // text variable used to display the current word (out of 10) to be guessed

    // array used to hold all the different types of entities that will be used in the program
    public enum EntityType {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
        GAMEOVER, UNDERLINE, HANGMAN, HANGMAN1, HANGMAN2, HANGMAN3, HANGMAN4, HANGMAN5, HANGMAN6,
        DARK_A, DARK_B, DARK_C, DARK_D, DARK_E, DARK_F, DARK_G, DARK_H, DARK_I, DARK_J, DARK_K,
        DARK_L, DARK_M, DARK_N, DARK_O, DARK_P, DARK_Q, DARK_R, DARK_S, DARK_T, DARK_U, DARK_V,
        DARK_W, DARK_X, DARK_Y, DARK_Z, CHALKBOARD, SCORE0, SCORE1, SCORE2, SCORE3, SCORE4, SCORE5,
        SCORE6, SCORE7, SCORE8, SCORE9, SCORE10, EXIT;
    }

    // array used to hold all the entity types to be excluded from particular checks. Mostly includes everything outside of the letters.
    EntityType[] entitiesToExclude = {EntityType.GAMEOVER, EntityType.UNDERLINE, EntityType.HANGMAN, EntityType.HANGMAN1, EntityType.HANGMAN2,
            EntityType.HANGMAN3, EntityType.HANGMAN4, EntityType.HANGMAN5, EntityType.HANGMAN6, EntityType.DARK_A, EntityType.DARK_B,
            EntityType.DARK_C, EntityType.DARK_D, EntityType.DARK_E, EntityType.DARK_F, EntityType.DARK_G, EntityType.DARK_H, EntityType.DARK_I,
            EntityType.DARK_J, EntityType.DARK_K, EntityType.DARK_L, EntityType.DARK_M, EntityType.DARK_N, EntityType.DARK_O, EntityType.DARK_P,
            EntityType.DARK_Q, EntityType.DARK_R, EntityType.DARK_S, EntityType.DARK_T, EntityType.DARK_U, EntityType.DARK_V, EntityType.DARK_W,
            EntityType.DARK_X, EntityType.DARK_Y, EntityType.DARK_Z, EntityType.CHALKBOARD, EntityType.SCORE0, EntityType.SCORE1, EntityType.SCORE2,
            EntityType.SCORE3, EntityType.SCORE4, EntityType.SCORE5, EntityType.SCORE6, EntityType.SCORE7, EntityType.SCORE8, EntityType.SCORE9,
            EntityType.SCORE10};

    // Same array as above, just converted to an ArrayList. This is done to get the functionality of an array list when checking for the elements listed in the array above
    List<EntityType> entitiesToExcludeList = new ArrayList<>(Arrays.asList(entitiesToExclude));

    // function that reads in text file that contains all the words for the hangman game
    public void readFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader("src/main/resources/words.txt");

        Scanner inputDevice = new Scanner(fileReader);

        int index = 0;

        // add all words in the text file to the wordList array
        while(inputDevice.hasNextLine()){
            wordList[index] = inputDevice.nextLine();
            index++;
        }
    }

    // function that checks for matches after the user has guessed a letter by typing or clicking the letter on screen
    public void lookForMatches(char letter){
        String wordToCheck = word.toLowerCase();

        // convert the letter to check from a character to a string
        String letterToCheck = Character.toString(letter);

        // if the lettersGuessed array contains the letter to check
        if(!Arrays.asList(lettersGuessed).contains(letterToCheck)){
            // remove the letter from the user's selection options
            removeKeyboardLetter(String.valueOf(letter));

            // boolean that holds whether or not there is a match
            Boolean isMatch = false;

            // loop through all the letters in the wordToCheck
            for (int i = 0; i < wordToCheck.length(); i++) {
                // if the letter guessed by the user is in the word (at index i)
                if (letter == wordToCheck.charAt(i)) {
                    isMatch = true;
                    // spawn the "keyboard" letter element. Here, since the letter is guessed by the user, the faded parameter will be false
                    spawnLetter(wordToCheck, String.valueOf(letter), i, false);
                    // increment the correctGuesses counter variable
                    correctGuesses++;
                    // if the number of correct guesses is equal to the length of the word (all letters have been guessed)
                    if (correctGuesses == wordToCheck.length()){
                        // if 10 words have already been asked, end the game
                        if (wordsAskedCounter == 10){
                            // display winning message to user
                            showMessage("You Win!", () -> {
                                roundsWon++; // increment the roundsWon counter
                                displayEndGame(wordToCheck); // display the end of game scene
                            });
                        } else { // otherwise
                            // display winning message to user
                            showMessage("You Win!", () -> {
                                roundsWon++; // increment the roundsWon counter
                                getGameController().startNewGame(); // load in a new word to guess
                            });
                        }
                    }
                }
            }

            // if the letter guessed does not match any of the letters in the word
            if (!isMatch){
                incorrectGuesses++; // increment the incorrectGuesses counter
                hangman.removeFromWorld(); // remove the current hangman entity
                String entityToSpawn = "hangman" + incorrectGuesses; // variable to hold the correct hangman entity to spawn
                hangman = spawn(entityToSpawn, 1029-(125/2), 225); // spawn the hangman entity

                // if the number of incorrect guesses is 6 (end of round), display remainder of word as faded letters. Also display appropriate message
                if (incorrectGuesses == 6){
                    boolean letterInWordNotGuessed = false; // boolean used to determine whether the letter is in a the word or not
                    String currentWord = word.toLowerCase();
                    // loop through the current word's letters
                    for(int i = 0; i < currentWord.length(); i++) {
                        String currentLetter = Character.toString(currentWord.charAt(i)); // current letter
                        // loop through the array of lettersGuessed
                        for(int j = 0; j < lettersGuessed.length; j++) {
                            // if the current letter equals the letter at the current index of the lettersGuessed array
                            if (currentLetter.equals(lettersGuessed[j])) {
                                letterInWordNotGuessed = false; // false, as the letter has already been guessed
                                break; // move on to next index of array
                            } else {
                                letterInWordNotGuessed = true; // true, letter is in word but hasn't been guessed
                            }
                        }
                        // if the letter is in the word but hasn't been guessed
                        if(letterInWordNotGuessed){
                            // spawn the faded version of the letter to show user which letters they missed
                            spawnLetter(currentWord, currentLetter, i, true);
                        }
                    }
                    // if the wordsAskedCounter is 10 (end of game)
                    if (wordsAskedCounter == 10){
                        showMessage("You lose!\nAnswer: " + word, () -> {
                            roundsLost++; // increment roundsLost counter
                            displayEndGame(wordToCheck); // display the end of game scene
                        });
                    } else {
                        showMessage("You lose!\nAnswer: " + word, () -> {
                            roundsLost++; // increment roundsLost counter
                            getGameController().startNewGame(); // load in the next letter/round for the user to play
                        });
                    }
                }
            }
            lettersGuessed[guessesIndex] = letterToCheck; // add the letter to the lettersGuessed array
            guessesIndex++; // increment the guessesIndex counter
        }

    }

    // function used to remove currently existing entities and display the end of game scene
    public void displayEndGame(String lastWord){
        // loop through all entity types
        for(EntityType entityType : EntityType.values()) {
            // if the entity is not an underline
            if (entityType != EntityType.UNDERLINE) {
                try {
                    // remove the entity from the application
                    getGameWorld().getSingleton(entityType).removeFromWorld();
                } catch (NoSuchElementException e) {
                    //NoSuchElementException was thrown, move on to next entity type
                    continue;
                }
            }

            // remove all the letters. Previous for loop will not remove duplicate letters or underlines
            // loop through all letters in the word
            for(int i = 0; i < lastWord.length(); i++){
                String letter = String.valueOf(lastWord.charAt(i)); // variable used to hold the current letter
                String darkLetter = "dark_" + lastWord.charAt(i); // varaible used to hold the faded version of the current letter
                // if the letter or faded version matches the current entity in question
                if((letter.equals(entityType.toString().toLowerCase(Locale.ROOT))) || darkLetter.equals(entityType.toString().toLowerCase(Locale.ROOT))){
                    try {
                        // remove the entity from the application
                        getGameWorld().getSingleton(entityType).removeFromWorld();
                    } catch (NoSuchElementException e) {
                        //NoSuchElementException was thrown, move on to next entity type
                        continue;
                    }
                }
                try {
                    // remove all underline entities from the application
                    getGameWorld().getSingleton(EntityType.UNDERLINE).removeFromWorld();
                } catch (NoSuchElementException e) {
                    //NoSuchElementException was thrown, move on to next entity type
                    continue;
                }
            }
        }
        // display the "Game Over" message and the remainder of the end of game scene
        showMessage("Game Over!", () -> {
            // remove the current word count
            getGameScene().removeUINode(wordCountText);

            // spawn entities for the end of game scene
            spawn("chalkboard", 270, 165);
            String roundsWonText = "score_" + roundsWon;
            String roundsLostText = "score_" + roundsLost;
            spawn(roundsWonText, 695, 340);
            spawn(roundsLostText, 695, 430);
            spawn("exit", 470, 615);
        });
    }

    // function used to spawn the "keyboard" letters on the sceeen
    public void spawnLetter(String word, String letter, int letterIndex, boolean faded){
        int WORD_START_X = 485; // used to hold the x-coordinate of the middle of the line (word)
        int WORD_START_Y = 235; // used to hold the y-coordinate of the middle of the line (word)
        int spawnX; // used to hold the x-coordinate of where the new element should be spawned
        int spawnY; // used to hold the y-coordinate of where the new element should be spawned
        int numAwayFromMiddle; // used to hold a number that represents how many letters the letter to be spawned is away from the middle

        // if word has an even number of letters
        if(word.length() % 2 == 0) {
            // if the letter is in the first half of the word
            if (letterIndex < word.length() / 2) {
                // calculate how many letters the current letter is away from the middle of the word
                numAwayFromMiddle = (word.length() / 2) - letterIndex;

                // calculate the x-coordinate of the letter to spawn
                spawnX = WORD_START_X - ((numAwayFromMiddle * KEY_WIDTH) +
                        (KEY_GAP_SPACE * (numAwayFromMiddle - 1) + KEY_GAP_SPACE / 2));
            } else { // if the letter is in the second half of the word
                // calculate the x-coordinate of the letter to spawn
                spawnX = WORD_START_X + (KEY_WIDTH * (letterIndex - word.length() / 2)) +
                        (KEY_GAP_SPACE * (letterIndex - word.length() / 2)) + KEY_GAP_SPACE/2;
            }
        } else { // if the word has an odd number of letters
            // calculate how many letters the current letter is away from the middle of the word
            numAwayFromMiddle = (int) (Math.ceil(word.length() / 2)) - letterIndex;
            // if the letter is in the middle (words with odd number of letters have a middle letter)
            if (letterIndex == Math.ceil(word.length() / 2)) {
                spawnX = WORD_START_X - KEY_WIDTH / 2;
            } else if (letterIndex < word.length() / 2) { // if the letter is before the middle letter
                spawnX = WORD_START_X - (KEY_WIDTH / 2 + (numAwayFromMiddle * KEY_WIDTH) +
                        (numAwayFromMiddle * KEY_GAP_SPACE));
            } else { // if the letter is after the middle letter
                spawnX = WORD_START_X + (KEY_WIDTH * (letterIndex - (int) (Math.ceil(word.length()/ 2))-1)) +
                        (KEY_GAP_SPACE * (letterIndex - (int)(Math.ceil(word.length() / 2)))) + KEY_WIDTH / 2;
            }
        }
        spawnY = WORD_START_Y; // set up y-coordinate for letter to be spawned

        // if the letter should be faded
        if (faded) {
            String fadedLetter = "dark_" + letter; // set up variable used to hold faded version of letter
            spawn(fadedLetter, spawnX, spawnY); // spawn letter
        } else { // if regular letter
            spawn(letter, spawnX, spawnY); // spawn letter
        }
    }

    // function used to remove the selected letter from the user's selection options
    // will change letter from "normal" letter to an un-clickable "faded" version
    public void removeKeyboardLetter(String letterToRemove){
        // loop through the entityTypes
        for(EntityType entityType : EntityType.values()) {
            // if the entity type is not in the list to exclude
            if (!entitiesToExcludeList.contains(entityType)){
                // remove the letter and replace it with a faded version at the same location
                if (letterToRemove.equals(entityType.toString().toLowerCase(Locale.ROOT))) {
                        double xPosition = getGameWorld().getSingleton(entityType).getX(); // get x-coordinate of letter
                        double yPosition = getGameWorld().getSingleton(entityType).getY(); // get y-coordinate of letter
                        getGameWorld().getSingleton(entityType).removeFromWorld(); // remove letter
                        spawnFadedKey(letterToRemove, xPosition, yPosition); // spawn faded version of letter
                }
            }
        }
    }

    // function used to spawn faded versions of the "keyboard" letters to be displayed to user
    public void spawnFadedKey(String letterToFade, double x, double y){
        String fadedKeyToSpawn = "dark_" + letterToFade; // add "dark_" to the string in order to spawn the faded version
        spawn(fadedKeyToSpawn, x, y);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        // initialize game settings
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("Hangman");
        settings.setVersion("1.0");
        settings.setAppIcon("gallows.png");

        // read in the file of words
        try {
            this.readFile();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    // function used to handle user input. Only handles mouse clicks and keyboard letter entries
    @Override
    protected void initInput() {
        // for all keyboard entries, look for matches using the corresponding letter (call to lookForMatches())
        onKeyDown(KeyCode.A, () -> {
            lookForMatches('a');
        });

        onKeyDown(KeyCode.B, () -> {
            lookForMatches('b');
        });

        onKeyDown(KeyCode.C, () -> {
            lookForMatches('c');
        });

        onKeyDown(KeyCode.D, () -> {
            lookForMatches('d');
        });

        onKeyDown(KeyCode.E, () -> {
            lookForMatches('e');
        });

        onKeyDown(KeyCode.F, () -> {
            lookForMatches('f');
        });

        onKeyDown(KeyCode.G, () -> {
            lookForMatches('g');
        });

        onKeyDown(KeyCode.H, () -> {
            lookForMatches('h');
        });

        onKeyDown(KeyCode.I, () -> {
            lookForMatches('i');
        });

        onKeyDown(KeyCode.J, () -> {
            lookForMatches('j');
        });

        onKeyDown(KeyCode.K, () -> {
            lookForMatches('k');
        });

        onKeyDown(KeyCode.L, () -> {
            lookForMatches('l');
        });

        onKeyDown(KeyCode.M, () -> {
            lookForMatches('m');
        });

        onKeyDown(KeyCode.N, () -> {
            lookForMatches('n');
        });

        onKeyDown(KeyCode.O, () -> {
            lookForMatches('o');
        });

        onKeyDown(KeyCode.P, () -> {
            lookForMatches('p');
        });

        onKeyDown(KeyCode.Q, () -> {
            lookForMatches('q');
        });

        onKeyDown(KeyCode.R, () -> {
            lookForMatches('r');
        });

        onKeyDown(KeyCode.S, () -> {
            lookForMatches('s');
        });

        onKeyDown(KeyCode.T, () -> {
            lookForMatches('t');
        });

        onKeyDown(KeyCode.U, () -> {
            lookForMatches('u');
        });

        onKeyDown(KeyCode.V, () -> {
            lookForMatches('v');
        });

        onKeyDown(KeyCode.W, () -> {
            lookForMatches('w');
        });

        onKeyDown(KeyCode.X, () -> {
            lookForMatches('x');
        });

        onKeyDown(KeyCode.Y, () -> {
            lookForMatches('y');
        });

        onKeyDown(KeyCode.Z, () -> {
            lookForMatches('z');
        });

        // input handling of mouse clicks
        onBtnDown(MouseButton.PRIMARY, () -> {
            // hold the value of the mouse click location (x, y)
            Point2D mouse = getInput().getMousePositionUI();
            double mouseX = mouse.getX();
            double mouseY = mouse.getY();

            // if the y-coordinate of the click is less than 400 (above the keyboard display), do nothing and return
            if (mouseY < 400){
                return;
            }

            // loop through entity types and if one is located at the location of the mouse click, remove it from the game world.
            for(EntityType entityType : EntityType.values()) {
                // if the entity is not in the exclude list
                if(!entitiesToExcludeList.contains(entityType)){
                    try {
                        // get the beginning/end x and y values for the entity
                        double entityYTop = getGameWorld().getSingleton(entityType).getY();
                        double entityYBottom = getGameWorld().getSingleton(entityType).getBottomY();
                        double entityXLeft = getGameWorld().getSingleton(entityType).getX();
                        double entityXRight = getGameWorld().getSingleton(entityType).getRightX();

                        // if the mouse click occured in the range of the x, y values of the entity
                        if (((mouseX >= entityXLeft) && (mouseX <= entityXRight)) && ((mouseY >= entityYTop) && (mouseY <= entityYBottom))) {
                            // if the entity type is Exit (only applies to the end game scene)
                            if (entityType == EntityType.EXIT){
                                // show a message and exit the program once the user clicks "OK"
                                showMessage("Thanks for playing. Goodbye!", () -> {
                                    System.exit(0);
                                });
                            } else {
                                // check to see if letter is in the word. Convert entity type to string, then a char, and pass it to lookForMatches()
                                String letterClicked = entityType.toString().toLowerCase(Locale.ROOT);
                                char letterToCheck = letterClicked.charAt(0);
                                lookForMatches(letterToCheck);
                            }

                        }

                    } catch (NoSuchElementException e) {
                        //NoSuchElementException was thrown, move on to next entity type
                        continue;
                    }
                }
            }


        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        // initialize the game variables
        this.lettersGuessed = new String[27]; // lettersGuessed array capable of holding the entire alphabet
        // reset/initialize counters
        this.guessesIndex = 0;
        this.incorrectGuesses = 0;
        this.correctGuesses = 0;
    }

    @Override
    protected void initGame() {
        getGameScene().setBackgroundRepeat("hangmanBackground.png");
        getGameWorld().addEntityFactory(hangmanFactory);

        // if the number of words are less than 10, spawn the empty image (no hangman) hangman entity
        if(wordsAskedCounter < 10){
            hangman = spawn("hangman", 1029-(125/2), 225);
        }

        // call the readFile function to read in the words of the text file
        try {
            this.readFile();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        // from the list of words, select a random word and ask it to the user
        if (wordsAskedCounter < 10) {
            Random random = new Random();
            int randomNumber = random.nextInt(wordList.length); // generate random number
            word = wordList[randomNumber]; // assign the random word to the word variable

            // check to see if the word has been asked already. If it has, repeat until a new word has been grabbed
            for (int i = 0; i < wordsAsked.length; i++) {
                // if the word is in the words asked array
                if (word.equals(wordsAsked[i])) {
                    randomNumber = random.nextInt(wordList.length); // generate new random number
                    word = wordList[randomNumber]; // grab a new word from the wordList array
                    i = -1; // decrease the index counter by one to check the new word added against the words asked
                }
            }

            // add the word to the wordsAsked array and increment the wordsAskedCounter
            wordsAsked[wordsAskedCounter] = word;
            wordsAskedCounter++;

            // code to display the keyboard for the user to click for their selections
            int KEY_BOARD_DISPLAY_START_X = 485; // x-coordinate of the middle of the first line of keyboard letters
            int KEY_BOARD_DISPLAY_START_Y = 450; // y-coordinate of the middle of the first line of keyboard letters
            int spawnX; // x-coordinate of the letter to be spawned
            int spawnY; // y-coordinate of the letter to be spawned
            int numAwayFromMiddle; // number of letters that the letter is away from the middle of the word

            // array used to hold all the letters of a keyboard
            String[][] keyboardLayout = {{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"}, {"a", "s", "d", "f", "g", "h", "j", "k", "l"},{"z", "x", "c", "v", "b", "n", "m"}};

            // this works similar to the spawnLetter() function. Please see line 226 for comments on the following code
            for (EntityType entityType : EntityType.values()){
                if(!entitiesToExcludeList.contains(entityType)){
                    String type = entityType.name().toLowerCase();

                    for (int i = 0; i < keyboardLayout.length; i++){
                        for(int j = 0; j < keyboardLayout[i].length; j++){
                            if (keyboardLayout[i][j].equals(type)) {
                                // if there is an even number of keys in the row
                                if (keyboardLayout[i].length % 2 == 0) {
                                    if (i < keyboardLayout[i].length / 2) {
                                        numAwayFromMiddle = (keyboardLayout[i].length / 2) - j;

                                        spawnX = KEY_BOARD_DISPLAY_START_X - ((numAwayFromMiddle * KEY_WIDTH) +
                                                (KEY_GAP_SPACE * (numAwayFromMiddle - 1) + KEY_GAP_SPACE / 2));

                                    } else {
                                        spawnX = KEY_BOARD_DISPLAY_START_X + (KEY_WIDTH * (j - keyboardLayout[i].length / 2)) +
                                                (KEY_GAP_SPACE * (j - keyboardLayout[i].length / 2));
                                    }
                                } else { // odd number of keys in the row
                                    numAwayFromMiddle = (int) (Math.ceil(keyboardLayout[i].length / 2)) - j;
                                    if (j == Math.ceil(keyboardLayout[i].length / 2)) { // if middle key
                                        spawnX = KEY_BOARD_DISPLAY_START_X - KEY_WIDTH / 2;
                                    } else if (j < keyboardLayout[i].length / 2) { // if on the left side of the middle
                                        spawnX = KEY_BOARD_DISPLAY_START_X - (KEY_WIDTH / 2 + (numAwayFromMiddle * KEY_WIDTH) +
                                                (numAwayFromMiddle * KEY_GAP_SPACE));
                                    } else { // if on the right side of the middle
                                        spawnX = KEY_BOARD_DISPLAY_START_X + (KEY_WIDTH * (j - (int) (Math.ceil(keyboardLayout[i].length / 2))-1)) +
                                                (KEY_GAP_SPACE * (j - (int) (Math.ceil(keyboardLayout[i].length / 2)))) + KEY_WIDTH / 2;
                                    }
                                }
                                spawnY = KEY_BOARD_DISPLAY_START_Y + (KEY_HEIGHT * i);
                                spawn(type,spawnX, spawnY);
                            }
                        }
                    }
                }
            }


            // code to display the underlines for the word to be guessed. This indicates how many letters are in the word. Works similar
            // to code found on line 226 (spawnLetter() function). Refer to this code for documentation on how the following works:

            int UNDERLINE_START_X = 485;
            int UNDERLINE_START_Y = 300;
            int underlineX;
            int underlineY;

            for(int i = 0; i < word.length(); i++){
                // if word has even number of letters
                if(word.length() % 2 == 0){
                    if (i < word.length() / 2){
                        numAwayFromMiddle = (word.length() / 2) - i;

                        // note: KEY_WIDTH and underline width will both be the same number, so we can use this variable
                        underlineX = UNDERLINE_START_X - ((numAwayFromMiddle * KEY_WIDTH) +
                                (KEY_GAP_SPACE * (numAwayFromMiddle - 1) + KEY_GAP_SPACE / 2));
                    } else {
                        underlineX = UNDERLINE_START_X + (KEY_WIDTH * (i - word.length() / 2)) +
                                (KEY_GAP_SPACE * (i - word.length() / 2)) + KEY_GAP_SPACE/2;
                    }
                } else { // if odd number of letters
                    numAwayFromMiddle = (int) (Math.ceil(word.length() / 2)) - i;
                    if (i == Math.ceil(word.length() / 2)) { // if middle letter
                        underlineX = UNDERLINE_START_X - KEY_WIDTH / 2;
                    } else if (i < word.length() / 2) { // if on left side of middle letter
                        underlineX = UNDERLINE_START_X - (KEY_WIDTH / 2 + (numAwayFromMiddle * KEY_WIDTH) +
                                (numAwayFromMiddle * KEY_GAP_SPACE));
                    } else { // if on right side of middle letter
                        underlineX = UNDERLINE_START_X + (KEY_WIDTH * (i - (int) (Math.ceil(word.length()/ 2))-1)) +
                                (KEY_GAP_SPACE * (i - (int) (Math.ceil(word.length() / 2)))) + KEY_WIDTH / 2;
                    }
                }
                underlineY = UNDERLINE_START_Y;
                underline = spawn("underline", underlineX, underlineY);
            }
        }
    }

    @Override
    protected void initUI() {
        // Text varible that is used to display which word (out of 10) that is currently being asked to the user
        wordCountText = new Text("Word: " + wordsAskedCounter + "/10");
        // set properties of the Text variable
        wordCountText.setTranslateX(925);
        wordCountText.setTranslateY(700);
        wordCountText.setFont(Font.font("Verdana", FontWeight.BOLD, 46));
        wordCountText.setStroke(Color.BLACK);
        wordCountText.setFill(Color.WHITE);

        // add the variable as a UI node to the game scene. It will now appear on the screen
        getGameScene().addUINode(wordCountText); // add to the scene graph
    }

    public static void main(String[] args) {
        launch(args);
    }
}