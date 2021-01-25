package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.CarDao;
import ru.sapteh.dao.impl.CarDaoImpl;
import ru.sapteh.model.Car;
import ru.sapteh.model.Engine;

public class Program {
    public static void main(String[] args) {

        Engine engine = new Engine();






        SessionFactory factory = null;

        try {

            factory = new Configuration().configure().buildSessionFactory();
            CarDao<Car, Integer> dao = new CarDaoImpl(factory);
//
           read(dao);
//
////            update(dao);
//
////            create(dao);
//
////            delete(dao);
//
//
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    private static void read(CarDao<Car, Integer> carDao) {
        final Car result = carDao.read(1);
        System.out.println("Read: " + result);
    }

    private static void update(CarDao<Car, Integer> carDao) {
        final Car result = carDao.read(1);
        result.setModel("Mazda");
        result.getEngine().setPower("500");
        result.getEngine().setModel("Super engine");
        carDao.update(result);
        System.out.println("Updated: " + carDao.read(1));
    }

    private static void create(CarDao<Car, Integer> carDao) {
        Car car = new Car();
        car.setModel("new model");
        car.setMark("new mark");
        Engine engine = new Engine();
        engine.setModel("new engine");
        engine.setPower("900");
        car.setEngine(engine);
        carDao.create(car);
        System.out.println("Created: " + carDao.read(2));
    }

    private static void delete(CarDao<Car, Integer> carDao) {
        Car car = new Car();
        car.setModel("new model");
        car.setMark("new mark");
        car.setId(2);
        Engine engine = new Engine();
        engine.setModel("new engine");
        engine.setPower("900");
        engine.setId(2);
        car.setEngine(engine);
        carDao.delete(car);
    }
}
