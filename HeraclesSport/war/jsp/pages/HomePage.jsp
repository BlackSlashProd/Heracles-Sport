<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.google.appengine.api.users.*" %>
    <% UserService userService = UserServiceFactory.getUserService(); %>

<jsp:include page="/jsp/general/HeaderSection.jsp" />
<jsp:include page="/jsp/general/NavigationSection.jsp" />
<section>
    <h2>Home Page</h2>
    <div class="debug" style="border-bottom:2px solid #FFFFFF;">
	    <p>
	        <b><u>Pour les utilisateurs non connectés :</b></u><br/>
	        Descriptif du jeu et formulaire de connection ainsi qu'un lien vers la création de compte.<br/><br/>
	        <b><u>Pour les joueurs connectés :</b></u><br/>
	        Récapitulatif du compte (nombre de point, mise actuelle en jeu, ...).<br/><br/>
	    </p>
    </div>
    <div class="homepage">
        <% if (userService.getCurrentUser() == null) { %>
            <p>
                Connectez vous pour pouvoir jouer : <a href="<%= userService.createLoginURL("/log") %>">Se connecter</a>
            </p>
        <% }
        else { %>
            <p>
                Bonjour <%= userService.getCurrentUser().getNickname() %> (<a href="/account">Editer mon profil</a>)<br/><br/>
            </p>
            <p style="margin:10px 0; padding:5px; border:2px solid #FF0000;">
                <b>Debug : </b><br/>
                <a href="/datatest?fct=create">Remplir BDD</a><br/>
                <a href="/datatest?fct=remove">Vider BDD (Déconnexion)</a><br/>
            </p>
        <% } %>
    </div>
</section>
<jsp:include page="/jsp/general/FooterSection.jsp" />