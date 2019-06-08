/***
 *** “G04‚Ì“®‚«
 ***/

import java.awt.*;

public class moveEnemy04 extends Enemy04 {
	
	public moveEnemy04() {

	}
	
	/*
	 * ˆÚ“®
	 */
	public void move(){
		if(moveDir == 1){
			cy+=15;
			if(cy>height){
				moveDir = 2;
				cx+=100;
				if(cy >= width){
					cx=0;
					cy=0;
				}
			}
		}
		else if(moveDir == 2){
			cy-=15;
			if(cy<0){
				moveDir = 1;
				cx+=100;
				if(cx >= width){
					cx=0;
					cy=0;
				}
			}
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