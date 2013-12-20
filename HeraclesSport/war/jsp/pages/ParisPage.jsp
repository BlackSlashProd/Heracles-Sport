<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.UserModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.ScheduleModel" %>
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
        List<ScheduleModel> scheds = (List<ScheduleModel>) request.getAttribute("schedules");
        for (ScheduleModel sched : scheds) {
        %>
        <div class="sched">
            <h3>
                <%= sched.getSchedTeamHome() %> <span class="orange">VS</span> <%= sched.getSchedTeamAway() %>
            </h3>
            <p>
                <b>Date : </b><%= sched.getSched_date() %><br/>
                
            </p>
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

