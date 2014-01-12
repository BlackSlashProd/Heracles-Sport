<%@page import="upmc.aar2013.project.heraclessport.server.front.forms.AccountForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Heracles Sport - Compte</title>
        <link rel="icon" type="image/png" href="images/style/favicon.png" />
        <link rel="stylesheet" href="css/general.css" type="text/css" />
        <link rel="stylesheet" href="css/account.css" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="js/script_account.js"></script>
    </head>
    <body>
        <div id="page">
			<jsp:include page="/jsp/general/NavigationSection.jsp" />
			<section>
			    <h2>Mon Compte</h2>
			    <% if (request.getAttribute("user") != null) { 
			    %>  
			        <h3>Profil</h3>
			        <p>
			            <b>Compte Google :</b> <%= ((UserModel)request.getAttribute("user")).getUser_name()  %> <br/>
			            <b>Mail Google :</b> <%= ((UserModel)request.getAttribute("user")).getUser_mail()  %> <br/>
			            <b>Date création compte :</b> <%= ((UserModel)request.getAttribute("user")).getUser_creationDateClean()  %> <br/>
			            <b>Pseudonyme :</b> <%= ((UserModel)request.getAttribute("user")).getUser_pseudo()  %> <br/>
			            <b>User Point :</b> <%= ((UserModel)request.getAttribute("user")).getUser_point()  %> <br/>
			            <br/>
			        </p>
			        <form class="log_form" method="post" action="/account">
			            <fieldset>
			                <legend>Modifier mon profil</legend>
			                <% if (request.getAttribute("form") != null) { 
			                %>  
			                    <div class="fiel_clear">
			                        <p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
			                    </div>
			                <% } %>
			                <div class="fiel_clear">
			                     <a id="generate" href="#">Générer un pseudo</a>
			                </div>
			                <div class="fiel_clear">
				                <label for="pseudo">Pseudo</label>
				                <input type="text" id="pseudo" name="pseudo" value="<%= ((UserModel)request.getAttribute("user")).getUser_pseudo()%>" size="20" maxlength="20" />
				                <span class="error">${form.errors['pseudo']}</span>
			                </div>
			                <div class="fiel_clear">
			                    <input type="submit" value="Modifier" class="button bg_gradient_green" />
			                </div>
			            </fieldset>
			        </form>
			    <% } %>
			</section>
        </div>
    </body>
</html>