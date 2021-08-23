/*
Выведите всех имеющихся в таблице employees сотрудников в виде иерархии подчинения,
отступая каждый уровень подчинения от предыдущего на четыре символа '-'.
На каждом уровне сотрудники должны быть отсортированы по last_name. Указание: использование сортировки SIBLINGS BY.
Имя сотрудника следует выводить в формате first_name last_name.
Подсказка: начинать следует с тех сотрудников, у кого не заполнен manager_id.

Пример конечной выборки:
EMP_NAME
------------------------------------
Steven King
----Gerald Cambrault
--------Elizabeth Bates
--------Harrison Bloom
*/

SELECT LPAD('----------------------------------------', 4*(level) - 4) || first_name || ' ' || last_name as EMP_NAME
from employees
start with manager_id is null
connect by manager_id = prior employee_id
order SIBLINGS BY last_name asc
