/*
Для каждой делавшей заказы компании из таблицы CUSTOMERS вывести имя заказчика (companyname), номер заказа (orderid) из таблицы ORDERS
и время (в днях), прошедшее с момента совершения предыдущего заказа этим же заказчиком (orderdate). Для первого заказа последний столбец равен 0.
Для заказов, совершенных заказчиком в один и тот же день, понятие "предыдущий заказ" определяется номером заказа (orderid).
Примечание: предполагается, что запрос будет содержать только одно слово SELECT
*/

select companyname, orderid, 
nvl(orderdate - max(orderdate) over (partition by 
customers.customerid order by orderdate, orderid rows between UNBOUNDED PRECEDING and 1 preceding), 0)
from orders, customers
where orders.customerid = customers.customerid
