/*
Выведите все данные (*) о сотрудниках с зарплатой, равной максимальной зарплате по всей компании. Указание: использовать только таблицу employees.
*/

select * 
from employees
where salary in (select max(salary) from employees)
