/*
Выведите все данные (*) о сотрудниках, которые НЕ находятся в подчинении у менеджеров с id 101 и 102 (включая топ-менеджера)
*/

select * 
from employees
where manager_id is null or manager_id not in (101, 102)
