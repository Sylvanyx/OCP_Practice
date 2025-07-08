package functional_programming.Optionals;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResultsExp {
    public static void main(String[] args) {

        var zoo = Stream.of("gorilla", "alligator", "grizzly bear");
        String res = zoo.collect(Collectors.joining(", "));
        System.out.println(res);

        // averagingInt/Double/Long returns Double from the primitive type
        var zoo2 = Stream.of("gorilla", "alligator", "grizzly bear");
        Double res2 = zoo2.collect(Collectors.averagingInt(String::length)); //
        System.out.println(res2);

        // counting() convinience method
        var zoo3 = Stream.of("gorilla", "alligator", "grizzly bear");
        long res3 = zoo3.collect(Collectors.counting());
        System.out.println(res3);

        // convert to new collection
        var zoo4 = Stream.of("gorilla", "alligator", "grizzly bear");
        TreeSet<String> treeSet = zoo4.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);

        // Collecting into Maps

        // toMap() needs 2 functions to specify map and key, optionally it needs another
        // param to specify conflicts with the key value
        // an additional fourth param can specify implementation returned since toMap
        // will return any map implementation if not specified
        var zoo5 = Stream.of("gorilla", "alligator", "grizzly bear");
        Map<Integer, String> res5 = zoo5
                .collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + " " + s2));
        System.out.println(res5);

        var zoo6 = Stream.of("gorilla", "alligator", "grizzly bear");
        Map<Integer, String> res6 = zoo6
                .collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + " " + s2, TreeMap::new));
        System.out.println(res6);

        // Collect using grouping,Partitioning, and Mapping

        var zoo7 = Stream.of("gorilla", "baby bear cub", "grizzly bears");
        Map<Integer, List<String>> res7 = zoo7
                .collect(Collectors.groupingBy(String::length));
        System.out.println(res7);

        // we can pass downStream collectors to specify the collections we want to use
        var zoo8 = Stream.of("gorilla", "baby bear cub", "grizzly bears");
        Map<Integer, Set<String>> res8 = zoo8
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        System.out.println(res8);

        var zoo9 = Stream.of("gorilla", "baby bear cub", "grizzly bears");
        TreeMap<Integer, List<String>> res9 = zoo9
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
        System.out.println(res9);

        // PartitioningBy seperates stream values into groups of true or false
        // Even if no values were false a false key would still be present in map, same
        // for values. The two keys are guarenteed
        var zoo10 = Stream.of("gorilla", "baby bear cub", "grizzly bears");
        Map<Boolean, List<String>> res10 = zoo10
                .collect(Collectors.partitioningBy(s -> s.length() <= 10));
        System.out.println(res10);

        // You can also pass a downstream collector
        // we cannot change the type of map returned however like in groupingBy
        var zoo11 = Stream.of("gorilla", "baby bear cub", "grizzly bears");
        Map<Boolean, Set<String>> res11 = zoo11
                .collect(Collectors.partitioningBy(s -> s.length() <= 10, Collectors.toSet()));
        System.out.println(res11);

        var zoo12 = Stream.of("gorilla", "baby bear cub", "grizzly bears");
        var res12 = zoo12.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(s -> s.charAt(0),
                                Collectors.minBy((a, b) -> a - b))));
        System.out.println(res12);
    }
}
