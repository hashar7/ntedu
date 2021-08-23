/*
Вывести почтовый код отдела, в котором работает сотрудник по фамилии 'Sully'.
*/

select postal_code
from departments d inner join locations l
on d.location_id = l.location_id inner join employees e on d.department_id = e.department_id and e.last_name = 'Sully'
