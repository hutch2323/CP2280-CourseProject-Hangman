package tutorials.GeoWars;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class GeoWarsApp extends GameApplication{
    private final GeoWarsFactory geoWarsFactory = new GeoWarsFactory();
    private Entity player;

    public enum EntityType {
        PLAYER, BULLET, ENEMY
    }

    @Override
    protected void initInput(){
        onKey(KeyCode.UP, () -> player.translateY(-5));
        onKey(KeyCode.DOWN, () -> player.translateY(5));
        onKey(KeyCode.LEFT, () -> player.translateX(-5));
        onKey(KeyCode.RIGHT, () -> player.translateX(5));

        onBtnDown(MouseButton.PRIMARY, () -> spawn("bullet", player.getCenter()));
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Geometry Wars");
    }

    @Override
    protected void initGame(){
        getGameWorld().addEntityFactory(geoWarsFactory);

        player = spawn("player", getAppWidth() / 2 - 15, getAppHeight() / 2 - 15);

        run(() -> spawn("enemy"), Duration.seconds(1.0));
    }

    @Override
    protected void initPhysics(){
        onCollisionBegin(EntityType.BULLET, EntityType.ENEMY, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
        });

        onCollisionBegin(EntityType.ENEMY, EntityType.PLAYER, (enemy, player) -> {
            showMessage("You Died!", () -> {
                getGameController().startNewGame();
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

