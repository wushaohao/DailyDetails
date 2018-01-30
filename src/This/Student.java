package This;

/**
 * Created by wuhao on 17/6/6.
 * 一:使用this调用构造方法只适用于构造方法的调用，类中的其他方法不能使用这种方法
 * 编程的时候有时候需要在一个构造方法中对另一个构造方法进行调用。但是，在使用this关键字
 * 调用其他构造方法的时候，this()调用构造方法只能放在构造方法的首行，为的是能
 * 够为类中的属性初始化；而且至少有一个构造方法是不用this调用，否则程序会出现错误
 * 二:this最重要的特定就是表示当前对象，那什么叫当前对象呢？在Java中当前对象就是指
 * 当前正在调用类中方法的对象。使用this引用当前对象是指如果在类的方法中需要返回一个对象，并且该对象是
 *方法所在的类的当前对象，可以使用this关键字作为方法的返回值
 */
public class Student {

    private String name;

    private int age;

    private int number;

    public String getName() {
        return name;
    }

    public Student(){
        this("AA");
        System.out.println("with paramters constructor has created..");
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int age) {
        this("nike");
        this.name = name;
        this.age = age;
    }



    public void setName(String name) {
        this.name = name;
        this.print();
    }

    public void print() {
        System.out.println("student name is "+name);
    }

    public Student increment(){
        number++;
        System.out.println("number is "+number);
        return this;
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("A");

        Student student1=new Student("wuhao",24);
        student1.print();

        student.increment().increment();
    }
}
