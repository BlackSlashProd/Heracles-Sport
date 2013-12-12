<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/jsp/general/HeaderSection.jsp" />
<jsp:include page="/jsp/general/NavigationSection.jsp" />
<section>
    <h2>Test JSP Servlet Page</h2>
    <p> Random int: <%=     request.getAttribute("randInt") %> </p>
</section>
<jsp:include page="/jsp/general/FooterSection.jsp" />

