package com.cubegame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Andrey on 26.09.2014.
 */
public class GameScreen implements Screen {
    private TextureActor menuButton;
    private Stage stage;

    class GoToMenuListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            BlocksGame.getInstance().showMenu();
        }
    }

    public GameScreen() {
        menuButton = new TextureActor(new Texture("badlogic.jpg"));
        menuButton.addListener(new GoToMenuListener());
        stage = new Stage();
        stage.addActor(menuButton);
    }

    @Override
    public void render(float delta) {
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
}
