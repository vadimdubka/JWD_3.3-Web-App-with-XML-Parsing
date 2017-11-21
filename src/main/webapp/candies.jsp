<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Candies</title>
</head>
<body>
<a href="index.jsp">Go back to index page</a>

<jsp:useBean id="candy_page_model" type="com.dubatovka.app.controller.pagination.PageModel" scope="request"/>
<div align="center">
    <p>Candies:</p>
    <table border="true">
        <tr>
            <td>Name</td>
            <td>Type</td>
            <td>Filled</td>
            <td>water</td>
            <td>sugar</td>
            <td>milk</td>
            <td>fructose</td>
            <td>vanillin</td>
            <td>nuts</td>
            <td>chocolateType</td>
            <td>porous</td>
            <td>amount</td>
            <td>proteins</td>
            <td>fats</td>
            <td>carbohydrates</td>
            <td>energy</td>
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

<%--TODO сделать вывод в зависимости от типа конфет поставка или производство--%>
<%--TODO сделать форматированный вывод на экран--%>

</body>
</html>
