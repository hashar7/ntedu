/*
Выведите фамилии тех сотрудников, которые являются менеджерами для других сотрудников.
*/

select m.last_name
from employees m
where m.employee_id = any(select manager_id from employees)
