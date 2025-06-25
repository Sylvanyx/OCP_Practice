package functional_programming.Optionals;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/*
 * Optionals can be created with 
 * 
 *  of()
 *  ofNullable()
 *  empty()
 *  
 * 
 * or retrieve values with instance methods...
 *  get() 
 *  ifPresent()
 *  ifPresent(Consumer c)
 *  orElse(T other)
 *  orElseGet(Supplier s)
 *  orElseThrow()
 *  orElseThrow(Supplier s)
 *  
 */
public class CreateAndUseOptionals {

    public static Optional<Double> average(double... scores) {
        if (scores.length == 0)
            return Optional.empty();
        int sum = 0;
        for (double score : scores)
            sum += score;
        Optional<Double> avg = Optional.of((double) sum / scores.length);
        return avg;
    }

    public static void main(String[] args) {
        double[] scores = new double[] { 7.4, 8.3, 6.5, 9.8, 7.7, 10.0, 6.1, 8.2 };

        Optional<Double> emptyOpt = Optional.empty();
        System.out.println(emptyOpt);

        Optional<Double> opt = average(scores);
        System.out.println(opt.get());

        // emptyOpt.get(); throws java.util.NoSuchElementException

        if (emptyOpt.isPresent()) { // wont get called
            emptyOpt.get();
        }

        Consumer<Double> squareIt = num -> {
            double square = (double) (num * num);
            System.out.println(num + "^2" + " is " + square);
        };

        emptyOpt.ifPresent(squareIt);// does nothing
        opt.ifPresent(squareIt);// prints square value

        System.out.println(emptyOpt.orElse(0.0));

        Supplier<Double> s = () -> Math.random();
        System.out.println(emptyOpt.orElseGet(s));

        try {
            emptyOpt.orElseThrow();
        } catch (NoSuchElementException e) {
            System.out.println("caught exception");
        }

        Supplier<RuntimeException> s2 = () -> new RuntimeException();
        // emptyOpt.orElseThrow(s2); // throws exception
        System.out.println(opt.orElseThrow(s2));

    }
}