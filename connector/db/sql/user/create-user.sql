INSERT INTO users(first_name, last_name, phone, email)
VALUES (:firstName, :lastName, :phone, :email)
returning *;
