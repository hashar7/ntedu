/*
Для каждой делавшей заказы компании из таблицы CUSTOMERS вывести имя заказчика (companyname), номер заказа (orderid) из таблицы ORDERS, который имел наибольшую стоимость,
и саму стоимость этого заказа с учетом скидки (из таблицы ORDERDETAILS).
Примечание: предполагается, что запрос будет содержать только одно слово SELECT
*/

select distinct c.companyname,
first_value(o.orderid) 
over(partition by c.companyname 
order by sum(UnitPrice * Quantity * (1 - Discount)) desc) ord,
max(sum(UnitPrice * Quantity * (1 - Discount))) 
over(partition by c.companyname ) price
from orders o, customers c, orderdetails od
where c.customerid = o.customerid and o.orderid = od.orderid
group by c.companyname, o.orderid
