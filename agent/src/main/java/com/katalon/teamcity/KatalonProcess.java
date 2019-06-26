package com.katalon.teamcity;

import com.katalon.utils.KatalonUtils;
import jetbrains.buildServer.agent.AgentRunningBuild;
import jetbrains.buildServer.agent.BuildFinishedStatus;
import jetbrains.buildServer.agent.BuildProgressLogger;
import jetbrains.buildServer.agent.BuildRunnerContext;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

public class KatalonProcess implements Callable<BuildFinishedStatus> {
    private final AgentRunningBuild agentRunningBuild;
    private final BuildRunnerContext context;

    private final BuildProgressLogger logger;

    public KatalonProcess(AgentRunningBuild agentRunningBuild, BuildRunnerContext buildRunnerContext) {
        this.agentRunningBuild = agentRunningBuild;
        this.context = buildRunnerContext;

        this.logger = agentRunningBuild.getBuildLogger();
    }

    @Override
    public BuildFinishedStatus call() {
        try {
            boolean result = build();

            if (!result) {
                return BuildFinishedStatus.FINISHED_FAILED;
            }

            return BuildFinishedStatus.FINISHED_SUCCESS;
        } catch (InterruptedException e) {
            logger.warning("Katalon Studio execution has been interrupted.\n" + e.getMessage());
            return BuildFinishedStatus.INTERRUPTED;
        } catch (Exception e) {
            logger.error("Exception caught during Katalon Studio execution.\n" + e.getMessage());
            return BuildFinishedStatus.FINISHED_FAILED;
        }
    }

    private boolean build() throws IOException, InterruptedException {
        Map<String, String> parameters = context.getRunnerParameters();
        String ksVersion = parameters.get(Constants.SETTINGS_KS_VERSION);
        String ksLocation = parameters.get(Constants.SETTINGS_KS_LOCATION);
        String commandArgs = parameters.get(Constants.SETTINGS_ARGS);
        String x11Display = parameters.get(Constants.SETTINGS_X11_DISPLAY);
        String xvfbConfiguration = parameters.get(Constants.SETTINGS_XVFBRUN);

        KatalonLogger katalonLogger = new KatalonLogger(logger);
        File projectPath = agentRunningBuild.getCheckoutDirectory();

        Map<String, String> env = context.getBuildParameters().getEnvironmentVariables();
        return KatalonUtils.executeKatalon(
                katalonLogger,
                ksVersion,
                ksLocation,
                projectPath.getPath(),
                commandArgs,
                x11Display,
                xvfbConfiguration,
                env
        );
    }
}
