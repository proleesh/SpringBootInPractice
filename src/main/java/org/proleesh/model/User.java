package org.proleesh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.proleesh.validation.Password;
@Getter
@AllArgsConstructor
@ToString
public class User {
    private String userName;
    @Password
    private String password;


}
