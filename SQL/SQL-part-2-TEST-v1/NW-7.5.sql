/*
Для каждого заказа из таблицы ORDERS вывести его номер (orderid), номер территории (territoryid),
и "номер на территории" - число, показывающее, каким по очереди был сделан этот заказ на данной территории.
Заказы с одной датой получают одинаковый "номер на территории", из-за этого в нумерации могут быть "дырки".
Примечание: предполагается, что запрос будет содержать только одно слово SELECT
*/

select o.orderid, o.territoryid, rank() over (partition by o.territoryid order by orderdate)
from ORDERS o
