
--
-- Name: intervl; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE intervl (
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

CREATE TABLE read (
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

CREATE TABLE register (
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
-- Name: intervl intervl_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY intervl ADD CONSTRAINT intervl_pkey PRIMARY KEY (id);


--
-- Name: read read_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY read ADD CONSTRAINT read_pkey PRIMARY KEY (id);


--
-- Name: register register_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY register ADD CONSTRAINT register_pkey PRIMARY KEY (id);

--
-- Name: register fk7821xxcsf4mlf4xkb7n0nq3tt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY register ADD CONSTRAINT fk7821xxcsf4mlf4xkb7n0nq3tt FOREIGN KEY (read_id) REFERENCES read(id);

--
-- Name: intervl fkhy5g3xvftw59936pq6abgqwkd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY intervl ADD CONSTRAINT fkhy5g3xvftw59936pq6abgqwkd FOREIGN KEY (read_id) REFERENCES read(id);

