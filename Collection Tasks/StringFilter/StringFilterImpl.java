package com.company;

import java.util.*;

public class StringFilterImpl implements StringFilter {

    private final Set<String> strings;

    /**
     * Вложенный интерфейс Filter, единственный метод которого
     * делегировать проверку: удовлетворяет ли заданная строка
     * (из набора) критерию фильтрации.
     */
    private interface Filter {
        boolean match(String s);
    }

    /**
     * Метод, реализующий проверку аргумента в цикле по
     * строкам набора на соответствие заданному критерию совадения.
     * @param filter экземпляр, реализующий интерфес с заданной
     *               логикой поиска совпадений по строкам из набора.
     * @return итератор, содержащий строки, удовлетворяющие условиям.
     */
    private Iterator<String> getMatch(Filter filter) {
        Set<String> matches = new HashSet<>();
        for (String s: strings) {
            if(s == null) {
                continue;
            }
            if(filter.match(s)) {
                matches.add(s);
            }
        }
        return matches.iterator();
    }

    /**
     * Метод позволяющий выявить соответствие или не соответсвие строки заданному паттерну.
     * @param first строка, задающая паттерн.
     * @param second строка, проверяемая на соответствие заданному паттерну.
     * @return {@code true}, если {@param second} удовлетворяет паттерну {@param first},
     * иначе {@code false}.
     */
    private boolean StringMatch(String first, String second) {
        if (first.length() == 0 && second.length() == 0) {
            return true;
        }
        if (first.length() > 1 && first.charAt(0) == '*' &&
                second.length() == 0) {
            return false;
        }
        if ((first.length() > 1 && first.charAt(0) == '?') ||
                (first.length() != 0 && second.length() != 0 &&
                        first.charAt(0) == second.charAt(0))) {
            return StringMatch(first.substring(1),
                    second.substring(1));
        }
        if (first.length() > 0 && first.charAt(0) == '*') {
            return StringMatch(first.substring(1), second) ||
                    StringMatch(first, second.substring(1));
        }
        return false;
    }

    /**
     * Конструктор по умолчанию без параметров.
     */
    public StringFilterImpl() {
        strings = new HashSet<>();
    }

    /**
     * Добавляет строку s в набор, приводя ее к нижнему регистру.
     * Если строка s уже есть в наборе, ничего не делает.
     *
     * @param s может быть null
     */
    @Override
    public void add(String s) {
        strings.add(s);
    }

    /**
     * Удаляет строку s из набора (предварительно приведя ее к нижнему регистру).
     *
     * @param s может быть null
     * @return true если строка была удалена, false если строка отсутствовала в наборе.
     */
    @Override
    public boolean remove(String s) {
        for (String str : strings) {
            if (Objects.equals(str, s)) {
                strings.remove(str);
                return true;
            }
        }
        return false;
    }

    /**
     * Очищает набор - удаляет из него все строки
     */
    @Override
    public void removeAll() {
       strings.clear();
    }

    /**
     * Возвращает набор (коллекцию), в котором хранятся строки.
     * В наборе не может быть двух одинаковых строк, однако может быть null.
     */
    @Override
    public Collection<String> getCollection() {
        return strings;
    }

    /**
     * Ищет и возвращает все строки, содержащие указанную последовательность символов.<br/>
     * Если <code>chars</code> - пустая строка или <code>null</code>,
     * то результат содержит все строки данного набора.<br/>
     *
     * @param chars символы, входящие в искомые строки
     *              (все символы, являющиеся буквами, - в нижнем регистре)
     * @return строки, содержащие указанную последовательность символов
     */
    @Override
    public Iterator<String> getStringsContaining(String chars) {
        if(chars == null || chars.length() == 0){
            return this.getCollection().iterator();
        }
        Filter filter = new Filter() {
            @Override
            public boolean match(String s) {
                return s != null && s.contains(chars);
            }
        };
        return getMatch(filter);
    }

