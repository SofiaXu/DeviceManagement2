package site.aoba.devicemanagement2api.ui.dto.requestmodels;

import lombok.Data;

@Data
public class LoginRequest {
    private Long userId;
    private String password;
}
