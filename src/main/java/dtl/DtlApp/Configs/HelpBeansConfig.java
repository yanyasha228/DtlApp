package dtl.DtlApp.Configs;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.jackson2.JacksonFactory;
import dtl.DtlApp.Handlers.ErrorHandlers.RestTemplateResponseErrorHandler;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
@ComponentScan
@PropertySource("classpath:googleCred/client_secret.json")
@PropertySource("classpath:sheets.properties")
@PropertySource("classpath:file.properties")
public class HelpBeansConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().errorHandler(new RestTemplateResponseErrorHandler()).build();
    }


    @Bean
    public JacksonFactory getJacksonFactory(){
        return JacksonFactory.getDefaultInstance();
    }
//        return new JacksonFactory().get;
//
//        // ...
//
//        return xmlConverter;
//    }

}
