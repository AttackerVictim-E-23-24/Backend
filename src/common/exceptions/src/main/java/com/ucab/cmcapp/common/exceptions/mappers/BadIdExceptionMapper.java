package com.ucab.cmcapp.common.exceptions.mappers;

import com.ucab.cmcapp.common.exceptions.BadIdException;
import com.ucab.cmcapp.common.exceptions.FaultBean;
import com.ucab.cmcapp.properties.Registry;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BadIdExceptionMapper implements ExceptionMapper<BadIdException>
{

    @Override
    public Response toResponse( BadIdException exception )
    {
        FaultBean faultBean = new FaultBean( Registry.getInstance().getProperty( Registry.EXC_BADID_CODE ),
                                             Registry.getInstance().getProperty( Registry.EXC_BADID_MSG ),
                                             exception.getMessage() );

        return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( faultBean ).build();
    }
}
