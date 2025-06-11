insert into pgdemo."users1" (username, password_hash, email, first_name, last_name, phone_number, address, date_of_birth, is_active, created_at, updated_at)
values
    ('john_doe', 'hashed_password_1', 'john.doe@example.com', 'John', 'Doe', '123-456-7890', '123 Main St', '1990-05-15', true, current_timestamp, current_timestamp),
    ('jane_smith', 'hashed_password_2', 'jane.smith@example.com', 'Jane', 'Smith', '098-765-4321', '456 Oak Ave', '1985-08-22', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('bob_jones', 'hashed_password_3', 'bob.jones@example.com', 'Bob', 'Jones', '555-555-5555', '789 Pine Rd', '1995-03-10', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);