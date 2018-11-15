package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Wall {
    class WallPair {
        Vector2 position;
        float speed;
        int offset;
        Rectangle emptySpace;

        public WallPair(Vector2 pos) {
            this.position = pos;
            this.speed = 2;
            this.offset = new Random().nextInt(250);
            emptySpace = new Rectangle(position.x, position.y - offset + 300, 50, betweenDistance);
        }

        public void update(){
            position.x -= speed;
            if (position.x < -50){
                position.x = 800;
                this.offset = new Random().nextInt(250);
            }
            emptySpace.x = position.x;
        }
    }

    static WallPair[] obs;
    Texture tx;
    int betweenDistance;

    public Wall() {
        this.tx = new Texture("wall.png");
        this.obs = new WallPair[4];
        this.betweenDistance = 250;
        int startPositionX = 400;
        for (int i = 0; i < obs.length; i++) {
            this.obs[i] = new WallPair(new Vector2(startPositionX, 0));
            startPositionX += 220;
        }
    }

    public void update() {
        for (int i = 0; i < obs.length; i++) {
            obs[i].update();

        }
    }

    public void render(SpriteBatch spriteBatch) {
        for (int i = 0; i < obs.length; i++) {
            spriteBatch.draw(tx, obs[i].position.x, obs[i].position.y - obs[i].offset);
            spriteBatch.draw(tx, obs[i].position.x, obs[i].position.y + betweenDistance + tx.getHeight() - obs[i].offset);
        }
    }

    public void recreate(){
        int startPositionX = 400;
        for (int i = 0; i < obs.length; i++) {
            obs[i] = new WallPair(new Vector2(startPositionX, 0));
            startPositionX += 220;
        }
    }
}
