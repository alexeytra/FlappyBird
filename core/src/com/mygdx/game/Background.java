package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

class Background {
    class BGPicture {
        private Texture tx;
        private Vector2 pos;

        BGPicture(Vector2 pos){
            tx = new Texture("back.png");
            this.pos = pos;
        }
    }

    private int speed = 4;
    private BGPicture[] bgPicturesBacks;

    Background(){
        bgPicturesBacks = new BGPicture[2];
        bgPicturesBacks[0] = new BGPicture(new Vector2(0, 0));
        bgPicturesBacks[1] = new BGPicture(new Vector2(800, 0));
    }

    void render(SpriteBatch batch){
        for (BGPicture bgPicturesBack : bgPicturesBacks) {
            batch.draw(bgPicturesBack.tx, bgPicturesBack.pos.x, bgPicturesBack.pos.y);
        }

    }

    void update() {
        for (BGPicture bgPicturesBack : bgPicturesBacks) {
            bgPicturesBack.pos.x -= speed;
        }
        if (bgPicturesBacks[0].pos.x < -800){
            bgPicturesBacks[0].pos.x = 0;
            bgPicturesBacks[1].pos.x = 800;

        }
    }
}
