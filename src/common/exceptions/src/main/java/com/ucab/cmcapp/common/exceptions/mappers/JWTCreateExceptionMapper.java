package com.ucab.cmcapp.common.exceptions.mappers;


import com.ucab.cmcapp.common.exceptions.FaultBean;
import com.ucab.cmcapp.common.exceptions.JWTCreateException;
import com.ucab.cmcapp.properties.Registry;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JWTCreateExceptionMapper implements ExceptionMapper<JWTCreateException>
{
    @Override
    public Response toResponse( JWTCreateException exception )
    {
        FaultBean faultBean = new FaultBean( Registry.getInstance().getProperty( Registry.EXC_JWTCREATE_CODE),
                                             Registry.getInstance().getProperty(Registry.EXC_JWTCREATE_MSG),
                                             exception.getMessage() );

        return Response.status( Response.Status.INTERNAL_SERVER_ERROR).entity( faultBean ).build();

    }

}
