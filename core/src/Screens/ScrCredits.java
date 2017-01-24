package Screens;

import Menu.GamGame1;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrCredits extends ApplicationAdapter implements Screen, InputProcessor {

    SpriteBatch batch;
    Sprite spr, spBack;
    GamGame1 game;
    float fMouseX, fMouseY;
    float nX, nY;
//    int fMouseX, fMouseY;

    public ScrCredits(GamGame1 _game) {
        game = _game;
    }

    @Override
    public void create() {
    }

    @Override
    public void render() {
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        spr = new Sprite(new Texture("Credits.png"));
        spBack = new Sprite(new Texture("LEFT.png"));
        spBack.setSize(100, 100);
        spBack.setPosition(0, Gdx.graphics.getHeight() - spBack.getHeight());
        spr.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spr.setPosition(0, 0);
        Gdx.input.setInputProcessor(this);
        nX = spBack.getX();
        nY = spBack.getY();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        spr.draw(batch);
        spBack.draw(batch);
        batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void hide() {
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
        if (button == Input.Buttons.LEFT && Gdx.input.getX() >= nX
                && Gdx.input.getX() <= (nX + spBack.getWidth()) && nY >= spBack.getY()
                && Gdx.input.getY() <= (nY + spBack.getHeight())) {
//                    nButtonMenuPress = 5;
            System.out.println("LOL");
            game.nScreen = 5;
            game.updateState();

        }
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
