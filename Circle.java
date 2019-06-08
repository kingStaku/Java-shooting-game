/***
 *** 円クラス
 ***/

import java.awt.*;

public abstract class Circle {

  static int width,height;	// 移動範囲の大きさ

  int cx,cy;		// 中心座標
  int rad;		// 半径
  int dy;		// 移動量

  /*
   * コンストラクタ
   * 位置、大きさ、移動量を設定する。
   */
  public Circle(){
    cx=500;
    cy=400;
    rad=3;
    dy=15;
  }

  /*
   * 描画
   */
  public void draw(Graphics g){
  	g.setColor(Color.red);
    g.fillOval(cx-rad,cy-rad,2*rad,2*rad);
  }

  /*
   * 移動
   */
  public abstract void move();

  /*
   * 移動範囲の大きさの設定
   */
  public static void setSize(int w,int h){
    width=w;
    height=h;
  }
}