package hangman;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import hangman.HangmanApp.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class HangmanFactory implements EntityFactory {

    // code used to handle all the entities to be spawned while running the application

    @Spawns("hangman")
    public Entity Hangman(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.HANGMAN)
                .viewWithBBox("hangman0.png")
                .build();
    }

    @Spawns("hangman1")
    public Entity Hangman1(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(EntityType.HANGMAN1)
                .viewWithBBox("hangman1.png")
                .build();
    }

    @Spawns("hangman2")
    public Entity Hangman2(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(EntityType.HANGMAN2)
                .viewWithBBox("hangman2.png")
                .build();
    }

    @Spawns("hangman3")
    public Entity Hangman3(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(EntityType.HANGMAN3)
                .viewWithBBox("hangman3.png")
                .build();
    }

    @Spawns("hangman4")
    public Entity Hangman4(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(EntityType.HANGMAN4)
                .viewWithBBox("hangman4.png")
                .build();
    }

    @Spawns("hangman5")
    public Entity Hangman5(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(EntityType.HANGMAN5)
                .viewWithBBox("hangman5.png")
                .build();
    }

    @Spawns("hangman6")
    public Entity Hangman6(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(EntityType.HANGMAN6)
                .viewWithBBox("hangman6.png")
                .build();
    }


    @Spawns("gameover")
    public Entity GameOver(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(HangmanApp.EntityType.UNDERLINE)
                .viewWithBBox("gameOver.png")
                .build();
    }

    @Spawns("underline")
    public Entity Underline(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(HangmanApp.EntityType.UNDERLINE)
                .viewWithBBox(new Rectangle(65, 5, Color.BLACK))
                .build();
    }

    @Spawns("a")
    public Entity newA(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(HangmanApp.EntityType.A)
                .viewWithBBox("a.png")
                .build();
    }

    @Spawns("b")
    public Entity newB(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.B)
                .viewWithBBox("b.png")
                .build();
    }

    @Spawns("c")
    public Entity newC(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.C)
                .viewWithBBox("c.png")
                .build();
    }

    @Spawns("d")
    public Entity newD(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.D)
                .viewWithBBox("d.png")
                .build();
    }

    @Spawns("e")
    public Entity newE(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.E)
                .viewWithBBox("e.png")
                .build();
    }

    @Spawns("f")
    public Entity newF(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.F)
                .viewWithBBox("f.png")
                .build();
    }

    @Spawns("g")
    public Entity newG(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.G)
                .viewWithBBox("g.png")
                .build();
    }

    @Spawns("h")
    public Entity newH(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.H)
                .viewWithBBox("h.png")
                .build();
    }

    @Spawns("i")
    public Entity newI(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.I)
                .viewWithBBox("i.png")
                .build();
    }

    @Spawns("j")
    public Entity newJ(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.J)
                .viewWithBBox("j.png")
                .build();
    }

    @Spawns("k")
    public Entity newK(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.K)
                .viewWithBBox("k.png")
                .build();
    }

    @Spawns("l")
    public Entity newL(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.L)
                .viewWithBBox("l.png")
                .build();
    }

    @Spawns("m")
    public Entity newM(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.M)
                .viewWithBBox("m.png")
                .build();
    }

    @Spawns("n")
    public Entity newN(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.N)
                .viewWithBBox("n.png")
                .build();
    }

    @Spawns("o")
    public Entity newO(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.O)
                .viewWithBBox("o.png")
                .build();
    }

    @Spawns("p")
    public Entity newP(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.P)
                .viewWithBBox("p.png")
                .build();
    }

    @Spawns("q")
    public Entity newQ(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.Q)
                .viewWithBBox("q.png")
                .build();
    }

    @Spawns("r")
    public Entity newR(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.R)
                .viewWithBBox("r.png")
                .build();
    }

    @Spawns("s")
    public Entity newS(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.S)
                .viewWithBBox("s.png")
                .build();
    }

    @Spawns("t")
    public Entity newT(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.T)
                .viewWithBBox("t.png")
                .build();
    }

    @Spawns("u")
    public Entity newU(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.U)
                .viewWithBBox("u.png")
                .build();
    }

    @Spawns("v")
    public Entity newV(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.V)
                .viewWithBBox("v.png")
                .build();
    }

    @Spawns("w")
    public Entity newW(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.W)
                .viewWithBBox("w.png")
                .build();
    }

    @Spawns("x")
    public Entity newX(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.X)
                .viewWithBBox("x.png")
                .build();
    }

    @Spawns("y")
    public Entity newY(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.Y)
                .viewWithBBox("y.png")
                .build();
    }

    @Spawns("z")
    public Entity newZ(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.Z)
                .viewWithBBox("z.png")
                .build();
    }

    // faded letters

    @Spawns("dark_a")
    public Entity darkA(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_A)
                .viewWithBBox("dark_a.png")
                .build();
    }

    @Spawns("dark_b")
    public Entity darkB(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_B)
                .viewWithBBox("dark_b.png")
                .build();
    }

    @Spawns("dark_c")
    public Entity darkC(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_C)
                .viewWithBBox("dark_c.png")
                .build();
    }

    @Spawns("dark_d")
    public Entity darkD(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_D)
                .viewWithBBox("dark_d.png")
                .build();
    }

    @Spawns("dark_e")
    public Entity darkE(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_E)
                .viewWithBBox("dark_e.png")
                .build();
    }

    @Spawns("dark_f")
    public Entity darkF(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_F)
                .viewWithBBox("dark_f.png")
                .build();
    }

    @Spawns("dark_g")
    public Entity darkG(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_G)
                .viewWithBBox("dark_g.png")
                .build();
    }

    @Spawns("dark_h")
    public Entity darkH(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_H)
                .viewWithBBox("dark_h.png")
                .build();
    }

    @Spawns("dark_i")
    public Entity darkI(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_I)
                .viewWithBBox("dark_i.png")
                .build();
    }

    @Spawns("dark_j")
    public Entity darkJ(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_J)
                .viewWithBBox("dark_j.png")
                .build();
    }

    @Spawns("dark_k")
    public Entity darkK(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_K)
                .viewWithBBox("dark_k.png")
                .build();
    }

    @Spawns("dark_l")
    public Entity darkL(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_L)
                .viewWithBBox("dark_l.png")
                .build();
    }

    @Spawns("dark_m")
    public Entity darkM(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_M)
                .viewWithBBox("dark_m.png")
                .build();
    }

    @Spawns("dark_n")
    public Entity darkN(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_N)
                .viewWithBBox("dark_n.png")
                .build();
    }

    @Spawns("dark_o")
    public Entity darkO(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_O)
                .viewWithBBox("dark_o.png")
                .build();
    }

    @Spawns("dark_p")
    public Entity darkP(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_P)
                .viewWithBBox("dark_p.png")
                .build();
    }

    @Spawns("dark_q")
    public Entity darkQ(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_Q)
                .viewWithBBox("dark_q.png")
                .build();
    }

    @Spawns("dark_r")
    public Entity darkR(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_R)
                .viewWithBBox("dark_r.png")
                .build();
    }

    @Spawns("dark_s")
    public Entity darkS(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_S)
                .viewWithBBox("dark_s.png")
                .build();
    }

    @Spawns("dark_t")
    public Entity darkT(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_T)
                .viewWithBBox("dark_t.png")
                .build();
    }

    @Spawns("dark_u")
    public Entity darkU(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_U)
                .viewWithBBox("dark_u.png")
                .build();
    }

    @Spawns("dark_v")
    public Entity darkV(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_V)
                .viewWithBBox("dark_v.png")
                .build();
    }

    @Spawns("dark_w")
    public Entity darkW(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_W)
                .viewWithBBox("dark_w.png")
                .build();
    }

    @Spawns("dark_x")
    public Entity darkX(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_X)
                .viewWithBBox("dark_x.png")
                .build();
    }

    @Spawns("dark_y")
    public Entity darkY(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_Y)
                .viewWithBBox("dark_y.png")
                .build();
    }

    @Spawns("dark_z")
    public Entity darkZ(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.DARK_Z)
                .viewWithBBox("dark_z.png")
                .build();
    }

    @Spawns("chalkboard")
    public Entity chalkBoard(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.CHALKBOARD)
                .viewWithBBox("chalkboard.png")
                .build();
    }

    @Spawns("score_0")
    public Entity score0(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE0)
                .viewWithBBox("0.png")
                .build();
    }

    @Spawns("score_1")
    public Entity score1(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE1)
                .viewWithBBox("1.png")
                .build();
    }

    @Spawns("score_2")
    public Entity score2(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE2)
                .viewWithBBox("2.png")
                .build();
    }

    @Spawns("score_3")
    public Entity score3(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE3)
                .viewWithBBox("3.png")
                .build();
    }

    @Spawns("score_4")
    public Entity score4(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE4)
                .viewWithBBox("4.png")
                .build();
    }

    @Spawns("score_5")
    public Entity score5(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE5)
                .viewWithBBox("5.png")
                .build();
    }

    @Spawns("score_6")
    public Entity score6(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE6)
                .viewWithBBox("6.png")
                .build();
    }

    @Spawns("score_7")
    public Entity score7(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE7)
                .viewWithBBox("7.png")
                .build();
    }

    @Spawns("score_8")
    public Entity score8(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE8)
                .viewWithBBox("8.png")
                .build();
    }

    @Spawns("score_9")
    public Entity score9(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE9)
                .viewWithBBox("9.png")
                .build();
    }

    @Spawns("score_10")
    public Entity score10(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.SCORE10)
                .viewWithBBox("10.png")
                .build();
    }

    @Spawns("exit")
    public Entity exit(SpawnData data){
        return entityBuilder()
                .from(data)
                .type(EntityType.EXIT)
                .viewWithBBox("exit.png")
                .build();
    }


}
