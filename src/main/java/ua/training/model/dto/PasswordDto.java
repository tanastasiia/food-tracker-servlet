package ua.training.model.dto;

public class PasswordDto {
    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    public static class Builder {
        private PasswordDto passwordDto;

        public Builder() {
            passwordDto = new PasswordDto();
        }

        public PasswordDto.Builder setOldPassword(String oldPassword) {
            this.passwordDto.oldPassword = oldPassword;
            return this;
        }

        public PasswordDto.Builder setNewPassword(String newPassword) {
            this.passwordDto.newPassword = newPassword;
            return this;
        }

        public PasswordDto build() {
            return passwordDto;
        }
    }
}
