/*
Вывести сотрудников (employees), у которых менеджер (manager_id) не совпадает с менеджером отдела,
где работает сотрудник (department_id, см. также атрибут manager_id в таблице departments).
Вывести нужно 3 столбца, в каждом из которых - фамилия (last_name): сотрудника, менеджера сотрудника, менеджера отдела.
Указание: использовать явное именование столбцов результирующей выборки (aliases)
*/

select e.last_name as "employee", m.last_name as "employee's manager", d.last_name as "employee's dept manager"
from employees e, employees m, employees d, departments dept
where e.manager_id = m.employee_id and e.department_id = dept.department_id and 
d.employee_id = dept.manager_id and m.employee_id <> dept.manager_id
