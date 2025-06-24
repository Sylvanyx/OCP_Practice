package functional_programming.Optionals;

import java.util.Optional;

public class CreateAndUseOptionals {

    public static Optional<Double> average(int... scores) {
        if (scores.length == 0)
            return Optional.empty();
        int sum = 0;
        for (int score : scores)
            sum += score;
        Optional<Double> avg = Optional.of((double) sum / scores.length);
        return avg;
    }

    public static void main(String[] args) {
        int[] scores = new int[] { 7, 8, 6, 9, 7, 10, 6, 8 };

        Optional<Double> opt = average(scores);
        System.out.println(opt.get());
    }
}