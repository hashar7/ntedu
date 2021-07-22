package com.company;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WordFinderImpl implements WordFinder {

    private String text;
    private boolean setText;
    private boolean findWords;
    private  Set<String> toPrint;

    public WordFinderImpl() {
        text        = null;
        setText     = false;
        findWords   = false;
        toPrint     = null;
    }

    /**
     * @return Текущий текст для поиска или <code>null</code>,
     * если ни один из set-методов не был выполнен успешно.
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * Принимает готовый текст для поиска (а не читает текст из файла/потока).
     *
     * @param text Текст для поиска
     * @throws IllegalArgumentException если <code>src == null</code>
     */
    @Override
    public void setText(String text) throws IllegalArgumentException {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        this.text = text;
        setText = true;
    }

    /**
     * Считывает текст из указанного потока ввода. Кодировка не важна.
     *
     * @param is Поток ввода
     * @throws IOException              в случае ошибок при чтении из потока
     * @throws IllegalArgumentException если <code>is == null</code>
     */
    @Override
    public void setInputStream(InputStream is) throws IOException, IllegalArgumentException {
        if (is == null) {
            throw new IllegalArgumentException();
        }
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = is.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }
        text = result.toString();
        setText = true;
        result.close();
    }

    /**
     * Считывает текст из указанного файла. Кодировка не важна.
     *
     * @param filePath Путь к файлу с текстом
     * @throws IOException              в случае ошибок при чтении файла
     * @throws IllegalArgumentException если <code>filePath == null</code>
     */
    @Override
    public void setFilePath(String filePath) throws IOException, IllegalArgumentException {
        if (filePath == null) {
            throw new IllegalArgumentException();
        }
        setInputStream(new FileInputStream(filePath));
    }

    /**
     * Считывает текст из указанного файла через {@link Class#getResourceAsStream(String)}.<br/>
     * Это позволяет указывать краткое имя файла и читать его даже в том случае, если он лежит
     * в jar-нике вместе с классами и если прямая работа с файлами запрещена настройками безопасности.<br/>
     * Кодировка не важна.
     *
     * @param resourceName Короткое имя ресурса (файла), который должен лежать в том же пакете, что и класс
     * @throws IOException              в случае ошибок при чтении
     * @throws IllegalArgumentException если <code>resourceName == null</code>
     */
    @Override
    public void setResource(String resourceName) throws IOException, IllegalArgumentException {
        if (resourceName == null) {
            throw new IllegalArgumentException();
        }
        setInputStream(getClass().getClassLoader().getResourceAsStream(resourceName));
    }

    /**
     * Ищет в тексте все слова, начинающиеся с указанной последовательности символов,
     * без учета их регистра ('А' и 'а' считаются одним и тем же символом). <br/>
     * Результат возвращает в виде {@link Stream}, порядок элементов в котором не важен.<br/>
     * Если <code>begin</code> - пустая строка или <code>null</code>,
     * то результат содержит все слова, найденные в тексте.<br/>
     * Все возвращенные слова должны быть приведены к нижнему регистру.
     *
     * @param begin первые символы искомых слов
     * @return слова, начинающиеся с указанной последовательности символов
     * @throws IllegalStateException если нет текста для поиска
     *                               (если ни один setter-метод не выполнялся или если он последний раз выполнился с ошибкой)
     */
    @Override
    public Stream<String> findWordsStartWith(String begin) throws IllegalStateException {
        if (text == null || !setText) {
            throw new IllegalStateException();
        }
        Set<String> wordSet = new TreeSet<>(Arrays.asList(getText().toLowerCase().split("\\s+")));
        if (begin != null && !begin.equals("")) {
            Pattern pattern = Pattern.compile("^" + begin + "\\S*", Pattern.CASE_INSENSITIVE);
            Matcher matcher;
            String s;
            Iterator<String> it = wordSet.iterator();
            while (it.hasNext()) {
                s = it.next();
                matcher = pattern.matcher(s);
                if (!matcher.find()) {
                    it.remove();
                }
            }
        }
        toPrint = wordSet;
        findWords = true;
        return toPrint.stream();
    }

    /**
     * Превращает слова, найденные в {@link #findWordsStartWith(String)},
     * в текст с разделителями "пробел",
     * предварительно приведя их к нижнему регистру
     * и отсортировав по алфавиту.<br/>
     * И записывает этот текст в <code>os</code>.
     *
     * @param os Поток вывода. Кодировка не важна.
     * @throws IOException           в случае ошибок при записи в поток
     * @throws IllegalStateException если поиск слов не выполнялся
     *                               (если {@link #findWordsStartWith(String)} не выполнялся после setter-метода
     *                               или если setter-метод последний раз выполнился с ошибкой)
     */
    @Override
    public void writeWords(OutputStream os) throws IOException, IllegalStateException {
        if (!findWords) {
            throw new IllegalStateException();
        }
        Object[] str = toPrint.toArray();
        int length = str.length;
        for (int i = 0; i < length; i++) {
            if (i != length - 1) {
                os.write((str[i].toString() + " ").getBytes());
            } else {
                os.write((str[i].toString()).getBytes());
            }
        }
    }
}
