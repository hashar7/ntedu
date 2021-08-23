/*
Для каждой локации (таблица LOCATIONS) вывести location_id, postal_code и количество локаций с тем же количеством символов в postal_code.
*/

select l.location_id, l.postal_code, count(l.location_id) over (order by length(l.postal_code) range current row)
from locations l