    /**
     * Ищет и возвращает строки, начинающиеся с указанной последовательности символов,
     * (без учета регистра). <br/>
     * Если <code>begin</code> - пустая строка или <code>null</code>,
     * то результат содержит все строки данного набора.<br/>
     *
     * @param begin первые символы искомых строк
     *              (для сравнения со строками набора символы нужно привести к нижнему регистру)
     * @return строки, начинающиеся с указанной последовательности символов
     */
    @Override
    public Iterator<String> getStringsStartingWith(String begin) {
        if(begin == null || begin.length() == 0){
            return this.getCollection().iterator();
        }
        Filter filter = new Filter() {
            @Override
            public boolean match(String s) {
                return s != null && s.startsWith(begin.toLowerCase());
            }
        };
        return getMatch(filter);
    }

    /**
     * Ищет и возвращает все строки, представляющие собой число в заданном формате.<br/>
     * Формат может содержать символ # (место для одной цифры от 0 до 9) и любые символы.
     * Примеры форматов: "(###)###-####" (телефон), "# ###" (целое число от 1000 до 9999),
     * "-#.##" (отрицательное число, большее -10, с ровно двумя знаками после десятичной точки).<br/>
     * Упрощающее ограничение: в строке, удовлетворяющей формату, должно быть ровно столько символов,
     * сколько в формате (в отличие от стандартного понимания числового формата,
     * где некоторые цифры на месте # не являются обязательными).<br/>
     * Примечание: в данной постановке задачи НЕ предполагается использование регулярных выражений
     * или какого-либо высокоуровневого API (эти темы изучаются позже).<br/>
     * Если <code>format</code> - пустая строка или <code>null</code>,
     * то результат содержит все строки данного набора.<br/>
     *
     * @param format формат числа
     * @return строки, удовлетворяющие заданному числовому формату
     */
    @Override
    public Iterator<String> getStringsByNumberFormat(String format) {
        if(format == null || format.length() == 0){
            return this.getCollection().iterator();
        }
        Filter filter = new Filter() {
            @Override
            public boolean match(String s) {
                String f = format.toLowerCase();
                int length = f.length();
                if (length != s.length()) {
                    return false;
                }
                for (int i = 0; i < length; i++){
                    if (f.charAt(i) == '#') {
                        if (!Character.isDigit(s.charAt(i))) {
                            return  false;
                        }
                    } else {
                        if (f.charAt(i) != s.charAt(i)) {
                            return false;
                        }
                    }
                }
                return true;
            }
        };
        return getMatch(filter);
    }

    /**
     * Ищет и возвращает строки, удовлетворяющие заданному шаблону поиска, содержащему символы *
     * в качестве wildcards (на месте * в строке может быть ноль или больше любых символов).<br/>
     * <a href="http://en.wikipedia.org/wiki/Wildcard_character#File_and_directory_patterns">Про * wildcard</a>.<br/>
     * Примеры шаблонов, которым удовлетворяет строка "distribute": "distr*", "*str*", "di*bute*".<br/>
     * Упрощение: достаточно поддерживать всего два символа * в шаблоне (их может быть и меньше двух).<br/>
     * Примечание: в данной постановке задачи НЕ предполагается использование регулярных выражений
     * и какого-либо высокоуровневого API (эти темы изучаются позже), цель - применить методы String.<br/>
     * Если <code>pattern</code> - пустая строка или <code>null</code>,
     * то результат содержит все строки данного набора.<br/>
     *
     * @param pattern шаблон поиска (все буквы в нем - в нижнем регистре)
     * @return строки, удовлетворяющие заданному шаблону поиска
     */
    @Override
    public Iterator<String> getStringsByPattern(String pattern) {
        if(pattern == null || pattern.length() == 0){
            return this.getCollection().iterator();
        }
        Filter filter = new Filter() {
            @Override
            public boolean match(String s) {
                return StringMatch(pattern, s);
            }
        };
        return getMatch(filter);
    }
}
