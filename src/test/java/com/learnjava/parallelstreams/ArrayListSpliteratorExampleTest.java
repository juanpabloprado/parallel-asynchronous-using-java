package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListSpliteratorExampleTest {

    ArrayListSpliteratorExample listSpliteratorExample = new ArrayListSpliteratorExample();
    @RepeatedTest(5)
    void multiplyEachValue() {
        int size = 1_000_000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        List<Integer> resultList = listSpliteratorExample.multiplyEachValue(inputList, 2, false);

        assertEquals(size, resultList.size());
    }

    @RepeatedTest(5)
    void multiplyEachValueParallel() {
        int size = 1_000_000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        List<Integer> resultList = listSpliteratorExample.multiplyEachValue(inputList, 2, true);

        assertEquals(size, resultList.size());
    }
}