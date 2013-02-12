<!--
Copyright (c) 2013 ComTrek.
All rights reserved. This program and the accompanying materials
are made available under the terms of the GNU Public License v3.0
which accompanies this distribution, and is available at
http://www.gnu.org/licenses/gpl.html

Contributors:
    ComTrek - initial API and implementation
-->
<%@ page import="comtrek.Participant" %>



<div class="fieldcontain ${hasErrors(bean: participantInstance, field: 'note', 'error')} required">
	<label for="note">
		<g:message code="participant.note.label" default="Note" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="note" from="${0..10}" class="range" required="" value="${fieldValue(bean: participantInstance, field: 'note')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: participantInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="participant.comments.label" default="Comments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${participantInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comment" action="create" params="['participant.id': participantInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: participantInstance, field: 'events', 'error')} required">
	<label for="events">
		<g:message code="participant.events.label" default="Events" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="events" name="events.id" from="${comtrek.Event.list()}" optionKey="id" required="" value="${participantInstance?.events?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: participantInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="participant.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${comtrek.User.list()}" optionKey="id" required="" value="${participantInstance?.user?.id}" class="many-to-one"/>
</div>

