insert into pgdemo.users1(username, password_hash, email, first_name, last_name, phone_number, address, date_of_birth, is_active)
values('john_doesmith123', 'hash1h9101ibcd', 'john.doe@example.com', 'John', 'Doe', '123-456-7890', '123 Main St, Cityville', '1986-07-20',true);

insert into pgdemo.users1 (username, password_hash, email, first_name, last_name, phone_number, address, date_of_birth, is_active)
values ('jane_doesmithsmith', 'hashh9101ifgh', 'jane.smith@example.com', 'Jane', 'Smith', '456-789-1230', '456 Oak St, Townsville', '1985-07-20', true);

insert into pgdemo.users1 (username, password_hash, email, first_name, last_name, phone_number, address, date_of_birth, is_active)
values ('alex_alexbrown', 'hash9101ijkl', 'alex.brown@example.com', 'Alex', 'Brown', '789-123-4567', '789 Pine St, Hamlet', '1992-03-10', true);
select * from pgdemo.Users1 order by id asc limit 30;

