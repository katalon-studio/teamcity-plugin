package com.katalon.teamcity;

import com.katalon.utils.Logger;
import jetbrains.buildServer.agent.BuildProgressLogger;

public class KatalonLogger implements Logger {
    private final BuildProgressLogger logger;

    public KatalonLogger(BuildProgressLogger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String message) {
        logger.message(message);
    }
}
