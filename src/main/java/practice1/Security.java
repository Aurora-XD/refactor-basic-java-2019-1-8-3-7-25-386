package practice1;

import com.google.common.collect.ImmutableList;

public class Security {

    private SecurityChecker securityChecker;

    public Security(SecurityChecker checker) {
        this.securityChecker = checker;
    }

    public boolean hasAccess(User user, Permission permission, ImmutableList<Permission> permissions) {

        boolean isAccess = false;

        if (this.securityChecker.checkPermission(user, permission) || permissions.contains(permission) || securityChecker.isAdmin()) {
            isAccess = true;
        }

        return isAccess;
    }
}
