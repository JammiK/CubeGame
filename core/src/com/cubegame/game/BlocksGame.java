package com.cubegame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Andrey on 28.09.2014.
 */
public class BlocksGame extends Game {
    private SpriteBatch batch; //Отрисовщик спрайтов. Он один на все экраны, больше и не нужно
    private MenuScreen menuScreen; // Экран меню
    private GameScreen gameScreen; //Игровой экран
    private int startX, startY, stopX, stopY;
    private int goUp, goLeft, goRight, goDown;

    public class MyInputProcessor implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            startX = screenX;
            startY = screenY;
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            stopX = screenX;
            stopY = screenY;

            if ((startX - stopX < -10) && (Math.abs(startX - stopX) > Math.abs(startY - stopY))) {
                goRight = 1;
                goDown = 0;
                goLeft = 0;
                goUp = 0;
            }
            if ((startX - stopX > 10) && (Math.abs(startX - stopX) > Math.abs(startY - stopY))) {
                goRight = 0;
                goDown = 0;
                goLeft = 1;
                goUp = 0;
            }
            if ((startY - stopY > 10) && (Math.abs(startX - stopX) < Math.abs(startY - stopY))) {
                goRight = 0;
                goDown = 0;
                goLeft = 0;
                goUp = 1;
            }
            if ((startY - stopY < -10) && (Math.abs(startX - stopX) < Math.abs(startY - stopY))) {
                goRight = 0;
                goDown = 1;
                goLeft = 0;
                goUp = 0;
            }

            startX = 0;
            stopX = 0;
            startY = 0;
            stopY = 0;

            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {

            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
    //Единственный экземляр
    private static BlocksGame instance = new BlocksGame();

    //Приватный конструктор
    private BlocksGame() {
    }

    //Только так мы можем получить доступ к нашему "ядру"
    public static BlocksGame getInstance() {
        return instance;
    }

    @Override
    public void create() {
        //Инициализируем отрисовщик спрайтов и экраны

        batch = new SpriteBatch();
        menuScreen = new MenuScreen();
        gameScreen = new GameScreen();
        //Показываем экран меню
        setScreen(gameScreen);
        Gdx.input.setInputProcessor(new InputAdapter(){

            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                startX = screenX;
                startY = screenY;
                return true;
            }

            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                stopX = screenX;
                stopY = screenY;

                if ((startX - stopX < -10) && (Math.abs(startX - stopX) > Math.abs(startY - stopY))) {
                    goRight = 1;
                    goDown = 0;
                    goLeft = 0;
                    goUp = 0;
                }
                if ((startX - stopX > 10) && (Math.abs(startX - stopX) > Math.abs(startY - stopY))) {
                    goRight = 0;
                    goDown = 0;
                    goLeft = 1;
                    goUp = 0;
                }
                if ((startY - stopY > 10) && (Math.abs(startX - stopX) < Math.abs(startY - stopY))) {
                    goRight = 0;
                    goDown = 0;
                    goLeft = 0;
                    goUp = 1;
                }
                if ((startY - stopY < -10) && (Math.abs(startX - stopX) < Math.abs(startY - stopY))) {
                    goRight = 0;
                    goDown = 1;
                    goLeft = 0;
                    goUp = 0;
                }

                startX = 0;
                stopX = 0;
                startY = 0;
                stopY = 0;

                return true;
            }
        });
    }

    //Показываем меню
    public void showMenu() {
        setScreen(menuScreen);
    }

    //Показываем игровой экран
    public void showGame() {
        setScreen(gameScreen);
    }

    public int getUp() {
        int x = goUp;
        goUp = 0;
        return x;
    }
    public int getRight() {
        int x = goRight;
        goRight = 0;
        return x;
    }
    public int getDown() {
        int x = goDown;
        goDown = 0;
        return x;
    }
    public int getLeft() {
        int x = goLeft;
        goLeft = 0;
        return x;
    }
}
