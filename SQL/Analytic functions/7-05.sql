/*
Для всех сотрудников вывести фамилию, зарплату, фамилию менеджера и максимальную зарплату среди непосредственных подчиненных этого менеджера.
Если у сотрудника менеджер отсутствует, никакой информации для такого сотрудника выводить не нужно.
*/

select e.last_name eln, e.salary, m.last_name mln, max(e.salary) over (partition by m.employee_id)
from employees e, employees m
where e.manager_id = m.employee_id and e.manager_id is not null
