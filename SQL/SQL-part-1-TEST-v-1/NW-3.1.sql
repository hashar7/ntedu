/*
Показать, сколько заказов (orders) на счету у каждого работника (employees). Пример результата запроса:
____________________
| EMPLOYEEID | CNT |
--------------------
| 1	         | 123 |
--------------------
*/

select e.EMPLOYEEID, count(*)
from employees e, orders o
where e.employeeid = o.employeeid
group by e.employeeid
