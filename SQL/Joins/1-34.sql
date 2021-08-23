/*
Для всех отделов компании вывести название отдела и фамилию (last_name) его менеджера.
Если отдел не имеет менеджера, второй столбец должен содержать NULL.
*/

select department_name, last_name
from departments d left join employees e on d.department_id = e.department_id
and d.manager_id = e.employee_id
