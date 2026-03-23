CREATE TABLE fee_config (
    fee_id SERIAL PRIMARY KEY,
    fee_code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    container_type_id INT,
    base_lift_on NUMERIC(12,2) DEFAULT 0,
    base_lift_off NUMERIC(12,2) DEFAULT 0,
    storage_fee_per_day NUMERIC(12,2) DEFAULT 0,
    reefer_fee_per_day NUMERIC(12,2) DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (container_type_id) REFERENCES container_types(container_type_id)
);

CREATE TABLE invoice (
    invoice_id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    total_amount NUMERIC(12,2) NOT NULL,
    status VARCHAR(30) DEFAULT 'UNPAID',
    note VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE payment (
    payment_id SERIAL PRIMARY KEY,
    invoice_id INT NOT NULL,
    amount NUMERIC(12,2) NOT NULL,
    method VARCHAR(30),
    transaction_ref VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id)
);
