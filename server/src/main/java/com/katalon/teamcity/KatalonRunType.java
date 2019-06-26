package com.katalon.teamcity;

import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.serverSide.RunType;
import jetbrains.buildServer.serverSide.RunTypeRegistry;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KatalonRunType extends RunType {

    @NotNull
    private final PluginDescriptor pluginDescriptor;

    @NotNull
    private final String editRunnerParamsPage = "editKatalonRunnerParams.jsp";

    @NotNull
    private final String viewRunnerParamsPage = "viewKatalonRunnerParams.jsp";

    @NotNull
    @Autowired
    KatalonRunTypePropertiesProcessor katalonRunTypePropertiesProcessor;

    private Map<String, String> defaultProperties = null;

    public KatalonRunType(final RunTypeRegistry runTypeRegistry,
                          @NotNull final PluginDescriptor pluginDescriptor) {
        this.pluginDescriptor = pluginDescriptor;

        runTypeRegistry.registerRunType(this);
    }


    @NotNull
    @Override
    public String getType() {
        return Constants.RUNNER_TYPE;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return Constants.RUNNER_DISPLAY_NAME;
    }

    @NotNull
    @Override
    public String getDescription() {
        return Constants.RUNNER_DISPLAY_NAME;
    }

    @Nullable
    @Override
    public PropertiesProcessor getRunnerPropertiesProcessor() {
        return katalonRunTypePropertiesProcessor;
    }

    @Nullable
    @Override
    public String getEditRunnerParamsJspFilePath() {
        return pluginDescriptor.getPluginResourcesPath(editRunnerParamsPage);
    }

    @Nullable
    @Override
    public String getViewRunnerParamsJspFilePath() {
        return pluginDescriptor.getPluginResourcesPath(viewRunnerParamsPage);
    }

    @Nullable
    @Override
    public Map<String, String> getDefaultRunnerProperties() {
        if (defaultProperties == null) {
            defaultProperties = new HashMap<>();
        }
        setupDefaultProperties(defaultProperties);
        return defaultProperties;
    }

    private void setupDefaultProperties(Map<String, String> params) {
    }
}
