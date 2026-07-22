import java.util.Collection;

import jakarta.transaction.Transaction;

public interface UserDao {
    void create(User user);

    Collection<User> readAll();

    User readById(int id);

    void update(User user);

    void delete(int id);
}

public class UserDaoImpl implements UserDao{
    @Override
    public void create(User user){

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
        catch(Exception e){
            if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                transaction.rollback();
            }
        }
    }

    @Override
    public Collection<User> readAll(){
        Transaction transaction = null;
        Collection<User> users = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            users = session.get(User.class);
            transaction.commit();
        }
        catch(Exception e){
            if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                transaction.rollback();
            }
        }
    }

    @Override
    public User readById(int id){
        
    }

    @Override
    public void update(User user){
        
    }

    @Override
    public void delete(int id){
        
    }
}
