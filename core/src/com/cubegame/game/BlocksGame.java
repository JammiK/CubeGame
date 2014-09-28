package com.cubegame.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Andrey on 28.09.2014.
 */
public class BlocksGame extends Game {
    private SpriteBatch batch; //Отрисовщик спрайтов. Он один на все экраны, больше и не нужно
    private MenuScreen menuScreen; // Экран меню
    private GameScreen gameScreen; //Игровой экран

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
        setScreen(menuScreen);
    }

    //Показываем меню
    public void showMenu() {
        setScreen(menuScreen);
    }

    //Показываем игровой экран
    public void showGame() {
        setScreen(gameScreen);
    }
}
