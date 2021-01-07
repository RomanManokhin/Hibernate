package query_to_db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import query_to_db.entity.Employee;

public class Test1AddObjToDB {
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

            Employee emp = new Employee("Inna", "Ivanova", "HR", 500);

            //открытие транзакции
            session.beginTransaction();

            //сохранение объекта emp в БД
            session.save(emp);

//        ОБЯЗАТЕЛЬНО
            //закрытие транзакции, или применяем все изменения в БД
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }


    }
}
