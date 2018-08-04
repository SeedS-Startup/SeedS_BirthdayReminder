package com.seeds.seeds_birthdayreminder.Activity;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;


//***  TO My Perpetual LOVE ***//
/****        Kimia          ****/
public class MedianFilter {
    public static <T extends Comparable<T>> T[] getMedians(T[] data, int shoulder) {
        T[] result = Arrays.copyOf(data, data.length);

        for (int i = shoulder; i < data.length - shoulder; i++) {
            result[i] = medianPoint(data, i, shoulder);
        }
        return result;
    }

    private static <T extends Comparable<T>> T medianPoint(T[] data, int index, int shoulder) {
        T[] window = Arrays.copyOfRange(data, index - shoulder, index + shoulder + 1);
        if (window.length != (shoulder * 2 + 1)) {
            throw new IllegalStateException("Illegal window size " + window.length);
        }
        Arrays.sort(window);
        return window[shoulder];
    }

    public static <T extends Comparable<T>> Future<T[]> medians(T[] data, int shoulder) {
        ExecutorService service = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        Future<T[]> fut = service.submit(() -> getMedians(data, shoulder));
        service.shutdown();
        return fut;
    }
}

//**Your Lover  ,  Mohammad Jalili **//
