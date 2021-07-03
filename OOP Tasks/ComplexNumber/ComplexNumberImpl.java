package com.company;

import java.util.Arrays;

public class ComplexNumberImpl implements ComplexNumber {

    private Double re;
    private Double im;

    /**
     * Default constructor
     */
    public ComplexNumberImpl() {
        re = 0.0;
        im = 0.0;
    }

    /**
     * Constructor from two doubles where:
     *
     * @param re — real part of complex number
     * @param im — imaginary part of complex number
     */
    public ComplexNumberImpl(Double re, Double im) {
        this.re = re;
        this.im = im;
    }

    /**
     * Copy constructor
     *
     * @param num — complex number to copy
     */
    public ComplexNumberImpl(ComplexNumberImpl num) {
        this.re = num.re;
        this.im = num.im;
    }

    /**
     * @return real part of this complex number
     */
    @Override
    public double getRe() {
        return re;
    }

    /**
     * @return imaginary part of this complex number
     */
    @Override
    public double getIm() {
        return im;
    }

    /**
     * @return true if this complex number has real part only (otherwise false)
     */
    @Override
    public boolean isReal() {
        return Math.abs(im) < 2 * Double.MIN_VALUE;
    }

    /**
     * Sets both real and imaginary part of this number.
     *
     * @param re
     * @param im
     */
    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    /**
     * Parses the given string value and sets the real and imaginary parts of this number accordingly.<br/>
     * The string format is "re+imi", where re and im are numbers (floating point or integer) and 'i' is a special symbol
     * denoting imaginary part (if present, it's always the last character in the string).<br/>
     * Both '+' and '-' symbols can be the first characters of re and im; but '*' symbol is NOT allowed.<br/>
     * Correct examples: "-5+2i", "1+i", "+4-i", "i", "-3i", "3". Incorrect examples: "1+2*i", "2+2", "j".<br/>
     * Note: explicit exception generation is an OPTIONAL requirement for this task,
     * but NumberFormatException can be thrown by {@link Double#parseDouble(String)}).<br/>
     * Note: it is not reasonable to use regex while implementing this method: the parsing logic is too complicated.
     *
     * @param value
     * @throws NumberFormatException if the given string value is incorrect
     */
    @Override
    public void set(String value) throws NumberFormatException {
        Double real = 0.0;
        Double imag = 0.0;
        if (value == null) {
            throw new NumberFormatException();
        }
        value = value.replace(" ", "");
        if (value.contains("+")) {
            String[] split = value.split("\\+");
            if (split.length > 2) {
                throw new NumberFormatException();
            }
            if (split[0].matches("-[0-9]+\\.[0-9]+") || split[0].matches("[0-9]+\\.[0-9]+") ||
                    split[0].matches("-[0-9]+") || split[0].matches("[0-9]+")) {
                real = Double.parseDouble(split[0]);

            } else {
                throw new NumberFormatException();
            }
            if (split[1].matches("[0-9]+\\.[0-9]+i") || split[1].matches("[0-9]+i") || split[1].equals("i")) {
                if (split[1].equals("i")) {
                    imag = 1.0;
                } else {
                    split[1] = split[1].replace("i", "");
                    imag = Double.parseDouble(split[1]);
                }
            } else {
                throw new NumberFormatException();
            }
        } else if (value.contains("-") && value.length() - value.replace("-", "").length() > 1) {
            String[] split = value.split("-");
            if (split.length == 3) {
                if (!split[0].equals("")) {
                    throw new NumberFormatException();
                }
                if (split[1].matches("[0-9]+\\.[0-9]+") || split[1].matches("[0-9]+")) {
                    real = -Double.parseDouble(split[1]);
                } else {
                    throw new NumberFormatException();
                }
                if (split[2].matches("[0-9]+\\.[0-9]+i") || split[2].matches("[0-9]+i") || split[2].equals("i")) {
                    if (split[2].equals("i")) {
                        imag = -1.0;
                    } else {
                        split[2] = split[2].replace("i", "");
                        imag = -Double.parseDouble(split[2]);
                    }
                } else {
                    throw new NumberFormatException();
                }
            } else {
                throw new NumberFormatException();
            }
        } else if (value.contains("-") && !value.startsWith("-")) {
            String[] split = value.split("-");
            if (split[0].matches("[0-9]+\\.[0-9]+") || split[0].matches("[0-9]+")) {
                real = Double.parseDouble(split[0]);
            } else {
                throw new NumberFormatException();
            }
            if (split[1].matches("[0-9]+\\.[0-9]+i") || split[1].matches("[0-9]+i") || split[1].equals("i")) {
                if (split[1].equals("i")) {
                    imag = -1.0;
                } else {
                    split[1] = split[1].replace("i", "");
                    imag = -Double.parseDouble(split[1]);
                }
            } else {
                throw new NumberFormatException();
            }
        } else if (value.contains("i")) {
            if (value.matches("-[0-9]+\\.[0-9]+i") || value.matches("[0-9]+\\.[0-9]+i") ||
                    value.matches("-[0-9]+i") || value.matches("[0-9]+i") || value.equals("i") || value.equals("-i")) {
                if (value.equals("i")) {
                    imag = 1.0;
                } else if (value.equals("-i")) {
                    imag = -1.0;
                } else {
                    value = value.replace("i", "");
                    imag = Double.parseDouble(value);
                }
            } else {
                throw new NumberFormatException();
            }
        } else if (value.matches("-[0-9]+\\.[0-9]+") || value.matches("[0-9]+\\.[0-9]+") ||
                value.matches("-[0-9]+") || value.matches("[0-9]+")) {
            real = Double.parseDouble(value);
        } else {
            throw new NumberFormatException();
        }
        re = real;
        im = imag;
    }

