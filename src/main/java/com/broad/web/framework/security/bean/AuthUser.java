package com.broad.web.framework.security.bean;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import com.broad.web.framework.enums.CommonStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class AuthUser implements UserDetails {

    private Long userId;

    private String nickName;

    private String accountName;

    @JsonIgnore
    private String password;

    private Set<UserRole> roles;

    private Long tenantId;

    private CommonStatus status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>(10);
        if (CollectionUtil.isEmpty(authorities)) {
            return authorities;
        }
        for (UserRole role : roles) {
            String roleName = role.getRoleName();
            if (!roleName.startsWith("ROLE_")) {
                roleName = "ROLE_" + roleName;
            }
            GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.accountName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.status == CommonStatus.ENABLE;
    }
}
