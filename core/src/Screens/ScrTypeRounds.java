package Screens;

import Menu.GamGame1;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class ScrTypeRounds extends ApplicationAdapter implements InputProcessor, Screen, Input.TextInputListener {

    GamGame1 game;
    SpriteBatch batch;
    Texture img;
    BitmapFont textRounds;
    StringBuilder sb;
    public static String sRounds = "0";
    boolean bType = false, isKey;
    int nRounds;
    String sRoundAmount;

    public ScrTypeRounds(GamGame1 _game) {
        game = _game;
    }

    @Override
    public void create() {
    }

    @Override
    public void render() {
    }

    @Override
    public boolean keyDown(int i) {
        if (!bType) {
            sRounds = "";
            bType = true;
        }
//
//        if(Gdx.input.isKeyPressed(Input.Keys.TAB) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.HOME) || Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.F1) || Gdx.input.isKeyPressed(Input.Keys.F2) || Gdx.input.isKeyPressed(Input.Keys.F3) || Gdx.input.isKeyPressed(Input.Keys.F4) ||Gdx.input.isKeyPressed(Input.Keys.F5) ||Gdx.input.isKeyPressed(Input.Keys.F6) ||Gdx.input.isKeyPressed(Input.Keys.F7)  )
        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            sb = new StringBuilder(sRounds);
            sb.deleteCharAt((sRounds.length() - 1));
            sRounds = sb.toString();
//            sSub = sRounds.charAt(sRounds.length());
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_0) || Gdx.input.isKeyPressed(Input.Keys.NUM_1)
                || Gdx.input.isKeyPressed(Input.Keys.NUM_2) || Gdx.input.isKeyPressed(Input.Keys.NUM_3)
                || Gdx.input.isKeyPressed(Input.Keys.NUM_4) || Gdx.input.isKeyPressed(Input.Keys.NUM_5)
                || Gdx.input.isKeyPressed(Input.Keys.NUM_6) || Gdx.input.isKeyPressed(Input.Keys.NUM_7)
                || Gdx.input.isKeyPressed(Input.Keys.NUM_8) || Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
            sRounds += Input.Keys.toString(i);
        } else if ((Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)
                || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)
                || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_5) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)
                || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_7) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)
                || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0))) {
            sRounds += Integer.toString((i - 144));
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        isKey = true;
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        isKey = true;
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    @Override
    public void show() {
        FileHandle fontFile = Gdx.files.internal("arial.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        textRounds = generator.generateFont(parameter);
        parameter.size = 18;
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (!isKey) {
        textRounds.draw(batch, "Type Rounds Here", Gdx.graphics.getWidth() / 2, 250);
        } else {
        textRounds.draw(batch, sRounds, Gdx.graphics.getWidth() / 2, 250);    
        }
        batch.end();
        if (Gdx.input.isKeyPressed(Keys.ENTER)) {
            //nRounds = Integer.parseInt(sRounds);
            //System.out.println(nRounds);
            game.nScreen = 4;
            game.updateState();
            dispose();
        }
    }

    @Override
    public void hide() {
    }

    @Override
    public void input(String sRoundAmount) {
         this.sRounds = sRoundAmount;
    }

    @Override
    public void canceled() {
        
    }
}
