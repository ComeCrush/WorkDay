package com.heima.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
@Controller
public class ValidateController {

    @RequestMapping("/checkCode")
    public void getCheckCode(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");
        //在内存中创建一个长80，宽30的图片，默认黑色背景颜色
        int width=80;
        int height=30;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //获取画笔
        Graphics graphics = bufferedImage.getGraphics();
        //设置颜色为灰色
        graphics.setColor(Color.gray);
        //设置填充图片大小
        graphics.fillRect(0,0,width,height);
        //产生4个随机闫增吗
        String  checkCode=getCheckCode();
        //将验证码存入session下
        request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);
        //设置画笔颜色是黄色
        graphics.setColor(Color.yellow);
        //设置字体大小
        graphics.setFont(new Font("黑体",Font.BOLD,24));
        //向图片写入验证码
        graphics.drawString(checkCode,15,25);
        //将内存中的图片输出到浏览器
        /**
         * 参数1：图片内存对象
         * 参数2：图片格式
         * 参数3：图片输出流
         */
        ImageIO.write(bufferedImage,"PNG",response.getOutputStream());
    }

    private String getCheckCode() {
        String base = "0123456789ABCDEFGabcdefg";
        int size=base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }

        return sb.toString();
    }
}
