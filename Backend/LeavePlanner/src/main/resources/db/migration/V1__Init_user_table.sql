CREATE TABLE users (
    us_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    us_firstname NVARCHAR(255),
    us_lastname NVARCHAR(255),
    us_email NVARCHAR(255) UNIQUE,
    us_password NVARCHAR(255),
    us_uuid NVARCHAR(255),
    us_role NVARCHAR(50),
    us_available_days INT,
    us_years_of_work INT,
    us_department NVARCHAR(50),
    us_enabled BIT,
    us_created_at DATE,
    us_updated_at DATE,
    us_deactivated_at DATE
);
