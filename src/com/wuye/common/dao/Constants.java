package com.wuye.common.dao;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.dao.Constants.java
 * @所含类：Constants
 * @author: wuq
 * @version: V1.0
 * @see:
 * @创建日期：2007-9-13
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:2007-9-13 wuq 创建
 * <p/>
 * =============================================================<br>
 */
public class Constants {
    //~ Static fields/initializers =============================================

    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * The encryption algorithm key to be used for passwords
     */
    public static final String ENC_ALGORITHM = "algorithm";

    /**
     * A flag to indicate if passwords should be encrypted
     */
    public static final String ENCRYPT_PASSWORD = "encryptPassword";

    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "admin";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "user";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     */
    public static final String CSS_THEME = "csstheme";
//OrderChange-START
    /**
     * The request scope attribute that holds the orderChange form.
     */
    public static final String ORDERCHANGE_KEY = "orderChangeForm";

    /**
     * The request scope attribute that holds the orderChange list
     */
    public static final String ORDERCHANGE_LIST = "orderChangeList";
//OrderChange-END

//SaleTask-START
    /**
     * The request scope attribute that holds the saleTask form.
     */
    public static final String SALETASK_KEY = "saleTaskForm";

    /**
     * The request scope attribute that holds the saleTask list
     */
    public static final String SALETASK_LIST = "saleTaskList";

    public static final int DEFAULT_PAGE_PER_COUNT = 10;
//SaleTask-END

}


