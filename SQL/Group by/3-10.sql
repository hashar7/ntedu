/*
Выведите название отдела и среднюю зарплату сотрудников в отделе,
причем только для отделов, где средняя зарплата больше 5000.
*/

select department_name, avg(salary)
from employees, departments
where employees.department_id = departments.department_id
group by department_name
having avg(salary) > 5000
