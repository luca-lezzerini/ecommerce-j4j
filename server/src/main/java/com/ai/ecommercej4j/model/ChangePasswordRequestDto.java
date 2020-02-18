package com.ai.ecommercej4j.model;

public class ChangePasswordRequestDto extends LoginResponseDto {
    private String doiCode;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordRequestDto(String doiCode, String oldPassword, String newPassword) {
        this.doiCode = doiCode;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ChangePasswordRequestDto() {
    }

    public String getDoiCode() {
        return doiCode;
    }

    public void setDoiCode(String doiCode) {
        this.doiCode = doiCode;
    }

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
    
    
}