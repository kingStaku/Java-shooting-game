/***
 *** 敵02クラス
 ***/

import java.awt.*;
import java.awt.image.*;

public abstract class Enemy02 {
	
	static int width,height;	// 移動範囲の大きさ

	int cx,cy;		// 中心座標
	int rad;		// 半径
	int dx,dy;		// 移動量
	int life;       // ライフ
	int moveDir = 1;
	boolean hitcan;  // ヒットできるとき
	
	// 画像
	Image enemyMachine, enemyMachine_t;
	BufferedImage bufenemyMachine;

	/*
	 * コンストラクタ
	 */
	  public Enemy02(){
	    cx=0;
	    cy=0;
	    rad=50;
	  	life=5;
	  	hitcan = false;
	  	
	  	Toolkit toolkit=Toolkit.getDefaultToolkit();
		enemyMachine = toolkit.getImage(getClass().getResource("enemy02.jpg") );
		MediaTracker tracker = new MediaTracker(new Component(){});
		tracker.addImage(enemyMachine, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException e) {}
		bufenemyMachine = createBufferedImage(enemyMachine);
		enemyMachine_t = getTransparentImage(bufenemyMachine);
	  }
	
	/* 
	 * 画像関連
	 */
	public BufferedImage createBufferedImage(Image img){
		BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bimg.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		
		return bimg;
	}
	
	public Image getTransparentImage(BufferedImage buf){
		int tc = buf.getRGB(0, 0);
		for(int y = 0; y<buf.getHeight(); y++){
			for(int x = 0; x<buf.getWidth(); x++){
				if(buf.getRGB(x, y) == tc){
					buf.setRGB(x, y, 0x00000000);
				}
			}
		}
		return (Image)buf;
	}
	
	/*
	 * 描画
	 */
	public void draw(Graphics g, Avalon a){
		g.drawImage(enemyMachine_t, cx-rad, cy-rad, 2*rad, 2*rad, a);
	}

	/*
	 * 移動
	 */
	public abstract void move();
	
	/*
	 * 衝突判定
	 */
	public abstract boolean collision(int GSx, int GSy);

	/*
	 * 移動範囲の大きさの設定
	 */
	public static void setSize(int w,int h){
		width=w;
	    height=h;
	}
}