# CRM Application - Authentication & Authorization Guide

## 📋 Overview

This CRM application implements a comprehensive role-based authentication and authorization system with three user roles: **ADMIN**, **LEADER**, and **USER**.

## 🔐 Authentication System

### Login Process
1. Users access `/login` page
2. Enter email and password
3. System validates credentials via `UserServices.login()`
4. On success:
   - User info stored in HttpSession
   - Role-based redirect to appropriate page
   - Optional "Remember Me" cookie (7 days)

### Session Attributes
```java
session.setAttribute("userId", user.getId());
session.setAttribute("userEmail", user.getEmail());
session.setAttribute("userFullname", user.getFullname());
session.setAttribute("roleId", user.getRoleId());
session.setAttribute("roleDescription", user.getRoleDescription());
```

### Logout Process
- Access `/logout` endpoint
- Session invalidated
- Cookies cleared
- Redirect to login page

## 👥 User Roles & Permissions

### Role IDs
- **ADMIN**: roleId = 1
- **LEADER**: roleId = 2  
- **USER**: roleId = 3

### Permission Matrix

| Feature | ADMIN | LEADER | USER |
|---------|-------|--------|------|
| **User Management** |
| View Users | ✅ | ❌ | ❌ |
| Add/Edit/Delete Users | ✅ | ❌ | ❌ |
| Assign Roles | ✅ | ❌ | ❌ |
| **Role Management** |
| View Roles | ✅ | ❌ | ❌ |
| Add/Edit/Delete Roles | ✅ | ❌ | ❌ |
| **Project Management** |
| View All Projects | ✅ | ✅ | ❌ |
| Create Project | ✅ | ✅ | ❌ |
| Edit Project | ✅ | ✅ | ❌ |
| Delete Project | ✅ | ✅ | ❌ |
| View Project Details | ✅ | ✅ | ❌ |
| **Task Management** |
| View All Tasks | ✅ | ✅ | ❌ |
| View Own Tasks | ✅ | ✅ | ✅ |
| Create Task | ✅ | ✅ | ❌ |
| Edit Task | ✅ | ✅ | ❌ |
| Delete Task | ✅ | ✅ | ❌ |
| Update Task Status | ✅ | ✅ | ✅ (own only) |
| **Statistics & Reports** |
| View Dashboard | ✅ | ✅ | ✅ |
| View All Statistics | ✅ | ❌ | ❌ |
| View Project Statistics | ✅ | ✅ | ❌ |
| View Personal Statistics | ✅ | ✅ | ✅ |
| **Profile** |
| View Own Profile | ✅ | ✅ | ✅ |
| Edit Own Profile | ✅ | ✅ | ✅ |
| View Other Profiles | ✅ | ✅ | ❌ |

## 🛡️ Security Filters

### AuthenticationFilter
- **URL Pattern**: `/*` (all requests)
- **Purpose**: Verify user is logged in
- **Behavior**:
  - Checks session for `userId` and `roleId`
  - Allows access to login page and static resources
  - Redirects unauthenticated users to `/login`

### AuthorizationFilter
- **URL Pattern**: `/*` (all requests)
- **Purpose**: Enforce role-based access control
- **Behavior**:
  - Checks user's roleId against required permissions
  - Blocks unauthorized access
  - Redirects to dashboard with error message

## 🎯 URL Access Control

### Admin Only URLs
```
/user-table
/user-add
/user-edit
/user-delete
/role-table
/role-add
/role-edit
/role-delete
```

### Admin & Leader URLs
```
/groupwork
/groupwork-add
/groupwork-edit
/groupwork-delete
/groupwork-details
/task-add
/task-edit
/task-delete
```

### All Authenticated Users
```
/dashboard
/
/task (filtered by role)
/task-update-status
/profile
/profile-edit
/user-details
```

### Public URLs (No Authentication)
```
/login
/plugins/**
/bootstrap/**
/css/**
/js/**
*.css, *.js, *.png, *.jpg
```

## 🎨 UI Role-Based Features

### Sidebar Menu
- Dynamically shows/hides menu items based on `sessionScope.roleId`
- ADMIN sees all menus
- LEADER sees project and task menus
- USER sees only task and profile menus

### Action Buttons
- Add/Edit/Delete buttons shown only to authorized roles
- USER sees "Chỉ xem" (View Only) instead of action buttons

