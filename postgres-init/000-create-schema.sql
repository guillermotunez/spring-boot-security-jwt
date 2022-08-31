CREATE TABLE ROLES (
    id                 SERIAL NOT NULL,
    name               TEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE USERS (
    id                 BIGSERIAL NOT NULL,
    email              TEXT NOT NULL,
    password           TEXT NOT NULL,
    username           TEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE USER_ROLES (
    user_id            BIGSERIAL NOT NULL,
    role_id            SERIAL NOT NULL,
    PRIMARY KEY (user_id, role_id),
	FOREIGN KEY (user_id) REFERENCES users (id),
	FOREIGN KEY (role_id) REFERENCES roles (id)
);