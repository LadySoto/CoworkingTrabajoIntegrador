package com.backend.digitalhouse.coworking.util;

        import java.util.Arrays;
        import java.util.List;

public enum Role {

    CUSTOMER(Arrays.asList(Permission.READ_ALL_TIPOSSALAS, Permission.READ_ALL_SALAS)),

    ADMINISTRATOR(Arrays.asList(Permission.SAVE_ONE_TIPOSALA, Permission.READ_ALL_TIPOSSALAS, Permission.SAVE_ONE_SALA, Permission.READ_ALL_SALAS));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
