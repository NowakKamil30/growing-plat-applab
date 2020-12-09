package App.models;

import App.Role;

public record User(
        Long id,
        String login,
        String email,
        String password,
        String firstName,
        String lastName,
        String gender,
        Role role,
        boolean isActive
){


}
