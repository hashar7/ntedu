/*
Для всех отделов, располагающихся в городе 'Seattle', вывести название отдела и номер телефона его менеджера.
Если отдел не имеет менеджера, второй столбец должен содержать NULL.
*/

select department_name, phone_number
from departments left join employees
on departments.department_id = employees.department_id and departments.manager_id = employees.employee_id full join locations on departments.location_id = locations.location_id where city = 'Seattle'
