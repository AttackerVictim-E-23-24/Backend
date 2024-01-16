package com.ucab.cmcapp.logic.commands;

import com.ucab.cmcapp.common.entities.*;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.*;
import com.ucab.cmcapp.logic.commands.coordenada.composite.CreateCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.GetAllCoordenadasByUsernameCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.GetCoordenadasZonaCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.UpdateCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.atomic.AddHistoricoCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.atomic.GetAllMovimientoBUserCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.composite.CreateHistoricoCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.composite.GetAllMovimientoByUsernameCommand;
import com.ucab.cmcapp.logic.commands.mailcode.atomic.AddMailCodeCommand;
import com.ucab.cmcapp.logic.commands.mailcode.atomic.GetMailCodeByCodeCommand;
import com.ucab.cmcapp.logic.commands.mailcode.composite.CreateMailCodeCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.*;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite.CreateMonitoreoElectronicoCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite.DeleteMonitoreoCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite.UpdateMonitoreoCommand;
import com.ucab.cmcapp.logic.commands.persona.atomic.AddPersonaCommand;
import com.ucab.cmcapp.logic.commands.persona.atomic.GetPersonaByCedulaCommand;
import com.ucab.cmcapp.logic.commands.persona.atomic.PutPersonaCommand;
import com.ucab.cmcapp.logic.commands.persona.composite.CreatePersonaCommand;
import com.ucab.cmcapp.logic.commands.persona.composite.UpdatePersonaCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.*;
import com.ucab.cmcapp.logic.commands.user.composite.CreateUserCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetAllUsersCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetUserCommand;
import com.ucab.cmcapp.logic.commands.user.composite.UpdateUserCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.*;
import com.ucab.cmcapp.logic.commands.zona_seguridad.composite.*;
import com.ucab.cmcapp.persistence.DBHandler;

public class CommandFactory {

    public static GetUserCommand createGetUserCommand(User user) {
        return new GetUserCommand(user);
    }

    public static GetUserByEmailCommand createGetUserByEmailCommand(User user) {
        return new GetUserByEmailCommand(user);
    }

    public static GetUserByUsernameCommand createGetUserByUsernameCommand(User user) {
        return new GetUserByUsernameCommand(user);
    }

    public static GetUserByEmailCommand createGetUserByEmailCommand(User user, DBHandler handler) {
        return new GetUserByEmailCommand(user, handler);
    }

    public static GetUserByIdCommand createGetUserByIdCommand(DBHandler handler, long userId) {
        return new GetUserByIdCommand(handler, userId);
    }

    public static AddUserCommand createAddUserCommand(User user, DBHandler handler) {
        return new AddUserCommand(user, handler);
    }

    public static AddUserCommand createAddUserCommand(User user) {
        return new AddUserCommand(user);
    }

    public static CreateUserCommand createCreateUserCommand(User user) {
        return new CreateUserCommand(user);
    }

    public static GetAllUsersCommand createGetAllUsersCommand(){
        return new GetAllUsersCommand();
    }

    public static GetAllUsersWPCommand createGetAllUsersWPCommand( DBHandler handler) {
        return new GetAllUsersWPCommand( handler );
    }

    public static AddPersonaCommand createAddPersonaCommand(Persona persona, DBHandler handler) {
        return new AddPersonaCommand(persona, handler);
    }

    public static AddPersonaCommand createAddPersonaCommand(Persona persona) {
        return new AddPersonaCommand(persona);
    }

    public static UpdatePersonaCommand createUpdatePersonaCommand(Persona persona) {
        return new UpdatePersonaCommand(persona);
    }
    public static PutPersonaCommand createPutPersonaCommand(Persona persona, DBHandler handler) {
        return new PutPersonaCommand(persona, handler);
    }
    public static CreatePersonaCommand createCreatePersonaCommand(Persona persona) {
        return new CreatePersonaCommand(persona);
    }

