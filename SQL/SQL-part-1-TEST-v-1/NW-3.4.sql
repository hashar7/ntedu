/*
Показать, кто из сотрудников (employees) работает более чем на 5 территориях (employeeterritories).
Имена сотрудников вывести в формате "firstname lastname"
*/

select e.firstname || ' ' || e.lastname
from employees e
where e.employeeid in 
(select employeeid from (select employeeid, count(*) from employeeterritories
group by employeeid having count(*) > 5))
