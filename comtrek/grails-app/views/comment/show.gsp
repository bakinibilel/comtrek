<!--
Copyright (c) 2013 ComTrek.
All rights reserved. This program and the accompanying materials
are made available under the terms of the GNU Public License v3.0
which accompanies this distribution, and is available at
http://www.gnu.org/licenses/gpl.html

Contributors:
    ComTrek - initial API and implementation
-->
<%@ page import="comtrek.Comment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comment.label', default: 'Comment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-comment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-comment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list comment">
			
				<g:if test="${commentInstance?.comment}">
				<li class="fieldcontain">
					<span id="comment-label" class="property-label"><g:message code="comment.comment.label" default="Comment" /></span>
					
						<span class="property-value" aria-labelledby="comment-label"><g:fieldValue bean="${commentInstance}" field="comment"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${commentInstance?.writing_date}">
				<li class="fieldcontain">
					<span id="writing_date-label" class="property-label"><g:message code="comment.writing_date.label" default="Writingdate" /></span>
					
						<span class="property-value" aria-labelledby="writing_date-label"><g:formatDate date="${commentInstance?.writing_date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${commentInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="comment.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:link controller="event" action="show" id="${commentInstance?.event?.id}">${commentInstance?.event?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${commentInstance?.participant}">
				<li class="fieldcontain">
					<span id="participant-label" class="property-label"><g:message code="comment.participant.label" default="Participant" /></span>
					
						<span class="property-value" aria-labelledby="participant-label"><g:link controller="participant" action="show" id="${commentInstance?.participant?.id}">${commentInstance?.participant?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${commentInstance?.trek}">
				<li class="fieldcontain">
					<span id="trek-label" class="property-label"><g:message code="comment.trek.label" default="Trek" /></span>
					
						<span class="property-value" aria-labelledby="trek-label"><g:link controller="trek" action="show" id="${commentInstance?.trek?.id}">${commentInstance?.trek?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${commentInstance?.id}" />
					<g:link class="edit" action="edit" id="${commentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
