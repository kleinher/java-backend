CREATE TABLE prices (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        brand_id BIGINT NOT NULL,
                        start_date TIMESTAMP NOT NULL,
                        end_date TIMESTAMP NOT NULL,
                        price_list INT NOT NULL,
                        product_id BIGINT NOT NULL,
                        priority INT NOT NULL,
                        product DECIMAL(19, 2) NOT NULL,
                        currency VARCHAR(3) NOT NULL
);
