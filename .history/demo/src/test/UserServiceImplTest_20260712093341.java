import java.time.LocalDateTime;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.exeption.UserServiceImpl;

public class UserServiceImplTest {
    private UserDao mockedUserDao = mock(UserDao.class);
    private UserServiceImpl serviceImpl = new UserServiceImpl(mockedUserDao);

    @BeforeEach
    public void setUp() {
        user = new User(1, "test name", "test email", 18, LocalDateTime.now());
    }

    @Test
    public void createTest(){
        serviceImpl.create(user);
        verify(mockedUserDao, times(1)).create(user);
    }

    @Test
    public void readAllTest(){
        List<User> innerList = List.of(user);

        when(mockedUserDao.readAll())
            .thenReturn(innerList);
        
        List<User> resultList = serviceImpl.readAll();

        assertEquals(innerList, resultList);
        verify(mockedUserDao, times(1)).readAll();
    }

    @Test
    public void readByIdTest(){
        when(mockedUserDao.readById(1))
            .thenReturn(innerUser);
        
        User resultUser = serviceImpl.readById(1);

        assertEquals(innerUser, resultUser);
        verify(mockedUserDao, times(1)).readById(1);
    }

    @Test
    public void updateTest(){
        serviceImpl.update(user);
        verify(mockedUserDao, times(1)).update(user);
    }

    @Test
    public void deleteTest(){
        serviceImpl.delete(user);
        verify(mockedUserDao, times(1)).delete(user);
    }
}
