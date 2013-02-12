<!--
Copyright (c) 2013 ComTrek.
All rights reserved. This program and the accompanying materials
are made available under the terms of the GNU Public License v3.0
which accompanies this distribution, and is available at
http://www.gnu.org/licenses/gpl.html

Contributors:
    ComTrek - initial API and implementation
-->
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'leftBar.css')}" type="text/css">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div id="grailsLogo" role="banner">
			<table style="width:100%">
				<tr>
					<td align="center" style="width:50%;"><p style="color:white;font-weight:Bold;font-size:60px;">ComTrek</p></td>
					<td style="text-align:right;"><img src="${resource(dir: 'images', file: 'trek.jpg')}" alt="Grails"/></td>
				</tr>
			</table>
				
		</div>
		
			<div id="content">
				<div class="ligne">
					<span class = "case"><g:render template="/common/leftbar"></g:render></span>
						
					<span class="case"><g:layoutBody/></span>
				</div>
			
			</div>

		<div class="footer" role="contentinfo">
			<div id="footer" align="center">
				<p style="color:white;font-size:18px;margin-bottom:5px;"><g:thisYear /></p>
				<p style="color:white;font-size:18px;">Created by : <g:creat /><p/>
			</div>
		</div>
		
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>
