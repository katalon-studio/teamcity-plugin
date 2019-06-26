package com.katalon.teamcity;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import org.jetbrains.annotations.NotNull;

public class KatalonAgent implements AgentBuildRunner {
    private AgentBuildRunnerInfo runnerInfo;
    private BuildProcess buildProcess;

    @NotNull
    @Override
    public BuildProcess createBuildProcess(@NotNull AgentRunningBuild runningBuild, @NotNull BuildRunnerContext context) throws RunBuildException {
        buildProcess = new KatalonBuildProcess(runningBuild, context);
        return buildProcess;
    }

    @NotNull
    @Override
    public AgentBuildRunnerInfo getRunnerInfo() {
        runnerInfo = new AgentBuildRunnerInfo() {

            @NotNull
            @Override
            public String getType() {
                return Constants.RUNNER_TYPE;
            }

            @Override
            public boolean canRun(@NotNull BuildAgentConfiguration agentConfiguration) {
                return true;
            }
        };

        return runnerInfo;
    }
}
