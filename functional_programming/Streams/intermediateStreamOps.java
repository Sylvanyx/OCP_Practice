package functional_programming.Streams;

import java.util.List;
import java.util.stream.*;

public class intermediateStreamOps {
    
    public static void main(String[] args) {
        
        //filter() returns the values matching the Predicate
        List<String> ls= List.of("intermediate ", "remove","operations ","dont ","end ","streams ");
        Stream <String> str= ls.stream().filter(x -> !x.startsWith("r"));
        str.forEach(System.out::print);

        //distinct should need no explanation, same as adding to a set
        Stream<String> unique= Stream.of("a","b","a","c");
        unique.distinct().collect(Collectors.toList()).forEach(System.out::print);

        //limit() and skip() help control processing
        Stream<String> strings= Stream.of("a","b","c","d","e","f","g","h","i","j");
        strings.skip(5).limit(7L).collect(Collectors.toList()).forEach(System.out::print);
        System.out.println();

        //map() creates a one to one mapping of elements in the stream to the elements from the next step in the stream
        // it does not create a map but maps objects to diff types
        // <R> Stream<R> map(Function<? super T, ? extends R> mapper)
        Stream<String> toMap = Stream.of("one","two","three");
        toMap.map(String::length).forEach(System.out::print);
        System.out.println();

        List<String> zero= List.of();
        var one= List.of("one ");
        var two= List.of("two ");
        Stream<List<String>> vals= Stream.of(zero,one,two);
        vals.flatMap(m -> m.stream()).forEach(System.out::print);
        System.out.println();

        Stream<String> unsorted= Stream.of("z","y","x","w","v","u");
        unsorted.sorted().forEach(System.out::print);
        System.out.println();

        Stream<String> peekable= Stream.of("first","second","third");
        peekable.filter(s -> s.startsWith("f")).peek(System.out::println).count();
        
    }
}
