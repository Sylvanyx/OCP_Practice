package functional_programming.Streams;

import java.util.function.*;

public class FunctionalInterfacesForPrimitives {

    public static void main(String[] args) {
        DoubleSupplier doubleSupp = () -> 0.0;
        IntSupplier intSupp = () -> 0;
        LongSupplier longSupp = () -> 0L;

        DoubleConsumer doubleCons = (x) -> System.out.print(x);
        IntConsumer intConsumer = (x) -> System.out.println(x);
        LongConsumer longConsumer = (x) -> System.out.println(x);

    }

}
