package com.ucab.cmcapp.logic.commands;

import com.ucab.cmcapp.persistence.DBHandler;

import java.io.IOException;

public abstract class Command<T>
{
    private DBHandler _handler;

    public DBHandler getHandler()
    {
        return _handler;
    }

    public void setHandler(DBHandler handler)
    {
        _handler = handler;
    }

    public abstract void execute() throws IOException;

    public abstract T getReturnParam();

    public abstract void closeHandlerSession();

}
