package tp.appliSpring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.core.config.CommonConfig;
import tp.appliSpring.core.config.TraducteurConfig;

//@RunWith(SpringJUnit4ClassRunner.class) //si JUnit4
@ExtendWith(SpringExtension.class ) //si JUnit5/jupiter
@ContextConfiguration(classes={CommonConfig.class,TraducteurConfig.class})
class TestTraducteur {

	@Test
	void contextLoads() {
	}

}
