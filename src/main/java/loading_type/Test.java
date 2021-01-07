package loading_type;

import loading_type.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                //SessionFactory читает файл конфигурации, после чего знает как создавать сессии
                .configure("hibernate.cfg.xml")
                //указываем класс который будет работать с БД
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                //вызываем метод, который "строит" SessionFactory
                .buildSessionFactory();

        Session session = null;
        //Hibernate за нас создаст SQL запрос и отправит его
        try {
            session = factory.getCurrentSession();


//            Department department = new Department()
//            Employee employee = new Employee("Elena", "Malisheva", 800);
//            Employee employee1 = new Employee("Irina", "Malaeva", 900);
//
//            department.addEmployToDepartment(employee);
//            department.addEmployToDepartment(employee1);

            session.beginTransaction();
            Department department = session.get(Department.class, 5);



            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }


    }
}
