package com.cubegame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Andrey on 28.09.2014.
 */
public class MenuScreen implements Screen {
    private TextureActor gameButton;
    private Stage stage;

    //Обработчик нажатий на наши актеры. Вы еще незнакомы с этим классом, поэтоу особо не заморачивайтесь
    class GoToGameListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            BlocksGame.getInstance().showGame(); //Кликнули на кнопку - перешли в экран игры
            System.out.println("Go12 UP");
        }
    }

    public MenuScreen() {
        //gameButton = new TextureActor(new Texture("badlogic.jpg"), -1, -1, -1); // Создаем кнопку
        //gameButton.setPosition(200, 200); //Располагаем ее в нужном месте
//        gameButton.addListener(new GoToGameListener()); //Устанавливаем на нее обработчик
    //    gameButton.addCaptureListener(new GoToGameListener());
        stage = new Stage(); //Делаем сцену
        BlocksGame.getInstance().showGame();
//        stage.addActor(gameButton); //добавляем на нее актера
    }

    // Здесь идет отрисовка экрана
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Очистка экрана

        //Отрисовка сцены
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void hide() {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void dispose() {}
}
