--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    default_price numeric(10,2),
    default_currency character varying(255),
    description text,
    productcategory_id integer,
    supplier_id integer
);


--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: productcategory; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.productcategory (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    department character varying(255) NOT NULL,
    description character varying(255)
);


--
-- Name: productcategory_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.productcategory_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: productcategory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.productcategory_id_seq OWNED BY public.productcategory.id;


--
-- Name: suppliers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.suppliers (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255)
);


--
-- Name: suppliers_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.suppliers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: suppliers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.suppliers_id_seq OWNED BY public.suppliers.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(255),
    email character varying(255),
    phone_number character varying(255),
    billing_address character varying(255),
    shipping_address character varying(255)
);


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: productcategory id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.productcategory ALTER COLUMN id SET DEFAULT nextval('public.productcategory_id_seq'::regclass);


--
-- Name: suppliers id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.suppliers ALTER COLUMN id SET DEFAULT nextval('public.suppliers_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.product (id, name, default_price, default_currency, description, productcategory_id, supplier_id) FROM stdin;
1	Amazon Fire,	49.90	USD	Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support	1	1
2	Lenovo IdeaPad Miix 700	479.00	USD	Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.	1	2
3	Amazon Fire HD 8	89.00	USD	Amazon's latest Fire HD 8 tablet is a great value for media consumption.	1	1
4	Samsung Notebook 9 Pro Pen	1049.99	USD	The Samsung Notebook 9 Pro Pen is engineered for people who are going places. You can count on exceptional processing power and fast charging to keep you moving forward and a lightweight frame that won’t weigh you down. Plus, with the versatility of 360-degree tablet mode, a touch screen, S Pen, and extreme processing power - consider yourself empowered!	2	3
5	Toshiba Portege Z30-A Laptop	299.00	USD	The Toshiba Portege Z30 is a business-oriented 13-inch Ultrabook. That gets you the slim profile and light body of an Ultrabook, but with the extra security and office-friendly features you’d expect of a business laptop.	2	4
6	Lenovo Flex 14 2-in-1 Convertible Laptop	650.00	USD	The Lenovo flex 14 Convertible touch screen laptop can help make your ideas happen.	2	2
7	Logitech HD Pro Webcam C920	59.90	USD	With spectacular video quality up to HD 1080p and dual built-in mics, C920 makes it a breeze to make your video presence stand out from the crowd.	3	5
8	Logitech HD Webcam C525	59.99	USD	For portable HD video calling and recording-with autofocus.	3	5
9	Microsoft Q2F-00013 USB 2.0 LifeCam Webcam	49.00	USD	The closest to being there. Experience the amazing clarity and detail of HD video with brilliant color and crystal-clear audio.	3	6
\.


--
-- Data for Name: productcategory; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.productcategory (id, name, department, description) FROM stdin;
1	Tablet	Hardware	A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.
2	Notebook	Hardware	A notebook is a small, portable personal computer (PC) with a \\"clamshell\\" form factor.
3	Webcam	Hardware	A webcam is a video camera that feeds or streams its image in real time to or through a computer to a computer network.
\.


--
-- Data for Name: suppliers; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.suppliers (id, name, description) FROM stdin;
1	Amazon	Digital content and services
2	Lenovo	Computers
3	Samsung	Information and communications technology, medical and health care services
4	Toshiba	Information technology and communications equipment and systems
5	Logitech	Provider of personal computer and mobile peripherals
6	Microsoft	Develops, manufactures, licenses, supports and sells computer software, consumer electronics, personal computers, and related services
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (id, name, email, phone_number, billing_address, shipping_address) FROM stdin;
\.


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_id_seq', 9, true);


--
-- Name: productcategory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.productcategory_id_seq', 3, true);


--
-- Name: suppliers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.suppliers_id_seq', 6, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: productcategory productcategory_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.productcategory
    ADD CONSTRAINT productcategory_pkey PRIMARY KEY (id);


--
-- Name: suppliers suppliers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.suppliers
    ADD CONSTRAINT suppliers_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: product product_productcategory_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_productcategory_id_fkey FOREIGN KEY (productcategory_id) REFERENCES public.productcategory(id);


--
-- Name: product product_supplier_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_supplier_id_fkey FOREIGN KEY (supplier_id) REFERENCES public.suppliers(id);


--
-- PostgreSQL database dump complete
--

