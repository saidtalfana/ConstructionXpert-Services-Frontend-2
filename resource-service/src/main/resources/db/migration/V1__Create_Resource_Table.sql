-- V1__Create_Resource_Table.sql
CREATE TABLE resource (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          task_id INT,
                          resource_name VARCHAR(255) NOT NULL,
                          resource_type VARCHAR(255) NOT NULL,
                          date DATE
);
