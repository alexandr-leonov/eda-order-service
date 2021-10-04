-- manual load because for r2dbc not created core migration
CREATE TABLE IF NOT EXISTS STORES (
   store_id VARCHAR(18) DEFAULT GEN_RANDOM_UUID() PRIMARY KEY,
   name     VARCHAR(120) NOT NULL,
   phone    VARCHAR(14) NOT NULL
);

CREATE TABLE IF NOT EXISTS ORDERS (
    order_id    VARCHAR(18) DEFAULT GEN_RANDOM_UUID() PRIMARY KEY,
    user_id     NUMERIC(20) NOT NULL,
    store_id    VARCHAR(18) NOT NULL,
    name        VARCHAR(240) NOT NULL,
    amount      DECIMAL NOT NULL,
    description TEXT DEFAULT '',
    state       VARCHAR(10) DEFAULT 'NEW' NOT NULL
);