package pl.chmielewski.LeavePlanner.Authentication.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.chmielewski.LeavePlanner.Authentication.user.Permission.*;

public enum Role {
    USER(Set.of(
            USER_READ,
            USER_CREATE,
            USER_UPDATE,
            USER_DELETE
    )),

    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            ADMIN_CREATE,
            MANAGER_READ,
            MANAGER_UPDATE,
            MANAGER_DELETE,
            MANAGER_CREATE
    )),

    MANAGER(Set.of(
            MANAGER_READ,
            MANAGER_UPDATE,
            MANAGER_DELETE,
            MANAGER_CREATE
    ));

    private final Set<pl.chmielewski.LeavePlanner.Authentication.user.Permission> permissions;

    Role(Set<pl.chmielewski.LeavePlanner.Authentication.user.Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<pl.chmielewski.LeavePlanner.Authentication.user.Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
