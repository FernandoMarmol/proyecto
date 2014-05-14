<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.boceto.bean.Request"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.boceto.Constants"%>

<%
@SuppressWarnings("unchecked")
ArrayList<Request> lista = (ArrayList<Request>) request.getAttribute(Constants.LIST_REQUESTS);
%>

<div style="position: absolute; text-align: center; width: 100%; height: 100%; top: 0%; left: 0%; overflow: auto;">

	<h3> Lista de Peticiones Realizadas </h3>
	
	<br>

	<table class="listTable">
		<%if(lista == null || lista.isEmpty()){%>
			<tr><td> No dispone de elementos para esta lista </td></tr>
		<%}
		else{
			for(Request req:lista){%>
				<tr>
					<td>
						<%=req.getTitle()%>
					</td>
				</tr>
		<%	}
		}%>
	</table>

</div>