/*
Вывести название самого малочисленного взвода (взвод - это подразделение, название которого начинается с 'Platoon') и его численность.
При подсчетах численности следует учитывать состав подразделений (отделений), подчиненных данному взводу.
Если во взводе нет ни одного военнослужащего, он выводиться не должен.
*/

select nm , res from (
select nm, res, RANK() OVER (ORDER BY res) rank from (
select nm, sum(cnt1) res from (
select connect_by_root mu.name nm, mu.name, mu.unit_id, nvl(t1.cnt, 0) cnt1
from military_units mu left join (select unit_id, count(*) cnt
from staff
group by unit_id) t1
on mu.unit_id = t1.unit_id
start with regexp_like(mu.name, 'Platoon*')
connect by prior mu.unit_id = parent_id
)
group by nm
) where res > 0
) where rank = 1
