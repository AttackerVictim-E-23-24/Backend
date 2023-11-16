package com.ucab.cmcapp.common.exceptions.mappers;

import com.ucab.cmcapp.common.exceptions.FaultBean;
import com.ucab.cmcapp.common.exceptions.JWTSetKeyException;
import com.ucab.cmcapp.properties.Registry;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class JWTSetKeyExceptionMapper implements ExceptionMapper<JWTSetKeyException>
{
    @Override
    public Response toResponse( JWTSetKeyException exception )
    {
        FaultBean faultBean = new FaultBean( Registry.getInstance().getProperty( Registry.EXC_JWTSETKEY_CODE),
                                             Registry.getInstance().getProperty(Registry.EXC_JWTSETKEY_MSG),
                                             exception.getMessage() );

        return Response.status( Response.Status.INTERNAL_SERVER_ERROR).entity( faultBean ).build();
    }
}
