package com.ninos.security;

import java.util.Set;

import com.ninos.domain.Authority;
import com.ninos.domain.User;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityUser extends User implements UserDetails
{
    private static final long serialVersionUID = -3188700847853328L;

//  public SecurityUser() {}

    public SecurityUser(User user)
    {
        this.setAuthorities(user.getAuthorities());
        this.setBudgets(user.getBudgets());
        this.setId(user.getId());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
    }

    @Override
    public Set<Authority> getAuthorities()
    {
        return super.getAuthorities();
    }

    @Override
    public String getPassword()
    {
        return super.getPassword();
    }

    @Override
    public String getUsername()
    {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

}
