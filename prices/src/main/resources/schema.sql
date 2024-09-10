CREATE TABLE brands (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(10) NOT NULL
);

CREATE TABLE prices (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        brand_id BIGINT,
                        start_date TIMESTAMP NOT NULL,
                        end_date TIMESTAMP NOT NULL,
                        price_list INT NOT NULL,
                        product_id BIGINT NOT NULL,
                        priority INT NOT NULL,
                        price DECIMAL(19, 2) NOT NULL,
                        currency VARCHAR(3) NOT NULL,
                        CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES brands(id)
);