/*
Для каждого отделения (отделение - это подразделение, название которого начинается со слова "Squad")
перечислить через запятую в одной строчке весь личный состав, упорядочив военнослужащих по алфавиту.
Учитывать только военнослужащих, приписанных непосредственно к отделению.
Вывод: первой колонкой - ID подразделения, второй - список имен военнослужащих (name) через запятую (без пробелов).
*/

select distinct mu.unit_id muid, listagg(s.name,',') within group (order by s.name) over (partition by s.unit_id)
from military_units mu, staff s
where regexp_like(mu.name, 'Squad*') and s.unit_id = mu.unit_id