    public static GetPersonaByCedulaCommand createGetPersonaByCedulaCommand(Persona persona) {
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

    public static GetMonitoreoByCedulaCommand createGetMonitoreoByCedulaCommand(MonitoreoElectronico monitoreoElectronico) {
        return new GetMonitoreoByCedulaCommand(monitoreoElectronico);
    }

    public static GetMonitoreoByCedulaNombreUsuarioCommand createGetMonitoreoByCedulaNombreUsuarioCommand(MonitoreoElectronico monitoreoElectronico) {
        return new GetMonitoreoByCedulaNombreUsuarioCommand(monitoreoElectronico);
    }

    public static UpdateMonitoreoCommand createUpdateMonitoreoCommand(MonitoreoElectronico monitoreoElectronico) {
        return new UpdateMonitoreoCommand(monitoreoElectronico);
    }

    public static PutMonitoreoCommand createPutMonitoreoCommand(MonitoreoElectronico monitoreoElectronico, DBHandler handler) {
        return new PutMonitoreoCommand(monitoreoElectronico, handler);
    }

    public static DeleteMonitoreoCommand createDeleteMonitoreoCommand(MonitoreoElectronico monitoreoElectronico) {
        return new DeleteMonitoreoCommand(monitoreoElectronico);
    }

    public static DelMonitoreoCommand createDelMonitoreoCommand(MonitoreoElectronico monitoreoElectronico, DBHandler handler) {
        return new DelMonitoreoCommand(monitoreoElectronico, handler);
    }

    public static AddZonaDeSeguridadCommand createAddZonaDeSeguridadCommand(ZonaDeSeguridad zonaDeSeguridad, DBHandler handler) {
        return new AddZonaDeSeguridadCommand(zonaDeSeguridad, handler);
    }

    public static CreateZonaDeSeguridadCommand createCreateZonaDeSeguridadCommand(ZonaDeSeguridad zonaDeSeguridad) {
        return new CreateZonaDeSeguridadCommand(zonaDeSeguridad);
    }

    public static DeleteZonaDeSeguridadCommand createDeleteZonaDeSeguridadCommand(ZonaDeSeguridad zonaDeSeguridad) {
        return new DeleteZonaDeSeguridadCommand(zonaDeSeguridad);
    }

    public static DelZonaDeSeguridadCommand createDelZonaDeSeguridadCommand(ZonaDeSeguridad zonaDeSeguridad, DBHandler handler) {
        return new DelZonaDeSeguridadCommand(zonaDeSeguridad, handler);
    }

    public static GetAllZonasCommand createGetAllZonasCommand(){
        return new GetAllZonasCommand();
    }

    public static GetAllZonasWPCommand createGetAllZonasWPCommand(DBHandler handler) {
        return new GetAllZonasWPCommand( handler );
    }

    public static GetZonaCommand createGetZonaCommand(ZonaDeSeguridad zonaDeSeguridad) {
        return new GetZonaCommand(zonaDeSeguridad);
    }
    public static GetZonaByIdCommand createGetZonaByIdCommand(DBHandler handler, long zonaId) {
        return new GetZonaByIdCommand(handler, zonaId);
    }

    public static GetAllZonasByUsernameCommand createGetAllZonasByUsernameCommand(long idMonitoreo){
        return new GetAllZonasByUsernameCommand(idMonitoreo);
    }

    public static GetAllZonasBUserCommand createGetAllZonasBUserCommand( DBHandler handler, long idMonitoreo ) {
        return new GetAllZonasBUserCommand( handler, idMonitoreo );
    }


    public static CreateCoordenadaCommand createCreateCoordenadaCommand(Coordenada coordenada) {
        return new CreateCoordenadaCommand(coordenada);
    }

    public static AddCoordenadaCommand createAddCoordenadaCommand(Coordenada coordenada, DBHandler handler) {
        return new AddCoordenadaCommand(coordenada, handler);
    }

    public static GetCoordenadaByUsernameIdCommand createGetCoordenadaByUsernameIdCommand(Coordenada coordenada) {
        return new GetCoordenadaByUsernameIdCommand(coordenada);
    }

    public static GetCoordenadasZonaCommand createGetCoordenadasZonaCommand(long idZona) {
        return new GetCoordenadasZonaCommand( idZona );
    }

    public static GetCoordenadasZonasByIdCommand createGetCoordenadasZonasByIdCommand(DBHandler handler, long zonaId) {
        return new GetCoordenadasZonasByIdCommand(handler, zonaId);
    }

    public static GetCoordenadaByLatLonCommand createGetCoordenadaByLatLonCommand(Coordenada coordenada) {
        return new GetCoordenadaByLatLonCommand(coordenada);
    }
    public static UpdateCoordenadaCommand createUpdateCoordenadaCommand(Coordenada coordenada) {
        return new UpdateCoordenadaCommand(coordenada);
    }

    public static PutCoordenadaCommand createPutCoordenadaCommand(Coordenada coordenada, DBHandler handler) {
        return new PutCoordenadaCommand(coordenada, handler);
    }

    public static CreateHistoricoCommand createCreateHistoricoCommand(HistoricoMovimiento historico) {
        return new CreateHistoricoCommand(historico);
    }
    public static AddHistoricoCommand createAddHistoricoCommand(HistoricoMovimiento historico, DBHandler handler) {
        return new AddHistoricoCommand(historico, handler);
    }

    public static GetAllMovimientoByUsernameCommand createGetAllMovimientoByUsernameCommand(long idUsuario) {
        return new GetAllMovimientoByUsernameCommand(idUsuario);
    }

    public static GetAllMovimientoBUserCommand createGetAllMovimientoBUserCommand( DBHandler handler, long idUsuario ) {
        return new GetAllMovimientoBUserCommand( handler, idUsuario );
    }

    public static GetAllCoordenadasByUsernameCommand createGetAllCoordenadasByUsernameCommand(long idUsuario) {
        return new GetAllCoordenadasByUsernameCommand(idUsuario);
    }

    public static GetAllCoordenadasBUserCommand createGetAllCoordenadasBUserCommand( DBHandler handler, long idUsuario ) {
        return new GetAllCoordenadasBUserCommand( handler, idUsuario );
    }

    public static UpdateUserCommand createUpdateUserCommand(User user) {
        return new UpdateUserCommand(user);
    }

    public static PutUserCommand createPutUserCommand(User user, DBHandler handler) {
        return new PutUserCommand(user, handler);
    }



}
