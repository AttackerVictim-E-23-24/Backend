package com.ucab.cmcapp.common.exceptions.mappers;

import com.ucab.cmcapp.common.exceptions.DeleteException;
import com.ucab.cmcapp.common.exceptions.FaultBean;
import com.ucab.cmcapp.properties.Registry;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DeleteExceptionMapper implements ExceptionMapper<DeleteException>
{
    @Override
    public Response toResponse( DeleteException exception )
    {
        FaultBean faultBean = new FaultBean( Registry.getInstance().getProperty( Registry.EXC_DELETEDB_CODE),
                                             Registry.getInstance().getProperty(Registry.EXC_DELETEDB_MSG),
                                             exception.getMessage() );

        return Response.status( Response.Status.INTERNAL_SERVER_ERROR).entity( faultBean ).build();
    }
}
