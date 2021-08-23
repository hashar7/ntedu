/*
Для каждой делавшей заказы компании из таблицы CUSTOMERS вывести имя заказчика (companyname)
и адрес доставки (shipaddress) первого заказа этой компании из таблицы ORDERS
Указание: «первый» определяется функцией first_value.
Примечание: предполагается, что запрос будет содержать только одно слово SELECT
*/

select distinct c.companyname, first_value (o.shipaddress) 
over (partition by o.customerid order by o.orderdate asc)
from CUSTOMERS c, ORDERS o
where c.customerid = o.customerid
