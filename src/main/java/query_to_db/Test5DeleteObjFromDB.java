package query_to_db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import query_to_db.entity.Employee;

public class Test5DeleteObjFromDB {
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
            Session session = factory.getCurrentSession();

            session.beginTransaction();
            //Обычное удаление
//            query_to_db.entity.Employee emp = session.get(query_to_db.entity.Employee.class, 7);
//
//            session.delete(emp);

            //через query
            session.createQuery("delete from Employee where firstName = 'Igor'").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }


    }
}
