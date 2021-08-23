/*
Перечислить в одной строчке через запятую (без пробелов) весь личный состав первого отделения
(т.е. подразделения с именем "Squad #1"), упорядочив там имена военнослужащих (name) по алфавиту.
Учитывать только военнослужащих, приписанных непосредственно к отделению.
*/

select listagg(name, ',') within group (order by name) names
from staff
where unit_id = (select unit_id from military_units where name = 'Squad #1')
