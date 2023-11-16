package com.ucab.cmcapp.common.exceptions.mappers;


import com.ucab.cmcapp.common.exceptions.FaultBean;
import com.ucab.cmcapp.common.exceptions.JWTVerifyException;
import com.ucab.cmcapp.properties.Registry;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JWTVerifyExceptionMapper implements ExceptionMapper<JWTVerifyException>
{
    @Override
    public Response toResponse( JWTVerifyException exception )
    {
        FaultBean faultBean = new FaultBean( Registry.getInstance().getProperty( Registry.EXC_JWTVERIFY_CODE),
                                             Registry.getInstance().getProperty(Registry.EXC_JWTVERIFY_MSG),
                                             exception.getMessage() );

        return Response.status( Response.Status.UNAUTHORIZED).entity( faultBean ).build();
    }
}
