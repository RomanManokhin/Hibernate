package hibernate_manytomany;

import hibernate_manytomany.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                //SessionFactory читает файл конфигурации, после чего знает как создавать сессии
                .configure("hibernate.cfg.xml")
                //указываем класс который будет работать с БД
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                //вызываем метод, который "строит" SessionFactory
                .buildSessionFactory();

        Session session = null;
        //Hibernate за нас создаст SQL запрос и отправит его
        try {
            session = factory.getCurrentSession();

            Section section1 = new Section("Footbal");
            Section section2 = new Section("Ches");
            Section section3 = new Section("Dance");
            Child child1 = new Child("Cveta", 10);
//            Child child2 = new Child("Ivan", 9);
//            Child child3 = new Child("Kolia", 6);

            child1.addSectionToChild(section1);
            child1.addSectionToChild(section2);
            child1.addSectionToChild(section3);

            session.beginTransaction();

            session.save(child1);

            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }

    }
    }
