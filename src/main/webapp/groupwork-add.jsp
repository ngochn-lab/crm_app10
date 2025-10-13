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
    <title>Pixel Admin - ${project != null ? 'Cập nhật' : 'Thêm mới'} dự án</title>
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
                        <h4 class="page-title">${project != null ? 'Cập nhật' : 'Thêm mới'} dự án</h4>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-2 col-12"></div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">
                            <form class="form-horizontal form-material" method="post" 
                                  action="${project != null ? 'groupwork-edit' : 'groupwork-add'}">
                                <c:if test="${project != null}">
                                    <input type="hidden" name="id" value="${project.id}">
                                </c:if>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Tên dự án</label>
                                    <div class="col-md-12">
                                        <input type="text" name="name" value="${project.name}" required
                                               placeholder="Nhập tên dự án" class="form-control form-control-line">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Ngày bắt đầu</label>
                                    <div class="col-md-12">
                                        <input type="date" name="startDate" value="${project.startDate}" required
                                               class="form-control form-control-line">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Ngày kết thúc</label>
                                    <div class="col-md-12">
                                        <input type="date" name="endDate" value="${project.endDate}" required
                                               class="form-control form-control-line">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Người quản lý</label>
                                    <div class="col-md-12">
                                        <select name="userId" class="form-control form-control-line" required>
                                            <option value="">-- Chọn người quản lý --</option>
                                            <c:forEach var="user" items="${listUsers}">
                                                <option value="${user.id}" ${project != null && project.userId == user.id ? 'selected' : ''}>
                                                    ${user.fullname} (${user.email})
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                
                                <c:if test="${param.error != null}">
                                    <div class="alert alert-danger">
                                        Có lỗi xảy ra khi ${project != null ? 'cập nhật' : 'thêm'} dự án!
                                    </div>
                                </c:if>
                                
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success">
                                            ${project != null ? 'Cập nhật' : 'Thêm mới'}
                                        </button>
                                        <a href="groupwork" class="btn btn-primary">Quay lại</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-2 col-12"></div>
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
    <script src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="js/custom.min.js"></script>
</body>

</html>
