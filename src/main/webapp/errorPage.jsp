<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
    <body>
        <main>
            <style>
                a {
                    flex: 1;
                    text-align: center;
                    color: green;
                    font-weight: bold;
                    text-decoration: none;
                    border: 3px solid green;
                    padding: 2px;
                    border-radius: 10px;
                }
            </style>
            <h1>Error occurs, and it is totally fine.</h1>
            <h2>These things are happen sometime...</h2>
            <p class="errorMessage">${message}</p>
            <br>
            <a href="${request.getContextPath}/">Home</a>
        </main>
    </body>
</html>
