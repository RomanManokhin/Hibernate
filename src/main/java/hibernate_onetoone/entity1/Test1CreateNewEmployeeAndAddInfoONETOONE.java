package hibernate_onetoone.entity1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1CreateNewEmployeeAndAddInfoONETOONE {
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
//            //создание сессии, это подключение к БД
//            Session session = factory.getCurrentSession();
//            Employee employee = new Employee("Ivan", "Ivanov", "HR", 500);
//            Detail detail = new Detail("NY", "123123123", "ivan2012@mail.ru");
//
//            //связка пользователя и его данных
//            employee.setEmpDetail(detail);
//            session.beginTransaction();
//
//            session.save(employee);
//
//            session.getTransaction().commit();
//            System.out.println("Done!");


//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            //получаем пользователя по id
//            Employee employee = session.get(Employee.class, 2);
//            //выводим пользователя по данному id в консоль
//            System.out.println(employee.getEmpDetail());
//
//            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            //получаем пользователя по id
            Employee employee = session.get(Employee.class, 3);
            session.delete(employee);

            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }


    }
}
