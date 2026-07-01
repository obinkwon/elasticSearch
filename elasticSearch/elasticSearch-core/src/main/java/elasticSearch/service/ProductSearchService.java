package elasticSearch.service;

import elasticSearch.document.ProductDocument;
import elasticSearch.dto.ProductCreateRequestDto;
import elasticSearch.repository.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductSearchRepository productSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public List<String> autocomplete(String keyword) {

        NativeQuery query = NativeQuery.builder()
            .withQuery(q -> q
                .multiMatch(mm -> mm
                    .query(keyword)
                    .fields("name", "name._2gram", "name._3gram")
                    .type(co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType.BoolPrefix)
                )
            )
            .withMaxResults(10)
            .build();

        return elasticsearchOperations.search(query, ProductDocument.class)
                .stream()
                .map(SearchHit::getContent)
                .map(ProductDocument::getName)
                .distinct()
                .toList();
    }

    public void save(ProductCreateRequestDto productCreateRequestDto) {

        ProductDocument document = ProductDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(productCreateRequestDto.getName())
                .build();

        productSearchRepository.save(document);
    }

    public void bulkSave(List<String> names) {

        List<ProductDocument> documents = names.stream()
                .map(name -> ProductDocument.builder()
                        .id(UUID.randomUUID().toString())
                        .name(name)
                        .build())
                .toList();

        productSearchRepository.saveAll(documents);
    }

}
