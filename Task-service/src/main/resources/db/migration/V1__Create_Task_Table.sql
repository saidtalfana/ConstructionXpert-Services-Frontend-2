-- V1__Create_Task_Table.sql
CREATE TABLE task (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      project_id INT,
                      heurs TIME,
                      start_date DATE,
                      end_date DATE,
                      status VARCHAR(255),
                      description TEXT
);
