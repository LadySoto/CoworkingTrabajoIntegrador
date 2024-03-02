package com.backend.digitalhouse.coworking.util;

import java.util.Arrays;
import java.util.List;

public enum Rol {

    CUSTOMER(Arrays.asList(Permission.READ_ALL_TIPOSSALA)),

    ADMINISTRATOR(Arrays.asList(Permission.SAVE_ONE_TIPOSALA, Permission.READ_ALL_TIPOSSALA));

    private List<Permission> permissions;

    Rol(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}

