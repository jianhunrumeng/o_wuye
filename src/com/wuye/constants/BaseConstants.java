package com.wuye.constants;

public class BaseConstants {

    /**
     * RET_TRUE String.
     */
    public static final String RET_TRUE = "TRUE";

    /**
     * RET_FALSE String.
     */
    public static final String RET_FALSE = "FALSE";
    /**
     * RET_SUCCESS String.
     */
    public static final String RET_SUCCESS = "SUCCESS";

    /**
     * RET_INVALID String.
     */
    public static final String RET_INVALID = "INVALID";

    /**
     * RET_WAIT String.
     */
    public static final String RET_WAIT = "WAIT";

    public static String STATUS_VALID = "1000";//有效
    public static String STATUS_INVALID = "1100";//失效
    public static String STATUS_NOT_EFF = "1299";//未生效

    public static String CLASS_USER = "10";//用户类型
    public static String CLASS_COMPANY = "11";//物业公司类型
    public static String CLASS_COMMUNITY = "12";//小区类型
    public static String CLASS_ORG = "13";//组织类型
    public static String CLASS_BUILDING = "14";//楼栋类型
    public static String CLASS_ROOM = "15";//房间类型
    public static String CLASS_ACCT_ITEM_TYPE = "16";//费用类型
    public static String CLASS_FLOOR = "17";//楼层本来作为一个属性存在楼栋上面的，单独拿出来做一个class，方便开发


    public static String ORG_TYPE_COMPANY = "10";//组织类型-物业类型
    public static String ORG_TYPE_COMMUNITY = "11";//组织-小区类型
    public static String ORG_TYPE_OTHER = "12";//组织-其他类型

    //房间关联联系人信息 10代表业主，11代表其他（租户或者其他）
    public static String ROOM_PARTY_REL_TYPE_10 = "10";
    //租户
    public static String ROOM_PARTY_REL_TYPE_11 = "11";
    /**
     * 默认每页展示行数 add by tan
     */
    public static final int DEFAULT_PAGE_PER_COUNT = 10;
    /**
     * 搜索下拉列表默认展示条数
     */
    public static final int DEFAULT_SHOW_ITEM = 5;

    /**
     * 动作：新增
     */
    public static final String ACTION_ADD = "ADD";
    /**
     * 动作：删除
     */
    public static final String ACTION_DEL = "DEL";

    /**
     * 动作：修改
     */
    public static final String ACTION_UPDATE = "UPDATE";

    /**
     * 动作：保持
     */
    public static final String ACTION_KEEP = "KEEP";
    /**
     * 查询返回的最大记录数
     */
    public static final Integer QUERY_ROW_MAX = 100;

    /**
     * 对象属性拷贝注解
     */
    public static final String ANNO_COPY = "copy";

    public static final String USER_TYPE_10 = "10";            //系统用户

    public static final String USER_TYPE_11 = "11";            //物业用户

    public static final String USER_TYPE_12 = "12";            //业主用户

    /**
     * 区域类型：省
     */
    public static final String AREA_TYPE_PROVINCE = "10";
    /**
     * 区域类型：市
     */
    public static final String AREA_TYPE_CITY = "11";
    /**
     * 区域类型：区
     */
    public static final String AREA_TYPE_REGION = "12";

    /**
     * 房间人员关联类型，业主
     * .
     */
    public static final String ROOM_PARTY_REL_TYPE_YZ = "10";

    /**
     * 房间人员关联类型，其它
     * .
     */
    public static final String ROOM_PARTY_REL_TYPE_O = "11";

}
