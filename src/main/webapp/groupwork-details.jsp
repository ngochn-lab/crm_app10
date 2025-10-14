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
    <title>Pixel Admin - Chi tiết dự án</title>
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
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
                        <h4 class="page-title">Chi tiết dự án</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="dashboard">Dashboard</a></li>
                            <li><a href="groupwork">Danh sách dự án</a></li>
                            <li class="active">Chi tiết</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- .row -->
                <c:set var="totalTasks" value="${notStarted + inProgress + completed}" />
                <c:set var="pctNS" value="${totalTasks > 0 ? (notStarted * 100.0) / totalTasks : 0}" />
                <c:set var="pctIP" value="${totalTasks > 0 ? (inProgress * 100.0) / totalTasks : 0}" />
                <c:set var="pctCP" value="${totalTasks > 0 ? (completed * 100.0) / totalTasks : 0}" />
                <fmt:formatNumber value="${pctNS}" maxFractionDigits="0" var="pctNSr"/>
                <fmt:formatNumber value="${pctIP}" maxFractionDigits="0" var="pctIPr"/>
                <fmt:formatNumber value="${pctCP}" maxFractionDigits="0" var="pctCPr"/>
                <div class="row">
                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                        <div class="white-box">
                            <div class="col-in row">
                                <div class="col-md-6 col-sm-6 col-xs-6">
                                    <i data-icon="E" class="linea-icon linea-basic"></i>
                                    <h5 class="text-muted vb">CHƯA BẮT ĐẦU</h5>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-6">
                                    <h3 class="counter text-right m-t-15 text-danger">${pctNSr}%</h3>
                                </div>
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="progress">
                                        <div class="progress-bar progress-bar-danger" role="progressbar"
                                             aria-valuemin="0" aria-valuemax="100" style="width: ${pctNSr}%">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                        <div class="white-box">
                            <div class="col-in row">
                                <div class="col-md-6 col-sm-6 col-xs-6">
                                    <i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
                                    <h5 class="text-muted vb">ĐANG THỰC HIỆN</h5>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-6">
                                    <h3 class="counter text-right m-t-15 text-megna">${pctIPr}%</h3>
                                </div>
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="progress">
                                        <div class="progress-bar progress-bar-megna" role="progressbar"
                                             aria-valuemin="0" aria-valuemax="100" style="width: ${pctIPr}%">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                        <div class="white-box">
                            <div class="col-in row">
                                <div class="col-md-6 col-sm-6 col-xs-6">
                                    <i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
                                    <h5 class="text-muted vb">HOÀN THÀNH</h5>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-6">
                                    <h3 class="counter text-right m-t-15 text-primary">${pctCPr}%</h3>
                                </div>
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="progress">
                                        <div class="progress-bar progress-bar-primary" role="progressbar"
                                             aria-valuemin="0" aria-valuemax="100" style="width: ${pctCPr}%">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
                <!-- BEGIN THỐNG KÊ -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title">Thông tin dự án</h3>
                            <div class="table-responsive">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td width="200"><strong>Tên dự án:</strong></td>
                                            <td>${project.name}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>Ngày bắt đầu:</strong></td>
                                            <td>${project.startDate}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>Ngày kết thúc:</strong></td>
                                            <td>${project.endDate}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>Người quản lý:</strong></td>
                                            <td>${project.userFullname}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END THỐNG KÊ -->
                
                <!-- BEGIN DANH SÁCH CÔNG VIỆC (NHÓM THEO THÀNH VIÊN) -->
                <h3 class="box-title">Danh sách công việc theo thành viên</h3>
                <c:forEach var="assignee" items="${assignees}">
                    <div class="row">
                        <div class="col-xs-12">
                            <a href="#" class="group-title">
                                <img width="30" src="${not empty assignee.avatar ? assignee.avatar : 'plugins/images/users/genu.jpg'}" class="img-circle" />
                                <span>${assignee.fullname}</span>
                            </a>
                        </div>
                        <div class="col-md-4">
                            <div class="white-box">
                                <h3 class="box-title">Chưa thực hiện</h3>
                                <div class="message-center">
                                    <c:forEach var="task" items="${tasksByUserNS[assignee.id]}">
                                        <a href="#">
                                            <div class="mail-contnet">
                                                <h5>${task.name}</h5>
                                                <span class="time">Bắt đầu: ${task.startDate}</span>
                                                <span class="time">Kết thúc: ${task.endDate}</span>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="white-box">
                                <h3 class="box-title">Đang thực hiện</h3>
                                <div class="message-center">
                                    <c:forEach var="task" items="${tasksByUserIP[assignee.id]}">
                                        <a href="#">
                                            <div class="mail-contnet">
                                                <h5>${task.name}</h5>
                                                <span class="time">Bắt đầu: ${task.startDate}</span>
                                                <span class="time">Kết thúc: ${task.endDate}</span>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="white-box">
                                <h3 class="box-title">Đã hoàn thành</h3>
                                <div class="message-center">
                                    <c:forEach var="task" items="${tasksByUserCP[assignee.id]}">
                                        <a href="#">
                                            <div class="mail-contnet">
                                                <h5>${task.name}</h5>
                                                <span class="time">Bắt đầu: ${task.startDate}</span>
                                                <span class="time">Kết thúc: ${task.endDate}</span>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <!-- END DANH SÁCH CÔNG VIỆC (NHÓM THEO THÀNH VIÊN) -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <!--Wave Effects -->
    <script src="js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="js/custom.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable();
        });
        
        function updateTaskStatus(taskId, statusId, projectId) {
            $.post('${pageContext.request.contextPath}/task-update-status', {
                taskId: taskId,
                statusId: statusId,
                projectId: projectId
            }, function(data) {
                location.reload();
            });
        }
    </script>
</body>

</html>
