CREATE TABLE project (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         budget DOUBLE NOT NULL,
                         heurs TIME,
                         start_date DATE,
                         end_date DATE,
                         description TEXT
);