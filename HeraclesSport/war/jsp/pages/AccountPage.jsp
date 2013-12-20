<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.UserModel" %>
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
            <b>Pseudonyme :</b> <%= ((UserModel)request.getAttribute("user")).getUser_name()  %> <br/>
            <b>User Point :</b> <%= ((UserModel)request.getAttribute("user")).getUser_point()  %> <br/>
        </p>
    <% } %>
</section>
<jsp:include page="/jsp/general/FooterSection.jsp" />