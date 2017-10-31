import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import javax.swing.*;

/**
 * Created by QHAHA on 2017/7/8.
 */
@ContextConfiguration(locations = "classpath:META-INF/spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager" ,defaultRollback = true)
public abstract class BaseTest extends AbstractTransactionalDataSourceSpringContextTests{

}
