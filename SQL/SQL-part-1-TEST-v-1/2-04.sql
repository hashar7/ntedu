/*
Из таблицы JOBS выведите должность (JOB_TITLE) и - второй колонкой - последнее слово в названии этой должности.
Указание: Использование SUBSTR, INSTR.
*/

select JOB_TITLE, trim(substr(
trim(substr(JOB_TITLE, instr(JOB_TITLE, ' ',instr(JOB_TITLE, ' ') ,1))), 
instr(
trim(substr(JOB_TITLE, instr(JOB_TITLE, ' ',instr(JOB_TITLE, ' ') ,1)))
, ' ',instr(
trim(substr(JOB_TITLE, instr(JOB_TITLE, ' ',instr(JOB_TITLE, ' ') ,1)))
, ' ') ,1)))


from jobs
