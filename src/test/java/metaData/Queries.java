package metaData;

public class Queries {



 public static String leftJoins(){


     return "select e.department_id, e.first_name, e.last_name, e.salary, d.department_name \n" +
             "from employees e\n" +
             "left join departments d\n" +
             "on e.department_id=d.department_id order by salary desc";
 }












}
