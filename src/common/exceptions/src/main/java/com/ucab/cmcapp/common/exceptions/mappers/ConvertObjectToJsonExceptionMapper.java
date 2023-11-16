package com.ucab.cmcapp.common.exceptions.mappers;

import com.ucab.cmcapp.common.exceptions.FaultBean;
import com.ucab.cmcapp.common.exceptions.ConvertObjectToJsonException;
import com.ucab.cmcapp.properties.Registry;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ConvertObjectToJsonExceptionMapper implements ExceptionMapper<ConvertObjectToJsonException>
{
    @Override
    public Response toResponse( ConvertObjectToJsonException exception )
    {
        FaultBean faultBean = new FaultBean( Registry.getInstance().getProperty( Registry.EXC_UTILITIES_CODE),
                                             Registry.getInstance().getProperty(Registry.EXC_UTILITIES_MSG),
                                             exception.getMessage() );

        return Response.status( Response.Status.INTERNAL_SERVER_ERROR).entity( faultBean ).build();
    }
}
