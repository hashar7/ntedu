package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	ArrayVector t0 = new ArrayVectorImpl();
	t0.set(2.3, 4.5, 6.7, -9.12);
	ArrayVector t1 = new ArrayVectorImpl();
	System.out.println("t0: " + Arrays.toString(t0.get()));
        System.out.println("t1: " + Arrays.toString(t1.get()));
        ArrayVector t2 = t0.clone();
        System.out.println("t2: " + Arrays.toString(t2.get()));
        t2.set(0, 2.9);
        System.out.println("t0: " + Arrays.toString(t0.get()));
        System.out.println("t2: " + Arrays.toString(t2.get()));
        System.out.println("t0 size: " + t0.getSize());
        System.out.println("t1 size: " + t1.getSize());
        t0.set(10, 99.8);
        System.out.println("t0 size: " + t0.getSize());
        System.out.println("t0: " + Arrays.toString(t0.get()));
        System.out.println("t0[2]: " + t0.get(2));
        System.out.println("t0 max: " + t0.getMax());
        System.out.println("t0 min: " + t0.getMin());
        t0.sortAscending();
        System.out.println("t0 sorted: " + Arrays.toString(t0.get()));
        //System.out.println("t0[-1]: " + t0.get(-1));
    }
}

