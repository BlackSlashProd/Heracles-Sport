<%@page import="upmc.aar2013.project.heraclessport.server.front.forms.AccountForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.UserModel" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<jsp:include page="/jsp/general/HeaderSection.jsp" />
<jsp:include page="/jsp/general/NavigationSection.jsp" />
<section>
    <h2>Mon Compte</h2>
    <% if (request.getAttribute("user") != null) { 
    %>  
        <h3>Profil</h3>
        <p>
            <b>Compte Google :</b> <%= ((UserModel)request.getAttribute("user")).getUser_name()  %> <br/>
            <b>Mail Google :</b> <%= ((UserModel)request.getAttribute("user")).getUser_mail()  %> <br/>
            <b>Date création compte :</b> <%= ((UserModel)request.getAttribute("user")).getUser_creation()  %> <br/>
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
<jsp:include page="/jsp/general/FooterSection.jsp" />