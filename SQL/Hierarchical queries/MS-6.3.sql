/*
Назовем средним сроком службы по подразделению (таблица military_units) среднее число дней службы на текущий момент всех военнослужащих (таблица staff),
приписанных к этому подразделению и ко всем его дочерним подразделениям (до нижнего уровня).
Для каждого из взводов (military_units.name начинается с "Platoon"), к которым приписаны военнослужащие,
вывести имя взвода и средний срок службы по взводу, усеченный до дней (т.е. округленный в меньшую сторону).
*/

select root_name, floor(avg(d)) from (
select s.name as sn, mu.name as mn, s.unit_id as suid, mu.unit_id as muid, sysdate - consc_date d, mmuu.root_name
from staff s, military_units mu, (select CONNECT_BY_ROOT mun.name as root_name, mun.unit_id from military_units mun start with regexp_like(mun.name, 'Platoon*') connect by prior mun.unit_id = parent_id) mmuu 
where s.unit_id = mu.unit_id and mmuu.unit_id = s.unit_id and s.unit_id in (select unit_id from military_units 
start with  regexp_like(name, 'Platoon*') connect by prior unit_id = parent_id)
)
group by root_name
