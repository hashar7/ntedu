/*
Для военнослужащего с именем "Vasiliev" вывести всех его подчиненных (прямых и непрямых), у которых, в свою очередь, нет собственных подчиненных.
Подчинение определяется колонкой chief в таблице staff.
Вывод: имя военнослужащего, его звание, название подразделения и ID военнослужащего.
*/

select sn, rn, nnmm, person_id from (
SELECT staff.name sn, ranks.name rn, staff.unit_id unid, connect_by_isleaf isleaf,
ghgh.name nnmm, staff.person_id
FROM staff left join (select mu.name, mu.unit_id
from military_units mu
) ghgh on ghgh.unit_id = staff.unit_id, ranks
WHERE staff.rank_id = ranks.rank_id
START WITH staff.name = 'Vasiliev'
CONNECT BY PRIOR staff.person_id = staff.chief
) where isleaf = 1
