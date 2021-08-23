/*
Для каждого военнослужащего званием ниже лейтенанта вывести начальника роты (подразделения с названием 'Company'), к которой приписан данный военнослужащий.
В обоих столбцах выводить атрибут name.
Примечание: отношение званий (выше/ниже) хранится в атрибуте priority таблицы ranks.
Для проверки можно использовать тот факт, что начальник роты является майором.
*/

select trim(regexp_replace(SYS_CONNECT_BY_PATH(s.name, ' '), '(((\w+)\s){1}).*', '\1')) nn1, trim(reverse(regexp_replace(reverse(trim(SYS_CONNECT_BY_PATH(s.name, ' '))), '(((\w+)\s){1}).*', '\1'))) nn2
from staff s
where rank_id = 40
start with s.rank_id > 50
connect by prior s.chief = s.person_id and s.rank_id >= 40
