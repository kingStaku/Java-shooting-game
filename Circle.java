/***
 *** �~�N���X
 ***/

import java.awt.*;

public abstract class Circle {

  static int width,height;	// �ړ��͈͂̑傫��

  int cx,cy;		// ���S���W
  int rad;		// ���a
  int dy;		// �ړ���

  /*
   * �R���X�g���N�^
   * �ʒu�A�傫���A�ړ��ʂ�ݒ肷��B
   */
  public Circle(){
    cx=500;
    cy=400;
    rad=3;
    dy=15;
  }

  /*
   * �`��
   */
  public void draw(Graphics g){
  	g.setColor(Color.red);
    g.fillOval(cx-rad,cy-rad,2*rad,2*rad);
  }

  /*
   * �ړ�
   */
  public abstract void move();

  /*
   * �ړ��͈͂̑傫���̐ݒ�
   */
  public static void setSize(int w,int h){
    width=w;
    height=h;
  }
}