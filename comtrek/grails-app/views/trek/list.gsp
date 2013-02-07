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
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-trek" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-trek" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'trek.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="distance" title="${message(code: 'trek.distance.label', default: 'Distance')}" />
					
						<g:sortableColumn property="level" title="${message(code: 'trek.level.label', default: 'Level')}" />
					
						<g:sortableColumn property="max_altitude" title="${message(code: 'trek.max_altitude.label', default: 'Maxaltitude')}" />
					
						<g:sortableColumn property="average_note" title="${message(code: 'trek.average_note.label', default: 'Averagenote')}" />
					
						<g:sortableColumn property="attitude_combined" title="${message(code: 'trek.attitude_combined.label', default: 'Attitudecombined')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${trekInstanceList}" status="i" var="trekInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${trekInstance.id}">${fieldValue(bean: trekInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: trekInstance, field: "distance")}</td>
					
						<td>${fieldValue(bean: trekInstance, field: "level")}</td>
					
						<td>${fieldValue(bean: trekInstance, field: "max_altitude")}</td>
					
						<td>${fieldValue(bean: trekInstance, field: "average_note")}</td>
					
						<td>${fieldValue(bean: trekInstance, field: "attitude_combined")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${trekInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
