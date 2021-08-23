/*
Выведите сегодняшнюю дату в следующих четырех столбцах (все столбцы - строкового типа):
Год (4 цифры)
Полное название месяца заглавными буквами
День месяца (число)
День недели словом заглавными буквами
Пример вывода:
___________________________________
| 2011  | OCTOBER | 04  | TUESDAY |
-----------------------------------
*/

select to_char(sysdate, 'YYYY'), to_char(sysdate, 'MONTH'), to_char(sysdate, 'DD'), to_char(sysdate, 'DAY')
from dual