    /**
     * Checks whether some other object is "equal to" this number.
     *
     * @param arg2 Any implementation of {@link ComplexNumber} interface (may not )
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object arg2) {
        if (!(arg2 instanceof ComplexNumber)) return false;
        ComplexNumber tmp = (ComplexNumber) arg2;
        return this.re == tmp.getRe() && this.im == tmp.getIm();
    }

    /**
     * Creates and returns a copy of this object: <code>x.copy().equals(x)</code> but <code>x.copy()!=x</code>.
     *
     * @return the copy of this number
     * @see #clone()
     */
    @Override
    public ComplexNumber copy() {
        return new ComplexNumberImpl(this);
    }

    /**
     * Creates and returns a copy of this object: the same as {@link #copy()}.<br/>
     * Note: when implemented in your class, this method overrides the {@link Object#clone()} method but changes
     * the visibility and the return type of the Object's method: the visibility modifier is changed
     * from protected to public, and the return type is narrowed from Object to ComplexNumber (see also
     * <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#d5e11368">covariant types example from JLS</a>).
     *
     * @return the copy of this number
     * @see Object#clone()
     */
    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return new ComplexNumberImpl(this);
    }

    /**
     * Returns a string representation of this number, which must be compatible with {@link #set(String)}:
     * for any ComplexNumber x, the code <code>x.set(x.toString());</code> must not change x.<br/>
     * For example: 12.5-1.0i or 0.0 or 0.3333333333333333i<br/>
     * If the imaginary part of the number is 0, only "re" must be returned (where re is the real part).<br/>
     * If the real part of the number is 0 and the imaginary part is not 0,
     * "imi" must be returned (where im is the imaginary part).<br/>
     * Both re and im must be converted to string "as is" - without truncation of last digits,
     * i.e. the number of characters in their string representation is not limited
     * (it is determined by {@link Double#toString(double)}).
     *
     * @see Object#toString()
     */
    @Override
    public String toString() {
        if (Math.abs(im) < 2 * Double.MIN_VALUE) {
            return re.toString();
        } else if (Math.abs(re) < 2 * Double.MIN_VALUE) {
            return im + "i";
        }
        return re + (im > 0 ? "+" : "") + im + "i";
    }

    /**
     * Compares this number with the other number by the absolute values of the numbers:
     * x < y if and only if |x| < |y| where |x| denotes absolute value (modulus) of x.<br/>
     * Can also compare the square of the absolute value which is defined as the sum
     * of the real part squared and the imaginary part squared: |re+imi|^2 = re^2 + im^2.
     *
     * @param other the object to be compared with this object.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the given object.
     * @see Comparable#compareTo(Object)
     */
    @Override
    public int compareTo(ComplexNumber other) {
        Double mod1 = re * re + im * im;
        Double mod2 = other.getRe() * other.getRe() + other.getIm() * other.getIm();
        return Math.abs(mod1 - mod2) < 2 * Double.MIN_VALUE ? 0 : mod1 < mod2 ? -1 : 1;
    }

    /**
     * Sorts the given array in ascending order according to the comparison rule defined in
     * {@link #compareTo(ComplexNumber)}.<br/>
     * It's strongly recommended to use {@link Arrays} utility class here
     * (and do not transform the given array to a double[] array).<br/>
     * Note: this method could be static: it does not use this instance of the ComplexNumber.
     * Nevertheless, it is not static because static methods can't be overridden.
     *
     * @param array an array to sort
     */
    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
    }

    /**
     * Changes the sign of this number. Both real and imaginary parts change their sign here.
     *
     * @return this number (the result of negation)
     */
    @Override
    public ComplexNumber negate() {
        this.re = -this.re;
        this.im = -this.im;
        return this;
    }

    /**
     * Adds the given complex number arg2 to this number. Both real and imaginary parts are added.
     *
     * @param arg2 the second operand of the operation
     * @return this number (the sum)
     */
    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        this.re += arg2.getRe();
        this.im += arg2.getIm();
        return this;
    }

    /**
     * Multiplies this number by the given complex number arg2. If this number is a+bi and arg2 is c+di then
     * the result of their multiplication is (a*c-b*d)+(b*c+a*d)i<br/>
     * The method should work correctly even if arg2==this.
     *
     * @param arg2 the second operand of the operation
     * @return this number (the result of multiplication)
     */
    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        Double real = re;
        Double imag = im;
        this.re = (real * arg2.getRe()) - (imag * arg2.getIm());
        this.im = (real * arg2.getIm()) + (imag * arg2.getRe());
        return this;

    }
}
