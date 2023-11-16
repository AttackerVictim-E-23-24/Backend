package com.ucab.cmcapp.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;


public class Registry
{
    private static final Logger LOGGER = LoggerFactory.getLogger( Registry.class );

    private static final String PROPERTIES_FILE = "cmcappconfig.properties";

    public static final String DB_UNIT = "config.db.unit";
    public static final String DATE_FORMAT = "config.dateformat";

    public static final String JWT_ISSUER = "config.jwt.issuer";
    public static final String JWT_ALGORITHM = "config.jwt.algorithm";

    public static final String MAX_RESULTS_PER_PAGE = "config.consult.page_size";

    public static final String HTTP_CODE_200 = "config.httpCode200";
    public static final String URL_TRAINING_HUB = "config.UrlTrainingHub";

    //region Exception
    public static final String EXC_GENERIC_CODE = "exception.generic.code";
    public static final String EXC_GENERIC_MSG = "exception.generic.message";
    public static final String EXC_JWTVERIFY_CODE = "exception.jwtverify.code";
    public static final String EXC_JWTVERIFY_MSG = "exception.jwtverify.message";
    public static final String EXC_JWTCREATE_CODE = "exception.jwtcreate.code";
    public static final String EXC_JWTCREATE_MSG = "exception.jwtcreate.message";
    public static final String EXC_JWTSETKEY_CODE = "exception.jwtsetkey.code";
    public static final String EXC_JWTSETKEY_MSG = "exception.jwtsetkey.message";
    public static final String EXC_DBHANDLER_CODE = "exception.dbhandler.code";
    public static final String EXC_DBHANDLER_MSG = "exception.dbhandler.message";
    public static final String EXC_AUTH_CODE = "exception.auth.code";
    public static final String EXC_AUTH_MSG = "exception.auth.message";
    public static final String EXC_BADID_CODE = "exception.badid.code";
    public static final String EXC_BADID_MSG = "exception.badid.message";
    public static final String EXC_DELETEDB_CODE = "exception.deletedb.code";
    public static final String EXC_DELETEDB_MSG = "exception.deletedb.message";
    public static final String EXC_FINDALLDB_CODE = "exception.findalldb.code";
    public static final String EXC_FINDALLDB_MSG = "exception.findalldb.message";
    public static final String EXC_FINDDB_CODE = "exception.finddb.code";
    public static final String EXC_FINDDB_MSG = "exception.finddb.message";
    public static final String EXC_INSERTDB_CODE = "exception.insertdb.code";
    public static final String EXC_INSERTDB_MSG = "exception.insertdb.message";
    public static final String EXC_UPDATEDB_CODE = "exception.updatedb.code";
    public static final String EXC_UPDATEDB_MSG = "exception.updatedb.message";
    public static final String EXC_UTILITIES_CODE = "exception.utilities.code";
    public static final String EXC_UTILITIES_MSG = "exception.utilities.message";

    public static final String BASE_URL_BINNACLE = "config.base_url_binnacle";

    public static final String DEFAULT_LANGUAGE = "config.language.default";

    public static final String NOTE_LIKE_TITLE = "config.notification.like.title";
    public static final String NOTE_LIKE_MESSAGE = "config.notification.like.message";
    public static final String NOTE_COMMENT_TITLE = "config.notification.comment.title";
    public static final String NOTE_COMMENT_MESSAGE = "config.notification.comment.message";

    //Firebase
    public static final String URL_FIREBASE = "config.UrlFireBase";
    public static final String FIREBASE_KEY = "config.FireBaseKey";

    //Notification Scheduler
    public static final String NOTIFICATION_RUN_AT= "config.runAt";
    public static final String NOTIFICATION_RUN_AT_STARTUP = "config.runAtStartup";
    //endregion

    //Translate
    public static final String URL_TRANSLATE = "config.url_translate";
    public static final String TRANSLATE_KEY = "config.translate_key";
    //Translate

    private static Registry _instance;
    private Properties _properties;

    public String getProperty( String key )
    {
        return _properties.getProperty( key );
    }

    public static Registry getInstance()
    {
        if ( _instance == null )
            _instance = new Registry();

        return _instance;
    }

    private Registry()
    {
        //region Instrumentation DEBUG
        LOGGER.debug( "Entering Registry.CTOR" );
        //endregion

        try
        {
            _properties = new Properties();
            _properties.load( getClass().getClassLoader().getResourceAsStream( PROPERTIES_FILE ) );
        }
        catch ( IOException e )
        {
            throw new IllegalArgumentException( "Error reading properties from " + PROPERTIES_FILE, e );
        }

        //region Instrumentation DEBUG
        LOGGER.debug( "Exiting Registry.CTOR: Properties {}", _properties );
        //endregion
    }


}
