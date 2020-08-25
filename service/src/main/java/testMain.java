import myboot.config.DataSouceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
@Component
@ComponentScan(value = {"myboot.config"})
public class testMain {

    @Value("${testYsg:1}")
    private String testYsg;

    @Autowired
    public DataSouceConfig dataSouceConfig;

    public static void main(String[] args) {
//        DefaultListableBeanFactory defaultListableBeanFactory =new DefaultListableBeanFactory();
//
//        PropertySourcesPlaceholderConfigurer.DEFAULT_PLACEHOLDER_PREFIX
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//
//        context.register(testMain.class);
//        context.refresh();
//        System.out.println(((DataSouceConfig) context.getBean("dataSouceConfig")).maxActive);
//        System.out.println(((testMain) context.getBean("testMain")).testYsg);
    }




}
