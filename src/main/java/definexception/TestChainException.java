package definexception;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author:wuhao
 * @description:测试异常链
 * @date:2019/12/17
 */
public class TestChainException {
    /**
     * 我们常常会想要在捕获一个异常后抛出另一个异常，并且希望把原始异常的信息保存下来，这被称为异常链。 throw
     * 抛出的是一个新的异常信息，这样会导致原有的异常信息丢失。在JDk1.4以前，程序员必须自己编写代码来保存原始异常信息。现在所有 Throwable 子类在构造器中都可以接受一个
     * cause(异常因由) 对象作为参数。 这个 cause就用来表示原始异常，这样通过把原始异常传递给新的异常，使得即使当前位置创建并抛出了新的异常，也能通过这个异常链追踪到异常最初发生的位置
     *
     * @throws MyException
     */
    public void readFile() throws MyException {
        try {
            InputStream in = new FileInputStream("java.txt");
            Scanner scanner = new Scanner(in);
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new MyException("文件在哪里?", e);
        }
    }

    public void invokeReadFile() throws MyException {
        try {
            readFile();
        } catch (MyException e) {
            throw new MyException("文件找不到!", e);
        }
    }

    public static void main(String[] args) {
        //
        TestChainException t = new TestChainException();
        try {
            t.invokeReadFile();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}

