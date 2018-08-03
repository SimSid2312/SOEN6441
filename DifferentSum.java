import java.util.stream.LongStream;
import java.util.stream.Stream;
public class DifferentSum {


    //iterativeSum
    public static long iterativeSum(long n)
    {
        long result=0L;
        for(long i=1L;i<=n;i++)
        {
            result+=i;
        }
        return  result;
    }

    //sequentialSum and iterative

    public static long sequentialSum(long n)
    {
        return Stream.iterate(1L,i-> i+1L)
                      .limit(n)
                      .reduce(Long::sum)
                      .get();
    }

    //parallelSum and iterative
    public static long parallelSum(long n)
    {
      return Stream.iterate(1L,i->i+1L)
                    .limit(n)
                    .parallel()
                    .reduce(Long::sum)
                    .get();
    }

    //rangedSum and sequential
    public static long rangedSum(long n)
    {
        return LongStream.rangeClosed(1L,n)
                          .reduce(Long::sum)
                          .getAsLong();

    }


    //rangedSum and parallel
    public static long parallelrangedSum(long n)
    {
        return LongStream.rangeClosed(1L,n)
                .parallel()
                .reduce(Long::sum)
                .getAsLong();

    }

    //Accumulator and sideEffect and sequential
    public static long sideEffectSum(long n) {

        Accumulator acc=new Accumulator();
        LongStream.rangeClosed(1L,n)
                .forEach(acc::sum);
        return Accumulator.tot;


    }

    //Accumulator and sideEffect and parallel
    public static long parallelsideEffectSum(long n) {

        Accumulator acc=new Accumulator();
        LongStream.rangeClosed(1L,n)
                .parallel()
                .forEach(acc::sum);
        return Accumulator.tot;


    }



    public static class Accumulator{

      private static long tot=0L;

      public void sum (long i)
      {
          tot+=i;

      }
    }

}
