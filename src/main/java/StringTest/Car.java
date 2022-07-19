package StringTest;

/**
 * Created by wuhao on 17/6/19.
 */
public class Car {
    private String color;


    public Car(String color) {
        this.color = color;
    }

    public Car(Car car) {
        Car c = new Car("black");
        car = c;
        System.out.println("Construct color is " + car.getColor());
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static void main(String[] args) {
        Car car = new Car("red");
        System.out.println("car color is " + car.getColor());
        Car car2 = new Car("blue");
        System.out.println("car2 color is " + car2.getColor());
        Car car3 = new Car(car2);
        System.out.println("car2 color is " + car2.getColor());

    }
}
