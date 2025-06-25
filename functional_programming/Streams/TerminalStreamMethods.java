package functional_programming.Streams;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class TerminalStreamMethods {

    public static void main(String[] args) {

        Stream<String> strStream = Stream.of("one", "two", "three", "four", "five");
        System.out.println(strStream.count());

        // System.out.println(numStream.min()); - error since stream is closed
        Stream<Integer> numStream = Stream.of(1, 2, 3, 4, 5);
        Optional<Integer> min = numStream.min(Comparator.naturalOrder());
        System.out.println(min.isPresent());

        System.out.println(numStream.findAny()); // returns optional<T>, terminates on infinite stream
        System.out.println(strStream.findFirst()); // returns same as above
        System.out.println(strStream.parallel().findFirst());

        System.out.println(strStream.anyMatch(s -> s.startsWith("t")));
        System.out.println(strStream.allMatch(s -> s.contains("o")));
        System.out.println(strStream.noneMatch(s -> s.contains("x")));

        strStream.forEach(System.out::println);
    }
}
