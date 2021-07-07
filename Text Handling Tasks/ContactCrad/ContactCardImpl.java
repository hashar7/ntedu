package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ContactCardImpl implements ContactCard {

    public enum Gender {
        MALE('M'),
        FEMALE('F');
        private final char gender;

        Gender(char gender) {
            this.gender = gender;
        }
    }

    public enum Obligatory {
        BEGIN_VCARD("BEGIN:VCARD"),
        FN("FN"),
        ORG("ORG"),
        END_VCARD("END:VCARD");
        private final String field;

        Obligatory(String field) {
            this.field = field;
        }
    }

    public enum Additional {
        GENDER("GENDER"),
        BIRTHDAY("BDAY"),
        TELEPHONE("TEL;TYPE=");
        private final String field;

        Additional(String field) {
            this.field = field;
        }
    }

    private String fullName;
    private String organization;
    private char gender;
    private Calendar birthday;
    private final Map<String, String> phones;

    public ContactCardImpl() {
        fullName = null;
        organization = null;
        gender = 0;
        birthday = null;
        phones = new HashMap<>();
    }

    /**
     * Основной метод парсинга: создает экземпляр карточки из источника данных (Scanner),
     * содержащего следующие текстовые данные о человеке (5 полей):<br/>
     * 1) FN - Полное имя - обязательное поле<br/>
     * 2) ORG - Организация - обязательное поле<br/>
     * 3) GENDER - Пол - один символ: F или M. Это поле в данных может отсутствовать.<br/>
     * 4) BDAY - Дата рождения - в следующем формате: "DD-MM-YYYY", где DD - 2 цифры, обозначающие день,
     * MM - 2 цифры, обозначающие месяц, YYYY - 4 цифры, обозначающие год.
     * Это поле в данных может отсутствовать.<br/>
     * 5) TEL - Номер телефона - ровно 10 цифр, не включающие код страны. Полей TEL может быть 0 или несколько,
     * и разные поля TEL различаются значением обязательного атрибута TYPE, например:
     * TEL;TYPE=HOME,VOICE:4991112233<br/>
     * <p/>
     * Каждое из этих полей в источнике данных расположено на отдельной строке;
     * строки в стандарте vCard отделяются символом CRLF (\r\n).<br/>
     * Имя поля отделяется от его значения двоеточием, например: GENDER:F<br/>
     * Если нужно, можно предположить, что порядок полей будет именно такой, как выше.<br/>
     * Но первой строкой всегда идет BEGIN:VCARD, последней - END:VCARD.<br/>
     * Пример содержимого Scanner:<br/>
     * <code>
     * BEGIN:VCARD
     * FN:Forrest Gump
     * ORG:Bubba Gump Shrimp Co.
     * BDAY:06-06-1944
     * TEL;TYPE=WORK,VOICE:4951234567
     * TEL;TYPE=CELL,VOICE:9150123456
     * END:VCARD
     * </code>
     * <p/>
     * ПРИМЕЧАНИЕ: Такой метод в реальных приложениях был бы static, однако
     * система проверки учебных задач проверяет только не-статические методы.
     *
     * @param scanner Источник данных
     * @return {@link ContactCard}, созданный из этих данных
     * @throws InputMismatchException Возникает, если структура или значения данных не соответствуют формату,
     *                                описанному выше; например, если после названия поля нет двоеточия или дата указана в ином формате
     *                                или номер телефона содержит неверное число цифр.
     * @throws NoSuchElementException Возникает, если данные не содержат обязательных полей
     *                                (FN, ORG, BEGIN:VCARD, END:VCARD)
     */
    @Override
    public ContactCard getInstance(Scanner scanner) throws InputMismatchException, NoSuchElementException {
        String in = scanner.nextLine();
        if (!in.equals(Obligatory.BEGIN_VCARD.field)) {
            throw new InputMismatchException();
        }
        while (scanner.hasNextLine()) {
            in = scanner.nextLine();
            if (in.startsWith(Obligatory.FN.field)) {
                if (!in.startsWith(Obligatory.FN.field + ":")) {
                    throw new InputMismatchException();
                }
                fullName = in.substring(3);
                continue;
            }
            if (in.startsWith(Obligatory.ORG.field)) {
                if (!in.startsWith(Obligatory.ORG.field + ":")) {
                    throw new InputMismatchException();
                }
                organization = in.substring(4);
                continue;
            }
            if (in.startsWith(Additional.GENDER.field)) {
                if (!in.startsWith(Additional.GENDER.field + ":") || in.substring(in.lastIndexOf(":") + 1).length() != 1) {
                    throw new InputMismatchException();
                }
                switch (in.substring(in.lastIndexOf(":") + 1)) {
                    case "M":
                        gender = Gender.MALE.gender;
                        break;
                    case "F":
                        gender = Gender.FEMALE.gender;
                        break;
                    default:
                        throw new InputMismatchException("Пол не равен M или F!");
                }
                continue;
            }
            if (in.startsWith(Additional.BIRTHDAY.field)) {
                if (!in.startsWith(Additional.BIRTHDAY.field + ":") || !in.substring(5).matches("\\d{2}-\\d{2}-\\d{4}")) {
                    throw new InputMismatchException();
                }
                String date = in.substring(5).trim();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                birthday = new GregorianCalendar();
                try {
                    birthday.setTime(sdf.parse(date));
                } catch (ParseException e) {
                    throw new InputMismatchException();
                }
                continue;
            }
            if (in.startsWith(Additional.TELEPHONE.field)) {
                if (!in.startsWith(Additional.TELEPHONE.field) || !in.matches("TEL;TYPE=(\\w*,)*\\w+:\\d{10}")) {
                    throw new InputMismatchException();
                }
                String phoneNumber = in.substring(in.lastIndexOf(":") + 1);
                phoneNumber = "(" + phoneNumber.substring(0, 3) + ") " + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
                phones.put(in.substring(in.lastIndexOf("=") + 1, in.indexOf(":")), phoneNumber);
                continue;
            }
            if (!in.matches("(\\w*)+;?(\\w*)?=?(\\w*,)*\\w+:(.*)")) {
                throw new InputMismatchException();
            }
        }
        if (!in.equals(Obligatory.END_VCARD.field) || this.fullName == null || this.organization == null) {
            throw new InputMismatchException();
        }
        return this;
    }

    /**
     * Метод создает {@link Scanner} и вызывает {@link #getInstance(Scanner)}
     *
     * @param data Данные для разбора, имеющие формат, описанный в {@link #getInstance(Scanner)}
     * @return {@link ContactCard}, созданный из этих данных
     */
    @Override
    public ContactCard getInstance(String data) {
        return getInstance(new Scanner(data));
    }

    /**
     * @return Полное имя - значение vCard-поля FN: например, "Forrest Gump"
     */
    @Override
    public String getFullName() {
        return fullName;
    }

    /**
     * @return Организация - значение vCard-поля ORG: например, "Bubba Gump Shrimp Co."
     */
    @Override
    public String getOrganization() {
        return organization;
    }

    /**
     * Если поле GENDER отсутствует в данных или равно "M", этот метод возвращает false
     *
     * @return true если этот человек женского пола (GENDER:F)
     */
    @Override
    public boolean isWoman() {
        return (gender != 0) && gender != 'M';
    }

    /**
     * ПРИМЕЧАНИЕ: в современных приложениях рекомендуется для работы с датой применять java.time.LocalDate,
     * однако такие классы как java.util.Calendar или java.util.Date необходимо знать.
     *
     * @return День рождения человека в виде {@link Calendar}
     * @throws NoSuchElementException Если поле BDAY отсутствует в данных
     */
    @Override
    public Calendar getBirthday() throws NoSuchElementException {
        if (birthday == null) {
            throw new NoSuchElementException();
        }
        return birthday;
    }

    /**
     * ПРИМЕЧАНИЕ: В реализации этого метода рекомендуется использовать {@link DateTimeFormatter}
     *
     * @return Возраст человека на данный момент в виде {@link Period}
     * @throws NoSuchElementException Если поле BDAY отсутствует в данных
     */
    @Override
    public Period getAge() throws NoSuchElementException {
        if (birthday == null) {
            throw new NoSuchElementException();
        }
        LocalDate startDate = LocalDate.of(birthday.get(Calendar.YEAR),
                birthday.get(Calendar.MONTH) + 1,
                birthday.get(Calendar.DAY_OF_MONTH));
        LocalDate endDate = LocalDate.now();
        return Period.between(startDate, endDate);
    }

    /**
     * @return Возраст человека в годах: например, 74
     * @throws NoSuchElementException Если поле BDAY отсутствует в данных
     */
    @Override
    public int getAgeYears() throws NoSuchElementException {
        if (birthday == null) {
            throw new NoSuchElementException();
        }
        return getAge().getYears();
    }

    /**
     * Возвращает номер телефона в зависимости от типа.
     *
     * @param type Тип телефона, который содержится в данных между строкой "TEL;TYPE=" и двоеточием
     * @return Номер телефона - значение vCard-поля TEL, приведенное к следующему виду: "(123) 456-7890"
     * @throws NoSuchElementException если в данных нет телефона указанного типа
     */
    @Override
    public String getPhone(String type) throws NoSuchElementException {
        if (phones.isEmpty() || !phones.containsKey(type)) {
            throw new NoSuchElementException();
        }
        return phones.get(type);
    }
}
