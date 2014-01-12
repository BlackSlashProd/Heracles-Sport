<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel" %>
    <%@ page import="com.google.appengine.api.users.*" %>
    <% UserService userService = UserServiceFactory.getUserService(); %>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Heracles Sport - Accueil</title>
        <link rel="icon" type="image/png" href="images/style/favicon.png" />
        <link rel="stylesheet" href="css/general.css" type="text/css" />
    </head>
    <body>
        <div id="page">
			<jsp:include page="/jsp/general/NavigationSection.jsp" />
			<section>
			    <h2>Accueil</h2>
			    <div class="homepage">
			            <h3>Descriptif</h3>
			            <p>
			                HeraclesSport est LE site de paris sportif. Descriptif...<br/><br/>
			            </p>
			            <h3>Rêgles du jeu</h3>
			            <p>
			                Explications calculs sur les points, avec multiplicateurs, ...
			            </p>
			            <div class="user_zone">
			        <% 
			            UserModel user = (UserModel)request.getAttribute("user");
			            if (user != null) {
			        %>
			            
			                <h3>Récapitulatif de votre compte</h3>
				            <p>
				                Bonjour <b class="green"><%= user.getUser_pseudo() %></b> (<a href="/account">Editer mon profil</a>)<br/>
				                <b class="orange">Points disponibles : </b><b class="green"><%= user.getUser_point() %></b><br/>
				                <b class="orange">Point en jeux : </b><b class="green"><%= user.getUser_ingame_point() %></b><br/>
				                <b class="orange">Ratio (Point/Jour) : </b><b class="green"><%= user.getUser_dailyRatio() %></b><br/>
				                <br/><br/>
				            </p>
				            <% if(userService.isUserAdmin()) { %>
					            <p style="margin:10px 0; padding:5px; border:2px solid #FF0000;">
					                <b>Debug : </b><br/>
					                <a href="/datatest?fct=create">Remplir BDD</a><br/>
					                <a href="/datatest?fct=finish">Finir les rencontres</a><br/>
					                <a href="/datatest?fct=remove">Vider BDD (Déconnexion)</a><br/>
					            </p>
				            <% } %>
			         <% } else { %>
                        <p>
                            Connectez vous pour pouvoir jouer : <a href="<%= userService.createLoginURL("/log") %>">Se connecter</a>
                        </p>			         
			         <% } %>
                     </div>
			    </div>
			</section>
		</div>
    </body>
</html>