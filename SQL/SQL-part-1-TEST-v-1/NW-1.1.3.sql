/*
Вывести все данные о заказах, имя отправителя которых (shipname) содержат символ '-' а город отправителя (shipcity) не содержит пробелов
*/

select *
from orders
where shipname like '%-%' and shipcity not like '% %'
