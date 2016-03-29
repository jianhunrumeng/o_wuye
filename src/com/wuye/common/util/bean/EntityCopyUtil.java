package com.wuye.common.util.bean;

import com.wuye.entity.*;
import net.sf.json.JSONObject;

public class EntityCopyUtil {
    public static void populate(PropertyCompany dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"companyName", "simpleName", "bankAcctName",
                    "bankName", "bankAcctNbr", "statusCd"});
        }
    }

    public static void populate(Building dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"buildingName", "buildingType", "ownerBuilding",
                    "phone", "floorCount", "stairArea", "userableArea", "afforestArea", "ownershipType",
                    "buildingStructure", "upgradeCondition", "finishTime", "statusCd"});
        }
    }

    public static void populate(JSONObject dest, Building src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property == null || property.length == 0) {
            property = new String[]{"buildingId", "buildingName", "buildingType",
                    "phone", "floorCount", "stairArea", "userableArea", "afforestArea", "ownershipType",
                    "buildingStructure", "upgradeCondition", "finishTime", "statusCd"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(JSONObject dest, AcctItemRel src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property == null || property.length == 0) {
            property = new String[]{"price", "floor", "caculateMethod", "objName"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(JSONObject dest, AcctItemType src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property == null || property.length == 0) {
            property = new String[]{"acctTypeName", "acctItemTypeId", "acctType", "parentAcctTypeId"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(Community dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"communityName", "simpleName",
                    "communityArea", "buildingArea", "afforestArea", "bankAcctName", "bankName",
                    "bankAcctNbr", "statusCd"});
        }
    }

    public static void populate(PartyInfo dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"partyName", "classId", "objId", "certNbr",
                    "certType", "mobile", "homePhone", "officePhone", "fax", "email", "qq", "weiXin", "zipCode"});
        }
    }

    public static void populate(JSONObject dest, PartyInfo src, String[] property) {
        if (dest == null || src == null) {
            return;
        }

        if (property == null || property.length == 0) {
            property = new String[]{"partyInfoId", "partyName", "classId", "objId", "certNbr",
                    "certType", "certTypeName", "mobile", "homePhone", "officePhone", "fax", "email", "qq", "weiXin", "zipCode"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(Address dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"detailAddress"});
        }
    }

    public static void populate(JSONObject dest, Address src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property == null || property.length == 0) {
            property = new String[]{"addressId", "detailAddress"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(JSONObject dest, Area src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property == null || property.length == 0) {
            property = new String[]{"areaId", "areaName", "areaType"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(JSONObject dest, AttrSpec src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property == null || property.length == 0) {
            property = new String[]{"attrId", "attrName", "attrCode"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(JSONObject dest, AttrValue src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property == null || property.length == 0) {
            property = new String[]{"attrValueId", "attrValueName", "attrValue", "communityId"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(JSONObject dest, Community src, String[] property) {
        if (dest == null || src == null) {
            return;
        }

        if (property == null || property.length == 0) {
            property = new String[]{"communityId", "communityName", "simpleName",
                    "communityArea", "buildingArea", "afforestArea", "bankAcctName", "bankName",
                    "bankAcctNbr", "statusCd", "statusName", "bankName2"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(JSONObject dest, PropertyCompany src, String[] property) {
        if (dest == null || src == null) {
            return;
        }

        if (property == null || property.length == 0) {
            property = new String[]{"companyId", "companyName", "simpleName", "bankAcctName",
                    "bankName", "bankAcctNbr", "statusCd", "statusName", "bankName2"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(JSONObject dest, Room src, String[] property) {
        if (dest == null || src == null) {
            return;
        }

        if (property == null || property.length == 0) {
            property = new String[]{"roomId", "roomNbr", "floor", "buildingStructure", "housingOrientation",
                    "usingState", "upgradeCondition", "housingFeeRate",
                    "upgradeStartDate", "upgradeEndDate", "housedDate", "statusCd", "statusName"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(Room dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"roomNbr", "floor", "buildingStructure", "housingOrientation",
                    "usingState", "upgradeCondition", "housingFeeRate",
                    "upgradeStartDate", "upgradeEndDate", "housedDate"});
        }
    }

    public static void populate(AcctItemRel dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"price", "floor", "caculateMethod"});
        }
    }

    public static void populate(JSONObject dest, BuildingType src, String[] property) {
        if (dest == null || src == null) {
            return;
        }

        if (property == null || property.length == 0) {
            property = new String[]{"buildingTypeName", "communityId", "buildingArea", "comprisingArea",
                    "publicArea", "costArea"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(BuildingType dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"buildingTypeName", "communityId", "buildingArea", "comprisingArea",
                    "publicArea", "costArea"});
        }
    }

    public static void populate(JSONObject dest, User src, String[] property) {
        if (dest == null || src == null) {
            return;
        }

        if (property == null || property.length == 0) {
            property = new String[]{"userId", "account", "userType", "statusName"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(JSONObject dest, ParkingType src, String[] property) {
        if (dest == null || src == null) {
            return;
        }

        if (property == null || property.length == 0) {
            property = new String[]{"parkingTypeId", "parkingTypeName", "price", "remark"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(User dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"account", "pwd", "userType"});
        }
    }

    private static void populate(JSONObject dest, Object src, String property) {
        Object value = BeanUtilsExtend.getPropertyValue(src, property);
        dest.put(property, value);
    }

    public static JSONObject getJSONObject(Object src, String[] property) {
        if (src == null) {
            return new JSONObject();
        }
        JSONObject jsObj = new JSONObject();
        if (property == null || property.length == 0) {
            if (src instanceof Community) {
                populate(jsObj, (Community) src, property);
            } else if (src instanceof PropertyCompany) {
                populate(jsObj, (PropertyCompany) src, property);
            } else if (src instanceof PartyInfo) {
                populate(jsObj, (PartyInfo) src, property);
            } else if (src instanceof Address) {
                populate(jsObj, (Address) src, property);
            } else if (src instanceof Area) {
                populate(jsObj, (Area) src, property);
            } else if (src instanceof AttrSpec) {
                populate(jsObj, (AttrSpec) src, property);
            } else if (src instanceof AttrValue) {
                populate(jsObj, (AttrValue) src, property);
            } else if (src instanceof Building) {
                populate(jsObj, (Building) src, property);
            } else if (src instanceof Room) {
                populate(jsObj, (Room) src, property);
            } else if (src instanceof BuildingType) {
                populate(jsObj, (BuildingType) src, property);
            } else if (src instanceof User) {
                populate(jsObj, (User) src, property);
            } else if (src instanceof ParkingType) {
                populate(jsObj, (ParkingType) src, property);
            }
        } else {
            for (String prop : property) {
                jsObj.put(prop, BeanUtilsExtend.getPropertyValue(src, prop));
            }
        }

        return jsObj;
    }

    public static void populate(ParkingType dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"parkingTypeName", "parkingType", "price", "remark"});
        }
    }

    public static void populate(Parking dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"parkingNbr", "parkingPosition"});
        }
    }

    public static void populate(Privilege dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"privilegeName", "statusCd", "path", "parentPrivilegeId", "privilegeType"});
        }
    }

    public static void populate(Role dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"roleName", "statusCd", "createDate", "statusDate", "updateDate"});
        }
    }

    public static void populate(Notice dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"noticeType", "title", "content", "statusCd", "createDate", "statusDate", "updateDate"});
        }
    }

    public static void populate(JSONObject dest, MeterSpec src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property == null || property.length == 0) {
            property = new String[]{"meterSpecId", "meterType", "parentMeterId", "meterName", "unit", "price"};
        }
        for (String prop : property) {
            populate(dest, src, prop);
        }
    }

    public static void populate(MeterSpec dest, JSONObject src, String[] property) {
        if (dest == null || src == null) {
            return;
        }
        if (property != null && property.length > 0) {
            BeanUtilsExtend.populate(dest, src, property);
        } else {
            BeanUtilsExtend.populate(dest, src, new String[]{"meterSpecId", "meterType", "parentMeterId", "meterName", "unit", "price"});
        }
    }
}
