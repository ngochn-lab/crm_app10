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
    ('Chưa Thực Hiện'),
    ('Đang Thực Hiện'),
    ('Đã Hoàn Thành');

-- Insert sample users (optional - uncomment if needed)
INSERT INTO users(email, password, fullname, avatar, role_id) VALUES
    ('admin@gmail.com', '123456', 'Quản Trị Hệ Thống', 'https://gravatar.com/avatar/521c92264476cc8c9289213e9a87a4c4?s=400&d=robohash&r=x', 1),
    ('manager1@gmail.com', '123456', 'Quản Lý Dự Án 1', 'https://gravatar.com/avatar/e799da9a2398eb8601d0bd3847433fc9?s=400&d=robohash&r=x', 2),
    ('manager2@gmail.com', '123456', 'Quản Lý Dự Án 2', 'https://gravatar.com/avatar/017f06f6e48b8efb18e87af8df6af767?s=400&d=robohash&r=x', 2),
    ('member1@gmail.com', '123456', 'Thành Viên 1', 'https://gravatar.com/avatar/bd4976bc09262c06dd86334fd84115a9?s=400&d=robohash&r=x', 3),
    ('member2@gmail.com', '123456', 'Thành Viên 2', 'https://gravatar.com/avatar/bd4976bc09262c06dd86334fd84115a9?s=400&d=robohash&r=x', 3),
    ('member3@gmail.com', '123456', 'Thành Viên 3', 'https://gravatar.com/avatar/bd4976bc09262c06dd86334fd84115a9?s=400&d=robohash&r=x', 3);

-- Insert sample projects (optional - uncomment if needed)
INSERT INTO projects(name, start_date, end_date, user_id) VALUES
    ('CRM Application', '2025-09-09', '2025-12-31', 2),
    ('E-commerce Website', '2025-02-01', '2026-08-31', 2),
    ('Azure Cloud Middleware', '2025-10-20', '2028-08-31', 3);

-- Insert sample tasks (optional - uncomment if needed)
INSERT INTO tasks(name, start_date, end_date, user_id, project_id, status_id) VALUES
    ('Database Design', '2025-01-01', '2026-01-15', 4, 1, 3),
    ('Backend Development', '2025-01-16', '2027-03-31', 4, 1, 2),
    ('Frontend Development', '2025-02-01', '2027-04-30', 5, 1, 1),
    ('Database Design', '2025-01-01', '2027-01-15', 6, 2, 3),
    ('Backend Development', '2025-01-16', '2027-03-31', 6, 2, 2),
    ('Frontend Development', '2025-02-01', '2029-04-30', 5, 2, 1),
    ('Database Design', '2025-10-21', '2026-01-15', 4, 3, 3),
    ('Backend Development', '2025-11-16', '2026-03-31', 4, 3, 2),
    ('Frontend Development', '2026-01-01', '2027-04-30', 5, 3, 1);