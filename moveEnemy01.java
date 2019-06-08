/***
 *** “G01‚Ì“®‚«
 ***/

import java.awt.*;

public class moveEnemy01 extends Enemy01 {
	
	
	public moveEnemy01() {
		
	}
	
	/*
	 * ˆÚ“®
	 */
	public void move(){
		if(moveDir == 1){
			cx+=5;
			if(cx>width){
				moveDir = 2;
			}
		}
		else if(moveDir == 2){
			cx-=5;
			if(cx<0){
				moveDir = 1;
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