import java.util.List;

import com.example.config.HibernateUtil;
import com.example.dao.UserDaoImpl;
import com.example.entity.User;

@Testcontainers
public class UserDaoIntegrationTest {
    @Container
    private static final PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres:18")
            .withDatabaseName("CRUD-testApp")
            .withUsername("postgres")
            .withPassword("123321");

    private SessionFactory sessionFactory;
    private UserDaoImpl userDao;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("test name", "test email", 18, LocalDateTime.now());
        sessionFactory = HibernateUtil.createSessionFactory(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
        userDao = new UserDaoImpl(sessionFactory);
    }

    @BeforeEach
    private void clearDatabase() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createMutationQuery("DELETE FROM User").executeUpdate();
            tx.commit();
        }
    }

    @AfterEach
    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void createDaoTest(){
        userDao.create(user);

        User resultUser = userDao.readById(user.getId());
        assertEquals(user, resultUser);
        assertTrue(user.getId() > 0);
    }

    @Test
    public void updateDaoTest(){
        userDao.create(user);

        user.setName("Test2");
        userDao.update(user);

        User resultUser = userDao.readById(user.getId());
        assertEquals(user, resultUser);
    }

    @Test
    public void deleteDaoTest(){
        userDao.create(user);

        userDao.delete(user);

        User resultUser = userDao.readById(user.getId());
        assertNull(resultUser);
    }

    @Test
    public void readAllDaoTest(){
        userDao.create(user);
        User user2 = new User("Test2", "Test2", 19, LocalDateTime.now());
        userDao.create(user2);

        List<User> testList = List.of(user, user2);
        List<User> resultList = userDao.readAll();
        
        assertEquals(testList, resultList);
    }

    @Test
    public void readByIdDaoTest(){
        userDao.create(user);

        User resultUser = userDao.readById(user.getId());
        assertEquals(user, resultUser);
    }
}
