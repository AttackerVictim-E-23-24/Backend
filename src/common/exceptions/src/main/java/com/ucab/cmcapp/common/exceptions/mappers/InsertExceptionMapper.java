package com.ucab.cmcapp.common.exceptions.mappers;

import com.ucab.cmcapp.common.exceptions.FaultBean;
import com.ucab.cmcapp.common.exceptions.InsertException;
import com.ucab.cmcapp.properties.Registry;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InsertExceptionMapper  implements ExceptionMapper<InsertException>
{
    @Override
    public Response toResponse( InsertException exception )
    {
        FaultBean faultBean = new FaultBean( Registry.getInstance().getProperty( Registry.EXC_INSERTDB_CODE),
                                             Registry.getInstance().getProperty(Registry.EXC_INSERTDB_MSG),
                                             exception.getMessage() );

        return Response.status( Response.Status.INTERNAL_SERVER_ERROR).entity( faultBean ).build();
    }
}
