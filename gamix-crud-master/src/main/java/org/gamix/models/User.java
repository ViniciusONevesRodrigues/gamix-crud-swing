package org.gamix.models;

public class User {

    private Integer id;
    private String username;
    private String email;
    private String icon;
    private PasswordUser passwordUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public PasswordUser getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(PasswordUser passwordUser) {
        this.passwordUser = passwordUser;
    }

    public void partialUserInput(User partialUserInput) {
        if (partialUserInput != null) {
            if (partialUserInput.getUsername() != null) {
                this.username = partialUserInput.getUsername();
            }
            if (partialUserInput.getEmail() != null) {
                this.email = partialUserInput.getEmail();
            }
            if (partialUserInput.getIcon() != null) {
                this.icon = partialUserInput.getIcon();
            }
        }
    }
}
