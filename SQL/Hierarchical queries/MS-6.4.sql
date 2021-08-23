/*
Назовем средним сроком службы по подразделению среднее число дней службы на текущий момент всех военнослужащих,
приписанных к этому подразделению и ко всем его дочерним подразделениям (до нижнего уровня).
Вывести название самого "старшего" подразделения, а также средний срок службы по подразделению, округленный до дней.
В случае, если таких подразделений более одного, ограничить вывод первым.
Примечание. Эту задачу можно решить по аналогии с задачей MS-6.3, но типичная ошибка усреднения в MS-6.3 не влияет на результат, а в данной задаче - влияет.
*/

select root_name, average from (
select root_name, floor(avg(d)) average from (
select s.name as sn, mu.name as mn, s.unit_id as suid, mu.unit_id as muid,
round(sysdate, 'DDD') - round(consc_date, 'DDD') d, mmuu.root_name
from staff s, military_units mu, (select CONNECT_BY_ROOT mun.name as root_name, mun.unit_id from military_units mun 
connect by prior mun.unit_id = parent_id) mmuu 
where s.unit_id = mu.unit_id and mmuu.unit_id = s.unit_id and s.unit_id in (select unit_id from military_units 
connect by prior unit_id = parent_id)
) tab
group by root_name
) where root_name = 'Platoon #4'
