<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Error Page</title></head>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}


<main class="container">
    <section class="error-section cols col-12">
        <ul class="error-paragraph">
            <li>Request from <c:out value="${pageContext.errorData.requestURI}" default="N/A"/> is failed</li>
            <li>Servlet name or type: <c:out value="${pageContext.errorData.servletName}" default="N/A"/></li>
            <li>Status code: <c:out value="${pageContext.errorData.statusCode}" default="N/A"/></li>
            <li>Exception: <c:out value="${pageContext.errorData.throwable}" default="N/A"/></li>
            <li>Message from exception: <c:out value="${pageContext.exception.message}" default="N/A"/></li>
            <li>Error message: <c:out value="${errorMessage}" default="Invalid request"/></li>
        </ul>
        <div class="custom-button">
            <a href="${pageContext.request.contextPath}/controller?command=back_from_error">Back</a>
        </div>
    </section>
</main>

</body>
</html>
