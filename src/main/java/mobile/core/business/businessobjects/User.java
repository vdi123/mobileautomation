package mobile.core.business.businessobjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private String username;
    private String password;


    public static User createDefaultUser() {
        return User.builder()
                .username("test@google.com")
                .password("testPassword")
                .build();
    }
}
