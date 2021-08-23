/*
Из таблицы LOCATIONS выведите адрес (STREET_ADDRESS) и позицию второго буквенного символа, входящего в ту же строку (адрес).
Указание: Использование REGEXP_INSTR.
*/

select street_address, REGEXP_INSTR (street_address, '[a-z]', 1, 2, 0, 'i')
from locations
