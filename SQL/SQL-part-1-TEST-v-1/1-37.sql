/*
Для сотрудника по фамилии 'Vargas' вывести сумму денег, на которую его зарплата
меньше зарплаты менеджера отдела, в котором работает этот сотрудник.
Примечание. Комиссионные к задаче отношения не имеют.
*/

select m.salary - e.salary
from employees e, employees m, departments d
where e.last_name = 'Vargas' and e.department_id = d.department_id and m.employee_id = d.manager_id
