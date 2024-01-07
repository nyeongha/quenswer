package com.example.quenswer.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {

    @Size(min=5,max=20)
    @NotEmpty(message ="id는 필수항목입니다." )
    private String userId;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;
}
