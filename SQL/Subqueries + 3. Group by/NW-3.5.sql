/*
Найти заказчиков (customers), сделавшие заказы в тот день или те дни (orderdate), в которые было совершено наибольшее число заказов.
Вывести: дату (orderdate) и название компании-заказчика (companyname).
*/

select orderdate, companyname
from orders, customers
where orders.customerId = customers.customerId and orderdate in
(select orderdate from (select * from
(select orderdate, count(orderdate) as count
from orders
group by orderdate) s
where s.count in (select max(count) from
(select orderdate, count(orderdate) as count
from orders
group by orderdate))))
