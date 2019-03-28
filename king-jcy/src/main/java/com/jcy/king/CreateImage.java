package com.jcy.king;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreateImage {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // 得到图片缓冲区
        BufferedImage bi = 
                new BufferedImage(350, 600, BufferedImage.TYPE_INT_RGB);// INT精确度达到一定,RGB三原色，高度70,宽度150

        // 得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D) bi.getGraphics();

        g2.fillRect(0, 0, 350, 600);// 填充一个矩形 左上角坐标(0,0),宽350,高600;填充整张图片
        // 设置颜色
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 350, 600);// 填充整张图片(其实就是设置背景颜色)

        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, 350 - 5, 600 - 5); // 画边框

        g2.setFont(new Font("宋体", Font.BOLD, 18)); // 设置字体:字体、字号、大小
        g2.setColor(Color.BLACK);// 设置背景颜色
        g2.drawString("戊戌年【狗年】", 135, 20); // 向图片上写字符串
        g2.drawLine(0, 30, 345, 30);
        
        g2.setFont(new Font("宋体", Font.BOLD, 15)); // 设置字体:字体、字号、大小
        g2.setColor(Color.BLACK);// 设置背景颜色
        g2.drawString("JANUARY", 140, 50); // 向图片上写字符串
        
        g2.setFont(new Font("宋体", Font.BOLD, 300)); // 设置字体:字体、字号、大小
        g2.setColor(Color.BLACK);// 设置背景颜色
        g2.drawString("4", 95, 300); // 向图片上写字符串
        
        g2.setFont(new Font("宋体", Font.BOLD, 30)); // 设置字体:字体、字号、大小
        g2.setColor(Color.BLACK);// 设置背景颜色
        g2.drawString("周四", 130, 360); // 向图片上写字符串
        g2.drawLine(0, 400, 345, 400);
        
        g2.setFont(new Font("宋体", Font.BOLD, 18)); // 设置字体:字体、字号、大小
        g2.setColor(Color.BLACK);// 设置背景颜色
        g2.drawString("你冒什么样的险，表明你看中什么。", 0, 420); // 向图片上写字符串
        g2.drawLine(0, 500, 340, 500);
        
        g2.setFont(new Font("宋体", Font.BOLD, 14)); // 设置字体:字体、字号、大小
        g2.setColor(Color.BLACK);// 设置背景颜色
        g2.drawString("作家 珍妮特.温特森《写在身体上》", 0, 480); // 向图片上写字符串
        g2.drawLine(0, 500, 340, 500);
        
//        g2.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)
        
        ImageIO.write(bi, "JPEG", new FileOutputStream("D:/image/a.jpg"));// 保存图片 JPEG表示保存格式

    }

}
