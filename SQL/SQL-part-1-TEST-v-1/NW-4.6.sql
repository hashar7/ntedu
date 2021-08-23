/*
Выведите фамилии тех сотрудников, которые являются менеджерами для других сотрудников.
Подчиненный ссылается на менджера в атрибуте ReportsTo.
*/

select distinct m.lastname
from employees e, employees m
where e.reportsto = m.employeeid
