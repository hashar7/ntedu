/*
Вывести имена сотрудников для второй страницы телефонного справочника при условиях, что:
а) сотрудники в справочнике упорядочены по фамилиям и затем по именам,
б) на одной странице справочника размещается ровно 10 записей.
Имена выводить в одном столбце в формате first_name пробел last_name. Никаких других столбцов выводить не надо.
Указание: использовать аналитическую функцию.
*/

select nm from (
select nth_value(e.first_name || ' ' || e.last_name, 11) over (order by 
e.last_name, e.first_name rows between current row and 10 following) nm, 
row_number() over(order by e.last_name, e.first_name) rn
from employees e )
where rn < 11
