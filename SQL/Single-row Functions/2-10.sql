/*
Выведите 'Yes', если в разложении числа 123456789 по степеням двойки существует двойка в степени 10, и 'No' иначе.
Указание: Использование BITAND, POWER.
*/

select (case when BITAND(to_number('123456789'), POWER(2, 10)) = 1024 then 'Yes' else 'No' end) "res" 
from dual
