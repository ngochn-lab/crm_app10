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
    <title>Pixel Admin - Công việc</title>
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
                        <h4 class="page-title">Danh sách công việc</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                        <!-- Chỉ ADMIN và LEADER có thể thêm task -->
                        <c:if test="${sessionScope.roleId == 1 || sessionScope.roleId == 2}">
                            <a href="task-add" class="btn btn-sm btn-success">Thêm mới</a>
                        </c:if>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /row -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="table-responsive">
                                <table class="table" id="example">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên Công Việc</th>
                                            <th>Dự Án</th>
                                            <th>Người Thực Hiện</th>
                                            <th>Ngày Bắt Đầu</th>
                                            <th>Ngày Kết Thúc</th>
                                            <th>Trạng Thái</th>
                                            <th>Hành Động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="task" items="${listTasks}" varStatus="loop">
                                            <tr>
                                                <td>${loop.index + 1}</td>
                                                <td>${task.name}</td>
                                                <td>${task.projectName}</td>
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
                                                    <!-- ADMIN và LEADER có thể sửa/xóa task -->
                                                    <c:if test="${sessionScope.roleId == 1 || sessionScope.roleId == 2}">
                                                        <a href="task-edit?id=${task.id}" class="btn btn-sm btn-primary">Sửa</a>
                                                        <a href="task-delete?id=${task.id}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc muốn xóa công việc này?')">Xóa</a>
                                                    </c:if>
                                                    <!-- USER chỉ có thể xem -->
                                                    <c:if test="${sessionScope.roleId == 3}">
                                                        <c:choose>                                
                                                            <c:when test="${task.userId == sessionScope.userId}">
                                                                <select class="form-control form-control-sm" onchange="updateTaskStatus(${task.id}, this.value)">
                                                                    <option value="1" ${task.statusId == 1 ? 'selected' : ''}>Chưa thực hiện</option>
                                                                    <option value="2" ${task.statusId == 2 ? 'selected' : ''}>Đang thực hiện</option>
                                                                    <option value="3" ${task.statusId == 3 ? 'selected' : ''}>Đã hoàn thành</option>
                                                                </select>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="text-muted">Chỉ xem</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
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
        function updateTaskStatus(taskId, statusId) {
            $.post('${pageContext.request.contextPath}/task-update-status', {
                taskId: taskId,
                statusId: statusId
            }, function(data) {
                location.reload();
            });
        }
    </script>
</body>

</html>
