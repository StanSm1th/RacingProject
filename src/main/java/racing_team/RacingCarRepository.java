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

    public void updateCarColor(RacingCar car, String newCarColor) {
        Transaction transaction = session.beginTransaction();
        car.setColor(newCarColor);
        transaction.commit();
        System.out.println("Car color changed to: " + car.getColor());
    }


    public void deleteCar(RacingCar car) {
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        System.out.println("Racing car was deleted: " + car);

        RacingCarRepository racingCarRepository = new RacingCarRepository();
        RacingCar bmw = new RacingCar();
        bmw.setBrand("BMW");
        bmw.setColor("white");
        bmw.setPrice(45000);
        bmw.setTopSpeed(260);
        racingCarRepository.saveCar(bmw);




    }
}
