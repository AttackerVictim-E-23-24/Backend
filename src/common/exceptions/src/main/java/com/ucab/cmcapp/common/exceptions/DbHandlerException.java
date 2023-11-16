package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

/***
 * Name: DbHandlerException
 * Description: Exception that is generated when an unexpected error occurs trying to connect to the database
 */
public class DbHandlerException extends BaseException implements Serializable
{
    public DbHandlerException( String e )
    {
        super( e );
    }
}
