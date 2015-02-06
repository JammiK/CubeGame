package com.cubegame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import java.awt.*;

/**
 * Created by Andrey on 26.09.2014.
 */
public class GameScreen implements Screen {
    private TextureActor menuButton;
    private Stage stage;
    private TextureRegion[] mapTank;
    private Texture quadTexture;

    Point[][] GameSpace = new Point[5][5];

    class GoToMenuListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            //BlocksGame.getInstance().showMenu();
            System.out.println("Go UP");
            generate();
        }
    }



    public GameScreen() {


        //menuButton = new TextureActor(new Texture("badlogic.jpg"), -1, -1, -1);
        //menuButton.addListener(new GoToMenuListener());
        stage = new Stage();
        loadBackground();
        quadTexture = new Texture("img/quads.png");
        mapTank = new TextureRegion[5];
        for (int i = 1; i < 6; i++) {
            mapTank[i - 1] = new TextureRegion(new Texture("img/icons/icon-" + i + ".png"), 0, 0, 127, 128);
        }
        //stage.addActor(menuButton);
        InitSpace();

        generate();
        System.out.println("Go UP");


    }


    @Override
    public void render(float delta) {
        if (BlocksGame.getInstance().getUp() == 1) {
            up();
            System.out.println("Go UP");
        } else if (BlocksGame.getInstance().getLeft() == 1) {
            left();
            System.out.println("Go LEFT");
        } else if (BlocksGame.getInstance().getRight() == 1) {
            right();
            System.out.println("Go RIGHT");
        } else if (BlocksGame.getInstance().getDown() == 1) {
            down();
            System.out.println("Go DOWN");
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    TextureActor getActorFromStage(int i, int j) {
        Array<Actor> actors = stage.getActors();
        for (int k = 0; k < actors.size; k++) {
            if (actors.get(k).getClass().getName() == "com.cubegame.game.TextureActor") {
                TextureActor act = (TextureActor) actors.get(k);
                if (act._i == i && act._j == j) {
                    return act;
                }
            }
        }
        return null;
    }

    private void generate() {
        checkOneColor();
        int x = MathUtils.random(0, 4);
        int y = MathUtils.random(0, 4);
        while (getActorFromStage(x,y) != null) {
            x = MathUtils.random(0, 4);
            y = MathUtils.random(0, 4);
        }
        int type = MathUtils.random(0, 3);
        TextureActor act = new TextureActor(mapTank[type], x, y, type);
        act.setX((float)GameSpace[x][y].getX());
        act.setY((float)GameSpace[x][y].getY());
        int size = Gdx.graphics.getWidth() / 6;
        act.setSize(size,size);
        AlphaAction alphaAction = Actions.fadeIn(0.3f);
        act.addAction(Actions.sequence(Actions.fadeOut(0f),alphaAction));
        act.addListener(new GoToMenuListener());
        stage.addActor(act);
        checkOneColor();
    }

    void InitSpace() {
        int size = Gdx.graphics.getWidth() / 6;
        int start = size / 2;
        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                GameSpace[i][j] = new Point(start + i * size + 2, start + j * size + 2);
            }
        }
    }

    void moveActorTo(final TextureActor actor, int i, int j) {
        actor._i = i;
        actor._j = j;
        MoveToAction action = new MoveToAction();
        action.setPosition((float)GameSpace[i][j].getX(), (float)GameSpace[i][j].getY());
        action.setDuration(0.1f);
        actor.addAction(action);
        System.out.println(stage.getActors().size);
    }

    private void checkOneColor() {
        for (int i = 0; i < 5; i++) { //horizontal
            int points = 1;
            int end = 0;
            for (int j = 1; j < 5; j++) {
                if (getActorFromStage(i, j) != null && getActorFromStage(i, j - 1) != null
                        && getActorFromStage(i, j).type == getActorFromStage(i, j - 1).type) {
                    points++;
                    end = j;
                } else {
                    if (points < 3) {
                        points = 1;
                    }
                }
            }
            if (points >= 3) {
                for (int k = end; k > end - points; k--) {
                    deleteActor(getActorFromStage(i,k));
                }
            }
            points = 1;
        }
        for (int j = 0; j < 5; j++) { // vertical
            int points = 1;
            int end = 0;
            for (int i = 1; i < 5; i++) {
                if (getActorFromStage(i, j) != null && getActorFromStage(i - 1, j) != null
                        && getActorFromStage(i, j).type == getActorFromStage(i - 1, j).type) {
                    points++;
                    end = i;
                } else {
                    if (points < 3) {
                        points = 1;
                    }
                }
            }
            if (points >= 3) {
                for (int k = end; k > end - points; k--) {
                    deleteActor(getActorFromStage(k,j));
                }
            }
            points = 1;
        }
    }

    void deleteActor(final TextureActor actor) {
        actor.addAction(Actions.sequence(Actions.fadeOut(0.25f), Actions.run(new Runnable() {
            public void run() {
                actor.remove();
            }
        })));
    }

    private void loadBackground() {
        TextureActor OrangeBack = new TextureActor(new TextureRegion(new Texture("img/OrangeBack.png"), 0, 0, 1, 1));
        OrangeBack.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        TextureActor GreyBack = new TextureActor(new TextureRegion(new Texture("img/GreyBack.png"), 0, 0, 1, 1));
        GreyBack.setSize(Gdx.graphics.getWidth() - 4, Gdx.graphics.getHeight() - 72);
        GreyBack.setX(2);
        GreyBack.setY(2);
        Texture ResultWindow = new Texture("img/win2.png");
        TextureActor ResActor = new TextureActor(new TextureRegion(ResultWindow, 0, 0, ResultWindow.getWidth(), ResultWindow.getHeight()));
        ResActor.setX(Gdx.graphics.getWidth() / 100 * 2);
        ResActor.setY( Gdx.graphics.getHeight() - ResActor.getHeight() - 75);
        ResActor.setSize(Gdx.graphics.getWidth() / 100 * 57, ResActor.getHeight());

        Texture BestWindow = new Texture("img/win2.png");
        TextureActor BestActor = new TextureActor(new TextureRegion(BestWindow, 0, 0, BestWindow.getWidth(), BestWindow.getHeight()));
        BestActor.setX(Gdx.graphics.getWidth() / 100 * 61);
        BestActor.setY( Gdx.graphics.getHeight() - BestActor.getHeight() - 75);
        BestActor.setSize(Gdx.graphics.getWidth() / 100 * 57, BestActor.getHeight());


        stage.addActor(OrangeBack);
        stage.addActor(GreyBack);
        stage.addActor(ResActor);
        stage.addActor(BestActor);
    }

    private void left() {
        boolean flag = false;
        for (int j = 0; j < 5; j++) {
            int space = 0;
            for (int i = 0; i < 5; i++) {
                TextureActor actor = getActorFromStage(i, j);
                if (actor == null) {
                    space++;
                } else if (space > 0) {
                    moveActorTo(actor, i - space, j);
                    flag = true;
                }
            }
        }
        if (flag) {
            generate();
        }
    }
    private void up() {
        boolean flag = false;
        for (int i = 0; i < 5; i++) {
            int space = 0;
            for (int j = 5 - 1; j >= 0; j--) {
                TextureActor actor = getActorFromStage(i, j);
                if (actor == null) {
                    space++;
                } else if (space > 0) {
                    moveActorTo(actor,i, j + space);

                    flag = true;
                }
            }
        }
        if (flag) {
            generate();
        }
    }
    private void right() {
        boolean flag = false;
        for (int j = 0; j < 5; j++) {
            int space = 0;
            for (int i = 5 - 1; i >= 0; i--) {
                TextureActor actor = getActorFromStage(i, j);
                if (actor == null) {
                    space++;
                } else if (space > 0) {
                    moveActorTo(actor, i + space, j);
                    flag = true;
                }
            }
        }
        if (flag) {
            generate();
        }
    }
    private void down() {
        boolean flag = false;
        for (int i = 0; i < 5; i++) {
            int space = 0;
            for (int j = 0; j < 5; j++) {
                TextureActor actor = getActorFromStage(i,j);
                if (actor == null) {
                    space++;
                } else if (space > 0) {
                    moveActorTo(actor, i, j - space);
                    flag = true;
                }
            }
        }
        if (flag) {
            generate();
        }
    }

}

