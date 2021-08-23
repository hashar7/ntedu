/*
Выведите все данные (*) о сотрудниках с зарплатой, равной максимальной зарплате внутри своего отдела.
Указание: Решите задачу с использованием некоррелированного подзапроса и оператора IN.
*/

select e1.* 
from employees e1, (select department_id, max(salary) as SALARY from employees group by department_id) e2
where e1.department_id = e2.department_id and e1.salary in (e2.SALARY)
