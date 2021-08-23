/*
Для каждого сотрудника вывести id отдела, фамилию, дату приема на работу и фамилию сотрудника, принятого на работу в этот отдел самым первым.
Если таких несколько (приняты в один день) – вывести фамилию первого из них. Указание: «первый» определяется функцией first_value.
*/

select e.department_id, e.last_name, e.hire_date, first_value(e.last_name)
over (partition by e.department_id order by hire_date)
from employees e
