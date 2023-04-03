package org.teilen.web;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.teilen.web.config.BeanPrintConfig;

@WebAppConfiguration
@ContextConfiguration(classes = {BeanPrintConfig.class})
//@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(true)
public class MyAbstractWebAppContext {

}
