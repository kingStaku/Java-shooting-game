/***
 *** “G05‚Ì“®‚«
 ***/

import java.awt.*;

public class moveEnemy05 extends Enemy05 {
	
	public moveEnemy05() {

	}
	
	/*
	 * ˆÚ“®
	 */
	public void move(){
		stay += 1;
		if(stay==30){
			cx=(int)(Math.random()*width);
			cy=(int)(Math.random()*height);
			stay = 0;
		}
	}
	
	/*
	 * Õ“Ë”»’è
	 */
	public boolean collision(int GSx, int GSy){
		if(GSx > cx-rad && GSx < cx+rad && GSy > cy-rad && GSy < cy+rad){
			if(hitcan==true){
				life -= 1;
				if(life < 0) life = 0;
				hitcan = false;
			}
			System.out.println(life);
			return true;
		}
		else{
			if(GSx > cx-rad && GSx < cx+rad && GSy > cy-rad && GSy < cy+rad+15) hitcan = true;
			return false;
		}
	}
}