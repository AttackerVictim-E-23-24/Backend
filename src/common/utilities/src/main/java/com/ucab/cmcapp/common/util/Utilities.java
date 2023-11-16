package com.ucab.cmcapp.common.util;

import com.ucab.cmcapp.common.exceptions.ConvertObjectToJsonException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilities
{

    private static Logger _logger = LoggerFactory.getLogger( Utilities.class );

    public JSONObject jsonToObject(String json)
    {
        _logger.debug( "Get in Utilities.jsonToObject" );
        JSONObject obj = null;
        try
        {
            obj = new JSONObject(json);
        }
        catch (JSONException ex)
        {
            _logger.error( String.format("Error: ", ex.getMessage()) );
            throw new ConvertObjectToJsonException( ex.getMessage());
        }
        _logger.debug( "Leavin Utilities.jsonToObject" );
        return obj;
    }
}
