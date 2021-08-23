/*
Выведите максимальное значение средней зарплаты по отделам. Результат округлите до ближайшего целого.
*/

select round(max(avg(salary)))
from employees
group by department_id
