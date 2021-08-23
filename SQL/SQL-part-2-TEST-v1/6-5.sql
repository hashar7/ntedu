/*
Вывести сотрудников, находящихся в транзитивном не прямом подчинении у сотрудника с id = 102.
О каждом сотруднике вывести employee_id, first_name, last_name.
*/

select employee_id, first_name, last_name
from employees
where level > 2
start with employee_id = 102
connect by manager_id = prior employee_id
