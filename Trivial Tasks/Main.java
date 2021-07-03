package com.company;

public class Main {
    static public void main(String[] args) {
        ControlFlowStatements1Impl test = new ControlFlowStatements1Impl();
	System.out.println("Hello, World!");
	System.out.println("Function at x = PI: " + test.getFunctionValue((float) Math.PI));
        System.out.println("Function at x = -PI: " + test.getFunctionValue((float) -Math.PI));
        System.out.println("Decoding weekday number 4: " + test.decodeWeekday(4));
        System.out.println("Minimal element of array: " + test.getMinValue(test.initArray()));
        System.out.println("Years when 100%: " + test.calculateBankDeposit(100.0).years);
	System.out.println("calculateSportsman(12.5): " + tmp.calculateSportsman((float) 12.5));
    }
}
