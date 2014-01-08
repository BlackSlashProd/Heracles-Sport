<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.schedules.ResultModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.schedules.ResultScoreModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisModel" %>
<jsp:include page="/jsp/general/HeaderSection.jsp" />
<jsp:include page="/jsp/general/NavigationSection.jsp" />
<section>
    <h2>DEBUG : Rencontres terminées</h2>
    <p>
        Liste des rencontres terminées avec leurs résultats (Juste pour debug).<br/> 
        <br/>
    </p>
    <div class="schedules">
        <%
        	List<ScheduleTeamModel> scheds = (List<ScheduleTeamModel>) request.getAttribute("schedules");
                for (ScheduleTeamModel sched : scheds) {
        %>
        <div class="sched">
            <h2> <%=sched.getSched_sportName()%> </h2>
            <h3>
                <%=sched.getSched_home_team().getTeam_name()%> 
                <span class="orange">VS</span> 
                <%=sched.getSched_away_team().getTeam_name()%>
            </h3>
            <p>
                <b>Date : </b><%=sched.getSched_date()%><br/><br/>
            </p>
            <p>
                <%
                	ResultScoreModel score = sched.getSched_res_score();
                    if(score != null) {
                %>
                	<b><u>Résultats :</u></b><br/>
                <%
                    }
                    if(score != null) {
                %>
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
        <%
        }
        if(scheds.size()==0) {
        %>
            <div class="nosched">
               Aucune rencontre sportive terminée.<br/>
            </div>
        <%
        }
        %>
    </div>
    <h2>DEBUG : Tous les paris</h2>
    <p>
        Liste des paris en cours (Juste pour debug).<br/> 
        <br/>
    </p>
    <div class="allparis">
        <table>
           <tr>
               <td>Joueur</td>
               <td>Mise</td>
           </tr>
          <%
            List<ParisModel> paris = (List<ParisModel>) request.getAttribute("paris");
          if(paris!=null)
          {
            for (ParisModel par : paris) {
           %>
           <tr>
               <td><%= par.getParis_user().getUser_pseudo() %></td>
               <td><%= par.getBet() %></td>
           </tr>
           <% 
            } 
          }
            %>
        </table>    
    </div>
</section>
<jsp:include page="/jsp/general/FooterSection.jsp" />