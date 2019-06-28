<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean" />

<l:settingsGroup title="Katalon Studio Settings">
    <tr>
        <th><label>Katalon Studio version:</label></th>
        <td>
            <props:textProperty name="katalon.ks.version" className="longField" />
            <span class="smallNote">Automatically download Katalon Studio with the specified version.</span>
        </td>
    </tr>

    <tr>
        <th><label>Pre-installed Katalon Studio location:</label></th>
        <td>
            <props:textProperty name="katalon.ks.location" className="longField" />
            <span class="smallNote">Use this field when Katalon Studio cannot be downloaded automatically (often due to network conditions).</span>
            <span class="smallNote">E.g. /var/lib/jenkins/Katalon_Studio_Linux_64-5.10.1.</span>
        </td>
    </tr>

    <tr>
        <th><label>Command arguments:</label></th>
        <td>
            <props:textProperty name="katalon.ks.args" className="longField" />
            <span class="smallNote">E.g. -browserType="Chrome" -retry=0 -statusDelay=15 -testSuitePath="Test Suites/Regression Tests/All tests".</span>
            <span class="smallNote">Please leave out -runMode. If not specified, -projectPath will be set to the current workspace directory.
                See <a href="https://docs.katalon.com/katalon-studio/docs/console-mode-execution.html" target="_blank">here</a> for more info</span>
        </td>
    </tr>

    <tr>
        <th><label>X11 DISPLAY (for Linux):</label></th>
        <td>
            <props:textProperty name="katalon.ks.x11" className="longField" />
            <span class="smallNote">Optional. This value will be used as the DISPLAY environment variable.</span>
            <span class="smallNote">TeamCity's agents must be allowed to connect to the display, see xhost if you encounter access control issues.</span>
        </td>
    </tr>

    <tr>
        <th><label>Xvfb-run configuration (for Linux):</label></th>
        <td>
            <props:textProperty name="katalon.ks.xvfbrun" className="longField" />
            <span class="smallNote">Optional. If specified, xvfb-run will be used. Please make sure Xvfb has been installed.</span>
        </td>
    </tr>
</l:settingsGroup>
