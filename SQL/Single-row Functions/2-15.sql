/*
Выведите всю информацию о сотрудниках, фамилии которых начинаются с H или K, содержат 2 одинаковые буквы подряд,
а оставшаяся после повторяющихся букв часть фамилии не заканчивается на букву s.
Указание: Использование REGEXP_LIKE.
*/

select *
from employees
where REGEXP_LIKE(last_name, '(H|K)') and 
REGEXP_LIKE(last_name, '([a-z])\1', 'i') and
REGEXP_LIKE(last_name, '(*)[^s]$') and
REGEXP_REPLACE(last_name, '\s*', '') = last_name
