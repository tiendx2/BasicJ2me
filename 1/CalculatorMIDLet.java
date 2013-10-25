package bai1_calculator;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class CalculatorMIDLet extends MIDlet {

    public Display display;

    public CalculatorMIDLet() {
        display = Display.getDisplay(this);
    }

    public void startApp() {
        Form f = new CalculatorForm(display);
        display.setCurrent(f);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

}