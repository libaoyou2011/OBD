package com.baolong.obd.common.injector.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope  //限定注解作用域，来管理每个对象实例的生命周期
@Retention(RetentionPolicy.RUNTIME) //注意注解范围是Runtime级别
public @interface ApplicationScope {
}