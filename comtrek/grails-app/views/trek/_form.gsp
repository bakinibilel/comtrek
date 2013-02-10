<!--
Copyright (c) 2013 ComTrek.
All rights reserved. This program and the accompanying materials
are made available under the terms of the GNU Public License v3.0
which accompanies this distribution, and is available at
http://www.gnu.org/licenses/gpl.html

Contributors:
    ComTrek - initial API and implementation
-->

<%@ page import="comtrek.Trek" %>



<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="trek.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="20" pattern="${trekInstance.constraints.name.matches}" required="" value="${trekInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'distance', 'error')} required">
	<label for="distance">
		<g:message code="trek.distance.label" default="Distance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="distance" value="${fieldValue(bean: trekInstance, field: 'distance')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'level', 'error')} required">
	<label for="level">
		<g:message code="trek.level.label" default="Level" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="level" from="${1..5}" class="range" required="" value="${fieldValue(bean: trekInstance, field: 'level')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'max_altitude', 'error')} required">
	<label for="max_altitude">
		<g:message code="trek.max_altitude.label" default="Maxaltitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="max_altitude" from="${0..10000}" class="range" required="" value="${fieldValue(bean: trekInstance, field: 'max_altitude')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'average_note', 'error')} required">
	<label for="average_note">
		<g:message code="trek.average_note.label" default="Averagenote" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="average_note" from="${0..5}" class="range" required="" value="${fieldValue(bean: trekInstance, field: 'average_note')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'attitude_combined', 'error')} required">
	<label for="attitude_combined">
		<g:message code="trek.attitude_combined.label" default="Attitudecombined" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="attitude_combined" type="number" value="${trekInstance.attitude_combined}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'average_time', 'error')} required">
	<label for="average_time">
		<g:message code="trek.average_time.label" default="Averagetime" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="average_time" type="number" value="${trekInstance.average_time}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="trek.comments.label" default="Comments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${trekInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comment" action="create" params="['trek.id': trekInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'events', 'error')} ">
	<label for="events">
		<g:message code="trek.events.label" default="Events" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${trekInstance?.events?}" var="e">
    <li><g:link controller="event" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="event" action="create" params="['trek.id': trekInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'event.label', default: 'Event')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="trek.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${comtrek.User.list()}" optionKey="id" required="" value="${trekInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: trekInstance, field: 'weather_link', 'error')} ">
	<label for="weather_link">
		<g:message code="trek.weather_link.label" default="Weatherlink" />
		
	</label>
	<g:textField name="weather_link" value="${trekInstance?.weather_link}"/>
</div>

