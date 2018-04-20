package sample.com.alifanov.m.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.com.alifanov.m.service.AuthenticationService;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractUser {
    private String nickName;
    private String email;
    private AuthenticationService authenticationService;
}
