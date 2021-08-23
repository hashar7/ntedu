/*
Для всех имен продуктов (PRODUCTNAME) из таблицы PRODUCTS, которые состоят более чем из одного слова,
выведите первым столбцом само имя, вторым - только первое слово имени. Например:
_________________________________________
| Teatime Chocolate Biscuits  | Teatime |
-----------------------------------------
Считать, что слова отделяются друг от друга пробелами.
*/

select PRODUCTNAME, SUBSTR(PRODUCTNAME, 1, REGEXP_INSTR (PRODUCTNAME, ' ', 1, 1, 0, 'i') - 1)
from (select PRODUCTNAME from PRODUCTS where REGEXP_LIKE(PRODUCTNAME, '(+)\s(+)')) 
