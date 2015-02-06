package com.cubegame.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Andrey on 26.09.2014.
 */
public class TextureActor extends Actor {
    private TextureRegion toDraw;
    private Texture toDrawTex;
    public int _i, _j, type;
    public TextureActor(TextureRegion toDraw, int i, int j, int t)
    {
        this.toDraw = toDraw;
        setSize(100,40);
        _i = i;
        _j = j;
        type = t;
    }

    public TextureActor(TextureRegion toDraw)
    {
        this.toDraw = toDraw;
        this.setSize(toDraw.getRegionWidth(), toDraw.getRegionHeight());
        _i = -1;
        _j = -1;
        type = -1;
    }

    public TextureActor(Texture toDraw)
    {
        this.toDrawTex = toDraw;
        _i = -1;
        _j = -1;
        type = -1;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        if (type == -1 && toDrawTex != null) {
            batch.setColor(getColor());
            batch.draw(toDrawTex,getX(),getY(),getWidth(),getHeight());
        }
        else {
            batch.setColor(getColor());
            batch.draw(toDraw,getX(),getY(),getWidth(),getHeight());
        }


    }
}
