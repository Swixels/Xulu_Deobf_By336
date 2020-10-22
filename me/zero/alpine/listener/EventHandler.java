package me.zero.alpine.listener;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
}
