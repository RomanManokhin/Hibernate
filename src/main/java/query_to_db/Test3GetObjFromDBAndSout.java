package query_to_db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import query_to_db.entity.Employee;

import java.util.List;

public class Test3GetObjFromDBAndSout {
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

            //HQL в createquery(создать запрос) мы пишем имя класса, а не столбца из БД
            //Получение всех работников
            List<Employee> emps = session.createQuery("from Employee").getResultList();
            //Получение всех у кого определенное имя (name - поле из класса)
            List<Employee> emps1 = session.createQuery("from Employee where firstName = 'Inna' and salary > 250").getResultList();



            for (Employee e : emps1) System.out.println(e);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }


    }
}
