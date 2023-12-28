package com.ucab.cmcapp.persistence;

import com.ucab.cmcapp.persistence.dao.*;

public class DaoFactory
{
    private DaoFactory() {}

    public static UserDao createUserDao( DBHandler handler )
    {
        return new UserDao( handler );
    }

    public static PersonaDao createPersonaDao( DBHandler handler )
    {
        return new PersonaDao( handler );
    }

    public static MailCodeDao createMailCodeDao( DBHandler handler ) {
        return new MailCodeDao( handler );
    }

    public static MonitoreoElectronicoDao createMonitoreoElectronicoDao( DBHandler handler ) {
        return new MonitoreoElectronicoDao( handler );
    }

    public static ZonaDeSeguridadDao createZonaDeSeguridadDao( DBHandler handler ) {
        return new ZonaDeSeguridadDao( handler );
    }

    public static CoordenadaDao createCoordenadaDao( DBHandler handler ) {
        return new CoordenadaDao( handler );
    }
}
