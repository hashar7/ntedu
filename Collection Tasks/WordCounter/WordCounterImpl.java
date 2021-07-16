package com.company;

import java.io.PrintStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounterImpl implements WordCounter {

    private String text;
    private HashMap<String, Long> frequencyMap;

    /**
     * Конструктор по умлочанию без параметров.
     */
    public WordCounterImpl() {
        text = null;
        frequencyMap = new HashMap<>();
    }

    /**
     * Принимает текст для анализа
     *
     * @param text текст для анализа
     */
    @Override
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return текст, переданный для анализа при последнем вызове метода
     * {@link #setText(String) setText}, или <code>null</code>,
     * если указанный метод еще не вызывался или последний раз вызывался
     * с параметром <code>null</code>
     */
    @Override
    public String getText() {
        return text == null ? null : text;
    }

    /**
     * Возвращает {@link Map}&lt;{@link String}, {@link Long}&gt;, сопоставляющую каждому
     * слову (длиной не менее 1 символа) количество его вхождений в анализируемый текст.<br/>
     * Все возвращаемые слова должны быть приведены к нижнему регистру.<br/>
     * Дополнительно оценивается, если из рассмотрения исключены слова, начинающиеся с &lt;
     * и заканчивающиеся на &gt; (то есть, расположенные в угловых скобках).<br/>
     *
     * @return результат подсчета количеств вхождений слов
     * @throws IllegalStateException если не задан текст для анализа
     *                               (если метод {@link #setText(String)} еще не вызывался
     *                               или последний раз вызывался с параметром <code>null</code>)
     */
    @Override
    public Map<String, Long> getWordCounts() throws IllegalStateException{
        if (getText() == null) {
            throw new IllegalStateException();
        }
        String newText = getText().replaceAll("<\\s*\\S*\\s*>|[.,:;?!\"()—\\[\\]]", "").toLowerCase().trim();
        Pattern pattern = Pattern.compile("\\S+");
        Matcher matcher = pattern.matcher(newText);
        while(matcher.find()) {
            Long count = this.frequencyMap.get(matcher.group());
            this.frequencyMap.put(matcher.group(), count == null ? Long.valueOf(1L) : ++count);
        }
        return this.frequencyMap;
    }

    /**
     * Возвращает список из {@link Map.Entry Map.Entry}&lt;{@link String}, {@link Long}&gt;,
     * сопоставляющий каждому слову количество его вхождений в анализируемый текст
     * и упорядоченный в прядке убывания количества вхождений слова.<br/>
     * Слова с одинаковым количеством вхождений упорядочиваются в алфавитном порядке (без учета регистра!).<br/>
     * Все возвращаемые слова должны быть приведены к нижнему регистру.<br/>
     * <p>
     * ПРИМЕЧАНИЕ: при реализации рекомендуется использовать {@link #sort(Map, Comparator)}
     *
     * @return упорядоченный результат подсчета количеств вхождений слов
     * @throws IllegalStateException если не задан текст для анализа
     *                               (если метод {@link #setText(String)} еще не вызывался
     *                               или последний раз вызывался с параметром <code>null</code>)
     */
    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() throws IllegalStateException {
        if (getText() == null) {
            throw new IllegalStateException();
        }
        return sort(getWordCounts(), new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                int result = o2.getValue().compareTo(o1.getValue());
                return result != 0 ? result : o1.getKey().compareTo(o2.getKey());
            }
        });
    }

    /**
     * Упорядочивает содержимое <code>map</code> (это слова и количество их вхождений)
     * в соответствии с <code>comparator</code>.<br/>
     * <br/>
     * ПРИМЕЧАНИЕ:Этот метод работает только со своими параметрами, но не с полями объекта {@link WordCounter}.
     *
     * @param map        Например, неупорядоченный результат подсчета числа слов
     * @param comparator
     * @return Содержимое <code>map</code> в виде списка, упорядоченного в соответствии с <code>comparator</code>
     */
    @Override
    public <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K, V>> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(comparator);
        return list;
    }

    /**
     * Распечатывает <code>entryList</code> (это слова и количество их вхождений)
     * в поток вывода <code>ps</code>.<br/>
     * Формат вывода следующий:
     * <ul>
     * 	<li>Каждое слово вместе с количеством вхождений выводится на отдельной строке</li>
     * 	<li>На каждой строке слово и количество вхождений разделены одним(!) пробелом,
     * никаких других символов на строке быть не должно</li>
     * </ul>
     * Все выводимые слова должны быть приведены к нижнему регистру.<br/>
     * <br/>
     * ПРИМЕЧАНИЕ: Этот метод работает только со своими параметрами, но не с полями объекта {@link WordCounter}.
     *  @param entries Список пар - например, результат подсчета числа слов
     *
     * @param ps Поток вывода - например, System.out.
     */
    @Override
    public <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {
        for (Map.Entry<K, V> entry : entries) {
            ps.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
