package hangman;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.*;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

import java.util.Arrays;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HangmanApp extends GameApplication {
    private final HangmanFactory hangmanFactory = new HangmanFactory();
    private Entity a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
    private Entity player;
    private int xPlacement = 0;
    //private int yPlacement = getAppHeight() / 2 - 15;
    private int yPlacement = 0;
    public String word = "";
    private int letterImageWidth = 100; // 100px
    private int letterImageHeight = 100; // 100px
    private int underLineWidth = 80;
    private String[] lettersGuessed;
    private int guessesIndex = 0;
    private int incorrectGuesses;
    private int correctGuesses;
    private int wordsAskedCounter = 0;
    private String[] wordList = new String[10];
    private String[] wordsAsked = new String[10];

    public enum EntityType {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, UNDERLINE, GAMEOVER;
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
            Boolean isMatch = false;
            for (int i = 0; i < wordToCheck.length(); i++) {
                if (letter == wordToCheck.charAt(i)) {
                    isMatch = true;
                    System.out.println(letter + ": " + i);
                    setXYPlacements(i, 0);
                    spawn(letterToCheck, xPlacement, yPlacement);
                    correctGuesses++;
                    if (correctGuesses == wordToCheck.length()){
                        showMessage("You Win!", () -> {
                            getGameController().startNewGame();
                        });
                    }
                }
            }
            if (!isMatch){
                inc("incorrectGuesses", 1);
                incorrectGuesses++;
                if (incorrectGuesses == 6){
                    showMessage("You lose!", () -> {
                        getGameController().startNewGame();
                    });
                }
            }


            //inc("lettersGuessed", 1);
            lettersGuessed[guessesIndex] = letterToCheck;
            guessesIndex++;
        }

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

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1080);
        settings.setHeight(720);
        settings.setTitle("hangman");
        settings.setVersion("0.1");

        try {
            this.readFile();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    @Override
    protected void initInput() {
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


    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
        vars.put("incorrectGuesses", 0);

        this.lettersGuessed = new String[27];
        this.guessesIndex = 0;
        this.incorrectGuesses = 0;
        this.correctGuesses = 0;
    }


    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(hangmanFactory);
        /*player = entityBuilder()
                .at(300, 300)
                .view(new Text("Hello"))
                .buildAndAttach();*/


        try {
            this.readFile();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        Random random = new Random();
        int randomNumber = random.nextInt(wordList.length);
        word = wordList[randomNumber];
        System.out.println("Original Word: " + word);

        if (wordsAskedCounter < 10) {
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

            System.out.println("Random Number: " + randomNumber + ". Word: " + this.word);

            for(int i = 0; i < word.length(); i++){
                xPlacement = ((underLineWidth + 20) * (i)) + 10;
                spawn("underline", xPlacement, yPlacement + 100);

            }

        } else {
            System.out.println("End Game");
            /*showMessage("Game Complete", () -> {
                System.exit(0);
            });*/
            spawn("gameover", 0, 0);
        }
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100

        textPixels.textProperty().bind(getWorldProperties().intProperty("incorrectGuesses").asString());

        getGameScene().addUINode(textPixels); // add to the scene graph
    }

    public static void main(String[] args) {
        launch(args);
    }
}


