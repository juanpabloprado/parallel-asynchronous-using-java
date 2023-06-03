package com.learnjava.parallelstreams;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class ParallelStreamPerformance {

    public int sumUsingIntStream(int count, boolean isParallel){
        IntStream intStream = IntStream.rangeClosed(0,count);

        if(isParallel)
            return intStream.parallel().sum();

        return intStream.sum();
    }


    public int sumUsingList(List<Integer> inputList, boolean isParallel){
        Stream<Integer> inputStream = inputList.stream();

        if(isParallel)
            return inputStream.parallel().mapToInt(Integer::intValue).sum();

        return inputStream
                .mapToInt(Integer::intValue) // unboxing
                .sum();
    }

    public int sumUsingIterate(int n, boolean isParallel){
         Stream<Integer> integerStream = Stream.
                 iterate(0, i ->i+1 );


        if(isParallel)
            return integerStream.parallel().limit(n+1).reduce(0, Integer::sum);

        return integerStream
                .limit(n+1) // includes the end value too
                .reduce(0, Integer::sum);
    }



}