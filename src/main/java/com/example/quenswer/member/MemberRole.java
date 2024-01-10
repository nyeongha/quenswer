package com.example.quenswer.member;

import lombok.Getter;

@Getter
public enum MemberRole {
    Admin("admin"),Member("member");

    private String value;
    MemberRole(String value){
        this.value=value;
    }
}
