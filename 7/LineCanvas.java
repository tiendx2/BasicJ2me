import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;


class LineCanvas extends Canvas {
	int arc1=0,arc2=0,arc3=0,arc4=0;
	public LineCanvas(int a1,int a2,int a3,int a4){
		this.arc1=a1;
		this.arc2=a2;
		this.arc3=a3;
		this.arc4=a4;
	}
	public void paint(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.setColor(255,255,255);
		g.fillRect(0, 0, width - 1, height - 1);
		g.setColor(0);
		g.setStrokeStyle(Graphics.SOLID);
		g.fillArc(10, 10, width - 30, height - 30, 0, arc1);
		g.setColor(255, 0, 0);
		g.fillArc(10, 10, width - 30, height - 30, arc1,arc2);
		g.setColor(255, 100, 0);
		g.fillArc(10, 10, width - 30, height - 30, arc1+arc2, arc3);
		g.setColor(255, 100, 100);
		g.fillArc(10, 10, width - 30, height - 30, arc1+arc2+arc3, arc4);

	}
}
