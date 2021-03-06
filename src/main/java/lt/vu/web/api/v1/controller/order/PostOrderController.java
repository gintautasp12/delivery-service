package lt.vu.web.api.v1.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lt.vu.application.exception.NotFoundException;
import lt.vu.persistence.entities.Order;
import lt.vu.persistence.repository.OrderRepository;
import lt.vu.infrastructure.interceptors.LoggedAction;
import lt.vu.web.api.v1.controller.security.CurrentUserAwareController;
import lt.vu.web.api.v1.exception.ExceptionDTO;
import lt.vu.application.order.factory.OrderFactory;
import lt.vu.web.api.v1.dto.order.PostOrderDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
@RequestScoped
public class PostOrderController extends CurrentUserAwareController {

    @Inject
    private OrderFactory orderFactory;

    @Inject
    private OrderRepository orderRepository;

    @POST
    @Path("/")
    @LoggedAction
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Operation(
        summary = "Submit new order",
        tags = { "Order" },
        responses = {
            @ApiResponse(
                responseCode = "201"
            ),
            @ApiResponse(
                responseCode = "404",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            ),
            @ApiResponse(
                responseCode = "500",
                content = @Content(schema = @Schema(implementation = ExceptionDTO.class))
            ),
        }
    )
    public Response createAction(
        @RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = PostOrderDTO.class))
        ) @Valid PostOrderDTO orderDTO) throws NotFoundException {

        Order order = this.orderFactory.createFromDTO(this.user, orderDTO);

        this.orderRepository.persist(order);

        return Response.status(Response.Status.CREATED).build();
    }
}
