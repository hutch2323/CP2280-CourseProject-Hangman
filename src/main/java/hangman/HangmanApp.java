package hangman;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

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
    private String word = "Strawberry";
    private int letterImageWidth = 100; // 100px
    private int letterImageHeight = 100; // 100px
    private String[] lettersGuessed;
    private int guessesIndex;
    private int incorrectGuesses;
    private int correctGuesses;

    public enum EntityType {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
    }

    public void lookForMatches(char letter){
        String wordToCheck = word.toLowerCase();
        String letterToCheck = Character.toString(letter);
        int numberOfMatches = 0;
        /*for (String letterGuessed : lettersGuessed){

        }*/
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
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1080);
        settings.setHeight(720);
        settings.setTitle("hangman");
        settings.setVersion("0.1");
    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.A, () -> {
            //String text = player.
            //System.out.println(text);
            //player.translateX(5); // move right 5 pixels
            //inc("pixelsMoved", +5);
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
        player = entityBuilder()
                .at(300, 300)
                .view(new Text("Hello"))
                .buildAndAttach();
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


