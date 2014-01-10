<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.schedules.ResultModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.schedules.ResultScoreModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisScoreModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisVictoryModel" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Heracles Sport - Historique</title>
        <link rel="icon" type="image/png" href="images/style/favicon.png" />
        <link rel="stylesheet" href="css/general.css" type="text/css" />
        <link rel="stylesheet" href="css/schedules.css" type="text/css" />
    </head>
    <body>
        <div id="page">
			<jsp:include page="/jsp/general/NavigationSection.jsp" />
			<section>
			    <h2>Paris effectués et terminés</h2>
			    <p>
			        Liste des paris terminés avec leurs résultats.<br/> 
			        <br/>
			    </p>
			    <div class="schedules">
			    	<%
			        List<ParisModel> paris = (List<ParisModel>) request.getAttribute("paris");
			        for (ParisModel pari : paris) {
			        	ScheduleTeamModel schedule = (ScheduleTeamModel) pari.getParis_sched();
			        	if (schedule==null) // oups
			        		System.out.println("schedule est a null");
				        %>
				        <div class="sched">
				            <h2> <%=schedule.getSched_sportName()%> </h2>
				            <h3>
				                <%=schedule.getSched_home_team().getTeam_name()%> 
				                <span class="orange">VS</span> 
				                <%=schedule.getSched_away_team().getTeam_name()%>
				            </h3>
				            <p>
				                <b>Date : </b><%=schedule.getSched_date()%><br/><br/>
				            </p>
				            <p>
				                <%
				                	ResultScoreModel score = schedule.getSched_res_score();
				                    if(score != null) {
				                %>
				                	<b><u>Résultats :</u></b><br/>
					                       <b>Scores :</b> 
					                       <%=score.getScore_res_score_home()%> 
					                       - 
					                       <%=score.getScore_res_score_away()%>             
					                       <br/><br/>
					            <%
					                }
				                %>
				            </p>
				        </div>
				        <div class="???">
				        	<% if (pari instanceof ParisScoreModel) { %>
				           		<b>Pari sur le score : </b> <br/>
			                        <%=((ParisScoreModel)pari).getScore_team_home()%> 
			                        - 
			                        <%=((ParisScoreModel)pari).getScore_team_away()%>           
			                        <br/><br/>
				        	<% } else if (pari instanceof ParisVictoryModel) { %>
				        		<b>Pari sur l'équipe : </b> <br/>
			                        <%=((ParisVictoryModel)pari).getParis_team().getTeam_name()%>         
			                        <br/><br/>
				        	<% } %>
				        </div>
			        <%
			        }
			        %>
			    </div>
			</section>
        </div>
    </body>
</html>