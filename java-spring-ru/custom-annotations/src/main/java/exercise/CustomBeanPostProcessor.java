package exercise;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private final Map<Object, String> beansToInspect = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String level = bean.getClass().getAnnotation(Inspect.class).level();
            beansToInspect.put(bean, level);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (beansToInspect.containsKey(bean)) {
            Class<?> beanClass = bean.getClass();
            return Proxy.newProxyInstance(
                    beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        Object invoke = method.invoke(bean, args);
                        LOGGER.info("Was called method: {}() with arguments: {}", method.getName(), args);
                        return invoke;
                    });
        }
        return bean;
    }
}
// END
