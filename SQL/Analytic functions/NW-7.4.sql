/*
Для каждого заказа из таблицы ORDERS вывести порядковый номер (orderid) и отклонение стоимости его фрахта (freight)
от средней стоимости фрахта в пределах его территории. Отклонение должно быть неотрицательным.
Примечание: предполагается, что запрос будет содержать только одно слово SELECT
*/

select orderid, abs(freight - avg(freight) over (partition by territoryid))
from orders
