package com.dal.cs.backend.member.Enum;

public enum MemberType {
    admin,
    president,
    member;

    public static MemberType fromString( String value){
        for (MemberType memberType: MemberType.values()) {
            if (memberType.name().equals(value)){
                return memberType;
            }
        }
        return null;
    }
}
