<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <title>Pixel Admin - Chi tiết thành viên</title>
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <link rel="stylesheet" href="./css/custom.css">
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <jsp:include page="navbar.jsp" />
        <!-- Left navbar-header -->
        <jsp:include page="sidebar.jsp" />
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Chi tiết thành viên</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="dashboard">Dashboard</a></li>
                            <li><a href="user-table">Danh sách thành viên</a></li>
                            <li class="active">Chi tiết</li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-4 col-xs-12">
                        <div class="white-box">
                            <div class="user-bg"> 
                                <img width="100%" alt="user" src="plugins/images/large/img1.jpg">
                                <div class="overlay-box">
                                    <div class="user-content">
                                        <a href="javascript:void(0)">
                                            <img src="${user.avatar != null && !user.avatar.isEmpty() ? user.avatar : 'plugins/images/users/genu.jpg'}"
                                                 class="thumb-lg img-circle" alt="img">
                                        <h4 class="text-white">${user.fullname}</h4>
                                        <h5 class="text-white">${user.email}</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8 col-xs-12">
                        <!-- BEGIN THỐNG KÊ -->
                        <c:set var="totalTasks" value="${notStarted + inProgress + completed}" />
                        <c:set var="percentNotStarted" value="${totalTasks > 0 ? (notStarted * 100.0) / totalTasks : 0}" />
                        <c:set var="percentInProgress" value="${totalTasks > 0 ? (inProgress * 100.0) / totalTasks : 0}" />
                        <c:set var="percentCompleted" value="${totalTasks > 0 ? (completed * 100.0) / totalTasks : 0}" />
                        <fmt:formatNumber value="${percentNotStarted}" maxFractionDigits="0" var="percentNotStartedRounded"/>
                        <fmt:formatNumber value="${percentInProgress}" maxFractionDigits="0" var="percentInProgressRounded"/>
                        <fmt:formatNumber value="${percentCompleted}" maxFractionDigits="0" var="percentCompletedRounded"/>
                        <div class="row">
                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <div class="white-box">
                                    <div class="col-in row">
                                        <div class="col-xs-12">
                                            <h3 class="counter text-right m-t-15 text-danger">${percentNotStartedRounded}%</h3>
                                        </div>
                                        <div class="col-xs-12">
                                            <i data-icon="E" class="linea-icon linea-basic"></i>
                                            <h5 class="text-muted vb text-center">CHƯA BẮT ĐẦU</h5>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="progress">
                                                <div class="progress-bar progress-bar-danger" role="progressbar"
                                                     aria-valuemin="0" aria-valuemax="100"
                                                     style="width: ${percentNotStartedRounded}%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <div class="white-box">
                                    <div class="col-in row">
                                        <div class="col-xs-12">
                                            <h3 class="counter text-right m-t-15 text-megna">${percentInProgressRounded}%</h3>
                                        </div>
                                        <div class="col-xs-12">
                                            <i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
                                            <h5 class="text-muted vb text-center">ĐANG THỰC HIỆN</h5>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="progress">
                                                <div class="progress-bar progress-bar-megna" role="progressbar"
                                                     aria-valuemin="0" aria-valuemax="100"
                                                     style="width: ${percentInProgressRounded}%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <div class="white-box">
                                    <div class="col-in row">
                                        <div class="col-xs-12">
                                            <h3 class="counter text-right m-t-15 text-primary">${percentCompletedRounded}%</h3>
                                        </div>
                                        <div class="col-xs-12">
                                            <i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
                                            <h5 class="text-muted vb text-center">HOÀN THÀNH</h5>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="progress">
                                                <div class="progress-bar progress-bar-primary" role="progressbar"
                                                     aria-valuemin="0" aria-valuemax="100"
                                                     style="width: ${percentCompletedRounded}%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END THỐNG KÊ -->
                    </div>
                </div>
                <!-- /.row -->
                <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
                <h4>DANH SÁCH CÔNG VIỆC</h4>
                <div class="row">
                    <div class="col-md-4">
                        <div class="white-box">
                            <h3 class="box-title">Chưa thực hiện</h3>
                            <div class="message-center">
                                <c:forEach var="task" items="${userTasks}">
                                    <c:if test="${task.statusId == 1}">
                                        <a href="#">
                                            <div class="mail-contnet">
                                                <h5>${task.name}</h5>
                                                <span class="time">Bắt đầu: ${task.startDate}</span>
                                                <span class="time">Kết thúc: ${task.endDate}</span>
                                            </div>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="white-box">
                            <h3 class="box-title">Đang thực hiện</h3>
                            <div class="message-center">
                                <c:forEach var="task" items="${userTasks}">
                                    <c:if test="${task.statusId == 2}">
                                        <a href="#">
                                            <div class="mail-contnet">
                                                <h5>${task.name}</h5>
                                                <span class="time">Bắt đầu: ${task.startDate}</span>
                                                <span class="time">Kết thúc: ${task.endDate}</span>
                                            </div>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="white-box">
                            <h3 class="box-title">Đã hoàn thành</h3>
                            <div class="message-center">
                                <c:forEach var="task" items="${userTasks}">
                                    <c:if test="${task.statusId == 3}">
                                        <a href="#">
                                            <div class="mail-contnet">
                                                <h5>${task.name}</h5>
                                                <span class="time">Bắt đầu: ${task.startDate}</span>
                                                <span class="time">Kết thúc: ${task.endDate}</span>
                                            </div>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END DANH SÁCH CÔNG VIỆC -->
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="js/custom.min.js"></script>
</body>

</html>
