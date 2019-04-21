import cn.cychust.comm.Executor;
import cn.cychust.data.tbrxx.T_BRXX;
import cn.cychust.data.tbrxx.source.TBRXXDataSource;
import cn.cychust.data.tbrxx.source.TBRXXRepository;
import cn.cychust.data.tbrxx.source.local.dao.UserDao;
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
        Timestamp date = Timestamp.valueOf("2018-09-12");

        T_BRXX one = new T_BRXX("111111", "1111", "11111", 1, date);
        new TBRXXDataSource(service).saveOne(one);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetOne() {
        new TBRXXDataSource(service).getOne("111", "11111", new TBRXXRepository.GetTbrxxCallback() {
            @Override
            public void onTasksLoaded(T_BRXX t_brxx) {
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
