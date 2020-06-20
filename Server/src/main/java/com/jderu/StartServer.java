package com.jderu;

import com.jderu.service.IAppServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@SpringBootApplication()
public class StartServer {

    @Bean
    RmiServiceExporter exporter(IAppServices implementation) {
        Class<IAppServices> serviceInterface = IAppServices.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName("App");
        exporter.setRegistryPort(1199);
        return exporter;
    }

    public static void main(String[] args) {
        SpringApplication.run(StartServer.class, args);
    }
}
