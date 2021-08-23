/*
Вывести набор уникальных наименований должности (title) из таблицы EMPLOYEES, город (city) которых - один из 'Tacoma', 'Seattle', 'Redmond'
*/

select distinct title
from employees
where city = any('Tacoma', 'Seattle', 'Redmond')