### Example JSP Code
```jsp
<!-- Show menu only for ADMIN -->
<c:if test="${sessionScope.roleId == 1}">
    <li><a href="/user-table">Thành viên</a></li>
</c:if>

<!-- Show menu for ADMIN and LEADER -->
<c:if test="${sessionScope.roleId == 1 || sessionScope.roleId == 2}">
    <li><a href="/groupwork">Dự án</a></li>
</c:if>
```

## 📝 Testing the System

### Create Test Users

```sql
-- ADMIN user
INSERT INTO users (email, password, fullname, role_id) 
VALUES ('admin@crm.com', 'admin123', 'System Administrator', 1);

-- LEADER user
INSERT INTO users (email, password, fullname, role_id)
VALUES ('leader@crm.com', 'leader123', 'Project Manager', 2);

-- USER (regular employee)
INSERT INTO users (email, password, fullname, role_id)
VALUES ('user@crm.com', 'user123', 'John Doe', 3);
```

### Test Scenarios

#### 1. ADMIN Login
- Login with admin@crm.com / admin123
- Should redirect to `/dashboard`
- Can access all menus and features
- Can manage users, roles, projects, and tasks

#### 2. LEADER Login
- Login with leader@crm.com / leader123
- Should redirect to `/groupwork`
- Can manage projects and tasks
- Cannot access user/role management
- Attempting to access `/user-table` redirects to dashboard with error

#### 3. USER Login
- Login with user@crm.com / user123
- Should redirect to `/task`
- Can only view own tasks
- Cannot create/edit/delete tasks
- Cannot access project management
- Attempting to access `/groupwork` redirects to dashboard with error

#### 4. Unauthorized Access
- Try accessing `/user-table` as LEADER or USER
- Should redirect to dashboard with error message
- Error message: "Bạn không có quyền truy cập chức năng này!"

#### 5. Session Timeout
- Login and wait for session timeout
- Any request should redirect to login page
- Must login again to continue

## 🔧 Implementation Details

### Controllers
All controllers should check session for user info:
```java
HttpSession session = req.getSession();
Integer userId = (Integer) session.getAttribute("userId");
Integer roleId = (Integer) session.getAttribute("roleId");
```

### Data Filtering
Controllers filter data based on role:
```java
if (roleId == 1) { // ADMIN
    listTasks = taskServices.getAllTasks();
} else if (roleId == 2) { // LEADER
    listTasks = taskServices.getAllTasks(); // Or filter by managed projects
} else { // USER
    listTasks = taskServices.getTasksByUserId(userId);
}
```

### Error Handling
Unauthorized access stores error in session:
```java
session.setAttribute("errorMessage", "Bạn không có quyền truy cập chức năng này!");
resp.sendRedirect(req.getContextPath() + "/dashboard");
```

## 🚀 Deployment Checklist

- [ ] Database created with correct schema
- [ ] Test users created for each role
- [ ] MySQL connection configured in `MySQLConfig.java`
- [ ] Application deployed to servlet container
- [ ] Test login for each role
- [ ] Verify role-based access control
- [ ] Test unauthorized access attempts
- [ ] Verify logout functionality
- [ ] Test Remember Me feature
- [ ] Check session timeout behavior

## 📚 Related Files

### Filters
- `/src/main/java/filter/AuthenticationFilter.java`
- `/src/main/java/filter/AuthorizationFilter.java`

### Controllers
- `/src/main/java/crm_app10/controller/LoginController.java`
- `/src/main/java/crm_app10/controller/LogoutController.java`

### JSP Pages
- `/src/main/webapp/login.jsp`
- `/src/main/webapp/navbar.jsp`
- `/src/main/webapp/sidebar.jsp`

### Services
- `/src/main/java/crm_app10/services/UserServices.java`

## 🔒 Security Best Practices

1. **Password Storage**: Currently storing plain text - **SHOULD** use bcrypt/hash in production
2. **Session Management**: Using HttpSession - configure timeout in web.xml
3. **SQL Injection**: Using PreparedStatement - already protected
4. **XSS Protection**: Sanitize user input in JSP pages
5. **HTTPS**: Use HTTPS in production environment
6. **CSRF Protection**: Consider adding CSRF tokens for forms

## 📞 Support

For issues or questions about the authentication system, refer to:
- Project documentation
- Database schema: `/src/main/resources/crm_app_schema.sql`
- Configuration: `/src/main/java/config/MySQLConfig.java`
