package com.backend.digitalhouse.coworking.util;

        import java.util.Arrays;
        import java.util.List;
        import java.util.EnumSet;
        import java.util.stream.Collectors;

public enum Role {

    CUSTOMER(Arrays.asList(Permission.READ_ALL_TIPOSSALAS, Permission.READ_ALL_SALAS, Permission.READ_ALL_SERVICIOS, Permission.SAVE_ONE_RESERVA, Permission.UPDATE_ONE_RESERVA, Permission.DELETE_ONE_RESERVA, Permission.SEARCH_ONE_RESERVA)),

    ADMINISTRATOR(EnumSet.allOf(Permission.class).stream().collect(Collectors.toList()));

    private List<Permission> permissions;

    private Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }


}
