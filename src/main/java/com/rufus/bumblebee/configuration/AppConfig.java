package com.rufus.bumblebee.configuration;

import com.rufus.bumblebee.controllers.ContainerController;
import com.rufus.bumblebee.controllers.GeneratorsController;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.generators.SymbolBaseGenerator;
import com.rufus.bumblebee.generators.annotation.AnnotationHandler;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.TestDataRepository;
import com.rufus.bumblebee.services.AsyncGeneratorService;
import com.rufus.bumblebee.services.ContainerService;
import com.rufus.bumblebee.services.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.List;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AppConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        return executor;
    }

    @Bean("methodValidationPostProcessor")
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean("containerController")
    public ContainerController getContainerController(@Autowired ContainerService service){
        return new ContainerController(service);
    }

    @Bean("containerService")
    public ContainerService getContainerService(@Autowired ContainerRepository repository){
        return new ContainerService(repository);
    }

    @Bean("containerRepository")
    public ContainerRepository getContainerRepository(){
        return new ContainerRepository();
    }

    @Bean("generatorsController")
    public GeneratorsController getGeneratorsController(@Autowired GeneratorService service){
        return new GeneratorsController(service);
    }

    @Bean("generatorService")
    public GeneratorService getGeneratorService(@Autowired AnnotationHandler handler,
                                                @Autowired ContainerRepository repository,
                                                @Autowired AsyncGeneratorService service){
        return new GeneratorService(handler,repository,service);
    }

    @Bean("annotationHandler")
    public AnnotationHandler getAnnotationHandler(@Autowired List<BaseGenerator> generators,
                                                  @Autowired ApplicationContext context){
        return new AnnotationHandler(generators,context);
    }

    @Bean("asyncGeneratorService")
    public AsyncGeneratorService getAsyncGeneratorService(@Autowired ContainerRepository repository,
                                                          @Autowired TestDataRepository testDataRepository){
        return new AsyncGeneratorService(testDataRepository,repository);
    }

    @Bean("testDataRepository")
    public TestDataRepository getTestDataRepository(){
        return new TestDataRepository();
    }

    @Bean("symbolBaseGenerator")
    public SymbolBaseGenerator getSymbolBaseGenerator(){
        return new SymbolBaseGenerator();
    }
}