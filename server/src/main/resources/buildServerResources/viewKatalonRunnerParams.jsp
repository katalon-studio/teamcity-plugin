<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>

<div>Katalon Studio version: <strong>${propertiesBean.properties['katalon.ks.version']}</strong></div>

<div>Pre-installed Katalon Studio location: <strong>${propertiesBean.properties['katalon.ks.location']}</strong></div>

<div>Command arguments: <strong>${propertiesBean.properties['katalon.ks.args']}</strong></div>

<div>X11 DISPLAY: <strong>${propertiesBean.properties['katalon.ks.x11']}</strong></div>

<div>Xvfb-run configuration: <strong>${propertiesBean.properties['katalon.ks.xvfbrun']}</strong></div>
