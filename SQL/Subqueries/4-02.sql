/*
Выведите все данные (*) о сотрудниках с зарплатой, равной максимальной зарплате внутри своего отдела.
Указание: Решите задачу с использованием коррелированного подзапроса.
*/

select * 
from employees e1
where salary in (select max(salary)
from employees e2
where e2.department_id = e1.department_id)
