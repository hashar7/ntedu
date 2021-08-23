/*
Вывести, какие уникальные иерархии подчинения (от самого старшего командира до младшего подчиненного) присутствуют в таблице staff.
Под элементом иерархии понимаются не имя военнослужащего, а его воинское звание (таблица ranks).
Элементы разделяются символом ">", а упорядочиваются от старшего к младшему. Например:
Colonel>Major>Leutenant>Sergeant
*/

select distinct substr(sys_connect_by_path(t.name, '>'), 2) 
from (
select s.person_id, s.rank_id, s.chief, r.name
from staff s, ranks r
where s.rank_id = r.rank_id
) t
where connect_by_isleaf = 1
start with t.name = 'Colonel'
connect by prior t.person_id = t.chief
