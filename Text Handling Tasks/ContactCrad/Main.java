package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        String string = "BDAY:12-01-2004";
        String in = "TEL;TYPE=WORK:4951234567";
        String data = "BEGIN:VCARD\r\n" +
                "FN:Forrest Gump\r\n" +
                "ORG:Bubba Gump Shrimp Co.\r\n" +
                "GENDER:M\r\n" +
                "BDAY:06-06-1944\r\n" +
                "TEL;TYPE=WORK,VOICE:4951234567\r\n" +
                "TEL;TYPE=CELL,VOICE:9150123456\r\n" +
                "GENDER:F\r\n" +
                "END:VCARD";
        ContactCard test = new ContactCardImpl();
        test.getInstance(data);
        System.out.println(test.isWoman());
        System.out.println(test.getAge().toString());
        System.out.println(test.getAgeYears());
        System.out.println(test.getBirthday().toString());
        System.out.println(test.getFullName());
        System.out.println(test.getOrganization());
        System.out.println(test.getPhone("WORK,VOICE"));
    }
}


