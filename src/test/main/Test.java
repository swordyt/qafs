import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.qafs.service.impl.EmailServiceImpl;
import com.qafs.util.PropertiesUtil;
@ContextConfiguration(locations = { "classpath:spring-mvc.xml" })
public class Test extends AbstractTestNGSpringContextTests {
	@Resource
	EmailServiceImpl es;
	
//	public static void main(String[] args) {
//		String  env=PropertiesUtil.getProperty("env");
//		System.out.println(env);
//	}

	public void test(){
		
	}
}
