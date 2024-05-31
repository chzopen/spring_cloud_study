package com.chz.myApplication1.video;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FFmpegTest {

    public static void main(String[] args) {
        File video = new File("C:/videos/bandicam 2024-05-13 22-21-07-965.mp4");
        String picPath = "c:/videos/pic/";
        getVideoPic(video, picPath);
    }

    /**
     * 根据读取到的视频文件，获取视频中的每一帧图片
     *
     * @param video   视频文件 E:/video/video.mp4
     * @param picPath 图片的保存路径 E:/pic/
     */
    public static void getVideoPic(File video, String picPath) {
        // 1.根据一个视频文件，创建一个按照视频中每一帧进行抓取图片的抓取对象
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        // 2.创建一个帧-->图片的转换器
        Java2DFrameConverter converter = new Java2DFrameConverter();

        try {
            ff.start();
            // 3.先知道这个视频一共有多少帧
            int length = ff.getLengthInFrames();
            System.out.println("高度：" + ff.getImageHeight());
            System.out.println("宽度：" + ff.getImageWidth());
            System.out.println("帧率：" + ff.getFrameRate());
            System.out.println("总帧数：" + length);
            System.out.println("==========================================");
            System.out.println("图片抓取中...");

            // 4.读取视频中每一帧图片
            int cnt = 1;
            Frame frame;
            while (true) {
                frame = ff.grabFrame();

                if (frame == null) {
                    break;
                }

                if (frame.image == null) {
                    continue;
                }

                // 5.将获取的帧，存储为图片
                BufferedImage image = converter.getBufferedImage(frame);
                File picFile = new File(picPath, cnt + ".png");

                // 6.将图片保存到目标文件中
                ImageIO.write(image, "png", picFile);
                if (cnt % 100 == 0) {
                    System.out.println("前" + cnt + "张图片抓取完成；");
                }
                cnt++;

            }

            System.out.println("图片抓取完成。");
            ff.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
