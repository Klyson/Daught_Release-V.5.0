package Daught.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import Menu.GamGame1;
import com.badlogic.gdx.Gdx;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//        config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
//        config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
//        config.fullscreen = true;
        new LwjglApplication(new GamGame1(), config);
    }
}
