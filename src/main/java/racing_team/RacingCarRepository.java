package racing_team;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class RacingCarRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void saveCar(RacingCar car) {
        Transaction transaction = session.beginTransaction();
        session.persist(car);
        transaction.commit();
        System.out.println("Car was created: " + car);
    }

    public RacingCar findCarById(Integer id) {
        return session.find(RacingCar.class, id);
    }

    public void updateCarColor(RacingCar car, RacingDriver racingDriver) {
        Transaction transaction = session.beginTransaction();
        car.setRacingDriver(racingDriver);
        transaction.commit();
        System.out.println("Car color changed to: " + car.getRacingDriver());
    }


    public void deleteCar(RacingCar car) {
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        System.out.println("Racing car was deleted: " + car);






    }
}
