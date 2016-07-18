<!DOCTYPE HTML>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />



	<title>MaPro Login Form</title>
		<meta name="generator" content="Bootply" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css"type="text/css" media="all"/>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css" type="text/css" media="all"/>
		
	</head>
	<body>
<!--login modal-->
<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h1 class="text-center">MaPro CMS</h1>
      </div>
      <div class="modal-body">
          <form:form id="loginForm" method="post" action="cmslogin" modelAttribute="cmsLoginObject" class="form col-md-12 center-block">
          
          	 <div class="form-group">
             <form:input class="form-control input-lg" id="maProUserId" name="maProUserId" path="maProUserId"  placeholder="User Id"/>
            </div>
            <div class="form-group">
             <form:password id="maProPassword" name="maProPassword" path="maProPassword" class="form-control input-lg" placeholder="Password"/>
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-lg btn-block">Sign In</button>
              <span class="pull-right"><a href="#">Register</a></span><span><a href="#">Need help?</a></span>
            </div>
          </form:form>
      </div>
      <div class="modal-footer">
          <div class="col-md-12">
          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
		  </div>	
      </div>
  </div>
  </div>
</div>
	<!-- script references -->
		<script	type='text/javascript' src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
		<script	type='text/javascript' src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	</body>
</html>