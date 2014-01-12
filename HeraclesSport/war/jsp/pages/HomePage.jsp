<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel" %>
    <%@ page import="upmc.aar2013.project.heraclessport.server.configs.Configs" %>
    <%@ page import="com.google.appengine.api.users.*" %>
    <% UserService userService = UserServiceFactory.getUserService(); %>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Heracles Sport - Accueil</title>
        <link rel="icon" type="image/png" href="images/style/favicon.png" />
        <link rel="stylesheet" href="css/general.min.css" type="text/css" />
    </head>
    <body>
        <div id="page">
			<jsp:include page="/jsp/general/NavigationSection.jsp" />
			<section>
			    <h2>Accueil</h2>
			    <div class="homepage">
			            <h3>Descriptif</h3>
			            <p>
			                HeraclesSport est LE site de paris sportif.<br/>
			                Pariez sur les rencontres de la <b class="orange">NBA</b> et bientôt d'autres sports<br/>
                            Plusieurs types de paris sont possibles :<br/>
							- pariez sur votre équipe favorite.<br/>
							- pariez sur le score d'une équipe.<br/>
							- pariez sur le score final du match.<br/>
							Plus le pari est risqué, plus le gain est important.<br/>
			                <br/>
			            </p>
			            <h3>Rêgles du jeu</h3>
			            <p>
			                <b>Vous commencez avec <span class="orange"><%= Configs.getStartPoint() %> points</span>.</b><br/><br/>
			            
							Chaque pari sur une équipe gagnante remporté vous rapporte <span class="orange"><%= Configs.getMultTeamVict() %></span> fois votre mise plus <span class="orange"><%= Configs.getMultTeamVict() %></span> fois un pourcentage de toutes les mises perdantes pour le type de paris et la rencontre en question !<br/>
							Plus vous misez gros, plus ce pourcentage est gros.<br/><br/>
						</p>
						<p style="font-style:italic;">	
								<u>Prenons un exemple :</u><br/>
								Rencontre A contre B.<br/>
								Vous pariez 50 points sur A.<br/>
								Bob pari 30 points sur A.<br/>
								Estelle parie 20 points sur A.<br/>
								Il y a 100 points sur A.<br/>
								Tom pari 30 points sur B.<br/>
								Max pari 20 points sur B.<br/>
								Il y a 50 points sur B.<br/><br/>
								
								Votre pari représente 50% des mises des paris sur A, se sera donc ce même pourcentage que vous récupérerez des mises sur B.<br/>
								Vous gagnerez donc <%= Configs.getMultTeamVict() %> * 50 + <%= Configs.getMultTeamVict() %> * 25 (25 = 50% des mises des perdants).<br/><br/>
								
								De la même façon, pariez sur le score d'une équipe multipliera par <%= Configs.getMultTeamScorOne() %> votre mise et le pourcentage de ce que vous gagnerez, et pariez sur le score des 2 équipes les multiplira par <%= Configs.getMultTeamScorBoth() %>.<br/><br/>
                        </p>
                        <p>
							<b>Chaque jour, un bonus de <span class="orange"><%= Configs.getDailyPoint() %> points</span> vous sera accordé</b> pour ne jamais rester bloqué à 0.<br/><br/>
							
							<b>Faites les bons choix, et restez au top du classement !!!</b><br/><br/>
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