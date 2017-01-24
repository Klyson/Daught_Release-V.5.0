package Screens;

import Menu.GamGame1;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrInstructions extends ApplicationAdapter implements Screen, InputProcessor {

    SpriteBatch batch;
    BitmapFont font;
    private Sprite spr, spBack;
    GamGame1 game;
    float fMouseX, fMouseY;
    float nX, nY;
//    int fMouseX, fMouseY;

    public ScrInstructions(GamGame1 _game) {
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
        font = new BitmapFont();
        spr = new Sprite(new Texture("Dance^4_Instructions.png"));
        spBack = new Sprite(new Texture("LEFT.png"));
        spBack.setSize(100, 100);
        spBack.setPosition(0, Gdx.graphics.getHeight() - spBack.getHeight());
        spr.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spr.setPosition(Gdx.graphics.getWidth() / 2, 0);
        Gdx.input.setInputProcessor(this);
        nX = spBack.getX();
        nY = spBack.getY();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //spr.draw(batch);
        font.draw(batch, "Hi! Welcome to our game, Dancing Woot! Thanks so much for playing!", 110, Gdx.graphics.getHeight() - 50);
        font.draw(batch, "Click play to start the game. When in the second menu, type a number. This number", 97, Gdx.graphics.getHeight() - 90);
        font.draw(batch, "will be the amount of times you click during the game. Hit enter, you will begin", 20, Gdx.graphics.getHeight() - 130);
        font.draw(batch, "the main game. First, four colours appear on screen. Memorize the colour locations!", 20, Gdx.graphics.getHeight() - 170);
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
                   // nButtonMenuPress = 5;
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
