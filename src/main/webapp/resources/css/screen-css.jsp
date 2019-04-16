<%@page contentType="text/css" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

/* begin structural code */


body {
	font-family: 'open sans', sans-serif;
	}

a {
	color: #000;
	text-decoration: underline;
}

a:hover, a:focus {
    color: #000;
    text-decoration: none;
}	

a.logo img {
	max-width: 300px;
    padding-right: 25px;
    }
    

/* end structural code */

/* begin header */

header.navbar {
	background-color: white;
	background-image: none;
	border-bottom: 10px solid black;
}
	

/* end header */

/* begin nav */

.navbar-nav li a {
    color: black;
    background: #fff;
    font-size: 1.4em;
    margin-top: 45px;
}

.navbar-default .navbar-nav>li>a {
	color: black;
}

/* end nav */
	 
/* begin footer */
	 
footer {
	margin-top: 20px;
	border-top: 10px solid black;
}

.footer-logos img {
    padding: 15px;
}

footer img {
    max-width: 100%;
    height: auto !important;
}

.footer-logos {
    text-align: center;
}
	
/* end footer */	 

/* begin page styles */

article a,
aside a {
	text-decoration: underline;
	}

article a.btn {
	text-decoration: none;
	}
	
article .panel-heading .panel-title a {
	text-decoration: none;
	}

h1 {
  font-size: 2em;
  color: #3d3d3d;
  }	

h2 {
  font-size: 1.7em;
  color: #3d3d3d;
  }	
	
h3 {
  font-size: 1.5em;
  color: #3d3d3d;
  }	

h4 {
  font-size: 1.2em;
  color: #3d3d3d;
  }	
  
/* end page styles */

/* skip navigation link for accessibility */

#skip a, #skip a:hover, #skip a:visited, .skipNav a, .skipNav a:hover, .skipNav a:visited  { 
	position:absolute; 
	left:0px; 
	top:-500px; 
	width:1px; 
	height:1px; 
	overflow:hidden;
} 

#skip a:active, #skip a:focus, .skipNav a:active, .skipNav a:focus {
	position:static; 
	width:auto; 
	height:auto; 
}
	
/* end skip nav */

	