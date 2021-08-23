/*
Для всех сотрудников вывести фамилию(last_name), дату приёма на работу (hire_date), зарплату (salary)
и разницу между зарплатой данного сотрудника и средней зарплатой всех сотрудников, нанятых за предыдущий год (включая данного сотрудника).
Указание: использовать аналитические функции и NUMTOYMINTERVAL.
*/

select e.last_name, e.hire_date, e.salary, e.salary - avg(e.salary) over(order by e.hire_date range NUMTOYMINTERVAL(1, 'YEAR') preceding)
from employees e
