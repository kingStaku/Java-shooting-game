/***
 *** �����̍U���e��
 ***/

import java.awt.*;

public class myGunShot extends Circle {
	
	public myGunShot(int x, int y) {
		super();
		this.cx = x;
		this.cy = y;
	}
	
	/*
	 * �ړ�
	 */
	public void move(){
		cy-=dy;
	}
}