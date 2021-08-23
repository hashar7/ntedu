/*
Выведите идентификатор (supplierid) всех поставщиков (suppliers), поставляющих продукцию (products) только одной категории (categories),
а также имя этой категории (categoryname)
*/

select distinct p.supplierid, categoryname
from products p, categories c
where p.categoryid = c.categoryid and p.supplierid = any (select supplierid from products group by supplierid
having count(distinct categoryid) = 1)
