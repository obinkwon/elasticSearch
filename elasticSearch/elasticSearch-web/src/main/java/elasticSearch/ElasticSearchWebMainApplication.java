package elasticSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"elasticSearch.web"})
public class ElasticSearchWebMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchWebMainApplication.class, args);
	}

}
