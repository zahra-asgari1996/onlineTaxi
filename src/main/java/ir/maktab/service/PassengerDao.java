package ir.maktab.service;

import ir.maktab.model.Driver;
import ir.maktab.model.Passenger;
import ir.maktab.model.TypeOfTrip;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PassengerDao {
    private SessionFactory sessionFactory=HibernateUtil.sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public  void saveNewPassenger(Passenger passenger){
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        session.save(passenger);
        session.getTransaction().commit();
        session.close();
    }
    public Passenger findPassengerById(int id){
        Session session= sessionFactory.openSession();
        Passenger passenger=session.get(Passenger.class,id);
        session.close();
        return passenger;
    }
    @SuppressWarnings("unchecked")
    @Deprecated
    public List<Passenger> fetchAllPassenger(){
            Session session = sessionFactory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            // output of query = type of CriteriaQuery :)
            CriteriaQuery<Passenger> criteriaQuery = criteriaBuilder.createQuery(Passenger.class);
            // which table that run query on it ? -> Contact
            Root<Passenger> root = criteriaQuery.from(Passenger.class);
            // send query from my program to database
            // SELECT * FROM contact
            criteriaQuery.select(root);
            Query query = session.createQuery(criteriaQuery);
            // get result =)
            List<Passenger> contactList = query.getResultList();
            session.close();
            return contactList;

    }

    public void  setPassengerType(Passenger passenger){
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        Query query=session.createQuery("update Passenger as p " +
                "set p.type=:p_type" +
                " where p.Id=: p_id ")
                .setParameter("p_type", TypeOfTrip.OnTrip)
                .setParameter("p_id",passenger.getId());
        query.executeUpdate();
        txn.commit();
        session.close();
    }

    public void increaseBalance(Passenger passenger,long balance){
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        Query query=session.createQuery("update Passenger as p " +
                "set p.balance=:p_balance" +
                " where p.Id=: p_id ")
                .setParameter("p_balance", balance+ passenger.getBalance())
                .setParameter("p_id",passenger.getId());
        query.executeUpdate();
        txn.commit();
        session.close();
    }
}
