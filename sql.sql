create database osahaneat;

drop database osahaneat

use osahaneat;

-- 
create table roles(
	id int auto_increment,
    role_name varchar(20),
    create_date timestamp,
    primary key (id)
);
-- 
create table users(
	id int auto_increment,
    user_name varchar(100),
    password varchar(100),
    full_name varchar(50),
    create_date timestamp,
    primary key (id),
    role_id int
    
);

-- 
create table rating_food(
	id int auto_increment,
    user_id int,
    food_id int,
    content text,
    rate_point int(8),
    
    primary key(id)
);

-- 
create table category(
	id int auto_increment,
    name_cate varchar(100),
    create_date timestamp,
    
    primary key(id)
);

-- 
create table food(
	id int auto_increment,
    title varchar(100),
    image text,
    time_ship varchar(10),
    price decimal,
    cate_id int,
    
    primary key(id)
);

-- 
create table rating_restaurant(
	id int auto_increment,
    user_id int,
    res_id int,
    content text,
    rate_point int(8),
    
    primary key (id)
);

-- 
create table orders(
	id int auto_increment,
    user_id int ,
    res_id int,
    create_date timestamp,
    
    primary key(id)
);


-- 
create table menu_restaurant(
	cate_id int,
    res_id int,
    create_date timestamp,
    
    primary key(cate_id,res_id)
);

-- 
create table restaurant(
	id int auto_increment,
    title varchar(255),
    subtitle varchar(255),
    description text,
    image text,
    is_freeship boolean,
    address varchar(255),
    open_date timestamp,
    
    primary key(id)
);

select * from restaurant
-- 
create table promo(
	id int auto_increment,
    res_id int,
    percent int,
    start_date timestamp,
    end_date timestamp,
    
    primary key(id)
);

-- 
create table order_item(
	order_id int,
    food_id int,
    create_date timestamp,
    
    primary key(order_id,food_id)
);


alter table users add constraint fk_users_role_id foreign key(role_id) references roles(id);

alter table rating_food add constraint fk_rating_food_user_id foreign key(user_id) references users(id);
alter table rating_food add constraint fk_rating_food_food_id foreign key(food_id) references food(id);

alter table food add constraint fk_food_cate_id foreign key(cate_id) references category(id);

alter table rating_restaurant add constraint fk_rating_restaurant_user_id foreign key(user_id) references users(id);
alter table rating_restaurant add constraint fk_rating_restaurant_res_id foreign key(res_id) references restaurant(id);

alter table orders add constraint fk_orders_user_id foreign key(user_id) references users(id);
alter table orders add constraint fk_orders_res_id foreign key(res_id) references restaurant(id);

alter table food add column is_freeship boolean

select * from food 

select * from menu_restaurant



select * from category

alter table order_item add constraint fk_order_item_order_id foreign key(order_id) references orders(id);
alter table order_item add constraint fk_order_item_food_id foreign key(food_id) references food(id);

alter table menu_restaurant add constraint fk_menu_restaurant_cate_id foreign key(cate_id) references category(id);
alter table menu_restaurant add constraint fk_menu_restaurant_res_id foreign key(res_id) references restaurant(id);

alter table promo add constraint fk_promo_res_id foreign key(res_id) references restaurant(id);

insert into roles(role_name) values("ROLE_ADMIN");
insert into roles(role_name) values("ROLE_USER");

SELECT * FROM ROLES

insert into Users(
    user_name ,
    password ,
    full_name ,
    role_id
) values("nguyen van a","123","nguyen van a",1)

insert into Users(
    user_name ,
    password ,
    full_name ,
    role_id
) values("nguyen van b","123","nguyen van b",2)

use osahaneat 

select * from users
select * from Restaurant;
select * from Rating_Restaurant;

select lrr1_0.res_id,lrr1_0.id,lrr1_0.content,lrr1_0.rate_point,u1_0.id,u1_0.create_date,u1_0.full_name,u1_0.password,r2_0.id,r2_0.create_date,r2_0.role_name,u1_0.user_name from rating_restaurant lrr1_0 left join users u1_0 on u1_0.id=lrr1_0.user_id left join roles r2_0 on r2_0.id=u1_0.role_id where lrr1_0.res_id=2;

select Restaurant.id, rate_point
from Restaurant, Rating_Restaurant
where Restaurant.id =  Rating_Restaurant.res_id


select * from Menu_Restaurant
select * from category
select * from restaurant

select * from orders 

select * from order_item

select * from food

select * from 

select * from roles


create database learn_security

use osahaneat 



