package fragment.db.ddl.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fragment.db.ddl.DDLApplication;

@SpringBootTest(classes = DDLApplication.class)
@RunWith(SpringRunner.class)
public class DaoTstTest {
    @Autowired
    private DaoTst daoTst;
    @Test
    public void metaTest() {
        daoTst.test1();
    }
}