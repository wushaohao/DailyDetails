package designModels.Decorator;

import java.io.*;
import java.util.Date;

/**
 * Created by wuhao on 17/5/24.
 */
public class test {

    public static void main(String[] args) {
        Cafe cafe = new ConcreateCafe();

        Cafe milk = new MilkDecoratorCafe(cafe);
        milk.getCafe();
        System.out.println("-----------------");

        Cafe sugar = new SugarDecoratorCafe(cafe);
        sugar.getCafe();
        System.out.println("-----------------");

        Cafe milkAndSugar = new MilkDecoratorCafe(new SugarDecoratorCafe(cafe));
        milkAndSugar.getCafe();
        System.out.println("-----------------");

        Cafe sugarMilk = new SugarDecoratorCafe(new MilkDecoratorCafe(cafe));
        sugarMilk.getCafe();
        System.out.println("-----------------");


        File file = new File("/Users/wuhao/Downloads/csf2.5_url");
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] tempByte = new byte[1024];
            int hasRead = 0;
            System.out.println("begin time is:" + new Date().getTime());
            while ((hasRead = fis.read(tempByte)) > 0) {
                System.out.println(new String(tempByte, 0, hasRead));
            }
            System.out.println("end time is:" + new Date().getTime());
            fis.close();
            System.out.println("------------------");


            FileInputStream fis2 = new FileInputStream(file);
            byte[] tempByte2 = new byte[(int) file.length()];
            System.out.println("begin time is:" + new Date().getTime());
            fis2.read(tempByte2);
            System.out.println(new String(tempByte2));
            fis2.close();
            System.out.println("end time is:" + new Date().getTime());
            System.out.println("------------------");


            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));// 字符流 BufferedInputStream-可以通过减少读写次数来提高输入和输出的速度
            String s2;
            System.out.println("begin time is:" + new Date().getTime());
            while ((s2 = br.readLine()) != null) {
                System.out.println(s2);
            }
            System.out.println("end time is:" + new Date().getTime());
            br.close();
            System.out.println("------------------");


            DataInputStream ds = new DataInputStream(new FileInputStream(file));// 字节流  DataInputStream-从数据流读取字节，并将它们装换为正确的基本类型值或字符串
            String s;
            System.out.println("begin time is:" + new Date().getTime());
            while ((s = ds.readLine()) != null) {
                System.out.println(s);
            }
            System.out.println("end time is:" + new Date().getTime());
            ds.close();
            System.out.println("------------------");

            DataInputStream ds2 = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            String ss;
            System.out.println("begin time is:" + new Date().getTime());
            while ((ss = ds2.readLine()) != null) {
                System.out.println(ss);
            }
            System.out.println("end time is:" + new Date().getTime());
            ds2.close();
            System.out.println("------------------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
