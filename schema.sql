CREATE TABLE suppliers (
    supplier_id SERIAL PRIMARY KEY,
    name TEXT,
    country TEXT,
    contact_name TEXT,
    phone TEXT,
    email TEXT,
    rating REAL,
    is_active BOOLEAN
);

CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    name TEXT,
    sku TEXT,
    category TEXT,
    unit TEXT,
    description TEXT
);

CREATE TABLE supplier_products (
    id SERIAL PRIMARY KEY,
    supplier_id INTEGER REFERENCES suppliers(supplier_id),
    product_id INTEGER REFERENCES products(product_id),
    supplier_sku TEXT,
    supplier_name TEXT,
    price NUMERIC,
    currency TEXT,
    min_order_qty NUMERIC,
    lead_time_days INTEGER,
    is_active BOOLEAN
);

CREATE TABLE supply_orders (
    order_id SERIAL PRIMARY KEY,
    supplier_id INTEGER REFERENCES suppliers(supplier_id),
    order_date DATE,
    delivery_date DATE,
    status TEXT,
    total_amount NUMERIC
);

CREATE TABLE order_items (
    item_id SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES supply_orders(order_id),
    supplier_product_id INTEGER REFERENCES supplier_products(id),
    quantity NUMERIC,
    price_per_unit NUMERIC
);

CREATE TABLE warehouses (
    warehouse_id SERIAL PRIMARY KEY,
    name TEXT,
    location TEXT,
    capacity NUMERIC
);

CREATE TABLE inventory (
    product_id INTEGER REFERENCES products(product_id),
    warehouse_id INTEGER REFERENCES warehouses(warehouse_id),
    quantity NUMERIC,
    last_updated TIMESTAMP,
    PRIMARY KEY (product_id, warehouse_id)
);
