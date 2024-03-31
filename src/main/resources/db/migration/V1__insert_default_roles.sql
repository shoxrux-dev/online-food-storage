create table IF NOT EXISTS roles(
    id SERIAL PRIMARY KEY,
    name varchar(255),
    created_at timestamp,
    updated_at timestamp,
    created_by INT,
    last_modified_by INT
);

insert into roles(name, created_at, updated_at, created_by) values ('SUPER_ADMIN', now(), now(), 1);
insert into roles(name, created_at, updated_at, created_by) values ('USER', now(), now(), 1);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by INT,
    last_modified_by INT
);

CREATE TABLE user_roles (
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE EXTENSION IF NOT EXISTS pgcrypto;

insert into users(username, password, status, created_at, updated_at, created_by)
values ('super-admin', crypt('super-admin', gen_salt('bf')), 'ACTIVE', now(), now(), 1);


insert into user_roles(user_id, role_id) values (1,1)