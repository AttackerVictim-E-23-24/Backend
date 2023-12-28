package com.ucab.cmcapp.logic.commands;

import com.ucab.cmcapp.common.entities.*;
import com.ucab.cmcapp.logic.commands.mailcode.atomic.AddMailCodeCommand;
import com.ucab.cmcapp.logic.commands.mailcode.atomic.GetMailCodeByCodeCommand;
import com.ucab.cmcapp.logic.commands.mailcode.composite.CreateMailCodeCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.AddMonitoreoElectronicoCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.GetMonitoreoByCedulaCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite.CreateMonitoreoElectronicoCommand;
import com.ucab.cmcapp.logic.commands.persona.atomic.AddPersonaCommand;
import com.ucab.cmcapp.logic.commands.persona.atomic.GetPersonaByCedulaCommand;
import com.ucab.cmcapp.logic.commands.persona.composite.CreatePersonaCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.AddUserCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByIdCommand;
import com.ucab.cmcapp.logic.commands.user.composite.CreateUserCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetUserCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByEmailCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.AddZonaDeSeguridadCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.composite.CreateZonaDeSeguridadCommand;
import com.ucab.cmcapp.persistence.DBHandler;

public class CommandFactory
{

    public static GetUserCommand createGetUserCommand(User user)
    {
        return new GetUserCommand(user);
    }

    public static GetUserByEmailCommand createGetUserByEmailCommand(User user)
    {
        return new GetUserByEmailCommand(user);
    }

    public static GetUserByEmailCommand createGetUserByEmailCommand(User user, DBHandler handler)
    {
        return new GetUserByEmailCommand(user, handler);
    }

    public static GetUserByIdCommand createGetUserByIdCommand (DBHandler handler, long userId )
    {
        return new GetUserByIdCommand(handler, userId);
    }

    public static AddUserCommand createAddUserCommand(User user, DBHandler handler)
    {
        return new AddUserCommand(user, handler);
    }

    public static AddUserCommand createAddUserCommand(User user)
    {
        return new AddUserCommand(user);
    }

    public static CreateUserCommand createCreateUserCommand(User user)
    {
        return new CreateUserCommand(user);
    }

    public static AddPersonaCommand createAddPersonaCommand(Persona persona, DBHandler handler)
    {
        return new AddPersonaCommand(persona, handler);
    }

    public static AddPersonaCommand createAddPersonaCommand(Persona persona)
    {
        return new AddPersonaCommand(persona);
    }

    public static CreatePersonaCommand createCreatePersonaCommand(Persona persona)
    {
        return new CreatePersonaCommand(persona);
    }

    public static GetPersonaByCedulaCommand createGetPersonaByCedulaCommand(Persona persona){
        return new GetPersonaByCedulaCommand(persona);
    }

    public static CreateMailCodeCommand createCreateMailCodeCommand(MailCodes mailCode) {
        return new CreateMailCodeCommand(mailCode);
    }

    public static AddMailCodeCommand createAddMailCodeCommand(MailCodes mailCode, DBHandler handler) {
        return new AddMailCodeCommand(mailCode, handler);
    }
    public static GetMailCodeByCodeCommand createGetMailCodeByCodeCommand(MailCodes mailCode) {
        return new GetMailCodeByCodeCommand(mailCode);
    }

    public static AddMonitoreoElectronicoCommand createAddMonitoreoElectronicoCommand(MonitoreoElectronico monitoreoElectronico, DBHandler handler) {
        return new AddMonitoreoElectronicoCommand(monitoreoElectronico, handler);
    }

    public static CreateMonitoreoElectronicoCommand createCreateMonitoreoElectronicoCommand(MonitoreoElectronico monitoreoElectronico) {
        return new CreateMonitoreoElectronicoCommand(monitoreoElectronico);
    }

    public static AddZonaDeSeguridadCommand createAddZonaDeSeguridadCommand(ZonaDeSeguridad zonaDeSeguridad, DBHandler handler) {
        return new AddZonaDeSeguridadCommand(zonaDeSeguridad, handler);
    }

    public static CreateZonaDeSeguridadCommand createCreateZonaDeSeguridadCommand(ZonaDeSeguridad zonaDeSeguridad) {
        return new CreateZonaDeSeguridadCommand(zonaDeSeguridad);
    }

    public static GetMonitoreoByCedulaCommand createGetMonitoreoByCedulaCommand(MonitoreoElectronico monitoreoElectronico){
        return new GetMonitoreoByCedulaCommand(monitoreoElectronico);
    }

}
