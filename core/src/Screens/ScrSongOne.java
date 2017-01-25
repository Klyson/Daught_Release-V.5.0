package Screens;
//CURRENT

import Menu.GamGame1;
import static Screens.ScrSongOne.nCountSwitch;
import static Screens.ScrSongOne.nRand;

import Screens.ScrTypeRounds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class ScrSongOne extends InputAdapter implements Screen {

    GamGame1 game;
    ScrTypeRounds typerounds;
    private float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    private SpriteBatch batch, batchFonts;
    private Texture imgs[] = new Texture[4], imgsG[] = new Texture[4];
    private Sprite ArSprColours[] = new Sprite[4], ArSprGrey[] = new Sprite[4], spriteP;
    private boolean isCorrect, isExit, isCirc, isKeyChange, isClick, isDone, bCount = true, isJUp, isRand, isCont, isPause, isCol = true, isColPause = true, isColNoClick;
    private BitmapFont font;
    private Circle circ;
    ArrayList<Vector2> AlvShuffle = new ArrayList();
    private Vector2 vec1, vec2, vec3, vec4;
    private String ArsCols[] = new String[4];
    private Color ArcRandCol[] = new Color[4];
    ShapeRenderer shapeRenderer, shapeRendHud;
    private Rectangle recHUD;
    public static float fXMid, fYMid, fGood = 1/*number of correct clicks*/, fEff = 0/*% correct so far*/;
    public static int nJ = 0/**/, nTimeout = 0, /*when nTimeout == nMaxOut: change middle colour*/ nMaxOut = 90, nCount = 0, nNext, nCountSwitch = 0, nRand = 10, nJMax, nCountCol = 0, nClick = 0;
    //ArrayList<Rectangle> AlRandRect = new ArrayList();
    //0 = TL, 1 = TR, 2 = BL, 3 = BR. 
    //=========================TV INP=============================//
    int nGB = 0, nTextX, nTextY;
    Sprite sprDance;
    Sound sndSong1, sndEnd;
    Texture txSheet, txTemp, txOne;
    BitmapFont textGreat, textBad;
    Animation araniDance[];
    TextureRegion trTemp;
    int fW, fH, fSx, fSy;
    int nFrame, nPos, nTime, nTextCount;
    private boolean bDance = true;
    //=========================TV INP=============================//

    public ScrSongOne(GamGame1 _game) {
        this.game = _game;
    }

    @Override
    public void show() {
        fXMid = Gdx.graphics.getWidth() / 2;
        fYMid = Gdx.graphics.getHeight() / 2;
        typerounds = new ScrTypeRounds(game);
        nJMax = Integer.parseInt(typerounds.sRounds);
        //System.out.println(nJMax);
        vec1 = new Vector2(0, 0);
        vec2 = new Vector2(w / 2, 0);
        vec3 = new Vector2(0, h / 2);
        vec4 = new Vector2(w / 2, h / 2);
        font = new BitmapFont();
        batch = new SpriteBatch();
        batchFonts = new SpriteBatch();
        spriteP = new Sprite(new Texture("pause.jpg"));
        shapeRenderer = new ShapeRenderer();
        shapeRendHud = new ShapeRenderer();
        circ = new Circle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 150);
        spriteP.setSize(w / 3, h / 3);
        ArsCols[0] = "RED";
        ArsCols[1] = "BLUE";
        ArsCols[2] = "GREEN";
        ArsCols[3] = "PURPLE";
        AlvShuffle.add(vec1);
        AlvShuffle.add(vec2);
        AlvShuffle.add(vec3);
        AlvShuffle.add(vec4);
        recHUD = new Rectangle(0, h - (h / 4.2f), w, (h / 4.2f) - 25);
        ArcRandCol[0] = Color.RED;
        ArcRandCol[1] = Color.BLUE;
        ArcRandCol[2] = Color.GREEN;
        ArcRandCol[3] = Color.PURPLE;
        for (int i = 0; i < 4; i++) {
            ArSprGrey[i] = new Sprite(new Texture("Grey" + i + ".png")); //BL, BR, TL, TR
            ArSprGrey[i].setPosition(AlvShuffle.get(i).x, AlvShuffle.get(i).y);
            ArSprGrey[i].setSize(w / 2, h / 2);
        }
        for (int i = 0; i < 4; i++) {
            ArSprColours[i] = new Sprite(new Texture("Col" + i + ".png"));//RED, BLUE, GREEN, PURPLE
            ArSprColours[i].setPosition(AlvShuffle.get(i).x, AlvShuffle.get(i).y);
            ArSprColours[i].setSize(w / 2, h / 2);
//            System.out.println( ArSprColours[i].getWidth() + " : " + ArSprColours[i].getHeight());
        }
        spriteP.setPosition(w / 2 - spriteP.getWidth() / 2, h - spriteP.getHeight());
        //=========================TV INP=============================//
        nFrame = 0;
        sndSong1 = Gdx.audio.newSound(Gdx.files.internal("Song1.mp3"));
        long id = sndSong1.play(1.0f, 1.0f, 0.0f);
        nPos = 0;
        araniDance = new Animation[10];
        txSheet = new Texture("dance.png");
        fW = txSheet.getWidth() / 8;
        fH = txSheet.getHeight() / 10;
        for (int i = 0; i < 10; i++) {
            Sprite[] arSprDance = new Sprite[8];
            for (int k = 0; k < 8; k++) {
                fSx = k * fW;
                fSy = i * fH;
                sprDance = new Sprite(txSheet, fSx, fSy, fW, fH);
                arSprDance[k] = new Sprite(sprDance);
            }
            araniDance[i] = new Animation(0.5f, arSprDance);
        }
        FileHandle fontFile = Gdx.files.internal("Woods.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
        parameter.color = Color.WHITE;
        textGreat = generator.generateFont(parameter);
        textBad = generator.generateFont(parameter);
//        textNice = generator.generateFont(parameter);
//        textOk = generator.generateFont(parameter);
        generator.dispose();
        //=========================TV INP=============================//
        //nJMa nNext= nNext = ThreadLocalRandom.current().nextInt(10, 20 + 1);
        nNext = ThreadLocalRandom.current().nextInt(0, 3 + 1);//initial randomization of where to click
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override //4 = TL, 5 = TR, 1 = BL, 2 = BR
    public void render(float delta) {
        //=========================TV INP=============================//
        nTime++;
        nTextCount++;
        //=========================TV INP=============================//
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (!isDone && !isPause) { //Playing Game
            if (isCol) {//colours displayed
                isColNoClick = true;
                nCountCol++;
                if (nCountCol == 110) {
                    isCol = false;
                    nCountCol = 0;
                }
            } else {//colours not displayed: screen is grey and points can be earned
                nCountSwitch++;
                isColNoClick = false;
            }
            if (nJ == nJMax) {//round ends
                isDone = true;
            }
            isCont = true;
            if ((nRand - 30) >= nCountSwitch) {
                Color();
            }
            if (!isRand) {//set how long before changing the colour location again
                //nRand = 50;
                nRand = ThreadLocalRandom.current().nextInt(200, 400 + 1); //randomizes WHEN to change the location of the colours
                isRand = true;
            }
            //System.out.println(rand);
            if (nCountSwitch == nRand) {//change the colour locations
                nCountSwitch = 0;
                isRand = false;//access the above if statement for one render
                isCol = true;
                //System.out.println(AlvShuffle);
                for (int i = 0; i < 4; i++) {
                    ArSprColours[i].setPosition(AlvShuffle.get(i).x, AlvShuffle.get(i).y);
                }
            }
            shapeRenderer.begin(ShapeType.Filled);
            shapeRendHud.begin(ShapeType.Filled);
            batch.begin();
            if (isCol) {//draw coloured sprites
                font.setColor(Color.WHITE);
                for (int k = 0; k < 4; k++) {
                    ArSprColours[k].draw(batch);
                }
            } else {//draw grey sprites
                Color();
                for (int k = 0; k < 4; k++) {
                    ArSprGrey[k].draw(batch);
                }
                shapeRendHud.rect(recHUD.getX(), 0, recHUD.getWidth(), recHUD.getHeight());
            }
            if (isExit) {//exit
                Gdx.app.exit();
            }//these nested ifs dictate what colour to display in the middle
            shapeRenderer.setColor(ArcRandCol[nNext]);
            if (isClick && nTimeout != nMaxOut) {//make the center circle flash white
                nCount++;
                shapeRenderer.setColor(Color.WHITE);
                if (nCount == 12) {
                    nCount = 0;
                    isClick = false;
                }
            }
            //System.out.println("nCountSwitch: " + nCountSwitch + " nRand: " + nRand);
            batch.end();
            if (bCount && !isCol) {//if true: count up towards changing middle colour
                nTimeout++;
            }
            if (nTimeout == nMaxOut && !isClick) {//change middle colour
                if (!isJUp) {
                    nJ++;
                    nNext = ThreadLocalRandom.current().nextInt(0, 3 + 1);
                    isJUp = true;
                }
                bCount = false;
                nCount++;
                shapeRenderer.setColor(Color.WHITE);
                if (nCount == 4) {//middle colour flashes white
                    nTimeout = 0;
                    nCount = 0;
                    isJUp = false;
                    bCount = true;
                }
            }
            shapeRenderer.circle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 150);
            shapeRenderer.end();
            shapeRendHud.end();
            batchFonts.begin();
            if (isCol) {//draw how long remains before points can be earned while coloured
                font.draw(batchFonts, String.valueOf(nCountCol) + " / " + String.valueOf("110"), fXMid + 140, fYMid * 2);
            } else {//draw these fonts while grey
                font.draw(batchFonts, "Clicks remaining: " + String.valueOf(nJ) + " / " + typerounds.sRounds, 10, 80);//amount of pattern remaining
                font.draw(batchFonts, "Clicks correct: " + String.valueOf(fGood - 1), 10, 60);//how many correct clicks
                font.draw(batchFonts, "Percentage correct: " + String.valueOf(fEff) + "%", 10, 40);//percentage correct
                font.draw(batchFonts, "Time to click remaining: " + String.valueOf(nTimeout) + " / " + String.valueOf(nMaxOut), 10, 20);//time until middle colour changes
                //font.draw(batchFonts, "Time until colours change: " + String.valueOf(nCountSwitch) + " / " + String.valueOf(nRand), 10, 20);//time until colour locations change
            }//draw these in both
            font.draw(batchFonts, "Escape to exit", fXMid + 110, 32);
            font.draw(batchFonts, "Spacebar to pause or unpause", fXMid + 110, 72);
            font.draw(batchFonts, "Press Enter to show end screen!", fXMid + 110, 52);
            batchFonts.end();
        } else if (!isDone && isPause) { //Paused (same comments)
            Color();
            shapeRenderer.begin(ShapeType.Filled);
            shapeRendHud.begin(ShapeType.Filled);
            batch.begin();
            for (int k = 0; k < 4; k++) {
                ArSprGrey[k].draw(batch);
            }
            isCirc = false;
            if (isExit) {
                Gdx.app.exit();
            }
            batch.end();
            shapeRenderer.circle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 150);
            shapeRendHud.rect(recHUD.getX(), 0, recHUD.getWidth(), recHUD.getHeight());
            shapeRenderer.end();
            shapeRendHud.end();
            batchFonts.begin();
            if (isCol) {
                font.draw(batchFonts, String.valueOf(nCountCol) + " / " + String.valueOf("110"), fXMid + 140, fYMid * 2);
            }
            font.draw(batchFonts, "Clicks remaining: " + String.valueOf(nJ) + " / " + typerounds.sRounds, 10, 80);//amount of pattern remaining
            font.draw(batchFonts, "Clicks correct: " + String.valueOf(fGood - 1), 10, 60);//how many correct clicks
            font.draw(batchFonts, "Percentage correct: " + String.valueOf(fEff) + "%", 10, 40);//percentage correct
            font.draw(batchFonts, "Time to click remaining: " + String.valueOf(nTimeout) + " / " + String.valueOf(nMaxOut), 10, 20);
            font.draw(batchFonts, "Escape to exit", fXMid + 110, 32);
            font.draw(batchFonts, "Spacebar to pause or unpause", fXMid + 110, 72);
            font.draw(batchFonts, "Press Enter to show end screen!", fXMid + 110, 52);
            spriteP.draw(batchFonts);
            batchFonts.end();
        } else { //End Screen
            //bDance = false;
            isPause = true;
            game.nScreen = 3;
            game.updateState();
            dispose();
        }
        //=========================TV INP=============================//
        if (bDance) {
            if (nGB == 1) {
                if (nTextCount % 0.5 == 0) {
                    batch.begin();
                    textGreat.draw(batch, "GREAT", nTextY, nTextX);
                    nTextX += 5;
                    batch.end();
                    //System.out.println("hi " + Gdx.graphics.getHeight());
                    if (nTextX > Gdx.graphics.getHeight()) {
                        nTextX = Gdx.graphics.getHeight() / 2;
                        nTextY = Gdx.graphics.getWidth() / 5 + (int) (Math.random() * (Gdx.graphics.getWidth() / 2));
                        nGB = 0;
                    }
                }//asdf
            }
            if (nGB == 2) {
                if (nTextCount % 0.5 == 0) {
                    batch.begin();
                    textBad.draw(batch, "bad", nTextY, nTextX += 5);
                    batch.end();
                    //System.out.println("hi " + Gdx.graphics.getHeight());
                    if (nTextX > Gdx.graphics.getHeight()) {
                        nTextX = Gdx.graphics.getHeight() / 2;
                        nTextY = Gdx.graphics.getWidth() / 5 + (int) (Math.random() * (Gdx.graphics.getWidth() / 2));
                        nGB = 0;
                    }//asdf
                }
            }
            if (!isPause) {
                if (nTime % 6 == 0) {
                    nFrame++;
                }
                if (nFrame > 8) {
                    nFrame = 0;
                }
            }
            //System.out.println(nPos + " " + nFrame);
            trTemp = araniDance[nPos].getKeyFrame(nFrame, true);
            batch.begin();
            batch.draw(trTemp, Gdx.graphics.getWidth() / 2 - trTemp.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - trTemp.getRegionHeight() / 2);
            batch.end();
        }

//=========================TV INP=============================//
    }

    public void Color() {
        font.setColor(Color.BLACK);
        if ((nRand - 30) <= nCountSwitch) {
            font.setColor(Color.RED);
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
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        isCorrect = false;
        isCirc = false;
        bDance = true;
        if (!isDone) {//decide if the click was correct
            if (button == Buttons.LEFT && !circ.contains(screenX, screenY)) {
                nClick = 4;
                for (int i = 0; i < 4; i++) {              
                    if (ArSprColours[i].getBoundingRectangle().contains(screenX, screenY)) {
                        if (i < 2) {
                            nClick = i + 2;
                        } else {
                            nClick = i - 2;
                        }
                    }
                }
            }
            if (nClick == nNext) {
                isCorrect = true;
            }
            if (!circ.contains(screenX, screenY) && !isPause && !isColNoClick && !recHUD.contains(screenX, screenY)) {//nothing happens if clicking middle circle
                isCirc = true;
            }
            if (isCirc) {//if anywhere other than middle circle clicked:
                bCount = true;
                nNext = ThreadLocalRandom.current().nextInt(0, 3 + 1);
                isClick = true;
                nTimeout = 0;
                nJ++;
                fEff = (fGood / nJ) * 100;
                if (isCorrect) {//these increase if correctly clicked
                    nGB = 1;
                    fGood++;
                } else if (!isCorrect) {
                    nGB = 2;
                }
            }//dictates which animation to do
            if (fEff == 100) {
                nPos = 6;
            }
            if (fEff < 100 && fEff > 79) {
                nPos = 7;
            }
            if (fEff < 80 && fEff > 59) {
                nPos = 8;
            }
            if (fEff < 60) {
                nPos = 1;
            }
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {//keyboard input
        if (keycode == Input.Keys.ESCAPE) {
            isExit = true;
        } else if (keycode == Input.Keys.ENTER) {
            isDone = true;
        } else if (keycode == Input.Keys.SPACE) {
            isPause = !isPause;
            isCirc = false;
        }
        return false;
    }
}
