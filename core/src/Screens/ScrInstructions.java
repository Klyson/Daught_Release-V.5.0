package Screens;

import Menu.GamGame1;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrInstructions /*extends ApplicationAdapter*/ implements Screen, InputProcessor {

    SpriteBatch batch;
    BitmapFont font;
    private Sprite ArSprs[] = new Sprite[7], spBack;
    GamGame1 game;
    boolean isSTAHP = false;
    float fMouseX, fMouseY;
    float fX, fY, fCount = 0;
    int i = 0, nChange = 0, nPrev;
//    int fMouseX, fMouseY;

    public ScrInstructions(GamGame1 _game) {
        game = _game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        for (int i = 0; i < 6; i++) {
            ArSprs[i] = new Sprite(new Texture("Inst" + i + ".png"));
            ArSprs[i].setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - 50);
            ArSprs[i].setPosition(0, 0);
        }
        spBack = new Sprite(new Texture("LEFT.png"));
        spBack.setSize(100, 100);
        spBack.setPosition(0, Gdx.graphics.getHeight() - 100);
        Gdx.input.setInputProcessor(this);
        fX = spBack.getX();
        fY = spBack.getY();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        ArSprs[i].draw(batch);
        if (nChange > nPrev && i < 5) {
            i++;
        } else if (nChange < nPrev && i > 0) {
            i--;
        }
        if (isSTAHP) {
            font.draw(batch, "PAUSE!", (Gdx.graphics.getWidth() - 50) / 2, (Gdx.graphics.getHeight() / 2) + 155);
        }
        if (i == 0) {
            font.draw(batch, "Right arrow goes forward! Left arrow goes back! Welcome to our game! On", 145, Gdx.graphics.getHeight() - 5);
            font.draw(batch, "this screen you can see, 'Play' will let you begin!", 145, Gdx.graphics.getHeight() - 30);
        } else if (i == 1) {
            font.draw(batch, "This is the TypeRounds screen. If you click, it sets to zero.", 145, Gdx.graphics.getHeight() - 5);
            font.draw(batch, "The number represents the number of times you'll click during the game.", 145, Gdx.graphics.getHeight() - 30);
        } else if (i == 2) {
            font.draw(batch, "This screen shows you where the colours will be! Memorize them!", 145, Gdx.graphics.getHeight() - 5);
            font.draw(batch, "The next screen requires you memorized the locations to know where to click!", 145, Gdx.graphics.getHeight() - 30);
        } else if (i == 3) {
            font.draw(batch, "It's time for grey! Click the corner that represents the colour in the middle!", 145, Gdx.graphics.getHeight() - 5);
            font.draw(batch, "Have the locations memorized, and you'll know where to click!", 145, Gdx.graphics.getHeight() - 30);
        } else if (i == 4) {
            font.draw(batch, "If the font is red, the screen will change in half a second!", 145, Gdx.graphics.getHeight() - 5);
            font.draw(batch, "The pause is triggered with spacebar.", 145, Gdx.graphics.getHeight() - 30);
        } else if (i == 5) {
            font.draw(batch, "This is the end screen. It shows your percentage correct.", 145, Gdx.graphics.getHeight() - 5);
            font.draw(batch, "Also shows number of correct clicks VS times you clicked. Escape exits the game!", 120, Gdx.graphics.getHeight() - 30);
        }
//        font.draw(batch, "The first screen has three options! Click Play to play the game. Click Instructions", 97, Gdx.graphics.getHeight() - 90);
//        font.draw(batch, "To view the instructions! Great job, you've already done this! Click Credits to see", 20, Gdx.graphics.getHeight() - 130);
//        font.draw(batch, "our names (they're beautiful!). Click the back arrow to go back! Have fun!", 20, Gdx.graphics.getHeight() - 170);
        spBack.draw(batch);
        batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        nPrev = nChange;
    }

    @Override
    public void hide() {
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.RIGHT) {
            nChange++;
        } else if (keycode == Keys.LEFT) {
            nChange--;
        }
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
        if (button == Input.Buttons.LEFT && Gdx.input.getX() >= fX
                && Gdx.input.getX() <= (fX + spBack.getWidth()) && fY >= spBack.getY()
                && Gdx.input.getY() <= (fY + spBack.getHeight())) {
            fCount = 0;
            i = 0;
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
    public void dispose() {
    }
}
