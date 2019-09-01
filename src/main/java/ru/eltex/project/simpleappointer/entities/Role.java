package ru.eltex.project.simpleappointer.entities;

import org.springframework.security.core.GrantedAuthority;
/**
 * This class represents a enum role system for a
 * role system in Spring Security
 * @version 1.0
 * @author skwardlow
 * @see GrantedAuthority
 * */
public enum Role implements GrantedAuthority {
    USER,
    SPECIALIST,
    ADMIN;
    /**
     * Automatically generated method for authorities and role system in Spring Security
     * @see org.springframework.security.authentication.jaas.AuthorityGranter
     * */
    @Override
    public String getAuthority() {
        return name();
    }
}
