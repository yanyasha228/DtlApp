package dtl.DtlApp.Models.ModelEnums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, LOGISTICIAN , MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
