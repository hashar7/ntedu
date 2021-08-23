/*
Выведите имена (companyname) и адреса (address) заказчиков (customers), не сделавших ни одного заказа (orders)
*/

select companyname, address
from customers
where customerid not in (select customerid from orders)
