package ir.maktab.service;

import ir.maktab.model.Driver;
import ir.maktab.model.Travel;
import ir.maktab.model.TypeOfTrip;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TravelDao {
    private  SessionFactory sessionFactory=HibernateUtil.sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public  void saveNewTravel(Travel travel){
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        session.save(travel);
        session.getTransaction().commit();
        session.close();
    }
    @SuppressWarnings("unchecked")
    @Deprecated
    public List<Travel> fetchAllTravels(){
        Session session= sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Travel.class);
        List<Travel> travels=criteria.list();
        session.close();
        return  travels;
    }
//    public void finishTravelWithDriver(Driver driver){
//
//        Session session= sessionFactory.openSession();
//        CriteriaBuilder cb=session.getCriteriaBuilder();
//        // output of query is <contact> criteria
//        CriteriaQuery<Travel> cr=cb.createQuery(Travel.class);
//        // table for query is contact
//        Root<Travel> root=cr.from(Travel.class);
//        //select * from contact
//        //cr.select(root.getModel()).where;
//        Query query=session.createQuery(cr);
//
//
//        session.close();
//
//
//    }
public void finishTravel(Driver driver) {
    Session session = sessionFactory.openSession();
    Transaction txn = session.beginTransaction();
    Query query1=session.createQuery("update Passenger as p " +
            "set p.type=:p_type" +
            " where p.Id in " +
            "(select t.passenger.Id" +
            " from Travel as t " +
            "where t.driver.Id=:d_id and t.type=:t_type)")
            .setParameter("p_type",TypeOfTrip.WaitForTrip)
            .setParameter("d_id",driver.getId())
            .setParameter("t_type",TypeOfTrip.OnTrip);
    query1.executeUpdate();

   Query query2=session.createQuery("update Driver as d" +
           "  set d.type =: d_type " +
           "where d.Id in" +
           " (select t.driver.Id " +
           "from Travel as t" +
           " where t.driver.Id=: t_driver and t.type=:t_type )")
           .setParameter("d_type",TypeOfTrip.WaitForTrip)
           .setParameter("t_driver",driver.getId())
           .setParameter("t_type",TypeOfTrip.OnTrip);
   query2.executeUpdate();



    Query query3=session.createQuery("select t.destinationX   from Travel  as t where t.driver.id=: d_id and t.type=:t_type")
            .setParameter("d_id",driver.getId())
            .setParameter("t_type",TypeOfTrip.OnTrip);
    //long destinationX= (long) query3.uniqueResult();
    //query3.executeUpdate();

    Query query4=session.createQuery("select t.destinationY   from Travel  as t where t.driver.id=: d_id and t.type=:t_type")
            .setParameter("d_id",driver.getId())
            .setParameter("t_type",TypeOfTrip.OnTrip);
    //long destinationY= (long) query4.uniqueResult();

    //query4.executeUpdate();

    Query query5=session.createQuery("update Driver as d" +
            "  set d.locationX=:locationX , d.locationY=:locationY " +
            "where d.Id in" +
            " (select t.driver.Id " +
            "from Travel as t" +
            " where t.driver.Id=: t_driver and t.type=:t_type )")
            .setParameter("locationX",query3.uniqueResult())
            .setParameter("locationY",query3.uniqueResult())
            .setParameter("t_driver",driver.getId())
            .setParameter("t_type",TypeOfTrip.OnTrip);
    query5.executeUpdate();

    Query query = session.createQuery(
            "UPDATE Travel t" +
                    " SET t.type = :t_type" +
                    " WHERE t.driver.Id = :t_driver and t.type=:typetravel"
    ).setParameter("t_type", TypeOfTrip.WaitForTrip)
            .setParameter("t_driver", driver.getId())
            .setParameter("typetravel",TypeOfTrip.OnTrip);
    query.executeUpdate();


     txn.commit();
    session.close();

}
}
