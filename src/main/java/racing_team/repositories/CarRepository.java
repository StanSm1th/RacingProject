package racing_team.repositories;

import org.hibernate.Session;
import racing_team.entities.Car;
import racing_team.utils.HibernateUtil;

public class CarRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void saveCar(Car newRacingCar) {
        session.beginTransaction();
        session.save(newRacingCar);
        session.getTransaction().commit();
        System.out.println("NEW racing car added : " + newRacingCar);
    }

    public Car findCarById(Integer id) {
        return session.find(Car.class, id);
    }

    public void deleteCarById(Car racingCar) {
        session.beginTransaction();
        session.delete(racingCar);
        session.getTransaction().commit();
        System.out.println("Driver was deleted: " + racingCar);
    }
}
