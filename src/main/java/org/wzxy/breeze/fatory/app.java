package org.wzxy.breeze.fatory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 覃能健
 * @create 2020-05
 */
public class app {
  public  static ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(fatoryBean.class);
}
