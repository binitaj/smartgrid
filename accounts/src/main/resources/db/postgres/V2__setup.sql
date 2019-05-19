--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE account (
    id character varying(255) NOT NULL,
    bc character varying(255),
    name character varying(255),
    s character varying(255),
    st character varying(255),
    address_id bigint
);



--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE address (
    id bigint NOT NULL,
    city character varying(255),
    geohash character varying(255),
    lat double precision NOT NULL,
    line1 character varying(255),
    lon double precision NOT NULL,
    state character varying(255),
    zip character varying(255)
);



--
-- Name: device; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE device (
    device_number bigint NOT NULL,
    amr_identifier character varying(255),
    device_type character varying(255),
    meter_size double precision NOT NULL,
    meter_type character varying(255),
    meterid_2 character varying(255),
    service_route character varying(255),
    status character varying(255),
    usage_uom character varying(255),
    account_id character varying(255)
);



--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO account (id, bc, name, s, st, address_id) VALUES ('032-002790-01', '03', 'Joe Smith', 'A', 'Residential', 1);
INSERT INTO account (id, bc, name, s, st, address_id) VALUES ('046-087070-04', '04', 'Joe Foo', 'A', 'Residential', 2);
INSERT INTO account (id, bc, name, s, st, address_id) VALUES ('093-000110-07', '04', 'Jenna Smith', 'A', 'Non-Residential', 3);


--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO address (id, city, geohash, lat, line1, lon, state, zip) VALUES (1, 'Atlanta', '9v6mwd0y', 30.590144800000001, '888 Smart Grid St', -97.790053999999998, 'GA', '30004');
INSERT INTO address (id, city, geohash, lat, line1, lon, state, zip) VALUES (2, 'Atlanta', '9v6mt7sv', 30.5191704, '1234 Smart Grid Dr', -97.845569999999995, 'GA', '30004');
INSERT INTO address (id, city, geohash, lat, line1, lon, state, zip) VALUES (3, 'Atlanta', '9v6mt70m', 30.515900299999998, '122 Smart Grid Ln', -97.851667000000006, 'GA', '30004');


--
-- Data for Name: device; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO device (device_number, amr_identifier, device_type, meter_size, meter_type, meterid_2, service_route, status, usage_uom, account_id) VALUES (75156022, '86810356', 'METER', 0.65000000000000002, 'WATER', '82646323', '046', 'ACTIVE', 'GAL', '046-087070-04');
INSERT INTO device (device_number, amr_identifier, device_type, meter_size, meter_type, meterid_2, service_route, status, usage_uom, account_id) VALUES (75116341, '87481355', 'METER', 0.625, 'WATER', '82548418', '093', 'ACTIVE', 'GAL', '093-000110-07');
INSERT INTO device (device_number, amr_identifier, device_type, meter_size, meter_type, meterid_2, service_route, status, usage_uom, account_id) VALUES (75118300, '86696858', 'METER', 0.69999999999999996, 'WATER', '82550377', '032', 'ACTIVE', 'GAL', '032-002790-01');
INSERT INTO device (device_number, amr_identifier, device_type, meter_size, meter_type, meterid_2, service_route, status, usage_uom, account_id) VALUES (35653439, '86696858', 'METER', 0.75, 'WATER', '82550377', '032', 'ACTIVE', 'GAL', '032-002790-01');


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY address ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: device device_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY device ADD CONSTRAINT device_pkey PRIMARY KEY (device_number);

--
-- Name: account fk9lna4d7ow9qbs27m5psafys58; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account ADD CONSTRAINT fk9lna4d7ow9qbs27m5psafys58 FOREIGN KEY (address_id) REFERENCES address(id);

--
-- PostgreSQL database dump complete
--

