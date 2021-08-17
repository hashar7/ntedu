package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.util.Objects;
import java.util.stream.IntStream;

public class XPathCallerImpl implements XPathCaller{

    private Element[] nodeListToElementArray(NodeList nodeList) {
        int bound = nodeList.getLength();
        return IntStream.range(0, bound).mapToObj(i -> (Element) nodeList.item(i)).toArray(Element[]::new);
    }

    /**
     * Для заданного отдела выбрать всех сотрудников.
     *
     * @param src     XML документ для поиска
     * @param deptno  Номер отдела deptno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getEmployees(Document src, String deptno, String docType) {

        String expression = docType.equals("emp") ?
                "/content/emp/employee[@deptno=" + deptno + "]" :
                "//employee[@deptno=" + deptno + "]";
        return getElements(src, expression);
    }

    /**
     * Выбрать имя самого высокооплачиваемого сотрудника.
     *
     * @param src     XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public String getHighestPayed(Document src, String docType) {
        String expression = docType.equals("emp") ?
                "/content/emp/employee/sal[not(. <=../preceding-sibling::employee/sal) and not(. <=../following-sibling::employee/sal)]" :
                "//employee/sal[not(. <=//preceding-sibling::employee/sal) and not(. <=//following-sibling::employee/sal)]";
        return getString(src, expression);
    }

    /**
     * Выбрать имя самого высокооплачиваемого сотрудника (любого, если таких несколько).
     *
     * @param src     XML документ для поиска
     * @param deptno  Номер отдела deptno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public String getHighestPayed(Document src, String deptno, String docType) {
        String expression = docType.equals("emp") ?
                "/content/emp/employee[@deptno=" + deptno + "]/sal[not(. <../preceding-sibling::employee[@deptno=" +
                        deptno + "]/sal) and not(. <../following-sibling::employee[@deptno=" + deptno + "]/sal)]" :
                "//employee[@deptno=" + deptno + "]/sal[not(. <//preceding-sibling::employee[@deptno=" + deptno +
                        "]/sal) and not(. <//following-sibling::employee[@deptno=" + deptno + "]/sal)]";
        return getString(src, expression);
    }

    private String getString(Document src, String expression) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = null;
        try {
            nodeList = (NodeList) xPath.compile(expression).evaluate(src, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return nodeList == null ? null : ((Element) nodeList.item(0)
                .getParentNode()).getElementsByTagName("ename").item(0).getTextContent();
    }

    /**
     * Выбрать всех топовых менеджеров (менеджер топовый, если над ним нет менеджера)
     *
     * @param src     XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getTopManagement(Document src, String docType) {
        String expression = docType.equals("emp") ? "content/emp/employee[not(@mgr)]" : "/employee";
        return getElements(src, expression);
    }

    /**
     * Выбрать всех сотрудников, не являющихся менеджерами.
     * Считать, что сотрудник не является менеджером, если у него нет подчиненных.
     *
     * @param src     XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getOrdinaryEmployees(Document src, String docType) {
        String expression = docType.equals("emp") ? "content/emp/employee[not(@empno = //employee/@mgr)]" : "//employee[not(employee)]";
        return getElements(src, expression);
    }

    private Element[] getElements(Document src, String expression) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = null;
        try {
            nodeList = (NodeList) xPath.compile(expression).evaluate(src, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return nodeList == null ? new Element[0] : nodeListToElementArray(nodeList);
    }

    /**
     * Для заданного сотрудника(empno) найти всех коллег, которые в подчинении у того же менеджера.
     *
     * @param src     XML документ для поиска
     * @param empno   Номер сотрудника empno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getCoworkers(Document src, String empno, String docType) {
        String expression = docType.equals("emp") ? "content/emp/employee[./@mgr=//employee[@empno=" + empno + "]/@mgr and ./@empno!=" + empno +"]" : "//employee[@empno=" + empno + "]/../*[@empno!=" + empno + "]";
        return getElements(src, expression);
    }
}
