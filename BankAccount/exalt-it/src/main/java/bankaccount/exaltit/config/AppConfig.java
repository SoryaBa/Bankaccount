package bankaccount.exaltit.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	/*    
	@Bean
	public TransferService transferService() {
	        return new TransferServiceImpl();
	    }
	}*/

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
