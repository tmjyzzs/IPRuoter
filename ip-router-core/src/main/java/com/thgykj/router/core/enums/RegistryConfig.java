package com.thgykj.router.core.enums;

/**
 * Description RegistryConfig
 * DATA 2023-12-13
 *
 * @Author ttt
 */
public class RegistryConfig {

    public static final int BEAT_TIMEOUT = 30;
    public static final int DEAD_TIMEOUT = BEAT_TIMEOUT * 3;

    public enum RegistType{ EXECUTOR, ADMIN }
}
