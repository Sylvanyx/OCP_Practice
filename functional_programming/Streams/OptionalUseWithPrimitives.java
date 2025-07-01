package functional_programming.Streams;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;


public class OptionalUseWithPrimitives {
    
    public static int range(IntStream ints){
        IntSummaryStatistics stats= ints.summaryStatistics();
        if(stats.getCount()==0) throw new RuntimeException();
        return stats.getMax()-stats.getMin();
    }

    public static void main(String[] args) {
        var stream = IntStream.rangeClosed(0,10);
        OptionalDouble opt= stream.average();

        var empty= IntStream.empty();
        var emptyOpt= empty.average();


        opt.ifPresent(System.out::println);
        System.out.println(opt.getAsDouble());
        System.out.println(emptyOpt.orElseGet(() -> 0.0));
    
        var optInt= List.of(1,2,3,4,5).stream().mapToInt(x -> x);
        int sumOfAnInt = optInt.sum();
        System.out.println(sumOfAnInt);


        IntStream stream2= IntStream.iterate(1,x->x+1).limit(10);
        System.out.println(range(stream2));
    }

}
