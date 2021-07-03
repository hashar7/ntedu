package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        ComplexNumber x = new ComplexNumberImpl();
        ComplexNumber y = new ComplexNumberImpl();
        ComplexNumber z = new ComplexNumberImpl();
        ComplexNumber[] array = new ComplexNumber[5];
        array[0] = new ComplexNumberImpl();
        array[1] = new ComplexNumberImpl();
        array[2] = new ComplexNumberImpl();
        array[3] = new ComplexNumberImpl();
        array[4] = new ComplexNumberImpl();
        array[0].set("-1");
        array[1].set("-0.5+0.5i");
        array[2].set("99+99.0i");
        array[3].set("-44-44.0i");
        array[4].set("10-10.0i");
        x.sort(array);
        System.out.println("0: " + array[0].toString());
        System.out.println("1: " + array[1].toString());
        System.out.println("2: " + array[2].toString());
        System.out.println("3: " + array[3].toString());
        System.out.println("4: " + array[4].toString());

        x.set("-12.3+95.3i");
        System.out.println(x.getRe() + " " + x.getIm());
        y.set("45.32-88.99i");
        System.out.println(y.getRe() + " " + y.getIm());
        z.set("12-2i");
        System.out.println(z.getRe() + " " + z.getIm());
        x.set("12-i");
        System.out.println(x.getRe() + " " + x.getIm());
        x.set("-12.455+1.345i");
        System.out.println(x.getRe() + " " + x.getIm());
        System.out.println(x.copy().equals(x) + " " + (x.copy() != x));
        System.out.println(x.getRe() + " " + x.getIm());
        System.out.println(x.copy().getRe() + " " + x.copy().getIm());

        //ComplexNumber a = new ComplexNumberImpl(x);
    }
}
