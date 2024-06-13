package io.travelers.platform.u20221b127.profiles.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetAllBusinessesQuery;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetBusinessByIdQuery;
import io.travelers.platform.u20221b127.profiles.domain.services.BusinessCommandService;
import io.travelers.platform.u20221b127.profiles.domain.services.BusinessQueryService;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.resources.BusinessResource;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.resources.CreateBusinessResource;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.transform.BusinessResourceFromEntityAssembler;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.transform.CreateBusinessCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing businesses.
 * <p>
 *     This controller is responsible for handling requests related to businesses.
 *     It provides endpoints for creating businesses.
 *     It also provides endpoints for searching and filtering businesses.
 * </p>
 * @author Salvador Salinas
 */

@RestController
@RequestMapping(value = "api/v1/businesses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Businesses", description = "Businesses management endpoints")
public class BusinessesController {
    private final BusinessCommandService businessCommandService;
    private final BusinessQueryService businessQueryService;

    public BusinessesController(BusinessCommandService businessCommandService, BusinessQueryService businessQueryService) {
        this.businessCommandService = businessCommandService;
        this.businessQueryService = businessQueryService;
    }

    /**
     * Create a new business.
     * @param resource the resource with the information of the business to create.
     * @return the created business.
     */
    @Operation(summary = "Create a new business", description = "Create a new business with the given information.")
    @PostMapping
    public ResponseEntity<BusinessResource> createBusiness(@RequestBody CreateBusinessResource resource) {
        var createBusinessCommand = CreateBusinessCommandFromResourceAssembler.toCommandFromResource(resource);
        var business = businessCommandService.handle(createBusinessCommand);
        if (business.isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400
        }
        var businessResource = BusinessResourceFromEntityAssembler.toResourceFromEntity(business.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(businessResource); // 201
    }

    /**
     * Get all businesses.
     * @return a list with all the businesses.
     */
    @Operation(summary = "Get all businesses", description = "Get all the businesses.")
    @GetMapping
    public ResponseEntity<List<BusinessResource>> getAllBusinesses() {
        var getAllBusinessesQuery = new GetAllBusinessesQuery();
        var businesses = businessQueryService.handle(getAllBusinessesQuery);
        var businessResources = businesses.stream()
                .map(BusinessResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(businessResources); // 200
    }

    /**
     * Get a business by its id.
     * @param id the id of the business to get.
     * @return the business with the given id.
     */
    @Operation(summary = "Get a business by its ID", description = "Get the business with the given ID.")
    @GetMapping("/{id}")
    public ResponseEntity<BusinessResource> getBusinessById(@PathVariable Long id) {
        var getBusinessByIdQuery = new GetBusinessByIdQuery(id);
        var business = businessQueryService.handle(getBusinessByIdQuery);
        if (business.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404
        }
        var businessResource = BusinessResourceFromEntityAssembler.toResourceFromEntity(business.get());
        return ResponseEntity.ok(businessResource); // 200
    }

}
