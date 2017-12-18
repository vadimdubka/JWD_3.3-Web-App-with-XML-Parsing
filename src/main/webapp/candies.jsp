<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Candies</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<jsp:useBean id="candy_page_model" type="com.dubatovka.app.controller.pagination.PageModel" scope="request"/>

<a href="index.jsp">Go back to index page</a>
<div align="center">
    <table border="true">
        <caption>Candies</caption>
        <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Filled</th>
            <th>water, g</th>
            <th>sugar, g</th>
            <th>milk, g</th>
            <th>fructose, mg</th>
            <th>vanillin, mg</th>
            <th>nuts, g</th>
            <th>chocolateType</th>
            <th>porous</th>
            <th>amount, g</th>
            <th>proteins, g</th>
            <th>fats, g</th>
            <th>carbohydrates, g</th>
            <th>energy, cal</th>
        </tr>
        <c:forEach var="candy" items="${candy_page_model.entityListOnPage}">
            <tr>
                <td>${candy.name}</td>
                <td>${candy.type}</td>
                <td>${candy.filled}</td>
                <td>${candy.ingredients.water}</td>
                <td>${candy.ingredients.sugar}</td>
                <td>${candy.ingredients.milk}</td>
                <td>${candy.ingredients.fructose}</td>
                <td>${candy.ingredients.vanillin}</td>
                <td>${candy.ingredients.nuts}</td>
                <td>${candy.ingredients.chocolate.chocolateType}</td>
                <td>${candy.ingredients.chocolate.porous}</td>
                <td>${candy.ingredients.chocolate.amount}</td>
                <td>${candy.value.proteins}</td>
                <td>${candy.value.fats}</td>
                <td>${candy.value.carbohydrates}</td>
                <td>${candy.energy}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div align="center">
    <c:forEach var="i" begin="1" end="${candy_page_model.amountOfPages}">
        <c:choose>
            <c:when test="${i!=candy_page_model.currentPage}">
                <a href="controller?command_type=goto_pagination&page_number=<c:out value="${i}"/>">
                    <c:out value="${i}"/>
                </a>
            </c:when>
            <c:otherwise>
                <c:out value="${i}"/>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
</body>
</html>
