/*
Выведите все данные (*) о тех сотрудниках, зарплаты которых больше, чем средняя зарплата в каждом из отделов.
Указание: Использование оператора сравнения с оператором ALL.
*/

select *
from employees
where salary > ALL (select avg(salary) from employees group by department_id)
