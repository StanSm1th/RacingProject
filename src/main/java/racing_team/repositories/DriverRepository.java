package racing_team.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import racing_team.entities.Driver;
import racing_team.utils.HibernateUtil;

import javax.persistence.Entity;

@Entity
public class DriverRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void saveDriver(Driver newRacingDriver) {
        session.beginTransaction();
        session.save(newRacingDriver);
        session.getTransaction().commit();
        System.out.println("NEW driver added : " + newRacingDriver);
    }

    public Driver findDriverById(Integer id) {
        return session.find(Driver.class, id);
    }

    public void deleteDriverByID(Driver racingDriver) {
        session.beginTransaction();
        session.delete(racingDriver);
        session.getTransaction().commit();
        System.out.println("Driver was deleted: " + racingDriver);
    }

    public void updateDriverSalary(Driver driver, Integer newSalary) {
        findDriverById(driver.getDriverId());
        Transaction transaction = session.beginTransaction();
        driver.setSalary(newSalary);
        transaction.commit();
        System.out.println("Drivers new salary set to: " + driver.getSalary());

    }

}
