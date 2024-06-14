package io.travelers.platform.u20221b127.profiles.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetAgencyByIdQuery;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetAllAgenciesQuery;
import io.travelers.platform.u20221b127.profiles.domain.services.AgencyCommandService;
import io.travelers.platform.u20221b127.profiles.domain.services.AgencyQueryService;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.resources.AgencyResource;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.resources.CreateAgencyResource;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.transform.AgencyResourceFromEntityAssembler;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.transform.CreateAgencyCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing agencies.
 * <p>
 *     This controller is responsible for handling requests related to agencies.
 *     It provides endpoints for creating agencies.
 *     It also provides endpoints for searching and filtering agencies.
 * </p>
 * @author Salvador Salinas
 * @version 1.0
 */

@RestController
@RequestMapping(value = "api/v1/agencies", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Agencies", description = "Agencies management endpoints")
public class AgenciesController {
    private final AgencyCommandService agencyCommandService;
    private final AgencyQueryService agencyQueryService;

    public AgenciesController(AgencyCommandService agencyCommandService, AgencyQueryService agencyQueryService) {
        this.agencyCommandService = agencyCommandService;
        this.agencyQueryService = agencyQueryService;
    }

    /**
     * Create a new agency.
     * @param resource the resource with the information of the agency to create.
     * @return the created agency.
     */
    @Operation(summary = "Create a new agency", description = "Create a new agency with the given information.")
    @PostMapping
    public ResponseEntity<AgencyResource> createAgency(@RequestBody CreateAgencyResource resource) {
        var createAgencyCommand = CreateAgencyCommandFromResourceAssembler.toCommandFromResource(resource);
        var agency = agencyCommandService.handle(createAgencyCommand);
        if (agency.isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400
        }
        var agencyResource = AgencyResourceFromEntityAssembler.toResourceFromEntity(agency.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(agencyResource); // 201

    }

    /**
     * Get all agencies.
     * @return all agencies.
     */
    @Operation(summary = "Get all agencies", description = "Get all agencies created.")
    @GetMapping
    public ResponseEntity<List<AgencyResource>> getAllAgencies() {
        var getAllAgenciesQuery = new GetAllAgenciesQuery();
        var agencies = agencyQueryService.handle(getAllAgenciesQuery);
        var agenciesResource = agencies.stream()
                .map(AgencyResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(agenciesResource); // 200
    }

    /**
     * Get an agency by its ID.
     * @param id the ID of the agency to get.
     * @return the agency with the given ID.
     */
    @Operation(summary = "Get an agency", description = "Get an agency by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<AgencyResource> getAgencyById(@PathVariable Long id) {
        var getAgencyByIdQuery = new GetAgencyByIdQuery(id);
        var agency = agencyQueryService.handle(getAgencyByIdQuery);
        if (agency.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404
        }
        var agencyResource = AgencyResourceFromEntityAssembler.toResourceFromEntity(agency.get());
        return ResponseEntity.ok(agencyResource); // 200
    }

}
