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
		<script	type='text/javascript' src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
		<script	type='text/javascript' src="<%=request.getContextPath()%>/js/maPro.js"></script>
		
	</head>
	<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="navbar-header">
        <a class="navbar-brand" rel="home" href="#">MaPro CMS</a>
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		</button>
	</div>
	<div class="collapse navbar-collapse">
		<ul class="nav navbar-nav">
			<li><a href="#">Browse Documents</a></li>
			<li><a href="#" onclick="uploadMaProDocument()">Upload Documents</a></li>
			<li><a href="#">List Favorites</a></li>
			<li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Account Settings <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">My Profile</a></li>
                <li><a href="#">Change Password</a></li>
                <li class="divider"></li>
                <li><a href="#">View MaPro Roles</a></li>
                <li class="divider"></li>
                <li><a href="#">Contact Us</a></li>
              </ul>
            </li>
		</ul>
		<div class="col-sm-3 col-md-3 pull-right">
		<form id="fileUploadForm">	 
					<input id="fileToUpload" name="fileToUpload" type="file"  style="visibility:hidden"/> <br/>
		 </form>
          <form class="navbar-form" role="search">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                </div>
            </div>
          </form>
		</div>
	</div>
</nav>

<div class="container-fluid">
  
  <!--left-->
  <div class="col-sm-3">
        <h2>Side</h2>
    	<div class="panel panel-default">
         	<div class="panel-heading">MaPro Title</div>
         	<div class="panel-body">MaPro Any Content Goes Here.. 
        </div>
        </div>
        <hr>
        <div class="panel panel-default">
         	<div class="panel-heading">MaPro Title</div>
         	<div class="panel-body">MaPro Any Content Goes Here..</div>
        </div>
        <hr>
        <div class="panel panel-default">
         	<div class="panel-heading">MaPro Title</div>
         	<div class="panel-body">MaPro Any Content Goes Here..</div>
        </div>
        <hr>
        <div class="panel panel-default">
         	<div class="panel-heading">MaPro Title</div>
         	<div class="panel-body">MaPro Any Content Goes Here..</div>
        </div>
        <hr>
  </div><!--/left-->
  
  <!--center-->
  <div class="col-sm-6">
    <div class="row">
      <div class="col-xs-12">
        <h2>MaPro Header</h2>
        <p>MaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaPro. 
          MaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaPrO. 
          MaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaPro. 
          MaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaPro.</p>
        <p class="lead"><button class="btn btn-default">Read More</button></p>
        <p class="pull-right"><span class="label label-default">keyword</span> <span class="label label-default">tag</span> <span class="label label-default">post</span></p>
        <ul class="list-inline"><li><a href="#">2 Days Ago</a></li><li><a href="#"><i class="glyphicon glyphicon-comment"></i> 2 Comments</a></li><li><a href="#"><i class="glyphicon glyphicon-share"></i> 14 Shares</a></li></ul>
      </div>
    </div>
    <hr>
    <div class="row">
      <div class="col-xs-12">
        <h2>Document List</h2>
        <c:forEach items="${modelMapLanding.maProDocumentList}" var="element"> 
  <p><a>${element.fileName}</a></p><br>
</c:forEach>
        
        <p class="lead"><button class="btn btn-default">Read More</button></p>
        <p class="pull-right"><span class="label label-default">keyword</span> <span class="label label-default">tag</span> <span class="label label-default">post</span></p>
        <ul class="list-inline"><li><a href="#">4 Days Ago</a></li><li><a href="#"><i class="glyphicon glyphicon-comment"></i> 7 Comments</a></li><li><a href="#"><i class="glyphicon glyphicon-share"></i> 56 Shares</a></li></ul>
      </div>
    </div>
    <hr>      
    <div class="row">
      <div class="col-xs-12">
        <h2>MaPro Header</h2>
        <p>MaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaPro. 
          MaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaPro. MaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaPro. 
          MaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaPro. 
          MaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaProMaPro.</p>
        <p class="lead"><button class="btn btn-default">Read More</button></p>
        <p class="pull-right"><span class="label label-default">keyword</span> <span class="label label-default">tag</span> <span class="label label-default">post</span></p>
        <ul class="list-inline"><li><a href="#">1 Week Ago</a></li><li><a href="#"><i class="glyphicon glyphicon-comment"></i> 4 Comments</a></li><li><a href="#"><i class="glyphicon glyphicon-share"></i> 34 Shares</a></li></ul>
      </div>
    </div>
    <hr>
  </div><!--/center-->

  <!--right-->
  <div class="col-sm-3">
        <h2>Side</h2>
    	<div class="panel panel-default">
         	<div class="panel-heading">MaPro Title</div>
         	<div class="panel-body">MaPro Any Content Goes Here...</div>
        </div>
        <hr>
        <div class="panel panel-default">
         	<div class="panel-heading">MaPro Title</div>
         	<div class="panel-body">MaPro Any Content Goes Here..</div>
        </div>
        <hr>
        <div class="panel panel-default">
         	<div class="panel-heading">MaPro Title</div>
         	<div class="panel-body">MaPro Any Content Goes Here..</div>
        </div>
        <hr>
        <div class="panel panel-default">
         	<div class="panel-heading">MaPro Title</div>
         	<div class="panel-body">MaPro Any Content Goes Here..</div>
        </div>
        <hr>
  </div><!--/right-->
  <hr>
</div><!--/container-fluid-->
	<!-- script references -->
		
		<script	type='text/javascript' src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</body>
</html>