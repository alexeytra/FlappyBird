package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Bird bird;
	Wall walls;
	boolean gameOver;
	Texture restart;
	Integer personalScore = 0;
	private BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		bird = new Bird();
		walls = new Wall();
		gameOver = false;
		restart = new Texture("RestartBtn.png");
		font = new BitmapFont();
		font.setColor(Color.BLACK);

	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		walls.render(batch);
		if (!gameOver) {
			bird.render(batch);
		}else {
			batch.draw(restart, 200, 200);
		}

		font.draw(batch, "Score: " + personalScore.toString(), 370, 500);
		batch.end();
	}

	public void update() {
		background.update();
		bird.update();
		walls.update();
		boolean goThrough = false;
		for (int i = 0; i < Wall.obs.length; i++){
			if (bird.position.x > Wall.obs[i].position.x && bird.position.x < Wall.obs[i].position.x + 50){
				if (!Wall.obs[i].emptySpace.contains(bird.position)){
					gameOver = true;
				}else {
					goThrough = true;
				}
			}
		}

		if (goThrough) personalScore +=1;

		if (bird.position.y < 0 || bird.position.y > 600) {
			gameOver = true;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver){
			personalScore = 0;
			recreate();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void recreate(){
		bird.recreate();
		walls.recreate();
		gameOver = false;
	}
}
