/*
Выведите имена и фамилии всех сотрудников (first_name, last_name) и в 3-7 столбцах выведите размер их комиссионных (commission_pct * salary) или 0, если они отсутствуют, -
пятью различными способами: с помощью функций NVL, COALESCE, NVL2, DECODE, CASE.
*/

select first_name, last_name, NVL(commission_pct * salary, 0), COALESCE(commission_pct * salary, 0), NVL2(commission_pct * salary, commission_pct * salary, 0), DECODE(commission_pct * salary, null, 0, commission_pct * salary, commission_pct * salary),
CASE WHEN commission_pct * salary is null then 0 else commission_pct * salary end
from employees
