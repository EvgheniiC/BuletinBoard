package com.evghenii.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = {"com.evghenii.dao", "com.evghenii.service"})
@ComponentScan(basePackages = "com")
public class ConfigApp {


}
