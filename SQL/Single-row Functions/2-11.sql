/*
Выведите 'Yes', если число 3607 является делителем числа 123456789, и 'No' иначе.
Указание: Использование MOD.
*/

select (case when mod(123456789, 3607) = 0 then 'Yes' else 'No' end) "res" 
from dual
