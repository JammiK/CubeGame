package com.cubegame.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Andrey on 26.09.2014.
 */
public class TextureActor extends Actor {
    private Texture toDraw;

    public TextureActor(Texture toDraw)
    {
        this.toDraw = toDraw;
        setSize(100,40);
        setPosition(100,100);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        batch.setColor(getColor());
        batch.draw(toDraw,getX(),getY(),getWidth(),getHeight());
    }
}
