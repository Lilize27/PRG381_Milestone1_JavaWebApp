-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    student_number VARCHAR(20) PRIMARY KEY,
    name           VARCHAR(100) NOT NULL,
    surname        VARCHAR(100) NOT NULL,
    email          VARCHAR(100) UNIQUE NOT NULL,
    phone          VARCHAR(20) NOT NULL,
    password       VARCHAR(256) NOT NULL
);

ALTER TABLE public.users OWNER TO postgres;
