CREATE TABLE leave.day_offs (
    doff_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    holy_name VARCHAR(255),
    holiday_year INT,
    day_off DATE UNIQUE
);
