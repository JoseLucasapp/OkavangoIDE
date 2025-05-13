package main.java.com.joselucasapp.okavangoide.helpers;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenData {
    public double[] getData(){

        double[] screenData = {0.0,0.0};
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        screenData[0] = screenBounds.getWidth();
        screenData[1] = screenBounds.getHeight();

        return screenData;
    }
}
