/*
Вывести имя (PRODUCTNAME) для всех продуктов, у которых хотя бы одно из значений UNITSONORDER, REORDERLEVEL или DISCONTINUED отлично от 0
_________________________________
| Grandma's Boysenberry Spread  |
---------------------------------
*/

select PRODUCTNAME
from PRODUCTS
where UNITSONORDER <> 0 OR REORDERLEVEL <> 0 OR DISCONTINUED <> 0
