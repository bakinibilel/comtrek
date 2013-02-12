<!--
Copyright (c) 2013 ComTrek.
All rights reserved. This program and the accompanying materials
are made available under the terms of the GNU Public License v3.0
which accompanies this distribution, and is available at
http://www.gnu.org/licenses/gpl.html

Contributors:
    ComTrek - initial API and implementation
-->
<div id="status" role="complementary">
			
	
	
	<div id="controller-list" role="navigation">
		
			<h2>Les controlleurs:</h2>
			<ul>
				<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<g:if test="${c.logicalPropertyName != 'dbdoc'}" >
							<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.logicalPropertyName}</g:link></li>
						</g:if>
				</g:each>
			</ul>
	
	</div>
	
	
</div>
