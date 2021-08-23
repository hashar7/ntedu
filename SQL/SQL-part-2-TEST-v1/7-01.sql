/*
Для всех сотрудников вывести фамилию сотрудника, его комиссионные (commission_pct)
и средние комиссионные по всем сотрудникам компании.
Указание: при расчете средних комиссионных учесть, что некоторые из них могут быть NULL.

Check (Ctrl+Enter)
*/

select last_name, commission_pct, avg(nvl(commission_pct, 0)) over () 
from employees
