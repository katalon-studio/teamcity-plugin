package com.katalon.teamcity;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.concurrent.*;

public class KatalonBuildProcess implements BuildProcess {
    private final AgentRunningBuild agentRunningBuild;
    private final BuildRunnerContext context;

    private final BuildProgressLogger logger;
    private final ExecutorService executor;
    private final KatalonProcess katalonProcess;

    private Future<BuildFinishedStatus> processFuture;

    public KatalonBuildProcess(AgentRunningBuild agentRunningBuild, BuildRunnerContext buildRunnerContext) {
        this.agentRunningBuild = agentRunningBuild;
        this.context = buildRunnerContext;

        this.logger = agentRunningBuild.getBuildLogger();
        this.executor = Executors.newSingleThreadExecutor();
        this.katalonProcess = new KatalonProcess(this.agentRunningBuild, context);
    }

    @Override
    public void start() throws RunBuildException {
        logger.message(MessageFormat.format("Start Katalon build #{0} for {1}",
                agentRunningBuild.getBuildNumber(),
                agentRunningBuild.getProjectName()));

        processFuture = executor.submit(katalonProcess);
    }

    @Override
    public void interrupt() {
        logger.message("Interrupt Katalon build.");
        if (processFuture != null) {
            processFuture.cancel(true);
        }
    }

    @Override
    public boolean isFinished() {
        return processFuture.isDone();
    }

    @Override
    public boolean isInterrupted() {
        return processFuture.isCancelled() && isFinished();
    }

    @NotNull
    @Override
    public BuildFinishedStatus waitFor() {
        try {
            return processFuture.get();
        } catch (InterruptedException | CancellationException e) {
            logger.warning("Katalon build process has been interrupted: " + e.toString());
            return BuildFinishedStatus.INTERRUPTED;
        } catch (ExecutionException e) {
            logger.error("Exception caught during Katalon build execution: " + e.toString());
            return BuildFinishedStatus.FINISHED_FAILED;
        } finally {
            executor.shutdown();
        }
    }
}
