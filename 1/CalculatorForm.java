package bai1_calculator;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.TextField;

public class CalculatorForm extends Form implements ItemStateListener,CommandListener {
    private TextField pin = new TextField("CAL :","",12,TextField.ANY);
    private Alert alert;
    private Display disp;
    public CalculatorForm(Display d)
    {
        super("");
        disp = d;
        this.setItemStateListener(this);
        this.append(pin);
        Command plus = new Command("+", Command.OK, 1);
        Command minute = new Command("-", Command.OK, 2);
        Command times = new Command("*", Command.OK, 3);
        Command div = new Command("/", Command.OK, 4);
        Command sum = new Command("=", Command.OK, 5);
        this.addCommand(plus);
        this.addCommand(minute);
        this.addCommand(times);
        this.addCommand(div);
        this.addCommand(sum);
        this.setCommandListener(this);
        
    }
    public void itemStateChanged(Item item) {
        if (item == pin)
        {
            for (int i=0; i<pin.getString().length(); i++)
            {
                if (String.valueOf(pin.getString().charAt(i)).equals(new String(" ")))
                    displayAlert();
            }
        }
    }
    private void displayAlert()
    {
        alert = new Alert("Info","No space please !",null, AlertType.ERROR);
        disp.setCurrent(alert, this);
    }
        public void commandAction(Command c, Displayable d) {
                if(!"=".equals(c.getLabel())){
                        if(!pin.getString().endsWith(c.getLabel()))
                        pin.setString(pin.getString()+c.getLabel());
                }
                
        }
}