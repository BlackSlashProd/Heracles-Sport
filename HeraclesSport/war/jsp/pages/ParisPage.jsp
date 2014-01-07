<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.UserModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.ScheduleTeamModel" %>
<jsp:include page="/jsp/general/HeaderSection.jsp" />
<jsp:include page="/jsp/general/NavigationSection.jsp" />
<section>
    <h2>Rencontres à venir</h2>
    <p>
        Liste des rencontres sur lesquelles miser.<br/> 
        <% 
           UserModel user = (UserModel)request.getAttribute("user");
           if(user != null) { %>
           Mise disponible : <%= user.getUser_point() %><br/>
        <% } %>
        <br/>
    </p>
    <div class="schedules">
        <%
        List<ScheduleTeamModel> scheds = (List<ScheduleTeamModel>) request.getAttribute("schedules");
        for (ScheduleTeamModel sched : scheds) {
        %>
        <div class="sched">
            <h2> <%= sched.getSched_sportName() %> </h2>
            <h3>
                <%= sched.getSched_home_team().getTeam_name() %> 
                <span class="orange">VS</span> 
                <%= sched.getSched_away_team().getTeam_name() %>
            </h3>
            <p>
                <b>Date : </b><%= sched.getSched_date() %><br/>
                
            </p>
            <form class="paris_form" method="post" action="/paris">
	            <fieldset>
	                <input type="hidden" name="sched_id" value="<%= sched.getSched_id() %>" />
	                <legend>Parier sur cette rencontre</legend>
	                <% if (request.getAttribute("form") != null && sched.getSched_id().compareTo(request.getAttribute("form_id").toString())==0) { 
	                %>  
	                    <div class="fiel_clear">
	                        <p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
	                    </div>
	                <% } %>
	                <div class="fiel_clear">
	                    <label for="paris_type">Type de paris</label>
	                    <select id="paris_type" name="paris_type">
	                       <option value="vict">Victoire</option>
	                       <option value="scor">Score</option>
	                    </select>
	                </div>
                    <div id="paris_type_vict" class="fiel_clear">
                        <label for="paris_vict">Equipe victorieuse</label>
                        <select id="paris_vict" name="paris_vict">
                           <option value="teamhome"><%= sched.getSched_home_team().getTeam_name() %></option>
                           <option value="teamaway"><%= sched.getSched_away_team().getTeam_name() %></option>
                        </select>
                    </div>
                    <div id="paris_type_scor" class="fiel_clear" >
                        <input type="radio" name="paris_scor_type" value="teamhome"><%= sched.getSched_home_team().getTeam_name() %>
                        <input type="radio" name="paris_scor_type" value="teamaway"><%= sched.getSched_away_team().getTeam_name() %>
                        <input checked="checked" type="radio" name="paris_scor_type" value="teamall">Les Deux<br/>
                        <%= sched.getSched_home_team().getTeam_name() %><input type="text" id="paris_scor_teamhome" name="paris_scor_teamhome" value="0" size="5" maxlength="5" /> - 
                        <%= sched.getSched_away_team().getTeam_name() %><input type="text" id="paris_scor_teamaway" name="paris_scor_teamaway" value="0" size="5" maxlength="5" />
                    </div>
	                <div class="fiel_clear">
                        <label for="paris_bet">Mise</label>
                        <input type="text" id="paris_bet" name="paris_bet" value="1" size="5" maxlength="5" />
                    </div>                
	                <div class="fiel_clear">
	                    <input type="submit" value="Parier" class="button bg_gradient_green" />
	                </div>
	            </fieldset>
            </form>
        </div>
        <%
        }
        if(scheds.size()==0) {
        %>
        	<div class="nosched">
        	   Aucune rencontre sportive à venir.<br/>
        	</div>
        <%
        }
        %>
    </div>
</section>
<jsp:include page="/jsp/general/FooterSection.jsp" />

