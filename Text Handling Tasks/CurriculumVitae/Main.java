package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        System.out.println("hello world!");

        CurriculumVitae tt = new CurriculumVitaeImpl();
        tt.setText("...st Campus Center +1((609) 234-5678]\n" +
                "Princeton, NJ 08544...");
        tt.hidePhone("(609) 234-5678");
        System.out.println(tt.getText());

        final String NAME_PATTERN1 = "([A-Z][a-z]*[a-z|\\.])\\s([A-Z][a-z]*[a-z|\\.])(\\s[A-Z][a-z]*[a-z|\\.])?";
        Pattern pattern = Pattern.compile(NAME_PATTERN1);
        String t = "Hskmd s sbnid* sdanm a8d jd ka j9 sakn d8\n" +
                " sdn j i9 jkad9 amm sdjkw9 njws m. Amd Msd. dimow2\n" +
                "sldm Msd. dda 98ad .da Mike Jr. Mdf Kjmw wkjdudkm\n" +
                "ajkdnw w9 dmwdm Ja Mak. Fort";
        Matcher matcher = pattern.matcher(t);

        System.out.println(t.matches(NAME_PATTERN1));

        if (matcher.find()) {
            System.out.println("Found group: " + matcher.group() + " counter: " + matcher.groupCount());
        } else {
            System.out.println("Error occurred!");
        }

        String[] split = matcher.group().split("\\s");
        System.out.println(matcher.group().substring(matcher.group().lastIndexOf(' ') + 1));

        while (matcher.find()) {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Group num " + i + " is: " + matcher.group(i));
            }
            System.out.println(matcher.group());
        }

        String input = "Hello Java! Hello JavaScript! JavaSE 8.";
        pattern = Pattern.compile("Java(\\w*)");
        matcher = pattern.matcher(input);
        while (matcher.find())
            System.out.println(matcher.group());

        String regularExpression = "\\bjava\\b";
        String inputString = "java java java2ee java";
        Pattern ptrn = Pattern.compile(regularExpression);
        Matcher match = ptrn.matcher(inputString);
        int matchCounter = 0;

        System.out.println("Now we will use method find()...");
        while (match.find()) {
            matchCounter++;
            System.out.println("start(): " + match.start());
            System.out.println("end(): " + match.end());
            System.out.println("Number of match: " + matchCounter);
        }

        Scanner scanner = new Scanner(System.in);
        final String PHONE_PATTERN =
                "(\\(?([1-9][0-9]{2})\\)?[-. ]*)?([1-9][0-9]{2})[-. ]*(\\d{2})[-. ]*(\\d{2})(\\s*ext\\.?\\s*([0-9]+))?";
        //String text = scanner.nextLine();
        String text;
        text = "asdka 9213 salnd laks oasnd (916)125-4171 aslkdm \n" +
                "asdasd sadas skjn asdjwu ajsndkw as 495 926-93-47 ext.1846 asiojd idmoka\n " +
                "asdasd asdasd wq3ed s smwdjn oapoiw iopjwd 800 250 0890\n" +
                "adawdawdaw asdawd 250 7777";
        List<String> phones = new ArrayList<>();
        Pattern p = Pattern.compile(PHONE_PATTERN);
        Matcher m = p.matcher(text);

        System.out.println("Groups: " + m.groupCount());
        while (m.find()) {
            for (int i = 1; i <= 7; i++) {
                System.out.println("Group num " + i + " is: " + m.group(i));
            }
            System.out.println(m.group());
        }
    }
}

