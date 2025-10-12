-- Create database
CREATE DATABASE IF NOT EXISTS crm_app;
USE crm_app;

-- Drop existing tables if they exist (in reverse order of dependencies)
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS roles;

-- Create roles table
CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);

-- Create status table
CREATE TABLE IF NOT EXISTS status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    avatar VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Create projects table
CREATE TABLE IF NOT EXISTS projects (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create tasks table
CREATE TABLE IF NOT EXISTS tasks (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    user_id INT NOT NULL,
    project_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES status(id) ON DELETE CASCADE
);

-- Insert initial data for roles
INSERT INTO roles(name, description) VALUES 
    ('ROLE_ADMIN', 'Quản trị hệ thống'),
    ('ROLE_MANAGER', 'Quản lý'),
    ('ROLE_USER', 'Nhân viên');

-- Insert initial data for status
INSERT INTO status(name) VALUES 
    ('Chưa thực hiện'),
    ('Đang thực hiện'),
    ('Đã hoàn thành');

-- Insert sample users (optional - uncomment if needed)
INSERT INTO users(email, password, fullname, avatar, role_id) VALUES
    ('admin@gmail.com', '123456', 'Nguyễn Văn Admin', NULL, 1),
    ('manager@gmail.com', '123456', 'Trần Thị Manager', NULL, 2),
    ('nguyenvana@gmail.com', '123456', 'Nguyễn Văn A', NULL, 3);

-- Insert sample projects (optional - uncomment if needed)
INSERT INTO projects(name, start_date, end_date, user_id) VALUES
    ('CRM Application', '2024-01-01', '2024-12-31', 1),
    ('E-commerce Website', '2024-02-01', '2024-08-31', 2);

-- Insert sample tasks (optional - uncomment if needed)
INSERT INTO tasks(name, start_date, end_date, user_id, project_id, status_id) VALUES
    ('Database Design', '2024-01-01', '2024-01-15', 3, 1, 3),
    ('Backend Development', '2024-01-16', '2024-03-31', 3, 1, 2),
    ('Frontend Development', '2024-02-01', '2024-04-30', 3, 1, 1);

-- Useful queries for testing
-- SELECT * FROM roles;
-- SELECT * FROM status;
-- SELECT * FROM users u JOIN roles r ON u.role_id = r.id;
-- SELECT * FROM projects p JOIN users u ON p.user_id = u.id;
-- SELECT * FROM tasks t 
--     JOIN users u ON t.user_id = u.id
--     JOIN projects p ON t.project_id = p.id
--     JOIN status s ON t.status_id = s.id;
