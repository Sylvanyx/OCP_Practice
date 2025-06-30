package functional_programming.Streams;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalStreamMethods {

    public static void main(String[] args) {

        Stream<String> strStream = Stream.of("one", "two", "three", "four", "five");
        System.out.println(strStream.count());

        // System.out.println(numStream.min()); - error since stream is closed
        Stream<Integer> numStream = Stream.of(1, 2, 3, 4, 5);
        Optional<Integer> min = numStream.min(Comparator.naturalOrder());
        System.out.println(min.isPresent());

        /* 
         * You cannot run multiple terminal conditions on the same stream
         * doing so throws an error
         * you would have to comment the above code and then uncomment a line below to avoid an error 
         * hence these lines are commented
         */

        // System.out.println(numStream.findAny()); // returns optional<T>, terminates on infinite stream
        // System.out.println(strStream.findFirst()); // returns same as above
        // System.out.println(strStream.parallel().findFirst());

        // System.out.println(strStream.anyMatch(s -> s.startsWith("t")));
        // System.out.println(strStream.allMatch(s -> s.contains("o")));
        // System.out.println(strStream.noneMatch(s -> s.contains("x")));

        Stream<String> strStream2 = Stream.of("This", "is", "a", "test", "ALERT");
        strStream2.forEach(System.out::print);
        System.out.println();
        /*
         * There are 3 versions of reduce() which combines stream objects into a single object
         * T reduce(T identity, BinaryOperator<T> accumulator)
         * Optional<T> reduce(BinaryOperator<T> acccumulator)
         * <U> reduce(U identity, BiFunction<U,? super t,U> accumulator, BinaryOperator<U> combiner)
         * 
         * An identity is the object holding our accumulated stream values
         * The accumulator combines the curr result with the curr value
         * the third param, combiner, lets us handle different types of objects 
         * 
         */

        Stream<String> streamOfStrings = Stream.of("1 ","param ","reduce");
        String sentence= streamOfStrings.reduce("",String::concat);
        System.out.println(sentence);
            

        Stream<String> streamOfStrings2 = Stream.of("2 ","param ","reduce");
        String sentence2= streamOfStrings2.reduce("",String::concat);
        System.out.println(sentence2);

        Stream<String> streamOfStrings3 = Stream.of("3 ","param ","reduce");
        String sentence3= streamOfStrings3.reduce("",String::concat,(a,b)-> a+b.length());
        System.out.println(sentence3);

        /*
         * collect() performs a mutable reduction, lets us accumulate objects within a mutable object like ArrayList or StringBuilder
         * gets data out of streams in one form and converts to another form
         * collect(Supplier<R> s, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
         * collect(Collector<? super T,A,R> collector)
         */

        Stream<String> stream = Stream.of("a","b","c","d");
        StringBuilder word= stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println(word);

        Stream<String> stream2 = Stream.of("a","b","c","d");
        TreeSet<String> set= stream2.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println(set);

        
        Stream<String> oneparam= Stream.of("one","param","collect");
        Set<String> wordset= oneparam.collect(Collectors.toSet());
        System.out.println(wordset);
    }
}
