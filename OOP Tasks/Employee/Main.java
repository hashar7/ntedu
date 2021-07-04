package com.company;

public class Main {

    public static void main(String[] args) {
        Employee m1 = new EmployeeImpl();
        Employee m2 = new EmployeeImpl();
        EmployeeImpl m22 = (EmployeeImpl) m2;
        Employee m3 = new EmployeeImpl();
        EmployeeImpl m33 = (EmployeeImpl) m3;
        Employee m4 = new EmployeeImpl();
        EmployeeImpl m44 = (EmployeeImpl) m4;
        Employee m5 = new EmployeeImpl();
        EmployeeImpl m55 = (EmployeeImpl) m5;

        m1.setFirstName("m1");
        m1.setLastName("m11");
        m1.increaseSalary(10000);

        m2.setFirstName("m2");
        m2.setLastName("m22");
        m2.increaseSalary(5000);
        m22.setManager(m1);

        m3.setFirstName("m3");
        m3.setLastName("m33");
        m3.increaseSalary(2500);
        //m33.setTopManager(m1);
        m33.setManager(m2);

        m4.setFirstName("m4");
        m4.setLastName("m44");
        m4.increaseSalary(17500);
        //m44.setTopManager(m1);
        m44.setManager(m2);

        m5.setFirstName("m5");
        m5.setLastName("m55");
        //m55.setTopManager(m2);
        m55.setManager(m3);

        System.out.println("m1 salary: " + m1.getSalary());
        System.out.println("m5 salary: " + m5.getSalary());
        System.out.println("m3 full name: " + m3.getFullName());
        System.out.println("m4 manager: " + m4.getManagerName());
        System.out.println("m4 top manager: " + m4.getTopManager().getFullName());
        System.out.println("m5 top manager: " + m5.getTopManager().getFullName());
    }
}
