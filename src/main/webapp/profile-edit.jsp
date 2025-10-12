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
    <title>Pixel Admin - Chỉnh sửa thông tin</title>
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
                        <h4 class="page-title">Chỉnh sửa thông tin cá nhân</h4>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-2 col-12"></div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">
                            <form class="form-horizontal form-material" method="post" 
                                  action="${pageContext.request.contextPath}/profile-edit">
                                
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
                                    <label class="col-md-12">Mật khẩu mới</label>
                                    <div class="col-md-12">
                                        <input type="password" name="password"
                                               placeholder="Để trống nếu không đổi mật khẩu" 
                                               class="form-control form-control-line">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Xác nhận mật khẩu</label>
                                    <div class="col-md-12">
                                        <input type="password" name="confirmPassword"
                                               placeholder="Nhập lại mật khẩu mới" 
                                               class="form-control form-control-line">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Avatar URL</label>
                                    <div class="col-md-12">
                                        <input type="text" name="avatar" value="${user.avatar}"
                                               placeholder="URL hình đại diện (tùy chọn)" 
                                               class="form-control form-control-line">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-12">Quyền</label>
                                    <div class="col-md-12">
                                        <select name="roleId" class="form-control form-control-line" disabled>
                                            <c:forEach var="role" items="${listRoles}">
                                                <option value="${role.id}" ${user.roleId == role.id ? 'selected' : ''}>
                                                    ${role.name} - ${role.description}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" name="roleId" value="${user.roleId}">
                                        <small class="form-text text-muted">Quyền không thể tự thay đổi</small>
                                    </div>
                                </div>
                                
                                <c:if test="${param.error != null}">
                                    <div class="alert alert-danger">
                                        Có lỗi xảy ra khi cập nhật thông tin!
                                    </div>
                                </c:if>
                                
                                <c:if test="${param.success != null}">
                                    <div class="alert alert-success">
                                        Cập nhật thông tin thành công!
                                    </div>
                                </c:if>
                                
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success">Cập nhật</button>
                                        <a href="${pageContext.request.contextPath}/profile" class="btn btn-primary">Quay lại</a>
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
