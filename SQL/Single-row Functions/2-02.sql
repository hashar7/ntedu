/*
Из таблицы LOCATIONS выведите первым столбцом уникальный идентификатор местоположения (LOCATION_ID),
вторым столбцом street — адрес, образованный усечением слева цифр и пробелов у колонки STREET_ADDRESS.
Указание: Использование LTRIM.
*/

select location_id, LTRIM(street_address, '0123456789 ')
from locations
