/*
Выведите все данные (*) о сотрудниках с зарплатой, равной максимальной зарплате внутри своего отдела.
Указание: Решите задачу с использованием некоррелированного подзапроса во FROM.
*/

select e1.* 
from employees e1, (select department_id, max(salary) as SALARY from employees group by department_id) e2
where e1.department_id = e2.department_id and e1.salary = e2.SALARY
