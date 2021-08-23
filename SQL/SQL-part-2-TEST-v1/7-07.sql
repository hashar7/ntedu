/*
Для всех сотрудников вывести фамилию сотрудника, дату устройства в компанию, зарплату и среднюю зарплату всех сотрудников, нанятых (строго) позже него.
Пример вывода:
LAST_NAME	  HIRE_DATE	      SALARY	  AVERAGE
Lorentz	    07-FEB-99	      4200	    5100
Cabrio	    07-FEB-99	      3000	    5100
Smith	      23-FEB-99	      7400	    2800
Jones	      17-MAR-99	      2800	    (null)
(в примере для наглядности предполагается, что в рассмотрении находятся только эти 4 сотрудника - остальные исключены с помощью WHERE)
*/

select LAST_NAME, HIRE_DATE, SALARY, avg(SALARY) over (order by HIRE_DATE range between 1 following and unbounded following)
from employees
