/*
Вывести в первом столбце названия всех отделов компании, а втором - названия стран, где они располагаются.
*/

select department_name, country_name 
from locations inner join countries
on locations.country_id = countries.country_id inner join departments on departments.location_id = locations.location_id
