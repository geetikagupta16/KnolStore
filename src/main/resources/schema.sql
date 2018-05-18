create table if not exists item_detail(
item_id int auto_increment,
item_name varchar(50),
price double,
primary key(item_id));

create table if not exists employee_details(
emp_id int,
emp_name varchar(50),
primary key(emp_id));

create table if not exists employee_transaction(
emp_id int,
item_id int,
transaction_date date,
quantity int,
amount double,
is_paid bit,
constraint item_id_fk foreign key (item_id) references item_detail(item_id),
constraint emp_id_fk foreign key (emp_id) references employee_details(emp_id)
);
