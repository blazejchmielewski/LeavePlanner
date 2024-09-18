CREATE TABLE leaves (
    lv_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lv_uuid VARCHAR(255),
    lv_start_date DATETIME,
    lv_end_date DATETIME,
    lv_type VARCHAR(255),
    lv_status VARCHAR(255),
    lv_days INT,
    lv_created_at DATETIME,
    lv_updated_at DATETIME,
    lv_settled_by_replacer_at DATETIME,
    lv_settled_by_acceptor_at DATETIME,
    lv_us_id BIGINT,
    lv_replacing_us_id BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (lv_us_id) REFERENCES users(id),
    CONSTRAINT fk_replacement_user FOREIGN KEY (lv_replacing_us_id) REFERENCES users(id)
);
