/*
Для каждой делавшей заказы компании из таблицы CUSTOMERS вывести имя заказчика (companyname)
и количество территорий (territoryid из таблицы ORDERS), на которых данный заказчик работает.
Примечание: предполагается, что запрос будет содержать только одно слово SELECT
*/

select distinct c.companyname, 
count(distinct o.territoryid) over (partition by c.companyname)
from customers c, orders o
where c.customerid = o.customerid
