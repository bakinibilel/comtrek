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



<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'comment', 'error')} required">
	<label for="comment">
		<g:message code="comment.comment.label" default="Comment" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="comment" maxlength="30" pattern="${commentInstance.constraints.comment.matches}" required="" value="${commentInstance?.comment}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'writing_date', 'error')} required">
	<label for="writing_date">
		<g:message code="comment.writing_date.label" default="Writingdate" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="writing_date" precision="day"  value="${commentInstance?.writing_date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="comment.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${comtrek.Event.list()}" optionKey="id" required="" value="${commentInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'participant', 'error')} required">
	<label for="participant">
		<g:message code="comment.participant.label" default="Participant" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="participant" name="participant.id" from="${comtrek.Participant.list()}" optionKey="id" required="" value="${commentInstance?.participant?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'trek', 'error')} required">
	<label for="trek">
		<g:message code="comment.trek.label" default="Trek" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="trek" name="trek.id" from="${comtrek.Trek.list()}" optionKey="id" required="" value="${commentInstance?.trek?.id}" class="many-to-one"/>
</div>

