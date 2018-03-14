CREATE TABLE outlet (id INT PRIMARY KEY, name VARCHAR(20), manager VARCHAR(20),
contact VARCHAR(15), location VARCHAR(20));
    
CREATE TABLE user ( id INT PRIMARY KEY, userid VARCHAR(20) UNIQUE KEY, name VARCHAR(20),  password VARCHAR(20),
outlet_id INT);

CREATE TABLE category (id INT PRIMARY KEY AUTO_INCREMENT,  name VARCHAR(20), description varchar(100), enabled char(1), 
					outlet_id INT , created_by VARCHAR(20),updated_by VARCHAR(20), created_at timestamp,updated_at timestamp);
					
CREATE TABLE product (id INT PRIMARY KEY AUTO_INCREMENT, category_id INT, name VARCHAR(20), description varchar(100), price FLOAT(6,2), 
					avail_qty INT, outlet_id INT,created_by VARCHAR(20),updated_by VARCHAR(20), created_at timestamp,updated_at timestamp);
					
CREATE TABLE ingredient (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), avail_qty INT, outlet_id INT);

CREATE TABLE product_ingredient_mapper ( product_id INT, ingredient_id INT);


--constraints
ALTER TABLE user ADD FOREIGN KEY (outlet_id) REFERENCES outlet(id);
ALTER TABLE category ADD FOREIGN KEY (outlet_id) REFERENCES outlet(id);
ALTER TABLE ingredient ADD FOREIGN KEY (outlet_id) REFERENCES outlet(id);
ALTER TABLE product ADD FOREIGN KEY (outlet_id) REFERENCES outlet(id);
ALTER TABLE product ADD FOREIGN KEY (category_id) REFERENCES outlet(id);