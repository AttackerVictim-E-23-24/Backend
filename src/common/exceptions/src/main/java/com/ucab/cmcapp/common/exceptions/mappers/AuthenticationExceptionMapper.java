package com.ucab.cmcapp.common.exceptions.mappers;

import com.ucab.cmcapp.common.exceptions.AuthenticationException;
import com.ucab.cmcapp.common.exceptions.FaultBean;
import com.ucab.cmcapp.properties.Registry;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException>
{

    @Override
    public Response toResponse( AuthenticationException exception )
    {
        FaultBean faultBean = new FaultBean( Registry.getInstance().getProperty( Registry.EXC_AUTH_CODE ),
                                             Registry.getInstance().getProperty( Registry.EXC_AUTH_MSG ),
                                             exception.getMessage() );

        return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( faultBean ).build();
    }
}
