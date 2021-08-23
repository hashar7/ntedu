/*
Вывести список названий городов (города в списке не должны повторяться), в которых работает хотя бы один сотрудник с зарплатой больше 8000.
*/

select distinct city
from locations l, departments d
where l.location_id = d.location_id and d.department_id = any(select department_id from employees where salary > 8000)
