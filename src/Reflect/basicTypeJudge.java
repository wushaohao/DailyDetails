package Reflect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuhao on 17/3/26.
 */
public class basicTypeJudge {

    public static void main(String[] args) {
        /**
         * getComponentType()  判断一个类的数组类型，如果不是一个数组类这个方法返回null
         * isArray() 判断是不是数组
         */
        String[] arr1=new String[]{};
        System.out.println("arr1的类型是:"+arr1.getClass().getComponentType()+"\t arr1是数组类型:"+arr1.getClass().isArray());

        long[] arr2=new long[]{};
        System.out.println("arr2的类型是:"+arr2.getClass().getComponentType().getName()+"\t arr2是数组类型:"+arr1.getClass().isArray());

        int[] arr3=new int[]{};
        System.out.println("arr2的类型是:"+arr3.getClass().getComponentType()+"\t arr1是数组类型:"+arr3.getClass().isArray());

        double[] arr4=new double[]{};
        System.out.println("arr4的类型是:"+arr4.getClass().getComponentType()+"\t arr1是数组类型:"+arr4.getClass().isArray());


        String str="wuhao";
        System.out.println("String类型是:"+str.getClass().getName());

        long num=2L;
        System.out.println("long类型是:"+long.class.getName());

        Map maps=new HashMap();
        maps.put("key","value");
        maps.put("key1","value1");

//        System.out.println("maps的是---->:"+maps.getClass().getComponentType());
        System.out.println("maps的类型是:"+maps.getClass().getName());

        List lists=new ArrayList();
        lists.add("OKC");
        System.out.println("lists的类型是:"+lists.getClass().getName());


    }
}
