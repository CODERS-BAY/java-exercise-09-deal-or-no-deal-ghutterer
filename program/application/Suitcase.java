package application;

public class Suitcase {
    double money;

    public Suitcase(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Suitcase{" +
                "money=" + money +
                '}';
    }
}
