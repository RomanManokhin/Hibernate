package query_to_db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import query_to_db.entity.Employee;

public class Test4UpdateObj {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                //SessionFactory читает файл конфигурации, после чего знает как создавать сессии
                .configure("hibernate.cfg.xml")
                //указываем класс который будет работать с БД
                .addAnnotatedClass(Employee.class)
                //вызываем метод, который "строит" SessionFactory
                .buildSessionFactory();

        //Hibernate за нас создаст SQL запрос и отправит его
        try {
            //создание сессии, это подключение к БД
            Session session = factory.getCurrentSession();

            session.beginTransaction();

            //через сеттер
            Employee emp = session.get(Employee.class, 1);
            emp.setSalary(600);

            //через query
            session.createQuery("update Employee set salary = 1000 where firstName = 'Roman'").executeUpdate();



            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }


    }
}
