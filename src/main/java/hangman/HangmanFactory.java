package hangman;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import hangman.HangmanApp.*;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class HangmanFactory implements EntityFactory {

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



}
