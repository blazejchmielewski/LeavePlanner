CREATE TABLE leave.tokens (
    tk_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tk_token VARCHAR(255),
    tk_us_id BIGINT,
    tk_revoked BOOLEAN,
    tk_expired BOOLEAN,
    FOREIGN KEY (tk_us_id) REFERENCES leave.users(us_id)
);
