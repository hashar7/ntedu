/*
Выведите все данные (*) о тех сотрудниках, у которых в трудовой истории (таблица job_history) есть запись о работе в должности ST_CLERK.
Указание: Использование оператора EXISTS.
*/

select * 
from employees
where EXISTS (select job_id from job_history where employees.employee_id = job_history.employee_id and job_id = 'ST_CLERK')
