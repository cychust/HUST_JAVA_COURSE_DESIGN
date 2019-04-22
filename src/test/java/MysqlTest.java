import cn.cychust.comm.Executor;
import cn.cychust.data.tbrxx.source.local.dao.UserDao;
import cn.cychust.data.tghxx.T_GHXX;
import cn.cychust.data.tghxx.source.TGHXXDataSource;
import cn.cychust.data.tksxx.T_KSXX;
import cn.cychust.data.tksxx.source.TKSXXDataSource;
import cn.cychust.data.tksxx.source.TKSXXRepository;
import cn.cychust.data.tksys.T_KSYS;
import cn.cychust.data.tksys.source.TKSYSDataSource;
import cn.cychust.data.tksys.source.TKSYSRepository;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;
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

    //    @Before
    public void setUp() {
        UserDao.createTable();
    }

    @Test
    public void testCreateTable() {
        TGHXXDataSource.getINSTANCE().createTable();
//        Thread.sleep(3000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveOne() {
        Timestamp date = Timestamp.valueOf("2018-09-12 12:17:00");

        T_KSYS one = new T_KSYS("111", "1111", "11111", "1111", ",111111", true, date);
        T_GHXX newone = new T_GHXX("111", "111", "1111", "111", 1, false, 10, date);
        TGHXXDataSource.getINSTANCE().saveOne(newone);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetOne() {
        TKSYSDataSource.getINSTANCE().getOne("111", "11111", new TKSYSRepository.GetTksysCallback() {
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

    @Test
    public void testGetAll() {
        TKSXXDataSource.getINSTANCE().getAll(new TKSXXRepository.LoadTksxxsCallback() {
            @Override
            public void onTasksLoaded(List<T_KSXX> list) {
                for (T_KSXX t_ksxx :
                        list) {
                    logger.info(t_ksxx.getKSMC());
                }
            }

            @Override
            public void onDataNotAvailable() {
                logger.info("nothing");
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
