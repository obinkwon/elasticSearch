package elasticSearch.config;

import elasticSearch.document.ProductDocument;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;

@Configuration
@RequiredArgsConstructor
public class ElasticsearchIndexConfig {

    private final ElasticsearchOperations operations;

    @PostConstruct
    public void createIndex() {

        IndexOperations indexOps =
                operations.indexOps(ProductDocument.class);

        if (!indexOps.exists()) {

            indexOps.create();

            Document mapping =
                    indexOps.createMapping(ProductDocument.class);

            indexOps.putMapping(mapping);
        }
    }
}
