package application;

import java.util.*;

public class Main {
    static HashMap<Integer, Suitcase> hm = new HashMap<>();
    static double[] values = {0.01, 1, 5, 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000, 5000, 10000, 25000, 50000, 75000,
            100000, 200000, 300000, 400000, 500000, 750000, 1000000};
    static Suitcase yourSuitcase;
    static int gameRound = 1;
    static double bankoffer;
    static double remainingMoney;

    public static void main(String[] args) {

        System.out.println("Deal or no Deal?");
        makeSuitcases(values, hm);
        yourSuitcase = pickSuitcase();
        startGame();


    }


    public static void makeSuitcases(double[] arr, HashMap<Integer, Suitcase> hm) {
        int i = 1;
        List<Double> values = new ArrayList<>();
        for (double x : arr) {
            values.add(x);
        }
        Collections.shuffle(values);
        for (double x : values) {
            hm.put(i, new Suitcase(x));
            i++;
        }
    }

    public static Suitcase pickSuitcase() {
        System.out.println("Hello please pick your suitcase number 1-26");
        Scanner input = new Scanner(System.in);
        int yourNumber = Integer.parseInt(input.nextLine());
        if (yourNumber < 1 || yourNumber > 26) {
            System.out.println("Enter a correct number");
            yourNumber = Integer.parseInt(input.nextLine());
        }
        System.out.println("You picked suitcase number " + yourNumber);

        Suitcase sc = new Suitcase(hm.get(yourNumber).money);
        hm.remove(yourNumber);
        return sc;
    }

    public static void startGame() {
        int i;
        int number;
        Scanner input = new Scanner(System.in);
        if (hm.size() == 25) {
            i = 6;
        } else if (hm.size() == 19) {
            i = 5;
        } else if (hm.size() == 14) {
            i = 4;
        } else if (hm.size() == 10) {
            i = 3;
        } else if (hm.size() == 7) {
            i = 2;
        } else
            i = 1;

        if (gameRound == 10) {
            System.out.println("Alright Champ this is the last round. If you want you can swap your Suitcase with the last one");
            System.out.println("Do you want to do that y for yes n for no");
            String lastAnswer = readStringInput();
            if (lastAnswer.equals("y")) {
                System.out.println("You swapped your suitcase and you win " + (remainingMoney - yourSuitcase.money) + "$");
            } else
                System.out.println("You won " + yourSuitcase.money + "$");
        } else {

            System.out.println("Ok you need to pick " + i + " suitcases now that you need to eliminate");
            for (int x = 1; x <= i; x++) {
                number = readNumberInput();
                System.out.println("You picked number " + number + " it contained " + hm.get(number).money);
                hm.remove(number);
            }

            calculateBankFormula();

        }
    }

    public static void calculateBankFormula() {
        remainingMoney = yourSuitcase.money;

        for (Suitcase s : hm.values()) {
            remainingMoney += s.money;
        }

        bankoffer = ((remainingMoney / hm.size()) * gameRound);
        bankoffer = bankoffer / 10;

        gameRound++;

        System.out.println("The bank offers you " + String.format("%.2f", bankoffer) + "$");
        System.out.println("Do you want to accept the offer? Answer y for yes or n for no");
        String answerForBank = readStringInput();
        if (answerForBank.equals("y")) {
            System.out.println("Congrats you won " + String.format("%.2f", bankoffer) + "$");
        } else if (answerForBank.equals("n")) {
            showremainingSuitcases();
            startGame();
        }
    }

    public static void showremainingSuitcases() {
        System.out.println("The remaining suitcase numbers are : ");
        for (int i : hm.keySet()) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
    }

    public static String readStringInput() {
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        if ((answer.equals("y")) || (answer.equals("n"))) {
            return answer;
        } else {
            System.out.println("sorry enter the correct input try again");
            readStringInput();
        }

        return answer;
    }

    public static int readNumberInput() {
        Scanner input = new Scanner(System.in);
        boolean available = false;
        int number = 0;

        try {
            number = Integer.parseInt(input.nextLine());

            for (int i : hm.keySet()) {
                if (number == i) {
                    available = true;
                    break;

                }
            }
            if (available) {
                return number;
            } else {
                System.out.println("Suitcase number is not available anymore try again");
                number = Integer.parseInt(input.nextLine());

            }
        } catch (NumberFormatException e) {
            System.out.println("Not a number try again");
            number = Integer.parseInt(input.nextLine());

        }
        return number;

    }
}


