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
<%@ page import="comtrek.Event" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-event" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-event" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list event">
			
				<g:if test="${eventInstance?.effectiveTime}">
				<li class="fieldcontain">
					<span id="effectiveTime-label" class="property-label"><g:message code="event.effectiveTime.label" default="Effective Time" /></span>
					
						<span class="property-value" aria-labelledby="effectiveTime-label"><g:formatDate date="${eventInstance?.effectiveTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventInstance?.averageNote}">
				<li class="fieldcontain">
					<span id="averageNote-label" class="property-label"><g:message code="event.averageNote.label" default="Average Note" /></span>
					
						<span class="property-value" aria-labelledby="averageNote-label"><g:fieldValue bean="${eventInstance}" field="averageNote"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventInstance?.comments}">
				<li class="fieldcontain">
					<span id="comments-label" class="property-label"><g:message code="event.comments.label" default="Comments" /></span>
					
						<g:each in="${eventInstance.comments}" var="c">
						<span class="property-value" aria-labelledby="comments-label"><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${eventInstance?.participants}">
				<li class="fieldcontain">
					<span id="participants-label" class="property-label"><g:message code="event.participants.label" default="Participants" /></span>
					
						<g:each in="${eventInstance.participants}" var="p">
						<span class="property-value" aria-labelledby="participants-label"><g:link controller="participant" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${eventInstance?.trek}">
				<li class="fieldcontain">
					<span id="trek-label" class="property-label"><g:message code="event.trek.label" default="Trek" /></span>
					
						<span class="property-value" aria-labelledby="trek-label"><g:link controller="trek" action="show" id="${eventInstance?.trek?.id}">${eventInstance?.trek?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="event.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${eventInstance?.user?.id}">${eventInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${eventInstance?.id}" />
					<g:link class="edit" action="edit" id="${eventInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
