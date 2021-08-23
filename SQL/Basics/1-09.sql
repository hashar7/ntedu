/*
Выведите все данные (*) о сотрудниках с зарплатой не менее 3000, кроме сотрудника с фамилией King.
*/

select * 
from employees
where salary >= 3000 and last_name != 'King'
