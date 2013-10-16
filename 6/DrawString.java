package bai6_fontString;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class DrawString extends MIDlet implements CommandListener {
	private Display display;
	private Command mExit, mFont, mBack, mSave;
	private Form fChoose;
	private ChoiceGroup typeFont, sizeFont;
	Font mFontString = Font.getDefaultFont();

	private TextCanvas canvas;

	public void creatCommand() {
		mBack = new Command("Back", Command.BACK, 0);
		mExit = new Command("mExit", Command.EXIT, 0);
		mFont = new Command("Font", Command.SCREEN, 0);
		mSave = new Command("Save", Command.SCREEN, 0);
	}

	public void creatForm() {
		fChoose = new Form("Font");

		typeFont = new ChoiceGroup("Kieu chu", Choice.MULTIPLE);
		typeFont.append("italic", null);
		typeFont.append("Bold", null);
		typeFont.append("Underline", null);
		fChoose.append(typeFont);

		sizeFont = new ChoiceGroup("Co chu", Choice.EXCLUSIVE);
		sizeFont.append("Small", null);
		sizeFont.append("Medium", null);
		sizeFont.append("Lage", null);
		fChoose.append(sizeFont);

		fChoose.addCommand(mBack);
		fChoose.addCommand(mSave);
		fChoose.setCommandListener(this);
	}

	public void startApp() {
		display = Display.getDisplay(this);
		creatCommand();
		creatForm();
		createString();
		display.setCurrent(canvas);
	}

	public void createString() {
		canvas = new TextCanvas(mFontString);
		canvas.addCommand(mFont);
		canvas.addCommand(mExit);
		canvas.setCommandListener(this);
	}

	public void pauseApp() {
	}

	public void destroyApp(boolean unconditional) {

	}

	public void getFontString() {

		boolean selectedType[] = new boolean[typeFont.size()];
		typeFont.getSelectedFlags(selectedType);
		int dem = 0;
		for (int i = 0; i < typeFont.size(); i++) {
			if (selectedType[i])
				dem = dem + 1;
		}
		int k = sizeFont.getSelectedIndex();
		System.out.println("k=" + k);
		if (k == 0) {
			if (dem == 0) {
				mFontString = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
						Font.SIZE_SMALL);
			} else if (dem == 1) {
				if (selectedType[0])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_ITALIC, Font.SIZE_SMALL);
				else if (selectedType[1])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_BOLD, Font.SIZE_SMALL);
				else
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_UNDERLINED, Font.SIZE_SMALL);
			} else if (dem == 2) {
				if (!selectedType[0])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_UNDERLINED | Font.STYLE_BOLD,
							Font.SIZE_SMALL);
				else if (!selectedType[1])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_ITALIC | Font.STYLE_UNDERLINED,
							Font.SIZE_SMALL);
				else
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_ITALIC | Font.STYLE_BOLD,
							Font.SIZE_SMALL);
			} else if (dem == 3) {
				mFontString = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC
						| Font.STYLE_BOLD | Font.STYLE_UNDERLINED,
						Font.SIZE_SMALL);
			}
		} else if (k == 1) {
			if (dem == 0) {
				mFontString = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
						Font.SIZE_MEDIUM);
			} else if (dem == 1) {
				if (selectedType[0])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
				else if (selectedType[1])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_BOLD, Font.SIZE_MEDIUM);
				else
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM);
			} else if (dem == 2) {
				if (!selectedType[0])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_UNDERLINED | Font.STYLE_BOLD,
							Font.SIZE_MEDIUM);
				else if (!selectedType[1])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_ITALIC | Font.STYLE_UNDERLINED,
							Font.SIZE_MEDIUM);
				else
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_ITALIC | Font.STYLE_BOLD,
							Font.SIZE_MEDIUM);
			} else if (dem == 3) {
				mFontString = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC
						| Font.STYLE_BOLD | Font.STYLE_UNDERLINED,
						Font.SIZE_MEDIUM);
			}
		} else {
			if (dem == 0) {
				mFontString = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
						Font.SIZE_LARGE);
			} else if (dem == 1) {
				if (selectedType[0])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_ITALIC, Font.SIZE_LARGE);
				else if (selectedType[1])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_BOLD, Font.SIZE_LARGE);
				else
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_UNDERLINED, Font.SIZE_LARGE);
			} else if (dem == 2) {
				if (!selectedType[0])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_UNDERLINED | Font.STYLE_BOLD,
							Font.SIZE_LARGE);
				else if (!selectedType[1])
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_ITALIC | Font.STYLE_UNDERLINED,
							Font.SIZE_LARGE);
				else
					mFontString = Font.getFont(Font.FACE_SYSTEM,
							Font.STYLE_ITALIC | Font.STYLE_BOLD,
							Font.SIZE_LARGE);
			} else if (dem == 3) {
				mFontString = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC
						| Font.STYLE_BOLD | Font.STYLE_UNDERLINED,
						Font.SIZE_LARGE);
			}
		}

	}

	public void commandAction(Command com, Displayable dis) {
		// TODO Auto-generated method stub
		if (dis == canvas) {
			if (com == mFont) {
				display.setCurrent(fChoose);
			} else if (com == mExit) {
				notifyDestroyed();
			}
		} else if (dis == fChoose) {
			if (com == mBack) {
				createString();
				display.setCurrent(canvas);
			}
			if (com == mSave) {
				getFontString();
			}
		}
	}
}

class TextCanvas extends Canvas {
	public Font font;

	public TextCanvas(Font f) {
		font = f;
	}

	public void paint(Graphics g) {
		g.setColor(255, 255, 255);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(0, 0, 255);
		g.setFont(font);
		g.drawString("Baseline/Center", getWidth() / 2, getHeight() / 2,
				Graphics.HCENTER | Graphics.BASELINE);
	}
}
