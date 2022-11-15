package com.example.test.login.domain;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {

    private int idx;
    private String uid;
    private String pw;
    private String uuid;


}
