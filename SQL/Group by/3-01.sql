/*
Выведите в одну строчку: максимальную, среднюю и минимальную зарплату, суммарную зарплату,
количество сотрудников и количество отделов, в которых состоят сотрудники.
Все значения вычисляйте по всем сотрудникам таблицы employees
*/

select max(salary), avg(salary), min(salary), sum(salary), count(employee_id), count (distinct department_id)
from employees
