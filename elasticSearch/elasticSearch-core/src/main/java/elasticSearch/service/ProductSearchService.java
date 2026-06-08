package elasticSearch.service;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import elasticSearch.document.ProductDocument;
import elasticSearch.dto.ProductCreateRequestDto;
import elasticSearch.repository.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductSearchRepository repository;
    private final ElasticsearchOperations elasticsearchOperations;

    public List<String> autocomplete(String keyword) {

        NativeQuery query = NativeQuery.builder()
                .withQuery(Query.of(q -> q
                        .match(m -> m
                                .field("name.autocomplete")
                                .query(keyword)
                        )
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

    public void save(ProductCreateRequestDto request) {

        ProductDocument document = ProductDocument.builder()
                .id(request.getId())
                .name(request.getName())
                .build();

        repository.save(document);
    }

}
