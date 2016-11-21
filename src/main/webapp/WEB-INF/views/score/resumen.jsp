<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${not empty consulta.scores }">
	<table class="table">
		<thead>
			<tr>
				<th>Score</th>
				<th>Valor</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entry" items="${consulta.scores}">
				<tr>
					<td>${entry.value.scoreType.name}</td>
					<td>${entry.value.score}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
