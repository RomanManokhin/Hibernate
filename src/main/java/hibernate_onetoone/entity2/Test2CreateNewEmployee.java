package hibernate_onetoone.entity2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test2CreateNewEmployee {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                //SessionFactory читает файл конфигурации, после чего знает как создавать сессии
                .configure("hibernate.cfg.xml")
                //указываем класс который будет работать с БД
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                //вызываем метод, который "строит" SessionFactory
                .buildSessionFactory();


        Session session = null;
        //Hibernate за нас создаст SQL запрос и отправит его
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            Employee employee = session.get(Employee.class, 1);
            Detail detail = new Detail("Rostov", "1236123123", "rom@mail.ru");

            employee.setEmpDetail(detail);

            session.save(employee);

            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }


    }
}
