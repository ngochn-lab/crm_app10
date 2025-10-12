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
    <title>Pixel Admin - ${user != null ? 'Cập nhật' : 'Thêm mới'} thành viên</title>
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
                        <h4 class="page-title">${user != null ? 'Cập nhật' : 'Thêm mới'} thành viên</h4>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-2 col-12"></div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">
                            <form class="form-horizontal form-material" method="post" 
                                  action="${pageContext.request.contextPath}/${user != null ? 'user-edit' : 'user-add'}">
                                <c:if test="${user != null}">
                                    <input type="hidden" name="id" value="${user.id}">
                                </c:if>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Họ tên</label>
                                    <div class="col-md-12">
                                        <input type="text" name="fullname" value="${user.fullname}" required
                                               placeholder="Nhập họ tên" class="form-control form-control-line">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Email</label>
                                    <div class="col-md-12">
                                        <input type="email" name="email" value="${user.email}" required
                                               placeholder="Nhập email" class="form-control form-control-line">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Mật khẩu</label>
                                    <div class="col-md-12">
                                        <input type="password" name="password" ${user == null ? 'required' : ''}
                                               placeholder="${user != null ? 'Để trống nếu không đổi mật khẩu' : 'Nhập mật khẩu'}" 
                                               class="form-control form-control-line">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Avatar URL</label>
                                    <div class="col-md-12">
                                        <input type="text" name="avatar" value="${user.avatar}"
                                               placeholder="URL hình đại diện (tùy chọn)" class="form-control form-control-line">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Quyền</label>
                                    <div class="col-md-12">
                                        <select name="roleId" class="form-control form-control-line" required>
                                            <option value="">-- Chọn quyền --</option>
                                            <c:forEach var="role" items="${listRoles}">
                                                <option value="${role.id}" ${user != null && user.roleId == role.id ? 'selected' : ''}>
                                                    ${role.name} - ${role.description}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                
                                <c:if test="${param.error != null}">
                                    <div class="alert alert-danger">
                                        Có lỗi xảy ra khi ${user != null ? 'cập nhật' : 'thêm'} thành viên!
                                    </div>
                                </c:if>
                                
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success">
                                            ${user != null ? 'Cập nhật' : 'Thêm mới'}
                                        </button>
                                        <a href="${pageContext.request.contextPath}/user-table" class="btn btn-primary">Quay lại</a>
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
