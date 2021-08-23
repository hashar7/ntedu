/*
Для каждой страны вывести ее название, название региона, в котором она находится и количество стран, находящихся в этом регионе.
*/

select c.country_name, r.region_name, count(*) over(partition by r.region_id)
from countries c ,regions r
where c.region_id = r.region_id
