package lt.vu.web.api.v1.controller.packageType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.persistence.entities.PackageType;
import lt.vu.persistence.repository.PackageTypeRepository;
import lt.vu.infrastructure.interceptors.LoggedAction;
import lt.vu.web.api.v1.dto.packageType.ListPackageTypeDTO;
import lt.vu.web.api.v1.dto.packageType.GetPackageTypeDTO;
import lt.vu.web.api.v1.exception.ExceptionDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/package-types")
@RequestScoped
public class ListPackageTypeController {

    @Inject
    private PackageTypeRepository packageTypeRepository;

    @GET
    @Path("/")
    @LoggedAction
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Fetch list of package types available",
        tags = { "PackageType" },
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = ListPackageTypeDTO.class))
            ),
            @ApiResponse(
                responseCode = "500",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            )
        }
    )
    public Response listAction() {
        List<PackageType> packageTypes = this.packageTypeRepository.findAll();

        return Response
                .ok(new ListPackageTypeDTO(GetPackageTypeDTO.createMany(packageTypes)))
                .build();
    }
}
