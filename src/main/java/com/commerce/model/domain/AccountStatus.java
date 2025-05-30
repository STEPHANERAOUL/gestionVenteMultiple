package com.commerce.model.domain;

public enum AccountStatus {
    PENDING_VERIFICATION,//Account is created but not yet verified
    ACTIVE,//Account is active and in good standing
    SUSPENDED, //Account is temporarily suspended,possibly due to violations no usages
    DEACTIVATED, // Account is deactivated , user may have chosen to deactivate it no usages
    BENNED,  // Account is permanently banned due to severe violations no usages
    CLOSED //Account is permanently closed, possibly at user request no usagess
}
