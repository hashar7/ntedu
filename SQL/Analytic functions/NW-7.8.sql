/*
Для каждой делавшей заказы компании из таблицы CUSTOMERS вывести имя заказчика (companyname), а тремя следующими столбцами - минимальную, максимальную и среднюю
стоимость заказов этого заказчика с учетом скидки (данные из таблиц ORDERS и ORDERDETAILS).
Примечание: предполагается, что запрос будет содержать только одно слово SELECT
*/

--ПОЧЕМУ-ТО РЕЗУЛЬТАТ НЕ ВЕРНЫЙ(((

select distinct c.CompanyName,

MIN(UnitPrice * Quantity * (1 - Discount)) 
OVER (PARTITION BY o.customerid) AS "MIN",
MAX(UnitPrice * Quantity * (1 - Discount)) 
OVER (PARTITION BY o.customerid) AS "MAX",
AVG(UnitPrice * Quantity * (1 - Discount)) 
OVER (PARTITION BY o.customerid) AS "AVG"

FROM Customers c, Orders o, OrderDetails od
WHERE c.CustomerId = o.CustomerID AND o.OrderID = od.OrderID
