<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.google.appengine.api.users.*" %>
    <% UserService userService = UserServiceFactory.getUserService(); %>
    
    <header class="bg_gradient_green">
        <h1>Bienvenue sur Heracles Sport</h1>
    </header>
    <nav>
        <ul>
            <li><a href="/">Accueil</a></li>
            <li><a href="/paris">Parier</a></li>
            <li><a href="/rank">Classement</a></li>
        <% if (userService.getCurrentUser() == null) { %>
            <li class="log_co">
                <a href="<%= userService.createLoginURL("/log") %>">Connexion</a>
            </li>
        <% }
        else { %>
            <li><a href="/histo">Historique</a></li>
            <li class="log_disc">
                <a href="<%= userService.createLogoutURL("/") %>">Déconnexion</a>
            </li>
        <% } %>
        </ul>
    </nav>