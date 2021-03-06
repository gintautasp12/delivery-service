package lt.vu.web.api.v1.exception;

import lt.vu.application.exception.BadRequestException;
import lt.vu.infrastructure.logger.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(BadRequestException e) {
        this.logger.warning(e);

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ExceptionDTO.create(e, Response.Status.BAD_REQUEST))
                .build();
    }
}
