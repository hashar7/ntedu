/*
Для сотрудников, у которых есть комиссионные, выведите в столбце sum сумму зарплаты и комиссионных сотрудника
(колонка commission_pct обозначает долю зарплаты, начисляемую в качестве комиссионных),
и затем все столбцы таблицы employees (*).
Результат должен быть отсортирован по sum, потом по четвертому столбцу в итоговой выборке. Указание: перед * использовать имя (или alias) таблицы
*/

select salary * (1 + commission_pct) as "sum", employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id
from employees
where commission_pct is not null
order by salary * (1 + commission_pct), last_name
