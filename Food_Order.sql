-- CREATE DATABASE FOOD_ORDER;

-- Tạo bảng Tài khoản

CREATE TABLE account (
	id_account int IDENTITY(1, 1),
	email varchar(50) NOT NULL,
	phone varchar(10),
	name nvarchar(50),
	created_time datetime DEFAULT current_timestamp NOT NULL,
	address ntext,
	avatar text,
	status int DEFAULT 0 CHECK (status >= 0 AND status <= 1),
		-- 0: Hoạt động
		-- 1: Khóa
	password text NOT NULL,

	PRIMARY KEY(id_account),
	UNIQUE(email)
);

-- 
INSERT INTO account (email, name, password) 
VALUES ('admin.foodorder@gmail.com', N'Admin', '123456');

INSERT INTO account (email, phone, name, address, avatar, password)
VALUES ('vinhvipvl@gmail.com', '0123456789', N'Trần Quang Vinh', N'Q9, TPHCM', 'resources/img/avatar/avatar.jpg', 'e10adc3949ba59abbe56e057f20f883e'),
('phuc@gmail.com', '0654231789', N'Hồng Phúc', N'Q9, TPHCM', 'resources/img/avatar/avatar.jpg', 'e10adc3949ba59abbe56e057f20f883e');

--------------------------------
-- Tạo bảng Danh mục

CREATE TABLE category (
	id_category int IDENTITY(1, 1),
	name nvarchar(255) NOT NULL,
	logo text,

	PRIMARY KEY(id_category),
	UNIQUE(name)
);

-- 
INSERT INTO category (name) VALUES (N'Cơm'), (N'Đồ ăn vặt')

-------------------------------
-- Tạo bảng Món ăn

CREATE TABLE food (
	id_food int IDENTITY(1,1),
	id_category int NOT NULL,
	name nvarchar(255) NOT NULL,
	detail ntext,
	price int DEFAULT 0,
	images text,
	type int DEFAULT 0 CHECK (type >= 0 AND type <= 1),
		-- 0: Còn món
		-- 1: Hết món
	status int DEFAULT 0 CHECK (status >= 0 AND status <= 1),
		-- 0: Hiện
		-- 1: Ẩn

	PRIMARY KEY (id_food)
)

ALTER TABLE food ADD CONSTRAINT fk_food_category 
FOREIGN KEY (id_category) REFERENCES category(id_category)
ON DELETE CASCADE;

-- 
INSERT INTO food (id_category, name, detail, price) 
VALUES 
(1, N'Cơm tấm sườn', N'Hơi bị ngon, mua đi', 25000),
(2, N'Bánh tráng trộn', N'Mua nhanh kẻo hết', 10000)


--------------------------------
-- Tạo bảng Giỏ hàng

CREATE TABLE cart (
	id_account int NOT NULL,
	id_food int NOT NULL,
	quantity int DEFAULT 1 CHECK (quantity >= 1),

	PRIMARY KEY (id_account, id_food)
)

ALTER TABLE cart ADD CONSTRAINT fk_cart_account 
FOREIGN KEY (id_account) REFERENCES account(id_account)
ON DELETE CASCADE;

ALTER TABLE cart ADD CONSTRAINT fk_cart_food 
FOREIGN KEY (id_food) REFERENCES food(id_food)
ON DELETE CASCADE;

-- 
INSERT INTO cart (id_account, id_food, quantity) 
VALUES (2, 1, 1), (3, 2, 3);


-------------------------------
-- Tạo bảng Phiếu giảm giá

CREATE TABLE coupons (
	id_coupons varchar(50),
	detail ntext,
	type int CHECK (type >= 0 AND type <= 1),
		-- 0: Giảm theo tiền
		-- 1: Giảm theo phần trăm
	value int,
		-- Mức giảm
	expired_time datetime,
	amount int,
	status int CHECK (status >= 0 AND status <= 1),
		-- 0: Công khai, ai cũng sử dụng được
		-- 1: Chỉ id_account mới được sử dụng

	PRIMARY KEY (id_coupons)
)

-- 
INSERT INTO coupons (id_coupons, detail, type, value, expired_time, amount, status)
VALUES ('FREEALL', N'Mã chào mừng khai trương quán', 0, 50000, '2021-12-31 08:04:19.567', 100, 0);


--------------------------------
-- Tạo bảng Đơn đặt món

CREATE TABLE orders (
	id_order int IDENTITY(1,1),
	order_time datetime DEFAULT current_timestamp NOT NULL,
	delivery_time datetime DEFAULT current_timestamp NOT NULL,
	id_account int NOT NULL,
	id_coupons varchar(50) DEFAULT NULL,
	status int DEFAULT 0 CHECK (status >= 0 AND status <= 3),
		-- 0: Chưa xác nhận
		-- 1: Đã xác nhận
		-- 2: Đã giao
		-- 3: Hủy đơn

	PRIMARY KEY (id_order)
)

ALTER TABLE orders ADD CONSTRAINT fk_orders_account 
FOREIGN KEY (id_account) REFERENCES account(id_account)
ON DELETE CASCADE;

ALTER TABLE orders ADD CONSTRAINT fk_orders_coupons
FOREIGN KEY (id_coupons) REFERENCES coupons(id_coupons);

-- 
INSERT INTO orders (id_account, status) VALUES (2, 0);

------------------------------
-- Tạo bảng Chi tiết đặt mòn

CREATE TABLE order_detail (
	id_order int NOT NULL,
	id_food int NOT NULL,
	amount int CHECK (amount >= 1),
	price int,

	PRIMARY KEY (id_order, id_food)
)

ALTER TABLE order_detail ADD CONSTRAINT fk_orderdetail_order 
FOREIGN KEY (id_order) REFERENCES orders(id_order)
ON DELETE CASCADE;

ALTER TABLE order_detail ADD CONSTRAINT fk_orderdetail_food 
FOREIGN KEY (id_food) REFERENCES food(id_food)
ON DELETE CASCADE;

-- 
INSERT INTO order_detail (id_order, id_food, amount, price) 
VALUES (1, 1, 1, 25000);

-------------------------
-- Tạo bảng Đánh giá món ăn

CREATE TABLE rated (
	id_food int NOT NULL,
	id_account int NOT NULL,
	star int CHECK (star >= 1 AND star <= 5),
		-- Đáh giá từ 1 đến 5 sao
	comment ntext,
	status int DEFAULT 0 CHECK (status >= 0 AND status <= 1),
		-- 0: Hiện
		-- 1: Ẩn
	cmt_time datetime DEFAULT current_timestamp,
	PRIMARY KEY (id_food, id_account)
)

ALTER TABLE rated ADD CONSTRAINT fk_rated_account 
FOREIGN KEY (id_account) REFERENCES account(id_account)
ON DELETE CASCADE;

ALTER TABLE rated ADD CONSTRAINT fk_rated_food 
FOREIGN KEY (id_food) REFERENCES food(id_food)
ON DELETE CASCADE;

-- 
INSERT INTO rated (id_food, id_account, star, comment)
VALUES (1, 2, 5, N'Đồ ăn ngon');