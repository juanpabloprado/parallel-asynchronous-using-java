package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.stopWatchReset;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelStreamPerformanceTest {

    ParallelStreamPerformance intStreamExample = new ParallelStreamPerformance();

    @Test
    void sumUsingIntStream() {
        //given

        //when
        startTimer();
        int sum = intStreamExample.sumUsingIntStream(1000000, false);
        timeTaken();
        System.out.println("sum : " + sum);

        //then
        assertEquals(1784293664, sum);
        stopWatchReset();
    }

    @Test
    void sumUsingIntStreamParallel() {
        //given


        //when
        startTimer();
        int sum = intStreamExample.sumUsingIntStream(1000000, true);
        timeTaken();
        System.out.println("sum : " + sum);

        //then
        assertEquals(1784293664, sum);
        stopWatchReset();
    }

    @Test
    void sumUsingIterate() {
        //given

        //when
        startTimer();
        int sum = intStreamExample.sumUsingIterate(1000000, false);
        timeTaken();
        System.out.println("sum : " + sum);

        //then
        assertEquals(1784293664, sum);
        stopWatchReset();
    }

    @Test
    void sumUsingIterateParallel() {
        //given

        //when
        startTimer();
        int sum = intStreamExample.sumUsingIterate(1000000, true);
        timeTaken();
        System.out.println("sum : " + sum);

        //then
        assertEquals(1784293664, sum);
        stopWatchReset();
    }

    @Test
    void sumUsingList() {
        //given
        int size = 1000000;
        List<Integer> inputList = DataSet.generateArrayList(size);
        //when
        startTimer();
        int sum = intStreamExample.sumUsingList(inputList, false);
        timeTaken();
        System.out.println("sum : " + sum);

        //then
        assertEquals(1784293664, sum);
        stopWatchReset();
    }

    @Test
    void sumUsingListParallel() {
        //given
        int size = 1000000;
        List<Integer> inputList = DataSet.generateArrayList(size);
        //when
        startTimer();
        int sum = intStreamExample.sumUsingList(inputList, true);
        timeTaken();
        System.out.println("sum : " + sum);

        //then
        assertEquals(1784293664, sum);
        stopWatchReset();
    }

}