package com.enva.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({"com.enva.service", "com.enva.repository", "com.enva.config"})
@Configuration
public class JavaConfig {
}
