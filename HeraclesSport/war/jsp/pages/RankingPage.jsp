<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Heracles Sport - Classement</title>
        <link rel="icon" type="image/png" href="images/style/favicon.png" />
        <link rel="stylesheet" href="css/general.min.css" type="text/css" />
        <link rel="stylesheet" href="css/ranking.min.css" type="text/css" />
    </head>
    <body>
        <div id="page">
			<jsp:include page="/jsp/general/NavigationSection.jsp" />
			<section>
			    <h2>Classement</h2>
			    <p>
			        Classement de tous les joueurs.<br/> 
			        <% 
			           UserModel user = (UserModel)request.getAttribute("user");
			           if(user != null) { %>
			            <a href="#me">Accéder directement à votre place.</a><br/>
			        <% } %>
			        <br/>
			    </p>
			    <div class="ranking">
				    <table>
				       <tr>
				           <td>Place</td>
				           <td>Joueur</td>
				           <td>Points</td>
				           <td>En jeu</td>
				       </tr>
			           <%
			            List<UserModel> players = (List<UserModel>) request.getAttribute("players");
				        int place = 1;
				        for (UserModel player : players) {
				        	if(user!=null && (user.getUser_id().compareTo(player.getUser_id())==0)) {
				       %>
				       <tr id="me">
				       <%   }
				        	else { %>
				       <tr>
				       <%   } %>
				           <td><%= place++ %></td>
				           <td><%= player.getUser_pseudo() %></td>
				           <td><%= player.getUser_point() %></td>
				           <td><%= player.getUser_ingame_point() %></td>
				       </tr>
				       <% 
				        } 
				        %>
				    </table>
			    </div>
			</section>
        </div>
    </body>
</html>