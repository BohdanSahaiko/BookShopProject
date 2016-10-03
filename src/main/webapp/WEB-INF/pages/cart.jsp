<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Your Sopping Cart</title>
    <!-- Bootstrap CSS -->
    <%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style>
        p {
            margin: 0px !important;
        }
        .main-container {
            width: 100% !important;
        }
        .one-book {
            margin-bottom: 25px;
            margin-top: 25px;
        }
        .books-row {
            height: 310px;
        }
        .book-container {
            height: 100% !important;
            padding: 0px !important;
        }
        .img-wrapper {
            height: 310px;
            width: 200px;
            overflow: hidden;
            margin: 0 auto;
            z-index: -1;

        }
        .img-wrapper img {
            width: 100%;
            height: auto;
        }
        .img-wrapper:hover {
            width: 230px;
            height: auto;
            overflow: visible;
            z-index: 1000;
            position: relative;
            -webkit-transition: 1s all ease-in-out 0s;
            transition: 1s all ease-in-out 0s;
        }
        .book-name {
            text-align: center;
            margin-top: 20px;
            font-size: 25px;
        }
        .author {
            opacity: 0.5;
            text-align: center;

        }
        .genre {
            margin-top: 15px;
            text-align: center;
        }
        .genre span {
            padding: 5px 20px;
            background-color: #3ca6d7;
            color: #fff;
            border-radius: 10px;
        }
        .price {
            font-size: 30px;
            opacity: 0.5;
            bottom: 10px;
            position: absolute;
            text-align: center;
            width: 100%;
        }
        .add-button {
            position: absolute;
            width: 100%;
            text-align: center;
            bottom: 20%;
        }
        .add-button a {
            font-size: 20px;
            font-weight: 900;
            padding: 10px 50px;
            background-color: #b5b5b5;
            color: #fff;
            border-radius: 50px;
        }
        .add-button a:hover {
            text-decoration: none;
            background-color: #a1a1a1;
            -webkit-transition: 0.5s background-color ease-in-out 0s;
            transition: 0.5s background-color ease-in-out 0s;
        }
    </style>
</head>
<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<c:if test="${empty booksCartList}">
    There are no Books
</c:if>
<c:if  test="${not empty booksCartList}">
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">
            <div align="left"><b>Home</b> </div>
            <div align="right"><a href="/">home</a></div>
        </h3>
    </div>
</div>
    <div class="container main-container">
        <div class="row">
            <c:forEach items="${booksCartList}" var="book">
                <div class="col-lg-4 col-md-6 one-book">
                    <div class="row books-row">
                        <div class="col-xs-6 book-container">
                            <div class="img-wrapper">
                                <img src="data:image/jpeg;base64,${book.getImgAsBase64()}">
                            </div>
                        </div>
                        <div class="col-xs-6 book-container">
                            <div class="book-name">
                                <p><c:out value="${book.name}"/></p>
                            </div>
                            <div class="author">
                                <p><c:out value="${book.author}"/></p>
                            </div>
                            <div class="genre">
                                <span><c:out value="${book.type}"/></span>
                            </div>
                            <div class="price">
                                <p><c:out value="${book.price}"/>$</p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <%-- <script src="<c:url value="/resources/js/jquery-2.1.3.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
     --%>

</body>
</html>