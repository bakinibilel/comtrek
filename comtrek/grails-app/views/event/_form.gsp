<!--
Copyright (c) 2013 ComTrek.
All rights reserved. This program and the accompanying materials
are made available under the terms of the GNU Public License v3.0
which accompanies this distribution, and is available at
http://www.gnu.org/licenses/gpl.html

Contributors:
    ComTrek - initial API and implementation
-->

<%@ page import="comtrek.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'effectiveTime', 'error')} required">
	<label for="effectiveTime">
		<g:message code="event.effectiveTime.label" default="Effective Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="effectiveTime" precision="day"  value="${eventInstance?.effectiveTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'averageNote', 'error')} required">
	<label for="averageNote">
		<g:message code="event.averageNote.label" default="Average Note" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="averageNote" type="number" min="0" max="10" value="${eventInstance.averageNote}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="event.comments.label" default="Comments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${eventInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comment" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'participants', 'error')} ">
	<label for="participants">
		<g:message code="event.participants.label" default="Participants" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${eventInstance?.participants?}" var="p">
    <li><g:link controller="participant" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="participant" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'participant.label', default: 'Participant')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'trek', 'error')} required">
	<label for="trek">
		<g:message code="event.trek.label" default="Trek" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="trek" name="trek.id" from="${comtrek.Trek.list()}" optionKey="id" required="" value="${eventInstance?.trek?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="event.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${comtrek.User.list()}" optionKey="id" required="" value="${eventInstance?.user?.id}" class="many-to-one"/>
</div>

