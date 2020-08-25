package myboot.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.EventListener;



public class ConfigChangeEventListener implements ApplicationListener<ConfigChangeEvent> {

    @Override
    public void onApplicationEvent(ConfigChangeEvent event) {
        System.out.println(event.getSource());
    }
}
