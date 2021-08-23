/*
Для сотрудника с employee_id = 110 выведите полную строку его подчинения, где менеджеры разделены символом #.
Имя сотрудника следует выводить в формате first_name last_name.
Пример строки подчинения: #employee#manager#manager_of_manager
(первым должен идти сам сотрудник, затем - его непосредственный менеджер, ..., последним - топ-менеджер).
Указание: использование SYS_CONNECT_BY_PATH.
*/

select SYS_CONNECT_BY_PATH(first_name || ' ' || last_name, '#')
from employees
where manager_id is null
start with employee_id = 110
connect by prior manager_id = employee_id;
