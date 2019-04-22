import cn.cychust.comm.Executor;
import cn.cychust.data.tbrxx.source.local.dao.UserDao;
import cn.cychust.data.tksys.T_KSYS;
import cn.cychust.data.tksys.source.TKSYSDataSource;
import cn.cychust.data.tksys.source.TKSYSRepository;
import org.apache.log4j.Logger;
import org.junit.*;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-28 20:13
 **/
public class MysqlTest {

    private static final Logger logger = Logger.getLogger(MysqlTest.class);
    static ExecutorService service;

    @BeforeClass
    public static void init() {
        service = Executor.getINSTANCE().getExecutor();
    }

    @Before
    public void setUp() {
        UserDao.createTable();
    }

    @Test
    public void testCreateTable() {
        TKSYSDataSource.getINSTANCE().createTable();
    }

    @Test
    public void saveOne() {
        Timestamp date = Timestamp.valueOf("2018-09-12 12:17:00");

        T_KSYS one = new T_KSYS("111", "1111", "11111", "1111", ",111111", true, date);
        TKSYSDataSource.getINSTANCE().saveOne(one);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetOne() {
        TKSYSDataSource.getINSTANCE().getOne("111", "11111", new TKSYSRepository.GetTbrxxCallback() {
            @Override
            public void onTasksLoaded(T_KSYS t_brxx) {
                logger.debug(t_brxx);
            }

            @Override
            public void onDataNotAvailable() {
                logger.error("not find");
            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
    }

    @AfterClass
    public static void destory() {
        service.shutdown();

    }

}
