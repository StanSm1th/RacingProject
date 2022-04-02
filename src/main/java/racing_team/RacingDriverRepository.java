package racing_team;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Entity;

@Entity
public class RacingDriverRepository extends RacingCarRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void saveDriver(RacingDriver newRacingDriver) {
        session.beginTransaction();
        session.save(newRacingDriver);
        session.getTransaction().commit();
        System.out.println("NEW driver added : " + newRacingDriver);
    }

    public RacingDriver findDriverById(Integer id) {
        return session.find(RacingDriver.class, id);
    }

    public void deleteDriverByID(RacingDriver racingDriver) {
        session.beginTransaction();
        session.delete(racingDriver);
        session.getTransaction().commit();
        System.out.println("Driver was deleted: " + racingDriver);
    }

    public void updateDriverOutfit(RacingDriver driver, Integer newSalary) {
        Transaction transaction = session.beginTransaction();
        driver.setSalary(newSalary);
        transaction.commit();
        System.out.println("Drivers outfit color changed to: " + driver.getSalary());
    }

    public void updateDriverSalary(RacingDriver driver, Integer newSalary) {
        Transaction transaction = session.beginTransaction();
        driver.setSalary(newSalary);
        transaction.commit();
        System.out.println("Drivers new salary set to: " + driver.getSalary());
    }















}
