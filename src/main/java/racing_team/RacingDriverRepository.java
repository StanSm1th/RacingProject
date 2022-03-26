package racing_team;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class RacingDriverRepository extends RacingCarRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void saveDriver(RacingDriver newRacingDriver) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(newRacingDriver);
        session.getTransaction().commit();
        System.out.println("Driver was created: " + newRacingDriver);
    }

    public void deleteDriver(RacingDriver driverToDelete) {
        session.beginTransaction();
        session.delete(driverToDelete);
        session.getTransaction().commit();
        System.out.println("Driver was deleted: " + driverToDelete);
    }

    public RacingDriver findDriverById(Integer id) {
        return session.find(RacingDriver.class, id);
    }

    public void updateDriverOutfit(RacingDriver driver, String newOutfitColor) {
        Transaction transaction = session.beginTransaction();
        driver.setOutfitColor(newOutfitColor);
        transaction.commit();
        System.out.println("Drivers outfit color changed to: " + driver.getOutfitColor());
    }

    public void updateDriverSalary(RacingDriver driver, Long newSalary) {
        Transaction transaction = session.beginTransaction();
        driver.setSalary(newSalary);
        transaction.commit();
        System.out.println("Drivers new salary set to: " + driver.getSalary());
    }










}
