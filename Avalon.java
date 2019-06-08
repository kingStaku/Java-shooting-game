import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;

public class Avalon extends Applet implements Runnable, KeyListener{
	Image background;
	Image myMachine, myMachine_t;
	BufferedImage bufmyMachine;
	
	Thread runner;
	boolean l_down,r_down,u_down,d_down;
	
	Image off;		// ���`��p�I�t�X�N���[���C���[�W
    Graphics offg;		// ���̃O���t�B�b�N�X
    Vector<Circle> circles;	// �~�̉ϒ��z��
	Vector<Enemy01> enemy01; // �G�P�̉ϒ��z��
	Vector<Enemy02> enemy02; // �G�Q�̉ϒ��z��
	Vector<Enemy03> enemy03; // �G�R�̉ϒ��z��
	Vector<Enemy04> enemy04; // �G�S�̉ϒ��z��
	Vector<Enemy05> enemy05; // �G�T�̉ϒ��z��
	
	int cx,cy;
	int rad;
	int dx,dy;
	int width,height;
	int sleep_time;
	int mylife;  // ���@�̃��C�t
	boolean hitcan;  // �q�b�g�ł���Ƃ�
	
	/*
	 * ��ԕϐ�
	 */
	int EnemyType;  // �G�̃^�C�v
	boolean GameTitle; // ��ʂ��Q�[���^�C�g�����ǂ���
	boolean GameClear; // ��ʂ��Q�[���N���A��ʂ��ǂ���
	boolean GameOver;  // ��ʂ��Q�[���I�[�o�[��ʂ��ǂ���
	
	public void init(){
		/*
		 * �摜�֘A
		 */
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		background = toolkit.getImage(getClass().getResource("space.jpg") );
		myMachine = toolkit.getImage(getClass().getResource("kadai.jpg") );
		MediaTracker tracker = new MediaTracker(new Component(){});
		tracker.addImage(myMachine, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException e) {}
		bufmyMachine = createBufferedImage(myMachine);
		myMachine_t = getTransparentImage(bufmyMachine);
		
		/*
		 * �}�֘A
		 */
		circles=new Vector<Circle>();
		enemy01=new Vector<Enemy01>();
		enemy02=new Vector<Enemy02>();
		enemy03=new Vector<Enemy03>();
		enemy04=new Vector<Enemy04>();
		enemy05=new Vector<Enemy05>();
    	setBackground(Color.black);
		
		width=getSize().width;
		height=getSize().height;
		cx=width/2;
		cy=height/2;
		rad=20;
		dx=dy=15;
		sleep_time=50;
		mylife = 3;  // ���@�̃��C�t
		
		/*
		 * ��ԕϐ��֘A
		 */
		EnemyType = 1;  // �����l���G�P�̖ڂƂ�������
		GameTitle = true;  // ��ʂ��Q�[���^�C�g�����ǂ���
		GameClear = false; // ��ʂ��Q�[���N���A��ʂ��ǂ���
		GameOver = false;  // ��ʂ��Q�[���I�[�o�[��ʂ��ǂ���
		hitcan = false;
		
		addKeyListener(this);  // �L�[���X�i�[
		
		off=createImage(width,height);
    	offg=off.getGraphics();

    	// ���E�̑傫���ݒ�
    	Circle.setSize(width,height);
		Enemy01.setSize(width,height);
		Enemy02.setSize(width,height);
		Enemy03.setSize(width,height);
		Enemy04.setSize(width,height);
		Enemy05.setSize(width,height);
		
		enemy01.addElement(new moveEnemy01());
		enemy02.addElement(new moveEnemy02());
		enemy03.addElement(new moveEnemy03());
		enemy04.addElement(new moveEnemy04());
		enemy05.addElement(new moveEnemy05());
	}
	
	public void start(){
		if(runner==null){
      		runner=new Thread(this);
      		runner.start();
    	}
	}
	
	public void stop(){
		runner=null;
	}
	
	public void run(){
		while(runner!=null){
			if(GameTitle==false && GameClear==false && GameOver==false){
				moveMyMachine();
				moveAllEnemy();
				moveAllGunShot();
			}
			repaint();
			try{
				runner.sleep(sleep_time);
			}
			catch(InterruptedException e){}
		}
	}
	
