<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse slimscrollsidebar">
        <ul class="nav" id="side-menu">
            <li style="padding: 10px 0 0;">
                <a href="dashboard" class="waves-effect"><i class="fa fa-clock-o fa-fw"
                        aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
            </li>
            
            <!-- Menu chỉ cho ADMIN (roleId = 1) -->
            <c:if test="${sessionScope.roleId == 1}">
                <li>
                    <a href="user-table" class="waves-effect"><i class="fa fa-user fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
                </li>
                <li>
                    <a href="role-table" class="waves-effect"><i class="fa fa-modx fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
                </li>
            </c:if>
            
            <!-- Menu cho ADMIN và LEADER (roleId = 1 hoặc 2) -->
            <c:if test="${sessionScope.roleId == 1 || sessionScope.roleId == 2}">
                <li>
                    <a href="groupwork" class="waves-effect"><i class="fa fa-table fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
                </li>
            </c:if>
            
            <!-- Menu cho tất cả user đã đăng nhập -->
            <li>
                <a href="task" class="waves-effect"><i class="fa fa-table fa-fw"
                        aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
            </li>
            <li>
                <a href="profile" class="waves-effect"><i class="fa fa-user-circle fa-fw"
                        aria-hidden="true"></i><span class="hide-menu">Thông tin cá nhân</span></a>
            </li>
        </ul>
    </div>
</div>
