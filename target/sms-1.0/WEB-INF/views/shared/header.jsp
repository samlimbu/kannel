<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="SITE_URL" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--
        <link href="${SITE_URL}/static/css/styles.css" rel="stylesheet" type="text/css"/>
        <link href="${SITE_URL}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${SITE_URL}/static/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <script src="${SITE_URL}/static/js/jquery.min.js" type="text/javascript"></script>
        <script src="${SITE_URL}/static/js/bootstrap.min.js" type="text/javascript"></script>
        -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <style>
            body{
                font-family: sans-serif;
            }
            h2{
                font-style: oblique;
            }
            
            form  {
    border-collapse:collapse;
    border-spacing:0;
    border-color:#ccc;
}
table tr:nth-child(odd) {
    background-color: hsl(50,80%,80%);
}
table tr:nth-child(even) {
    background-color: hsl(240,20%,89%);
}

table td{font-family:Arial, sans-serif;
         font-size:14px;
         padding:10px 5px;
         border-style:solid;
         border-width:1px;
         overflow:hidden;
         word-break:normal;
         border-color:#ccc;

        
}
 table th{
     font-family:Arial, sans-serif;font-size:14px;
     font-weight:normal;
     padding:10px 5px;
     border-style:solid;
     border-width:1px;
     overflow:hidden;
     word-break:normal;
     border-color:#ccc;
     color:#333;
     background-color:#f0f0f0;
 }
		

 form-4eph{

     background-color:#f9f9f9
 }
 #table-container, #form-container{
     display: block;
     float: left;
 }
 #disable{
     color: gray;
     background-color: #ababab;
 }
 
 .hide{
     display: none;
 }
 
 .button-label{
     font-size: 12px;
     padding: 5px;
     cursor: pointer;
     border:1px solid #333;
     border-radius: 9px;
 }
 
 .button-label:hover{
     cursor: pointer;
     border-color: lime;
 }
 
  .button-label-2{
      font-size: 12px;
     padding: 5px;
     cursor: pointer;
     border:1px solid #333;
     border-radius: 9px;
       
 }
 
 .button-label-2:hover{
     cursor: pointer;
     border-color: lime;
 }
 
 .submit-button-container{
     margin-top: 5px;
 }
 
 #formlist-container-input{
     width: 150px;
     margin: 10px;
     background-color: #b7b7b7;
     float: top;
     vertical-align: top; 
 }
 .form-inputs{
     display: inline-block;
     padding: auto;
 }
 #myspan{
     display: block;
 }
 
         </style>   
    </head>
    <body><a href="<c:url value="/"/>"><h1>Student Management System</h1>
        <div class ="container">

