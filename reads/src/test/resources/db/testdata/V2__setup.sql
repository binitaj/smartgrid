--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

create sequence hibernate_sequence;

CREATE TABLE PUBLIC.account (
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

CREATE TABLE PUBLIC.address (
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

CREATE TABLE PUBLIC.device (
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
-- Name: intervl; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE PUBLIC.intervl (
    id bigint NOT NULL,
    ds character varying(255),
    et date,
    il character varying(255),
    it date,
    m character varying(255),
    mul double precision NOT NULL,
    rq character varying(255),
    v double precision NOT NULL,
    read_id character varying(255)
);


--
-- Name: read; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE PUBLIC.read (
    id character varying(255) NOT NULL,
    commodity character varying(255),
    created_at date,
    last_updated_at date,
    radio_id bigint NOT NULL,
    device_id bigint NOT NULL
);


--
-- Name: register; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE PUBLIC.register (
    id bigint NOT NULL,
    ds character varying(255),
    et date,
    il character varying(255),
    it date,
    m character varying(255),
    mul double precision NOT NULL,
    rq character varying(255),
    v double precision NOT NULL,
    read_id character varying(255)
);

INSERT INTO account (id, bc, name, s, st, address_id) VALUES ('032-002790-01', '03', 'Joe Smith', 'A', 'Residential', 1);

INSERT INTO address (id, city, geohash, lat, line1, lon, state, zip) VALUES (1, 'Atlanta', '9v6mwd0y', 30.590144800000001, '888 Smart Grid St', -97.790053999999998, 'GA', '30004');

INSERT INTO device (device_number, amr_identifier, device_type, meter_size, meter_type, meterid_2, service_route, status, usage_uom, account_id) VALUES (75118300, '86696858', 'METER', 0.69999999999999996, 'WATER', '82550377', '032', 'ACTIVE', 'GAL', '032-002790-01');
INSERT INTO device (device_number, amr_identifier, device_type, meter_size, meter_type, meterid_2, service_route, status, usage_uom, account_id) VALUES (35653439, '86696858', 'METER', 0.75, 'WATER', '82550377', '032', 'ACTIVE', 'GAL', '032-002790-01');

INSERT INTO read (id, commodity, created_at, last_updated_at, radio_id, device_id) VALUES ('35653439;85967588;20181208', 'WATER', '2018-12-07', '2018-12-08', 0, 35653439);


INSERT INTO register (id, ds, et, il, it, m, mul, rq, v, read_id) VALUES (18, 'CEDAR-ReadReport.20190409060000.csv;3289', NULL, '', NULL, 'GAL', 1, 'R', 386200, '35653439;85967588;20181208');
INSERT INTO register (id, ds, et, il, it, m, mul, rq, v, read_id) VALUES (19, 'CEDAR-IntervalReport.20190409060003.csv;6543', NULL, '', NULL, 'GAL', 1, 'R', 386207.70000000001, '35653439;85967588;20181208');
INSERT INTO register (id, ds, et, il, it, m, mul, rq, v, read_id) VALUES (20, 'CEDAR-ReadReport.20190409100001.csv;3288', NULL, '', NULL, 'GAL', 1, 'R', 386254, '35653439;85967588;20181208');


INSERT INTO intervl (id, ds, et, il, it, m, mul, rq, v, read_id) VALUES (6012, 'CEDAR-IntervalReport.20181203220006.csv;9627', NULL, '', NULL, 'GAL', 1, 'R', 71, '35653439;85967588;20181208');
INSERT INTO intervl (id, ds, et, il, it, m, mul, rq, v, read_id) VALUES (6013, 'CEDAR-IntervalReport.20181204020001.csv;9754', NULL, '', NULL, 'GAL', 1, 'R', 46, '35653439;85967588;20181208');
INSERT INTO intervl (id, ds, et, il, it, m, mul, rq, v, read_id) VALUES (6014, 'CEDAR-IntervalReport.20181204220001.csv;9640', NULL, '', NULL, 'GAL', 1, 'R', 50, '35653439;85967588;20181208');
