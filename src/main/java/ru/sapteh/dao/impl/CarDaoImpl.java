package ru.sapteh.dao.impl;

import com.sun.istack.NotNull;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sapteh.dao.CarDao;
import ru.sapteh.model.Car;

public class CarDaoImpl implements CarDao<Car, Integer> {

    private SessionFactory factory;

    public CarDaoImpl (SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Car car) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(car);
            session.getTransaction().commit();
        }
    }

    @Override
    public Car read(Integer id) {
        try(Session session = factory.openSession()){
            final Car result = session.get(Car.class, id);
            if(result != null){
                Hibernate.initialize(result.getEngine());
            }
            return result;
        }
    }

    @Override
    public void update(Car car) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(car);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Car car) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(car);
            session.getTransaction().commit();
        }
    }
}
