package functional_programming.Streams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreatingStreams {

    public static void main(String[] args) {
        Stream<String> emptyStream = Stream.empty();
        Stream<String> strStream = Stream.of("one", "two", "three", "four", "five");

        var list = List.of(1, 2, 3, 4, 5, 6);
        list.stream().forEach(System.out::println); // always ordered
        System.out.println("--------------");
        list.parallelStream().forEach(System.out::println); // random order
        System.out.println("--------------");
        list.parallelStream().sequential().forEach(System.out::println); // prints in order
        System.out.println("--------------");
        list.parallelStream().forEachOrdered(System.out::println);// prints in order

        Stream<Double> infiniteRandoms = Stream.generate(Math::random);
        Stream<Integer> infiniteOdds = Stream.iterate(1, x -> x + 2);
        Stream<Integer> infiniteOdds2 = Stream.iterate(1, // seed
                x -> x < 100, // predicate
                x -> x + 2); // UnaryOperator

    }
}
