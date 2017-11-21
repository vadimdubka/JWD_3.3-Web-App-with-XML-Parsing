<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Index</title></head>
<body>
<div align="center">
    <h2>Choose XML-Parser to get Candies from XML File</h2>
    ${errorMessage}
    <form action="controller" method="get">
        <input type="hidden" name="command_type" value="xml_parse"/>
        <input type="hidden" name="document_path" value="data/candies.xml"/>
        <input type="hidden" name="schema_path" value="data/candies.xsd"/>
        <input type="hidden" name="page_number" value="1"/>
        <button type="submit" name="parser_type" value="sax">SAX</button>
        <br/><br/>
        <button type="submit" name="parser_type" value="stax">StAX</button>
        <br><br>
        <button type="submit" name="parser_type" value="dom">DOM</button>
    </form>
</div>
</body>
</html>

