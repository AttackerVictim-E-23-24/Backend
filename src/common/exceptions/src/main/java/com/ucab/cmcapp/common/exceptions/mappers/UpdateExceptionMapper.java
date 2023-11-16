package com.ucab.cmcapp.common.exceptions.mappers;

import com.ucab.cmcapp.common.exceptions.FaultBean;
import com.ucab.cmcapp.common.exceptions.UpdateException;
import com.ucab.cmcapp.properties.Registry;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class UpdateExceptionMapper  implements ExceptionMapper<UpdateException>
{
    @Override
    public Response toResponse( UpdateException exception )
    {
        FaultBean faultBean = new FaultBean( Registry.getInstance().getProperty( Registry.EXC_UPDATEDB_CODE),
                                             Registry.getInstance().getProperty(Registry.EXC_UPDATEDB_MSG),
                                             exception.getMessage() );

        return Response.status( Response.Status.INTERNAL_SERVER_ERROR).entity( faultBean ).build();
    }
}
