/*
Вывести названия всех отделов, располагающихся по адресу '2004 Charade Rd'.
*/

select department_name
from departments inner join locations on departments.location_id = locations.location_id and locations.street_address = '2004 Charade Rd'
