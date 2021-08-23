/*
Вывести телефон менеджера сотрудника по фамилии 'Chen'.
*/

select m.phone_number 
from employees e inner join employees m on e.last_name = 'Chen' and e.manager_id = m.employee_id
