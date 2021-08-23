/*
Для всех сотрудников вывести отдел (department_id), фамилию (last_name), зарплату (salary) и количество человек,
которые, работая в этом же отделе, имеют зарплату (строго) больше, чем данный сотрудник.
*/

select e.department_id, e.last_name, e.salary, count(*) over (partition by e.department_id order by e.salary range between 1 following and unbounded following)
from employees e
