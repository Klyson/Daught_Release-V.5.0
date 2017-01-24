package Menu;

import Screens.ScrInstructions;
import com.badlogic.gdx.Game;
import Screens.ScrMainMenuBox;
import Screens.ScrSongOne;
import Screens.ScrTypeRounds;
import Screens.ScrCredits;
import Screens.ScrEndScreen;
import com.badlogic.gdx.Screen;

//import Screens.ScrEndScreen;
public class GamGame1 extends Game {

    ScrTypeRounds scrTpye;
    ScrCredits scrCred;
    ScrSongOne scrSong1;
    ScrMainMenuBox scrMain;
    ScrInstructions scrIns;
    ScrEndScreen scrEnd;
    public int nScreen; // 0 for mainmenu, 1 for song1, 2 for endscreen

    public void updateState() {
        if (nScreen == 0) {
            setScreen(scrIns);
        } else if (nScreen == 1) {
            setScreen(scrTpye);
        } else if (nScreen == 2) {
            setScreen(scrCred);
        } else if (nScreen == 3) {
            setScreen(scrEnd);
        } else if (nScreen == 4) {
            setScreen(scrSong1);
        } else if (nScreen == 5) {
            setScreen(scrMain);
        }
    }

    @Override
    public void create() {
        nScreen = 5;
        scrTpye = new ScrTypeRounds(this);
        scrSong1 = new ScrSongOne(this);
        scrMain = new ScrMainMenuBox(this);
        scrIns = new ScrInstructions(this);
        scrCred = new ScrCredits(this);
        scrEnd = new ScrEndScreen(this);
        updateState();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }
}