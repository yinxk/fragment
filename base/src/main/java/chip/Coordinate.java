package chip;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * 给定以下两个列表a和b,分别由列表a, b构造两个一维数组x.y.
 * 绘制由x和y构成的折线图(x为横坐标，y为纵坐标)
 * a=[1,2,3,4,5,6] b=[12,39,36,25,4,41
 */
public class Coordinate {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("坐标系");
        jFrame.setBounds(600, 300, 600, 300);
        jFrame.setVisible(true);
        Graphics graphics = jFrame.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.drawLine(20, 560, 280, 560);
        jFrame.update(graphics);

    }


}
