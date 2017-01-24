package Screens;

//import Menu.GamGame1;
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

public class ScrMainMenuBox extends ApplicationAdapter implements InputProcessor, Screen {

    GamGame1 game;
    SpriteBatch batch;
    Texture img[] = new Texture[3];
    Sprite arsprSongBox[] = new Sprite[3];
    //int
    int nMoved, nButtonMenuPress = 0;
    //float
    float fSizeX, fSizeY, fCentre, fDivisor[] = new float[3], arfPosOrigin[] = new float[3];
    float fPosX[] = new float[3];
    float fPosY[] = new float[3], fMouseX, fMove, fMouseY;
    //bools
    boolean bMove = false, bClick = false;

    public ScrMainMenuBox(GamGame1 _game) {
        game = _game;
    }
//    public ScrMainMenuBox(GamGame1 aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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
        if (!bMove) {
            bClick = true;
            fMouseX = Gdx.input.getX();
            fMouseY = Gdx.input.getY();
            System.arraycopy(fPosX, 0, arfPosOrigin, 0, 3);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX + ": Screen x");
        System.out.println(screenY + ": Screen y");

        if (!bMove && bClick) {
            for (int i = 0; i < 3; i++) {
                if (button == Input.Buttons.LEFT && fMouseX >= fPosX[i]
                        && fMouseX <= (fPosX[i] + arsprSongBox[i].getWidth()) && fMouseY >= fPosY[i]
                        && fMouseY <= (fPosY[i] + arsprSongBox[i].getHeight())) {
                    nButtonMenuPress = i;
                    game.nScreen = i;
                    System.out.println(i);
            
                    game.updateState();
                    

                }
            }
        }
        bMove = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        bMove = true;
        bClick = false;
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
    public void show() {
        //set basics
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        //populate images to be used for the sprite

        for (int i = 0; i < 3; i++) {
            img[i] = new Texture("MenuSongButton" + i + ".png");
        }

//        img[1] = new Texture("MenuSongButton2.png");
//        img[2] = new Texture("MenuSongButton3.png");
        //populate the Sprites for the buttons
        for (int i = 0; i < 3; i++) {
            arsprSongBox[i] = new Sprite(img[i]);
        }
        //varriables to us to
        fCentre = Gdx.graphics.getWidth() / 2 - arsprSongBox[0].getWidth();


        fPosX[1] = Gdx.graphics.getWidth() / 2 - arsprSongBox[1].getWidth() / 2;
        fPosX[0] = Gdx.graphics.getWidth() / 2 - arsprSongBox[0].getWidth() / 2 - arsprSongBox[1].getWidth();
        fPosX[2] = Gdx.graphics.getWidth() / 2 - arsprSongBox[2].getWidth() / 2 + arsprSongBox[1].getWidth();
//        System.out.println("0 - 1: " + (fPosX[1] - fPosX[0]) + " 1-2: " + (fPosX[2]-fPosX[1]));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Divisor to create an effect for the menu select screen

        for (int i = 0; i < 3; i++) {
            fPosY[i] = Gdx.graphics.getHeight() / 2 - ((arsprSongBox[i].getHeight() / fDivisor[i]) / 2);

            fDivisor[i] = ((fCentre / 100 - fPosX[i] / 100) + 1);
            if (fDivisor[i] <= 0) {
                fDivisor[i] = fDivisor[i] * -1;
            }
            if (fDivisor[i] < 1 && fDivisor[i] > 0) {
                fDivisor[i] = 1;
            }
        }
//        System.out.println("1: " + (fPosY[0] + arsprSongBox[0].getHeight()/2) + " 2: " + (fPosY[1] + arsprSongBox[1].getHeight()/2) + "3: " + (fPosY[2] + arsprSongBox[2].getHeight()/2));
//        System.out.println("1: " + fDivisor[0] + " 2: " + fDivisor[1] + " 3: " + fDivisor[2]);

        //draw button sprites
        batch.begin();
        for (int i = 0; i < 3; i++) {
            batch.draw(arsprSongBox[i], fPosX[i], fPosY[i], arsprSongBox[i].getWidth() / fDivisor[i], arsprSongBox[i].getHeight() / fDivisor[i]);        //x,y,w,h
        }
        batch.end();
        //Position of the buttons move while mouse is dragged
        if (bMove) {
            fMove = Gdx.input.getX() - fMouseX;
            for (int i = 0; i < 3; i++) {
                fPosX[i] = arfPosOrigin[i] + fMove;
            }
        } else if (!bMove) {
            nMoved = 0;
        }
//        System.out.println(nButtonMenuPress);
//                System.out.println(bMove);

    }

    @Override
    public void hide() {
    }
}
