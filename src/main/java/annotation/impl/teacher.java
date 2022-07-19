package annotation.impl;

import annotation.interfaces.people;

/**
 * Created by wuhao on 17/3/12.
 */
public class teacher implements people {
    @Override
    public String getName() {
        System.out.println("I am a teacher");
        return new String("Chimeical Teacher");
    }

    @Override
    public int getAge() {
        System.out.println("I am a old man");
        return 100;
    }

    /**
     * @return
     * @SuppressWarnings 注解表示忽略这个警告
     */
    @SuppressWarnings("deprecation")
    public String address() {
        System.out.println("I am in NanJing");
        return new String("Nanjing");
    }

    public static void main(String[] args) {
        teacher t = new teacher();
        t.address();
    }
}
