package racing_team;

import org.hibernate.Session;

public class RacingCarRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void saveCar(RacingCar newRacingCar) {
        session.beginTransaction();
        session.save(newRacingCar);
        session.getTransaction().commit();
        System.out.println("NEW racing car added : " + newRacingCar);
    }

    public RacingCar findCarById(Integer id) {
        return session.find(RacingCar.class, id);
    }

    public void deleteCarById(RacingCar racingCar) {
        session.beginTransaction();
        session.delete(racingCar);
        session.getTransaction().commit();
        System.out.println("Driver was deleted: " + racingCar);
    }
}
