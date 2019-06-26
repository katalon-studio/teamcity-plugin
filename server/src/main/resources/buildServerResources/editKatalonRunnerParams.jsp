<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean" />

<l:settingsGroup title="Katalon Studio Settings">
    <tr>
        <th><label>Katalon Studio version</label></th>
        <td>
            <props:textProperty name="katalon.ks.version" />
        </td>
    </tr>

    <tr>
        <th><label>Pre-installed Katalon Studio location</label></th>
        <td>
            <props:textProperty name="katalon.ks.location" />
        </td>
    </tr>

    <tr>
        <th><label>Command arguments</label></th>
        <td>
            <props:textProperty name="katalon.ks.args" />
        </td>
    </tr>

    <tr>
        <th><label>X11 DISPLAY (for Linux)</label></th>
        <td>
            <props:textProperty name="katalon.ks.x11" />
        </td>
    </tr>

    <tr>
        <th><label>Xvfb-run configuration (for Linux)</label></th>
        <td>
            <props:textProperty name="katalon.ks.xvfbrun" />
        </td>
    </tr>
</l:settingsGroup>
