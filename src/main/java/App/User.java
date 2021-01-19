package App;

import java.util.Objects;

public final class User {
    private final Long id;
    private final String login;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final Role role;
    private final boolean isActive;

    User(
            Long id,
            String login,
            String email,
            String password,
            String firstName,
            String lastName,
            String gender,
            Role role,
            boolean isActive
    ) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.role = role;
        this.isActive = isActive;
    }

    public Long id() {
        return id;
    }

    public String login() {
        return login;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String gender() {
        return gender;
    }

    public Role role() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.login, that.login) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.gender, that.gender) &&
                Objects.equals(this.role, that.role) &&
                this.isActive == that.isActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, password, firstName, lastName, gender, role, isActive);
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id + ", " +
                "login=" + login + ", " +
                "email=" + email + ", " +
                "password=" + password + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "gender=" + gender + ", " +
                "role=" + role + ", " +
                "isActive=" + isActive + ']';
    }


}
