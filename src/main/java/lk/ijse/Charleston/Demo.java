package lk.ijse.Charleston;

public class Demo  {
    public static void main(String[] args) {
       Amimal a1 = new Lion();
       a1.eat();


    }


}

abstract class Amimal{
    abstract void eat();

}
class Lion extends Amimal {

    @Override
    void eat() {
        System.out.println("meat");
    }

}
