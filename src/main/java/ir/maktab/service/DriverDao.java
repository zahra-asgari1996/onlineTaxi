package ir.maktab.service;

import ir.maktab.model.Driver;
import ir.maktab.model.TypeOfTrip;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Comparator;
import java.util.List;

public class DriverDao {
    private SessionFactory sessionFactory=HibernateUtil.sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public  void saveNewDriver(Driver driver){
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        session.save(driver);
        session.getTransaction().commit();
        session.close();
    }
    public Driver findDriverById(int id){
        Session session= sessionFactory.openSession();
        Driver driver=session.get(Driver.class,id);
        session.close();
        return driver;
    }
    @SuppressWarnings("unchecked")
    @Deprecated
    public List<Driver> fetchAllDriver(){
        Session session= sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Driver.class);
        List<Driver> drivers=criteria.list();
        session.close();
        return  drivers;
    }

    public Driver returnCandidDriver(long originX,long originY){
        Session session= sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        Query query=session.createQuery("from Driver as d where d.type=:d_type")
                .setParameter("d_type",TypeOfTrip.WaitForTrip);
        List<Driver> drivers=query.list();
        System.out.println(drivers.size());

        txn.commit();
        session.close();
        Driver candid=drivers.get(0);
        for (Driver d:drivers) {
            System.out.println(d.getLocationX());
            System.out.println(d.getLocationY());
            double dist1=Math.sqrt((d.getLocationX()-originX)*(d.getLocationX()-originX)+(d.getLocationY()-originY)*(d.getLocationY()-originY));
            double dist2=Math.sqrt(((candid.getLocationX()-originX)*(candid.getLocationX()-originX))+((candid.getLocationY()-originY)*(candid.getLocationY()-originY)));
            if (dist1<dist2){
                candid=d;
            }
        }

        return  candid;
    }

    public  void setDriverType(Driver driver){
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        Query query=session.createQuery("update Driver as d " +
                "set d.type=:d_type" +
                " where d.Id=: d_id ")
                .setParameter("d_type", TypeOfTrip.OnTrip)
                .setParameter("d_id",driver.getId());
        query.executeUpdate();
        txn.commit();
        session.close();
    }


}
