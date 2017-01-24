/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import Menu.GamGame1;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Your Name <David>
 */
public class ScrEndScreen implements Screen, InputProcessor {

    private BitmapFont font, font2;
    private SpriteBatch batch;
    ScrSongOne songone;
    GamGame1 game;
    float fEff = 0;

    public ScrEndScreen(GamGame1 _game) {
        this.game = _game;
    }

    @Override
    public void show() {
        songone = new ScrSongOne(game);
        font = new BitmapFont();
        font2 = new BitmapFont();
        batch = new SpriteBatch();
        font2.setColor(Color.RED);
        fEff = ((songone.fGood - 1) / songone.nJ) * 100;
        //Gdx.input.setInputProcessor(this);
    }
//fYMid, fEff, fGood, nJ

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        font.setColor(Color.WHITE);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        batch.begin();
        font.draw(batch, "You clicked correctly " + String.valueOf(songone.fGood - 1) + " times out of " + String.valueOf(songone.nJ), 250, songone.fYMid + 100);
        font.draw(batch, "Your efficiency was " + String.valueOf(fEff) + "%", 250, songone.fYMid);
        font.draw(batch, "Press Escape to Exit", 250, songone.fYMid - 200);
        batch.end();
//        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
//            game.nScreen = 3;
//            game.updateState();
//            dispose();
//        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        dispose();
    }

    @Override
    public void dispose() {
    }

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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
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