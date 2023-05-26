package com.sadeghi.billpayment;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

/**
 * Description of file goes here
 *
 * @author Ali Sadeghi
 * Created at 2022/05/02 - 2:26 PM
 */
@SpringBootTest(classes = BillPaymentApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("h2")
@TestPropertySource(locations = "classpath:application-h2.yml")
public abstract class BaseTestClass {

}
