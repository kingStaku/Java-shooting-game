/***
 *** �G02�N���X
 ***/

import java.awt.*;
import java.awt.image.*;

public abstract class Enemy02 {
	
	static int width,height;	// �ړ��͈͂̑傫��

	int cx,cy;		// ���S���W
	int rad;		// ���a
	int dx,dy;		// �ړ���
	int life;       // ���C�t
	int moveDir = 1;
	boolean hitcan;  // �q�b�g�ł���Ƃ�
	
	// �摜
	Image enemyMachine, enemyMachine_t;
	BufferedImage bufenemyMachine;

	/*
	 * �R���X�g���N�^
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
	 * �摜�֘A
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
	 * �`��
	 */
	public void draw(Graphics g, Avalon a){
		g.drawImage(enemyMachine_t, cx-rad, cy-rad, 2*rad, 2*rad, a);
	}

	/*
	 * �ړ�
	 */
	public abstract void move();
	
	/*
	 * �Փ˔���
	 */
	public abstract boolean collision(int GSx, int GSy);

	/*
	 * �ړ��͈͂̑傫���̐ݒ�
	 */
	public static void setSize(int w,int h){
		width=w;
	    height=h;
	}
}