	private void moveMyMachine(){
		if(l_down) cx-=dx;
		if(r_down) cx+=dx;
		if(u_down) cy-=dy;
		if(d_down) cy+=dy;
		if(cx>=width) cx=width-1;
		if(cy>=height) cy=height-1;
		if(cx<=0) cx=1;
		if(cy<=0) cy=1;
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
	 * �L�[���X�i�[
	 */
	public void keyPressed(KeyEvent ev){
		if(GameTitle==false && GameClear==false && GameOver==false){
	    	switch(ev.getKeyCode()){
	    	case KeyEvent.VK_LEFT:  l_down=true; break;
	    	case KeyEvent.VK_RIGHT: r_down=true; break;
	    	case KeyEvent.VK_UP:    u_down=true; break;
	    	case KeyEvent.VK_DOWN:  d_down=true; break;
	    	}
		}
	}
	
	public void keyReleased(KeyEvent ev){
		if(GameTitle==false && GameClear==false && GameOver==false){
	    	switch(ev.getKeyCode()){
	    	case KeyEvent.VK_LEFT:  l_down=false; break;
	    	case KeyEvent.VK_RIGHT: r_down=false; break;
	    	case KeyEvent.VK_UP:    u_down=false; break;
	    	case KeyEvent.VK_DOWN:  d_down=false; break;
	    	case KeyEvent.VK_X: circles.addElement(new myGunShot(cx, cy)); break;
    		}
		}
		else if(GameTitle==true){
			switch(ev.getKeyCode()){
			case KeyEvent.VK_ENTER: GameTitle=false; break;
			}
		}
  	}
	public void keyTyped(KeyEvent ev){}
	
	private void moveAllGunShot(){
		for(Circle c:circles){
			myGunShot mGS = new myGunShot(c.cx, c.cy);
			boolean hit=false;
      		c.move();
	
			// �G�P�q�b�g��
			if(EnemyType==1){
				for(Enemy01 e01:enemy01){
					hit = e01.collision(mGS.cx, mGS.cy);
					if(hit==true){
						System.out.println("HIT !!");
					}
					if(e01.life < 1){
						EnemyType=2;
					}
				}
			}
			// �G�Q�q�b�g��
			else if(EnemyType==2){
				for(Enemy02 e02:enemy02){
					hit = e02.collision(mGS.cx, mGS.cy);
					if(hit==true){
						System.out.println("HIT !!");
					}
					if(e02.life < 1){
						EnemyType=3;
					}
				}
			}
			// �G�R�q�b�g��
			else if(EnemyType==3){
				for(Enemy03 e03:enemy03){
					hit = e03.collision(mGS.cx, mGS.cy);
					if(hit==true){
						System.out.println("HIT !!");
					}
					if(e03.life < 1){
						EnemyType=4;
					}
				}
			}
			// �G�S�q�b�g��
			else if(EnemyType==4){
				for(Enemy04 e04:enemy04){
					hit = e04.collision(mGS.cx, mGS.cy);
					if(hit==true){
						System.out.println("HIT !!");
					}
					if(e04.life < 1){
						EnemyType=5;
					}
				}
			}
			// �G�T�q�b�g��
			else if(EnemyType==5){
				for(Enemy05 e05:enemy05){
					hit = e05.collision(mGS.cx, mGS.cy);
					if(hit==true){
						System.out.println("HIT !!");
					}
					if(e05.life < 1){
						GameClear=true;
					}
				}
			}
		}
	}
	
	/*
	 * �G�̓���
	 */
	private void moveAllEnemy(){
		if(EnemyType==1){
			for(Enemy01 e01:enemy01)
				e01.move();
		}
		else if(EnemyType==2){
			for(Enemy02 e02:enemy02)
				e02.move();
		}
		else if(EnemyType==3){
			for(Enemy03 e03:enemy03)
				e03.move();
		}
		else if(EnemyType==4){
			for(Enemy04 e04:enemy04)
				e04.move();
		}
		else if(EnemyType==5){
			for(Enemy05 e05:enemy05)
				e05.move();
		}
	}
	
	/*
	 * �����ƓG�Ƃ̏Փ˔���
	 */
	private void myCollision(int ex, int ey, int erad){
		if(Math.sqrt(Math.pow(cx-ex,2)+Math.pow(cy-ey,2)) < rad+erad){
			if(hitcan){
				mylife -= 1;
				hitcan = false;
				if(mylife < 1) GameOver = true;
			}
		}
		else{
			hitcan = true;
		}
	}
		
	public void update(Graphics g){ 
		paint(g);
	}
	
	/*
	 * �`��
	 */
	public void paint(Graphics g){
		offg.clearRect(0,0,width,height);
		
		if(GameTitle==false && GameClear==false && GameOver==false){
			offg.drawImage(background, 0, 0, width, height, this);
			offg.drawImage(myMachine_t, cx-rad, cy-rad, 2*rad, 2*rad, this);
			if(EnemyType==1){
				for(Enemy01 e01:enemy01){
					e01.draw(offg, this);
					myCollision(e01.cx, e01.cy, e01.rad);
					Font font = new Font("MS����", Font.BOLD, 24);
					offg.setFont(font);
					offg.setColor(Color.white);
					offg.drawString("Round1�@VS ���R���ꏈ���@�@�G���C�t : "+ e01.life +"�@�@�@���@���C�t"+ mylife, 50, 750);
				}
			}
			else if(EnemyType==2){
				for(Enemy02 e02:enemy02){
					e02.draw(offg, this);
					myCollision(e02.cx, e02.cy, e02.rad);
					Font font = new Font("MS����", Font.BOLD, 24);
					offg.setFont(font);
					offg.setColor(Color.white);
					offg.drawString("Round2�@VS ���ϗ��w�@�@�G���C�t : "+ e02.life +"�@�@�@���@���C�t"+ mylife, 50, 750);
				}
			}
			else if(EnemyType==3){
				for(Enemy03 e03:enemy03){
					e03.draw(offg, this);
					myCollision(e03.cx, e03.cy, e03.rad);
					Font font = new Font("MS����", Font.BOLD, 24);
					offg.setFont(font);
					offg.setColor(Color.white);
					offg.drawString("Round3�@VS �H�w�p��@�@�G���C�t : "+ e03.life +"�@�@�@���@���C�t"+ mylife, 50, 750);
				}
			}
			else if(EnemyType==4){
				for(Enemy04 e04:enemy04){
					e04.draw(offg, this);
					myCollision(e04.cx, e04.cy, e04.rad);
					Font font = new Font("MS����", Font.BOLD, 24);
					offg.setFont(font);
					offg.setColor(Color.white);
					offg.drawString("Round4�@VS ����IV�@�@�G���C�t : "+ e04.life +"�@�@�@���@���C�t"+ mylife, 50, 750);
				}
			}
			else if(EnemyType==5){
				for(Enemy05 e05:enemy05){
					e05.draw(offg, this);
					myCollision(e05.cx, e05.cy, e05.rad);
					Font font = new Font("MS����", Font.BOLD, 24);
					offg.setFont(font);
					offg.setColor(Color.white);
					offg.drawString("Round5�@VS �v����II���K�@�@�G���C�t : "+ e05.life +"�@�@�@���@���C�t"+ mylife, 50, 750);
				}
			}
			for(Circle c:circles)
	      		c.draw(offg);
		}
		else if(GameTitle==true){                               // �^�C�g�����
			Font font01 = new Font("MS����", Font.BOLD, 36);
			offg.setFont(font01);
			offg.setColor(Color.red);
			offg.drawString("-Avalon-", 430, 350);
			Font font02 = new Font("MS����", Font.BOLD, 24);
			offg.setFont(font02);
			offg.setColor(Color.red);
			offg.drawString("Press ENTER Key", 405, 430);
		}
		else if(GameClear==true){
			Font font01 = new Font("MS����", Font.BOLD, 36);
			offg.setFont(font01);
			offg.setColor(Color.green);
			offg.drawString("Game Clear !", 400, 350);
		}
		else if(GameOver==true){
			Font font01 = new Font("MS����", Font.BOLD, 36);
			offg.setFont(font01);
			offg.setColor(Color.red);
			offg.drawString("Game Over !", 400, 350);
		}
		
		g.drawImage(off,0,0,null);
	}
	
	public static void main(String [] args){
		Avalon avalon = new Avalon();
		Frame frame = new Frame();
		frame.setSize( 1000, 800 );
		frame.setVisible( true );
  		frame.add(avalon);
    	frame.addWindowListener( new WindowEventHandler() );
  		avalon.init();
  		avalon.start();
    }
}

class WindowEventHandler extends WindowAdapter {
    public void windowClosing( WindowEvent ev ){
        System.exit(0);
    }
}

