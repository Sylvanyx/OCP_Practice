package functional_programming.Streams;


import java.util.DoubleSummaryStatistics;
import java.util.OptionalDouble;
import java.util.stream.*;


        /*
         * Three primitive stream types
         * IntStream
         * LongStream
         * DoubleStream
         * 
         * Primitive streams are special streams to make working with primitives easier
         * 
         * Methods on primitive streams
         * OptionalDouble average()
         * Stream<T> boxed()
         * 
         * max() which returns Optional Int/Long/Double
         * min() with same return types as max()
         * 
         * IntStream range(int a,int b) exclusive a to b-1
         * LongStream range(long a,long b)
         * 
         * IntStream rangeClosed(int a,int b) inclusive a to b 
         * LongStream rangeClosed(long a,long b)
         *  
         * int/double/long sum()
         * int/long/double SummaryStatistics()
         */

public class PrimitiveStreamExps {


    public static void main(String[] args) {   
        Stream<Integer> strm = Stream.of();
        IntStream intStrm = strm.mapToInt(x -> x);

        // since intStrm never ran a terminal method attempting to print retruns a reference to the string pipeline
        System.out.println(intStrm); //java.util.stream.ReferencePipeline$4@4b67cf4d
        System.out.println(intStrm.sum());

        IntStream another= IntStream.of(1,2,3,4);
        OptionalDouble optD = another.average();
        System.out.println(optD.getAsDouble());
        

        IntStream streamInt= IntStream.range(0,10);
        IntStream streamInt2= IntStream.rangeClosed(0,10);

        streamInt.forEach(System.out::print);
        System.out.println();

        streamInt2.forEach(System.out::print);
        System.out.println();


        DoubleStream dstrm= DoubleStream.generate(Math::random);
        Stream<Double> doublestream= dstrm.boxed(); // boxed() converts primitiveStream to regular stream
        DoubleStream dstrm2 = doublestream.mapToDouble(x->(double) x);

        // Below line calls summarystatistics on an infinite stream, summaryStatistics will try to process all elements and cause code to hang
        // DoubleSummaryStatistics dsum= dstrm2.summaryStatistics();

        DoubleSummaryStatistics dsum= dstrm2.limit(10).summaryStatistics();
        System.out.println(dsum.getAverage());
    }
}
