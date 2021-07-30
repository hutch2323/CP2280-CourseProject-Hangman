package hangman;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
/*import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.input.*;*/
import com.almasb.fxgl.input.Input;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.awt.event.KeyListener;



import hangman.HangmanFactory;
import java.util.*;
import java.io.*;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HangmanApp extends GameApplication {
    private final HangmanFactory hangmanFactory = new HangmanFactory();
    private GameApplication app;
    private Entity a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
    private Entity player, hangman, underline;
    private int xPlacement = 0;
    //private int yPlacement = getAppHeight() / 2 - 15;
    private int yPlacement = 0;
    public String word = "";
    private int letterImageWidth = 100; // 100px
    private int letterImageHeight = 100; // 100px
    private int underLineWidth = 80;
    private String[] lettersGuessed;
    private int guessesIndex = 0;
    private int incorrectGuesses = 0;
    private int correctGuesses;
    private int wordsAskedCounter = 0;
    private String[] wordList = new String[10];
    private String[] wordsAsked = new String[10];
    private int KEY_GAP_SPACE = 5;
    private int KEY_WIDTH = 65;
    private int KEY_HEIGHT = 65;
    private int functionOrder = 0;

    public enum EntityType {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, GAMEOVER, UNDERLINE, HANGMAN, HANGMAN1, HANGMAN2, HANGMAN3, HANGMAN4, HANGMAN5, HANGMAN6;
    }

    public String[] getWordList(){
        return wordList;
    }

    public void readFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader("C:\\Users\\marcu\\Documents\\words.txt");

        Scanner inputDevice = new Scanner(fileReader);

        int index = 0;
        while(inputDevice.hasNextLine()){
            wordList[index] = inputDevice.nextLine();
            index++;
        }
    }

    public void lookForMatches(char letter){
        String wordToCheck = word.toLowerCase();
        String letterToCheck = Character.toString(letter);
        int numberOfMatches = 0;
        //Input input = getInput();
        //System.out.println(input);
        if (guessesIndex == 0){
            xPlacement = 0;
        }

        if(Arrays.asList(lettersGuessed).contains(letterToCheck)){

        } else {
            removeKeyboardLetter(String.valueOf(letter));
            Boolean isMatch = false;
            for (int i = 0; i < wordToCheck.length(); i++) {
                if (letter == wordToCheck.charAt(i)) {
                    isMatch = true;
                    System.out.println(letter + ": " + i);
                    spawnLetter(wordToCheck, String.valueOf(letter), i);
                    correctGuesses++;
                    if (correctGuesses == wordToCheck.length()){
                        if (wordsAskedCounter == 10){
                            showMessage("You Win!", () -> {
                                displayEndGame(wordToCheck);
                            });
                        } else {
                            showMessage("You Win!", () -> {
                                getGameController().startNewGame();
                            });
                        }
                        /*if (wordsAskedCounter == 10){
                            getGameController().exit();
                        }*/
                    }
                }
               // removeKeyboardLetter(String.valueOf(letter));
            }
            if (!isMatch){
                //inc("incorrectGuesses", 1);
                incorrectGuesses++;

                hangman.removeFromWorld();

                String entityToSpawn = "hangman" + incorrectGuesses;
                hangman = spawn(entityToSpawn, 1029-(125/2), 225);


                if (incorrectGuesses == 6){
                    if (wordsAskedCounter == 10){
                        showMessage("You lose!", () -> {
                            displayEndGame(wordToCheck);
                        });
                    } else {
                        showMessage("You lose!", () -> {
                            getGameController().startNewGame();
                        });
                    }
                }
            }


            //inc("lettersGuessed", 1);
            lettersGuessed[guessesIndex] = letterToCheck;
            guessesIndex++;
        }

    }

    public void displayEndGame(String lastWord){
        for(EntityType entityType : EntityType.values()) {
            if (entityType != EntityType.UNDERLINE) {
                try {
                    getGameWorld().getSingleton(entityType).removeFromWorld();
                } catch (NoSuchElementException e) {
                    //NoSuchElementException was thrown, move on to next entity type
                    continue;
                }
            } else {
                for(int i = 0; i < lastWord.length(); i++){
                    getGameWorld().getSingleton(EntityType.UNDERLINE).removeFromWorld();
                }
            }
        }
        spawn("gameover", 0, -100);
        showMessage("Game Over!", () -> {
            //getGameController().exit();
        });
    }

    public void setXYPlacements(int xAdjustment, int yAdjustment){
        if (xAdjustment > 0){
            xPlacement = xAdjustment * letterImageWidth;
        } else {
            xPlacement = 0;
        }

        yPlacement += yAdjustment;
    }

    public void setXYPlacementsUnderlines(int xAdjustment, int yAdjustment){
            xPlacement = ((underLineWidth + 20) * (xAdjustment)) + 10;
    }

    public void spawnLetter(String word, String letter, int letterIndex){
        int WORD_START_X = 485;
        int WORD_START_Y = 235;
        int spawnX = -100;
        int spawnY;
        int numAwayFromMiddle = 0;

        // if word has even number of letters
        if(word.length() % 2 == 0) {
            if (letterIndex < word.length() / 2) {
                numAwayFromMiddle = (word.length() / 2) - letterIndex;

                spawnX = WORD_START_X - ((numAwayFromMiddle * KEY_WIDTH) +
                        (KEY_GAP_SPACE * (numAwayFromMiddle - 1) + KEY_GAP_SPACE / 2));
            } else {
                spawnX = WORD_START_X + (KEY_WIDTH * (letterIndex - word.length() / 2)) +
                        (KEY_GAP_SPACE * (letterIndex - word.length() / 2)) + KEY_GAP_SPACE/2;
            }
        } else {
            numAwayFromMiddle = (int) (Math.ceil(word.length() / 2)) - letterIndex;
            if (letterIndex == Math.ceil(word.length() / 2)) {
                spawnX = WORD_START_X - KEY_WIDTH / 2;
            } else if (letterIndex < word.length() / 2) {
                spawnX = WORD_START_X - (KEY_WIDTH / 2 + (numAwayFromMiddle * KEY_WIDTH) +
                        (numAwayFromMiddle * KEY_GAP_SPACE));
            } else {
                spawnX = WORD_START_X + (KEY_WIDTH * (letterIndex - (int) (Math.ceil(word.length()/ 2))-1)) +
                        (KEY_GAP_SPACE * (letterIndex - (int)(Math.ceil(word.length() / 2)))) + KEY_WIDTH / 2;
            }
        }
        spawnY = WORD_START_Y;
        System.out.println("Spawning " + letter + " at X: " + spawnX +", Y: " + spawnY);
        spawn(letter, spawnX, spawnY);
    }

    public void removeKeyboardLetter(String letterToRemove){
        for(EntityType entityType : EntityType.values()) {
            if ((entityType != EntityType.GAMEOVER) && (entityType != EntityType.UNDERLINE) && (entityType != EntityType.HANGMAN)
                    && (entityType != EntityType.HANGMAN1) && (entityType != EntityType.HANGMAN2) && (entityType != EntityType.HANGMAN3)
                    && (entityType != EntityType.HANGMAN4) && (entityType != EntityType.HANGMAN5) && (entityType != EntityType.HANGMAN6)) {

                if (letterToRemove.equals(entityType.toString().toLowerCase(Locale.ROOT))) {
                    getGameWorld().getSingleton(entityType).removeFromWorld();
                }
            }
        }
    }

    @Override
    protected void initSettings(GameSettings settings) {
        System.out.println("initSettings: " + functionOrder);
        functionOrder++;
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("hangman");
        settings.setVersion("0.1");
        //settings.setAppIcon();

        try {
            this.readFile();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    @Override
    protected void initInput() {
        System.out.println("initInput: " + functionOrder);
        functionOrder++;
        onKeyDown(KeyCode.A, () -> {
            lookForMatches('a');
            //String code = KeyCode.A.getChar();
            //System.out.println(code);
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

        onBtnDown(MouseButton.PRIMARY, () -> {
            // hold the value of the mouse click location
            Point2D mouse = getInput().getMousePositionUI();
            double mouseX = mouse.getX();
            double mouseY = mouse.getY();
            if (mouseY < 400){
                return;
            }

            // loop through entity types and if one is located at the location of the mouse click, remove it from the game world.
            for(EntityType entityType : EntityType.values()) {
                if((entityType != EntityType.GAMEOVER) && (entityType != EntityType.UNDERLINE) && (entityType != EntityType.HANGMAN)
                        && (entityType != EntityType.HANGMAN1) && (entityType != EntityType.HANGMAN2) && (entityType != EntityType.HANGMAN3)
                        && (entityType != EntityType.HANGMAN4) && (entityType != EntityType.HANGMAN5) && (entityType != EntityType.HANGMAN6)) {
                    try {
                        double entityYTop = getGameWorld().getSingleton(entityType).getY();
                        double entityYBottom = getGameWorld().getSingleton(entityType).getBottomY();
                        double entityXLeft = getGameWorld().getSingleton(entityType).getX();
                        double entityXRight = getGameWorld().getSingleton(entityType).getRightX();

                        /*System.out.println("Entity Y Top: " + entityYTop + ". Entity Y Bottom: " + entityYBottom);
                        System.out.println("Entity X Left: " + entityXLeft + ". Entity X Right: " + entityXRight);

                        ViewComponent viewComponent = getGameWorld().getSingleton(entityType).getViewComponent();
                        System.out.println("View component before removal: " + viewComponent);*/

                        if (((mouseX >= entityXLeft) && (mouseX <= entityXRight)) && ((mouseY >= entityYTop) && (mouseY <= entityYBottom))) {
                            // remove selectable letter option
                            // getGameWorld().getSingleton(entityType).removeFromWorld();

                            // check to see if letter is in the word. Convert entity type to string, then a char, and pass it to lookForMatches()
                            String letterClicked = entityType.toString().toLowerCase(Locale.ROOT);
                            char letterToCheck = letterClicked.charAt(0);
                            lookForMatches(letterToCheck);
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
        System.out.println("initGameVars: " + functionOrder);
        functionOrder++;
        /*vars.put("pixelsMoved", 0);
        vars.put("incorrectGuesses", 0);*/

        this.lettersGuessed = new String[27];
        this.guessesIndex = 0;
        this.incorrectGuesses = 0;
        this.correctGuesses = 0;
    }

    @Override
    protected void initGame() {
        System.out.println("initGame: " + functionOrder);
        functionOrder++;
        getGameScene().setBackgroundRepeat("hangmanBackground.png");
        getGameWorld().addEntityFactory(hangmanFactory);
        /*player = entityBuilder()
                .at(300, 300)
                .view(new Text("Hello"))
                .buildAndAttach();*/

        if(wordsAskedCounter < 10){
            hangman = spawn("hangman", 1029-(125/2), 225);
        }

        /*getGameWorld().selectedEntityProperty().addListener((o, oldEntity, newEntity) -> {
            if (newEntity != null)
                getGameWorld().removeEntity(newEntity);
        });*/


        try {
            this.readFile();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        if (wordsAskedCounter == 10){
            {
                System.out.println("End Game");
            /*showMessage("Game Complete", () -> {
                System.exit(0);
            });*/
                //spawn("gameover", 300, 200);
                //GameApplication.exit();
                //System.exit(0);
            }
        }

        if (wordsAskedCounter < 10) {
            Random random = new Random();
            int randomNumber = random.nextInt(wordList.length);
            word = wordList[randomNumber];
            System.out.println("Original Word: " + word);

            for (int i = 0; i < wordsAsked.length; i++) {
                System.out.println("Word to search: " + word + ". Word in wordsAsked: " + wordsAsked[i]);
                if (word.equals(wordsAsked[i])) {
                    randomNumber = random.nextInt(wordList.length);
                    word = wordList[randomNumber];
                    System.out.println("New word to check: " + word);
                    i = -1;
                }
            }

            wordsAsked[wordsAskedCounter] = word;
            wordsAskedCounter++;

            System.out.println(wordsAskedCounter);
            System.out.println("Random Number: " + randomNumber + ". Word: " + this.word);

            int KEY_BOARD_DISPLAY_START_X = 485;
            int KEY_BOARD_DISPLAY_START_Y = 450;
            int spawnX;
            int spawnY;
            int numAwayFromMiddle;

            String[][] keyboardLayout = {{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"}, {"a", "s", "d", "f", "g", "h", "j", "k", "l"},{"z", "x", "c", "v", "b", "n", "m"}};
            for (EntityType entityType : EntityType.values()){
                if((entityType != EntityType.GAMEOVER) && (entityType != EntityType.UNDERLINE) && (entityType != EntityType.HANGMAN)
                        && (entityType != EntityType.HANGMAN1) && (entityType != EntityType.HANGMAN2) && (entityType != EntityType.HANGMAN3)
                        && (entityType != EntityType.HANGMAN4) && (entityType != EntityType.HANGMAN5) && (entityType != EntityType.HANGMAN6)){
                    String type = entityType.name().toLowerCase();

                    for (int i = 0; i < keyboardLayout.length; i++){
                        for(int j = 0; j < keyboardLayout[i].length; j++){
                            if (keyboardLayout[i][j].equals(type)) {
                                // if there is an even number of keys
                                if (keyboardLayout[i].length % 2 == 0) {
                                    if (i < keyboardLayout[i].length / 2) {
                                        numAwayFromMiddle = (keyboardLayout[i].length / 2) - j;

                                        spawnX = KEY_BOARD_DISPLAY_START_X - ((numAwayFromMiddle * KEY_WIDTH) +
                                                (KEY_GAP_SPACE * (numAwayFromMiddle - 1) + KEY_GAP_SPACE / 2));

                                    } else {
                                        spawnX = KEY_BOARD_DISPLAY_START_X + (KEY_WIDTH * (j - keyboardLayout[i].length / 2)) +
                                                (KEY_GAP_SPACE * (j - keyboardLayout[i].length / 2));
                                    }
                                } else {
                                    numAwayFromMiddle = (int) (Math.ceil(keyboardLayout[i].length / 2)) - j;
                                    if (j == Math.ceil(keyboardLayout[i].length / 2)) {
                                        spawnX = KEY_BOARD_DISPLAY_START_X - KEY_WIDTH / 2;
                                    } else if (j < keyboardLayout[i].length / 2) {
                                        spawnX = KEY_BOARD_DISPLAY_START_X - (KEY_WIDTH / 2 + (numAwayFromMiddle * KEY_WIDTH) +
                                                (numAwayFromMiddle * KEY_GAP_SPACE));
                                    } else {
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


            int UNDERLINE_START_X = 485;
            int UNDERLINE_START_Y = 300;
            int underlineX;
            int underlineY;
            numAwayFromMiddle = 0;

            for(int i = 0; i < word.length(); i++){
                // if word has even number of letters
                if(word.length() % 2 == 0){
                    if (i < word.length() / 2){
                        numAwayFromMiddle = (word.length() / 2) - i;

                        // note: KEY_WIDTH and underline width will both be the same number
                        underlineX = UNDERLINE_START_X - ((numAwayFromMiddle * KEY_WIDTH) +
                                (KEY_GAP_SPACE * (numAwayFromMiddle - 1) + KEY_GAP_SPACE / 2));
                    } else {
                        underlineX = UNDERLINE_START_X + (KEY_WIDTH * (i - word.length() / 2)) +
                                (KEY_GAP_SPACE * (i - word.length() / 2)) + KEY_GAP_SPACE/2;
                    }
                } else {
                    numAwayFromMiddle = (int) (Math.ceil(word.length() / 2)) - i;
                    if (i == Math.ceil(word.length() / 2)) {
                        underlineX = UNDERLINE_START_X - KEY_WIDTH / 2;
                    } else if (i < word.length() / 2) {
                        underlineX = UNDERLINE_START_X - (KEY_WIDTH / 2 + (numAwayFromMiddle * KEY_WIDTH) +
                                (numAwayFromMiddle * KEY_GAP_SPACE));
                    } else {
                        underlineX = UNDERLINE_START_X + (KEY_WIDTH * (i - (int) (Math.ceil(word.length()/ 2))-1)) +
                                (KEY_GAP_SPACE * (i - (int) (Math.ceil(word.length() / 2)))) + KEY_WIDTH / 2;
                    }
                }
                underlineY = UNDERLINE_START_Y;
                underline = spawn("underline", underlineX, underlineY);
            }

        } else {
            System.out.println("End Game");
            /*showMessage("Game Complete", () -> {
                System.exit(0);
            });*/
            //spawn("gameover", 0, 0);
            getGameController().startNewGame();
            //GameApplication.exit();
            //System.exit(0);
        }
    }

    @Override
    protected void initUI() {
        System.out.println("initUI: " + functionOrder);
        functionOrder++;
        Text textPixels = new Text();
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100

        //textPixels.textProperty().bind(getWorldProperties().intProperty("incorrectGuesses").asString());

        //getGameScene().addUINode(textPixels); // add to the scene graph
    }

    public static void main(String[] args) {
        System.out.println("This is main");
        launch(args);
    }
}


