#-------------------------------------------------------------------------------
# Copyright (c) 2013 ComTrek.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the GNU Public License v3.0
# which accompanies this distribution, and is available at
# http://www.gnu.org/licenses/gpl.html
# 
# Contributors:
#     ComTrek - initial API and implementation
#-------------------------------------------------------------------------------
<%@ page import="comtrek.Trek" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'trek.label', default: 'Trek')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-trek" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-trek" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list trek">
			
				<g:if test="${trekInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="trek.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${trekInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${trekInstance?.distance}">
				<li class="fieldcontain">
					<span id="distance-label" class="property-label"><g:message code="trek.distance.label" default="Distance" /></span>
					
						<span class="property-value" aria-labelledby="distance-label"><g:fieldValue bean="${trekInstance}" field="distance"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${trekInstance?.level}">
				<li class="fieldcontain">
					<span id="level-label" class="property-label"><g:message code="trek.level.label" default="Level" /></span>
					
						<span class="property-value" aria-labelledby="level-label"><g:fieldValue bean="${trekInstance}" field="level"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${trekInstance?.max_altitude}">
				<li class="fieldcontain">
					<span id="max_altitude-label" class="property-label"><g:message code="trek.max_altitude.label" default="Maxaltitude" /></span>
					
						<span class="property-value" aria-labelledby="max_altitude-label"><g:fieldValue bean="${trekInstance}" field="max_altitude"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${trekInstance?.average_note}">
				<li class="fieldcontain">
					<span id="average_note-label" class="property-label"><g:message code="trek.average_note.label" default="Averagenote" /></span>
					
						<span class="property-value" aria-labelledby="average_note-label"><g:fieldValue bean="${trekInstance}" field="average_note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${trekInstance?.attitude_combined}">
				<li class="fieldcontain">
					<span id="attitude_combined-label" class="property-label"><g:message code="trek.attitude_combined.label" default="Attitudecombined" /></span>
					
						<span class="property-value" aria-labelledby="attitude_combined-label"><g:fieldValue bean="${trekInstance}" field="attitude_combined"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${trekInstance?.average_time}">
				<li class="fieldcontain">
					<span id="average_time-label" class="property-label"><g:message code="trek.average_time.label" default="Averagetime" /></span>
					
						<span class="property-value" aria-labelledby="average_time-label"><g:fieldValue bean="${trekInstance}" field="average_time"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${trekInstance?.comments}">
				<li class="fieldcontain">
					<span id="comments-label" class="property-label"><g:message code="trek.comments.label" default="Comments" /></span>
					
						<g:each in="${trekInstance.comments}" var="c">
						<span class="property-value" aria-labelledby="comments-label"><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${trekInstance?.events}">
				<li class="fieldcontain">
					<span id="events-label" class="property-label"><g:message code="trek.events.label" default="Events" /></span>
					
						<g:each in="${trekInstance.events}" var="e">
						<span class="property-value" aria-labelledby="events-label"><g:link controller="event" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${trekInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="trek.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${trekInstance?.user?.id}">${trekInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${trekInstance?.weather_link}">
				<li class="fieldcontain">
					<span id="weather_link-label" class="property-label"><g:message code="trek.weather_link.label" default="Weatherlink" /></span>
					
						<span class="property-value" aria-labelledby="weather_link-label"><g:fieldValue bean="${trekInstance}" field="weather_link"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${trekInstance?.id}" />
					<g:link class="edit" action="edit" id="${trekInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
