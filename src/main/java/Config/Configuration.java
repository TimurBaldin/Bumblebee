package Config;

import Repository.RepositiryTestValues;
import Services.LinesService;
import Services.ReportService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Configuration {
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ReportService getReportService(){
        return new ReportService();
    }
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public RepositiryTestValues getRepositiry() {
    return new RepositiryTestValues();
}
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public LinesService linesService() {
    return new LinesService(getReportService(),getRepositiry());
}
}
