package com.learnjava.parallelstreams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.stopWatchReset;
import static com.learnjava.util.CommonUtil.timeTaken;
import static java.util.stream.Collectors.toList;

public class ArrayListSpliteratorExample {

    public List<Integer> multiplyEachValue(ArrayList<Integer> list, int multiplier, boolean isParallel) {
        startTimer();
        Stream<Integer> integerStream = isParallel ? list.stream() : list.parallelStream();

        List<Integer> resultList = integerStream
                .map(i -> i * multiplier)
                .collect(toList());

        timeTaken();
        stopWatchReset();
        return resultList;
    }
}
