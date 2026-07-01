package elasticSearch.api.controller;

import elasticSearch.dto.ProductCreateRequestDto;
import elasticSearch.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody ProductCreateRequestDto productCreateRequestDto) {
        productSearchService.save(productCreateRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<Void> bulkSave(@RequestBody List<String> names) {
        productSearchService.bulkSave(names);
        return ResponseEntity.ok().build();
    }
}
