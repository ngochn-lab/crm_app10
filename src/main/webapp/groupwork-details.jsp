<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
                            <li><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
                            <li><a href="${pageContext.request.contextPath}/groupwork">Danh sách dự án</a></li>
                            <li class="active">Chi tiết</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- .row -->
                <div class="row">
                    <div class="col-lg-4 col-sm-6 col-md-6 col-xs-12">
                        <div class="white-box">
                            <h3 class="box-title">CHƯA BẮT ĐẦU</h3>
                            <div class="text-right">
                                <h2><sup><i class="ti-arrow-down text-danger"></i></sup> ${notStarted}</h2>
                            </div>
                            <div class="progress m-b-0">
                                <div class="progress-bar progress-bar-danger" role="progressbar" 
                                     aria-valuenow="${notStarted}" aria-valuemin="0" aria-valuemax="100" 
                                     style="width: ${(notStarted * 100) / (notStarted + inProgress + completed)}%">
                                    <span class="sr-only">${(notStarted * 100) / (notStarted + inProgress + completed)}% Complete</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6 col-md-6 col-xs-12">
                        <div class="white-box">
                            <h3 class="box-title">ĐANG THỰC HIỆN</h3>
                            <div class="text-right">
                                <h2><sup><i class="ti-arrow-up text-warning"></i></sup> ${inProgress}</h2>
                            </div>
                            <div class="progress m-b-0">
                                <div class="progress-bar progress-bar-warning" role="progressbar" 
                                     aria-valuenow="${inProgress}" aria-valuemin="0" aria-valuemax="100" 
                                     style="width: ${(inProgress * 100) / (notStarted + inProgress + completed)}%">
                                    <span class="sr-only">${(inProgress * 100) / (notStarted + inProgress + completed)}% Complete</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6 col-md-6 col-xs-12">
                        <div class="white-box">
                            <h3 class="box-title">HOÀN THÀNH</h3>
                            <div class="text-right">
                                <h2><sup><i class="ti-arrow-up text-success"></i></sup> ${completed}</h2>
                            </div>
                            <div class="progress m-b-0">
                                <div class="progress-bar progress-bar-success" role="progressbar" 
                                     aria-valuenow="${completed}" aria-valuemin="0" aria-valuemax="100" 
                                     style="width: ${(completed * 100) / (notStarted + inProgress + completed)}%">
                                    <span class="sr-only">${(completed * 100) / (notStarted + inProgress + completed)}% Complete</span>
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
                
                <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <h3 class="box-title">Danh sách công việc</h3>
                            <div class="table-responsive">
                                <table class="table" id="example">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên Công Việc</th>
                                            <th>Người Thực Hiện</th>
                                            <th>Ngày Bắt Đầu</th>
                                            <th>Ngày Kết Thúc</th>
                                            <th>Trạng Thái</th>
                                            <th>Hành Động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="task" items="${projectTasks}" varStatus="loop">
                                            <tr>
                                                <td>${loop.index + 1}</td>
                                                <td>${task.name}</td>
                                                <td>${task.userFullname}</td>
                                                <td>${task.startDate}</td>
                                                <td>${task.endDate}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${task.statusId == 1}">
                                                            <span class="label label-danger">${task.statusName}</span>
                                                        </c:when>
                                                        <c:when test="${task.statusId == 2}">
                                                            <span class="label label-warning">${task.statusName}</span>
                                                        </c:when>
                                                        <c:when test="${task.statusId == 3}">
                                                            <span class="label label-success">${task.statusName}</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="label label-default">${task.statusName}</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <select class="form-control form-control-sm" onchange="updateTaskStatus(${task.id}, this.value, ${project.id})">
                                                        <option value="1" ${task.statusId == 1 ? 'selected' : ''}>Chưa thực hiện</option>
                                                        <option value="2" ${task.statusId == 2 ? 'selected' : ''}>Đang thực hiện</option>
                                                        <option value="3" ${task.statusId == 3 ? 'selected' : ''}>Đã hoàn thành</option>
                                                    </select>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <a href="${pageContext.request.contextPath}/groupwork" class="btn btn-primary">Quay lại</a>
                        </div>
                    </div>
                </div>
                <!-- END DANH SÁCH CÔNG VIỆC -->
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
