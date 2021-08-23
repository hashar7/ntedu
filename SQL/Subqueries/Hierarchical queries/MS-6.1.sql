/*
Выведите иерархию подчинений воинских подразделений сверху вниз, начиная с полка 'Regiment #1271A',
и численность личного состава, приписанного непосредственно к подразделению.
Все подчиненные подразделения должны располагаться "лесенкой" с отступом, равным 3-м пробелам.
О каждом подразделении выводить: название, численность.
Regiment #1271A	1
   First Company	1
      Platoon #1	0
      Platoon #2	1
...	...

*/

select lpad('                  ', 3*(level - 1)) || military_units.name, nvl(num, 0)
from military_units, (select unit_id, count(*) num from staff group by unit_id) t1
where military_units.unit_id = t1.unit_id(+)
start with military_units.name = 'Regiment #1271A'
connect by prior military_units.unit_id = parent_id
