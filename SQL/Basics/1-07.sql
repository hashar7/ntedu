/*
По таблице employees, выведите первым столбцом порядковый номер извлеченной строки, вторым — фамилию сотрудника.
Указание: использовать псевдостолбец rownum.
*/

select rownum, last_name
from employees
