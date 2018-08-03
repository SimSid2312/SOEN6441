import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

public class DifferentSumHarness {

	public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void main(String[] args) {
        System.out.println("Iterative Sum done in: " + measurePerf(DifferentSum::iterativeSum, 10_000_000L) + " msecs");
        System.out.println("Sequential Sum done in: " + measurePerf(DifferentSum::sequentialSum, 10_000_000L) + " msecs");
        System.out.println("Parallel Sum done in: " + measurePerf(DifferentSum::parallelSum, 10_000_000L) + " msecs" );
        System.out.println("RangeClosed Sum done in: " + measurePerf(DifferentSum::rangedSum, 10_000_000L) + " msecs");
       System.out.println("RangeClosed Sum with parallel done in: " + measurePerf(DifferentSum::parallelrangedSum, 10_000_000L) + " msecs" );
      
        System.out.println("SideEffect with RangeClosed: " + measurePerf(DifferentSum::sideEffectSum, 10_000_000L) + " msecs" );
        System.out.println("SideEffect prallel sum with RangeClosed donein: " + measurePerf(DifferentSum::parallelsideEffectSum, 10_000_000L) + " msecs" );
        System.out.println("ForkJoin sum done in: " + measurePerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000L) + " msecs" );
    
    }


    public static <T, R> long measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
           // System.out.println("Result: " + result);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
