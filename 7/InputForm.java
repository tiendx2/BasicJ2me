import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class InputForm extends MIDlet implements CommandListener {

	Display mdis;
	Form mForm;
	TextField num1,num2,num3,num4;
	Command mExit,mOk;
	public InputForm() {
		// TODO Auto-generated constructor stub
		mForm = new Form("Input Form");
		num1 = new TextField("Num1","",30,TextField.NUMERIC);
		num2 = new TextField("Num2","",30,TextField.NUMERIC);
		num3 = new TextField("Num3","",30,TextField.NUMERIC);
		num4 = new TextField("Num4","",30,TextField.NUMERIC);
		
		mForm.append(num1);
		mForm.append(num2);
		mForm.append(num3);
		mForm.append(num4);
		
		mExit = new Command("Exit",Command.EXIT, 0);
		mOk = new Command("Ok", Command.OK, 0);
		
		mForm.addCommand(mOk);
		mForm.addCommand(mExit);
		mForm.setCommandListener(this);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		mdis = Display.getDisplay(this);
		mdis.setCurrent(mForm);
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if(arg0==mOk){
			int k1=Integer.parseInt(num1.getString());
			int k2= Integer.parseInt(num2.getString());
			int k3= Integer.parseInt(num3.getString());
			int k4= Integer.parseInt(num4.getString());
			if(k1+k2+k3+k4>100){
				mdis.setCurrent(new Alert("Canh bao","Nhap ti le khong chinh xac", null,AlertType.WARNING));
			}
			else {
				k1=(k1*360)/100;
				k2=(k2*360)/100;
				k3=(k3*360)/100;
				k4=(k4*360)/100;
				
				System.out.println("k1="+k1+"-k2="+k2+"-k3="+k3+"-k4="+k4);
				mdis.setCurrent(new LineCanvas(k1,k2,k3,k4));
			}
		}
	}

}
