package elasticSearch.api.controller;

import elasticSearch.dto.ProductCreateRequestDto;
import elasticSearch.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @GetMapping("/autocomplete")
    public List<String> autocomplete(@RequestParam String keyword) {
        return productSearchService.autocomplete(keyword);
    }

    @PostMapping
    public void save(@RequestBody ProductCreateRequestDto request) {
        productSearchService.save(request);
    }
}
