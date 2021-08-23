/*
Для каждого заказа из таблицы ORDERS вывести customerid, orderid и суммарную стоимость всех компонентов этого заказа (с учетом скидки).
Примечание: предполагается, что запрос будет содержать только одно слово SELECT
*/

select distinct o.customerid, od.orderid, sum(od.unitprice * od.quantity * (1 - od.discount)) over (partition by od.orderid)
from orderdetails od, orders o
where od.orderid = o.orderid
