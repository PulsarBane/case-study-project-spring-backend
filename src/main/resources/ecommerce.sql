CREATE TABLE user_info(user_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
					   username VARCHAR(50) NOT NULL,
					   password VARCHAR(50) NOT NULL,
					   first_name VARCHAR(50) NOT NULL,
					   last_name VARCHAR(50) NOT NULL,
					   email VARCHAR(50) NOT NULL,
					   user_type VARCHAR(50) NOT NULL);

CREATE TABLE orders(order_no SERIAL PRIMARY KEY,
					user_id INT NOT NULL,
					order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
					order_status BOOLEAN DEFAULT FALSE,
					CONSTRAINT fk_user
      	            FOREIGN KEY(user_id) 
	                REFERENCES user_info(user_id));
	                			 
	           
CREATE TABLE order_items(items_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
						 order_no SERIAL NOT NULL,
						 product_sku INT NOT NULL,
						 CONSTRAINT fk_order
      	                 FOREIGN KEY(order_no) 
	                     REFERENCES orders(order_no));
					   

CREATE TABLE products(product_sku INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      product_name VARCHAR(50) NOT NULL,
                      product_image VARCHAR(500) NOT NULL,
                      product_quantity INT NOT NULL,
                      product_price DOUBLE PRECISION NOT NULL);



